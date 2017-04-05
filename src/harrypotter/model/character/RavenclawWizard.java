package harrypotter.model.character;

import harrypotter.exceptions.InCooldownException;

public class RavenclawWizard extends Wizard implements Champion{
	
	public RavenclawWizard(String name){
		super(name,750,700);
		super.setDefaultHp(750);
		super.setDefaultIp(700);
	}
	
	public void useTrait() throws InCooldownException
	{
		if(super.getListener() != null)
			super.getListener().onRavenclawTrait();
	}

}
