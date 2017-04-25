package harrypotter.controller;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

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
                   btn.setIcon(this.firstTaskView.getGifs().get(4));
				}
			}
		}
	}
}
