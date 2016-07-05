package nl.ShadeBlackwolf.persistance;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import nl.ShadeBlackwolf.AppConfig;
import nl.ShadeBlackwolf.player.Player;
import nl.ShadeBlackwolf.player.bodyparts.Tail;
import nl.ShadeBlackwolf.player.species.dragon.DragonArms;
import nl.ShadeBlackwolf.player.species.dragon.DragonHead;
import nl.ShadeBlackwolf.player.species.dragon.DragonTail;
import nl.ShadeBlackwolf.player.species.fox.FoxLegs;
import nl.ShadeBlackwolf.player.species.fox.FoxTail;
import nl.ShadeBlackwolf.player.species.raccoon.RaccoonTorso;
import nl.ShadeBlackwolf.player.species.wolf.WolfCock;
import nl.ShadeBlackwolf.player.species.wolf.WolfTail;
import nl.ShadeBlackwolf.testutils.SaveTestUtils;
import nl.ShadeBlackwolf.ui.GameScreen;
import nl.ShadeBlackwolf.ui.InputParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class LoadTest implements ApplicationContextAware {
	
	@Autowired 
	private Loader loader;
	
	@Autowired
	private GameScreen ui;
	
	@Autowired
	private SaveTestUtils utils;

	@Autowired
	private InputParser parser;
	
	@Autowired
	private Player player;

	private final String fileName = "dragonsav";

	private ApplicationContext context; 
	
	@Before
	public void setup() throws IOException{
		ensureSaveDir();
		ensureSaveFile();
	}

	@Test
	public void playerRestoresCorrectly() throws Exception{
		parser.preLoadParser(new ArrayList<String>(Arrays.asList(fileName)));
		loader.load();
		assertEquals("Draco", player.getName());
		assertEquals(context.getBean(DragonHead.class), utils.getPrivatePlayerField("head"));
		assertEquals(context.getBean(WolfCock.class), utils.getPrivatePlayerField("cock"));
		assertEquals(context.getBean(RaccoonTorso.class), utils.getPrivatePlayerField("torso"));
		assertEquals(context.getBean(FoxLegs.class), utils.getPrivatePlayerField("legs"));
		assertEquals(context.getBean(DragonArms.class), utils.getPrivatePlayerField("arms"));
		assertEquals(getTailsList(), utils.getPrivatePlayerField("tails"));
	}

	private List<Tail> getTailsList() {
		List<Tail> tails = new ArrayList<>();
		tails.add(context.getBean(WolfTail.class));
		tails.add(context.getBean(FoxTail.class));
		tails.add(context.getBean(DragonTail.class));
		tails.add(context.getBean(WolfTail.class));
		return tails;
	}

	@After
	public void cleanup() throws Exception{
		ui.clearText();
		ui.setInput("");
		utils.destroyAllSaves();
		utils.clearTails();
	}

	private void ensureSaveFile() throws IOException, AssertionError {
		File f = new File("saves" ,fileName + ".save");
		try (PrintWriter w = new PrintWriter(f)){
			w.println("name:Draco");
			w.println("head:dragon");
			w.println("cock:wolf");
			w.println("torso:raccoon");
			w.println("legs:fox");
			w.println("arms:dragon");
			w.println("perk:hung");
			w.println("tails:wolf fox dragon wolf");
		}
		if (!(f.createNewFile()||f.exists())){
			throw new AssertionError();
		}
	}

	private void ensureSaveDir() throws AssertionError {
		File dir = new File("saves");
		if (!(dir.mkdir()||dir.exists())){
			throw new AssertionError();
		}
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}
	
}
