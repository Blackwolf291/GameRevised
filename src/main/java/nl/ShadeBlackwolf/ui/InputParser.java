package nl.ShadeBlackwolf.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.ShadeBlackwolf.exceptions.Cancel;
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
			return raceFinder.getRace(getString());
		} catch (RuntimeException e) {
			return user.reaskSpecies();
		}
	}
	
	public String getString() {
		waitIfInputEmpty();
		return input.remove(0).toLowerCase();
	}
	
	public File getFileFromSaves(File[] saves) {
		File f = new File(getCapSensitiveString());
		if(f.exists()){
			return f;
		}
		return user.reaskLoad();
	}

	public String getCancellableCapSensitiveString() {
		String reply = getCapSensitiveString();
		if (reply.toLowerCase().equals("cancel")){
			throw new Cancel();
		}
		return reply;
	}
	
	public void preLoadParser(List<String> input){
		this.input = input;
	}

	public Perk getPerk() {
		try{
			return Perk.valueOf(getString());
		} catch (IllegalArgumentException e){
			return user.reaskPerk();
		}
	}

	public String getCapSensitiveString() {
		waitIfInputEmpty();
		return input.remove(0);
	}
	
	public boolean getBoolean() {
		try{
			return convertToBoolean(getString());
		} catch (RuntimeException e){
			return user.reaskBoolean();
		}
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
	
	private void waitIfInputEmpty() {
		if (input.size()==0){
			waitForInput();
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
