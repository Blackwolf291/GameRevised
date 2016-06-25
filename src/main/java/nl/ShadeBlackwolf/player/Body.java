package nl.ShadeBlackwolf.player;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import nl.ShadeBlackwolf.RandomValueGenerator;
import nl.ShadeBlackwolf.exceptions.WrongDiceException;
import nl.ShadeBlackwolf.player.bodyparts.Arms;
import nl.ShadeBlackwolf.player.bodyparts.Cock;
import nl.ShadeBlackwolf.player.bodyparts.Head;
import nl.ShadeBlackwolf.player.bodyparts.Legs;
import nl.ShadeBlackwolf.player.bodyparts.Tail;
import nl.ShadeBlackwolf.player.bodyparts.Torso;

public abstract class Body implements PlayerRace{
	

	@PostConstruct
	private void setAllLimbSpecies(){
		getHead().setRace(getRace());
		getCock().setRace(getRace());
		getTorso().setRace(getRace());
		getTail().setRace(getRace());
		getArms().setRace(getRace());
		getLegs().setRace(getRace());
	}
	
	
	public abstract Head getHead();
	public abstract Torso getTorso();
	public abstract Arms getArms();
	public abstract Legs getLegs();
	public abstract Tail getTail();
	public abstract Cock getCock();
	
	@Autowired
	private RandomValueGenerator random;
	
	public BodyPart getRandomBodyPart(){
		switch (random.rollDice(6)){
		case 1: return getHead();
		case 2: return getTorso();
		case 3: return getArms();
		case 4: return getLegs();
		case 5: return getTail();
		case 6: return getCock();
		} throw new WrongDiceException();
	}
}
