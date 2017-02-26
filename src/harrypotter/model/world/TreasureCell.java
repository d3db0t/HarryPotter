package harrypotter.model.world;
import harrypotter.model.character.Champion;

public class TreasureCell extends Cell implements Champion{
	private Champion owner ;
	
	public TreasureCell(Champion owner)
	{
		this.owner = owner ;
	}
	public Champion getOwner()
	{
		return owner ;
	}

}
