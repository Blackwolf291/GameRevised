package nl.ShadeBlackwolf.ui;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import nl.ShadeBlackwolf.AppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class GameScreenTest {
	
	@Autowired
	private GameScreen screen;
	private String testText = "Notice me senpai.";
	
	@Before
	public void setup(){
		screen.run();
	}
	
	@Test
	public void textFieldEchoes(){
		screen.setInput(testText);
		GameScreen.OnEnter onEnter = screen.new OnEnter();
		onEnter.actionPerformed(null);
		assertEquals("> " + testText + "\n", screen.getText());
	}
	
	@Test
	public void canRemoveAndDisplayText(){
		assertEquals("", screen.getText());
		screen.println(testText);
		assertEquals(testText + "\n", screen.getText());
		screen.clearText();
		assertEquals("", screen.getText());
	}
	
	@Test
	public void emptyInputsAreIgnored(){
		GameScreen.OnEnter onEnter = screen.new OnEnter();
		onEnter.actionPerformed(null);
		assertEquals("", screen.getText());
	}
	
	@Test
	public void inputFieldIsCleanedOnEnter(){
		screen.setInput(testText);
		GameScreen.OnEnter onEnter = screen.new OnEnter();
		onEnter.actionPerformed(null);
		assertEquals("> " +testText + "\n", screen.getText());
		screen.clearText();
		onEnter.actionPerformed(null);
		assertEquals("", screen.getText());
	}
}
