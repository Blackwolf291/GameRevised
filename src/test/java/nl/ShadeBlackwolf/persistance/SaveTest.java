package nl.ShadeBlackwolf.persistance;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import nl.ShadeBlackwolf.AppConfig;
import nl.ShadeBlackwolf.UI;
import nl.ShadeBlackwolf.player.Player;
import nl.ShadeBlackwolf.player.PlayerBuilder;
import nl.ShadeBlackwolf.player.RaceFinder;
import nl.ShadeBlackwolf.testutils.SaveTestUtils;
import nl.ShadeBlackwolf.ui.InputParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class SaveTest {

	private static final String perk = "smart";

	private static final String species = "wolf";

	private static final String name = "Shade";
	
	private final String fileName = name + ".save";

	@Autowired
	private Saver saver;
	
	@Autowired
	private PlayerBuilder builder;
	
	@Autowired
	private InputParser parser;
	
	@Autowired
	private UI ui;
	
	@Autowired
	private Player player;
	
	@Autowired
	private RaceFinder raceFinder;
	
	@Autowired
	private SaveTestUtils utils;
	
	@Before
	public void setup(){
		parser.preLoadParser(new ArrayList<String>(Arrays.asList(name, species, perk)));
		builder.makePlayer();	
	}
	
	@Test
	public void fileIsCreated(){
		parser.preLoadParser(new ArrayList<String>(Arrays.asList(fileName,"y")));
		File file = new File("saves", fileName);
		saver.manualSave();
		assertTrue(file.exists());
		file.delete();
	}
	
	@Test
	public void playerReturnsMapOfWritableVersions(){
		Map<String, String> playerMap = player.getPersistMap();
		assertEquals(name, playerMap.get("name"));
		assertEquals(species, playerMap.get("head"));
		assertEquals(species, playerMap.get("cock"));
		assertEquals(species, playerMap.get("torso"));
		assertEquals(species, playerMap.get("arms"));
		assertEquals(species, playerMap.get("legs"));
		assertEquals(species, playerMap.get("tails"));
		assertEquals(perk, playerMap.get("perk"));
	}
	
	@Test
	public void createdFileContainsPlayerData() throws IOException{
		parser.preLoadParser(new ArrayList<String>(Arrays.asList(fileName,"yes")));
		File file = new File("saves", fileName);
		saver.manualSave();
		Map<String, String> fieldsInSaveFile = utils.parseFile(file);
		assertTrue(fieldsInSaveFile.containsKey("name"));
	}
	
	@Test
	public void AutosaveGeneratesAutosaveFiles() throws IOException{
		File file = new File("saves", "AutoSave_Shade.save");
		saver.autoSave();
		Map<String, String> fieldsInSaveFile = utils.parseFile(file);
		assertTrue(fieldsInSaveFile.containsKey("name"));
		assertEquals("Shade", fieldsInSaveFile.get("name"));
	}
	
	@Test
	public void AutosaveOverwritesPastAutosaves() throws IOException{
		File file = new File("saves", "AutoSave_Shade.save");
		saver.autoSave();
		player.setCock(builder.getCock(raceFinder.getRace("raccoon")));
		saver.autoSave();
		Map<String, String> fieldsInSaveFile = utils.parseFile(file);
		assertTrue(fieldsInSaveFile.containsKey("cock"));
		assertEquals("raccoon", fieldsInSaveFile.get("cock"));
	}

	@After
	public void cleanup(){
		ui.clearText();
		utils.destroyAllSaves();
	}
}
