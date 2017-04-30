package harrypotter.controller;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import harrypotter.exceptions.OutOfBordersException;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Spell;
import harrypotter.model.world.Direction;

public interface TaskActionListener {

	public void moveUp();
	public void moveDown();
	public void moveRight();
	public void moveLeft();
	public void updateNEWPanels() throws OutOfBordersException, IOException;
	public void showFire();
	public void castHealing();
	public void moveSlytherin(Direction d);
	public void showMarkedCells(ArrayList <Point> p , String case1);
	public void moveGryffindorOnTrait();
	public void updateAfterPotion(int index) throws OutOfBordersException, IOException;
	public void castDamaging(Point p);
	public void castRelocating(Point pre , Point new1);
	public void removeChamp(Wizard w, String case1);
	public void showCollectible();
	//public void removeObstacle();
	//public void activateTrait();
	//public void castSpell();
}
