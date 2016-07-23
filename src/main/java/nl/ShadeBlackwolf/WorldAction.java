package nl.ShadeBlackwolf;

public interface WorldAction {
	String[] getInputMatchers();
	public String getCanonicalName();
	public void performAction();
	public default boolean inputMatchesAction(String input){
		for (String matcher : getInputMatchers()){
			if (input.equals(matcher)){
				return true;
			}
		} return false;
	}
}
