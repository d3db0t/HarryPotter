package harrypotter.exceptions;

public class OutOfBordersException extends InvalidActionException{
	
	public OutOfBordersException(){
		super();
	}
	
	public OutOfBordersException(String message){
		super(message);
	}

}
