package harrypotter.controller;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;

import harrypotter.exceptions.OutOfBordersException;
import harrypotter.model.magic.Spell;
import harrypotter.model.world.Direction;

public interface TaskActionListener {

	public void moveUp();
	public void moveDown();
	public void moveRight();
	public void moveLeft();
	public void updateNEWPanels() throws OutOfBordersException, IOException;
	public void showFire();
	public void updatePotions();
	public void castHealing();
	public void moveSlytherin(Direction d);
	public void showMarkedCells(ArrayList <Point> p);
	public void moveGryffindorOnTrait();
	//public void removeChamp();
	//public void removeObstacle();
	//public void activateTrait();
	//public void castSpell();
}
