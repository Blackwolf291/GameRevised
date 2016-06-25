package nl.ShadeBlackwolf.player;

import nl.ShadeBlackwolf.player.bodyparts.Arms;
import nl.ShadeBlackwolf.player.bodyparts.Cock;
import nl.ShadeBlackwolf.player.bodyparts.Head;
import nl.ShadeBlackwolf.player.bodyparts.Legs;
import nl.ShadeBlackwolf.player.bodyparts.Tail;
import nl.ShadeBlackwolf.player.bodyparts.Torso;

public interface PlayerRace {
	public default boolean equals(PlayerRace otherSpecies){
		return getRace().equals(otherSpecies.getRace());
	}
	
	public String getRace();

	public Head getHead();

	public Torso getTorso();

	public Arms getArms();

	public Legs getLegs();

	public Tail getTail();

	public Cock getCock();
}
