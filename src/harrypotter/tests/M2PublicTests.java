package harrypotter.tests;

import harrypotter.model.character.*;
import harrypotter.model.magic.*;
import harrypotter.model.tournament.*;
import harrypotter.model.world.*;

import java.awt.Point;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static org.junit.Assert.*;

import org.junit.Test;

public class M2PublicTests {

	boolean hasCalled = false;
	int turnsPassed = 0;
	Champion w;
	SecondTask secondTask;
	ThirdTask thirdTask;
	Direction direction;
	ArrayList<Champion> winnersList;

	@Test(timeout = 1000)
	public void testBeginTournamentIntializesFirstTaskCorrectly()
			throws Exception {
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");

		Tournament t = new Tournament();
		t.getChampions().add(s);
		t.getChampions().add(h);
		t.getChampions().add(r);
		t.getChampions().add(g);
		t.beginTournament();
		FirstTask task1 = t.getFirstTask();

		assertNotNull(
				"Method beginTournament in Tournament class should intialize first task correctly ",
				task1);

		ArrayList<Champion> championsOfTournament = t.getChampions();
		ArrayList<Champion> championsOfTask = task1.getChampions();
		boolean condition = championsOfTournament.get(0).equals(
				championsOfTask.get(0))
				&& championsOfTournament.get(1).equals(championsOfTask.get(1))
				&& championsOfTournament.get(2).equals(championsOfTask.get(2))
				&& championsOfTournament.get(3).equals(championsOfTask.get(3));
		assertTrue(
				"Method beginTournament in Tournament class should intialize first task correctly and pass to it the same ArrayList of champions",
				condition);
	}

	@Test(timeout = 1000)
	public void testCastDamagingSpellOnChampionCell1() throws Exception {
		testMethodExistsInClass(Task.class, "castDamagingSpell", true,
				Void.TYPE, DamagingSpell.class, Direction.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(g);
		task.getMap()[4][3] = new ChampionCell(g);

		task.getMap()[5][3] = new ChampionCell(r);

		((Wizard) task.getCurrentChamp()).setLocation(new Point(4, 3));

		DamagingSpell dmg = new DamagingSpell("Sectumsempra", 150, 5, 300);
		task.castDamagingSpell(dmg, Direction.BACKWARD);

		assertEquals(
				"In FirstTask, when the current champion casts a damaging spell on a Champion cell, the hp of the champion in the cell should be affected",
				450, r.getHp());

		SecondTask task2 = new SecondTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task2.getMap()[i][j] = new EmptyCell();

		task2.setCurrentChamp(g);
		task2.getMap()[4][3] = new ChampionCell(g);

		task2.getMap()[5][3] = new ChampionCell(r);

		((Wizard) task2.getCurrentChamp()).setLocation(new Point(4, 3));
		dmg = new DamagingSpell("Sectumsempra", 150, 5, 300);
		task2.castDamagingSpell(dmg, Direction.BACKWARD);

		assertEquals(
				"In SecondTask, when the current champion casts a damaging spell on a Champion cell, the hp of the champion in the cell should be affected",
				450, r.getHp());

		ThirdTask task3 = new ThirdTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task3.getMap()[i][j] = new EmptyCell();

		task3.setCurrentChamp(g);
		task3.getMap()[4][3] = new ChampionCell(g);

		task3.getMap()[5][3] = new ChampionCell(r);

		((Wizard) task3.getCurrentChamp()).setLocation(new Point(4, 3));
		dmg = new DamagingSpell("Sectumsempra", 150, 5, 300);
		task3.castDamagingSpell(dmg, Direction.BACKWARD);

		assertEquals(
				"In ThirdTask, when the current champion casts a damaging spell on a Champion cell, the hp of the champion in the cell should be affected",
				450, r.getHp());
	}

	@Test(timeout = 1000)
	public void testCastDamagingSpellOnChampionCellThirdTaskHufflepuffWizard()
			throws Exception {
		testMethodExistsInClass(Task.class, "castDamagingSpell", true,
				Void.TYPE, DamagingSpell.class, Direction.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		ThirdTask task = new ThirdTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(g);
		task.getMap()[4][3] = new ChampionCell(g);

		task.getMap()[5][3] = new ChampionCell(h);

		((Wizard) task.getCurrentChamp()).setLocation(new Point(4, 3));

		DamagingSpell dmg = new DamagingSpell("Sectumsempra", 150, 5, 300);
		task.castDamagingSpell(dmg, Direction.BACKWARD);
		assertEquals(
				"In the Thirdtask, when the current champion casts a damaging spell on a Champion cell, the hp of the champion in the cell should be deducted by half the damage in case of HufflepuffWizard",
				850, h.getHp());

	}

	@Test(timeout = 1000)
	public void testCastDamagingSpellOnObstacleCell1() throws Exception {
		testMethodExistsInClass(Task.class, "castDamagingSpell", true,
				Void.TYPE, DamagingSpell.class, Direction.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(h);
		task.getMap()[4][3] = new ChampionCell(h);
		PhysicalObstacle p = new PhysicalObstacle(1000);
		task.getMap()[5][3] = new ObstacleCell(p);

		((Wizard) task.getCurrentChamp()).setLocation(new Point(4, 3));

		DamagingSpell dmg = new DamagingSpell("Sectumsempra", 150, 5, 300);
		task.castDamagingSpell(dmg, Direction.BACKWARD);

		assertEquals(
				"In the FirstTask, when the current champion casts a damaging spell, the damage value of the spell should be deducted from the hp of the obstacles",
				700, p.getHp());

		SecondTask task2 = new SecondTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task2.getMap()[i][j] = new EmptyCell();

		task2.setCurrentChamp(h);
		task2.getMap()[4][3] = new ChampionCell(h);
		Merperson Merperson = new Merperson(10, 1000);
		task2.getMap()[5][3] = new ObstacleCell(Merperson);

		((Wizard) task2.getCurrentChamp()).setLocation(new Point(4, 3));

		dmg = new DamagingSpell("Sectumsempra", 150, 5, 300);

		task2.castDamagingSpell(dmg, Direction.BACKWARD);

		assertEquals(
				"In the secondTask, when the current champion casts a damaging spell, the damage value of the spell should be deducted from the hp of the obstacles",
				700, p.getHp());

		ThirdTask task3 = new ThirdTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task3.getMap()[i][j] = new EmptyCell();

		task3.setCurrentChamp(h);
		task.getMap()[4][3] = new ChampionCell(h);
		p = new PhysicalObstacle(1000);
		task3.getMap()[5][3] = new ObstacleCell(p);

		dmg = new DamagingSpell("Sectumsempra", 150, 5, 300);

		((Wizard) task3.getCurrentChamp()).setLocation(new Point(4, 3));

		task3.castDamagingSpell(dmg, Direction.BACKWARD);

		assertEquals(
				"In the thirdTask, when the current champion casts a damaging spell, the damage value of the spell should be deducted from the hp of the obstacles",
				700, p.getHp());
	}

	@Test(timeout = 1000)
	public void testCastRelocatingSpellCallsFinalizeAction() throws Exception {
		hasCalled = false;
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e) {
			public void finalizeAction() {
				hasCalled = true;
			}
		};

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(g);
		task.getMap()[7][8] = new ChampionCell(g);
		task.getMap()[8][8] = new ChampionCell(r);

		((Wizard) task.getCurrentChamp()).setLocation(new Point(7, 8));

		RelocatingSpell rel = new RelocatingSpell("Accio", 100, 1, 1);
		g.getSpells().add(rel);
		task.castRelocatingSpell(rel, Direction.BACKWARD, Direction.FORWARD, 1);
		assertTrue(
				"castRelocatingSpell method in FirstTask should call finalizeAction method",
				hasCalled);

		hasCalled = false;
		SecondTask task2 = new SecondTask(e) {
			public void finalizeAction() {
				hasCalled = true;
			}
		};

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task2.getMap()[i][j] = new EmptyCell();

		task2.setCurrentChamp(g);
		task2.getMap()[7][8] = new ChampionCell(g);
		task2.getMap()[8][8] = new ChampionCell(r);

		((Wizard) task2.getCurrentChamp()).setLocation(new Point(7, 8));

		rel = new RelocatingSpell("Accio", 100, 1, 1);
		g.getSpells().add(rel);
		task2.castRelocatingSpell(rel, Direction.BACKWARD, Direction.FORWARD, 1);
		assertTrue(
				"castRelocatingSpell method in SecondTask should call finalizeAction method",
				hasCalled);

		hasCalled = false;
		ThirdTask task3 = new ThirdTask(e) {
			public void finalizeAction() {
				hasCalled = true;
			}
		};

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task3.getMap()[i][j] = new EmptyCell();

		task3.setCurrentChamp(g);
		task3.getMap()[7][8] = new ChampionCell(g);
		task3.getMap()[8][8] = new ChampionCell(r);

		((Wizard) task3.getCurrentChamp()).setLocation(new Point(7, 8));

		rel = new RelocatingSpell("Accio", 100, 1, 1);
		g.getSpells().add(rel);
		task3.castRelocatingSpell(rel, Direction.BACKWARD, Direction.FORWARD, 1);
		assertTrue(
				"castRelocatingSpell method in ThirdTask should call finalizeAction method",
				hasCalled);
	}

	@Test(timeout = 1000)
	public void testCastRelocatingSpellCallsUseSpell() throws Exception {
		hasCalled = false;
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e) {
			public void useSpell(Spell spell) {
				hasCalled = true;
			}
		};

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(g);
		task.getMap()[7][8] = new ChampionCell(g);
		task.getMap()[8][8] = new ChampionCell(r);

		((Wizard) task.getCurrentChamp()).setLocation(new Point(7, 8));

		RelocatingSpell rel = new RelocatingSpell("Accio", 100, 1, 1);
		g.getSpells().add(rel);
		task.castRelocatingSpell(rel, Direction.BACKWARD, Direction.FORWARD, 1);
		assertTrue(
				"castRelocatingSpell method in FirstTask should call useSpell method",
				hasCalled);

		hasCalled = false;
		SecondTask task2 = new SecondTask(e) {
			public void useSpell(Spell spell) {
				hasCalled = true;
			}
		};

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task2.getMap()[i][j] = new EmptyCell();

		task2.setCurrentChamp(g);
		task2.getMap()[7][8] = new ChampionCell(g);
		task2.getMap()[8][8] = new ChampionCell(r);

		((Wizard) task2.getCurrentChamp()).setLocation(new Point(7, 8));

		rel = new RelocatingSpell("Accio", 100, 1, 1);
		g.getSpells().add(rel);
		task2.castRelocatingSpell(rel, Direction.BACKWARD, Direction.FORWARD, 1);
		assertTrue(
				"castRelocatingSpell method in SecondTask should call useSpell method",
				hasCalled);

		hasCalled = false;
		ThirdTask task3 = new ThirdTask(e) {
			public void useSpell(Spell spell) {
				hasCalled = true;
			}
		};

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task3.getMap()[i][j] = new EmptyCell();

		task3.setCurrentChamp(g);
		task3.getMap()[7][8] = new ChampionCell(g);
		task3.getMap()[8][8] = new ChampionCell(r);

		((Wizard) task3.getCurrentChamp()).setLocation(new Point(7, 8));

		rel = new RelocatingSpell("Accio", 100, 1, 1);
		g.getSpells().add(rel);
		task3.castRelocatingSpell(rel, Direction.BACKWARD, Direction.FORWARD, 1);
		assertTrue(
				"castRelocatingSpell method in ThirdTask should call useSpell method",
				hasCalled);
	}

	@Test(timeout = 1000)
	public void testCastRelocatingSpellOnObstacleCell() throws Exception {
		testMethodExistsInClass(Task.class, "castRelocatingSpell", true,
				Void.TYPE, RelocatingSpell.class, Direction.class,
				Direction.class, int.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(h);
		task.getMap()[7][8] = new ChampionCell(g);
		PhysicalObstacle p = new PhysicalObstacle(1000);
		task.getMap()[8][8] = new ObstacleCell(p);

		((Wizard) task.getCurrentChamp()).setLocation(new Point(7, 8));

		RelocatingSpell rel = new RelocatingSpell("Accio", 100, 1, 1);
		g.getSpells().add(rel);
		task.castRelocatingSpell(rel, Direction.BACKWARD, Direction.FORWARD, 1);

		assertTrue(
				"In FirstTask, when a relocating spell is applied on an obstacle cell, the target cell should be an obstacle cell",
				task.getMap()[6][8] instanceof ObstacleCell);
		assertTrue(
				"In FirstTask, when a relocating spell is applied on an obstacle cell, the obstacle should be moved to the target cell in the correct direction",
				((ObstacleCell) task.getMap()[6][8]).getObstacle().equals(p));
		assertTrue(
				"In FirstTask, when a relocating spell is applied on an obstacle cell, the cell should become an Empty cell after the relocation",
				task.getMap()[8][8] instanceof EmptyCell);

		SecondTask task2 = new SecondTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task2.getMap()[i][j] = new EmptyCell();

		task2.setCurrentChamp(h);
		task2.getMap()[2][3] = new ChampionCell(h);
		Merperson mer = new Merperson(3, 100);
		task2.getMap()[2][2] = new ObstacleCell(mer);

		((Wizard) task2.getCurrentChamp()).setLocation(new Point(2, 3));

		rel = new RelocatingSpell("Accio", 100, 1, 3);
		h.getSpells().add(rel);
		task2.castRelocatingSpell(rel, Direction.LEFT, Direction.RIGHT, 3);

		assertTrue(
				"In SecondTask, when a relocating spell is applied on an obstacle cell, the target cell should be an obstacle cell",
				task2.getMap()[2][6] instanceof ObstacleCell);
		assertTrue(
				"In SecondTask, when a relocating spell is applied on an obstacle cell, the obstacle should be moved to the target cell in the correct direction",
				((ObstacleCell) task2.getMap()[2][6]).getObstacle().equals(mer));
		assertTrue(
				"In SecondTask, when a relocating spell is applied on an obstacle cell, the cell should become an Empty cell after the relocation",
				task2.getMap()[2][2] instanceof EmptyCell);

		ThirdTask task3 = new ThirdTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task3.getMap()[i][j] = new EmptyCell();

		task3.setCurrentChamp(h);
		task3.getMap()[1][5] = new ChampionCell(h);
		task3.getMap()[0][5] = new ObstacleCell(p);

		((Wizard) task3.getCurrentChamp()).setLocation(new Point(1, 5));

		rel = new RelocatingSpell("Accio", 100, 1, 2);
		h.getSpells().add(rel);
		task3.castRelocatingSpell(rel, Direction.FORWARD, Direction.RIGHT, 2);

		assertTrue(
				"In ThirdTask, when a relocating spell is applied on an obstacle cell, the target cell should be an obstacle cell",
				task3.getMap()[1][7] instanceof ObstacleCell);
		assertTrue(
				"In ThirdTask, when a relocating spell is applied on an obstacle cell, the obstacle should be moved to the target cell in the correct direction",
				((ObstacleCell) task3.getMap()[1][7]).getObstacle().equals(p));
		assertTrue(
				"In ThirdTask, when a relocating spell is applied on an obstacle cell, the cell should become an Empty cell after the relocation",
				task3.getMap()[0][5] instanceof EmptyCell);

	}

	@Test(timeout = 1000)
	public void testChampionsLocationIsSetCorrectly() throws Exception {
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);

		FirstTask task1 = new FirstTask(e);
		ArrayList<Champion> championsList = task1.getChampions();

		for (int i = 0; i < championsList.size(); i++) {
			Wizard champ = (Wizard) championsList.get(i);
			if (i == 0) {
				assertEquals(
						"The instance variable location of the first champion should be set correctly after the intialization of the FirstTask.",
						new Point(9, 0), champ.getLocation());
			}

			else if (i == 1) {
				assertEquals(
						"The instance variable location of the second champion should be set correctly after the intialization of the FirstTask.",
						new Point(9, 9), champ.getLocation());

			} else if (i == 2) {
				assertEquals(
						"The instance variable location of the third champion should be set correctly after the intialization of the FirstTask.",
						new Point(0, 9), champ.getLocation());

			} else {
				assertEquals(
						"The instance variable location of the fourth champion should be set correctly after the intialization of the FirstTask.",
						new Point(0, 0), champ.getLocation());

			}
		}

		e = new ArrayList<>();
		g = new GryffindorWizard("gryff");
		h = new HufflepuffWizard("huff");
		r = new RavenclawWizard("raven");
		s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);

		SecondTask task2 = new SecondTask(e);

		championsList = task2.getChampions();

		for (int i = 0; i < championsList.size(); i++) {
			Wizard champ = (Wizard) championsList.get(i);
			if (i == 0) {
				assertEquals(
						"The instance variable location of the first champion should be set correctly after the intialization of the SecondTask.",
						new Point(9, 0), champ.getLocation());
			}

			else if (i == 1) {
				assertEquals(
						"The instance variable location of the second champion should be set correctly after the intialization of the SecondTask.",
						new Point(9, 9), champ.getLocation());

			} else if (i == 2) {
				assertEquals(
						"The instance variable location of the third champion should be set correctly after the intialization of the SecondTask.",
						new Point(0, 9), champ.getLocation());

			} else {
				assertEquals(
						"The instance variable location of the fourth champion should be set correctly after the intialization of the SecondTask.",
						new Point(0, 0), champ.getLocation());

			}
		}

		e = new ArrayList<>();
		g = new GryffindorWizard("gryff");
		h = new HufflepuffWizard("huff");
		r = new RavenclawWizard("raven");
		s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);

		ThirdTask task3 = new ThirdTask(e);

		championsList = task3.getChampions();
		Point[] champLocations = new Point[championsList.size()];

		for (int i = 0; i < task3.getMap().length; i++)
			for (int j = 0; j < task3.getMap()[i].length; j++)
				if (task3.getMap()[i][j] instanceof ChampionCell) {

					int champI = championsList.indexOf(((ChampionCell) task3
							.getMap()[i][j]).getChamp());
					if (champI >= 0)
						champLocations[champI] = new Point(i, j);

				}

