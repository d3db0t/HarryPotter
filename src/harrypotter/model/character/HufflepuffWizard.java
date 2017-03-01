//reconstructing
package harrypotter.model.character;

public class HufflepuffWizard extends Wizard implements Champion{
	
	public HufflepuffWizard(String name){
		super(name);
		super.setDefaultHp(1000);
		super.setDefaultIp(450);
	}
	
	public void useTrait(){};

}
