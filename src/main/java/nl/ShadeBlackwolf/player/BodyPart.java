package nl.ShadeBlackwolf.player;

public abstract class BodyPart{
	
	private String species;
	
	public final String getRace(){return species;}
	
	public final void setRace(String string){this.species = string;}
	
	protected abstract void applyStatsModifier(Stats stats);
	
}
