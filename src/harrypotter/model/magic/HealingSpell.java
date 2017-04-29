package harrypotter.model.magic;

public class HealingSpell extends Spell{
	private int healingAmount;
	
	public HealingSpell(String name, int cost, int defaultCoolDown, int healingAmount){
		super(name, cost, defaultCoolDown);
		this.healingAmount = healingAmount;
	}

	public int getHealingAmount() {
		return healingAmount;
	}
	
	@Override
	public String toString()
	{
		return "Name :" + getName() + "\n" + "Type :HealingSpell" + "\n" + "Cost :" + getCost() +
				"\n" + "HealingAmount :" + this.healingAmount + "\n" + "Cooldown :"+ getCoolDown();
	}
	

}
