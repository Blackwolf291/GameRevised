package nl.ShadeBlackwolf.player.species;

import nl.ShadeBlackwolf.player.Body;
import nl.ShadeBlackwolf.player.bodyparts.*;
import nl.ShadeBlackwolf.player.species.fox.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Fox extends Body{

	private final String species = "fox";

	@Autowired
	private FoxHead head;

	@Autowired
	private FoxCock cock;

	@Autowired
	private FoxTorso torso;

	@Autowired
	private FoxTail tail;

	@Autowired
	private FoxLegs legs;

	@Autowired
	private FoxArms arms;
	
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
