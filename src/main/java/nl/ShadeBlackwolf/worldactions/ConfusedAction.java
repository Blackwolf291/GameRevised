package nl.ShadeBlackwolf.worldactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.ShadeBlackwolf.UI;
import nl.ShadeBlackwolf.WorldAction;

@Component
public class ConfusedAction implements WorldAction{
	
	@Autowired
	private UI ui;

	@Override
	public String[] getInputMatchers() {
		return new String[]{"confused"};
	}

	@Override
	public String getCanonicalName() {
		return "Confused";
	}

	@Override
	public void performAction() {
		ui.println("You try tying your shoes before realising you don't have any.");
		ui.println("The sad part is that that took you a whole hour.");
	}

}
