package nl.ShadeBlackwolf.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.ShadeBlackwolf.player.Player;

@Component
public class PersistenceUserRequestHandler {

	@Autowired
	private GameScreen ui;
	
	@Autowired
	private InputParser inputParser;
	
	@Autowired
	private Player player;
	
	private File[] saves = new File[0];
	
	public File askWhichLoad(File[] saves) {
		storeSaves(saves);
		ui.println("Which file would you like to load?");
		displaySaves();
		return inputParser.getFileFromSaves(saves);
	}

	private void storeSaves(File[] saves) {
		this.saves = saves == null?new File[0]:saves;
	}

	public String askSaveName(File[] files) {
		saves = files;
		ui.println("What would you like to name your savegame?");
		ui.println("Current save files: (can be overwritten)");
		displaySaves();
		ui.setInput(player.getName());
		return inputParser.getCancellableCapSensitiveString();
	}

	public void displaySaves() {
		for (File f : saves){
			try {
				ui.println(f.getName() + ", saved:" + Files.getLastModifiedTime(f.toPath()));
			} catch (Exception e) {
				//ignore and proceed
			}
		}
	}

	public File reaskLoad() {
		ui.println("That is not a file");
		displaySaves();
		return inputParser.getFileFromSaves(saves);
	}

	public boolean askConfirmOverwrite() {
		ui.println("are you sure you wish to overwrite this file?");
		return inputParser.getBoolean();
	}

}
