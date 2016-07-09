package nl.ShadeBlackwolf.locations;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LocationChangeTest {

	private Location locationA;
	private Location locationB;
	private Key key;
	
	@Before
	public void setup(){
		new LocationBuilder().build();
		locationA = new TestLocation();
		locationB = new TestLocation();
		key = new TestKey(Direction.south);
	}
	
	@Test
	public void addingMutualLocationAllowsTwoWayTransfer() {
		locationA.addExit(Direction.north, locationB).isMutual();
		assertEquals(locationB, locationA.move(Direction.north));
		assertEquals(locationA, locationB.move(Direction.south));
	}

	@Test
	public void findDisallowedLocationKeepsYouInPlace(){
		assertEquals(locationA, locationA.move(Direction.down));
	}
	
	@Test
	public void moveWhenLockedKeepsYouInPlace(){
		locationA.addExit(Direction.south, locationB).lock(key);
		assertEquals(locationA, locationA.move(Direction.south));
	}

	@Test
	public void moveWhenUnlockedLetsYouMove(){
		locationA.addExit(Direction.south, locationB).lock(key);
		locationA.unlock(key);
		assertEquals(locationB, locationA.move(Direction.south));
	}
	
	public class TestLocation extends Location{}
	public class TestKey implements Key{
		
		private Direction direction;
		
		public TestKey(Direction direction){
			this.direction = direction;
		}
		public Direction getDirection(){
			return direction;
		}
	}
}
