package nl.ShadeBlackwolf;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import nl.ShadeBlackwolf.player.PlayerBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class GameRevisedApplicationTests {
			
	@Mock
	public GameLoop loop;
	
	@Mock
	public PlayerBuilder playerBuilder;
	
	@Autowired
	public GameRevisedApplication game;
	
	@Autowired
	public UI ui;
	
	@Test(timeout = 5000)
	public void totalStartStopTimeAcceptable(){
		GameRevisedApplication.main("Shade");
	}
	
	@Test
	public void gracefulShutdownThrowsNoExceptions(){
		MockitoAnnotations.initMocks(this);
		doThrow(new RuntimeException()).when(loop).run();
		ReflectionTestUtils.setField(game, "gameLoop", loop);
		ReflectionTestUtils.setField(game, "playerBuilder", playerBuilder);
		game.run();
	}
	@After
	public void teardown(){
		ui.clearText();
	}
	
}
