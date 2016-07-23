package nl.ShadeBlackwolf.worldactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.ShadeBlackwolf.GameLoop;
import nl.ShadeBlackwolf.WorldAction;

@Component
public class GameEnder implements WorldAction{

	@Autowired 
	private GameLoop gameLoop;
	
	@Override
	public String[] getInputMatchers() {
		return new String[]{"quit", "q"};
	}

	@Override
	public String getCanonicalName() {
		return "End Game Action";
	}

	@Override
	public void performAction() {
		gameLoop.finished = true;
	}

}
