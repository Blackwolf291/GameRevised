package nl.ShadeBlackwolf.player.species;

import nl.ShadeBlackwolf.player.Body;
import nl.ShadeBlackwolf.player.bodyparts.*;
import nl.ShadeBlackwolf.player.species.raccoon.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Raccoon extends Body {

	private static final String species = "raccoon";

	@Autowired
	private RaccoonHead head;

	@Autowired
	private RaccoonCock cock;

	@Autowired
	private RaccoonTorso torso;

	@Autowired
	private RaccoonTail tail;

	@Autowired
	private RaccoonLegs legs;

	@Autowired
	private RaccoonArms arms;
	
	@Override
	public String getRace() {
		return species;
	}

	@Override
	public Head getHead() {
		return head;
	}

	@Override
	public Torso getTorso() {
		return torso;
	}

	@Override
	public Arms getArms() {
		return arms;
	}

	@Override
	public Legs getLegs() {
		return legs;
	}

	@Override
	public Tail getTail() {
		return tail;
	}

	@Override
	public Cock getCock() {
		return cock;
	}

}
