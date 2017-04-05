package harrypotter.exceptions;

public class InvalidTargetCellException extends InvalidActionException{
	
	public InvalidTargetCellException(){
		super();
	}
	
	public InvalidTargetCellException(String message){
		super(message);
	}

}
