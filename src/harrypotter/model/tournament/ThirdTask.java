package harrypotter.model.tournament;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import harrypotter.model.character.Champion;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CupCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.PhysicalObstacle;
import harrypotter.model.world.TreasureCell;
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
		super.generateMapWithEmptyCells();
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
    			   if(super.getChampions().size() >= 1){
    				   super.getMap()[c][i] = new ChampionCell(super.getChampions()
    						   .get(0));
    			   }
    			   break;
    		   case 2:
    			   if(super.getChampions().size() >= 2){
    				   super.getMap()[c][i] = new ChampionCell(super.getChampions()
    						   .get(1));
    			   }
    			   break;
    		   case 3:
    			   if(super.getChampions().size() >= 3){
    				   super.getMap()[c][i] = new ChampionCell(super.getChampions()
    						   .get(2));
    			   }
    			   break;
    		   case 4:
    			   if(super.getChampions().size() >= 4){
    				   super.getMap()[c][i] = new ChampionCell(super.getChampions()
    						   .get(super.getChampions().size()-1));
    			   }
    			   break;
    		   case 5:
    			   super.getMap()[c][i] = new WallCell();
    			   break;
    		   case 6:
    			   int hp = 200 + (int)(Math.random() * ((300 - 200) + 1));
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
	@Override
	public void finalizeAction()
	{
		Wizard c = (Wizard) super.getCurrentChamp();
		Point p = c.getLocation();
		int x = (int) p.getX();
    	int y = (int) p.getY();
		if(this.getMap()[x][y] instanceof CupCell)
		{
    		super.getTaskListener().onFinishingThirdTask(this.getCurrentChamp());
    		return;
		}
	    this.getMap()[x][y] = new ChampionCell(super.getCurrentChamp());
    	endTurn();
	}
	@Override
	public void moveForward()
	{
		super.moveForward();
		finalizeAction();
	}
	@Override
	public void moveBackward()
	{
		super.moveBackward();
		finalizeAction();
	}
	@Override
	public void moveRight()
	{
		super.moveRight();
		finalizeAction();
	}
	@Override
	public void moveLeft()
	{
		super.moveLeft();
		finalizeAction();
	}
	@Override
	public void castDamagingSpell(DamagingSpell s, Direction d){
    	Point p = directionToPoint(d, this.getCurrentChamp());
    	int x = (int) p.getX();
    	int y = (int) p.getY();
    	Cell cl = this.getMap()[x][y];
    	if (cl instanceof ObstacleCell){
    		ObstacleCell o = (ObstacleCell) cl;
    		o.getObstacle().setHp(o.getObstacle().getHp() - s.getDamageAmount());
    		if(o.getObstacle().getHp() <= 0)
    			this.getMap()[x][y] = new EmptyCell();
    	}
    	else if (cl instanceof ChampionCell){
    		ChampionCell c = (ChampionCell) cl;
    		Wizard w = (Wizard) c.getChamp();
    		if (w instanceof HufflepuffWizard){
    			int halfdamage = s.getDamageAmount() / 2;
    			w.setHp(w.getHp() - halfdamage);
    		}
    		else{
    			w.setHp(w.getHp() - s.getDamageAmount());
    		}
    		if(!isAlive(c.getChamp()))
    		{
    			this.getMap()[x][y] = new EmptyCell();
    			removeWizard(c.getChamp());
    		}
    	}
    	super.useSpell(s);
    	finalizeAction(); // To be implemented... ;)
    }
	@Override
	 public void castRelocatingSpell(RelocatingSpell s,Direction d,Direction t,int r)
    {
	    super.castRelocatingSpell(s, d, t, r);
	    finalizeAction();
	 }
	
	

}
