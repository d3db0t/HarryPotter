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
	
	public Wizard(String name){
		this.name = name;
		this.hp = defaultHp;
		this.ip = defaultIp;
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
		this.hp = this.defaultHp;
	}

	public int getDefaultIp() {
		return defaultIp;
	}

	public void setDefaultIp(int defaultIp) {
		this.defaultIp = defaultIp;
		this.ip = this.defaultIp;
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
	
	
}
