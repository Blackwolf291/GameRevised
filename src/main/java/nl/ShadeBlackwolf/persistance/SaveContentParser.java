package nl.ShadeBlackwolf.persistance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.ShadeBlackwolf.player.PlayerBuilder;
import nl.ShadeBlackwolf.player.RaceFinder;
import nl.ShadeBlackwolf.player.bodyparts.Arms;
import nl.ShadeBlackwolf.player.bodyparts.Cock;
import nl.ShadeBlackwolf.player.bodyparts.Head;
import nl.ShadeBlackwolf.player.bodyparts.Legs;
import nl.ShadeBlackwolf.player.bodyparts.Torso;

@Component
public class SaveContentParser {

	@Autowired
	private RaceFinder raceFinder;
	
	@Autowired
	private PlayerBuilder builder;
	
	public Head getHead(String race) {
		return builder.getHead(raceFinder.getRace(race));
	}

	public Cock getCock(String race) {
		return builder.getCock(raceFinder.getRace(race));
	}

	public Torso getTorso(String race) {
		return builder.getTorso(raceFinder.getRace(race));
	}

	public Legs getLegs(String race) {
		return builder.getLegs(raceFinder.getRace(race));
	}

	public Arms getArms(String race) {
		return builder.getArms(raceFinder.getRace(race));
	}

}
