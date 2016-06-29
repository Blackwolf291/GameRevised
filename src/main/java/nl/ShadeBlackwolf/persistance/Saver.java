package nl.ShadeBlackwolf.persistance;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.ShadeBlackwolf.UI;
import nl.ShadeBlackwolf.exceptions.Cancel;
import nl.ShadeBlackwolf.ui.UserRequestHandler;

@Component
public class Saver {

	@Autowired
	private Savable[] savables;
	
	@Autowired
	private UserRequestHandler user;
	
	private File saveFolder = new File ("saves");
	private Map<String, String> saveData = new HashMap<>();
	
	@Autowired
	private UI ui;
	
	@PostConstruct
	private void createSaveFolder(){
		if (!saveFolder.exists()){
			saveFolder.mkdir();
		}
	}
	
	public void autoSave() {
		String fileName = "AutoSave_" + saveData.get("name") + ".save";
		gatherSaveData();
		save(fileName);
		ui.println("Autosaved");
	}

	public void manualSave() {
		try {
			String fileName = user.askSaveName(saveFolder.listFiles()) + ".save";
			gatherSaveData();
			if (new File(saveFolder, fileName).exists()){
				if(user.askConfirmOverwrite()){
					save(fileName);
				} else {
					manualSave();
				}
			} else {
				save(fileName);
			}
			ui.println("Saved");
		} catch (Cancel c){
			//skip to the end of the save
		}
	}

	private void save(String fileName) {
		destroySaveIfExists(fileName);
		gatherSaveData();
		File file = new File(saveFolder, fileName);
		createFile(file);
		storeData(file);
	}

	private void destroySaveIfExists(String fileName) {
		File file = new File(saveFolder, fileName);
		file.delete();
	}
	
	private void storeData(File file) {
		try(PrintWriter out = new PrintWriter(file)){
			writeDataToFile(out);
		} catch (FileNotFoundException e) {
			throw new SaveFailedException(e);
		}
	}

	private void writeDataToFile(PrintWriter out) {
		for (String key : saveData.keySet()){
			out.println(key + ":" + saveData.get(key));
		}
	}

	private void gatherSaveData() {
		for (Savable savable: savables){
			saveData.putAll(savable.getPersistMap());
		}
	}

	private void createFile(File file) {
		try {
			file.createNewFile();
		} catch (IOException e) {
			throw new SaveFailedException(e);
		}
	}
	private class SaveFailedException extends RuntimeException{
		
		private static final long serialVersionUID = 1L;

		SaveFailedException(Exception e){
			super(e);
		}
	}
	
}
