package nl.ShadeBlackwolf.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.stereotype.Component;

import nl.ShadeBlackwolf.persistance.Savable;
import nl.ShadeBlackwolf.player.bodyparts.Arms;
import nl.ShadeBlackwolf.player.bodyparts.Cock;
import nl.ShadeBlackwolf.player.bodyparts.Head;
import nl.ShadeBlackwolf.player.bodyparts.Legs;
import nl.ShadeBlackwolf.player.bodyparts.Tail;
import nl.ShadeBlackwolf.player.bodyparts.Torso;

@Component
public class Player implements Savable{
	
	private String name;
	private Head head;
	private Torso torso;
	private Arms arms;
	private Legs legs;
	private List<Tail> tails = new ArrayList<>();
	private Cock cock;

	Random r = new Random();

	private Perk perk;
	public void setName(String name) {
		this.name = name;
	}

	public void setBody(PlayerRace playerRace) {
		head = playerRace.getHead();
		torso = playerRace.getTorso();
		arms = playerRace.getArms();
		legs = playerRace.getLegs();
		if (tails.size() == 0){
			tails.add(playerRace.getTail());
		} else {
			tails.remove(r.nextInt(tails.size()));
			tails.add(playerRace.getTail());
		}
		cock = playerRace.getCock();
	}
	
	public void setStats() {
		// TODO Auto-generated method stub
		
	}

	public void setGold() {
		// TODO Auto-generated method stub
		
	}

	public void setEquipt() {
		// TODO Auto-generated method stub
		
	}

	public void setInventory() {
		// TODO Auto-generated method stub
		
	}

	public void setCurrectLocation() {
		// TODO Auto-generated method stub
		
	}

	public void setPerk(Perk perk) {
		this.perk = perk;
	}
	
	public String getName() {
		return name;
	}

	public Head getHead() {
		return head;
	}

	public Perk getPerk() {
		return perk;
	}

	@Override
	public Map<String, String> getPersistMap() {
		Map<String, String> playerMap = new HashMap<>();
		playerMap.put("name", name);
		playerMap.put("head", head.getRace().toString());
		playerMap.put("torso", torso.getRace().toString());
		playerMap.put("arms", arms.getRace().toString());
		playerMap.put("legs", legs.getRace().toString());
		playerMap.put("cock", cock.getRace().toString());
		playerMap.put("tails", getTails().toString());
		playerMap.put("perk", perk.toString().toString());
		return playerMap;
	}

	@Override
	public void restoreFields(Map<String, String> storedData){
		name = storedData.get(name);
	}
	
	private String getTails() {
		String result = "";
		for (Tail tail : tails){
			result += (tail.getRace() + " ");
		}
		return result.trim();
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public void setCock(Cock cock) {
		this.cock = cock;
	}

}
