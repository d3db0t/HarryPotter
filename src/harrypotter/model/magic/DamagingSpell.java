package harrypotter.model.magic;

public class DamagingSpell extends Spell{
	private int damageAmount;
	
	public DamagingSpell(String name, int cost, int defaultCoolDown, int damageAmount){
		super(name, cost, defaultCoolDown);
		this.damageAmount = damageAmount;
	}

	public int getDamageAmount() {
		return damageAmount;
	}
	
	@Override
	public String toString()
	{
		return "Name :" + getName() + "\n" + "Type :DamagingSpell" + "\n" + "Cost :" + getCost() +
				"\n" + "DamageAmount :" + this.damageAmount;
	}

}
