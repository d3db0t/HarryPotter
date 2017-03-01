package harrypotter.model.tournament;

import java.io.IOException;
import java.util.ArrayList;
import harrypotter.model.character.Champion;
import harrypotter.model.world.Merperson;
import harrypotter.model.world.ObstacleCell;


public class SecondTask extends Task {
	
	public SecondTask(ArrayList<Champion> champions)throws IOException
	{
		super(champions);
		super.shuffleChampions();
		generateMap();
	}
	@Override
	public void generateMap() throws IOException
	{
		super.generateMap();
		super.addRandomPotions();
		addMerpersons();
	}
	public void addMerpersons()
	{
		for(int i = 0; i < 40 ;i++)
		{
		   int x = (int)Math.random()*10;
		   int y = (int)Math.random()*10;
		   if(!super.isEmptyCell(super.getMap()[x][y]))
			  i--;
		   else
	 	   {   
			 int hp =200 +(int) (Math.random()*300);
			 int dmg = 100 + (int) (Math.random()*300);
			 super.getMap()[x][y] = new ObstacleCell(new Merperson(hp,dmg));
	       }
		 }
	}

}
