package nl.ShadeBlackwolf.testutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.ShadeBlackwolf.player.Player;

@Component
public class SaveTestUtils {

	@Autowired
	private Player player;
	
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
	

	public Object getPrivatePlayerField(String field) throws IllegalAccessException, NoSuchFieldException {
		Field f = Player.class.getDeclaredField(field);
		f.setAccessible(true);
		return f.get(player);
	}
	
}
