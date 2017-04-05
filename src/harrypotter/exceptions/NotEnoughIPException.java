package harrypotter.exceptions;

public class NotEnoughIPException extends NotEnoughResourcesException{
	private int requiredIP;
	private int remainingIP;
	
	public int getRequiredIP() {
		return requiredIP;
	}
	public int getRemainingIP() {
		return remainingIP;
	}
	
	public NotEnoughIPException(int requiredIP, int remainingIP){
		super("Not enough ip\nrequiredIP: "+ requiredIP + "\nremainingIP: " + remainingIP);
		this.requiredIP  = requiredIP;
		this.remainingIP = remainingIP;
	}
	

}
