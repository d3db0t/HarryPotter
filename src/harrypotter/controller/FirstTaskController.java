package harrypotter.controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.NotEnoughIPException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.exceptions.OutOfRangeException;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Collectible;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.CupCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.WallCell;
import harrypotter.view.FirstTaskView;
import harrypotter.view.Launcher;

public class FirstTaskController implements TaskActionListener , TournamentListener ,ActionListener , MouseListener
{

	private FirstTask firstTask;
	private FirstTaskView firstTaskView;
	private Tournament tournament;
	private Launcher launcher;
	//private ArrayList <Boolean> spellsActivated;
	private JButton spell1;
	private JButton spell2;
	private JButton spell3;

	public FirstTaskController(Launcher launcher, ArrayList <Champion> champs , Tournament tournament)
	{
		this.launcher = launcher;
		this.tournament = tournament;
		this.tournament.getChampions().addAll(champs);
		firstTaskView = new FirstTaskView();
		this.tournament.setTournamentListener(this);
		this.launcher.add(firstTaskView);
		//spellsActivated = new ArrayList<Boolean>();
		//spellsActivated.add(false);
		//spellsActivated.add(false);
		//spellsActivated.add(false);
		try {
			this.tournament.beginTournament();
			this.firstTask = this.tournament.getFirstTask();
			this.firstTask.setTaskActionListener(this);
			generateMap();
			this.updateNEWPanels();
		} catch (OutOfBordersException e) {
			// TODO Auto-generated catch blocktaskActionListener.updateNEWPanels();
			e.printStackTrace();
		} catch (IOException e) {
			
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void generateMap()
	{
		Cell [][] map = firstTask.getMap();
		int c = 0;
		for(int i = 0 ; i < 10 ; i++)
		{
			for(int j = 0 ; j < 10 ; j++)
			{
				Cell a = map[i][j]; 
				if(a instanceof ChampionCell)
				{
				   JButton btn = this.firstTaskView.getButtonsMap()[i][j];
				   //btn.setName(""+i+j);
				   //ImageIcon champgif = this.firstTaskView.getGifs().get(c);
				   //btn.setIcon(new ImageIcon(champgif.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH)));
				   btn.setIcon(this.firstTaskView.getGifs().get(c));
				   c++; 
				}
				else if (a instanceof ObstacleCell)
				{
				   JButton btn = this.firstTaskView.getButtonsMap()[i][j];
				   //btn.setName(""+i+j);
				   ImageIcon wallimg = this.firstTaskView.getGifs().get(5);
				   btn.setIcon(new ImageIcon(wallimg.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
				}
				else if (i == 4 && j == 4)
				{
				   JButton btn = this.firstTaskView.getButtonsMap()[i][j];
				   //btn.setName(""+i+j);
				   ImageIcon eggimg = this.firstTaskView.getGifs().get(4);
                   btn.setIcon(new ImageIcon(eggimg.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
				}
			}
		}
		// Up, Down, Right, Left Buttons in South panel
		JButton up = new JButton("Up");
		JButton down = new JButton("Down");
		JButton right = new JButton("Right");
		JButton left = new JButton("Left");
		JButton trait = new JButton("Activate Trait");
		ArrayList<JButton> btns = new ArrayList<JButton>();
		up.setName("Up");
		down.setName("Down");
		right.setName("Right");
		left.setName("Left");
		up.addActionListener(this);
		down.addActionListener(this);
		right.addActionListener(this);
		left.addActionListener(this);
		btns.add(up);
		btns.add(down);
		btns.add(right);
		btns.add(left);
		trait.setName("Trait");
		trait.addActionListener(this);
		trait.addMouseListener(this);
		firstTaskView.addTrait(trait);
		firstTaskView.generateSouthPanel(btns);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton btn = (JButton) e.getSource();
		Wizard w    = (Wizard) firstTask.getCurrentChamp();
		switch(btn.getName())
		{
		case"Up": try {
				firstTask.moveForward();
			} catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(launcher, "Invalid Move");
				e1.printStackTrace();
			}
		    break;
		case"Down": try {
			firstTask.moveBackward();
		} catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(launcher, "Invalid Move");
			e1.printStackTrace();
		}
	    break;
		case"Right": try {
			firstTask.moveRight();
		} catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(launcher, "Invalid Move");
			e1.printStackTrace();
		}
	    break;
		case"Left": try {
			firstTask.moveLeft();
		} catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(launcher, "Invalid Move");
			e1.printStackTrace();
		}
	    break;
		case "Spell1" : String type1 = spellType(w.getSpells().get(0));
						/*
						if (spellsActivated.get(1) || spellsActivated.get(2)){
							JOptionPane.showMessageDialog(launcher, "Only one spell can be activated at a time!");
							break;
						}
						*/
						if (type1 == "Healing"){
							HealingSpell s1 = (HealingSpell) w.getSpells().get(0);
							try {
								firstTask.castHealingSpell(s1);
								JOptionPane.showMessageDialog(launcher, "You have been healed by " + s1.getHealingAmount());
								return;
							} catch (NotEnoughIPException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "IP is not enough");
								e1.printStackTrace();
								return;
							} catch (InCooldownException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Can not use the spell, try again later");
								e1.printStackTrace();
								return;
							} catch (OutOfBordersException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Out of border");
								e1.printStackTrace();
								return;
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Can not read from csv");
								e1.printStackTrace();
								return;
							}
							
						}
						/*
						if (spellsActivated.get(0)){
							spellsActivated.set(0, false);
							spell1.setBackground(Color.GREEN);
							break;
						}
						*/
						else if (type1 == "Damaging"){
							DamagingSpell dmg1 = (DamagingSpell) w.getSpells().get(0);
							String direction = JOptionPane.showInputDialog(launcher, 
									"Please state your desired direction (UP ,DOWN"
									+ ", RIGHT , LEFT", null);
							switch(direction.toLowerCase())
							{
							case "up"   : try {
									firstTask.castDamagingSpell(dmg1, Direction.FORWARD);
									JOptionPane.showMessageDialog(launcher, "Damage dealt " + dmg1.getDamageAmount());
									return;
								} catch (NotEnoughIPException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "IP is not enough");
									e1.printStackTrace();
									return;
								} catch (InCooldownException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Spell is in cooldown");
									e1.printStackTrace();
									return;
								} catch (OutOfBordersException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Direction is out of borders");	
									e1.printStackTrace();
									return;
								} catch (InvalidTargetCellException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not attack this cell");
									e1.printStackTrace();
									return;
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not read from csv");
									e1.printStackTrace();
									return;
								}
								
							case "down" : try {
									firstTask.castDamagingSpell(dmg1, Direction.BACKWARD);
									JOptionPane.showMessageDialog(launcher, "Damage dealt " + dmg1.getDamageAmount());
									return;
								} catch (NotEnoughIPException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "IP is not enough");
									e1.printStackTrace();
									return;
								} catch (InCooldownException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Spell is in cooldown");
									e1.printStackTrace();
									return;
								} catch (OutOfBordersException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Direction is out of borders");	
									e1.printStackTrace();
									return;
								} catch (InvalidTargetCellException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not attack this cell");
									e1.printStackTrace();
									return;
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not read from csv");
									e1.printStackTrace();
									return;
								}
								
							case "right": try {
									firstTask.castDamagingSpell(dmg1, Direction.RIGHT);
									JOptionPane.showMessageDialog(launcher, "Damage dealt " + dmg1.getDamageAmount());
									return;
								} catch (NotEnoughIPException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "IP is not enough");
									e1.printStackTrace();
									return;
								} catch (InCooldownException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Spell is in cooldown");
									e1.printStackTrace();
									return;
								} catch (OutOfBordersException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Direction is out of borders");	
									e1.printStackTrace();
									return;
								} catch (InvalidTargetCellException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not attack this cell");
									e1.printStackTrace();
									return;
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not read from csv");
									e1.printStackTrace();
									return;
								}
								
							case "left" : try {
									firstTask.castDamagingSpell(dmg1, Direction.LEFT);
									JOptionPane.showMessageDialog(launcher, "Damage dealt " + dmg1.getDamageAmount());
									return;
								} catch (NotEnoughIPException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "IP is not enough");
									e1.printStackTrace();
									return;
								} catch (InCooldownException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Spell is in cooldown");
									e1.printStackTrace();
									return;
								} catch (OutOfBordersException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Direction is out of borders");	
									e1.printStackTrace();
									return;
								} catch (InvalidTargetCellException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not attack this cell");
									e1.printStackTrace();
									return;
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not read from csv");
									e1.printStackTrace();
									return;
								}
								
							default     : JOptionPane.showMessageDialog(launcher, "Please enter a valid position");
										  return;
							}
						}
						else if (type1 == "Relocating"){
							RelocatingSpell rel1 = (RelocatingSpell) w.getSpells().get(0);
							String objectd = JOptionPane.showInputDialog(launcher, 
									"Please state the object direction (UP ,DOWN"
									+ ", RIGHT , LEFT", null);
							String objectmove = JOptionPane.showInputDialog(launcher, 
									"Please state where you want to move the object (UP ,DOWN"
									+ ", RIGHT , LEFT", null);
							String range = JOptionPane.showInputDialog(launcher, 
									"Please state your desired range (UP ,DOWN"
									+ ", RIGHT , LEFT", null);
							if (!(Integer.parseInt(range) > 0) && 
									!(returnDirection(objectd.toLowerCase()) instanceof Direction) && 
									!(returnDirection(objectmove.toLowerCase()) instanceof Direction)){
								JOptionPane.showMessageDialog(launcher, "Please enter a valid position");
								return;
							}
							try {
								firstTask.castRelocatingSpell(rel1, returnDirection(objectd.toLowerCase()), 
										returnDirection(objectmove.toLowerCase()), Integer.parseInt(range));
								return;
							} catch (NotEnoughIPException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "IP is not enough");
								e1.printStackTrace();
								return;
							} catch (InCooldownException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Spell is in cooldown");
								e1.printStackTrace();
								return;
							} catch (NumberFormatException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Number format is incorrect");	
								e1.printStackTrace();
								return;
							} catch (OutOfRangeException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Out of range");	
								e1.printStackTrace();
								return;
							} catch (OutOfBordersException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Direction is out of borders");	
								e1.printStackTrace();
								return;
							} catch (InvalidTargetCellException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "InvalidTarget cell");
								e1.printStackTrace();
								return;
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Can not read from csv");
								e1.printStackTrace();
								return;
							}
						}
						/*
						else if (type1 == "Relocating"){
							spellsActivated.set(0, true);
							spell1.setBackground(Color.BLUE);
							break;
						}
						*/
		case "Spell2" : String type2 = spellType(w.getSpells().get(1));
						/*
					    if (spellsActivated.get(0) || spellsActivated.get(2)){
							JOptionPane.showMessageDialog(launcher, "Only one spell can be activated at a time!");
							break;
						}
						*/
						if (type2 == "Healing"){
							HealingSpell s2 = (HealingSpell) w.getSpells().get(1);
							try {
								firstTask.castHealingSpell(s2);
								JOptionPane.showMessageDialog(launcher, "You have been healed by " + s2.getHealingAmount());
								return;
							} catch (NotEnoughIPException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "IP is not enough");
								e1.printStackTrace();
								return;
							} catch (InCooldownException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Can not use the spell, try again later");
								e1.printStackTrace();
								return;
							} catch (OutOfBordersException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Out of border");
								e1.printStackTrace();
								return;
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Can not read from csv");
								e1.printStackTrace();
								return;
							}
							
						}
						/*
						if (spellsActivated.get(1)){
							spellsActivated.set(1, false);
							spell2.setBackground(Color.GREEN);
							break;
						}
						*/
						else if (type2 == "Damaging"){
							DamagingSpell dmg2 = (DamagingSpell) w.getSpells().get(1);
							String direction = JOptionPane.showInputDialog(launcher, 
									"Please state your desired direction (UP ,DOWN"
									+ ", RIGHT , LEFT", null);
							switch(direction.toLowerCase())
							{
							case "up"   : try {
									firstTask.castDamagingSpell(dmg2, Direction.FORWARD);
									JOptionPane.showMessageDialog(launcher, "Damage dealt " + dmg2.getDamageAmount());
									return;
								} catch (NotEnoughIPException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "IP is not enough");
									e1.printStackTrace();
									return;
								} catch (InCooldownException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Spell is in cooldown");
									e1.printStackTrace();
									return;
								} catch (OutOfBordersException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Direction is out of borders");	
									e1.printStackTrace();
									return;
								} catch (InvalidTargetCellException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not attack this cell");
									e1.printStackTrace();
									return;
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not read from csv");
									e1.printStackTrace();
									return;
								}
								
							case "down" : try {
									firstTask.castDamagingSpell(dmg2, Direction.BACKWARD);
									JOptionPane.showMessageDialog(launcher, "Damage dealt " + dmg2.getDamageAmount());
									return;
								} catch (NotEnoughIPException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "IP is not enough");
									e1.printStackTrace();
									return;
								} catch (InCooldownException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Spell is in cooldown");
									e1.printStackTrace();
									return;
								} catch (OutOfBordersException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Direction is out of borders");	
									e1.printStackTrace();
									return;
								} catch (InvalidTargetCellException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not attack this cell");
									e1.printStackTrace();
									return;
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not read from csv");
									e1.printStackTrace();
									return;
								}
								
							case "right": try {
									firstTask.castDamagingSpell(dmg2, Direction.RIGHT);
									JOptionPane.showMessageDialog(launcher, "Damage dealt " + dmg2.getDamageAmount());
									return;
								} catch (NotEnoughIPException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "IP is not enough");
									e1.printStackTrace();
									return;
								} catch (InCooldownException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Spell is in cooldown");
									e1.printStackTrace();
									return;
								} catch (OutOfBordersException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Direction is out of borders");	
									e1.printStackTrace();
									return;
								} catch (InvalidTargetCellException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not attack this cell");
									e1.printStackTrace();
									return;
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not read from csv");
									e1.printStackTrace();
									return;
								}
								
							case "left" : try {
									firstTask.castDamagingSpell(dmg2, Direction.LEFT);
									JOptionPane.showMessageDialog(launcher, "Damage dealt " + dmg2.getDamageAmount());
									return;
								} catch (NotEnoughIPException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "IP is not enough");
									e1.printStackTrace();
									return;
								} catch (InCooldownException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Spell is in cooldown");
									e1.printStackTrace();
									return;
								} catch (OutOfBordersException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Direction is out of borders");	
									e1.printStackTrace();
									return;
								} catch (InvalidTargetCellException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not attack this cell");
									e1.printStackTrace();
									return;
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not read from csv");
									e1.printStackTrace();
									return;
								}
								
							default     : JOptionPane.showMessageDialog(launcher, "Please enter a valid position");
										  return;
							}
						}
							else if (type2 == "Relocating"){
								RelocatingSpell rel1 = (RelocatingSpell) w.getSpells().get(1);
								String objectd = JOptionPane.showInputDialog(launcher, 
										"Please state the object direction (UP ,DOWN"
										+ ", RIGHT , LEFT", null);
								String objectmove = JOptionPane.showInputDialog(launcher, 
										"Please state where you want to move the object (UP ,DOWN"
										+ ", RIGHT , LEFT", null);
								String range = JOptionPane.showInputDialog(launcher, 
										"Please state your desired range (UP ,DOWN"
										+ ", RIGHT , LEFT", null);
								if (!(Integer.parseInt(range) > 0) && 
										!(returnDirection(objectd.toLowerCase()) instanceof Direction) && 
										!(returnDirection(objectmove.toLowerCase()) instanceof Direction)){
									JOptionPane.showMessageDialog(launcher, "Please enter a valid position");
									return;
								}
								try {
									firstTask.castRelocatingSpell(rel1, returnDirection(objectd.toLowerCase()), 
											returnDirection(objectmove.toLowerCase()), Integer.parseInt(range));
									return;
								} catch (NotEnoughIPException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "IP is not enough");
									e1.printStackTrace();
									return;
								} catch (InCooldownException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Spell is in cooldown");
									e1.printStackTrace();
									return;
								} catch (NumberFormatException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Number format is incorrect");	
									e1.printStackTrace();
									return;
								} catch (OutOfRangeException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Out of range");	
									e1.printStackTrace();
									return;
								} catch (OutOfBordersException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Direction is out of borders");	
									e1.printStackTrace();
									return;
								} catch (InvalidTargetCellException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "InvalidTarget cell");
									e1.printStackTrace();
									return;
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not read from csv");
									e1.printStackTrace();
									return;
								}
							}
						/*
						else if (type2 == "Relocating"){
							spellsActivated.set(1, true);
							spell2.setBackground(Color.BLUE);
							break;
						}
						*/
		case "Spell3" : String type3 = spellType(w.getSpells().get(2));
						/*
						if (spellsActivated.get(0) || spellsActivated.get(1)){
							JOptionPane.showMessageDialog(launcher, "Only one spell can be activated at a time!");
							break;
						}
						*/
						if (type3 == "Healing"){
							HealingSpell s3 = (HealingSpell) w.getSpells().get(2);
							try {
								firstTask.castHealingSpell(s3);
								JOptionPane.showMessageDialog(launcher, "You have been healed by " + s3.getHealingAmount());
								return;
							} catch (NotEnoughIPException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "IP is not enough");
								e1.printStackTrace();
								return;
							} catch (InCooldownException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Can not use the spell, try again later");
								e1.printStackTrace();
								return;
							} catch (OutOfBordersException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Out of border");
								e1.printStackTrace();
								return;
							} catch (IOException e1) {
									// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Can not read from csv");
								e1.printStackTrace();
								return;
							}
							
						}
						/*
						if (spellsActivated.get(2)){
							spellsActivated.set(2, false);
							spell3.setBackground(Color.GREEN);
							break;
						}
						*/
						else if (type3 == "Damaging"){
							DamagingSpell dmg3 = (DamagingSpell) w.getSpells().get(2);
							String direction = JOptionPane.showInputDialog(launcher, 
									"Please state your desired direction (UP ,DOWN"
									+ ", RIGHT , LEFT", null);
							switch(direction.toLowerCase())
							{
							case "up"   : try {
									firstTask.castDamagingSpell(dmg3, Direction.FORWARD);
									JOptionPane.showMessageDialog(launcher, "Damage dealt " + dmg3.getDamageAmount());
									return;
								} catch (NotEnoughIPException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "IP is not enough");
									e1.printStackTrace();
									return;
								} catch (InCooldownException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Spell is in cooldown");
									e1.printStackTrace();
									return;
								} catch (OutOfBordersException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Direction is out of borders");	
									e1.printStackTrace();
									return;
								} catch (InvalidTargetCellException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not attack this cell");
									e1.printStackTrace();
									return;
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not read from csv");
									e1.printStackTrace();
									return;
								}
								
							case "down" : try {
									firstTask.castDamagingSpell(dmg3, Direction.BACKWARD);
									JOptionPane.showMessageDialog(launcher, "Damage dealt " + dmg3.getDamageAmount());
									return;
								} catch (NotEnoughIPException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "IP is not enough");
									e1.printStackTrace();
									return;
								} catch (InCooldownException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Spell is in cooldown");
									e1.printStackTrace();
									return;
								} catch (OutOfBordersException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Direction is out of borders");	
									e1.printStackTrace();
									return;
								} catch (InvalidTargetCellException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not attack this cell");
									e1.printStackTrace();
									return;
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not read from csv");
									e1.printStackTrace();
									return;
								}
								
							case "right": try {
									firstTask.castDamagingSpell(dmg3, Direction.RIGHT);
									JOptionPane.showMessageDialog(launcher, "Damage dealt " + dmg3.getDamageAmount());
									return;
								} catch (NotEnoughIPException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "IP is not enough");
									e1.printStackTrace();
									return;
								} catch (InCooldownException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Spell is in cooldown");
									e1.printStackTrace();
									return;
								} catch (OutOfBordersException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Direction is out of borders");	
									e1.printStackTrace();
									return;
								} catch (InvalidTargetCellException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not attack this cell");
									e1.printStackTrace();
									return;
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not read from csv");
									e1.printStackTrace();
									return;
								}
								
							case "left" : try {
									firstTask.castDamagingSpell(dmg3, Direction.LEFT);
									JOptionPane.showMessageDialog(launcher, "Damage dealt " + dmg3.getDamageAmount());
									return;
								} catch (NotEnoughIPException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "IP is not enough");
									e1.printStackTrace();
									return;
								} catch (InCooldownException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Spell is in cooldown");
									e1.printStackTrace();
									return;
								} catch (OutOfBordersException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Direction is out of borders");	
									e1.printStackTrace();
									return;
								} catch (InvalidTargetCellException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not attack this cell");
									e1.printStackTrace();
									return;
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									JOptionPane.showMessageDialog(launcher, "Can not read from csv");
									e1.printStackTrace();
									return;
								}
								
							default     : JOptionPane.showMessageDialog(launcher, "Please enter a valid position");
										  return;
							}
						}
						else if (type3 == "Relocating"){
							RelocatingSpell rel1 = (RelocatingSpell) w.getSpells().get(2);
							String objectd = JOptionPane.showInputDialog(launcher, 
									"Please state the object direction (UP ,DOWN"
									+ ", RIGHT , LEFT", null);
							String objectmove = JOptionPane.showInputDialog(launcher, 
									"Please state where you want to move the object (UP ,DOWN"
									+ ", RIGHT , LEFT", null);
							String range = JOptionPane.showInputDialog(launcher, 
									"Please state your desired range (UP ,DOWN"
									+ ", RIGHT , LEFT", null);
							if (!(Integer.parseInt(range) > 0) && 
									!(returnDirection(objectd.toLowerCase()) instanceof Direction) && 
									!(returnDirection(objectmove.toLowerCase()) instanceof Direction)){
								JOptionPane.showMessageDialog(launcher, "Please enter a valid position");
								return;
							}
							try {
								firstTask.castRelocatingSpell(rel1, returnDirection(objectd.toLowerCase()), 
										returnDirection(objectmove.toLowerCase()), Integer.parseInt(range));
								return;
							} catch (NotEnoughIPException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "IP is not enough");
								e1.printStackTrace();
								return;
							} catch (InCooldownException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Spell is in cooldown");
								e1.printStackTrace();
								return;
							} catch (NumberFormatException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Number format is incorrect");	
								e1.printStackTrace();
								return;
							} catch (OutOfRangeException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Out of range");	
								e1.printStackTrace();
								return;
							} catch (OutOfBordersException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Direction is out of borders");	
								e1.printStackTrace();
								return;
							} catch (InvalidTargetCellException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "InvalidTarget cell");
								e1.printStackTrace();
								return;
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(launcher, "Can not read from csv");
								e1.printStackTrace();
								return;
							}
						}
						/*
						else if (type3 == "Relocating"){
							spellsActivated.set(2, true);
							spell3.setBackground(Color.BLUE);
							break;
						}
						*/
		case "Trait": if(w instanceof SlytherinWizard)
					  {
						String name = JOptionPane.showInputDialog(launcher, 
										"Please state your desired direction (UP ,DOWN"
										+ ", RIGHT , LEFT", null);
						switch(name.toLowerCase())
						{
						case "up" : ((SlytherinWizard) w).setTraitDirection(Direction.FORWARD);
							break;
						case "down": ((SlytherinWizard) w).setTraitDirection(Direction.BACKWARD);
							break;
						case "right": ((SlytherinWizard) w).setTraitDirection(Direction.RIGHT);
						    break;
						case "left": ((SlytherinWizard) w).setTraitDirection(Direction.LEFT);
						    break;
						    default : JOptionPane.showMessageDialog(launcher, "Please enter a valid position");
						              return;
						}
					  }
					 try {
						firstTask.getCurrentChamp().useTrait();
					 } catch (InCooldownException e1) {
						JOptionPane.showMessageDialog(launcher, "Trait is in cooldown");
						e1.printStackTrace();
					 } catch (OutOfBordersException e1) {
						JOptionPane.showMessageDialog(launcher, "Direction is out of borders");								
						e1.printStackTrace();
					 } catch (InvalidTargetCellException e1) {
						JOptionPane.showMessageDialog(launcher, "Target cell is not empty");								
						e1.printStackTrace();
					 } catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					 }
					 break;
		case "Potion": for(int i = 0; i <w.getInventory().size() ; i++)
		               {
			              if(w.getInventory().get(i).getName().equals(btn.getText()))
			              {
			            	  try {
								this.firstTask.usePotion((Potion)w.getInventory().get(i));
							} catch (OutOfBordersException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			              }
		               }
		             break;
		}
	}
	
	public Direction returnDirection(String s){
		Direction d = Direction.FORWARD;
		switch(s){
		case "up"    : d = Direction.FORWARD; break;
		case "down"  : d = Direction.BACKWARD; break;
		case "right" : d = Direction.RIGHT; break;
		case "left"  : d = Direction.LEFT; break;
		//default      : JOptionPane.showMessageDialog(launcher, "Please enter a valid position"); break;
		}
		return d;
	}
	public void moveUp()
	{
		Wizard a = (Wizard) firstTask.getCurrentChamp();
		Point p = a.getLocation();
		firstTaskView.getButtonsMap()[p.x][p.y].setIcon(firstTaskView.getButtonsMap()[p.x+1][p.y].getIcon());
		firstTaskView.getButtonsMap()[p.x+1][p.y].setBackground(Color.BLACK);
		firstTaskView.getButtonsMap()[p.x+1][p.y].setIcon(null);
	}
	
	public void moveDown()
	{
		Wizard a = (Wizard) firstTask.getCurrentChamp();
		Point p = a.getLocation();
		firstTaskView.getButtonsMap()[p.x][p.y].setIcon(firstTaskView.getButtonsMap()[p.x-1][p.y].getIcon());
		firstTaskView.getButtonsMap()[p.x-1][p.y].setBackground(Color.BLACK);
		firstTaskView.getButtonsMap()[p.x-1][p.y].setIcon(null);
	}
	
	public void moveRight()
	{
		Wizard a = (Wizard) firstTask.getCurrentChamp();
		Point p = a.getLocation();
		firstTaskView.getButtonsMap()[p.x][p.y].setIcon(firstTaskView.getButtonsMap()[p.x][p.y-1].getIcon());
		firstTaskView.getButtonsMap()[p.x][p.y-1].setBackground(Color.BLACK);
		firstTaskView.getButtonsMap()[p.x][p.y-1].setIcon(null);
	}
	
	public void moveLeft()
	{
		Wizard a = (Wizard) firstTask.getCurrentChamp();
		Point p = a.getLocation();
		firstTaskView.getButtonsMap()[p.x][p.y].setIcon(firstTaskView.getButtonsMap()[p.x][p.y+1].getIcon());
		firstTaskView.getButtonsMap()[p.x][p.y+1].setBackground(Color.BLACK);
		firstTaskView.getButtonsMap()[p.x][p.y+1].setIcon(null);
	}
	public void updateNEWPanels() throws OutOfBordersException, IOException{
		//firstTask.endTurn();
		//JPanel north            = firstTaskView.getNorth();
		//JPanel east             = firstTaskView.getEast();
		//JPanel west             = firstTaskView.getWest();
		firstTaskView.getPotionInfo().removeAll();
		firstTaskView.getPotionInfo().revalidate();
		firstTaskView.getSpellsInfo().removeAll();
		firstTaskView.getSpellsInfo().revalidate();
		firstTaskView.getWinners().removeAll();
		firstTaskView.getWinners().revalidate();
		for(int i = 0 ; i < 10 ; i++)
		{
			for(int j = 0; j < 10 ; j++)
				this.firstTaskView.getButtonsMap()[i][j].setBackground(Color.BLACK);
		}
		Wizard w                = (Wizard) firstTask.getCurrentChamp();
		Point p                 = w.getLocation();
		firstTaskView.getButtonsMap()[p.x][p.y].setBackground(Color.ORANGE);
		String name             = w.getName();
		int wizardhp            = w.getHp();
		int wizardip            = w.getIp();
		String house;
		if (w instanceof HufflepuffWizard){
			house = "Hufflepuff";
		}
		else if (w instanceof SlytherinWizard){
			house = "Slytherin";
		}
		else if (w instanceof GryffindorWizard){
			house = "Gryffindor";
		}
		else{
			house = "Ravenclaw";
		}
		ArrayList<Collectible> potions = w.getInventory();
		for(int i = 0 ; i < potions.size();i++)
		{
			JButton btn = new JButton(potions.get(i).getName());
			btn.setName("Potion");
			btn.addActionListener(this);
			btn.addMouseListener(this);
			this.firstTaskView.addPotions(btn);
		}
		ArrayList <Spell> s = w.getSpells();
		spell1 = new JButton(s.get(0).getName());
		spell1.setName("Spell1");
		spell1.addActionListener(this);
		spell1.addMouseListener(this);
		spell2 = new JButton(s.get(1).getName());
		spell2.setName("Spell2");
		spell2.addMouseListener(this);
		spell2.addActionListener(this);
		spell3 = new JButton(s.get(2).getName());
		spell3.setName("Spell3");
		spell3.addActionListener(this);
		spell3.addMouseListener(this);
		ArrayList <JButton> bs = new ArrayList <JButton>();
		bs.add(spell1);
		bs.add(spell2);
		bs.add(spell3);
		for (int i = 0; i < s.size();i++){
			Spell sp = s.get(i);
			if (sp.getCoolDown() == 0){
				bs.get(i).setBackground(Color.GREEN);
			}
			else{
				bs.get(i).setBackground(Color.RED);
			}
		}
		if (firstTask.getWinners().size() > 0){
			firstTaskView.addWinners(firstTask.getWinners());
		}
		//int wizardtraitcooldown = w.getTraitCooldown();
		firstTaskView.addChampInfo(house, name, wizardhp, wizardip);
		firstTaskView.addSpells(bs);
	}
	
	public void showFire()
	{
		Wizard w  = (Wizard) firstTask.getCurrentChamp();
		JOptionPane.showMessageDialog(launcher, w.getName() + "is fired by a dragon"
				+ "  your hp is now : " + w.getHp());
	}
	
	public String spellType(Spell s){
		String type;
		if (s instanceof HealingSpell){
			type = "Healing";
		}
		else if (s instanceof DamagingSpell){
			type = "Damaging";
		}
		else{
			type = "Relocating";
		}
		return type;
	}
	
	public void castHealing(){
		
		//JOptionPane.showMessageDialog(launcher, "You have been healed by " + s.getCost());
		Wizard a = (Wizard) firstTask.getCurrentChamp();
		Point p = a.getLocation();
		firstTaskView.getButtonsMap()[p.x][p.y].setBackground(Color.BLACK);
	}
	
	public void castDamaging(Point p){ // p is the point of the cell that has been hit
		// x and y
		int i = p.x;
		int j = p.y;
		firstTaskView.getButtonsMap()[p.x][p.y].setIcon(null);;		
		
	}
	
	public void castRelocating(Point pre , Point new1){
		this.firstTaskView.getButtonsMap()[new1.x][new1.y].setIcon(
				this.firstTaskView.getButtonsMap()[pre.x][pre.y].getIcon());
		this.firstTaskView.getButtonsMap()[pre.x][pre.y].setIcon(null);
	}

	public void moveSlytherin(Direction d)
	{
		Wizard a = (Wizard) firstTask.getCurrentChamp();
		Point p = a.getLocation();
		switch(d)
		{
		case FORWARD :
					  firstTaskView.getButtonsMap()[p.x][p.y].setIcon(firstTaskView.getButtonsMap()[p.x+2][p.y].getIcon());
					  firstTaskView.getButtonsMap()[p.x+2][p.y].setBackground(Color.BLACK);
					  firstTaskView.getButtonsMap()[p.x+2][p.y].setIcon(null);
					  break;
		case BACKWARD :
					  firstTaskView.getButtonsMap()[p.x][p.y].setIcon(firstTaskView.getButtonsMap()[p.x-2][p.y].getIcon());
					  firstTaskView.getButtonsMap()[p.x-2][p.y].setBackground(Color.BLACK);
					  firstTaskView.getButtonsMap()[p.x-2][p.y].setIcon(null);
					  break;
		case RIGHT    :
			          firstTaskView.getButtonsMap()[p.x][p.y].setIcon(firstTaskView.getButtonsMap()[p.x][p.y-2].getIcon());
			          firstTaskView.getButtonsMap()[p.x][p.y-2].setBackground(Color.BLACK);
			          firstTaskView.getButtonsMap()[p.x][p.y-2].setIcon(null);
			          break;
		case LEFT     :
			          firstTaskView.getButtonsMap()[p.x][p.y].setIcon(firstTaskView.getButtonsMap()[p.x][p.y+2].getIcon());
			          firstTaskView.getButtonsMap()[p.x][p.y+2].setBackground(Color.BLACK);
			          firstTaskView.getButtonsMap()[p.x][p.y+2].setIcon(null);
			          break;
		}
	}

	public void showMarkedCells(ArrayList <Point> cells , String case1)
	{
		switch(case1)
		{
		case "Trait":
			this.firstTaskView.getButtonsMap()[cells.get(0).x][cells.get(0).y].setBackground(Color.WHITE);
			this.firstTaskView.getButtonsMap()[cells.get(1).x][cells.get(1).y].setBackground(Color.WHITE);
			break;
		case "Fire":
			this.firstTaskView.getButtonsMap()[cells.get(0).x][cells.get(0).y].setBackground(Color.RED);
			this.firstTaskView.getButtonsMap()[cells.get(1).x][cells.get(1).y].setBackground(Color.RED);
			break;		
	    }
	}
	
	public void moveGryffindorOnTrait()
	{
		Wizard w = (Wizard) this.firstTask.getCurrentChamp();
		Point p = w.getLocation();
		this.firstTaskView.getButtonsMap()[p.x][p.y].setBackground(Color.ORANGE);
	}
	
	public void updateAfterPotion(int i) throws OutOfBordersException, IOException
	{
		JPanel p = this.firstTaskView.getPotionInfo();
		p.remove(p.getComponent(i));
		this.updateNEWPanels();
	}
	
	public void removeChamp(Wizard w , String case1)
	{
		switch(case1)
		{
		case "Dead":
				JOptionPane.showMessageDialog(launcher, w.getName() + " is dead");
				Point p = w.getLocation();
				ImageIcon broom = new  ImageIcon("Broom.gif");
				this.firstTaskView.getButtonsMap()[p.x][p.y].setBackground(Color.BLACK);
				this.firstTaskView.getButtonsMap()[p.x][p.y].setIcon((new ImageIcon(broom.getImage()
						.getScaledInstance(50, 50, Image.SCALE_SMOOTH))));
				break;
		case "Winner":
				JOptionPane.showMessageDialog(launcher, w.getName() + " won");
				Point p1 = w.getLocation();
				ImageIcon eggimg = new ImageIcon("egg-icon.png");
				this.firstTaskView.getButtonsMap()[p1.x][p1.y].setBackground(Color.BLACK);
				this.firstTaskView.getButtonsMap()[p1.x][p1.y].setIcon((new ImageIcon(eggimg.getImage()
						.getScaledInstance(50, 50, Image.SCALE_SMOOTH))));
				break;
		}
	}
	
	public void showCollectible()
	{
		JOptionPane.showMessageDialog(launcher, "You found a collectible");
	}
	
	public void startSecondTask(){
		firstTaskView.removeAll();
		firstTaskView.revalidate();
		new SecondTaskController(launcher, tournament);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		/*
		JButton btn    = (JButton) e.getSource();
		String btnID   = btn.getText(); // i and j as string
		// i and j as int | this represents the point of the cell (button) clicked
		int i          = Integer.parseInt("" + btnID.charAt(0));
		int j          = Integer.parseInt("" + btnID.charAt(1));
		Wizard w       = (Wizard) firstTask.getCurrentChamp();
		int spellindex = activatedSpellIndex();
		Spell s        = w.getSpells().get(spellindex);
		if (spellType(s) == "Damaging" && spellindex != 404){
			String direction = JOptionPane.showInputDialog(launcher, 
					"Please state your desired direction (UP ,DOWN"
					+ ", RIGHT , LEFT", null);
			DamagingSpell s2 = (DamagingSpell) s;
			switch(direction.toLowerCase())
			{
			case "up" : 
				break;
			case "down": 
				break;
			case "right": 
			    break;
			case "left": 
			    break;
			    default : JOptionPane.showMessageDialog(launcher, "Please enter a valid position");
			              return;
			}
			firstTask.castDamagingSpell(s, direction);
		}
		*/
	}
	/*
	public int activatedSpellIndex(){
		int i;
		if (spellsActivated.get(0)){
			i = 0;
		}
		else if (spellsActivated.get(1)){
			i = 1;
		}
		else if (spellsActivated.get(2)){
			i = 2;
		}
		else{
			i = 404; // HAHA
		}
		return i;
	}
	*/

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	   Wizard w = (Wizard) firstTask.getCurrentChamp();
	   JButton btn = (JButton) e.getSource();
	   switch(btn.getName())
	   {
	   case "Spell1": this.firstTaskView.showEast(w.getSpells().get(0).toString());
	                  break;
	   case "Spell2": this.firstTaskView.showEast(w.getSpells().get(1).toString());
	                  break;
	   case "Spell3": this.firstTaskView.showEast(w.getSpells().get(2).toString());
	                  break;
	   case "Trait" : this.firstTaskView.showEast(w.getTraitInfo("First"));
	                  break;
	   case "Potion": for(int i = 0 ; i <w.getInventory().size() ; i++)
	                  {
		                  if(w.getInventory().get(i).getName().equals(btn.getText()))
		                  {
		                	  this.firstTaskView.showEast(w.getInventory().get(i).toString());
		                	  break;
		                  }
	                  }
	                  break;
	   }
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.firstTaskView.getInfo().removeAll();
		this.firstTaskView.getInfo().revalidate();
	    this.firstTaskView.getInfo().setVisible(false);
	}

	@Override
	public void showHint(ArrayList<Direction> hint) {
		// TODO Auto-generated method stub
		
	}
	 public void showAttack()
	    {
	    	
	    }
	public void gameOver()
	{
		JOptionPane.showMessageDialog(launcher, "The game is over all champions dead");
		this.firstTaskView.removeAll();
		this.firstTaskView.revalidate();
		try {
			new LauncherController();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void startThirdTask() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showWinner(Champion c) {
		// TODO Auto-generated method stub
		
	}
}
