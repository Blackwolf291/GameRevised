package nl.ShadeBlackwolf;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import nl.ShadeBlackwolf.persistance.Savable;

@Component
public class WorldTracker implements Savable{
	private String globalsPrefix = "G_";

	private Map<String, String> GLOBALS = new HashMap<>();
	
	public void addGLOBAL(String key, Object value){
		if(GLOBALS.containsKey(globalsPrefix+key)){
			throw new DuplicateGlobal();
		}
		GLOBALS.put(globalsPrefix+key, value.toString());
	}
	
	public void updateGLOBAL(String key, Object value) {
		ensureGLOBALExists(globalsPrefix+key);
		GLOBALS.put(globalsPrefix+key, value.toString());
	}

	public String getGLOBAL(String key) {
		ensureGLOBALExists(globalsPrefix+key);
		return GLOBALS.get(globalsPrefix+key);
	}

	private void ensureGLOBALExists(String key) {
		if(!GLOBALS.containsKey(key)){
			throw new NoSuchGlobal();
		}
	}
	
	private class NoSuchGlobal extends RuntimeException {}

	private class DuplicateGlobal extends RuntimeException {}

	@Override
	public Map<String, String> getPersistMap() {
		return GLOBALS;
	}

	@Override
	public void restoreFields(Map<String, String> storedData) {
		for(String key: storedData.keySet()){
			if (key.startsWith(globalsPrefix)){
				GLOBALS.put(key, storedData.get(key));
			}
		}
	}

}
