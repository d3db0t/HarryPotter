package harrypotter.exceptions;

public abstract class InvalidActionException extends Exception{
	
	public InvalidActionException(){
		super();
	}
	
	public InvalidActionException(String message){
		super(message);
	}

}
