package harrypotter.model.character;

public class GryffindorWizard extends Wizard implements Champion{
	
	public GryffindorWizard(String name){
		super(name,900,500);
		super.setDefaultHp(900);
		super.setDefaultIp(500);
	}
	
	public void useTrait()
	{
	  super.getListener().onGryffindorTrait();	
	}

}
