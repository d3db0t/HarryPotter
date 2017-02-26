package harrypotter.model.world;

public class TreasureCell extends Cell {
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
