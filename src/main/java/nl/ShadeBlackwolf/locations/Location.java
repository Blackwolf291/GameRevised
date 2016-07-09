package nl.ShadeBlackwolf.locations;

import java.util.EnumMap;
import java.util.Map;

public abstract class Location {
	private Map<Direction, Exit> exits = new EnumMap<>(Direction.class);
	public Exit addExit(Direction direction, Location targetLocation){
		Exit e = new Exit(direction , targetLocation, this);
		addIfNew(e);
		return e;
	}
	
	public Exit addExit(Exit e){
		addIfNew(e);
		return e;
	}
	
	private void addIfNew(Exit e) {
		if (isNew(e)){
			exits.put(e.getDirection(), e);
		}
	}

	private boolean isNew(Exit e) {
		return !exits.containsValue(e);
	}

	public Location move(Direction direction) {
		try{
			return exits.get(direction).getTargetLocation();
		} catch (NullPointerException e){
			return this;
		}
	}

	public Exit unlock(Key key) {
		return exits.get(key.getDirection()).unlock(key);
	}
}
