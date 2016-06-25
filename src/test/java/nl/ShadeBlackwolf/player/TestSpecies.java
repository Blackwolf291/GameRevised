package nl.ShadeBlackwolf.player;

import org.springframework.stereotype.Component;

import nl.ShadeBlackwolf.player.bodyparts.Arms;
import nl.ShadeBlackwolf.player.bodyparts.Cock;
import nl.ShadeBlackwolf.player.bodyparts.Head;
import nl.ShadeBlackwolf.player.bodyparts.Legs;
import nl.ShadeBlackwolf.player.bodyparts.Tail;
import nl.ShadeBlackwolf.player.bodyparts.Torso;

@Component
public class TestSpecies implements PlayerRace{
	
	@Override
	public String getRace() {
		return "test";
	}

	@Override
	public Head getHead() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Torso getTorso() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Arms getArms() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Legs getLegs() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tail getTail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cock getCock() {
		// TODO Auto-generated method stub
		return null;
	}
}
