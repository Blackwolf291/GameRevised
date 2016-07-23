package nl.ShadeBlackwolf;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import nl.ShadeBlackwolf.locations.LocationBuilder;
import nl.ShadeBlackwolf.ui.InputParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class GameRevisedApplicationTests {
	
	@Mock
	public LocationBuilder locationBuilder;
	
	@Autowired
	public GameRevisedApplication game;
	
	@Autowired
	public UI ui;
	
	@Autowired
	public InputParser inputParser;
	
	@Test(timeout = 5000)
	public void totalStartStopTimeAcceptable(){
		GameRevisedApplication.main("Shade", "wolf", "q");
	}

	@Test
	public void unknownCommandReturnsConfused(){
		GameRevisedApplication.main("Shade", "wolf", "not a command", "q");
	}
	
	@Test
	public void gracefulShutdownThrowsNoExceptions(){
		MockitoAnnotations.initMocks(this);
		doThrow(new RuntimeException()).when(locationBuilder).build();
		ReflectionTestUtils.setField(game, "locationBuilder", locationBuilder);
		game.run();
		new File("error.log").delete();
	}
	@After
	public void teardown(){
		ui.clearText();
	}
	
}
