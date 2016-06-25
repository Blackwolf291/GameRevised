package nl.ShadeBlackwolf.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import nl.ShadeBlackwolf.AppConfig;
import nl.ShadeBlackwolf.player.Perk;
import nl.ShadeBlackwolf.player.RaceFinder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class UserRequestHandlerTest {
	
	@Autowired
	private UserRequestHandler user;
	
	@Autowired
	private GameScreen gameScreen;
	
	@Autowired
	private RaceFinder raceFinder;
	
	@Test
	public void askNameReturnsEnteredName(){
		setScreenThreadInput("Shade");
		assertEquals("Shade", user.askName());
	}
	@Test
	public void askSpeciesReturnsEnteredSpeciesIfExits(){
		setScreenThreadInput("Wolf");
		assertEquals(raceFinder.getRace("wolf"), user.askSpecies());
	}
	
	@Test
	public void askSpeciesRepromptsEnteredSpeciesIfNotExists(){
		setScreenThreadInput("bullshit");
		setScreenThreadInput("draGON");
		assertEquals(raceFinder.getRace("dragon"), user.askSpecies());
	}
	
	@Test
	public void askPerkReturnsEnteredPerkIfExits(){
		setScreenThreadInput("strong");
		assertEquals(Perk.strong, user.askPerk());
	}
	
	@Test
	public void askPerkRepromptsEnteredPerkIfNotExists(){
		setScreenThreadInput("kink");
		setScreenThreadInput("precise");
		assertEquals(Perk.precise, user.askPerk());
	}
	public void setScreenThreadInput(String input){
		new Runnable(){

			@Override
			public void run() {
				try {
					synchronized(gameScreen) {
						gameScreen.wait(5);
					}
					gameScreen.setInput(input);
					gameScreen.new OnEnter().actionPerformed(null);
				} catch (InterruptedException e) {
				}
			}}.run();
		}
}
