package harrypotter.exceptions;

public class InCooldownException extends NotEnoughResourcesException{
	private int remainingTurns;
	
	public InCooldownException(int remainingTurns){
		super("Cooldown is not yet zero\nremainingTurns: " + remainingTurns);
		this.remainingTurns = remainingTurns;
	}

	public int getRemainingTurns() {
		return remainingTurns;
	}

}
