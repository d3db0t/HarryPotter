package harrypotter.model.tournament;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import harrypotter.controller.TaskActionListener;
import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.NotEnoughIPException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.exceptions.OutOfRangeException;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
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
	private TaskActionListener taskActionListener;
	
	public FirstTask(ArrayList<Champion> champions)throws IOException, OutOfBordersException
	{
		super(champions);
		super.shuffleChampions();
		generateMap();
		this.markedCells = new ArrayList<Point>();
		this.winners = new ArrayList<Champion>();
	    super.setCurrentChamp(super.getChampions().get(0));
		markCells();
	}
	
	public void setTaskActionListener(TaskActionListener taskActionListener)
	{
		this.taskActionListener = taskActionListener;
	}
	public TaskActionListener getTaskActionListener()
	{
		return this.taskActionListener;
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
	public void markCells() throws OutOfBordersException
	{
		this.markedCells.clear();
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
	  Point one = this.markedCells.get(0);  
	  Point two = this.markedCells.get(1);
	  if(this.getMap()[one.x][one.y] instanceof ChampionCell)  
	  {  
		 ChampionCell a = (ChampionCell) super.getMap()[one.x][one.y];
		 if(checkBeforeFire())
		 {
            ((Wizard)a.getChamp()).setHp(((Wizard) a.getChamp()).getHp()-150);
            this.taskActionListener.showFire();
		    if(!super.isAlive(a.getChamp()))
		    {
			   super.removeWizard(a.getChamp());
			   super.getMap()[one.x][one.y]= new EmptyCell();
			}
		 }
	 }
	 if(this.getMap()[two.x][two.y] instanceof ChampionCell)  
	 {  
	    ChampionCell a = (ChampionCell) super.getMap()[two.x][two.y];
		if(checkBeforeFire())
		{
			((Wizard)a.getChamp()).setHp(((Wizard) a.getChamp()).getHp()-150);    
			if(!super.isAlive(a.getChamp())) 
			{
			   super.removeWizard(a.getChamp());
			   super.getMap()[two.x][two.y] = new EmptyCell();
		    }
		}
	}
  }
	@Override
	public void finalizeAction() throws IOException, OutOfBordersException
	{
    	Wizard w = (Wizard) this.getCurrentChamp();
    	Point p  = w.getLocation();
    	int x = (int) p.getX();
    	int y = (int) p.getY();
    	if (x == 4 && y == 4)
    	{  
    	    this.winners.add(super.getCurrentChamp());
    	    super.removeWizard(super.getCurrentChamp());
    	    this.getMap()[x][y] = new EmptyCell();
    	    fire();
	    	endTurn();
            return;
    	}
	    this.getMap()[x][y] = new ChampionCell(super.getCurrentChamp());
	    fire();
	    if(super.getAllowedMoves() == 0 )
	    	endTurn();
    }
	private boolean checkBeforeFire()
	{
		if (super.getCurrentChamp() instanceof HufflepuffWizard)
		{
			if(this.isTraitActivated())
			{
				return false;
			}
			else return true;
		}
		if(super.getCurrentChamp() instanceof GryffindorWizard && super.isTraitActivated())
		{
			if(super.getAllowedMoves() == 0)
				return true;
			else 
			{
				this.taskActionListener.moveGryffindorOnTrait();
				return false;
			}
		}
		return true;
		
	}
	@Override
	public void moveForward() throws IOException, OutOfBordersException, InvalidTargetCellException
	{
		super.moveForward();
		this.taskActionListener.moveUp();
		finalizeAction();
	}
	@Override
	public void moveBackward() throws IOException, OutOfBordersException, InvalidTargetCellException
	{
		super.moveBackward();
		this.taskActionListener.moveDown();
		finalizeAction();
	}
	@Override
	public void moveRight() throws IOException, OutOfBordersException, InvalidTargetCellException
	{
		super.moveRight();
		this.taskActionListener.moveRight();
		finalizeAction();
	}
	@Override
	public void moveLeft() throws IOException, OutOfBordersException, InvalidTargetCellException
	{
		super.moveLeft();
		this.taskActionListener.moveLeft();
		finalizeAction();
	}
	@Override
	public void castDamagingSpell(DamagingSpell s, Direction d) throws IOException, NotEnoughIPException, InCooldownException, OutOfBordersException, InvalidTargetCellException
	{
    	super.castDamagingSpell(s, d);
    	finalizeAction();
    }
	@Override
	public void castHealingSpell(HealingSpell s) throws IOException, NotEnoughIPException, InCooldownException, OutOfBordersException{
		super.castHealingSpell(s);
		taskActionListener.castHealing();
		finalizeAction();
	}
    @Override
    public void castRelocatingSpell(RelocatingSpell s,Direction d,Direction t,int r) throws IOException, NotEnoughIPException, InCooldownException, OutOfRangeException, OutOfBordersException, InvalidTargetCellException
    {
    	super.castRelocatingSpell(s, d, t, r);
    	finalizeAction();
    }
    @Override
    public void endTurn() throws IOException, OutOfBordersException
    {
    	if(super.getChampions().size() != 0)
    	{
    		super.endTurn();
    		this.taskActionListener.updateNEWPanels();
    		markCells();
    	}
    	else
    	{
    		if(super.getListener() != null)
    			super.getListener().onFinishingFirstTask(this.winners);
    	}
    }
    @Override
    public void onSlytherinTrait(Direction d) throws IOException, InCooldownException, OutOfBordersException, InvalidTargetCellException{
    	Wizard w = (Wizard) this.getCurrentChamp();
    	if (w.getTraitCooldown() > 0){
    		throw new InCooldownException(w.getTraitCooldown());
    	}
    	//Checks if target point inside map 
	    //Note: getExactPosition method could create point outside map and does not assign it to null
	    //not like getAdjacentCells method
    	Point champpoint  = w.getLocation();
    	Point firstpoint  = super.getExactPosition(w.getLocation(), d, 1);
    	Point secondpoint = super.getExactPosition(w.getLocation(), d, 2);
    	if (!(super.insideBoundary(firstpoint) || super.insideBoundary(secondpoint) ))
    		throw new OutOfBordersException();
    	
    	int cpx = (int) champpoint.getX();
    	int cpy = (int) champpoint.getY();
    	int fpx = (int) firstpoint.getX();
    	int fpy = (int) firstpoint.getY();
    	int spx = (int) secondpoint.getX();
    	int spy = (int) secondpoint.getY();
    	Cell cellofchamp  = this.getMap()[cpx][cpy];
    	Cell firstcell    = this.getMap()[fpx][fpy];
    	Cell secondcell   = this.getMap()[spx][spy];
    	if(!(secondcell instanceof EmptyCell || secondcell instanceof CollectibleCell))
    		throw new InvalidTargetCellException();
    	if (secondcell instanceof EmptyCell && 
    			(firstcell instanceof EmptyCell ||
    					firstcell instanceof ObstacleCell)){
    		w.setLocation(secondpoint);
    		this.getMap()[spx][spy] = cellofchamp;
    		this.getMap()[cpx][cpy] = new EmptyCell();
    		
    	}
    	super.setTraitActivated(true);
		super.setAllowedMoves(0);
		w.setTraitCooldown(6);
		this.taskActionListener.moveSlytherin(d);
		finalizeAction();
    }
    @Override
    public void onHufflepuffTrait() throws InCooldownException
    {
    	Wizard c = (Wizard) super.getCurrentChamp();
    	super.onHufflepuffTrait();
    	c.setTraitCooldown(3);
    }
    
    public Object onRavenclawTrait() throws InCooldownException{
    	Wizard w = (Wizard) super.getCurrentChamp();
    	if (w.getTraitCooldown() > 0){
    		throw new InCooldownException(w.getTraitCooldown());
    	}
    	super.setTraitActivated(true);
    	w.setTraitCooldown(5);
		this.taskActionListener.showMarkedCells(this.markedCells);
    	return this.markedCells;
    }
}
