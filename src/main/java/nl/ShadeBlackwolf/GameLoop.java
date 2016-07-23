package nl.ShadeBlackwolf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.ShadeBlackwolf.ui.UserRequestHandler;

@Component
public class GameLoop {
	public boolean finished = false;
	
	private long turnCount = 0;
	
	@Autowired
	public UserRequestHandler userRequestHandler;

	public void run(){
		while (! finished ){
			userRequestHandler.askAction().performAction();
			executeAfterTurn();
		}
	}

	private void executeAfterTurn() {
		turnCount++;
	}
}
