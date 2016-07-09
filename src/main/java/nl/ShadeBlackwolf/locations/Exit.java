package nl.ShadeBlackwolf.locations;

public class Exit {

	private Direction direction;
	private Location targetLocation;
	private Location originLocation;
	private boolean mutual;
	private Key lockKey = null;
	
	public Exit(Direction direction, Location targetLocation, Location originLocation) {
		this(direction, targetLocation, originLocation, false);
	}
	
	public Exit(Direction direction, Location targetLocation, Location originLocation, boolean mutual) {
		this.direction = direction;
		this.targetLocation = targetLocation;
		this.originLocation = originLocation;
		this.mutual = mutual;
	}
	
	public void isMutual() {
		mutual = true;
		targetLocation.addExit(new Exit(direction.getReverse(), originLocation, targetLocation, mutual));
	}

	public Direction getDirection() {
		return direction;
	}

	public Location getTargetLocation() {
		if (lockKeyExists()){
			return targetLocation;
		}
		return originLocation;
	}

	private boolean lockKeyExists() {
		return lockKey == null;
	}

	public void lock(Key key) {
		lockKey = key;
	}
	
	public Exit unlock(Key key){
		if (key.equals(lockKey)){
			lockKey = null;
		}
		return this;
	}

}
