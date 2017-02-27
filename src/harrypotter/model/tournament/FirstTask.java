package harrypotter.model.tournament;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import harrypotter.model.character.Champion;

public class FirstTask extends Task{
	
	public FirstTask(ArrayList<Champion> champions)throws IOException
	{
		super(champions);
		super.shuffleChampions();
		generateMap();
	}

}
