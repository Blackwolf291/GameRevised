package nl.ShadeBlackwolf.persistance;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.ShadeBlackwolf.ui.UserRequestHandler;

@Component
public class Loader {

	@Autowired
	private Savable[] savables;
	
	@Autowired
	private UserRequestHandler user;
	
	private Map<String, String> storedData = new HashMap<>();
	private File saveFolder = new File ("saves");
	
	public void load() {
		File save = user.askWhichLoad(saveFolder.listFiles());
		for (Savable savable: savables){
			savable.restoreFields(storedData);
		}
	}

}
