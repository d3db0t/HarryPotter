package harrypotter.model.world;

public abstract class Obstacle {
	private int hp ;
	
	public Obstacle(int hp)
	{
		this.hp = hp ;
	}
	public int getHp()
	{
		return hp ;
	}

}
