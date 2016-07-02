package nl.ShadeBlackwolf.player;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import nl.ShadeBlackwolf.AppConfig;
import nl.ShadeBlackwolf.player.species.raccoon.RaccoonHead;
import nl.ShadeBlackwolf.testutils.SaveTestUtils;
import nl.ShadeBlackwolf.ui.InputParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class PlayerTest{
	
	@Autowired
	private Player player;
	
	@Autowired
	private PlayerBuilder builder;
	
	@Autowired
	private InputParser parser;
	
	@Autowired 
	private RaccoonHead raccoonHead;
	
	@Autowired
	private SaveTestUtils utils;
	
	@Test
	public void runCharacterBuilderFromClean() throws Exception{
		parser.preLoadParser(new ArrayList<String>(Arrays.asList(new String[]{"Shade", "raccoon", "strong"})));
		builder.makePlayer();
		assertEquals("Shade", player.getName());
		assertEquals(raccoonHead, utils.getPrivatePlayerField("head"));
		assertEquals(Perk.strong, player.getPerk());
	}
}