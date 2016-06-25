package nl.ShadeBlackwolf.persistance;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Loader {

	@Autowired
	private Savable[] savables;
	
	private Map<String, String> storedData = new HashMap<>();
	private File saveFolder = new File ("saves");
	
	public void load() {
		String[] saveNames = new String[saveFolder.listFiles().length];
		saveFolder.listFiles();
		for (Savable savable: savables){
			savable.restoreFields(storedData);
		}
	}

}
