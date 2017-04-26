package harrypotter.model.tournament;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import harrypotter.controller.TaskActionListener;
import harrypotter.controller.TournamentListener;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.model.character.Champion;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;

public class Tournament implements TaskListener{
	private ArrayList<Champion> champions;
	private ArrayList<Spell> spells;
	private FirstTask firstTask;
	private SecondTask secondTask;
	private ThirdTask thirdTask;
	private TournamentListener tournamentListener;
	
	public Tournament() throws Exception{
		champions = new ArrayList<Champion>();
		spells = new ArrayList<Spell>();
		loadSpells("Spells.csv");
		
	}
	
	public static int parseInt(String str){
		return Integer.parseInt(str);
	}
	
	private void loadSpells(String filePath) throws Exception{
		String currentLine = "";
		FileReader fileReader= new FileReader(filePath);
		BufferedReader br = new BufferedReader(fileReader);
		while ((currentLine = br.readLine()) != null) {
		
			// Parsing the currentLine String
			String [] result= currentLine.split(",");
		
			switch(result[0]){
			case "DMG":
				DamagingSpell d = new DamagingSpell(result[1], parseInt(result[2]),
						parseInt(result[4]), parseInt(result[3]));
				spells.add(d);
				break;
			case "HEL":
				HealingSpell h = new HealingSpell(result[1], parseInt(result[2]),
						parseInt(result[4]), parseInt(result[3]));
				spells.add(h);
				break;
			case "REL":
				RelocatingSpell r = new RelocatingSpell(result[1], parseInt(result[2]),
						parseInt(result[4]), parseInt(result[3]));
				spells.add(r);
				break;
			default:
				break;
			
		}
		}
	}

	public void setTournamentListener(TournamentListener tournamentListener)
	{
		this.tournamentListener = tournamentListener;
	}
	
	public TournamentListener getTournamentListener()
	{
		return this.tournamentListener;
	}
	
	public ArrayList<Champion> getChampions() {
		return champions;
	}

	public ArrayList<Spell> getSpells() {
		return spells;
	}

	public FirstTask getFirstTask() {
		return firstTask;
	}

	public SecondTask getSecondTask() {
		return secondTask;
	}

	public ThirdTask getThirdTask() {
		return thirdTask;
	}
	
	public void addChampion(Champion c)
	{
		this.champions.add(c);
	}
	public void beginTournament() throws IOException, OutOfBordersException{
		this.firstTask = new FirstTask(this.champions);
		this.firstTask.setListener(this);
	}
	
	public void onFinishingFirstTask(ArrayList<Champion> winners) throws IOException{
		if(winners.size() > 0)
		{
			this.secondTask= new SecondTask(winners);
			this.secondTask.setListener(this);
		}
	}
	
	
	public void onFinishingSecondTask(ArrayList<Champion> winners)throws IOException
	{
		if(winners.size() > 0)
		{
			this.thirdTask = new ThirdTask(winners);
			this.thirdTask.setListener(this);
		}
	}
	
	public void onFinishingThirdTask(Champion winner)
	{
		
	}

}
