package nl.ShadeBlackwolf.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import nl.ShadeBlackwolf.player.bodyparts.*;
import nl.ShadeBlackwolf.ui.UserRequestHandler;

@Component
public class PlayerBuilder {

	@Autowired
	private Player player;
	
	@Autowired
	private UserRequestHandler user;
	
	@Autowired
	private Head[] heads;

	@Autowired
	private Arms[] arms;

	@Autowired
	private Torso[] torsos;

	@Autowired
	private Legs[] legs;

	@Autowired
	private Cock[] cocks;

	@Autowired
	private Tail[] tails;
	
	public void makePlayer() {
		player.setName(user.askName());
		player.setBody(user.askSpecies());
		player.setPerk(user.askPerk());
	}
	
	public Head getHead(PlayerRace species){
		return getBySpecies(species, heads);
	}

	public Cock getCock(PlayerRace species){
		return getBySpecies(species, cocks);
	}

	public Tail getTail(PlayerRace species){
		return getBySpecies(species, tails);
	}
	
	public Torso getTorso(PlayerRace species){
		return getBySpecies(species, torsos);
	}

	public Arms getArms(PlayerRace species){
		return getBySpecies(species, arms);
	}

	public Legs getLegs(PlayerRace species){
		return getBySpecies(species, legs);
	}

	private <T extends BodyPart> T getBySpecies(PlayerRace playerRace, T[] array) {
		for (T value : array){
			if(value.getRace().equals(playerRace.getRace())){
				return value;
			}
		}
		throw new SpeciesNotFound();
	}
	private class SpeciesNotFound extends RuntimeException{

		private static final long serialVersionUID = 1L;}
}
