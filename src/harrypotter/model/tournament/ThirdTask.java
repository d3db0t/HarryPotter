package harrypotter.model.tournament;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import harrypotter.model.character.Champion;

public class ThirdTask extends Task {
	
	public ThirdTask(ArrayList<Champion> champions)throws IOException
	{
		super(champions);
		generateMap();
	}
	

}
