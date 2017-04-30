package harrypotter.controller;

import harrypotter.model.character.Champion;

public interface TournamentListener {

	public void gameOver();
	public void startSecondTask();
	public void startThirdTask();
	public void showWinner(Champion c);
}
