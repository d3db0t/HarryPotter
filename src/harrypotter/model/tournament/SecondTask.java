package harrypotter.model.tournament;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import harrypotter.model.character.Champion;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.Merperson;
import harrypotter.model.world.Obstacle;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.TreasureCell;


public class SecondTask extends Task {
	
	ArrayList<Champion> winners;
	private ArrayList<Champion> winners;
	private ArrayList<Point> treasuresLocation;
	
	public ArrayList<Champion> getWinners() {
		return winners;
	}
	public void setWinners(ArrayList<Champion> winners) {
		this.winners = winners;
	}
	
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
		addTreasures();
	}
	private void addMerpersons()
	{
		for(int i = 0; i < 40 ;i++)
		{
		   int x = (int)(Math.random()*10);
		   int y = (int)(Math.random()*10);
		   if(!super.isEmptyCell(super.getMap()[x][y]))
			  i--;
		   else
	 	   {   
			 int hp = 200 + (int)(Math.random() * ((300 - 200) + 1));
			 int dmg = 100 + (int)(Math.random() * ((300 - 100) + 1));
			 super.getMap()[x][y] = new ObstacleCell(new Merperson(hp,dmg));
	       }
		 }
	}
	private void addTreasures()
	{
		for(int i = 0; i < super.getChampions().size() ;i++)
		{
		   int x = (int)(Math.random()*10);
		   int y = (int)(Math.random()*10);
		   if(!super.isEmptyCell(super.getMap()[x][y]))
			  i--;
		   else
	 	   {   
			 super.getMap()[x][y] = new TreasureCell(super.getChampions().get(i));
	       }
		 }
		
	}
	
	public void encounterMerPerson()
	{ //for the currentChamp
		Wizard c = (Wizard) super.getCurrentChamp();
		Point p  = c.getLocation();
		ArrayList<Point> a = Task.getAdjacentCells(p);
		for (int i = 0;i < a.size()-1;i++)
		{
			if (super.isNull(i, a)){
				continue;
			}
			int x = (int) a.get(i).getX();
			int y = (int) a.get(i).getY();
			if (this.getMap()[x][y] instanceof ObstacleCell )
			{
			  ObstacleCell m =(ObstacleCell)this.getMap()[x][y];
			  Merperson n = (Merperson)m.getObstacle();
			  c.setHp(c.getHp()-n.getDamage());
			  if(!super.isAlive(this.getCurrentChamp()))
				  super.removeWizard(super.getCurrentChamp());
			}
		}
	}
	@Override
	public void finalizeAction()
	{
		Wizard c = (Wizard) super.getCurrentChamp();
		Point p = c.getLocation();
		int x = (int) p.getX();
    	int y = (int) p.getY();
		if(this.getMap()[x][y] instanceof TreasureCell)
		{
			this.getWinners().add(super.getCurrentChamp());
			super.removeWizard(super.getCurrentChamp());
			endTurn();
    		return;
		}
	    this.getMap()[x][y] = new ChampionCell(super.getCurrentChamp());
		if (c instanceof HufflepuffWizard)
		{
    		if(!(this.isTraitActivated()))
    			encounterMerPerson();
    		else
    			super.setTraitActivated(false);
    	}
    	else encounterMerPerson();
    	if(super.getAllowedMoves() == 0)
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
    	super.castDamagingSpell(s, d);
    	finalizeAction();
    }
	@Override
	public void castHealingSpell(HealingSpell s){
		super.castHealingSpell(s);
		finalizeAction();
	}
	 
	@Override
	public void castRelocatingSpell(RelocatingSpell s,Direction d,Direction t,int r)
    {
	   super.castRelocatingSpell(s, d, t, r);
	   finalizeAction();
	}
	@Override
	public void endTurn()
	{
	   if(super.getChampions().size() != 0)
	     super.endTurn();
	   else
	     super.getTaskListener().onFinishingSecondTask(this.winners);
	}
	@Override
    public void onSlytherinTrait(Direction d){
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
    			firstcell instanceof EmptyCell){
    					
    		w.setLocation(secondpoint);
    		this.getMap()[spx][spy] = cellofchamp;
    		this.getMap()[cpx][cpy] = new EmptyCell();
    		super.setTraitActivated(true);
    		w.setTraitCooldown(5);
    		super.setAllowedMoves(super.getAllowedMoves() - 1);
    		finalizeAction();
    	}
    }
	@Override
	public void onHufflepuffTrait()
	{
	   Wizard c = (Wizard) super.getCurrentChamp();
	   super.onHufflepuffTrait();
	   c.setTraitCooldown(7);
	}

}
