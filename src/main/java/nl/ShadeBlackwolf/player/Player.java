package nl.ShadeBlackwolf.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nl.ShadeBlackwolf.persistance.Savable;
import nl.ShadeBlackwolf.persistance.SaveContentParser;
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
	
	@Autowired
	private SaveContentParser saveParser;
	
	public void setName(String name) {
		this.name = name;
	}

	public void setBody(PlayerRace playerRace) {
		setHead(playerRace.getHead());
		setTorso(playerRace.getTorso());
		setArms(playerRace.getArms());
		setLegs(playerRace.getLegs());
		addTail(playerRace.getTail());
		setCock(playerRace.getCock());
	}

	public void setHead(Head head) {
		this.head = head;
	}

	public void setCock(Cock cock) {
		this.cock = cock;
	}

	public void setTorso(Torso torso){
		this.torso = torso;
	}
	
	public void setArms(Arms arms) {
		this.arms = arms;
	}

	public void setLegs(Legs legs) {
		this.legs = legs;
	}

	public void changeTail(Tail tail) {
		tails.remove(r.nextInt(tails.size()));
		addTail(tail);
	}

	public void addTail(Tail tail) {
		tails.add(tail);
	}
	
	public void removeTail(){
		tails.remove(tails.size());
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
		name = storedData.get("name");
		setHead(saveParser.getHead(storedData.get("head")));
		setCock(saveParser.getCock(storedData.get("cock")));
		setTorso(saveParser.getTorso(storedData.get("torso")));
		setLegs(saveParser.getLegs(storedData.get("legs")));
		setArms(saveParser.getArms(storedData.get("arms")));
		setTails(saveParser.getTails(storedData.get("tails")));
		setPerk(Perk.valueOf(storedData.get("perk")));
	}
	
	private void setTails(List<Tail> tails) {
		this.tails = tails;
	}

	private String getTails() {
		String result = "";
		for (Tail tail : tails){
			result += (tail.getRace() + " ");
		}
		return result.trim();
	}

}
