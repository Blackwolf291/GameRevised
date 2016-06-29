package nl.ShadeBlackwolf.persistance;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import nl.ShadeBlackwolf.AppConfig;
import nl.ShadeBlackwolf.player.Player;
import nl.ShadeBlackwolf.testutils.SaveTestUtils;
import nl.ShadeBlackwolf.ui.GameScreen;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class LoadTest {
	
	@Autowired 
	private Loader loader;
	
	@Autowired
	private Player player;
	
	@Autowired
	private GameScreen ui;
	
	@Autowired
	private SaveTestUtils utils;
	
	@Test
	public void playerRestoresCorrectly(){
		loader.load();
	}
	
	@After
	public void cleanup(){
		ui.clearText();
		ui.setInput("");
		utils.destroyAllSaves();
	}
}
