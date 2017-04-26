package harrypotter.controller;

import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import harrypotter.exceptions.OutOfBordersException;
import harrypotter.model.character.Champion;
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

public class FirstTaskController {

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
		this.launcher.add(firstTaskView);
		try {
			this.tournament.beginTournament();
			this.firstTask = this.tournament.getFirstTask();
			generateMap();
		} catch (OutOfBordersException e) {
			// TODO Auto-generated catch block
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
				   btn.setText("Dragon");
				   btn.setForeground(Color.WHITE);
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
		}
	}
	public void moveUp()
	{
		Wizard a = (Wizard) firstTask.getCurrentChamp();
		Point p = a.getLocation();
		firstTaskView.getButtonsMap()[p.x][p.y].setIcon(firstTaskView.getButtonsMap()[p.x-1][p.y].getIcon());
	}
}
