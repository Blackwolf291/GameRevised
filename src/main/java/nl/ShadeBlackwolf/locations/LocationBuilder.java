package nl.ShadeBlackwolf.locations;

import org.springframework.stereotype.Component;

@Component
public class LocationBuilder {

	public void build(){
		Direction.north.setReverse(Direction.south);
		Direction.east.setReverse(Direction.west);
		Direction.northeast.setReverse(Direction.southwest);
		Direction.northwest.setReverse(Direction.southeast);
		Direction.south.setReverse(Direction.north);
		Direction.west.setReverse(Direction.east);
		Direction.southwest.setReverse(Direction.northeast);
		Direction.southeast.setReverse(Direction.northwest);
		Direction.in.setReverse(Direction.out);
		Direction.out.setReverse(Direction.in);
		Direction.up.setReverse(Direction.down);
		Direction.down.setReverse(Direction.up);
	}
}
