package harrypotter.model.character;

public class RavenclawWizard extends Wizard implements Champion{
	
	public RavenclawWizard(String name){
		super(name);
		super.setDefaultHp(750);
		super.setDefaultIp(700);
	}

}
