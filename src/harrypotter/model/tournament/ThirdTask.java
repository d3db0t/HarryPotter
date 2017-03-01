//reconstructing
package harrypotter.model.tournament;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import harrypotter.model.character.Champion;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CupCell;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.PhysicalObstacle;
import harrypotter.model.world.WallCell;

public class ThirdTask extends Task {
	
	public ThirdTask(ArrayList<Champion> champions)throws IOException
	{
		super(champions);
		generateMap();
	}
	@Override
	public void generateMap() throws IOException
	{
		readMap("task3map.csv");
		super.addRandomPotions();
	}
	private void readMap(String filePath)throws IOException
	{
		String currentLine = "";
    	FileReader fileReader= new FileReader(filePath);
    	BufferedReader br = new BufferedReader(fileReader);
    	int c = 0;
    	while ((currentLine = br.readLine()) != null)
    	{
    	   String [] result= currentLine.split(",");
    	   for (int i = 0;i < result.length;i++)
    	   {
    		   switch(Tournament.parseInt(result[i])){
    		   case 0:
    			   super.getMap()[c][i] = new EmptyCell();
    			   break;
    		   case 1:
    		   case 2:
    		   case 3:
    		   case 4:
    			   super.getMap()[c][i] = new ChampionCell(
    					   super.getChampions().get(
    							   Tournament.parseInt(result[i])));
    			   break;
    		   case 5:
    			   super.getMap()[c][i] = new WallCell();
    			   break;
    		   case 6:
    			   int hp =200 +(int) (Math.random()*300);
    			   super.getMap()[c][i] = new ObstacleCell(new PhysicalObstacle(hp));
    			   break;
    		   case 7:
    			   super.getMap()[c][i] = new CupCell();
    			   break;
    		   default:
    			   break;
    		   }
    		   
    	   }
    	   c++;
    	}
	}
	
	

}
