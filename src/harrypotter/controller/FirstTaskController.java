package harrypotter.controller;

import java.io.IOException;
import java.util.ArrayList;

import harrypotter.exceptions.OutOfBordersException;
import harrypotter.model.character.Champion;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.Tournament;
import harrypotter.view.FirstTaskView;
import harrypotter.view.Launcher;

public class FirstTaskController extends TaskController{

	private FirstTask firstTask;
	private FirstTaskView firstTaskView;
	
	public FirstTaskController(Launcher launcher, ArrayList <Champion> champs , Tournament tournament)
	{
		super(launcher, champs , tournament);
		try {
			super.getTournament().beginTournament();
			this.firstTask = super.getTournament().getFirstTask();
			
		} catch (OutOfBordersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		firstTaskView = new FirstTaskView();
		super.getLauncher().add(firstTaskView);
	}
}
