package nl.ShadeBlackwolf.locations;

public enum Direction {
	north, south, east, west, northeast, northwest, southeast, southwest, up, down, in, out;

	private Direction reverse;
	private boolean firstCall = true;
	
	public void setReverse(Direction reverse){
		if (firstCall){
			this.firstCall = false;
			this.reverse = reverse;
		}
	}
	
	public Direction getReverse() {
		return reverse;
	}

}
