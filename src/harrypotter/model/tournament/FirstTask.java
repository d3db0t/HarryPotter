package harrypotter.model.tournament;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import harrypotter.model.character.Champion;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.PhysicalObstacle;

public class FirstTask extends Task{
	private ArrayList<Point> markedCells;
	private ArrayList<Champion> winners;
	
	public FirstTask(ArrayList<Champion> champions)throws IOException
	{
		super(champions);
		super.shuffleChampions();
		generateMap();
		ArrayList<Point> markedCells = new ArrayList<Point>();
		markCells();
	}
	public ArrayList <Point> getMarkedCells()
	{
		return markedCells;
	}
	public void setMarkedCells(ArrayList <Point> markedCells)
	{
		this.markedCells = markedCells;
	}
	public ArrayList <Champion> getWinners()
	{
		return winners;
	}
	public void setWinners(ArrayList <Champion> winners)
	{
		this.winners = winners;
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
			Random n = new Random();
    		int a = n.nextInt(super.getPotions().size());
    		addPotionsToMap(a);
    	}
	}
	private void addPotionsToMap(int a)
	{
		Random n = new Random();
		int x = n.nextInt(10);
    	int y = n.nextInt(10);
    	
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
		   int x = (int) (Math.random()*10);
		   int y = (int) (Math.random()*10);
		   if(x == 4 && y == 4)
			   i--;
		   else if(!super.isEmptyCell(super.getMap()[x][y]))
			   i--;
		   else
 		   {   
			   int range = 200 + (int)(Math.random() * ((300 - 200) + 1));
			   super.getMap()[x][y] = new ObstacleCell(new PhysicalObstacle(range));
		   }
	   }
	}
	public void markCells()
	{
		Wizard c = (Wizard) this.getCurrentChamp();
		Point p = c.getLocation();
		ArrayList<Point> possiblePositions = getAdjacentCells(p);
		Random x = new Random();
		int a = x.nextInt(5);
		int b = x.nextInt(5);
		while(a == b || super.isNull(a , possiblePositions) || super.isNull(b, possiblePositions))
		{
		  a = x.nextInt(5);
		  b = x.nextInt(5);
		}
		this.markedCells.add(possiblePositions.get(a));
	    this.markedCells.add(possiblePositions.get(b));
	}
	
	public void fire()
	{
	  Wizard c = (Wizard) super.getCurrentChamp();
	  Point p = c.getLocation();
	  if(this.markedCells.get(0) == p || this.markedCells.get(1) == p)
		  c.setHp(c.getHp()-150);
	  if(!super.isAlive(this.getCurrentChamp()))
		  super.removeWizard(super.getCurrentChamp());
	}
	@Override
	public void finalizeAction(){
    	Wizard w = (Wizard) this.getCurrentChamp();
    	Point p  = w.getLocation();
    	int x = (int) p.getX();
    	int y = (int) p.getY();
    	if (x == 4 && y == 4){
    		this.getWinners().add(super.getCurrentChamp());
    		return;
    	}
    	if (w instanceof HufflepuffWizard){
    		if(!(this.isTraitActivated())){
    			fire();
    		}
    	}
    	else fire();
    	endTurn(); // To be implemented... ;)
    }
	
	@Override
	public void castDamagingSpell(DamagingSpell s, Direction d){
    	super.castDamagingSpell(s, d);
    	finalizeAction();
    }

}
