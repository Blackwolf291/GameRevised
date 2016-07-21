package nl.ShadeBlackwolf;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class WorldTrackerTest {
	
	WorldTracker WORLD;
	
	@Before
	public void setup(){
		WORLD = new WorldTracker();
	}

	@Test 
	public void canAddGlobal(){
		WORLD.addGLOBAL("number", 3);
	}
	
	@Test (expected = Exception.class)
	public void canNotAddSameGlobalTwice(){
		WORLD.addGLOBAL("character", 'c');
		WORLD.addGLOBAL("character", 'c');
	}
	
	@Test
	public void canUpdateGlobals(){
		WORLD.addGLOBAL("name", "Shade");
		WORLD.updateGLOBAL("name", "Chase");
	}
	
	@Test (expected = Exception.class)
	public void updateWithoutCreate_throws(){
		WORLD.updateGLOBAL("testObject", this);
	}
	
	@Test
	public void getExistingGlobal(){
		WORLD.addGLOBAL("boolean", true);
		assertEquals("true", WORLD.getGLOBAL("boolean"));
	}
	
	
	@Test (expected = Exception.class)
	public void getNonExistingGlobal_Throws(){
		WORLD.getGLOBAL("imaginary");
	}
	
	@Test
	public void worldTrackerPersists(){
		WORLD.getPersistMap();
	}
	
	@Test
	public void worldTrackerRestores(){
		Map<String, String> dataMap = new HashMap<>();
		dataMap.put("key", "value");
		dataMap.put("G_key", "G_value");
		WORLD.restoreFields(dataMap);
		assertEquals(1, WORLD.getPersistMap().size());
		assertEquals("G_value", WORLD.getGLOBAL("key"));
	}
}
