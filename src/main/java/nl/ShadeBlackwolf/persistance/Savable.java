package nl.ShadeBlackwolf.persistance;

import java.util.Map;

public interface Savable {
	public Map<String, String> getPersistMap();
	public void restoreFields(Map<String, String> storedData);
}
