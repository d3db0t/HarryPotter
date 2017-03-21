package harrypotter.model.tournament;

import java.util.ArrayList;
import harrypotter.model.character.Champion;

public interface TaskListener 
{
	public void onFinishingFirstTask(ArrayList<Champion>winners);
	public void onFinishingSecondTask(ArrayList<Champion>winners);
	public void onFinishingThirdTask(Champion winner);
}
