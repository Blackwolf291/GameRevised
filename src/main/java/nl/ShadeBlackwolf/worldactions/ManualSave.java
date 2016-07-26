package nl.ShadeBlackwolf.worldactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.ShadeBlackwolf.WorldAction;
import nl.ShadeBlackwolf.persistance.Saver;

@Component
public class ManualSave implements WorldAction {
	
	@Autowired
	private Saver saver;

	@Override
	public String[] getInputMatchers() {
		return new String[]{"save", "s"};
	}

	@Override
	public String getCanonicalName() {
		return "manual save";
	}

	@Override
	public void performAction() {
		saver.manualSave();
	}

}
