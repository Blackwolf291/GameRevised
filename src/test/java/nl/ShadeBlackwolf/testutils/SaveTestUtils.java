package nl.ShadeBlackwolf.testutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class SaveTestUtils {

	public void destroyAllSaves() {
		File saves = new File("saves");
		for (File s : saves.listFiles()){
			s.delete();
		}
	}
	

	public Map<String, String> parseFile(File file) throws IOException {
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			return parseFileToMap(br.lines().toArray(String[]::new));
		}
	}

	private Map<String, String> parseFileToMap(String[] fileContent) {
		Map<String, String> fieldsInSaveFile = new HashMap<>();
		for (String line : fileContent){
			String[] entry = line.split(":");
			fieldsInSaveFile.put(entry[0], entry[1]);
		}
		return fieldsInSaveFile;
	}
	

}
