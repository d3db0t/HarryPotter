package harrypotter.exceptions;

public class OutOfRangeException extends InvalidActionException{
	private int allowedRange;

	public int getAllowedRange() {
		return allowedRange;
	}
	
	public OutOfRangeException(int allowedRange){
		super("Range out of bound\nallowedRange: " + allowedRange);
		this.allowedRange = allowedRange;
	}

}
