package harrypotter.model.character;

import java.awt.Point;
import java.util.ArrayList;

import harrypotter.model.magic.Collectible;
import harrypotter.model.magic.Spell;

public abstract class Wizard {
	private String name;
	private int defaultHp;
	private int defaultIp;
	private int hp;
	private int ip;
	private ArrayList<Spell> spells;
	private ArrayList<Collectible> inventory;
	private Point location;
	private int traitCooldown;
	private WizardListener listener;
	
	public Wizard(String name)
	{
		this.name = name;
		this.hp = this.defaultHp;
		this.ip = this.defaultIp;
		this.traitCooldown = 0;
		this.spells = new ArrayList<Spell>();
		this.inventory = new ArrayList<Collectible>();
	}
	
	public Wizard(String name , int hp , int ip){
		this.name = name;
		this.hp = hp;
		this.ip = ip;
		this.traitCooldown = 0;
		this.spells = new ArrayList<Spell>();
		this.inventory = new ArrayList<Collectible>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDefaultHp() {
		return defaultHp;
	}

	public void setDefaultHp(int defaultHp) {
		this.defaultHp = defaultHp;
	}

	public int getDefaultIp() {
		return defaultIp;
	}

	public void setDefaultIp(int defaultIp) {
		this.defaultIp = defaultIp;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getIp() {
		return ip;
	}

	public void setIp(int ip) {
		this.ip = ip;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public int getTraitCooldown() {
		return traitCooldown;
	}

	public void setTraitCooldown(int traitCooldown) {
		this.traitCooldown = traitCooldown;
	}

	public ArrayList<Spell> getSpells() {
		return spells;
	}

	public ArrayList<Collectible> getInventory() {
		return inventory;
	}
	public WizardListener getListener()
	{
		return listener;
	}
	public void setListener(WizardListener listener)
	{
		this.listener = listener;
	}
	
}
