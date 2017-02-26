package harrypotter.model.character;

public class GryffindorWizard extends Wizard implements Champion{
	
	public GryffindorWizard(String name){
		super(name);
		super.setDefaultHp(900);
		super.setDefaultIp(500);
	}

}
