package harrypotter.controller;

import java.util.ArrayList;

import harrypotter.model.character.Champion;
import harrypotter.model.tournament.Tournament;

public abstract class TaskController {

	private Tournament tournament;
	
	public TaskController(ArrayList <Champion> champs , Tournament tournament)
	{
	   this.tournament = tournament;
	   this.tournament.getChampions().addAll(champs);
	}
	
	public Tournament getTournament()
	{
		return this.tournament;
	}
}
