package harrypotter.controller;

import java.util.ArrayList;

import harrypotter.model.character.Champion;
import harrypotter.model.tournament.Tournament;
import harrypotter.view.Launcher;

public abstract class TaskController {

	private Tournament tournament;
	private Launcher launcher;
	
	public TaskController(Launcher launcher, ArrayList <Champion> champs , Tournament tournament)
	{
	   this.launcher = launcher;
	   this.tournament = tournament;
	   this.tournament.getChampions().addAll(champs);
	}
	
	public Tournament getTournament()
	{
		return this.tournament;
	}

	public Launcher getLauncher() {
		return launcher;
	}
	
}
