package nl.ShadeBlackwolf.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.ShadeBlackwolf.player.Perk;
import nl.ShadeBlackwolf.player.Player;
import nl.ShadeBlackwolf.player.PlayerRace;

@Component
public class UserRequestHandler {

	@Autowired
	private GameScreen ui;
	
	@Autowired
	private InputParser inputParser;
	
	@Autowired
	private Player player;
	
	public String askName() {
		ui.println("A Bear of a sailor blocks access to the cruiseship you were lucky enough to win tickets for.");
		ui.println("He smiles a gentle smile. \"I'm sorry sir, I just need to ask you a few questions for the record.");
		ui.println("Could you please state your name?\"");
		return inputParser.getCapSensitiveString();
	}

	public PlayerRace askSpecies() {
		ui.println("\"And your species? Not that I can't tell, but the microphone on the passenger log is quite blind.\"");
		ui.println("Currently supported races are: wolf, fox, dragon, raccoon");
		return inputParser.getRace();
	}
	
	public PlayerRace reaskSpecies(){
		ui.println("Sorry, you're a what exactly? I think I might have misunderstood.");
		ui.println("Currently supported races are: wolf, fox, dragon, raccoon");
		return inputParser.getRace();
	}
	
	

	public Perk askPerk() {
		ui.println("\"And this one is out of personal interrest. What is your biggest strength?\"");
		ui.println("Are you smart, strong, tough, quick, precise, rich, hung or convincing?");
		return inputParser.getPerk();
	}

	public Perk reaskPerk() {
		ui.println("\"Interresting, I've never heard anyone say that one before.");
		ui.println("Wanna try again for something I can work with?\"");
		return inputParser.getPerk();
	}

	public boolean askConfirmOverwrite() {
		// TODO Auto-generated method stub
		return inputParser.getBoolean();
	}

	public boolean reaskBoolean() {
		// TODO Auto-generated method stub
		return inputParser.getBoolean();
	}

	public File askWhichLoad(File[] saves) {
		ui.println("Which file would you like to load?");
		displaySaves(saves);
		return inputParser.getFileFromSaves(saves);
	}

	public String askSaveName(File[] files) {
		ui.println("What would you like to name your savegame?");
		ui.println("Current save files: (can be overwritten)");
		displaySaves(files);
		ui.setInput(player.getName());
		return inputParser.getCancellableCapSensitiveString();
	}

	public void displaySaves(File[] files) {
		for (File f : files){
			try {
				ui.println(f.getName() + ", saved:" + Files.getLastModifiedTime(f.toPath()));
			} catch (IOException e) {
				//ignore and proceed
			}
		}
	}

	public File reaskLoad() {
		ui.println("That is not a file");
		return null;
	}
}
