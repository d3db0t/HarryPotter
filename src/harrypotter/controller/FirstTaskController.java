package harrypotter.controller;

import java.io.IOException;
import java.util.ArrayList;

import harrypotter.exceptions.OutOfBordersException;
import harrypotter.model.character.Champion;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.Tournament;

public class FirstTaskController extends TaskController{

	private FirstTask firstTask;
	
	public FirstTaskController(ArrayList <Champion> champs , Tournament tournament)
	{
		super(champs , tournament);
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
	}
}
