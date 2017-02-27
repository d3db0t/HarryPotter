package harrypotter.model.tournament;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;

public class Tournament {
	ArrayList<Champion> champions;
	ArrayList<Spell> spells;
	FirstTask firstTask;
	SecondTask secondTask;
	ThirdTask thirdTask;
	
	public Tournament() throws Exception{
		ArrayList<Champion> champions = new ArrayList<Champion>();
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
	
	

}
