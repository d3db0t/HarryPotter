package harrypotter.model.character;

import harrypotter.exceptions.InCooldownException;

public class GryffindorWizard extends Wizard implements Champion{
	
	public GryffindorWizard(String name){
		super(name,900,500);
		super.setDefaultHp(900);
		super.setDefaultIp(500);
	}
	
	public void useTrait() throws InCooldownException
	{
	  if(super.getListener() != null)
		  super.getListener().onGryffindorTrait();	
	}

	public String getTraitInfo(String task)
	{
		return "TraitInfo : This trait allows \n the wizard to make two \n moves instead of one \n Cooldown : " + this.getTraitCooldown();
	}
}
