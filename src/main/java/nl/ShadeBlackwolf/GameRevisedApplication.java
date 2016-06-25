package nl.ShadeBlackwolf;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import nl.ShadeBlackwolf.player.PlayerBuilder;
import nl.ShadeBlackwolf.ui.InputParser;

@Component
public class GameRevisedApplication {

	@Autowired
	private ExceptionLogger logger;
	
	@Autowired
	private GameLoop gameLoop;
	
	@Autowired 
	private UI ui;
	
	@Autowired
	private PlayerBuilder playerBuilder;
	
	public static void main(String... args) {
			ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
			context.getBean(InputParser.class).preLoadParser(Arrays.asList(args));
			context.getBean(GameRevisedApplication.class).run();
	}
	
	void run(){
		try{
			setupGame();
			gameLoop.run();
			tearDownGame();
		} catch (Exception e){
			logger.logException(e);
			gracefulShutdown();
		}
	}

	private void gracefulShutdown() {
		// TODO Auto-generated method stub
		
	}

	private void tearDownGame() {
		// TODO Auto-generated method stub
		
	}

	private void setupGame() {
		ui.run();
		playerBuilder.makePlayer();
	}
	
}
