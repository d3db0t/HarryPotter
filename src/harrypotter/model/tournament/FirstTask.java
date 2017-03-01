package harrypotter.model.tournament;

import java.io.IOException;
import java.util.ArrayList;
import harrypotter.model.character.Champion;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.PhysicalObstacle;

public class FirstTask extends Task{
	
	public FirstTask(ArrayList<Champion> champions)throws IOException
	{
		super(champions);
		super.shuffleChampions();
		generateMap();
	}
	
	@Override
	public void generateMap() throws IOException
	{
		super.generateMap();
		addRandomPotions();
		addObstacleCells();
	}
	@Override
	public void addRandomPotions()
	{
		for(int i = 0 ;i < 10;i++)
    	{
    		int a = (int) Math.random()*6;
    		addPotionsToMap(a);
    	}
	}
	private void addPotionsToMap(int a)
	{
		int x = (int) Math.random()*10;
    	int y = (int) Math.random()*10;
    	
    	if(x == 4 && y == 4)
    		addPotionsToMap(a);
    	else if(!super.isEmptyCell(super.getMap()[x][y]))
    		addPotionsToMap(a);
    	else
    		super.getMap()[x][y] = new CollectibleCell(super.getPotions().get(a));
	}
	public void addObstacleCells()
	{
	   for(int i = 0; i < 40 ;i++)
	   {
		   int x = (int)Math.random()*10;
		   int y = (int)Math.random()*10;
		   if(x == 4 && y == 4)
			   i--;
		   else if(!super.isEmptyCell(super.getMap()[x][y]))
			   i--;
		   else
 		   {   
			   int range =200 +(int) (Math.random()*300);
			   super.getMap()[x][y] = new ObstacleCell(new PhysicalObstacle(range));
		   }
	   }
	}

}
