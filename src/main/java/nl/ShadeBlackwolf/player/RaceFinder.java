package nl.ShadeBlackwolf.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RaceFinder {
	@Autowired
	List<PlayerRace> races;
	
	public PlayerRace getRace(String string) {
		for (PlayerRace race : races){
			if(race.getRace().equals(string.toLowerCase())){
				return race;
			}
		}
		throw new RaceNotFound();
	}

	private class RaceNotFound extends RuntimeException {

		private static final long serialVersionUID = 1L;}

}
