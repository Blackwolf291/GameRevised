package nl.ShadeBlackwolf.ui;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import nl.ShadeBlackwolf.player.Perk;
import nl.ShadeBlackwolf.player.PlayerRace;
import nl.ShadeBlackwolf.player.RaceFinder;

@Component
public class InputParser {

	@Autowired
	private UserRequestHandler user;
	
	@Autowired
	private RaceFinder raceFinder;
	
	private List<String> input = new ArrayList<>();
	public PlayerRace getRace(){
		try {
			return raceFinder.getRace(getString().toLowerCase());
		} catch (RuntimeException e) {
			return user.reaskSpecies();
		}
	}
	public String getString() {
		waitIfInputEmpty();
		return input.remove(0).toLowerCase();
	}

	private void waitForInput() {
		try {
			synchronized (this) {
				this.wait();
			}
		} catch (InterruptedException e) {
		}
	}

	void setInput(String string) {
		input.add(string);
	}
	
	public void preLoadParser(List<String> input){
		this.input = input;
	}

	public Perk getPerk() {
		waitIfInputEmpty();
		try{
			return Perk.valueOf(input.remove(0).toLowerCase());
		} catch (IllegalArgumentException e){
			return user.reaskPerk();
		}
	}
	private void waitIfInputEmpty() {
		if (input.size()==0){
			waitForInput();
		}
	}

	public String getCapSensitiveString() {
		waitIfInputEmpty();
		return input.remove(0);
	}
	public boolean getBoolean() {
		waitIfInputEmpty();
		String inputString = input.remove(0).toLowerCase();
		try{
			return convertToBoolean(inputString);
		} catch (RuntimeException e){
			return user.reaskBoolean();
		}
	}
	private boolean convertToBoolean(String inputString) {
		if (notConvertableToBoolean(inputString)){
			throw new NotABooleanException();
		}
		return isConfirm(inputString);
	}
	private boolean isConfirm(String inputString) {
		return inputString.equals("y")||inputString.equals("yes");
	}
	private boolean notConvertableToBoolean(String inputString) {
		return !(inputString.equals("y")||inputString.equals("yes")||inputString.equals("n")||inputString.equals("no"));
	}
	private class NotABooleanException extends RuntimeException{
		private static final long serialVersionUID = 1L;}
}
