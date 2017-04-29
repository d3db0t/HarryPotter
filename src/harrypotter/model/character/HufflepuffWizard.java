package harrypotter.model.character;

import harrypotter.exceptions.InCooldownException;

public class HufflepuffWizard extends Wizard implements Champion{
	
	public HufflepuffWizard(String name){
		super(name,1000,450);
		super.setDefaultHp(1000);
		super.setDefaultIp(450);
	}
	
	public void useTrait() throws InCooldownException
	{
	  if(super.getListener() != null)
		  super.getListener().onHufflepuffTrait();	
	}

	public String getTraitInfo(String task)
	{
		switch(task)
		{
		case "First" : return "TraitInfo : This Turn the \n dragon won't attack \n Cooldown : " + this.getTraitCooldown()
		                      + "\n" + "DefaultCooldown :3 ";
		
		case "Second": return "TraitInfo : This Turn the \n merpersons won't attack \n Cooldown : "+ this.getTraitCooldown()
							  + "\n" + "DefaultCooldown :6";
		
		case "Third" : return "TraitInfo : Attacks from \n other champions will only deal half the damage \n "
				+ "Cooldown : 0 + "+ "\n" + "DefaultCooldown : always activated";
		
		}
		return null;
	}
}
