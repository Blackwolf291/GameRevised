package nl.ShadeBlackwolf.exceptions;

public class NotImplementedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public NotImplementedException(){
		super();
	}
	public NotImplementedException(Exception e) {
		super(e);
	}

}
