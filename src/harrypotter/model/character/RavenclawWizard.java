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

	public String getTraitInfo(String task)
	{
		switch(task)
		{
		case "First" : return "TraitInfo : This turn, the \n champion \n is shown where the dragon \n is going to attack \n"
				+ "CoolDown : " + this.getTraitCooldown()+ "\n" + "DefaultCooldown :5";
		case "Second": return "TraitInfo : This turn, the \n champion \n is given a hint on \n where the target is  hidden \n relative to the current position \n"
				+ "CoolDown : " + this.getTraitCooldown()+ "\n" + "DefaultCooldown :7";
		case "Third" : return "TraitInfo : This turn, the \n champion is given a hint \n on where the cup is hidden \n relative to the current position \n"
				+ "CoolDown : " + this.getTraitCooldown()+ "\n" + "DefaultCooldown :7";
				
		}
		return null;
	}
}
