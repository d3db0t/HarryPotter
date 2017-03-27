package harrypotter.model.tournament;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import harrypotter.model.character.Champion;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
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
		this.markedCells = new ArrayList<Point>();
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
	public void finalizeAction() throws IOException
	{
    	Wizard w = (Wizard) this.getCurrentChamp();
    	Point p  = w.getLocation();
    	int x = (int) p.getX();
    	int y = (int) p.getY();
    	if (x == 4 && y == 4)
    	{
    		this.winners.add(super.getCurrentChamp());
    		super.removeWizard(super.getCurrentChamp());
    		endTurn();
    		return;
    	}
	    this.getMap()[x][y] = new ChampionCell(super.getCurrentChamp());
    	if (w instanceof HufflepuffWizard)
    	{
    		if(!(this.isTraitActivated()))
    		{
    			fire();
    		}
    		else 
    			super.setTraitActivated(false);
    	}
    	else fire();
    	if(super.getAllowedMoves() == 0)
    	   endTurn();
    		
    }
	
	@Override
	public void moveForward() throws IOException
	{
		super.moveForward();
		finalizeAction();
	}
	@Override
	public void moveBackward() throws IOException
	{
		super.moveBackward();
		finalizeAction();
	}
	@Override
	public void moveRight() throws IOException
	{
		super.moveRight();
		finalizeAction();
	}
	@Override
	public void moveLeft() throws IOException
	{
		super.moveLeft();
		finalizeAction();
	}
	@Override
	public void castDamagingSpell(DamagingSpell s, Direction d) throws IOException
	{
    	super.castDamagingSpell(s, d);
    	finalizeAction();
    }
	@Override
	public void castHealingSpell(HealingSpell s) throws IOException{
		super.castHealingSpell(s);
		finalizeAction();
	}
    @Override
    public void castRelocatingSpell(RelocatingSpell s,Direction d,Direction t,int r) throws IOException
    {
    	super.castRelocatingSpell(s, d, t, r);
    	finalizeAction();
    }
    @Override
    public void endTurn() throws IOException
    {
    	if(super.getChampions().size() != 0)
    		super.endTurn();
    	else
    		super.getListener().onFinishingFirstTask(this.winners);
    }
    @Override
    public void onSlytherinTrait(Direction d) throws IOException{
    	Wizard w = (Wizard) this.getCurrentChamp();
    	Point champpoint  = w.getLocation();
    	Point firstpoint  = super.getExactPosition(w.getLocation(), d, 1);
    	Point secondpoint = super.getExactPosition(w.getLocation(), d, 2);
    	int cpx = (int) champpoint.getX();
    	int cpy = (int) champpoint.getY();
    	int fpx = (int) firstpoint.getX();
    	int fpy = (int) firstpoint.getY();
    	int spx = (int) secondpoint.getX();
    	int spy = (int) secondpoint.getY();
    	Cell cellofchamp  = this.getMap()[cpx][cpy];
    	Cell firstcell    = this.getMap()[fpx][fpy];
    	Cell secondcell   = this.getMap()[spx][spy];
    	if (!( super.insideBoundary(firstpoint) || super.insideBoundary(secondpoint) )){
    		return;
    	}
    	if (secondcell instanceof EmptyCell && 
    			(firstcell instanceof EmptyCell ||
    					firstcell instanceof ObstacleCell)){
    		w.setLocation(secondpoint);
    		this.getMap()[spx][spy] = cellofchamp;
    		this.getMap()[cpx][cpy] = new EmptyCell();
    		super.setTraitActivated(true);
    		w.setTraitCooldown(6);
    		super.setAllowedMoves(super.getAllowedMoves() - 1);
    		finalizeAction();
    	}
    }
    @Override
    public void onHufflepuffTrait()
    {
    	Wizard c = (Wizard) super.getCurrentChamp();
    	super.onHufflepuffTrait();
    	c.setTraitCooldown(3);
    }
    
    public Object onRavenclawTrait(){
    	Wizard w = (Wizard) super.getCurrentChamp();
    	super.setTraitActivated(true);
    	w.setTraitCooldown(5);
    	return this.markedCells;
    }
}
