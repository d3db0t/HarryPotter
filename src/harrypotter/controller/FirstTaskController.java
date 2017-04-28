package harrypotter.controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.CupCell;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.WallCell;
import harrypotter.view.FirstTaskView;
import harrypotter.view.Launcher;

public class FirstTaskController implements TaskActionListener , TournamentListener ,ActionListener {

	private FirstTask firstTask;
	private FirstTaskView firstTaskView;
	private Tournament tournament;
	private Launcher launcher;

	public FirstTaskController(Launcher launcher, ArrayList <Champion> champs , Tournament tournament)
	{
		this.launcher = launcher;
		this.tournament = tournament;
		this.tournament.getChampions().addAll(champs);
		firstTaskView = new FirstTaskView();
		this.tournament.setTournamentListener(this);
		this.launcher.add(firstTaskView);
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
				   //ImageIcon champgif = this.firstTaskView.getGifs().get(c);
				   //btn.setIcon(new ImageIcon(champgif.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
				   btn.setIcon(this.firstTaskView.getGifs().get(c));
				   c++; 
				}
				else if (a instanceof ObstacleCell)
				{
				   JButton btn = this.firstTaskView.getButtonsMap()[i][j];
				   ImageIcon wallimg = this.firstTaskView.getGifs().get(5);
				   btn.setIcon(new ImageIcon(wallimg.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
				}
				else if (i == 4 && j == 4)
				{
				   JButton btn = this.firstTaskView.getButtonsMap()[i][j];
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
		firstTaskView.generateSouthPanel(btns);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton btn = (JButton) e.getSource();
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
		}
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
		firstTaskView.getSpellsInfo().removeAll();
		firstTaskView.getSpellsInfo().revalidate();
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
		ArrayList <Spell> s = w.getSpells();
		JButton spell1 = new JButton(s.get(0).getName());
		spell1.setName("Spell1");
		spell1.setBackground(Color.GREEN);
		spell1.addActionListener(this);
		JButton spell2 = new JButton(s.get(1).getName());
		spell2.setName("Spell2");
		spell2.setBackground(Color.GREEN);
		spell2.addActionListener(this);
		JButton spell3 = new JButton(s.get(2).getName());
		spell3.setName("Spell3");
		spell3.setBackground(Color.GREEN);
		spell3.addActionListener(this);
		ArrayList <JButton> bs = new ArrayList <JButton>();
		bs.add(spell1);
		bs.add(spell2);
		bs.add(spell3);
		//int wizardtraitcooldown = w.getTraitCooldown();
		firstTaskView.addChampInfo(house, name, wizardhp, wizardip);
		firstTaskView.addSpells(bs);
	}
	
	public void showFire()
	{
		Wizard w  = (Wizard) firstTask.getCurrentChamp();
		JOptionPane.showMessageDialog(launcher, "The champion hp is : " + w.getHp());
	}
	
	
	public void castHealing(){
		
		//JOptionPane.showMessageDialog(launcher, "You have been healed by " + s.getCost());
		Wizard a = (Wizard) firstTask.getCurrentChamp();
		Point p = a.getLocation();
		firstTaskView.getButtonsMap()[p.x][p.y].setBackground(Color.BLACK);
	}
	public void updatePotions()
	{
		
	}
}