		for (int i = 0; i < championsList.size(); i++) {
			Wizard champ = (Wizard) championsList.get(i);
			if (i == 0) {
				assertEquals(
						"The instance variable location of the first champion should be set correctly after the intialization of the ThirdTask.",
						champLocations[i], champ.getLocation());
			}

			else if (i == 1) {
				assertEquals(
						"The instance variable location of the second champion should be set correctly after the intialization of the ThirdTask.",
						champLocations[i], champ.getLocation());

			} else if (i == 2) {
				assertEquals(
						"The instance variable location of the third champion should be set correctly after the intialization of the ThirdTask.",
						champLocations[i], champ.getLocation());

			} else {
				assertEquals(
						"The instance variable location of the fourth champion should be set correctly after the intialization of the ThirdTask.",
						champLocations[i], champ.getLocation());

			}
		}

	}

	@Test(timeout = 1000)
	public void testConstructorFirstTaskInitialization() throws Exception {
		ArrayList<Champion> e = new ArrayList<>();
		Constructor<?> constructor = Class.forName(
				"harrypotter.model.tournament.FirstTask").getConstructor(
				e.getClass());

		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		Object myObj = constructor.newInstance(e);
		Method myObjMethod = myObj.getClass().getMethod("getChampions");
		@SuppressWarnings("unchecked")
		ArrayList<Champion> champs = (ArrayList<Champion>) myObjMethod
				.invoke(myObj);

		for (Champion c : champs) {
			ArrayList<Spell> spells = ((Wizard) c).getSpells();
			for (Spell ss : spells) {
				if (ss.getCoolDown() != 0)
					fail("Cooldown variable of spell: " + ss.getName()
							+ " for the Champion: " + ((Wizard) c).getName()
							+ " should be reset in Task constructor");
			}
			assertEquals(
					"Hp variable for the Champion: " + ((Wizard) c).getName()
							+ " should be reset in Task constructor",
					s.getDefaultHp(), s.getHp());
			assertEquals(
					"Ip variable for the Champion: " + ((Wizard) c).getName()
							+ " should be reset in Task constructor",
					s.getDefaultIp(), s.getIp());
			assertEquals("TraitCooldown variable for the Champion: "
					+ ((Wizard) c).getName()
					+ " should be reset in Task constructor", 0,
					s.getTraitCooldown());
			assertNotNull(((Wizard) c).getName()
					+ "'listener is incorrectly set", s.getListener());
			assertEquals(((Wizard) c).getName()
					+ "'listener is incorrectly set", myObj, s.getListener());

		}

		myObjMethod = myObj.getClass().getMethod("getAllowedMoves");
		int allowedMoves = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the FirstTask class should initialize the instance variable \"allowedMoves\" correctly",
				1, allowedMoves);

		myObjMethod = myObj.getClass().getMethod("isTraitActivated");
		boolean traitActivated = (boolean) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the FirstTask class should initialize the instance variable \"traitActivated\" correctly",
				false, traitActivated);

		myObjMethod = myObj.getClass().getMethod("getMarkedCells");
		@SuppressWarnings("unchecked")
		ArrayList<Point> markedCells = (ArrayList<Point>) myObjMethod
				.invoke(myObj);
		assertNotNull(
				"The constructor of the FirstTask class should initialize the instance variable \"markedCells\" correctly",
				markedCells);
		assertTrue(
				"The constructor of the FirstTask class should initialize the instance variable \"markedCells\" correctly",
				markedCells.size() == 0 || markedCells.size() == 2);
	}

	@Test(timeout = 1000)
	public void testCurrentChampIsSetCorrectly() throws Exception {
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);
		FirstTask task = new FirstTask(e);

		assertEquals(
				"CurrentChamp is not correctly set after FirstTask intialization",
				task.getChampions().get(0), task.getCurrentChamp());

		SecondTask task2 = new SecondTask(e);
		assertEquals(
				"CurrentChamp is not correctly set after SecondTask intialization",
				task2.getChampions().get(0), task2.getCurrentChamp());

		ThirdTask task3 = new ThirdTask(e);
		assertEquals(
				"CurrentChamp is not correctly set after ThirdTask intialization",
				task3.getChampions().get(0), task3.getCurrentChamp());

	}

	@Test(timeout = 1000)
	public void testDragonFireDamageCurrentChamp() throws Exception {
		for (int yr = 0; yr < 500; yr++) {
			testMethodExistsInClass(FirstTask.class, "fire", true, Void.TYPE);
			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);
			FirstTask task = new FirstTask(e);
			int expectedDmg = 150;

			for (int i = 0; i < 10; i++)
				for (int j = 0; j < 10; j++)
					task.getMap()[i][j] = new EmptyCell();

			for (int c = 0; c < 6; c++) {
				int[] pointExist = new int[5];
				task.setCurrentChamp(s);
				int i, j = 0;
				i = (int) (3 + (Math.random() * 7));
				j = (int) (3 + (Math.random() * 7));
				switch (c) {
				case 0:
					i = 0;
					j = 0;
					break;
				case 1:
					i = 9;
					break;
				case 2:
					j = 0;
					break;
				case 3:
					j = 9;
					i = 9;
					break;
				}
				task.getMap()[i][j] = new ChampionCell(s);
				Wizard currentChamp = ((Wizard) task.getCurrentChamp());
				currentChamp.setLocation(new Point(i, j));
				task.getMarkedCells().clear();
				task.markCells();
				ArrayList<Point> markedCells = task.getMarkedCells();
				markedCells.trimToSize();
				assertEquals("Number of cells in marked cells is incorrect", 2,
						task.getMarkedCells().size());

				boolean duplicateCells = markedCells.get(0).x == markedCells
						.get(1).x;
				duplicateCells = duplicateCells
						&& markedCells.get(0).y == markedCells.get(1).y;
				assertFalse("The marked cells must be two distinct cells",
						duplicateCells);

				for (int k = 0; k < 2; k++) {
					int x, y = 0;
					x = markedCells.get(k).x;
					y = markedCells.get(k).y;
					if ((x == i && y == j)) {
						pointExist[0] = 1;
					} else if ((x == (i + 1) && y == j) && i != 9) {
						pointExist[1] = 1;
					} else if ((x == (i - 1) && y == j) && i != 0) {
						pointExist[2] = 1;
					} else if ((x == i && y == (j - 1)) && j != 0) {
						pointExist[3] = 1;
					} else if ((x == i && y == (j + 1)) && j != 9) {
						pointExist[4] = 1;
					}
				}
				if (currentChamp.getHp() < 150)
					expectedDmg = currentChamp.getHp();
				assertTrue(
						"One or both of the marked cells is in an incorrect location",
						sumArray(pointExist) == 2);
				currentChamp
						.setLocation((Point) markedCells.get(c / 3).clone());

				task.getMap()[i][j] = new EmptyCell();

				int champX = (int) markedCells.get(c / 3).getX();
				int champY = (int) markedCells.get(c / 3).getY();
				task.getMap()[champX][champY] = new ChampionCell(s);
				int hpOld = currentChamp.getHp();

				task.fire();
				assertEquals(
						"The damage inflicted on the champion by the dragon fire is incorrect",
						(hpOld - expectedDmg), currentChamp.getHp());
				task.getMap()[champX][champY] = new EmptyCell();

			}
			assertEquals("Hp of the champion should have reached zero", 0,
					((Wizard) task.getCurrentChamp()).getHp());

		}
	}

	@Test(timeout = 1000)
	public void testDragonFireOnOtherChampionWhenChampionGoesToEggCell()
			throws Exception {
		testMethodExistsInClass(Task.class, "moveRight", true, Void.TYPE);

		while (true) {
			boolean done = false;
			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			SlytherinWizard r = new SlytherinWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");

			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);
			FirstTask task = new FirstTask(e);
			int expectedDmg = 150;

			task.getMap()[4][2] = new EmptyCell();
			task.getMap()[4][3] = new EmptyCell();

			task.setCurrentChamp(s);
			Wizard currentChamp = ((Wizard) task.getCurrentChamp());
			task.getMap()[(int) currentChamp.getLocation().getX()][(int) currentChamp
					.getLocation().getY()] = new EmptyCell();
			task.getMap()[4][2] = new ChampionCell(s);
			currentChamp.setLocation(new Point(4, 2));

			task.getMap()[(int) r.getLocation().getX()][(int) r.getLocation()
					.getY()] = new EmptyCell();
			task.getMap()[3][3] = new ChampionCell(r);
			r.setLocation(new Point(3, 3));
			r.setHp(150);
			for (int c = 0; c < 2; c++) {
				task.setCurrentChamp(s);
				currentChamp = ((Wizard) task.getCurrentChamp());
				task.getMarkedCells().clear();
				task.markCells();
				ArrayList<Point> markedCells = task.getMarkedCells();
				assertEquals("Number of cells in marked cells is incorrect", 2,
						task.getMarkedCells().size());

				boolean duplicateCells = markedCells.get(0).x == markedCells
						.get(1).x;
				duplicateCells = duplicateCells
						&& markedCells.get(0).y == markedCells.get(1).y;
				assertFalse("The marked cells must be two distinct cells",
						duplicateCells);

				int[] pointExist = new int[5];
				int i = 4;
				int j = 2 + c;
				boolean marked = false;
				boolean otherMarked = false;
				for (int k = 0; k < 2; k++) {
					int x, y = 0;
					x = markedCells.get(k).x;
					y = markedCells.get(k).y;
					if ((x == i && y == j)) {
						pointExist[0] = 1;
					} else if ((x == (i + 1) && y == j) && i != 9) {
						pointExist[1] = 1;
					} else if ((x == (i - 1) && y == j) && i != 0) {
						otherMarked = true;
						pointExist[2] = 1;
					} else if ((x == i && y == (j - 1)) && j != 0) {
						pointExist[3] = 1;
					} else if ((x == i && y == (j + 1)) && j != 9) {
						marked = true;
						pointExist[4] = 1;
					}
				}
				assertTrue(
						"One or both of the marked cells is in an incorrect location",
						sumArray(pointExist) == 2);

				if (marked && otherMarked) {
					int hpOld = currentChamp.getHp();
					int hpOldOther = r.getHp();
					task.moveRight();
					if (j == 2)
						assertEquals(
								"The damage inflicted on the current champion by the dragon fire is incorrect",
								expectedDmg, (hpOld - currentChamp.getHp()));
					else {
						assertEquals(
								"The damage inflicted on the current champion by the dragon fire is incorrect, the champion should win before receving any damage from the dragon",
								0, (hpOld - currentChamp.getHp()));
						assertEquals(
								"The damage inflicted on the other champion by the dragon fire is incorrect, dragon should fire even if the current champion reaches the egg cell ",
								expectedDmg, (hpOldOther - r.getHp()));
						assertTrue("Champion should be added to winners list",
								task.getWinners().contains(s));
						assertFalse(
								"Other Champion should NOT be added to the winners list",
								task.getWinners().contains(r));
						done = true;
					}

				} else
					c = 2;
			}
			if (done)
				break;
		}
	}

	@Test(timeout = 1000)
	public void testEncounterMerPeopleChampionDead() throws Exception {

		testMethodExistsInClass(SecondTask.class, "encounterMerPerson", true,
				Void.TYPE);
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		int merPersons = 4;

		for (int i = 1; i < 9; i++) {
			for (int j = 1; j < 9; j++) {
				ArrayList<Champion> e = new ArrayList<>();
				SlytherinWizard s = new SlytherinWizard("slyth");
				e.add(g);
				e.add(h);
				e.add(r);
				e.add(s);
				SecondTask task = new SecondTask(e);

				for (int x = 0; x < 10; x++)
					for (int y = 0; y < 10; y++)
						task.getMap()[x][y] = new EmptyCell();

				ArrayList<Point> possibleLocations = new ArrayList<Point>();
				possibleLocations.add(new Point(i + 1, j));
				possibleLocations.add(new Point(i - 1, j));
				possibleLocations.add(new Point(i, j - 1));
				possibleLocations.add(new Point(i, j + 1));

				task.getMap()[i][j] = new ChampionCell(s);
				task.setCurrentChamp(s);
				s.setLocation(new Point(i, j));

				for (int k = 0; k < possibleLocations.size() && k < merPersons; k++) {
					int x = (int) possibleLocations.get(k).getX();
					int y = (int) possibleLocations.get(k).getY();
					int damage = 240 + (int) (Math.random() * 51);
					task.getMap()[x][y] = new ObstacleCell(new Merperson(100,
							damage));
				}

				task.encounterMerPerson();
				assertEquals("Hp of the champion should have reached zero", 0,
						s.getHp());

			}
		}
	}

	@Test(timeout = 1000)
	public void testEncounterMerPeopleNotInRangeClose() throws Exception {

		testMethodExistsInClass(SecondTask.class, "encounterMerPerson", true,
				Void.TYPE);
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		ArrayList<Champion> e = new ArrayList<>();
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		SecondTask task = new SecondTask(e);

		for (int x = 0; x < 10; x++)
			for (int y = 0; y < 10; y++)
				task.getMap()[x][y] = new EmptyCell();

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				ArrayList<Point> possibleLocations = new ArrayList<Point>();
				possibleLocations.add(new Point(i - 1, j - 1));
				possibleLocations.add(new Point(i + 1, j + 1));
				possibleLocations.add(new Point(i + 1, j - 1));
				possibleLocations.add(new Point(i - 1, j + 1));

				task.getMap()[i][j] = new ChampionCell(s);
				task.setCurrentChamp(s);
				s.setLocation(new Point(i, j));
				int hpOld = s.getHp();

				for (int k = 0; k < possibleLocations.size(); k++) {
					int x = (int) possibleLocations.get(k).getX();
					int y = (int) possibleLocations.get(k).getY();
					if (x >= 0 && x <= 9 && y >= 0 && y <= 9) {
						int damage = 100 + (int) (Math.random() * 51);
						task.getMap()[x][y] = new ObstacleCell(new Merperson(
								100, damage));

						task.encounterMerPerson();

					}
				}

				assertEquals(
						"The champion should not receive any damage from MerPeople not in the correct damage range",
						0, (hpOld - s.getHp()));

				task.getMap()[i][j] = new EmptyCell();

				for (int k = 0; k < possibleLocations.size(); k++) {
					int x = (int) possibleLocations.get(k).getX();
					int y = (int) possibleLocations.get(k).getY();
					if (x >= 0 && x <= 9 && y >= 0 && y <= 9) {
						task.getMap()[x][y] = new EmptyCell();

					}
				}

			}
		}
	}

	@Test(timeout = 1000)
	public void testEncounterMerPersonNotCurrentChampion() throws Exception {

		testMethodExistsInClass(SecondTask.class, "encounterMerPerson", true,
				Void.TYPE);
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ArrayList<Champion> e = new ArrayList<>();
				SlytherinWizard s = new SlytherinWizard("slyth");
				e.add(g);
				e.add(h);
				e.add(r);
				e.add(s);
				SecondTask task = new SecondTask(e);

				for (int x = 0; x < 10; x++)
					for (int y = 0; y < 10; y++)
						task.getMap()[x][y] = new EmptyCell();

				ArrayList<Point> possibleLocations = new ArrayList<Point>();

				if (i + 1 <= 9)
					possibleLocations.add(new Point(i + 1, j));
				if (i - 1 >= 0)
					possibleLocations.add(new Point(i - 1, j));
				if (j - 1 >= 0)
					possibleLocations.add(new Point(i, j - 1));
				if (j + 1 <= 9)
					possibleLocations.add(new Point(i, j + 1));

				int damage = 100 + (int) (Math.random() * 51);
				task.getMap()[i][j] = new ObstacleCell(new Merperson(100,
						damage));
				Point current;
				Point notCurrent = null;

				current = possibleLocations
						.get((int) (Math.random() * possibleLocations.size()));
				while (current != notCurrent)
					notCurrent = possibleLocations
							.get((int) (Math.random() * possibleLocations
									.size()));

				int x, y = 0;
				x = (int) current.getX();
				y = (int) current.getY();
				task.getMap()[x][y] = new ChampionCell(s);
				task.setCurrentChamp(s);
				s.setLocation((Point) current.clone());
				x = (int) notCurrent.getX();
				y = (int) notCurrent.getY();
				task.getMap()[x][y] = new ChampionCell(g);
				g.setLocation((Point) notCurrent.clone());

				int hpOld = s.getHp();
				task.encounterMerPerson();
				assertEquals(
						"Inflicted merperson damage on the champion is incorrect",
						damage, (hpOld - s.getHp()));
				assertEquals(
						"Merperson should not attack champions other than the current champion",
						0, (g.getDefaultHp() - g.getHp()));
			}

		}

	}

	@Test(timeout = 1000)
	public void testEncounterOneMerPerson() throws Exception {

		testMethodExistsInClass(SecondTask.class, "encounterMerPerson", true,
				Void.TYPE);
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ArrayList<Champion> e = new ArrayList<>();
				SlytherinWizard s = new SlytherinWizard("slyth");
				e.add(g);
				e.add(h);
				e.add(r);
				e.add(s);
				SecondTask task = new SecondTask(e);

				for (int x = 0; x < 10; x++)
					for (int y = 0; y < 10; y++)
						task.getMap()[x][y] = new EmptyCell();

				ArrayList<Point> possibleLocations = new ArrayList<Point>();
				if (i + 1 <= 9)
					possibleLocations.add(new Point(i + 1, j));
				if (i - 1 >= 0)
					possibleLocations.add(new Point(i - 1, j));
				if (j - 1 >= 0)
					possibleLocations.add(new Point(i, j - 1));
				if (j + 1 <= 9)
					possibleLocations.add(new Point(i, j + 1));

				task.getMap()[i][j] = new ChampionCell(s);
				task.setCurrentChamp(s);
				s.setLocation(new Point(i, j));
				for (int k = 0; k < possibleLocations.size(); k++) {
					int x = (int) possibleLocations.get(k).getX();
					int y = (int) possibleLocations.get(k).getY();

					int damage = 100 + (int) (Math.random() * 51);
					task.getMap()[x][y] = new ObstacleCell(new Merperson(100,
							damage));

					int hpOld = s.getHp();
					task.encounterMerPerson();
					assertEquals(
							"Inflicted merperson damage on the champion is incorrect",
							damage, (hpOld - s.getHp()));
					task.getMap()[x][y] = new EmptyCell();
				}
			}
		}
	}

	@Test(timeout = 1000)
	public void testEncounterTwoMerPeople() throws Exception {

		testMethodExistsInClass(SecondTask.class, "encounterMerPerson", true,
				Void.TYPE);
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		int merPersons = 2;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ArrayList<Champion> e = new ArrayList<>();
				SlytherinWizard s = new SlytherinWizard("slyth");
				e.add(g);
				e.add(h);
				e.add(r);
				e.add(s);
				SecondTask task = new SecondTask(e);

				for (int x = 0; x < 10; x++)
					for (int y = 0; y < 10; y++)
						task.getMap()[x][y] = new EmptyCell();

				ArrayList<Point> possibleLocations = new ArrayList<Point>();
				if (i + 1 <= 9)
					possibleLocations.add(new Point(i + 1, j));
				if (i - 1 >= 0)
					possibleLocations.add(new Point(i - 1, j));
				if (j - 1 >= 0)
					possibleLocations.add(new Point(i, j - 1));
				if (j + 1 <= 9)
					possibleLocations.add(new Point(i, j + 1));

				task.getMap()[i][j] = new ChampionCell(s);
				task.setCurrentChamp(s);
				s.setLocation(new Point(i, j));
				int accumelatedDamage = 0;
				int hpOld = s.getHp();
				for (int k = 0; k < possibleLocations.size() && k < merPersons; k++) {
					int x = (int) possibleLocations.get(k).getX();
					int y = (int) possibleLocations.get(k).getY();
					int damage = 100 + (int) (Math.random() * 51);
					task.getMap()[x][y] = new ObstacleCell(new Merperson(100,
							damage));
					accumelatedDamage += damage;
				}
				task.encounterMerPerson();
				assertEquals(
						"Inflicted merperson damage on the champion is incorrect",
						accumelatedDamage, (hpOld - s.getHp()));
			}
		}
	}

	@Test(timeout = 1000)
	public void testFinalizeActionMethodInClassTask() throws Exception {
		testMethodExistsInClass(Task.class, "finalizeAction", true, Void.TYPE);

	}

	@Test(timeout = 1000)
	public void testFireEffectOnOtherCellTypes() throws Exception {

		testMethodExistsInClass(FirstTask.class, "markCells", true, Void.TYPE);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();
		for (int c = 0; c < 20; c++) {
			for (int i = 1; i < 9; i++) {
				for (int j = 1; j < 9; j++) {
					int random = (int) (Math.random() * 10);
					r.setHp(r.getDefaultHp());
					task.getMap()[i - 1][j] = new ObstacleCell(
							new PhysicalObstacle(250 + random));
					task.getMap()[i + 1][j] = new CollectibleCell(new Potion(
							"Senzo", 250 + random));
					task.getMap()[i][j - 1] = new ObstacleCell(
							new PhysicalObstacle(250 + random));
					task.getMap()[i][j + 1] = new ChampionCell(r);

					r.setLocation(new Point(i, j + 1));

					int[] pointExist = new int[5];
					task.setCurrentChamp(h);
					task.getMap()[i][j] = new ChampionCell(h);
					((Wizard) task.getCurrentChamp()).setLocation(new Point(i,
							j));
					task.getMarkedCells().clear();

					task.markCells();

					assertEquals(
							"Number of cells in marked cells is incorrect", 2,
							task.getMarkedCells().size());

					for (int k = 0; k < 2; k++) {
						double x, y = 0;
						x = task.getMarkedCells().get(k).getX();
						y = task.getMarkedCells().get(k).getY();
						if ((x == i && y == j)) {
							pointExist[0] = 1;
						} else if ((x == (i + 1) && y == j) && i != 9) {
							pointExist[1] = 1;
						} else if ((x == (i - 1) && y == j) && i != 0) {
							pointExist[2] = 1;
						} else if ((x == i && y == (j - 1)) && j != 0) {
							pointExist[3] = 1;
						} else if ((x == i && y == (j + 1)) && j != 9) {
							pointExist[4] = 1;
						}
					}
					assertTrue(
							"One or both of the marked cells is in an incorrect location",
							sumArray(pointExist) == 2);
					task.fire();
					assertTrue(
							"The type of the obstacle cell should not be affected by the fire of the dragon",
							task.getMap()[i - 1][j] instanceof ObstacleCell);
					assertTrue(
							"The type of the collectible cell should not be affected by the fire of the dragon",
							task.getMap()[i + 1][j] instanceof CollectibleCell);
					assertTrue(
							"The type of the obstacle cell should not be affected by the fire of the dragon",
							task.getMap()[i][j - 1] instanceof ObstacleCell);
					assertTrue(
							"The type of the champion cell should not be affected by the fire of the dragon",
							task.getMap()[i][j + 1] instanceof ChampionCell);
					assertTrue(
							"The obstacle types should not be affected by the fire of the dragon",
							((ObstacleCell) task.getMap()[i - 1][j])
									.getObstacle() instanceof PhysicalObstacle
									&& ((ObstacleCell) task.getMap()[i][j - 1])
											.getObstacle() instanceof PhysicalObstacle);

					task.getMap()[i][j] = new EmptyCell();
					task.getMap()[i - 1][j] = new EmptyCell();
					task.getMap()[i + 1][j] = new EmptyCell();
					task.getMap()[i][j - 1] = new EmptyCell();
					task.getMap()[i][j + 1] = new EmptyCell();
				}
			}
		}

	}

	@Test(timeout = 1000)
	public void testFirstTaskClassHandlesNullListenerWhenFourChampionsWin()
			throws Exception {

		testMethodExistsInClass(Tournament.class, "onFinishingFirstTask", true,
				Void.TYPE, ArrayList.class);
		hasCalled = false;
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.getMap()[5][4] = new ChampionCell(s);
		s.setLocation(new Point(5, 4));

		task.getMap()[3][4] = new ChampionCell(h);
		h.setLocation(new Point(3, 4));

		task.getMap()[4][5] = new ChampionCell(r);
		r.setLocation(new Point(4, 5));

		task.getMap()[4][3] = new ChampionCell(g);
		g.setLocation(new Point(4, 3));

		task.setListener(null);

		try {
			task.setCurrentChamp(s);
			task.moveForward();

			task.setCurrentChamp(h);
			task.moveBackward();

			task.setCurrentChamp(r);
			task.moveLeft();

			task.setCurrentChamp(g);
			task.moveRight();

		} catch (NullPointerException ee) {
			fail("The FirstTask class should handle when listener is null. It shouldn't notify the listener that the task ended.");
		}

	}

	@Test(timeout = 1000)
	public void testFirstTaskClassNotifiesListenerWhenAllChampionsDie()
			throws Exception {
		testMethodExistsInClass(Tournament.class, "onFinishingFirstTask", true,
				Void.TYPE, ArrayList.class);
		hasCalled = false;
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();
		Tournament t = new Tournament() {
			@Override
			public void onFinishingFirstTask(ArrayList<Champion> winners)
					throws IOException {
				hasCalled = true;
				super.onFinishingFirstTask(winners);
				secondTask = getSecondTask();
				winnersList = winners;
			}
		};
		task.setListener(t);

		task.setCurrentChamp(s);
		s.setHp(150);
		task.getMap()[3][1] = new ChampionCell(s);

		((Wizard) task.getCurrentChamp()).setLocation(new Point(3, 1));
		task.getMarkedCells().clear();
		task.getMarkedCells().add(new Point(3, 2));
		task.getMarkedCells().add(new Point(7, 8));
		task.moveRight();

		task.setCurrentChamp(h);
		h.setHp(150);
		task.getMap()[3][4] = new ChampionCell(h);
		h.setLocation(new Point(3, 4));
		((Wizard) task.getCurrentChamp()).setLocation(new Point(3, 4));
		task.getMarkedCells().clear();
		task.getMarkedCells().add(new Point(3, 3));
		task.getMarkedCells().add(new Point(7, 8));
		task.moveLeft();

		task.setCurrentChamp(r);
		r.setHp(150);
		task.getMap()[4][5] = new ChampionCell(r);
		r.setLocation(new Point(4, 5));
		((Wizard) task.getCurrentChamp()).setLocation(new Point(4, 5));
		task.getMarkedCells().clear();
		task.getMarkedCells().add(new Point(3, 5));
		task.getMarkedCells().add(new Point(7, 8));
		task.moveForward();

		task.setCurrentChamp(g);
		g.setHp(150);
		task.getMap()[4][3] = new ChampionCell(g);
		g.setLocation(new Point(4, 3));
		((Wizard) task.getCurrentChamp()).setLocation(new Point(4, 3));
		task.getMarkedCells().clear();
		task.getMarkedCells().add(new Point(4, 2));
		task.getMarkedCells().add(new Point(7, 8));
		task.moveLeft();

		assertTrue(
				"Upon completion of the first task, the FirstTask notifies the Tournament class",
				hasCalled);
		assertNull(
				"Upon completion of the first task, the tournament SHOULD NOT intialize the second task as long as there are no winners",
				secondTask);
		assertTrue(
				"Upon completion of the first task and no champions survived, the FirstTask should send to the Listener an empty ArrayList",
				winnersList.isEmpty());

	}

	@Test(timeout = 1000)
	public void testFirstTaskClassNotifiesListenerWhenFourChampionsWin()
			throws Exception {
		testMethodExistsInClass(Tournament.class, "onFinishingFirstTask", true,
				Void.TYPE, ArrayList.class);
		hasCalled = false;
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();
		Tournament t = new Tournament() {
			public void onFinishingFirstTask(ArrayList<Champion> winners)
					throws IOException {
				hasCalled = true;
				super.onFinishingFirstTask(winners);
				winnersList = winners;
				secondTask = getSecondTask();

			}
		};

		task.setListener(t);
		task.setCurrentChamp(s);
		task.getMap()[5][4] = new ChampionCell(s);

		((Wizard) task.getCurrentChamp()).setLocation(new Point(5, 4));

		task.getMap()[3][4] = new ChampionCell(h);
		h.setLocation(new Point(3, 4));

		task.getMap()[4][5] = new ChampionCell(r);
		r.setLocation(new Point(4, 5));

		task.getMap()[4][3] = new ChampionCell(g);
		g.setLocation(new Point(4, 3));

		task.setListener(t);

		task.moveForward();
		assertFalse(
				"ONLY upon the completion of the first task, the FirstTask notifies the Tournament class",
				hasCalled);

		task.setCurrentChamp(h);
		task.moveBackward();
		assertFalse(
				"ONLY upon the completion of the first task, the FirstTask notifies the Tournament class",
				hasCalled);

		task.setCurrentChamp(r);
		task.moveLeft();
		assertFalse(
				"ONLY upon the completion of the first task, the FirstTask notifies the Tournament class",
				hasCalled);

		task.setCurrentChamp(g);
		task.moveRight();

		assertTrue(
				"Upon completion of the first task, the FirstTask notifies the Tournament class",
				hasCalled);

		assertNotNull(
				"Upon completion of the first task, the tournament should intialize the second task as long as there are winners",
				secondTask);

		boolean condition = winnersList.contains(s) && winnersList.contains(h)
				&& winnersList.contains(r) && winnersList.contains(g);
		assertTrue(
				"Upon completion of the first task, only the champions who survived should be sent as winners to the second task",
				condition);
		assertEquals(
				"Upon completion of the first task, the tournament should intialize the second task and set its listener to the tournament",
				t, secondTask.getListener());

	}

	@Test(timeout = 1000)
	public void testFirstTaskListenerIsSetCorrectly() throws Exception {
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");

		Tournament t = new Tournament();
		t.getChampions().add(s);
		t.getChampions().add(h);
		t.getChampions().add(r);
		t.getChampions().add(g);

		t.beginTournament();

		FirstTask task1 = t.getFirstTask();
		assertEquals(
				"The instance variable listener of the FirstTask should be set correctly after the beginning of the Tournament.",
				t, task1.getListener());
	}

	@Test(timeout = 1000)
	public void testGetTargetPoint() throws Exception {

		testMethodExistsInClass(Task.class, "getTargetPoint", true,
				Point.class, Direction.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(h);
		task.getMap()[5][3] = new ChampionCell(h);
		((Wizard) task.getCurrentChamp()).setLocation(new Point(5, 3));

		Point targetPoint = task.getTargetPoint(Direction.BACKWARD);
		assertEquals(
				"The method getTargetPoint in class Task does not return the Point of the cell adjacent to the currentChamp's location in the BACKWARD direction",
				new Point(6, 3), targetPoint);

		((Wizard) task.getCurrentChamp()).setLocation(new Point(5, 3));

		targetPoint = task.getTargetPoint(Direction.FORWARD);
		assertEquals(
				"The method getTargetPoint in class Task does not return the Point of the cell adjacent to the currentChamp's location in the FORWARD direction",
				new Point(4, 3), targetPoint);

		((Wizard) task.getCurrentChamp()).setLocation(new Point(5, 3));

		targetPoint = task.getTargetPoint(Direction.LEFT);
		assertEquals(
				"The method getTargetPoint in class Task does not return the Point of the cell adjacent to the currentChamp's location in the LEFT direction",
				new Point(5, 2), targetPoint);

		((Wizard) task.getCurrentChamp()).setLocation(new Point(5, 3));

		targetPoint = task.getTargetPoint(Direction.RIGHT);
		assertEquals(
				"The method getTargetPoint in class Task does not return the Point of the cell adjacent to the currentChamp's location in the RIGHT direction",
				new Point(5, 4), targetPoint);

	}

	@Test(timeout = 1000)
	public void testGryffindorWizardNotifiesListener() throws Exception {
		testMethodExistsInClassOrSuperClass(FirstTask.class,
				"onGryffindorTrait", true, Void.TYPE);
		testMethodExistsInClassOrSuperClass(SecondTask.class,
				"onGryffindorTrait", true, Void.TYPE);
		testMethodExistsInClassOrSuperClass(ThirdTask.class,
				"onGryffindorTrait", true, Void.TYPE);

		testMethodExistsInClass(GryffindorWizard.class, "useTrait", true,
				Void.TYPE);

		hasCalled = false;
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);
		FirstTask task = new FirstTask(e) {
			public void onGryffindorTrait() {
				hasCalled = true;
			}
		};

		g.setListener(null);
		try {
			g.useTrait();
		} catch (NullPointerException ee) {
			fail("The method useTrait in GryffindorWizard class should handle when listener is null");
		}

		g.setListener(task);
		g.useTrait();
		assertTrue(
				"FirstTask, GryffindorWizard class should notify the Task class when the champion uses a special trait",
				hasCalled);

		hasCalled = false;
		e = new ArrayList<>();
		g = new GryffindorWizard("gryff");
		h = new HufflepuffWizard("huff");
		r = new RavenclawWizard("raven");
		s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);
		SecondTask task2 = new SecondTask(e) {
			public void onGryffindorTrait() {
				hasCalled = true;
			}
		};

		g.setListener(null);
		try {
			g.useTrait();
		} catch (NullPointerException ee) {
			fail("The method useTrait in GryffindorWizard class should handle when listener is null");
		}

		g.setListener(task2);
		g.useTrait();
		assertTrue(
				"SecondTask, GryffindorWizard class should notify the Task class when the champion uses a special trait",
				hasCalled);

		hasCalled = false;
		e = new ArrayList<>();
		g = new GryffindorWizard("gryff");
		h = new HufflepuffWizard("huff");
		r = new RavenclawWizard("raven");
		s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);
		ThirdTask task3 = new ThirdTask(e) {
			public void onGryffindorTrait() {
				hasCalled = true;
			}
		};

		g.setListener(null);
		try {
			g.useTrait();
		} catch (NullPointerException ee) {
			fail("The method useTrait in GryffindorWizard class should handle when listener is null");
		}

		g.setListener(task3);
		g.useTrait();
		assertTrue(
				"ThirdTask, GryffindorWizard class should notify the Task class when the champion uses a special trait",
				hasCalled);

	}

	@Test(timeout = 1000)
	public void testHufflepuffWizardNotifiesListener() throws Exception {
		testMethodExistsInClassOrSuperClass(FirstTask.class,
				"onHufflepuffTrait", true, Void.TYPE);
		testMethodExistsInClassOrSuperClass(SecondTask.class,
				"onHufflepuffTrait", true, Void.TYPE);
		testMethodExistsInClassOrSuperClass(ThirdTask.class,
				"onHufflepuffTrait", true, Void.TYPE);

		testMethodExistsInClass(HufflepuffWizard.class, "useTrait", true,
				Void.TYPE);

		hasCalled = false;
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);
		FirstTask task = new FirstTask(e) {
			public void onHufflepuffTrait() {
				hasCalled = true;
			}
		};

		h.setListener(null);
		try {
			h.useTrait();
		} catch (NullPointerException ee) {
			fail("The method useTrait in HufflepuffWizard class should handle when listener is null");
		}

		h.setListener(task);
		h.useTrait();
		assertTrue(
				"FirstTask, HufflepuffWizard class should notify the Task class when the champion uses a special trait",
				hasCalled);

		hasCalled = false;
		e = new ArrayList<>();
		g = new GryffindorWizard("gryff");
		h = new HufflepuffWizard("huff");
		r = new RavenclawWizard("raven");
		s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);
		SecondTask task2 = new SecondTask(e) {
			public void onHufflepuffTrait() {
				hasCalled = true;
			}
		};

		h.setListener(null);
		try {
			h.useTrait();
		} catch (NullPointerException ee) {
			fail("The method useTrait in HufflepuffWizard class should handle when listener is null");
		}

		h.setListener(task2);
		h.useTrait();
		assertTrue(
				"SecondTask, HufflepuffWizard class should notify the Task class when the champion uses a special trait",
				hasCalled);

		hasCalled = false;
		e = new ArrayList<>();
		g = new GryffindorWizard("gryff");
		h = new HufflepuffWizard("huff");
		r = new RavenclawWizard("raven");
		s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);
		ThirdTask task3 = new ThirdTask(e) {
			public void onHufflepuffTrait() {
				hasCalled = true;
			}
		};

		h.setListener(null);
		try {
			h.useTrait();
		} catch (NullPointerException ee) {
			fail("The method useTrait in HufflepuffWizard class should handle when listener is null");
		}

		h.setListener(task3);
		h.useTrait();
		assertTrue(
				"ThirdTask, HufflepuffWizard class should notify the Task class when the champion uses a special trait",
				hasCalled);

	}

	@Test(timeout = 1000)
	public void testInstanceVariableMarkedCellsInFirstTaskClass() {
		testInstanceVariablesArePresent(FirstTask.class, "markedCells", true);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableMarkedCellsInFirstTaskClassGetter() {
		testGetterMethodExistsInClass(FirstTask.class, "getMarkedCells",
				ArrayList.class);
	}

	@Test(timeout = 1000)
	public void testInstanceVariableMarkedCellsInFirstTaskClassSetter() {
		testSetterMethodExistsInClass(FirstTask.class, "setMarkedCells",
				ArrayList.class, true);
	}

	@Test(timeout = 1000)
	public void testMarkCellsCount() throws Exception {

		testMethodExistsInClass(FirstTask.class, "markCells", true, Void.TYPE);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int[] pointExist = new int[5];
				task.setCurrentChamp(h);
				task.getMap()[i][j] = new ChampionCell(h);
				((Wizard) task.getCurrentChamp()).setLocation(new Point(i, j));
				task.getMarkedCells().clear();
				task.markCells();
				ArrayList<Point> markedCells = task.getMarkedCells();
				assertEquals("Number of cells in marked cells is incorrect", 2,
						task.getMarkedCells().size());

				boolean duplicateCells = markedCells.get(0).x == markedCells
						.get(1).x;
				duplicateCells = duplicateCells
						&& markedCells.get(0).y == markedCells.get(1).y;
				assertFalse("The marked cells must be two distinct cells",
						duplicateCells);

				for (int k = 0; k < 2; k++) {

					int x, y = 0;
					x = markedCells.get(k).x;
					y = markedCells.get(k).y;
					if ((x == i && y == j)) {
						pointExist[0] = 1;
					} else if ((x == (i + 1) && y == j) && i != 9) {
						pointExist[1] = 1;
					} else if ((x == (i - 1) && y == j) && i != 0) {
						pointExist[2] = 1;
					} else if ((x == i && y == (j - 1)) && j != 0) {
						pointExist[3] = 1;
					} else if ((x == i && y == (j + 1)) && j != 9) {
						pointExist[4] = 1;
					}
				}

				assertTrue(
						"One or both of the marked cells is in an incorrect location",
						sumArray(pointExist) == 2);
				task.getMap()[i][j] = new EmptyCell();

			}
		}

	}

	@Test(timeout = 1000)
	public void testMarkCellsObstcles() throws Exception {

		testMethodExistsInClass(FirstTask.class, "markCells", true, Void.TYPE);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();
		int[] pointExistAll = new int[5];
		for (int c = 0; c < 20; c++) {
			for (int i = 1; i < 9; i++) {
				for (int j = 1; j < 9; j++) {
					if (c / 4 < 4)
						task.getMap()[i - 1][j] = new ObstacleCell(
								new PhysicalObstacle(250));
					if (c / 4 < 3)
						task.getMap()[i + 1][j] = new ObstacleCell(
								new PhysicalObstacle(250));
					if (c / 4 < 2)
						task.getMap()[i][j - 1] = new ObstacleCell(
								new PhysicalObstacle(250));
					if (c / 4 < 1)
						task.getMap()[i][j + 1] = new ObstacleCell(
								new PhysicalObstacle(250));
					int[] pointExist = new int[5];
					task.setCurrentChamp(h);
					task.getMap()[i][j] = new ChampionCell(h);
					((Wizard) task.getCurrentChamp()).setLocation(new Point(i,
							j));
					task.getMarkedCells().clear();
					task.markCells();
					ArrayList<Point> markedCells = task.getMarkedCells();
					assertEquals(
							"Number of cells in marked cells is incorrect", 2,
							task.getMarkedCells().size());

					boolean duplicateCells = markedCells.get(0).x == markedCells
							.get(1).x;
					duplicateCells = duplicateCells
							&& markedCells.get(0).y == markedCells.get(1).y;
					assertFalse("The marked cells must be two distinct cells",
							duplicateCells);

					for (int k = 0; k < 2; k++) {
						int x, y = 0;
						x = task.getMarkedCells().get(k).x;
						y = task.getMarkedCells().get(k).y;
						if ((x == i && y == j)) {
							pointExist[0] = 1;
							pointExistAll[0] = 1;
						} else if ((x == (i + 1) && y == j) && i != 9) {
							pointExist[1] = 1;
							pointExistAll[1] = 1;
						} else if ((x == (i - 1) && y == j) && i != 0) {
							pointExist[2] = 1;
							pointExistAll[2] = 1;
						} else if ((x == i && y == (j - 1)) && j != 0) {
							pointExist[3] = 1;
							pointExistAll[3] = 1;
						} else if ((x == i && y == (j + 1)) && j != 9) {
							pointExist[4] = 1;
							pointExistAll[4] = 1;
						}
					}
					assertTrue(
							"One or both of the marked cells is in an incorrect location",
							sumArray(pointExist) == 2);
					task.getMap()[i][j] = new EmptyCell();
					if (c / 4 < 4)
						task.getMap()[i - 1][j] = new EmptyCell();
					if (c / 4 < 3)
						task.getMap()[i + 1][j] = new EmptyCell();
					if (c / 4 < 2)
						task.getMap()[i][j - 1] = new EmptyCell();
					if (c / 4 < 1)
						task.getMap()[i][j + 1] = new EmptyCell();
				}
			}
		}
		assertTrue(
				"One or both of the marked cells is in an incorrect location",
				sumArray(pointExistAll) == 5);

	}

	@Test(timeout = 1000)
	public void testMarkCellsRange() throws Exception {

		testMethodExistsInClass(FirstTask.class, "markCells", true, Void.TYPE);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();
		int[] pointExistAll = new int[5];
		for (int c = 0; c < 20; c++) {
			for (int i = 1; i < 9; i++) {
				for (int j = 1; j < 9; j++) {
					int[] pointExist = new int[5];
					task.setCurrentChamp(h);
					task.getMap()[i][j] = new ChampionCell(h);
					((Wizard) task.getCurrentChamp()).setLocation(new Point(i,
							j));
					task.getMarkedCells().clear();
					task.markCells();
					assertEquals(
							"Number of cells in marked cells is incorrect", 2,
							task.getMarkedCells().size());
					for (int k = 0; k < 2; k++) {
						double x, y = 0;
						x = task.getMarkedCells().get(k).getX();
						y = task.getMarkedCells().get(k).getY();
						if ((x == i && y == j)) {
							pointExist[0] = 1;
							pointExistAll[0] = 1;
						} else if ((x == (i + 1) && y == j) && i != 9) {
							pointExist[1] = 1;
							pointExistAll[1] = 1;
						} else if ((x == (i - 1) && y == j) && i != 0) {
							pointExist[2] = 1;
							pointExistAll[2] = 1;
						} else if ((x == i && y == (j - 1)) && j != 0) {
							pointExist[3] = 1;
							pointExistAll[3] = 1;
						} else if ((x == i && y == (j + 1)) && j != 9) {
							pointExist[4] = 1;
							pointExistAll[4] = 1;
						}
					}
					assertTrue(" Only " + sumArray(pointExist)
							+ " of the cells are marked correctly ",
							sumArray(pointExist) == 2);
					task.getMap()[i][j] = new EmptyCell();

				}
			}
		}
		assertTrue("The range of the chosen cells to be marked is incorrect",
				sumArray(pointExistAll) == 5);

	}

	@Test(timeout = 1000)
	public void testMerPersonDamageOnChampionInTreassureCell() throws Exception {
		testMethodExistsInClass(Task.class, "moveRight", true, Void.TYPE);

		ArrayList<Champion> e = new ArrayList<>();
		SlytherinWizard r = new SlytherinWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(r);
		e.add(s);
		SecondTask task = null;
		int x, y;
		Point location = null;

		do {
			task = new SecondTask(e);

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (task.getMap()[i][j] instanceof TreasureCell) {
						if (((TreasureCell) task.getMap()[i][j]).getOwner() == s) {
							location = new Point(i, j);
						}
					}

				}
			}

			x = location.x;
			y = location.y;

			if (x > 1 && x < 8 && y < 7 && y > 2)
				break;
		} while (true);

		task.getMap()[x][y - 1] = new EmptyCell();

		task.getMap()[x - 1][y] = new ObstacleCell(new Merperson(100, 100));
		task.getMap()[x - 1][y - 1] = new ObstacleCell(new Merperson(100, 100));
		task.getMap()[x + 1][y - 1] = new ObstacleCell(new Merperson(100, 100));
		task.getMap()[x][y - 2] = new ChampionCell(s);
		task.setCurrentChamp(s);
		Wizard currentChamp = ((Wizard) task.getCurrentChamp());
		currentChamp.setLocation(new Point(x, y - 2));

		int hpOld = currentChamp.getHp();
		task.moveRight();
		assertEquals(
				"The damage inflicted to the current champion is incorrect",
				200, hpOld - s.getHp());
		task.setCurrentChamp(s);
		hpOld = currentChamp.getHp();
		task.moveRight();

		assertEquals(
				"The damage inflicted to the current champion is incorrect", 0,
				hpOld - s.getHp());

		assertTrue("Champion should be added to winners list", task
				.getWinners().contains(s));

	}

	@Test(timeout = 1000)
	public void testMethodCastHealingSpell1() throws Exception {
		testMethodExistsInClass(Task.class, "castHealingSpell", true,
				Void.TYPE, HealingSpell.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task1 = new FirstTask(e) {
			@Override
			public void finalizeAction() {

			}

			@Override
			public void useSpell(Spell s) {

			}
		};
		task1.getChampions().clear();
		task1.getChampions().add(g);
		task1.getChampions().add(h);
		task1.getChampions().add(r);
		task1.getChampions().add(s);
		HealingSpell spell = new HealingSpell("HEL", 300, 4, 120);
		g.getSpells().add(spell);
		g.setHp(g.getHp() - 200);
		task1.setCurrentChamp(g);
		int initHp = g.getHp();
		int healingHp = spell.getHealingAmount();
		task1.castHealingSpell(spell);

		assertEquals(
				"In FirstTask after castHealingSpell, the current champion's health points should be healed by spell's healing amount",
				initHp + healingHp, g.getHp());

		SecondTask task2 = new SecondTask(e) {
			@Override
			public void finalizeAction() {

			}

			@Override
			public void useSpell(Spell s) {

			}
		};
		task2.getChampions().clear();
		task2.getChampions().add(g);
		task2.getChampions().add(h);
		task2.getChampions().add(s);
		task2.getChampions().add(r);
		g = new GryffindorWizard("gryff");
		spell = new HealingSpell("HEL", 300, 4, 120);
		g.getSpells().add(spell);
		g.setHp(g.getHp() - 200);
		task2.setCurrentChamp(g);
		initHp = g.getHp();
		healingHp = spell.getHealingAmount();
		task2.castHealingSpell(spell);

		assertEquals(
				"In SecondTask after castHealingSpell, the current champion's health points should be healed by spell's healing amount",
				initHp + healingHp, g.getHp());

		ThirdTask task3 = new ThirdTask(e) {
			@Override
			public void finalizeAction() {

			}

			@Override
			public void useSpell(Spell s) {

			}
		};
		task3.getChampions().add(g);
		task3.getChampions().add(h);
		task3.getChampions().add(r);
		task3.getChampions().add(s);
		g = new GryffindorWizard("gryff");
		g.getSpells().add(spell);
		spell = new HealingSpell("HEL", 300, 4, 120);
		g.setHp(g.getHp() - 200);
		task3.setCurrentChamp(g);
		initHp = g.getHp();
		healingHp = spell.getHealingAmount();
		task3.castHealingSpell(spell);

		assertEquals(
				"In ThirdTask after castHealingSpell, the current champion's health points should be healed by spell's healing amount",
				initHp + healingHp, g.getHp());

	}

	@Test(timeout = 1000)
	public void testMethodEncounterMerpersonIsCalledPerTurn2() throws Exception {
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		hasCalled = false;
		SecondTask task1 = new SecondTask(e) {
			@Override
			public void encounterMerPerson() {
				hasCalled = true;
			}
		};
		task1.getChampions().clear();
		task1.getChampions().add(g);
		task1.getChampions().add(h);
		task1.getChampions().add(r);
		task1.getChampions().add(s);
		Field map = Task.class.getDeclaredField("map");
		map.setAccessible(true);
		map.set(task1, new Cell[10][10]);
		Cell[][] taskMap = task1.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}
		int champPosX = (int) (6 * Math.random()) + 2;
		int champPosY = (int) (6 * Math.random()) + 2;
		task1.getMap()[champPosX][champPosY] = new ChampionCell(g);
		g.setLocation(new Point(champPosX, champPosY));
		g.getSpells().clear();
		HealingSpell spell = new HealingSpell("HEL", 20, 5, 20);
		g.getSpells().add(spell);
		task1.setCurrentChamp(g);
		task1.castHealingSpell(spell);
		assertTrue(
				"In SecondTask, the merperson should attack once at each turn",
				hasCalled);

		task1.getMap()[champPosX][champPosY] = new ChampionCell(h);
		h.setLocation(new Point(champPosX, champPosY));
		h.getSpells().clear();
		DamagingSpell spell2 = new DamagingSpell("HEL", 20, 5, 20);
		h.getSpells().add(spell2);
		task1.setCurrentChamp(h);
		task1.castDamagingSpell(spell2, Direction.BACKWARD);
		assertTrue(
				"In SecondTask, the merperson should attack once at each turn",
				hasCalled);

		task1.getMap()[champPosX][champPosY] = new ChampionCell(s);
		s.setLocation(new Point(champPosX, champPosY));
		s.getSpells().clear();
		spell = new HealingSpell("HEL", 20, 5, 20);
		s.getSpells().add(spell2);
		task1.setCurrentChamp(s);
		task1.castHealingSpell(spell);
		assertTrue(
				"In SecondTask, the merperson should attack once at each turn",
				hasCalled);

		task1.getMap()[champPosX][champPosY] = new ChampionCell(r);
		r.setLocation(new Point(champPosX, champPosY));
		r.getSpells().clear();
		spell2 = new DamagingSpell("HEL", 20, 5, 20);
		r.getSpells().add(spell2);
		task1.setCurrentChamp(r);
		task1.castDamagingSpell(spell2, Direction.BACKWARD);
		assertTrue(
				"In SecondTask, the merperson should attack once at each turn",
				hasCalled);
	}

	@Test(timeout = 1000)
	public void testMethodFireIsCalledPerTurn1() throws Exception {
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		hasCalled = false;
		FirstTask task1 = new FirstTask(e) {
			@Override
			public void fire() {
				hasCalled = true;
			}
		};
		task1.getChampions().clear();
		task1.getChampions().add(g);
		task1.getChampions().add(h);
		task1.getChampions().add(r);
		task1.getChampions().add(s);

		Field map = Task.class.getDeclaredField("map");
		map.setAccessible(true);
		map.set(task1, new Cell[10][10]);
		Cell[][] taskMap = task1.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}
		int champPosX = (int) (6 * Math.random()) + 2;
		int champPosY = (int) (6 * Math.random()) + 2;
		task1.getMap()[champPosX][champPosY] = new ChampionCell(g);
		g.setLocation(new Point(champPosX, champPosY));
		task1.setCurrentChamp(g);
		task1.moveRight();
		assertTrue("In FirstTask, the dragon should fire once at each turn",
				hasCalled);

		task1.getMap()[champPosX][champPosY] = new ChampionCell(h);
		h.setLocation(new Point(champPosX, champPosY));
		task1.setCurrentChamp(h);
		task1.moveLeft();
		assertTrue("In FirstTask, the dragon should fire once at each turn",
				hasCalled);

		task1.getMap()[champPosX][champPosY] = new ChampionCell(r);
		r.setLocation(new Point(champPosX, champPosY));
		task1.setCurrentChamp(r);
		task1.moveForward();
		assertTrue("In FirstTask, the dragon should fire once at each turn",
				hasCalled);

		task1.getMap()[champPosX][champPosY] = new ChampionCell(s);
		s.setLocation(new Point(champPosX, champPosY));
		task1.setCurrentChamp(s);
		task1.moveForward();
		assertTrue("In FirstTask, the dragon should fire once at each turn",
				hasCalled);
	}

	@Test(timeout = 1000)
	public void testMethodHealingSpellCallsFinalizeAction() throws Exception {

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);
		hasCalled = false;
		FirstTask task1 = new FirstTask(e) {
			@Override
			public void finalizeAction() {
				hasCalled = true;
			}
		};
		task1.getChampions().clear();
		task1.getChampions().add(s);
		task1.getChampions().add(h);
		task1.getChampions().add(r);
		task1.getChampions().add(g);
		HealingSpell spell = new HealingSpell("HEL", 300, 4, 120);
		task1.setCurrentChamp(g);
		task1.castHealingSpell(spell);
		assertTrue(
				"In FirstTask, castHealingSpell method should call finalizeAction method",
				hasCalled);

		hasCalled = false;
		SecondTask task2 = new SecondTask(e) {
			@Override
			public void finalizeAction() {
				hasCalled = true;
			}
		};
		task2.getChampions().clear();
		task2.getChampions().add(g);
		task2.getChampions().add(h);
		task2.getChampions().add(s);
		task2.getChampions().add(r);
		spell = new HealingSpell("HEL", 300, 4, 120);
		task2.setCurrentChamp(g);
		task2.castHealingSpell(spell);
		assertTrue(
				"In SecondTask, castHealingSpell method should call finalizeAction method",
				hasCalled);

		hasCalled = false;
		ThirdTask task3 = new ThirdTask(e) {
			@Override
			public void finalizeAction() {
				hasCalled = true;
			}
		};
		task3.getChampions().add(g);
		task3.getChampions().add(h);
		task3.getChampions().add(r);
		task3.getChampions().add(s);
		spell = new HealingSpell("HEL", 300, 4, 120);
		task3.setCurrentChamp(g);
		task3.castHealingSpell(spell);
		assertTrue(
				"In ThirdTask, castHealingSpell method should call finalizeAction method",
				hasCalled);

	}

	@Test(timeout = 1000)
	public void testMethodHealingSpellCallsUseSpell() throws Exception {

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);
		hasCalled = false;
		FirstTask task1 = new FirstTask(e) {
			@Override
			public void useSpell(Spell s) {
				hasCalled = true;
			}
		};
		task1.getChampions().clear();
		task1.getChampions().add(s);
		task1.getChampions().add(h);
		task1.getChampions().add(r);
		task1.getChampions().add(g);
		HealingSpell spell = new HealingSpell("HEL", 300, 4, 120);
		g.getSpells().add(spell);
		task1.setCurrentChamp(g);
		task1.castHealingSpell(spell);
		assertTrue(
				"In FirstTask, castHealingSpell method should call useSpell method",
				hasCalled);

		hasCalled = false;
		SecondTask task2 = new SecondTask(e) {
			@Override
			public void useSpell(Spell s) {
				hasCalled = true;
			}
		};
		task2.getChampions().clear();
		task2.getChampions().add(g);
		task2.getChampions().add(h);
		task2.getChampions().add(s);
		task2.getChampions().add(r);
		spell = new HealingSpell("HEL", 300, 4, 120);
		g.getSpells().add(spell);
		task2.setCurrentChamp(g);
		task2.castHealingSpell(spell);
		assertTrue(
				"In SecondTask, castHealingSpell method should call useSpell method",
				hasCalled);

		hasCalled = false;
		ThirdTask task3 = new ThirdTask(e) {
			@Override
			public void useSpell(Spell s) {
				hasCalled = true;
			}
		};
		task3.getChampions().add(g);
		task3.getChampions().add(h);
		task3.getChampions().add(r);
		task3.getChampions().add(s);
		spell = new HealingSpell("HEL", 300, 4, 120);
		g.getSpells().add(spell);
		task3.setCurrentChamp(g);
		task3.castHealingSpell(spell);
		assertTrue(
				"In ThirdTask, castHealingSpell method should call useSpell method",
				hasCalled);

	}

	@Test(timeout = 1000)
	public void testMethodsInTaskListenerInterface() throws Exception {
		testMethodExistsInClass(TaskListener.class, "onFinishingFirstTask",
				true, Void.TYPE, ArrayList.class);
		testMethodExistsInClass(TaskListener.class, "onFinishingSecondTask",
				true, Void.TYPE, ArrayList.class);
		testMethodExistsInClass(TaskListener.class, "onFinishingThirdTask",
				true, Void.TYPE, Champion.class);
	}

	@Test(timeout = 1000)
	public void testMethodsInWizardListenerInterface() throws Exception {
		testMethodExistsInClass(WizardListener.class, "onGryffindorTrait",
				true, Void.TYPE);
		testMethodExistsInClass(WizardListener.class, "onSlytherinTrait", true,
				Void.TYPE, Direction.class);
		testMethodExistsInClass(WizardListener.class, "onHufflepuffTrait",
				true, Void.TYPE);
		testMethodExistsInClass(WizardListener.class, "onRavenclawTrait", true,
				Object.class);
	}

	@Test(timeout = 1000)
	public void testMethodUsePotions1() throws Exception {

		testMethodExistsInClass(Task.class, "usePotion", true, Void.TYPE,
				Potion.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task1 = new FirstTask(e);
		task1.getChampions().clear();
		task1.getChampions().add(g);
		task1.getChampions().add(h);
		task1.getChampions().add(r);
		task1.getChampions().add(s);
		g = new GryffindorWizard("gryff");
		task1.setCurrentChamp(g);
		Potion p = task1.getPotions().get(3);
		g.getInventory().add(p);
		g.setIp(100);
		int newIP = g.getIp() + p.getAmount();
		task1.usePotion(p);

		assertEquals(
				"In FirstTask, whenever a champion uses a potion, his/her ip should be increased by the potion amount",
				newIP, g.getIp());

		SecondTask task2 = new SecondTask(e);
		task2.getChampions().clear();
		task2.getChampions().add(g);
		task2.getChampions().add(h);
		task2.getChampions().add(s);
		task2.getChampions().add(r);
		g = new GryffindorWizard("gryff");
		task2.setCurrentChamp(g);
		p = task2.getPotions().get(3);
		g.getInventory().add(p);
		g.setIp(100);
		newIP = g.getIp() + p.getAmount();
		task2.usePotion(p);

		assertEquals(
				"In SecondTask, whenever a champion uses a potion, his/her ip should be increased by the potion amount",
				newIP, g.getIp());

		ThirdTask task3 = new ThirdTask(e);
		task3.getChampions().add(g);
		task3.getChampions().add(h);
		task3.getChampions().add(r);
		task3.getChampions().add(s);
		g = new GryffindorWizard("gryff");
		task3.setCurrentChamp(g);
		p = task3.getPotions().get(3);
		g.getInventory().add(p);
		g.setIp(100);
		newIP = g.getIp() + p.getAmount();
		task3.usePotion(p);
		assertEquals(
				"In ThirdTask, whenever a champion uses a potion, his/her ip should be increased by the potion amount",
				newIP, g.getIp());

	}

	@Test(timeout = 1000)
	public void testMethodUsePotions4() throws Exception {

		testMethodExistsInClass(Task.class, "usePotion", true, Void.TYPE,
				Potion.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task1 = new FirstTask(e);
		task1.getChampions().clear();
		task1.getChampions().add(g);
		task1.getChampions().add(h);
		task1.getChampions().add(r);
		task1.getChampions().add(s);
		g = new GryffindorWizard("gryff");

		g.getInventory().clear();
		g.getInventory().add(task1.getPotions().get(0));
		g.getInventory().add(task1.getPotions().get(1));
		g.getInventory().add(task1.getPotions().get(2));
		g.getInventory().add(task1.getPotions().get(3));
		task1.setCurrentChamp(g);
		Potion p = task1.getPotions().get(1);
		task1.usePotion(p);
		assertFalse(
				"In FirstTask, a champion remove potion from his/her inventory after using it",
				g.getInventory().contains(p));

		SecondTask task2 = new SecondTask(e);
		task2.getChampions().clear();
		task2.getChampions().add(g);
		task2.getChampions().add(h);
		task2.getChampions().add(s);
		task2.getChampions().add(r);
		g = new GryffindorWizard("gryff");
		g.getInventory().clear();
		g.getInventory().add(task1.getPotions().get(0));
		g.getInventory().add(task1.getPotions().get(1));
		g.getInventory().add(task1.getPotions().get(2));
		g.getInventory().add(task1.getPotions().get(3));
		task2.setCurrentChamp(g);
		p = task2.getPotions().get(1);
		task2.usePotion(p);
		assertFalse(
				"In SecondTask, a champion remove potion from his/her inventory after using it",
				g.getInventory().contains(p));

		ThirdTask task3 = new ThirdTask(e);
		task3.getChampions().add(g);
		task3.getChampions().add(h);
		task3.getChampions().add(r);
		task3.getChampions().add(s);
		g = new GryffindorWizard("gryff");
		g.getInventory().clear();
		g.getInventory().add(task1.getPotions().get(0));
		g.getInventory().add(task1.getPotions().get(1));
		g.getInventory().add(task1.getPotions().get(2));
		g.getInventory().add(task1.getPotions().get(3));
		task3.setCurrentChamp(g);
		p = task3.getPotions().get(1);
		task2.usePotion(p);
		assertFalse(
				"In ThirdTask, a champion remove potion from his/her inventory after using it",
				g.getInventory().contains(p));

	}

	@Test(timeout = 1000)
	public void testMethodUseSpell1() throws Exception {
		for (int yh = 0; yh < 500; yh++) {
			testMethodExistsInClass(Task.class, "useSpell", true, Void.TYPE,
					Spell.class);

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");

			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			turnsPassed = 0;
			FirstTask task1 = new FirstTask(e) {

				public void endTurn() {

					turnsPassed = 1;
					try {
						super.endTurn();
					} catch (Exception e) {
						fail("An exception occured while ending turn.\n"
								+ e.getMessage());
					}

				}

			};
			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);

			Spell spell = new DamagingSpell("DMG", 20, 5, 20);
			g.getSpells().add(spell);
			int initSpellCooldown = spell.getDefaultCooldown();
			task1.setCurrentChamp(g);
			task1.useSpell(spell);

			if (turnsPassed == 1) {

				Cell[][] taskMap = task1.getMap();
				for (int i = 0; i < taskMap.length; i++) {
					for (int j = 0; j < taskMap[i].length; j++) {
						taskMap[i][j] = new EmptyCell();
					}
				}

				g.setLocation(new Point(1, 1));
				h.setLocation(new Point(2, 2));
				r.setLocation(new Point(3, 3));
				s.setLocation(new Point(5, 5));
				taskMap[1][1] = new ChampionCell(g);
				taskMap[2][2] = new ChampionCell(h);
				taskMap[3][3] = new ChampionCell(r);
				taskMap[5][5] = new ChampionCell(s);

				task1.setCurrentChamp(h);
				task1.moveForward();
				task1.setCurrentChamp(r);
				task1.moveForward();
				task1.setCurrentChamp(s);
				task1.moveForward();

			}

			assertEquals(
					"In the FirstTask, whenever a spell is used, the spell cooldown should be set",
					initSpellCooldown - turnsPassed, spell.getCoolDown());

			turnsPassed = 0;

			e = new ArrayList<>();
			g = new GryffindorWizard("gryff");
			h = new HufflepuffWizard("huff");
			r = new RavenclawWizard("raven");
			s = new SlytherinWizard("slyth");

			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			SecondTask task2 = new SecondTask(e) {

				public void endTurn() {

					turnsPassed = 1;
					try {
						super.endTurn();
					} catch (Exception e) {
						fail("An exception occured while ending turn.\n"
								+ e.getMessage());
					}

				}

			};

			for (int i = 0; i < task2.getMap().length; i++) {
				for (int j = 0; j < task2.getMap()[i].length; j++) {
					if (task2.getMap()[i][j] instanceof ObstacleCell)
						task2.getMap()[i][j] = new EmptyCell();
				}
			}

			task2.getChampions().clear();
			task2.getChampions().add(g);
			task2.getChampions().add(h);
			task2.getChampions().add(r);
			task2.getChampions().add(s);

			g.setLocation(new Point(0, 0));
			spell = new DamagingSpell("DMG", 20, 5, 20);
			initSpellCooldown = spell.getDefaultCooldown();

			g.getSpells().add(spell);

			task2.setCurrentChamp(g);

			task2.useSpell(spell);

			if (turnsPassed == 1) {

				Cell[][] taskMap = task2.getMap();
				for (int i = 0; i < taskMap.length; i++) {
					for (int j = 0; j < taskMap[i].length; j++) {
						taskMap[i][j] = new EmptyCell();
					}
				}

				g.setLocation(new Point(1, 1));
				h.setLocation(new Point(3, 3));
				r.setLocation(new Point(7, 7));
				s.setLocation(new Point(5, 5));
				taskMap[1][1] = new ChampionCell(g);
				taskMap[3][3] = new ChampionCell(h);
				taskMap[7][7] = new ChampionCell(r);
				taskMap[5][5] = new ChampionCell(s);

				task2.setCurrentChamp(h);
				task2.moveForward();

				task2.setCurrentChamp(r);
				task2.moveForward();

				task2.setCurrentChamp(s);
				task2.moveForward();

			}

			assertEquals(
					"In the SecondTask, whenever a spell is used, the spell cooldown should be set",
					initSpellCooldown - turnsPassed, spell.getCoolDown());

			turnsPassed = 0;

			e = new ArrayList<>();
			g = new GryffindorWizard("gryff");
			h = new HufflepuffWizard("huff");
			r = new RavenclawWizard("raven");
			s = new SlytherinWizard("slyth");

			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			ThirdTask task3 = new ThirdTask(e) {

				public void endTurn() {

					turnsPassed = 1;
					try {
						super.endTurn();
					} catch (Exception e) {
						fail("An exception occured while ending turn.\n"
								+ e.getMessage());
					}

				}

			};

			for (int i = 0; i < task2.getMap().length; i++) {
				for (int j = 0; j < task2.getMap()[i].length; j++) {
					if (task2.getMap()[i][j] instanceof ObstacleCell)
						task2.getMap()[i][j] = new EmptyCell();
				}
			}

			g = new GryffindorWizard("gryff");

			task3.getChampions().clear();
			task3.getChampions().add(g);
			task3.getChampions().add(h);
			task3.getChampions().add(r);
			task3.getChampions().add(s);

			g.setLocation(new Point(0, 0));
			spell = new DamagingSpell("DMG", 20, 5, 20);
			initSpellCooldown = spell.getDefaultCooldown();

			g.getSpells().add(spell);

			task3.setCurrentChamp(g);
			task3.useSpell(spell);

			if (turnsPassed == 1) {

				Cell[][] taskMap = task3.getMap();
				for (int i = 0; i < taskMap.length; i++) {
					for (int j = 0; j < taskMap[i].length; j++) {
						taskMap[i][j] = new EmptyCell();
					}
				}

				g.setLocation(new Point(1, 1));
				h.setLocation(new Point(3, 3));
				r.setLocation(new Point(7, 7));
				s.setLocation(new Point(5, 5));
				taskMap[1][1] = new ChampionCell(g);
				taskMap[3][3] = new ChampionCell(h);
				taskMap[7][7] = new ChampionCell(r);
				taskMap[5][5] = new ChampionCell(s);

				task3.setCurrentChamp(h);
				task3.moveForward();
				task3.setCurrentChamp(r);
				task3.moveForward();
				task3.setCurrentChamp(s);
				task3.moveForward();

			}

			assertEquals(
					"In the ThirdTask, whenever a spell is used, the spell cooldown should be set",
					initSpellCooldown - turnsPassed, spell.getCoolDown());

		}
	}

	@Test(timeout = 1000)
	public void testMoveForwardToCollectibeCell() throws Exception {

		testMethodExistsInClass(Task.class, "moveForward", true, Void.TYPE);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(h);
		task.getMap()[4][3] = new ChampionCell(h);
		((Wizard) task.getCurrentChamp()).setLocation(new Point(4, 3));
		Potion p = new Potion("p", 12);
		task.getMap()[3][3] = new CollectibleCell(p);

		task.moveForward();
		task.setCurrentChamp(h);

		assertTrue(
				"When a champion moves to a collectibe cell, the collectible should be added to his/her inventory",
				((Wizard) task.getCurrentChamp()).getInventory().contains(p));

		assertEquals(
				"When a champion moves from a cell, it should be converted to an EmptyCell ",
				EmptyCell.class, task.getMap()[4][3].getClass());

		assertEquals(
				"When a champion moves forward to a cell, it should be converted to a ChampionCell ",
				ChampionCell.class, task.getMap()[3][3].getClass());
	}

	@Test(timeout = 1000)
	public void testMoveForwardToEggCell() throws Exception {

		testMethodExistsInClass(Task.class, "moveForward", true, Void.TYPE);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(h);
		task.getMap()[5][4] = new ChampionCell(h);
		((Wizard) task.getCurrentChamp()).setLocation(new Point(5, 4));

		task.moveForward();
		task.setCurrentChamp(h);

		assertTrue(
				"When a champion moves to a cell (4,4), he/she is considered a winner. Thus the champion should be added to the winners list ",
				task.getWinners().contains(h));

		assertFalse(
				"When a champion moves to a cell (4,4), he/she is considered a winner. Thus the champion should be removed from the champions list ",
				task.getChampions().contains(h));

		assertEquals(
				"When a champion moves forward from a cell, it should be converted to an EmptyCell ",
				EmptyCell.class, task.getMap()[5][4].getClass());

	}

	@Test(timeout = 1000)
	public void testMoveForwardToEggCellSecondTask() throws Exception {

		testMethodExistsInClass(Task.class, "moveForward", true, Void.TYPE);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		SecondTask task = new SecondTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(h);
		task.getMap()[5][4] = new ChampionCell(h);
		((Wizard) task.getCurrentChamp()).setLocation(new Point(5, 4));

		task.moveForward();
		task.setCurrentChamp(h);

		assertFalse(
				"When a champion moves to a cell (4,4) in SecondTask, he/she is NOT considered a winner. Thus the champion should NOT be added to the winners list ",
				task.getWinners().contains(h));

		assertTrue(
				"When a champion moves to a cell (4,4) in SecondTask, he/she is NOT considered a winner. Thus the champion should NOT be removed from the champions list ",
				task.getChampions().contains(h));

		assertEquals(
				"When a champion moves forward from a cell, it should be converted to an EmptyCell ",
				EmptyCell.class, task.getMap()[5][4].getClass());

	}

	@Test(timeout = 1000)
	public void testMoveForwardToEggCellThirdTask() throws Exception {

		testMethodExistsInClass(Task.class, "moveForward", true, Void.TYPE);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		ThirdTask task = new ThirdTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(h);
		task.getMap()[5][4] = new ChampionCell(h);
		((Wizard) task.getCurrentChamp()).setLocation(new Point(5, 4));

		task.moveForward();
		task.setCurrentChamp(h);

		assertTrue(
				"When a champion moves to a cell (4,4) in ThirdTask, he/she is NOT considered a winner. Thus the champion should NOT be removed from the champions list ",
				task.getChampions().contains(h));

		assertEquals(
				"When a champion moves forward from a cell, it should be converted to an EmptyCell ",
				EmptyCell.class, task.getMap()[5][4].getClass());

	}

	@Test(timeout = 1000)
	public void testMoveForwardToEmptyCell() throws Exception {

		testMethodExistsInClass(Task.class, "moveForward", true, Void.TYPE);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(h);
		task.getMap()[4][3] = new ChampionCell(h);
		((Wizard) task.getCurrentChamp()).setLocation(new Point(4, 3));

		task.moveForward();
		task.setCurrentChamp(h);

		Point newLocation = ((Wizard) task.getCurrentChamp()).getLocation();
		assertEquals(
				"MoveForward method in class Task is not moving the champion correctly",
				new Point(3, 3), newLocation);

		assertEquals(
				"When a champion moves to a cell, it should be converted to an ChampionCell ",
				ChampionCell.class, task.getMap()[3][3].getClass());

		assertEquals(
				"When a champion moves from a cell, it should be converted to an EmptyCell ",
				EmptyCell.class, task.getMap()[4][3].getClass());
	}

	@Test(timeout = 1000)
	public void testMoveForwardToTreasureCell() throws Exception {

		testMethodExistsInClass(Task.class, "moveForward", true, Void.TYPE);
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		SecondTask task = new SecondTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(h);
		task.getMap()[2][3] = new ChampionCell(h);
		((Wizard) task.getCurrentChamp()).setLocation(new Point(2, 3));

		task.getMap()[1][3] = new TreasureCell(task.getCurrentChamp());

		task.moveForward();
		task.setCurrentChamp(h);

		assertTrue(
				"When a champion moves to his/her treasure cell in the Second Task, he/she is considered a winner. Thus the champion should be added to the winners list ",
				task.getWinners().contains(h));

		assertFalse(
				"When a champion moves to his/her treasure cell in the Second Task, he/she is considered a winner. Thus the champion should be removed from the champions list ",
				task.getChampions().contains(h));

		assertEquals(
				"When a champion moves forward from a cell, it should be converted to an EmptyCell ",
				EmptyCell.class, task.getMap()[2][3].getClass());
	}

	@Test(timeout = 1000)
	public void testOnGryffindorTrait1() throws Exception {
		testMethodExistsInClassOrSuperClass(Task.class, "onGryffindorTrait",
				true, Void.TYPE);
		testMethodExistsInClass(FirstTask.class, "onGryffindorTrait", false,
				Void.TYPE);
		testMethodExistsInClass(SecondTask.class, "onGryffindorTrait", false,
				Void.TYPE);
		testMethodExistsInClass(ThirdTask.class, "onGryffindorTrait", false,
				Void.TYPE);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task1 = new FirstTask(e);
		task1.getChampions().clear();
		task1.getChampions().add(g);
		task1.getChampions().add(h);
		task1.getChampions().add(r);
		task1.getChampions().add(s);
		task1.setCurrentChamp(g);
		task1.onGryffindorTrait();
		assertEquals(
				"In FirstTask, onGryffindorTrait allows the champion to do two actions",
				2, task1.getAllowedMoves());

		SecondTask task2 = new SecondTask(e);
		task2.getChampions().clear();
		task2.getChampions().add(g);
		task2.getChampions().add(h);
		task2.getChampions().add(s);
		task2.getChampions().add(r);
		task2.setCurrentChamp(g);
		g.setTraitCooldown(0);
		task2.onGryffindorTrait();
		assertEquals(
				"In SecondTask, onGryffindorTrait allows the champion to do two actions",
				2, task2.getAllowedMoves());

		ThirdTask task3 = new ThirdTask(e);
		task3.getChampions().add(g);
		task3.getChampions().add(h);
		task3.getChampions().add(r);
		task3.getChampions().add(s);
		task3.setCurrentChamp(g);
		g.setTraitCooldown(0);
		task3.onGryffindorTrait();
		assertEquals(
				"In ThirdTask, onGryffindorTrait allows the champion to do two actions",
				2, task3.getAllowedMoves());

	}

	@Test(timeout = 1000)
	public void testOnGryffindorTrait2() throws Exception {
		testMethodExistsInClassOrSuperClass(Task.class, "onGryffindorTrait",
				true, Void.TYPE);
		testMethodExistsInClass(FirstTask.class, "onGryffindorTrait", false,
				Void.TYPE);
		testMethodExistsInClass(SecondTask.class, "onGryffindorTrait", false,
				Void.TYPE);
		testMethodExistsInClass(ThirdTask.class, "onGryffindorTrait", false,
				Void.TYPE);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task1 = new FirstTask(e);
		task1.getChampions().clear();
		task1.getChampions().add(g);
		task1.getChampions().add(h);
		task1.getChampions().add(r);
		task1.getChampions().add(s);
		task1.setCurrentChamp(g);
		task1.onGryffindorTrait();
		assertEquals(
				"In FirstTask, onGryffindorTrait method should set the champion's traitCooldown",
				4, g.getTraitCooldown());

		SecondTask task2 = new SecondTask(e);
		task2.getChampions().clear();
		task2.getChampions().add(g);
		task2.getChampions().add(h);
		task2.getChampions().add(s);
		task2.getChampions().add(r);
		g = new GryffindorWizard("gryff");
		task2.setCurrentChamp(g);
		task2.onGryffindorTrait();
		assertEquals(
				"In SecondTask, onGryffindorTrait method should set the champion's traitCooldown",
				4, g.getTraitCooldown());

		ThirdTask task3 = new ThirdTask(e);
		task3.getChampions().add(g);
		task3.getChampions().add(h);
		task3.getChampions().add(r);
		task3.getChampions().add(s);
		g = new GryffindorWizard("gryff");
		task3.setCurrentChamp(g);
		task3.onGryffindorTrait();
		assertEquals(
				"In ThirdTask, onGryffindorTrait method should set the champion's traitCooldown",
				4, g.getTraitCooldown());

	}

	@Test(timeout = 1000)
	public void testOnGryffindorTrait3() throws Exception {
		testMethodExistsInClassOrSuperClass(Task.class, "onGryffindorTrait",
				true, Void.TYPE);
		testMethodExistsInClass(FirstTask.class, "onGryffindorTrait", false,
				Void.TYPE);
		testMethodExistsInClass(SecondTask.class, "onGryffindorTrait", false,
				Void.TYPE);
		testMethodExistsInClass(ThirdTask.class, "onGryffindorTrait", false,
				Void.TYPE);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task1 = new FirstTask(e);
		task1.getChampions().clear();
		task1.getChampions().add(g);
		task1.getChampions().add(h);
		task1.getChampions().add(r);
		task1.getChampions().add(s);
		task1.setCurrentChamp(g);
		task1.onGryffindorTrait();
		assertTrue(
				"In FirstTask, onGryffindorTrait method should set traitActivated",
				task1.isTraitActivated());

		SecondTask task2 = new SecondTask(e);
		task2.getChampions().clear();
		task2.getChampions().add(g);
		task2.getChampions().add(h);
		task2.getChampions().add(s);
		task2.getChampions().add(r);
		g = new GryffindorWizard("gryff");
		task2.setCurrentChamp(g);
		task2.onGryffindorTrait();
		assertTrue(
				"In SecondTask, onGryffindorTrait method should set traitActivated",
				task2.isTraitActivated());

		ThirdTask task3 = new ThirdTask(e);
		task3.getChampions().add(g);
		task3.getChampions().add(h);
		task3.getChampions().add(r);
		task3.getChampions().add(s);
		g = new GryffindorWizard("gryff");
		task3.setCurrentChamp(g);
		task3.onGryffindorTrait();
		assertTrue(
				"In ThirdTask, onGryffindorTrait method should set traitActivated",
				task3.isTraitActivated());

	}

	@Test(timeout = 1000)
	public void testOnGryffindorTraitSubClass() throws Exception {
		testMethodExistsInClass(FirstTask.class, "onGryffindorTrait", false,
				Void.TYPE);
		testMethodExistsInClass(SecondTask.class, "onGryffindorTrait", false,
				Void.TYPE);
		testMethodExistsInClass(ThirdTask.class, "onGryffindorTrait", false,
				Void.TYPE);
	}

	@Test(timeout = 1000)
	public void testOnGryffindorTraitTaskScenario1() throws Exception {

		for (int dh = 0; dh < 500; dh++) {

			testMethodExistsInClass(Task.class, "onGryffindorTrait", true,
					Void.TYPE);

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			FirstTask task1 = new FirstTask(e);
			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task1, new Cell[10][10]);
			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}
			int champPosX = 6;
			int champPosY = 6;

			task1.getMap()[champPosX][champPosY] = new ChampionCell(g);
			g.setLocation(new Point(champPosX, champPosY));
			task1.setCurrentChamp(g);

			g.useTrait();
			int newChampPosX = champPosX;
			int newChampPosY = champPosY;
			for (int i = 0; i < 2; i++) {

				assertEquals(
						"In all Tasks, when GryffindorTrait is activated the current champion should be updated only after finishing his/her two actions",
						g, task1.getCurrentChamp());

				int randomMove = (int) (4 * Math.random());
				switch (randomMove) {
				case 0: {
					task1.moveForward();
					newChampPosX -= 1;
				}
					break;
				case 1: {
					task1.moveBackward();
					newChampPosX += 1;
				}
					break;

				case 2: {
					task1.moveLeft();
					newChampPosY -= 1;
				}
					break;
				case 3: {
					task1.moveRight();
					newChampPosY += 1;
				}
					break;

				}

			}

			if (task1.getMap()[newChampPosX][newChampPosY] instanceof ChampionCell) {
				assertEquals(
						"In all Tasks, when GryffindorTrait is activated the champion location in the map should be updated correctly after using his/her two actions",
						g,
						((ChampionCell) task1.getMap()[newChampPosX][newChampPosY])
								.getChamp());
			} else {

				fail("In all Tasks, if GryffindorTrait is activated it should allow the champion to do two actions in his/her turn");
			}

			assertEquals(
					"In all Tasks, when GryffindorTrait is activated the champion location should be updated correctly after using his/her two actions",
					new Point(newChampPosX, newChampPosY), g.getLocation());
		}
	}

	@Test(timeout = 1000)
	public void testOnGryffindorTraitTaskScenario4() throws Exception {
		testMethodExistsInClass(Task.class, "onGryffindorTrait", true,
				Void.TYPE);
		for (int hj = 0; hj < 500; hj++) {

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			ThirdTask task3 = new ThirdTask(e);
			task3.getChampions().clear();
			task3.getChampions().add(g);
			task3.getChampions().add(h);
			task3.getChampions().add(r);
			task3.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task3, new Cell[10][10]);
			Cell[][] taskMap = task3.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			g.setLocation(new Point(1, 1));
			h.setLocation(new Point(2, 2));
			r.setLocation(new Point(3, 3));
			s.setLocation(new Point(5, 5));
			taskMap[1][1] = new ChampionCell(g);
			taskMap[2][2] = new ChampionCell(h);
			taskMap[3][3] = new ChampionCell(r);
			taskMap[5][5] = new ChampionCell(s);

			int champPosX = (int) (6 * Math.random()) + 2;
			int champPosY = (int) (6 * Math.random()) + 2;
			task3.getMap()[champPosX][champPosY] = new ChampionCell(g);
			g.setLocation(new Point(champPosX, champPosY));
			task3.setCurrentChamp(g);
			g.useTrait();
			g.getSpells().clear();
			g.getSpells().add(new DamagingSpell("DMG", 20, 5, 20));
			g.getSpells().add(new DamagingSpell("DMG", 40, 5, 20));
			Spell spell1 = g.getSpells().get(0);
			Spell spell2 = g.getSpells().get(1);
			int initSpell1Cooldown = spell1.getDefaultCooldown();
			int initSpell2Cooldown = spell2.getDefaultCooldown();
			int newChampionIp = g.getIp() - 60;
			task3.getMap()[champPosX][champPosY - 1] = new ObstacleCell(
					new PhysicalObstacle(10000));
			task3.castDamagingSpell((DamagingSpell) spell1, Direction.LEFT);

			assertEquals(
					"In all Tasks, when GryffindorTrait is activated the current champion should be updated only after finishing his/her two actions",
					g, task3.getCurrentChamp());

			task3.castDamagingSpell((DamagingSpell) spell2, Direction.LEFT);

			task3.setCurrentChamp(h);
			task3.moveForward();
			task3.setCurrentChamp(r);
			task3.moveForward();
			task3.setCurrentChamp(s);
			task3.moveForward();

			assertEquals(
					"In all Tasks, if GryffindorTrait is activated it should allow the champion to cast two spells in his/her turn and update the IP accordingly ",
					newChampionIp, g.getIp());

			assertEquals(
					"In all Tasks, if GryffindorTrait is activated it should allow the champion to cast two spells in his/her turn and update the spell CoolDown accordingly ",
					(initSpell1Cooldown <= 0 ? 0 : initSpell1Cooldown - 1),
					spell1.getCoolDown());

			assertEquals(
					"In all Tasks, if GryffindorTrait is activated it should allow the champion to cast two spells in his/her turn and update the spell CoolDown accordingly ",
					(initSpell2Cooldown <= 0 ? 0 : initSpell2Cooldown - 1),
					spell2.getCoolDown());
		}
	}

	@Test(timeout = 1000)
	public void testOnGryffindorTraitTaskScenario6() throws Exception {

		for (int dh = 0; dh < 500; dh++) {

			testMethodExistsInClass(Task.class, "onGryffindorTrait", true,
					Void.TYPE);

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			SecondTask task2 = new SecondTask(e);
			task2.getChampions().clear();
			task2.getChampions().add(g);
			task2.getChampions().add(h);
			task2.getChampions().add(r);
			task2.getChampions().add(s);

			boolean edge = false;
			Cell[][] taskMap;
			do {
				task2 = new SecondTask(e);
				task2.getChampions().clear();
				task2.getChampions().add(g);
				task2.getChampions().add(h);
				task2.getChampions().add(r);
				task2.getChampions().add(s);

				taskMap = task2.getMap();

				edge = false;
				for (int i = 0; !edge && i < 10; i += 9)
					for (int j = 0; !edge && j < 10; j++)
						if (taskMap[i][j] instanceof TreasureCell
								&& ((TreasureCell) taskMap[i][j]).getOwner() == g)
							edge = true;
						else if (taskMap[j][i] instanceof TreasureCell
								&& ((TreasureCell) taskMap[j][i]).getOwner() == g)
							edge = true;
			} while (edge);

			Point treasureLocation = new Point();

			for (int i = 1; i < 9; i++) {
				for (int j = 1; j < 9; j++) {
					if (taskMap[i][j] instanceof TreasureCell
							&& ((TreasureCell) taskMap[i][j]).getOwner() == g)
						treasureLocation = new Point(i, j);
					else
						taskMap[i][j] = new EmptyCell();
				}
			}

			int champPosX = 6;
			int champPosY = 6;

			task2.setCurrentChamp(g);

			g.useTrait();

			int randomMove = (int) (4 * Math.random());
			switch (randomMove) {
			case 0: {

				champPosX = treasureLocation.x + 1;
				champPosY = treasureLocation.y;
				task2.getMap()[champPosX][champPosY] = new ChampionCell(g);
				g.setLocation(new Point(champPosX, champPosY));

				task2.moveForward();

			}
				break;
			case 1: {

				champPosX = treasureLocation.x - 1;
				champPosY = treasureLocation.y;
				task2.getMap()[champPosX][champPosY] = new ChampionCell(g);
				g.setLocation(new Point(champPosX, champPosY));

				task2.moveBackward();

			}
				break;

			case 2: {

				champPosX = treasureLocation.x;
				champPosY = treasureLocation.y + 1;
				task2.getMap()[champPosX][champPosY] = new ChampionCell(g);
				g.setLocation(new Point(champPosX, champPosY));

				task2.moveLeft();

			}
				break;
			case 3: {

				champPosX = treasureLocation.x;
				champPosY = treasureLocation.y - 1;
				task2.getMap()[champPosX][champPosY] = new ChampionCell(g);
				g.setLocation(new Point(champPosX, champPosY));

				task2.moveRight();

			}
				break;

			}

			assertTrue(
					"In SecondTask, when GryffindorTrait is activated and the champion moves to his/her treasure cell, he/she is considered a winner and thus should be added to the winners list ",
					task2.getWinners().contains(g));

			assertFalse(
					"In SecondTask, when GryffindorTrait is activated and the champion moves to his/her treasure cell, he/she is considered a winner and thus should be removed from the champions list ",
					task2.getChampions().contains(g));

			assertNotEquals(
					"In SecondTask, when GryffindorTrait is activated and the champion moves to his/her treasure cell, he/she should no longer be the current champ",
					g, task2.getCurrentChamp());

			assertEquals(
					"In SecondTask, when GryffindorTrait is activated and the champion moves to his/her treasure cell and wins, his/her treasure cell should become Empty",
					EmptyCell.class,
					task2.getMap()[treasureLocation.x][treasureLocation.y]
							.getClass());

			assertEquals(
					"In all Tasks, when GryffindorTrait is activated and the champion moves from a cell, it should be converted to an EmptyCell ",
					EmptyCell.class,
					task2.getMap()[champPosX][champPosY].getClass());

		}
	}

	@Test(timeout = 1000)
	public void testOnGryffindorTraitTaskScenario7() throws Exception {

		for (int dh = 0; dh < 500; dh++) {

			testMethodExistsInClass(Task.class, "onGryffindorTrait", true,
					Void.TYPE);

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			ThirdTask task3 = new ThirdTask(e);
			task3.getChampions().clear();
			task3.getChampions().add(g);
			task3.getChampions().add(h);
			task3.getChampions().add(r);
			task3.getChampions().add(s);

			Tournament t = new Tournament() {
				public void onFinishingThirdTask(Champion winner) {
					hasCalled = true;
					w = winner;
				}
			};

			boolean edge = false;
			Cell[][] taskMap;
			do {
				task3 = new ThirdTask(e);
				task3.getChampions().clear();
				task3.getChampions().add(g);
				task3.getChampions().add(h);
				task3.getChampions().add(r);
				task3.getChampions().add(s);

				taskMap = task3.getMap();

				edge = false;
				for (int i = 0; !edge && i < 10; i += 9)
					for (int j = 0; !edge && j < 10; j++)
						if (taskMap[i][j] instanceof CupCell)
							edge = true;
						else if (taskMap[j][i] instanceof CupCell)
							edge = true;
			} while (edge);

			task3.setListener(t);

			Point cupLocation = new Point();

			for (int i = 1; i < 9; i++) {
				for (int j = 1; j < 9; j++) {
					if (taskMap[i][j] instanceof CupCell)
						cupLocation = new Point(i, j);
					else
						taskMap[i][j] = new EmptyCell();
				}
			}

			int champPosX = 6;
			int champPosY = 6;

			task3.getMap()[champPosX][champPosY] = new ChampionCell(g);
			g.setLocation(new Point(champPosX, champPosY));
			task3.setCurrentChamp(g);

			g.useTrait();

			int randomMove = (int) (4 * Math.random());
			switch (randomMove) {
			case 0: {

				champPosX = cupLocation.x + 1;
				champPosY = cupLocation.y;
				task3.getMap()[champPosX][champPosY] = new ChampionCell(g);
				g.setLocation(new Point(champPosX, champPosY));

				task3.moveForward();

			}
				break;
			case 1: {

				champPosX = cupLocation.x - 1;
				champPosY = cupLocation.y;
				task3.getMap()[champPosX][champPosY] = new ChampionCell(g);
				g.setLocation(new Point(champPosX, champPosY));

				task3.moveBackward();

			}
				break;

			case 2: {

				champPosX = cupLocation.x;
				champPosY = cupLocation.y + 1;
				task3.getMap()[champPosX][champPosY] = new ChampionCell(g);
				g.setLocation(new Point(champPosX, champPosY));

				task3.moveLeft();

			}
				break;
			case 3: {

				champPosX = cupLocation.x;
				champPosY = cupLocation.y - 1;
				task3.getMap()[champPosX][champPosY] = new ChampionCell(g);
				g.setLocation(new Point(champPosX, champPosY));

				task3.moveRight();

			}
				break;

			}

			assertTrue(
					"In ThirdTask, when GryffindorTrait is activated and the champion moves to the cup cell, he/she is considered a winner and thus should notify the listener ",
					hasCalled);

			assertEquals(
					"In ThirdTask, when GryffindorTrait is activated and the champion moves to the cup cell, he/she is considered a winner and thus should notify the listener ",
					g, w);

			assertEquals(
					"In all Tasks, when GryffindorTrait is activated and the champion moves from a cell, it should be converted to an EmptyCell ",
					EmptyCell.class,
					task3.getMap()[champPosX][champPosY].getClass());

		}
	}

	@Test(timeout = 1000)
	public void testOnGryffindorTraitTaskScenario8() throws Exception {

		for (int dh = 0; dh < 500; dh++) {

			testMethodExistsInClass(Task.class, "onGryffindorTrait", true,
					Void.TYPE);

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			FirstTask task1 = new FirstTask(e);
			task1.getChampions().clear();
			task1.getChampions().add(g);
			task1.getChampions().add(h);
			task1.getChampions().add(r);
			task1.getChampions().add(s);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task1, new Cell[10][10]);
			Cell[][] taskMap = task1.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}
			int champPosX = 6;
			int champPosY = 6;

			task1.getMap()[champPosX][champPosY] = new ChampionCell(g);
			g.setLocation(new Point(champPosX, champPosY));
			task1.setCurrentChamp(g);
			int champHP = ((Wizard) task1.getCurrentChamp()).getHp();

			g.useTrait();

			int randomMove = (int) (4 * Math.random());
			switch (randomMove) {
			case 0: {

				champPosX = 5;
				champPosY = 8;
				task1.getMap()[champPosX][champPosY] = new ChampionCell(g);
				g.setLocation(new Point(champPosX, champPosY));

				task1.moveForward();

			}
				break;
			case 1: {

				champPosX = 3;
				champPosY = 7;
				task1.getMap()[champPosX][champPosY] = new ChampionCell(g);
				g.setLocation(new Point(champPosX, champPosY));

				task1.moveBackward();

			}
				break;

			case 2: {

				champPosX = 1;
				champPosY = 5;
				task1.getMap()[champPosX][champPosY] = new ChampionCell(g);
				g.setLocation(new Point(champPosX, champPosY));

				task1.moveLeft();

			}
				break;
			case 3: {

				champPosX = 6;
				champPosY = 3;
				task1.getMap()[champPosX][champPosY] = new ChampionCell(g);
				g.setLocation(new Point(champPosX, champPosY));

				task1.moveRight();

			}
				break;

			}

			assertEquals(
					"In FirstTask, when GryffindorTrait is activated the Dragon should not attack until the champion makes his/her two actions.",
					champHP, g.getHp());

			assertTrue(
					"In FirstTask, when GryffindorTrait is activated the Dragon should not attack until the champion makes his/her two actions.",
					task1.getChampions().contains(g));

			assertEquals(
					"In FirstTask, when GryffindorTrait is activated the current champion should not be changed until the champion makes his/her two actions ",
					g, task1.getCurrentChamp());

			assertEquals(
					"In all Tasks, when GryffindorTrait is activated and the champion moves from a cell, it should be converted to an EmptyCell ",
					EmptyCell.class,
					task1.getMap()[champPosX][champPosY].getClass());

		}
	}

	@Test(timeout = 1000)
	public void testOnGryffindorTraitTaskScenario9() throws Exception {

		for (int dh = 0; dh < 500; dh++) {

			testMethodExistsInClass(Task.class, "onGryffindorTrait", true,
					Void.TYPE);

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);

			SecondTask task2 = new SecondTask(e);
			task2.getChampions().clear();
			task2.getChampions().add(g);
			task2.getChampions().add(h);
			task2.getChampions().add(r);
			task2.getChampions().add(s);

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					task2.getMap()[i][j] = new EmptyCell();
				}
			}

			int champPosX = (int) (Math.random() * 7) + 1;
			int champPosY = (int) (Math.random() * 7) + 1;

			task2.setCurrentChamp(g);
			int champHP = g.getHp();

			g.useTrait();

			int randomMove = (int) (4 * Math.random());
			switch (randomMove) {
			case 0: {

				champPosX = champPosX + 1;

				task2.getMap()[champPosX][champPosY] = new ChampionCell(g);
				g.setLocation(new Point(champPosX, champPosY));

				task2.getMap()[champPosX - 2][champPosY] = new ObstacleCell(
						new Merperson(100, 1000));

				task2.moveForward();

			}
				break;
			case 1: {

				champPosX = champPosX - 1;
				task2.getMap()[champPosX][champPosY] = new ChampionCell(g);
				g.setLocation(new Point(champPosX, champPosY));

				task2.getMap()[champPosX + 2][champPosY] = new ObstacleCell(
						new Merperson(100, 1000));

				task2.moveBackward();

			}
				break;

			case 2: {

				champPosY = champPosY + 1;
				task2.getMap()[champPosX][champPosY] = new ChampionCell(g);
				g.setLocation(new Point(champPosX, champPosY));

				task2.getMap()[champPosX][champPosY - 2] = new ObstacleCell(
						new Merperson(100, 1000));

				task2.moveLeft();

			}
				break;
			case 3: {

				champPosY = champPosY - 1;
				task2.getMap()[champPosX][champPosY] = new ChampionCell(g);
				g.setLocation(new Point(champPosX, champPosY));

				task2.getMap()[champPosX][champPosY + 2] = new ObstacleCell(
						new Merperson(100, 1000));

				task2.moveRight();

			}
				break;

			}

			assertEquals(
					"In SecondTask, when GryffindorTrait is activated the MerPeople should not attack until the champion makes his/her two actions.",
					champHP, g.getHp());

			assertTrue(
					"In SecondTask, when GryffindorTrait is activated the MerPeople should not attack until the champion makes his/her two actions.",
					task2.getChampions().contains(g));

			assertEquals(
					"In SecondTask, when GryffindorTrait is activated the current champion should not be changed until the champion makes his/her two actions ",
					g, task2.getCurrentChamp());

			assertEquals(
					"In all Tasks, when GryffindorTrait is activated and the champion moves from a cell, it should be converted to an EmptyCell ",
					EmptyCell.class,
					task2.getMap()[champPosX][champPosY].getClass());

		}
	}

	@Test(timeout = 1000)
	public void testOnHuffelpuffTraitFirstTaskScenario() throws Exception {
		testMethodExistsInClassOrSuperClass(FirstTask.class,
				"onHufflepuffTrait", true, Void.TYPE);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");

		e.add(h);
		e.add(g);
		e.add(r);
		e.add(s);
		FirstTask task1 = new FirstTask(e);
		task1.getChampions().clear();
		task1.getChampions().add(h);
		task1.getChampions().add(g);
		task1.getChampions().add(r);
		task1.getChampions().add(s);

		Field map = Task.class.getDeclaredField("map");
		map.setAccessible(true);
		map.set(task1, new Cell[10][10]);
		Cell[][] taskMap = task1.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}

		int champPosX = (int) (6 * Math.random()) + 2;
		int champPosY = (int) (6 * Math.random()) + 2;
		task1.getMap()[champPosX][champPosY] = new ChampionCell(h);
		h.setLocation(new Point(champPosX, champPosY));

		task1.getMap()[champPosX][champPosY - 1] = new ChampionCell(g);
		g.setLocation(new Point(champPosX, champPosY - 1));

		task1.setCurrentChamp(h);

		task1.getMarkedCells().clear();
		ArrayList<Point> newMarkedCells = new ArrayList<Point>();
		newMarkedCells.add(new Point(champPosX, champPosY - 1));
		newMarkedCells.add(new Point(champPosX, champPosY + 1));
		Field markedCells = FirstTask.class.getDeclaredField("markedCells");
		markedCells.setAccessible(true);
		markedCells.set(task1, newMarkedCells);

		int hOldHp = h.getDefaultHp() - ((int) (4 * Math.random()) + 1) * 150;
		h.setHp(hOldHp);
		int gOldHp = g.getDefaultHp() - ((int) (4 * Math.random()) + 1) * 150;
		g.setHp(gOldHp);

		task1.onHufflepuffTrait();
		task1.moveRight();

		assertEquals(
				"In FirstTask, if a hufflepuff champion is activating his/her trait the dragon should not attack",
				hOldHp, h.getHp());
		assertEquals(
				"In FirstTask, if a hufflepuff champion is activating his/her trait the dragon should not attack",
				gOldHp, g.getHp());

	}

	@Test(timeout = 1000)
	public void testOnHufflepuffTraitFirstTaskClass1() throws Exception {
		testMethodExistsInClassOrSuperClass(FirstTask.class,
				"onHufflepuffTrait", true, Void.TYPE);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task1 = new FirstTask(e);
		task1.getChampions().clear();
		task1.getChampions().add(h);
		task1.getChampions().add(g);
		task1.getChampions().add(r);
		task1.getChampions().add(s);
		task1.setCurrentChamp(h);
		task1.onHufflepuffTrait();
		assertTrue(
				"In FirstTask, onHufflepuffTrait method should set traitActivated",
				task1.isTraitActivated());

	}

	@Test(timeout = 1000)
	public void testOnHufflepuffTraitFirstTaskClass2() throws Exception {
		testMethodExistsInClassOrSuperClass(FirstTask.class,
				"onHufflepuffTrait", true, Void.TYPE);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task1 = new FirstTask(e);
		task1.getChampions().clear();
		task1.getChampions().add(g);
		task1.getChampions().add(h);
		task1.getChampions().add(r);
		task1.getChampions().add(s);
		task1.setCurrentChamp(h);
		task1.onHufflepuffTrait();
		assertEquals(
				"In FirstTask, onHufflepuffTrait method should set the champion's traitCooldown",
				3, h.getTraitCooldown());

	}

	@Test(timeout = 1000)
	public void testOnHufflepuffTraitSecondTaskClass1() throws Exception {
		testMethodExistsInClassOrSuperClass(SecondTask.class,
				"onHufflepuffTrait", true, Void.TYPE);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		SecondTask task2 = new SecondTask(e);
		task2.getChampions().clear();
		task2.getChampions().add(h);
		task2.getChampions().add(g);
		task2.getChampions().add(s);
		task2.getChampions().add(r);
		h = new HufflepuffWizard("huff");
		task2.setCurrentChamp(h);
		task2.onHufflepuffTrait();
		assertTrue(
				"In SecondTask, onHufflepuffTrait method should set traitActivated",
				task2.isTraitActivated());

	}

	@Test(timeout = 1000)
	public void testOnRavenclawTraitInFirstTaskClass2() throws Exception {
		testMethodExistsInClassOrSuperClass(FirstTask.class,
				"onRavenclawTrait", true, Object.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		FirstTask task1 = new FirstTask(e);
		task1.getChampions().clear();
		task1.getChampions().add(r);
		task1.getChampions().add(h);
		task1.getChampions().add(g);
		task1.getChampions().add(s);
		task1.setCurrentChamp(r);
		task1.onRavenclawTrait();
		assertEquals(
				"In FirstTask, onRavenclawTrait method should set the champion's traitCooldown",
				5, r.getTraitCooldown());

	}

	@Test(timeout = 1000)
	public void testOnRavenclawTraitInFirstTaskClass3() throws Exception {
		testMethodExistsInClassOrSuperClass(FirstTask.class,
				"onRavenclawTrait", true, Object.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		FirstTask task1 = new FirstTask(e);
		task1.getChampions().clear();
		task1.getChampions().add(g);
		task1.getChampions().add(h);
		task1.getChampions().add(r);
		task1.getChampions().add(s);
		ArrayList<Point> object = new ArrayList<Point>();
		object.add(new Point((int) (Math.random() * 10),
				(int) (Math.random() * 10)));
		object.add(new Point((int) (Math.random() * 10),
				(int) (Math.random() * 10)));
		object.add(new Point((int) (Math.random() * 10),
				(int) (Math.random() * 10)));
		Field markedCells = FirstTask.class.getDeclaredField("markedCells");
		markedCells.setAccessible(true);
		markedCells.set(task1, object);

		task1.setCurrentChamp(r);
		Object returnedMarkedCells = task1.onRavenclawTrait();

		assertEquals(
				"In FirstTask, onRavenclawTrait method should return markedCells object",
				object, returnedMarkedCells);

	}

	@SuppressWarnings("unchecked")
	@Test(timeout = 1000)
	public void testOnRavenclawTraitInSecondTaskClass1() throws Exception {
		for (int ghf = 0; ghf < 500; ghf++) {
			testMethodExistsInClassOrSuperClass(SecondTask.class,
					"onRavenclawTrait", true, Object.class);

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			e.add(g);
			e.add(h);
			e.add(r);
			e.add(s);
			boolean edge;
			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			Cell[][] taskMap;
			SecondTask task;
			do {
				task = new SecondTask(e);

				taskMap = task.getMap();

				edge = false;
				for (int i = 0; !edge && i < 10; i += 9)
					for (int j = 0; !edge && j < 10; j++)
						if (taskMap[i][j] instanceof TreasureCell
								&& ((TreasureCell) taskMap[i][j]).getOwner() == r)
							edge = true;
						else if (taskMap[j][i] instanceof TreasureCell
								&& ((TreasureCell) taskMap[j][i]).getOwner() == r)
							edge = true;
			} while (edge);

			Point treasureLocation = new Point();

			for (int i = 1; i < 9; i++) {
				for (int j = 1; j < 9; j++) {
					if (taskMap[i][j] instanceof TreasureCell
							&& ((TreasureCell) taskMap[i][j]).getOwner() == r)
						treasureLocation = new Point(i, j);
					else
						taskMap[i][j] = new EmptyCell();
				}
			}

			int champPosX = treasureLocation.x + 1;
			int champPosY = treasureLocation.y + 1;
			TreasureCell treasure = (TreasureCell) taskMap[treasureLocation.x][treasureLocation.y];
			task.getMap()[champPosX][champPosY] = new ChampionCell(
					treasure.getOwner());

			r.setLocation(new Point(champPosX, champPosY));
			task.setCurrentChamp(r);
			ArrayList<Direction> returnedDirection = (ArrayList<Direction>) task
					.onRavenclawTrait();

			assertEquals(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					2, returnedDirection.size());
			assertTrue(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					returnedDirection.contains(Direction.LEFT));
			assertTrue(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					returnedDirection.contains(Direction.FORWARD));

			r.setTraitCooldown(0);
			task.setTraitActivated(false);
			champPosX = treasureLocation.x + 1;
			champPosY = treasureLocation.y;
			task.getMap()[champPosX][champPosY] = new ChampionCell(
					treasure.getOwner());
			r.setLocation(new Point(champPosX, champPosY));
			task.setCurrentChamp(r);
			returnedDirection = (ArrayList<Direction>) task.onRavenclawTrait();

			assertEquals(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					1, returnedDirection.size());
			assertTrue(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					returnedDirection.contains(Direction.FORWARD));

			r.setTraitCooldown(0);
			task.setTraitActivated(false);
			champPosX = treasureLocation.x + 1;
			champPosY = treasureLocation.y - 1;
			task.getMap()[champPosX][champPosY] = new ChampionCell(
					treasure.getOwner());
			r.setLocation(new Point(champPosX, champPosY));
			task.setCurrentChamp(r);
			returnedDirection = (ArrayList<Direction>) task.onRavenclawTrait();

			assertEquals(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					2, returnedDirection.size());
			assertTrue(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					returnedDirection.contains(Direction.FORWARD));
			assertTrue(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					returnedDirection.contains(Direction.RIGHT));

			r.setTraitCooldown(0);
			task.setTraitActivated(false);
			champPosX = treasureLocation.x;
			champPosY = treasureLocation.y + 1;
			task.getMap()[champPosX][champPosY] = new ChampionCell(
					treasure.getOwner());
			r.setLocation(new Point(champPosX, champPosY));
			task.setCurrentChamp(r);
			returnedDirection = (ArrayList<Direction>) task.onRavenclawTrait();

			assertEquals(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					1, returnedDirection.size());
			assertTrue(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					returnedDirection.contains(Direction.LEFT));

			r.setTraitCooldown(0);
			task.setTraitActivated(false);
			champPosX = treasureLocation.x;
			champPosY = treasureLocation.y - 1;
			task.getMap()[champPosX][champPosY] = new ChampionCell(
					treasure.getOwner());
			r.setLocation(new Point(champPosX, champPosY));
			task.setCurrentChamp(r);
			returnedDirection = (ArrayList<Direction>) task.onRavenclawTrait();

			assertEquals(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					1, returnedDirection.size());
			assertTrue(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					returnedDirection.contains(Direction.RIGHT));

			r.setTraitCooldown(0);
			task.setTraitActivated(false);
			champPosX = treasureLocation.x - 1;
			champPosY = treasureLocation.y - 1;
			task.getMap()[champPosX][champPosY] = new ChampionCell(
					treasure.getOwner());
			r.setLocation(new Point(champPosX, champPosY));
			task.setCurrentChamp(r);
			returnedDirection = (ArrayList<Direction>) task.onRavenclawTrait();

			assertEquals(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					2, returnedDirection.size());
			assertTrue(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					returnedDirection.contains(Direction.BACKWARD));
			assertTrue(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					returnedDirection.contains(Direction.RIGHT));

			r.setTraitCooldown(0);
			task.setTraitActivated(false);
			champPosX = treasureLocation.x - 1;
			champPosY = treasureLocation.y;
			task.getMap()[champPosX][champPosY] = new ChampionCell(
					treasure.getOwner());
			r.setLocation(new Point(champPosX, champPosY));
			task.setCurrentChamp(r);
			returnedDirection = (ArrayList<Direction>) task.onRavenclawTrait();

			assertEquals(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					1, returnedDirection.size());
			assertTrue(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					returnedDirection.contains(Direction.BACKWARD));

			r.setTraitCooldown(0);
			task.setTraitActivated(false);
			champPosX = treasureLocation.x - 1;
			champPosY = treasureLocation.y + 1;
			task.getMap()[champPosX][champPosY] = new ChampionCell(
					treasure.getOwner());
			r.setLocation(new Point(champPosX, champPosY));
			task.setCurrentChamp(r);
			returnedDirection = (ArrayList<Direction>) task.onRavenclawTrait();

			assertEquals(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					2, returnedDirection.size());
			assertTrue(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					returnedDirection.contains(Direction.BACKWARD));
			assertTrue(
					"In SecondTask, onRavenclawTrait should return the directions to the treasure cell correctly",
					returnedDirection.contains(Direction.LEFT));

		}
	}

	@Test(timeout = 1000)
	public void testOnRavenclawTraitInSecondTaskClass2() throws Exception {
		testMethodExistsInClassOrSuperClass(SecondTask.class,
				"onRavenclawTrait", true, Object.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		SecondTask task = new SecondTask(e);
		task.getChampions().add(r);
		task.getChampions().add(g);
		task.getChampions().add(s);
		task.getChampions().add(h);
		Field map = Task.class.getDeclaredField("map");
		map.setAccessible(true);
		map.set(task, new Cell[10][10]);
		Cell[][] taskMap = task.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}
		int champPosX = (int) (8 * Math.random()) + 1;
		int champPosY = (int) (8 * Math.random()) + 1;
		task.getMap()[champPosX][champPosY] = new ChampionCell(r);
		task.getMap()[champPosX - 1][champPosY - 1] = new TreasureCell(r);
		r.setLocation(new Point(champPosX, champPosY));
		task.setCurrentChamp(r);
		task.onRavenclawTrait();
		assertTrue(
				"In SecondTask, onRavenclawTrait method should set traitActivated",
				task.isTraitActivated());

	}

	@Test(timeout = 1000)
	public void testOnRavenclawTraitInThirdTaskClass2() throws Exception {
		testMethodExistsInClassOrSuperClass(ThirdTask.class,
				"onRavenclawTrait", true, Object.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		ThirdTask task = new ThirdTask(e);
		task.getChampions().add(r);
		task.getChampions().add(g);
		task.getChampions().add(h);
		task.getChampions().add(s);
		Field map = Task.class.getDeclaredField("map");
		map.setAccessible(true);
		map.set(task, new Cell[10][10]);
		Cell[][] taskMap = task.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}
		int champPosX = (int) (8 * Math.random()) + 1;
		int champPosY = (int) (8 * Math.random()) + 1;
		task.getMap()[champPosX][champPosY] = new ChampionCell(r);
		task.getMap()[champPosX - 1][champPosY - 1] = new CupCell();
		r.setLocation(new Point(champPosX, champPosY));
		task.setCurrentChamp(r);
		task.onRavenclawTrait();
		assertTrue(
				"In ThirdTask, onRavenclawTrait method should set traitActivated",
				task.isTraitActivated());

	}

	@Test(timeout = 1000)
	public void testOnRavenclawTraitInThirdTaskClass3() throws Exception {
		testMethodExistsInClassOrSuperClass(ThirdTask.class,
				"onRavenclawTrait", true, Object.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		ThirdTask task = new ThirdTask(e);
		task.getChampions().add(r);
		task.getChampions().add(g);
		task.getChampions().add(h);
		task.getChampions().add(s);
		Field map = Task.class.getDeclaredField("map");
		map.setAccessible(true);
		map.set(task, new Cell[10][10]);
		Cell[][] taskMap = task.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}
		int champPosX = (int) (8 * Math.random()) + 1;
		int champPosY = (int) (8 * Math.random()) + 1;
		task.getMap()[champPosX][champPosY] = new ChampionCell(r);
		task.getMap()[champPosX - 1][champPosY - 1] = new CupCell();
		r.setLocation(new Point(champPosX, champPosY));
		task.setCurrentChamp(r);
		task.onRavenclawTrait();
		assertEquals(
				"In ThirdTask, onRavenclawTrait method should set the champion's traitCooldown",
				7, r.getTraitCooldown());

	}

	@Test(timeout = 1000)
	public void testOnSlytherinTraitFirstTaskSetsDirectionVariableInSlythWizard()
			throws Exception {

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);
		FirstTask task = new FirstTask(e);
		task.getChampions().add(s);
		task.getChampions().add(h);
		task.getChampions().add(r);
		task.getChampions().add(g);
		Field map = Task.class.getDeclaredField("map");
		map.setAccessible(true);
		map.set(task, new Cell[10][10]);
		Cell[][] taskMap = task.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}

		int champPosX = (int) (6 * Math.random()) + 2;
		int champPosY = (int) (6 * Math.random()) + 2;
		task.getMap()[champPosX][champPosY] = new ChampionCell(s);
		s.setLocation(new Point(champPosX, champPosY));

		task.setCurrentChamp(s);
		task.onSlytherinTrait(Direction.FORWARD);

		assertNotEquals(
				"In FirstTask, if slytherin champ activated his trait, his instance variable \"Direction\" should be set to the value of trait direction ",
				Direction.FORWARD, s.getTraitDirection());
	}

	@Test(timeout = 1000)
	public void testOnSlytherinTraitInFirstTaskClass3() throws Exception {
		for (int yh = 0; yh < 1; yh++) {
			testMethodExistsInClassOrSuperClass(FirstTask.class,
					"onSlytherinTrait", true, Void.TYPE, Direction.class);

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");

			e.add(s);
			e.add(h);
			e.add(r);
			e.add(g);

			FirstTask task = new FirstTask(e);
			task.getChampions().clear();
			task.getChampions().add(s);
			task.getChampions().add(h);
			task.getChampions().add(r);
			task.getChampions().add(g);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task, new Cell[10][10]);
			Cell[][] taskMap = task.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			g.setLocation(new Point(1, 1));
			h.setLocation(new Point(2, 2));
			r.setLocation(new Point(3, 3));
			s.setLocation(new Point(5, 5));
			taskMap[1][1] = new ChampionCell(g);
			taskMap[2][2] = new ChampionCell(h);
			taskMap[3][3] = new ChampionCell(r);
			taskMap[5][5] = new ChampionCell(s);

			int champPosX = (int) (6 * Math.random()) + 2;
			int champPosY = (int) (6 * Math.random()) + 2;
			while (champPosX - 2 == 4 && champPosY == 4)
				champPosY = (int) (6 * Math.random()) + 2;
			task.getMap()[champPosX][champPosY] = new ChampionCell(s);
			s.setLocation(new Point(champPosX, champPosY));
			task.setCurrentChamp(s);
			s.setTraitDirection(Direction.FORWARD);
			task.onSlytherinTrait(Direction.FORWARD);

			task.setCurrentChamp(h);
			task.moveForward();
			task.setCurrentChamp(r);
			task.moveForward();
			task.setCurrentChamp(g);
			task.moveForward();

			assertEquals(
					"In FirstTask, onSlytherinTrait method should end the turn and update the champion's traitCooldown",
					5, s.getTraitCooldown());

		}
	}

	@Test(timeout = 1000)
	public void testOnSlytherinTraitInSecondTaskClass3() throws Exception {
		for (int yh = 0; yh < 500; yh++) {
			testMethodExistsInClassOrSuperClass(SecondTask.class,
					"onSlytherinTrait", true, Void.TYPE, Direction.class);

			ArrayList<Champion> e = new ArrayList<>();
			GryffindorWizard g = new GryffindorWizard("gryff");
			HufflepuffWizard h = new HufflepuffWizard("huff");
			RavenclawWizard r = new RavenclawWizard("raven");
			SlytherinWizard s = new SlytherinWizard("slyth");
			e.add(s);
			e.add(h);
			e.add(r);
			e.add(g);

			SecondTask task = new SecondTask(e);
			task.getChampions().clear();
			task.getChampions().add(s);
			task.getChampions().add(h);
			task.getChampions().add(r);
			task.getChampions().add(g);

			Field map = Task.class.getDeclaredField("map");
			map.setAccessible(true);
			map.set(task, new Cell[10][10]);
			Cell[][] taskMap = task.getMap();
			for (int i = 0; i < taskMap.length; i++) {
				for (int j = 0; j < taskMap[i].length; j++) {
					taskMap[i][j] = new EmptyCell();
				}
			}

			g.setLocation(new Point(1, 1));
			h.setLocation(new Point(2, 2));
			r.setLocation(new Point(3, 3));
			s.setLocation(new Point(5, 5));
			taskMap[1][1] = new ChampionCell(g);
			taskMap[2][2] = new ChampionCell(h);
			taskMap[3][3] = new ChampionCell(r);
			taskMap[5][5] = new ChampionCell(s);

			int champPosX = (int) (6 * Math.random()) + 2;
			int champPosY = (int) (6 * Math.random()) + 2;
			task.getMap()[champPosX][champPosY] = new ChampionCell(s);
			s.setLocation(new Point(champPosX, champPosY));
			task.setCurrentChamp(s);
			s.setTraitDirection(Direction.FORWARD);
			task.onSlytherinTrait(Direction.FORWARD);

			task.setCurrentChamp(h);
			task.moveForward();
			task.setCurrentChamp(r);
			task.moveForward();
			task.setCurrentChamp(g);
			task.moveForward();

			assertEquals(
					"In SecondTask, onSlytherinTrait method should end the turn and update the champion's traitCooldown",
					3, s.getTraitCooldown());
		}
	}

	@Test(timeout = 1000)
	public void testOnSlytherinTraitInThirdTaskClass1() throws Exception {
		testMethodExistsInClassOrSuperClass(ThirdTask.class,
				"onSlytherinTrait", true, Void.TYPE, Direction.class);

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		ThirdTask task = new ThirdTask(e);
		task.getChampions().add(s);
		task.getChampions().add(g);
		task.getChampions().add(h);
		task.getChampions().add(r);
		Field map = Task.class.getDeclaredField("map");
		map.setAccessible(true);
		map.set(task, new Cell[10][10]);
		Cell[][] taskMap = task.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}
		int champPosX = (int) (6 * Math.random()) + 2;
		int champPosY = (int) (6 * Math.random()) + 2;
		int newchampPosX = champPosX - 2;
		int newchampPosY = champPosY;
		task.getMap()[champPosX][champPosY] = new ChampionCell(s);
		s.setLocation(new Point(champPosX, champPosY));
		task.setCurrentChamp(s);
		s.setTraitDirection(Direction.FORWARD);
		task.onSlytherinTrait(Direction.FORWARD);

		if (task.getMap()[newchampPosX][newchampPosY] instanceof ChampionCell) {
			assertEquals(
					"In ThirdTask, onSlytherinTrait should allow the champion to move two cells in the map at a time and place him correctly",
					s,
					((ChampionCell) task.getMap()[newchampPosX][newchampPosY])
							.getChamp());

		} else {

			fail("In ThirdTask, onSlytherinTrait should allow the champion to move two cells in the map at a time");
		}
		assertTrue(
				"In ThirdTask, after Slytherin champion uses his/her trait and move, his/her old cell should be empty",
				task.getMap()[champPosX][champPosY] instanceof EmptyCell);
		assertEquals(
				"In ThirdTask, after Slytherin champion uses his/her trait and move, his/her location should be updated",
				new Point(newchampPosX, newchampPosY), s.getLocation());

		task = new ThirdTask(e);
		task.getChampions().add(s);
		task.getChampions().add(g);
		task.getChampions().add(h);
		task.getChampions().add(r);
		map.set(task, new Cell[10][10]);
		taskMap = task.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}
		champPosX = (int) (6 * Math.random()) + 2;
		champPosY = (int) (6 * Math.random()) + 2;
		newchampPosX = champPosX + 2;
		newchampPosY = champPosY;
		task.getMap()[champPosX][champPosY] = new ChampionCell(s);
		s.setLocation(new Point(champPosX, champPosY));
		task.setCurrentChamp(s);
		task.setTraitActivated(false);
		s.setTraitCooldown(0);
		s.setTraitDirection(Direction.BACKWARD);
		task.onSlytherinTrait(Direction.BACKWARD);

		if (task.getMap()[newchampPosX][newchampPosY] instanceof ChampionCell) {
			assertEquals(
					"In ThirdTask, onSlytherinTrait should allow the champion to move two cells in the map at a time and place him correctly",
					s,
					((ChampionCell) task.getMap()[newchampPosX][newchampPosY])
							.getChamp());

		} else {
			fail("In ThirdTask, onSlytherinTrait should allow the champion to move two cells in the map at a time");
		}
		assertTrue(
				"In ThirdTask, after Slytherin champion uses his/her trait and move, his/her old cell should be empty",
				task.getMap()[champPosX][champPosY] instanceof EmptyCell);
		assertEquals(
				"In ThirdTask, after Slytherin champion uses his/her trait and move, his/her location should be updated",
				new Point(newchampPosX, newchampPosY), s.getLocation());

		task = new ThirdTask(e);
		task.getChampions().add(s);
		task.getChampions().add(g);
		task.getChampions().add(h);
		task.getChampions().add(r);
		map.set(task, new Cell[10][10]);
		taskMap = task.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}
		champPosX = (int) (6 * Math.random()) + 2;
		champPosY = (int) (6 * Math.random()) + 2;
		newchampPosX = champPosX;
		newchampPosY = champPosY - 2;
		task.getMap()[champPosX][champPosY] = new ChampionCell(s);
		s.setLocation(new Point(champPosX, champPosY));
		task.setCurrentChamp(s);
		task.setTraitActivated(false);
		s.setTraitCooldown(0);
		s.setTraitDirection(Direction.LEFT);
		task.onSlytherinTrait(Direction.LEFT);

		if (task.getMap()[newchampPosX][newchampPosY] instanceof ChampionCell) {
			assertEquals(
					"In ThirdTask, onSlytherinTrait should allow the champion to move two cells in the map at a time and place him correctly",
					s,
					((ChampionCell) task.getMap()[newchampPosX][newchampPosY])
							.getChamp());

		} else {
			fail("In ThirdTask, onSlytherinTrait should allow the champion to move two cells in the map at a time");
		}
		assertTrue(
				"In ThirdTask, after Slytherin champion uses his/her trait and move, his/her old cell should be empty",
				task.getMap()[champPosX][champPosY] instanceof EmptyCell);
		assertEquals(
				"In ThirdTask, after Slytherin champion uses his/her trait and move, his/her location should be updated",
				new Point(newchampPosX, newchampPosY), s.getLocation());

		task = new ThirdTask(e);
		task.getChampions().add(s);
		task.getChampions().add(g);
		task.getChampions().add(h);
		task.getChampions().add(r);
		map.set(task, new Cell[10][10]);
		taskMap = task.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}
		champPosX = (int) (6 * Math.random()) + 2;
		champPosY = (int) (6 * Math.random()) + 2;
		newchampPosX = champPosX;
		newchampPosY = champPosY + 2;
		task.getMap()[champPosX][champPosY] = new ChampionCell(s);
		s.setLocation(new Point(champPosX, champPosY));
		task.setCurrentChamp(s);
		task.setTraitActivated(false);
		s.setTraitCooldown(0);
		s.setTraitDirection(Direction.RIGHT);
		task.onSlytherinTrait(Direction.RIGHT);

		if (task.getMap()[newchampPosX][newchampPosY] instanceof ChampionCell) {
			assertEquals(
					"In ThirdTask, onSlytherinTrait should allow the champion to move two cells in the map at a time and place him correctly",
					s,
					((ChampionCell) task.getMap()[newchampPosX][newchampPosY])
							.getChamp());

		} else {
			fail("In ThirdTask, onSlytherinTrait should allow the champion to move two cells in the map at a time");
		}
		assertTrue(
				"In ThirdTask, after Slytherin champion uses his/her trait and move, his/her old cell should be empty",
				task.getMap()[champPosX][champPosY] instanceof EmptyCell);
		assertEquals(
				"In ThirdTask, after Slytherin champion uses his/her trait and move, his/her location should be updated",
				new Point(newchampPosX, newchampPosY), s.getLocation());

	}

	@Test(timeout = 1000)
	public void testOnSlytherinTraitSecondTaskSetsDirectionVariableInSlythWizard()
			throws Exception {

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);
		SecondTask task = new SecondTask(e);
		task.getChampions().add(s);
		task.getChampions().add(h);
		task.getChampions().add(r);
		task.getChampions().add(g);
		Field map = Task.class.getDeclaredField("map");
		map.setAccessible(true);
		map.set(task, new Cell[10][10]);
		Cell[][] taskMap = task.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}

		int champPosX = (int) (6 * Math.random()) + 2;
		int champPosY = (int) (6 * Math.random()) + 2;
		task.getMap()[champPosX][champPosY] = new ChampionCell(s);
		s.setLocation(new Point(champPosX, champPosY));

		task.setCurrentChamp(s);
		task.onSlytherinTrait(Direction.BACKWARD);
		assertNotEquals(
				"In SecondTask, if slytherin champ activated his trait, his instance variable \"Direction\" should be set to the value of trait direction ",
				Direction.BACKWARD, s.getTraitDirection());

	}

	@Test(timeout = 1000)
	public void testOnSlytherinTraitThirdTaskSetsDirectionVariableInSlythWizard()
			throws Exception {

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);
		ThirdTask task = new ThirdTask(e);
		task.getChampions().add(s);
		task.getChampions().add(h);
		task.getChampions().add(r);
		task.getChampions().add(g);
		Field map = Task.class.getDeclaredField("map");
		map.setAccessible(true);
		map.set(task, new Cell[10][10]);
		Cell[][] taskMap = task.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}

		int champPosX = (int) (6 * Math.random()) + 2;
		int champPosY = (int) (6 * Math.random()) + 2;
		task.getMap()[champPosX][champPosY] = new ChampionCell(s);
		s.setLocation(new Point(champPosX, champPosY));

		task.setCurrentChamp(s);
		task.onSlytherinTrait(Direction.LEFT);
		assertNotEquals(
				"In ThirdTask, if slytherin champ activated his trait, his instance variable \"Direction\" should be set to the value of trait direction ",
				Direction.LEFT, s.getTraitDirection());

	}

	@Test(timeout = 1000)
	public void testSecondTaskClassNotifiesListenerWhenTwoChampionsWin()
			throws Exception {
		testMethodExistsInClass(Tournament.class, "onFinishingSecondTask",
				true, Void.TYPE, ArrayList.class);
		hasCalled = false;
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);
		SecondTask task = new SecondTask(e);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				task.getMap()[i][j] = new EmptyCell();
		Tournament t = new Tournament() {
			public void onFinishingSecondTask(ArrayList<Champion> winners)
					throws IOException {
				hasCalled = true;
				super.onFinishingSecondTask(winners);
				thirdTask = getThirdTask();
				winnersList = winners;

			}
		};

		task.setCurrentChamp(s);
		task.getMap()[3][1] = new ChampionCell(s);
		((Wizard) task.getCurrentChamp()).setLocation(new Point(3, 1));
		task.getMap()[3][2] = new TreasureCell(s);

		task.getMap()[1][2] = new ChampionCell(h);
		h.setLocation(new Point(1, 2));
		task.getMap()[2][2] = new TreasureCell(h);

		task.getMap()[5][5] = new ChampionCell(r);
		r.setLocation(new Point(5, 5));
		task.getMap()[5][3] = new ObstacleCell(new Merperson(100, 1000));

		task.getMap()[5][7] = new ChampionCell(g);
		g.setLocation(new Point(5, 7));
		task.getMap()[6][6] = new ObstacleCell(new Merperson(100, 1000));

		task.setListener(t);
		task.moveRight();
		assertFalse(
				"ONLY upon the completion of the first task, the FirstTask notifies the Tournament class",
				hasCalled);

		task.setCurrentChamp(h);
		task.moveBackward();
		assertFalse(
				"ONLY upon the completion of the first task, the FirstTask notifies the Tournament class",
				hasCalled);

		task.setCurrentChamp(r);
		task.moveLeft();
		assertFalse(
				"ONLY upon the completion of the first task, the FirstTask notifies the Tournament class",
				hasCalled);

		task.setCurrentChamp(g);
		task.moveBackward();

		assertTrue(
				"Upon completion of the second task, the SecondTask notifies the Tournament class",
				hasCalled);
		assertNotNull(
				"Upon completion of the second task, the tournament should intialize the third task as long as there are winners",
				thirdTask);

		boolean condition = winnersList.contains(s) && winnersList.contains(h)
				&& !(winnersList.contains(r)) && !(winnersList.contains(g));
		assertTrue(
				"Upon completion of the second task, only the champions who survived should be sent as winners to the third task",
				condition);
		assertEquals(
				"Upon completion of the second task, the tournament should intialize the third task and set its listener to the tournament",
				t, thirdTask.getListener());

	}

	@Test(timeout = 1000)
	public void testSpellsCooldownIsDecrementedPerTurn() throws Exception {
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task1 = new FirstTask(e);
		task1.getChampions().clear();
		task1.getChampions().add(g);
		task1.getChampions().add(h);
		task1.getChampions().add(r);
		task1.getChampions().add(s);
		Field map = Task.class.getDeclaredField("map");
		map.setAccessible(true);
		map.set(task1, new Cell[10][10]);
		Cell[][] taskMap = task1.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}
		task1.getMap()[2][2] = new ChampionCell(g);
		g.setLocation(new Point(2, 2));
		task1.getMap()[3][3] = new ChampionCell(h);
		h.setLocation(new Point(3, 3));
		task1.getMap()[4][4] = new ChampionCell(r);
		r.setLocation(new Point(4, 4));
		task1.getMap()[5][5] = new ChampionCell(s);
		s.setLocation(new Point(5, 5));
		int initFirstSpellCooldown = (int) ((5 * Math.random()) + 3);
		int initSecondSpellCooldown = (int) ((5 * Math.random()) + 3);
		int initThirdSpellCooldown = (int) ((5 * Math.random()) + 3);
		HealingSpell s1 = new HealingSpell("HEL", 100, initFirstSpellCooldown,
				200);
		RelocatingSpell s2 = new RelocatingSpell("RELOC", 5,
				initSecondSpellCooldown, 5);
		DamagingSpell s3 = new DamagingSpell("DMG", 10, initThirdSpellCooldown,
				50);
		g.getSpells().add(s1);
		g.getSpells().add(s2);
		g.getSpells().add(s3);

		task1.setCurrentChamp(g);

		task1.castHealingSpell(s1);
		task1.castRelocatingSpell(s2, Direction.LEFT, Direction.FORWARD, 1);
		int turn1FirstSpellCooldown = s1.getCoolDown();
		int turn1SecondSpellCooldown = s2.getCoolDown();

		task1.setCurrentChamp(h);
		task1.moveLeft();

		task1.setCurrentChamp(r);
		task1.moveLeft();

		task1.setCurrentChamp(s);
		task1.moveLeft();

		task1.setCurrentChamp(g);
		task1.moveLeft();

		assertEquals(
				"In each turn, the cool down of the champions' spell should be decremented by 1",
				(turn1FirstSpellCooldown <= 0 ? 0 : turn1FirstSpellCooldown - 1),
				s1.getCoolDown());
		assertEquals(
				"In each turn, the cool down of the champions' spell should be decremented by 1",
				(turn1SecondSpellCooldown <= 0 ? 0
						: turn1SecondSpellCooldown - 1), s2.getCoolDown());

	}

	@Test(timeout = 1000)
	public void testTaskClassImplementsWizardListener() throws Exception {
		testClassImplementsInterface(Task.class, WizardListener.class);
	}

	@Test(timeout = 1000)
	public void testTaskListenerIsInterface() {
		testIsInterface(TaskListener.class);
	}

	@Test(timeout = 1000)
	public void testTaskListenerVariableGetter() throws Exception {
		testGetterMethodExistsInClass(Task.class, "getListener",
				TaskListener.class);
	}

	@Test(timeout = 1000)
	public void testTaskListenerVariableInTaskClass() throws Exception {
		testInstanceVariablesArePresent(Task.class, "listener", true);
		testInstanceVariablesArePrivate(Task.class, "listener");
		testInstanceVariablesType(Task.class, "listener", TaskListener.class);
	}

	@Test(timeout = 1000)
	public void testTaskListenerVariableSetter() throws Exception {
		testSetterMethodExistsInClass(Task.class, "setListener",
				TaskListener.class, true);
	}

	@Test(timeout = 1000)
	public void testThirdTaskClassHandlesNullListenerWhenAChampionWins()
			throws Exception {
		testMethodExistsInClass(Tournament.class, "onFinishingThirdTask", true,
				Void.TYPE, Champion.class);

		hasCalled = false;

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);
		ThirdTask task = new ThirdTask(e);

		task.setListener(null);

		try {
			task.setCurrentChamp(g);

			Point cupLocation = new Point(-1, -1);
			Cell[][] taskMap = task.getMap();
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					if (taskMap[i][j] instanceof CupCell)
						cupLocation = new Point(i, j);
				}
			}
			if (cupLocation.x == -1 || cupLocation.y == -1) {
				task.getMap()[1][3] = new CupCell();
				cupLocation = new Point(1, 3);
			}

			if (cupLocation.x > 0) {

				task.getMap()[cupLocation.x][cupLocation.y - 1] = new ChampionCell(
						g);
				((Wizard) task.getCurrentChamp()).setLocation(new Point(
						cupLocation.x, cupLocation.y - 1));
				task.moveRight();

			} else {

				task.getMap()[cupLocation.x][cupLocation.y + 1] = new ChampionCell(
						g);
				((Wizard) task.getCurrentChamp()).setLocation(new Point(
						cupLocation.x, cupLocation.y + 1));
				task.moveLeft();

			}

		} catch (NullPointerException ee) {
			fail("The ThirdTask class should handle when listener is null. It shouldn't notify the listener that the task ended.");
		}
	}

	@Test(timeout = 1000)
	public void testThirdTaskClassNotifiesListenerWhenAChampionWins()
			throws Exception {
		testMethodExistsInClass(Tournament.class, "onFinishingThirdTask", true,
				Void.TYPE, Champion.class);

		hasCalled = false;

		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(s);
		e.add(h);
		e.add(r);
		e.add(g);
		ThirdTask task = new ThirdTask(e);

		Tournament t = new Tournament() {
			public void onFinishingThirdTask(Champion winner) {
				hasCalled = true;
				w = winner;
			}
		};

		task.setListener(t);
		task.setCurrentChamp(g);

		Point cupLocation = new Point(-1, -1);
		Cell[][] taskMap = task.getMap();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (taskMap[i][j] instanceof CupCell)
					cupLocation = new Point(i, j);
			}
		}
		if (cupLocation.x == -1 || cupLocation.y == -1) {
			task.getMap()[1][3] = new CupCell();
			cupLocation = new Point(1, 3);
		}

		if (cupLocation.x > 0) {

			task.getMap()[cupLocation.x][cupLocation.y - 1] = new ChampionCell(
					g);
			((Wizard) task.getCurrentChamp()).setLocation(new Point(
					cupLocation.x, cupLocation.y - 1));
			task.moveRight();

		} else {

			task.getMap()[cupLocation.x][cupLocation.y + 1] = new ChampionCell(
					g);
			((Wizard) task.getCurrentChamp()).setLocation(new Point(
					cupLocation.x, cupLocation.y + 1));
			task.moveLeft();

		}

		assertTrue(
				"Upon completion of the the third task, the ThirdTask notifies the Tournament class",
				hasCalled);

		assertEquals(
				"Upon completion of the the third task, the ThirdTask should send to the tournament class the winning champion",
				g, w);

	}

	@Test(timeout = 1000)
	public void testTournamentClassImplementsTaskListener() throws Exception {
		testClassImplementsInterface(Tournament.class, TaskListener.class);
	}

	@Test(timeout = 1000)
	public void testTraitActivatedPerTurn() throws Exception {
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task1 = new FirstTask(e);
		task1.getChampions().clear();
		task1.getChampions().add(g);
		task1.getChampions().add(h);
		task1.getChampions().add(r);
		task1.getChampions().add(s);
		Field map = Task.class.getDeclaredField("map");
		map.setAccessible(true);
		map.set(task1, new Cell[10][10]);
		Cell[][] taskMap = task1.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}
		task1.getMap()[2][2] = new ChampionCell(g);
		g.setLocation(new Point(2, 2));
		task1.getMap()[3][3] = new ChampionCell(h);
		h.setLocation(new Point(3, 3));
		task1.getMap()[4][4] = new ChampionCell(r);
		r.setLocation(new Point(4, 4));
		task1.getMap()[5][5] = new ChampionCell(s);
		s.setLocation(new Point(5, 5));

		task1.setCurrentChamp(g);
		task1.moveLeft();

		task1.setCurrentChamp(h);
		task1.onHufflepuffTrait();
		task1.moveRight();

		task1.setCurrentChamp(r);
		boolean traitActivatedNextTurnOtherPlayer = task1.isTraitActivated();
		task1.moveLeft();

		task1.setCurrentChamp(s);
		task1.moveRight();

		task1.setCurrentChamp(g);
		task1.moveRight();

		task1.setCurrentChamp(h);
		task1.moveLeft();
		boolean traitActivatedNextTurnOSamePlayer = task1.isTraitActivated();

		task1.setCurrentChamp(r);
		task1.moveRight();

		task1.setCurrentChamp(s);
		task1.moveLeft();

		assertFalse(
				"In each turn, the trait can be activated for one turn per player",
				traitActivatedNextTurnOtherPlayer);
		assertFalse(
				"In each turn, the trait can be activated for one turn per player",
				traitActivatedNextTurnOSamePlayer);
	}

	@Test(timeout = 1000)
	public void testTraitCooldownIsDecrementedPerTurn() throws Exception {
		ArrayList<Champion> e = new ArrayList<>();
		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task1 = new FirstTask(e);
		task1.getChampions().clear();
		task1.getChampions().add(g);
		task1.getChampions().add(h);
		task1.getChampions().add(r);
		task1.getChampions().add(s);
		Field map = Task.class.getDeclaredField("map");
		map.setAccessible(true);
		map.set(task1, new Cell[10][10]);
		Cell[][] taskMap = task1.getMap();
		for (int i = 0; i < taskMap.length; i++) {
			for (int j = 0; j < taskMap[i].length; j++) {
				taskMap[i][j] = new EmptyCell();
			}
		}
		task1.getMap()[2][2] = new ChampionCell(g);
		g.setLocation(new Point(2, 2));
		task1.getMap()[3][3] = new ChampionCell(h);
		h.setLocation(new Point(3, 3));
		task1.getMap()[4][4] = new ChampionCell(r);
		r.setLocation(new Point(4, 4));
		task1.getMap()[5][5] = new ChampionCell(s);
		s.setLocation(new Point(5, 5));

		task1.setCurrentChamp(g);
		task1.onGryffindorTrait();
		task1.moveLeft();
		task1.moveLeft();
		int turn1Cooldown = g.getTraitCooldown();

		task1.setCurrentChamp(h);
		task1.moveRight();

		task1.setCurrentChamp(r);
		task1.moveLeft();

		task1.setCurrentChamp(s);
		task1.moveRight();

		task1.setCurrentChamp(g);
		task1.moveRight();

		assertEquals(
				"In each turn, the trait cool down of the champions should be decremented by 1",
				(turn1Cooldown <= 0 ? 0 : turn1Cooldown - 1),
				g.getTraitCooldown());

	}

	@Test(timeout = 1000)
	public void testTurnAfterBothChampionsDieByDragonFire() throws Exception {

		ArrayList<Champion> e = new ArrayList<>();
		SlytherinWizard g = new SlytherinWizard("gryff");
		SlytherinWizard h = new SlytherinWizard("huff");
		SlytherinWizard r = new SlytherinWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		FirstTask task = new FirstTask(e);
		int expectedDmg = 150;

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				if (!(task.getMap()[i][j] instanceof ChampionCell))
					task.getMap()[i][j] = new EmptyCell();

		Wizard currentChamp = (Wizard) task.getCurrentChamp();
		Wizard otherChamp = (Wizard) task.getChampions().get(1);

		Champion thirdChamp = task.getChampions().get(2);
		Champion fourthChamp = task.getChampions().get(3);

		currentChamp.setHp(100);
		otherChamp.setHp(100);
		task.getMap()[currentChamp.getLocation().x][currentChamp.getLocation().y] = new EmptyCell();
		task.getMap()[2][2] = new ChampionCell((Champion) currentChamp);
		currentChamp.setLocation((Point) new Point(2, 2));

		int i, j = 0;
		i = currentChamp.getLocation().x;
		j = currentChamp.getLocation().y;
		Point currentLocation = currentChamp.getLocation();
		Point otherLocation = new Point(0, 0);

		task.getMarkedCells().clear();
		task.getMarkedCells().add(new Point(i - 1, j));
		task.getMarkedCells().add(new Point(i, j + 1));

		if (currentChamp.getHp() < 150)
			expectedDmg = currentChamp.getHp();

		int hpOld = currentChamp.getHp();
		otherLocation = new Point(i - 1, j);

		task.getMap()[otherChamp.getLocation().x][otherChamp.getLocation().y] = new EmptyCell();
		task.getMap()[otherLocation.x][otherLocation.y] = new ChampionCell(
				(Champion) otherChamp);
		otherChamp.setLocation((Point) otherLocation.clone());
		task.moveRight();

		currentLocation = currentChamp.getLocation();

		assertEquals(
				"The damage inflicted on the champion by the dragon fire is incorrect",
				expectedDmg, hpOld - currentChamp.getHp());

		assertEquals(
				"The damage inflicted on the champion by the dragon fire is incorrect",
				expectedDmg, hpOld - otherChamp.getHp());

		assertEquals("Hp of the current champion should have reached zero", 0,
				currentChamp.getHp());

		assertFalse(
				"The champion arraylist should not contain the dead champions",
				task.getChampions().contains(currentChamp));

		assertTrue(
				"The champion location should be an empty cell",
				task.getMap()[currentLocation.x][currentLocation.y] instanceof EmptyCell);

		assertEquals("Hp of the other champion should have reached zero", 0,
				otherChamp.getHp());

		assertFalse(
				"The champion arraylist should not contain the dead champions",
				task.getChampions().contains(otherChamp));

		assertTrue(
				"The champion location should be an empty cell",
				task.getMap()[otherLocation.x][otherLocation.y] instanceof EmptyCell);

		assertEquals(
				"The size of the arraylist of champions should be equal to the alive champions only",
				2, task.getChampions().size());

		assertTrue(
				"The update in the order of the champions is incorrect after the champion's death",
				thirdChamp == task.getCurrentChamp());

		task.moveLeft();

		assertTrue(
				"The update in the order of the champions is incorrect after the champion's death",
				fourthChamp == task.getCurrentChamp());

		task.moveRight();

		assertTrue(
				"The update in the order of the champions is incorrect after the champion's death",
				thirdChamp == task.getCurrentChamp());
	}

	@Test(timeout = 1000)
	public void testTurnAfterCurrentChampionDieByMerPerson() throws Exception {
		testMethodExistsInClass(Task.class, "moveRight", true, Void.TYPE);

		ArrayList<Champion> e = new ArrayList<>();
		SlytherinWizard r = new SlytherinWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		SlytherinWizard g = new SlytherinWizard("gryff");
		e.add(r);
		e.add(s);
		e.add(g);

		SecondTask task = null;
		int x, y;
		Point location = null;
		task = new SecondTask(e);
		location = new Point(5, 5);
		Champion secondChamp = task.getChampions().get(1);
		Champion thirdChamp = task.getChampions().get(2);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				if (!(task.getMap()[i][j] instanceof ChampionCell))
					task.getMap()[i][j] = new EmptyCell();

		task.setCurrentChamp(task.getChampions().get(0));

		x = location.x;
		y = location.y;

		task.getMap()[9][0] = new EmptyCell();
		Wizard currentChamp = (Wizard) task.getCurrentChamp();
		task.getMap()[x - 1][y] = new ObstacleCell(new Merperson(100, 300));
		task.getMap()[x][y + 1] = new ObstacleCell(new Merperson(100, 300));
		task.getMap()[x + 1][y] = new ObstacleCell(new Merperson(100, 300));
		task.getMap()[x][y - 1] = new ChampionCell((Champion) currentChamp);

		currentChamp.setLocation(new Point(x, y - 1));

		int hpOld = currentChamp.getHp();
		task.moveRight();
		assertEquals(
				"The damage inflicted to the current champion is incorrect",
				850, hpOld - currentChamp.getHp());

		assertFalse(
				"The arraylist of champions should not contain the current champion after he dies",
				task.getChampions().contains(currentChamp));

		assertEquals(
				"The size of the arraylist of champions should be equal to the alive champions only",
				2, task.getChampions().size());

		assertTrue("The champion location should be an empty cell",
				task.getMap()[5][5] instanceof EmptyCell);

		assertTrue(
				"The update in the order of the champions is incorrect after the champion's death",
				secondChamp == task.getCurrentChamp());

		task.moveForward();

		assertTrue(
				"The update in the order of the champions is incorrect after the champion's death",
				thirdChamp == task.getCurrentChamp());

		task.moveLeft();

		assertTrue(
				"The update in the order of the champions is incorrect after the champion's death",
				secondChamp == task.getCurrentChamp());
	}

	@Test(timeout = 1000)
	public void testTurnAfterOtherChampionDieByDragonFire() throws Exception {

		ArrayList<Champion> e = new ArrayList<>();
		SlytherinWizard g = new SlytherinWizard("gryff");
		SlytherinWizard h = new SlytherinWizard("huff");
		SlytherinWizard r = new SlytherinWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);
		FirstTask task = new FirstTask(e);
		int expectedDmg = 150;
		int expectedDmgOther = 150;

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				if (!(task.getMap()[i][j] instanceof ChampionCell))
					task.getMap()[i][j] = new EmptyCell();

		Wizard currentChamp = (Wizard) task.getCurrentChamp();
		Wizard otherChamp = (Wizard) task.getChampions().get(2);

		Champion secondChamp = task.getChampions().get(1);
		Champion fourthChamp = task.getChampions().get(3);

		otherChamp.setHp(250);
		task.getMap()[currentChamp.getLocation().x][currentChamp.getLocation().y] = new EmptyCell();
		task.getMap()[2][2] = new ChampionCell((Champion) currentChamp);
		currentChamp.setLocation((Point) new Point(2, 2));

		int i, j = 0;
		i = currentChamp.getLocation().x;
		j = currentChamp.getLocation().y;
		@SuppressWarnings("unused")
		Point currentLocation = currentChamp.getLocation();
		Point otherLocation = new Point(0, 0);

		otherChamp.setHp(100);
		task.setCurrentChamp((Champion) currentChamp);
		task.getMarkedCells().clear();
		task.getMarkedCells().add(new Point(i - 1, j));
		task.getMarkedCells().add(new Point(i, j + 1));

		if (currentChamp.getHp() < 150)
			expectedDmg = currentChamp.getHp();
		if (otherChamp.getHp() < 150)
			expectedDmgOther = otherChamp.getHp();

		int hpOld = currentChamp.getHp();
		int hpOldOther = otherChamp.getHp();
		otherLocation = new Point(i - 1, j);

		task.getMap()[otherChamp.getLocation().x][otherChamp.getLocation().y] = new EmptyCell();
		task.getMap()[otherLocation.x][otherLocation.y] = new ChampionCell(
				(Champion) otherChamp);
		otherChamp.setLocation((Point) otherLocation.clone());
		task.moveRight();

		currentLocation = currentChamp.getLocation();

		assertEquals(
				"The damage inflicted on the champion by the dragon fire is incorrect",
				expectedDmg, hpOld - currentChamp.getHp());

		assertEquals(
				"The damage inflicted on the champion by the dragon fire is incorrect",
				expectedDmgOther, hpOldOther - otherChamp.getHp());

		assertEquals("Hp of the other champion should have reached zero", 0,
				otherChamp.getHp());

		assertFalse(
				"The champion arraylist should not contain the dead champions",
				task.getChampions().contains(otherChamp));

		assertTrue(
				"The champion location should be an empty cell",
				task.getMap()[otherLocation.x][otherLocation.y] instanceof EmptyCell);

		assertEquals(
				"The size of the arraylist of champions should be equal to the alive champions only",
				3, task.getChampions().size());

		assertTrue(
				"The update in the order of the champions is incorrect after the champion's death",
				secondChamp == task.getCurrentChamp());

		task.moveForward();

		assertEquals(
				"The update in the order of the champions is incorrect after the champion's death",
				fourthChamp, task.getCurrentChamp());

		task.moveRight();

		assertTrue(
				"The update in the order of the champions is incorrect after the champion's death",
				currentChamp == task.getCurrentChamp());

		task.moveRight();

		assertTrue(
				"The update in the order of the champions is incorrect after the champion's death",
				secondChamp == task.getCurrentChamp());

	}

	@Test(timeout = 1000)
	public void testWizardListenerIsInterface() {
		testIsInterface(WizardListener.class);
	}

	@Test(timeout = 1000)
	public void testWizardListenerVariableGetter() throws Exception {
		testGetterMethodExistsInClass(Wizard.class, "getListener",
				WizardListener.class);
	}

	@Test(timeout = 1000)
	public void testWizardListenerVariableInWizardClass() throws Exception {
		testInstanceVariablesArePresent(Wizard.class, "listener", true);
		testInstanceVariablesArePrivate(Wizard.class, "listener");
		testInstanceVariablesType(Wizard.class, "listener",
				WizardListener.class);
	}

	@Test(timeout = 1000)
	public void testWizardListenerVariableSetter() throws Exception {
		testSetterMethodExistsInClass(Wizard.class, "setListener",
				WizardListener.class, true);
	}

	/* Helper methods */
	public int sumArray(int[][] input) {
		int sum = 0;
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input[i].length; j++) {
				sum += input[i][j];
			}
		}
		return sum;
	}

	public int sumArray(int[] input) {
		int sum = 0;
		for (int i = 0; i < input.length; i++) {
			sum += input[i];
		}
		return sum;
	}

	@SuppressWarnings("rawtypes")
	private void testIsInterface(Class aClass) {
		assertEquals(aClass.getName() + " should be an Interface", 1537,
				aClass.getModifiers());

	}

	@SuppressWarnings("rawtypes")
	private void testClassImplementsInterface(Class aClass, Class interfaceName) {
		Class[] interfaces = aClass.getInterfaces();
		boolean inherits = false;
		for (Class i : interfaces) {
			if (i.toString().equals(interfaceName.toString()))
				inherits = true;
		}
		assertTrue(aClass.getName() + " class should implement "
				+ interfaceName.getName() + " interface.", inherits);
	}

	@SuppressWarnings("rawtypes")
	private void testInstanceVariablesArePresent(Class aClass, String varName,
			boolean implementedVar) throws SecurityException {

		boolean thrown = false;
		try {
			aClass.getDeclaredField(varName);
		} catch (NoSuchFieldException e) {
			thrown = true;
		}
		if (implementedVar) {
			assertFalse("There should be " + varName
					+ " instance variable in class " + aClass.getName(), thrown);
		} else {
			assertTrue("There should not be " + varName
					+ " instance variable in class " + aClass.getName()
					+ ", it should be inherited from the super class", thrown);
		}
	}

	@SuppressWarnings("rawtypes")
	private void testInstanceVariablesArePrivate(Class aClass, String varName)
			throws NoSuchFieldException, SecurityException {
		Field f = aClass.getDeclaredField(varName);
		assertEquals(
				varName + " instance variable in class " + aClass.getName()
						+ " should not be accessed outside that class", 2,
				f.getModifiers());
	}

	@SuppressWarnings("rawtypes")
	private void testInstanceVariablesType(Class aClass, String varName,
			Class varType) throws NoSuchFieldException, SecurityException {
		Field f = aClass.getDeclaredField(varName);
		assertEquals(
				varName + " instance variable in class " + aClass.getName()
						+ " should be of type " + varType.getName(), varType,
				f.getType());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void testGetterMethodExistsInClass(Class aClass, String methodName,
			Class returnedType) {
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName);
		} catch (Exception e) {
			found = false;
		}
		String varName = methodName.substring(3).toLowerCase();
		assertTrue(
				"The " + varName + " instance variable in class "
						+ aClass.getName() + " is a READ variable.", found);
		assertTrue("incorrect return type for " + methodName + " method in "
				+ aClass.getName() + " class.", m.getReturnType()
				.isAssignableFrom(returnedType));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void testSetterMethodExistsInClass(Class aClass, String methodName,
			Class inputType, boolean writeVariable) {

		Method[] methods = aClass.getDeclaredMethods();
		String varName = methodName.substring(3).toLowerCase();
		if (writeVariable) {
			assertTrue("The " + varName + " instance variable in class "
					+ aClass.getName() + " is a WRITE variable.",
					containsMethodName(methods, methodName));
		} else {
			assertFalse("The " + varName + " instance variable in class "
					+ aClass.getName() + " is a READ ONLY variable.",
					containsMethodName(methods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputType);
		} catch (Exception e) {
			found = false;
		}

		assertTrue(aClass.getName() + " class should have " + methodName
				+ " method that takes one " + inputType.getSimpleName()
				+ " parameter", found);

		assertTrue("incorrect return type for " + methodName + " method in "
				+ aClass.getName() + ".", m.getReturnType().equals(Void.TYPE));

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void testMethodExistsInClassOrSuperClass(Class aClass,
			String methodName, boolean implementedMethod, Class returnType,
			Class... inputTypes) {

		Method[] methods = aClass.getDeclaredMethods();
		Method[] superMethods = aClass.getSuperclass().getDeclaredMethods();
		if (implementedMethod) {

			assertTrue("The method " + methodName
					+ " should be implemented in " + aClass.getName()
					+ " class or " + aClass.getSuperclass().getName()
					+ " class", containsMethodName(methods, methodName)
					|| containsMethodName(superMethods, methodName));
		} else {
			assertFalse(
					"The "
							+ methodName
							+ " method in class "
							+ aClass.getName()
							+ " should not be implemented, only inherited from super class.",
					containsMethodName(methods, methodName)
							|| containsMethodName(superMethods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputTypes);
		} catch (Exception e) {
			try {
				m = aClass.getSuperclass().getDeclaredMethod(methodName,
						inputTypes);
			} catch (Exception e1) {
				found = false;
			}
		}

		String inputsList = "";
		for (Class inputType : inputTypes) {
			inputsList += inputType.getSimpleName() + ", ";
		}
		if (inputsList.equals(""))
			assertTrue("The method " + methodName
					+ " should be implemented in " + aClass.getName()
					+ " class or " + aClass.getSuperclass().getName()
					+ " class", found);
		else {
			if (inputsList.charAt(inputsList.length() - 1) == ' ')
				inputsList = inputsList.substring(0, inputsList.length() - 2);
			assertTrue(
					"The method " + methodName + " implemented in "
							+ aClass.getName() + " class or "
							+ aClass.getSuperclass().getName()
							+ " class, should take " + inputsList
							+ " parameter(s)", found);
		}

		assertTrue("incorrect return type for " + methodName + " method in "
				+ aClass.getName() + ".", m.getReturnType().equals(returnType));

	}

	private static boolean containsMethodName(Method[] methods, String name) {
		for (Method method : methods) {
			if (method.getName().equals(name))
				return true;
		}
		return false;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void testMethodExistsInClass(Class aClass, String methodName,
			boolean implementedMethod, Class returnType, Class... inputTypes) {

		Method[] methods = aClass.getDeclaredMethods();

		if (implementedMethod) {
			assertTrue(
					"The " + methodName + " method in class "
							+ aClass.getName() + " should be implemented.",
					containsMethodName(methods, methodName));
		} else {
			assertFalse(
					"The "
							+ methodName
							+ " method in class "
							+ aClass.getName()
							+ " should not be implemented, only inherited from super class.",
					containsMethodName(methods, methodName));
			return;
		}
		Method m = null;
		boolean found = true;
		try {
			m = aClass.getDeclaredMethod(methodName, inputTypes);
		} catch (Exception e) {
			found = false;
		}

		String inputsList = "";
		for (Class inputType : inputTypes) {
			inputsList += inputType.getSimpleName() + ", ";
		}
		if (inputsList.equals(""))
			assertTrue(aClass.getName() + " class should have " + methodName
					+ " method that takes no parameters", found);
		else {
			if (inputsList.charAt(inputsList.length() - 1) == ' ')
				inputsList = inputsList.substring(0, inputsList.length() - 2);
			assertTrue(aClass.getName() + " class should have " + methodName
					+ " method that takes " + inputsList + " parameter(s)",
					found);
		}

		assertTrue("incorrect return type for " + methodName + " method in "
				+ aClass.getName() + ".", m.getReturnType().equals(returnType));

	}

}
