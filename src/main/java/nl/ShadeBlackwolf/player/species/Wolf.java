package nl.ShadeBlackwolf.player.species;

import nl.ShadeBlackwolf.player.Body;
import nl.ShadeBlackwolf.player.bodyparts.*;
import nl.ShadeBlackwolf.player.species.wolf.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Wolf extends Body{

	private static final String species = "wolf";

	@Autowired
	private WolfHead head;

	@Autowired
	private WolfCock cock;

	@Autowired
	private WolfTorso torso;

	@Autowired
	private WolfTail tail;

	@Autowired
	private WolfLegs legs;

	@Autowired
	private WolfArms arms;
	
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
