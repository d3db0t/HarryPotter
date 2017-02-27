package harrypotter.model.tournament;

import java.util.ArrayList;

import harrypotter.model.magic.Spell;

public class Tournament {
	ArrayList<Champion> champions;
	ArrayList<Spell> spells;
	FirstTask firstTask;
	SecondTask secondTask;
	ThirdTask thirdTask;
	
	public Tournament() throws Exception{
		ArrayList<Champion> champions = new ArrayList<Champion>();
		
	}
	
	public static int parseInt(String str){
		return Integer.parseInt(str);
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
