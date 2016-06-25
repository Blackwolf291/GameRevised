package nl.ShadeBlackwolf.player.species;

import nl.ShadeBlackwolf.player.Body;
import nl.ShadeBlackwolf.player.bodyparts.*;
import nl.ShadeBlackwolf.player.species.dragon.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Dragon extends Body{
	
	@Autowired
	private DragonHead head;

	@Autowired
	private DragonCock cock;

	@Autowired
	private DragonTorso torso;

	@Autowired
	private DragonTail tail;

	@Autowired
	private DragonLegs legs;

	@Autowired
	private DragonArms arms;
	
	@Override
	public String getRace() {
		return "dragon";
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
