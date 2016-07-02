package nl.ShadeBlackwolf.persistance;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.ShadeBlackwolf.ui.PersistenceUserRequestHandler;

@Component
public class Loader {

	@Autowired
	private Savable[] savables;
	
	@Autowired
	private PersistenceUserRequestHandler user;
	
	private Map<String, String> storedData = new HashMap<>();
	private File saveFolder = new File ("saves");
	
	@PostConstruct
	public void makeSaveDirectory(){
		if (!saveFolder.exists()){saveFolder.mkdirs();}
	}
	
	public void load() {
		clearCache();
		File save = user.askWhichLoad(saveFolder.listFiles());
		try {
			parseFile(save);
		} catch (IOException e) {
			throw new LoadCorrupted(e);
		}
		for (Savable savable: savables){
			savable.restoreFields(storedData);
		}
	}
	
	private void clearCache() {
		storedData = new HashMap<>();
	}

	private void parseFile(File file) throws IOException {
		try(FileReader fr = new FileReader(file);BufferedReader br = new BufferedReader(fr)){
			parseFileToMap(br.lines().toArray(String[]::new));
		}
	}

	private void parseFileToMap(String[] fileContent) {
		for (String line : fileContent){
			String[] entry = line.split(":");
			storedData.put(entry[0], entry[1]);
		}
	}

	private class LoadCorrupted extends RuntimeException {
		private static final long serialVersionUID = 1L;
		LoadCorrupted(Exception e){
			super(e);
		}
	}
	
}
