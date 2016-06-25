package nl.ShadeBlackwolf.player;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import nl.ShadeBlackwolf.AppConfig;
import nl.ShadeBlackwolf.player.species.Dragon;
import nl.ShadeBlackwolf.player.species.Raccoon;
import nl.ShadeBlackwolf.player.species.Wolf;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class RaceFinderTest {
	
	@Autowired
	private RaceFinder raceFinder;
	
	@Autowired
	private TestSpecies testSpecies;
	
	@Autowired
	private Wolf wolf;

	@Autowired
	private Dragon dragon;

	@Autowired
	private Raccoon raccoon;
	
	@Test
	public void assertRaceFinderCanReturnRaces(){
		assertEquals(wolf, raceFinder.getRace("wolf"));
		assertEquals(dragon, raceFinder.getRace("dragon"));
		assertEquals(raccoon, raceFinder.getRace("raccoon"));
		assertEquals(testSpecies, raceFinder.getRace("test"));
	}
	
	@Test (expected = Exception.class)
	public void throwsErrorWhenSpeciesNotFound(){
		raceFinder.getRace("not a creature");
	}
}
