package harrypotter.model.character;

import harrypotter.model.world.Direction;

public class SlytherinWizard extends Wizard implements Champion{
	Direction traitDirection;
	
	public SlytherinWizard(String name, Direction traitDirection){
		super(name);
		super.setDefaultHp(850);
		super.setDefaultIp(550);
		this.traitDirection = traitDirection;
	}
	
	public Direction getTraitDirection() {
		return traitDirection;
	}

	public void setTraitDirection(Direction traitDirection) {
		this.traitDirection = traitDirection;
	}

	public void useTrait(){};

}
