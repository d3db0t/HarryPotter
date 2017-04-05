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

}
