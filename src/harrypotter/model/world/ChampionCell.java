package harrypotter.model.world;

public class ChampionCell extends Cell{
    private Champion champ ;
    
    public ChampionCell(Champion champ)
    {
    	this.champ = champ ;
    }
    public Champion getChamp()
    {
    	return champ ;
    }
}
