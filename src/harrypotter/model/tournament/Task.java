package harrypotter.model.tournament;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import harrypotter.model.character.Champion;
import harrypotter.model.magic.Potion;
import harrypotter.model.world.Cell;

public abstract class Task implements Champion{
	private ArrayList <Champion> champions ;
	private Champion currentChamp ;
	private Cell [][] map ;
    private int allowedMoves ;
    private boolean traitActivated ;
    private ArrayList <Potion> potions ;
    
    public Task(ArrayList<Champion> champions)throws IOException
    {
      this.champions = champions ;
      map = new Cell[10][10];
      allowedMoves = 1 ;
      traitActivated = false ;
      loadPotions("Potions.csv");
    }
    private void loadPotions(String filePath)throws IOException
    {
    	String currentLine = "";
    	FileReader fileReader= new FileReader(filePath);
    	BufferedReader br = new BufferedReader(fileReader);
    	while ((currentLine = br.readLine()) != null)
    	{
    	   String [] result= currentLine.split(",");
    	   Potion p = new Potion(result[0],Tournament.parseInt(result[1]));
    	   //Insert in ArrayList
    	   potions.add(p);
    	}
    }
    public ArrayList <Champion> getChampions()
    {
    	return champions ;
    }
    public Champion getCurrentChamp()
    {
    	return currentChamp ;
    }
    public void setCurrentChamp(Champion currentChamp)
    {
    	this.currentChamp = currentChamp ;
    }
    public Cell [][] getMap()
    {
    	return map ;
    }
    public int getAllowedMoves()
    {
    	return allowedMoves ;
    }
    public void setAllowedMoves(int allowedMoves)
    {
    	this.allowedMoves = allowedMoves ;
    }
    public boolean istraitActivated()
    {
    	return traitActivated ;
    }
    public void setTraitActivated(boolean traitActivated)
    {
    	this.traitActivated = traitActivated ;
    }
    public ArrayList<Potion> getPotions()
    {
    	return potions;
    }
    public void shuffleChampions()
    {
    	Collections.shuffle(champions);
    }
    public void generateMap()
    {
    	generateMapWithEmptyCells();
    	map[0][9] = (ChampionCell) champions.get(0);
    	map[9][9] = (ChampionCell) champions.get(1);
    	map[9][0] = (ChampionCell) champions.get(2);
    	map[0][0] = (ChampionCell) champions.get(3);
    	
    }
}
