//reconstructing
package harrypotter.tests;

import harrypotter.model.character.*;
import harrypotter.model.magic.*;
import harrypotter.model.tournament.*;
import harrypotter.model.world.*;

import java.awt.Point;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

import org.junit.Test;

public class M1PublicTests {

	@Test(timeout = 100)
	public void testClassImplementsInterfaceGryffindorWizard() throws Exception {
		testClassImplementsInterface(GryffindorWizard.class, Champion.class);
	}

	@Test(timeout = 100)
	public void testClassImplementsInterfaceHufflepuffWizard() throws Exception {
		testClassImplementsInterface(HufflepuffWizard.class, Champion.class);
	}

	@Test(timeout = 100)
	public void testClassIsAbstractCell() {
		testClassIsAbstract(Cell.class);
	}

	@Test(timeout = 100)
	public void testClassIsAbstractCollectible() {
		testClassIsAbstract(Collectible.class);
	}

	@Test(timeout = 100)
	public void testClassIsAbstractObstacle() {
		testClassIsAbstract(Obstacle.class);
	}

	@Test(timeout = 100)
	public void testClassIsAbstractSpell() {
		testClassIsAbstract(Spell.class);
	}

	@Test(timeout = 100)
	public void testClassIsAbstractTask() {
		testClassIsAbstract(Task.class);
	}

	@Test(timeout = 100)
	public void testClassIsAbstractWizard() {
		testClassIsAbstract(Wizard.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassChampionCell() throws Exception {
		testClassIsSubClass(ChampionCell.class, Cell.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassCollectibleCell() throws Exception {
		testClassIsSubClass(CollectibleCell.class, Cell.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassEmptyCell() throws Exception {
		testClassIsSubClass(EmptyCell.class, Cell.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassMerpersonObstacle() throws Exception {
		testClassIsSubClass(Merperson.class, Obstacle.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassRavenclawWizard() throws Exception {
		testClassIsSubClass(RavenclawWizard.class, Wizard.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassRelocatingSpell() throws Exception {
		testClassIsSubClass(RelocatingSpell.class, Spell.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassSlytherinWizard() throws Exception {
		testClassIsSubClass(SlytherinWizard.class, Wizard.class);
	}

	@Test(timeout = 100)
	public void testClassIsSubClassThirdTask() throws Exception {
		testClassIsSubClass(ThirdTask.class, Task.class);
	}

	@Test(timeout = 100)
	public void testConstructorChampionCell() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { Champion.class };
		testConstructorExists(ChampionCell.class, inputs);
	}

	@Test(timeout = 100)
	public void testConstructorChampionCellInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(
				"harrypotter.model.world.ChampionCell").getConstructor(
				Champion.class);
		Object myObj = constructor.newInstance(new GryffindorWizard("Gryff"));

		Method myObjMethod = myObj.getClass().getMethod("getChamp");
		Champion champ = (Champion) myObjMethod.invoke(myObj);
		assertTrue(
				"The constructor of the ChampionCell class should initialize the instance variable \"champ\" correctly",
				champ instanceof GryffindorWizard
						&& ((GryffindorWizard) champ).getName().equals("Gryff"));
	}

	@Test(timeout = 100)
	public void testConstructorCollectible() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { String.class };
		testConstructorExists(Collectible.class, inputs);
	}

	@Test(timeout = 100)
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

		boolean shuffled = true;
		int times = 0;
		for (int i = 0; i < 10; i++) {
			Object myObj = constructor.newInstance(e);
			Method myObjMethod = myObj.getClass().getMethod("getChampions");
			@SuppressWarnings("unchecked")
			ArrayList<Champion> champs = (ArrayList<Champion>) myObjMethod
					.invoke(myObj);
			if (champs.get(0).equals(g) && champs.get(1).equals(h)
					&& champs.get(2).equals(r) && champs.get(3).equals(s)) {
				times++;
			}

		}
		if (times == 10)
			shuffled = false;
		assertTrue(
				"The constructor of the FirstTask class should randomly shuffle the champions list",
				shuffled);

		Object myObj = constructor.newInstance(e);
		Method myObjMethod = myObj.getClass().getMethod("getChampions");
		@SuppressWarnings("unchecked")
		ArrayList<Champion> champs = (ArrayList<Champion>) myObjMethod
				.invoke(myObj);
		assertTrue(
				"The constructor of the FirstTask class should initialize the instance variable \"champions\" correctly",
				champs.contains(g) && champs.contains(h) && champs.contains(r)
						&& champs.contains(s));

		myObjMethod = myObj.getClass().getMethod("getMap");
		Cell[][] map = (Cell[][]) myObjMethod.invoke(myObj);
		assertTrue(
				"The constructor of the FirstTask class should initialize the instance variable \"map\" correctly",
				map.length == 10 && map[0].length == 10);

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

		ArrayList<Potion> correctPotions = new ArrayList<Potion>();
		correctPotions.add(new Potion("Felix Felicis", 1000));
		correctPotions.add(new Potion("Pepperup Potion", 500));
		correctPotions.add(new Potion("Skele-Gro", 200));
		correctPotions.add(new Potion("Amortentia", 100));
		correctPotions.add(new Potion("Senzu", 700));
		correctPotions.add(new Potion("Thunder Bolt", 1700));

		myObjMethod = myObj.getClass().getMethod("getPotions");
		@SuppressWarnings("unchecked")
		ArrayList<Potion> potions = (ArrayList<Potion>) myObjMethod
				.invoke(myObj);

		assertEquals(
				"The loaded potions ArrayList doesn't contain the same number of potions given in the CSV file, after FirstTask intialization",
				correctPotions.size(), potions.size());

		for (int i = 0; i < correctPotions.size(); i++) {

			assertEquals(
					"Potion name is not loaded correctly from the CSV file after FirstTask intialization",
					((Potion) correctPotions.get(i)).getName(),
					((Potion) potions.get(i)).getName());

			assertEquals(
					"Potion amount is not loaded correctly from the CSV file after FirstTask intialization",
					((Potion) correctPotions.get(i)).getAmount(),
					((Potion) potions.get(i)).getAmount());
		}
	}

	@Test(timeout = 100)
	public void testConstructorObstacleCell() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { Obstacle.class };
		testConstructorExists(ObstacleCell.class, inputs);
	}

	@Test(timeout = 100)
	public void testConstructorObstacleCellInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(
				"harrypotter.model.world.ObstacleCell").getConstructor(
				Obstacle.class);
		Object myObj = constructor.newInstance(new PhysicalObstacle(120));

		Method myObjMethod = myObj.getClass().getMethod("getObstacle");
		Obstacle obst = (Obstacle) myObjMethod.invoke(myObj);
		assertTrue(
				"The constructor of the ObstacleCell class should initialize the instance variable \"obstacle\" correctly",
				obst instanceof PhysicalObstacle
						&& ((PhysicalObstacle) obst).getHp() == 120);
	}

	@Test(timeout = 100)
	public void testConstructorPhysicalObstacle() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { int.class };
		testConstructorExists(PhysicalObstacle.class, inputs);
	}

	@Test(timeout = 100)
	public void testConstructorPhysicalObstacleInitialization()
			throws Exception {
		Constructor<?> constructor = Class.forName(
				"harrypotter.model.world.PhysicalObstacle").getConstructor(
				int.class);
		Object myObj = constructor.newInstance(50);

		Method myObjMethod = myObj.getClass().getMethod("getHp");
		int hp = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the PhyicalObstacle class should initialize the instance variable \"hp\" correctly",
				50, hp);

	}

	@Test(timeout = 100)
	public void testConstructorRavenclawWizard() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { String.class };
		testConstructorExists(RavenclawWizard.class, inputs);
	}

	@Test(timeout = 100)
	public void testConstructorRelocatingSpell() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { String.class, int.class, int.class, int.class };
		testConstructorExists(RelocatingSpell.class, inputs);
	}

	@Test(timeout = 100)
	public void testConstructorRelocatingSpellInitialization() throws Exception {
		Constructor<?> constructor = Class.forName(
				"harrypotter.model.magic.RelocatingSpell").getConstructor(
				String.class, int.class, int.class, int.class);
		Object myObj = constructor.newInstance("Amortentia", 30, 40, 50);

		Method myObjMethod = myObj.getClass().getMethod("getName");
		String name = (String) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the RelocatingSpell class should initialize the instance variable \"name\" correctly",
				"Amortentia", name);

		myObjMethod = myObj.getClass().getMethod("getCost");
		int cost = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the RelocatingSpell class should initialize the instance variable \"cost\" correctly",
				30, cost);
		myObjMethod = myObj.getClass().getMethod("getDefaultCooldown");
		int cool = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the RelocatingSpell class should initialize the instance variable \"defaultCooldown\" correctly",
				40, cool);
		myObjMethod = myObj.getClass().getMethod("getRange");
		int range = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the RelocatingSpell class should initialize the instance variable \"range\" correctly",
				50, range);
		myObjMethod = myObj.getClass().getMethod("getCoolDown");
		int coolDown = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the RelocatingSpell class should initialize the instance variable \"coolDown\" correctly",
				0, coolDown);
	}

	@SuppressWarnings("unchecked")
	@Test(timeout = 100)
	public void testConstructorSecondTaskInitialization() throws Exception {
		ArrayList<Champion> e = new ArrayList<>();
		Constructor<?> constructor = Class.forName(
				"harrypotter.model.tournament.SecondTask").getConstructor(
				e.getClass());

		GryffindorWizard g = new GryffindorWizard("gryff");
		HufflepuffWizard h = new HufflepuffWizard("huff");
		RavenclawWizard r = new RavenclawWizard("raven");
		SlytherinWizard s = new SlytherinWizard("slyth");
		e.add(g);
		e.add(h);
		e.add(r);
		e.add(s);

		boolean shuffled = true;
		int times = 0;
		for (int i = 0; i < 10; i++) {
			Object myObj = constructor.newInstance(e);
			Method myObjMethod = myObj.getClass().getMethod("getChampions");
			ArrayList<Champion> champs = (ArrayList<Champion>) myObjMethod
					.invoke(myObj);
			if (champs.get(0).equals(g) && champs.get(1).equals(h)
					&& champs.get(2).equals(r) && champs.get(3).equals(s)) {
				times++;
			}

		}
		if (times == 10)
			shuffled = false;
		assertTrue(
				"The constructor of the SecondTask class should randomly shuffle the champions list",
				shuffled);

		Object myObj = constructor.newInstance(e);

		Method myObjMethod = myObj.getClass().getMethod("getChampions");
		ArrayList<Champion> champs = (ArrayList<Champion>) myObjMethod
				.invoke(myObj);
		assertTrue(
				"The constructor of the SecondTask class should initialize the instance variable \"champions\" correctly",
				champs.contains(g) && champs.contains(h) && champs.contains(r)
						&& champs.contains(s));

		myObjMethod = myObj.getClass().getMethod("getMap");
		Cell[][] map = (Cell[][]) myObjMethod.invoke(myObj);

		assertTrue(
				"The constructor of the SecondTask class should initialize the instance variable \"map\" correctly",
				map.length == 10 && map[0].length == 10);

		myObjMethod = myObj.getClass().getMethod("getAllowedMoves");
		int allowedMoves = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the SecondTask class should initialize the instance variable \"allowedMoves\" correctly",
				1, allowedMoves);

		myObjMethod = myObj.getClass().getMethod("isTraitActivated");
		boolean traitActivated = (boolean) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the SecondTask class should initialize the instance variable \"traitActivated\" correctly",
				false, traitActivated);

		ArrayList<Potion> correctPotions = new ArrayList<Potion>();
		correctPotions.add(new Potion("Felix Felicis", 1000));
		correctPotions.add(new Potion("Pepperup Potion", 500));
		correctPotions.add(new Potion("Skele-Gro", 200));
		correctPotions.add(new Potion("Amortentia", 100));
		correctPotions.add(new Potion("Senzu", 700));
		correctPotions.add(new Potion("Thunder Bolt", 1700));

		myObjMethod = myObj.getClass().getMethod("getPotions");
		ArrayList<Potion> potions = (ArrayList<Potion>) myObjMethod
				.invoke(myObj);

		assertEquals(
				"The loaded potions ArrayList doesn't contain the same number of potions given in the CSV file, after SecondTask intialization",
				correctPotions.size(), potions.size());

		for (int i = 0; i < correctPotions.size(); i++) {

			assertEquals(
					"Potion name is not loaded correctly from the CSV file after SecondTask intialization",
					((Potion) correctPotions.get(i)).getName(),
					((Potion) potions.get(i)).getName());

			assertEquals(
					"Potion amount is not loaded correctly from the CSV file after SecondTask intialization",
					((Potion) correctPotions.get(i)).getAmount(),
					((Potion) potions.get(i)).getAmount());
		}
	}

	@Test(timeout = 100)
	public void testConstructorSlytherinWizard() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { String.class };
		testConstructorExists(SlytherinWizard.class, inputs);
	}

	@Test(timeout = 100)
	public void testConstructorSpell() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { String.class, int.class, int.class };
		testConstructorExists(Spell.class, inputs);
	}

	@Test(timeout = 100)
	public void testConstructorTask() throws Exception {
		ArrayList<Champion> e = new ArrayList<>();
		@SuppressWarnings("rawtypes")
		Class[] inputs = { e.getClass() };
		testConstructorExists(Task.class, inputs);
	}

	@Test(timeout = 100)
	public void testConstructorTournament() throws Exception {
		testConstructorExists(Tournament.class, new Class[] {});
	}

	@Test(timeout = 100)
	public void testEnumDirection() throws Exception {
		assertTrue("Direction should be an enum.", Direction.class.isEnum());
		assertNotNull("Direction can be FORWARD", Direction.valueOf("FORWARD"));
		assertNotNull("Direction can be BACKWARD",
				Direction.valueOf("BACKWARD"));
		assertNotNull("Direction can be RIGHT", Direction.valueOf("RIGHT"));
		assertNotNull("Direction can be BACKWARD", Direction.valueOf("LEFT"));
	}

	@SuppressWarnings("unchecked")
	@Test(timeout = 100)
	public void testFirstConstructorGryffindorWizardInitialization()
			throws Exception {
		Constructor<?> constructor = Class.forName(
				"harrypotter.model.character.GryffindorWizard").getConstructor(
				String.class);
		Object myObj = constructor.newInstance("Gryffindor");

		Method myObjMethod = myObj.getClass().getMethod("getName");
		String name = (String) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the GryffindorWizard class should initialize the instance variable \"name\" correctly",
				"Gryffindor", name);

		myObjMethod = myObj.getClass().getMethod("getDefaultHp");
		int defaultHp = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the GryffindorWizard class should initialize the instance variable \"defaultHp\" correctly",
				900, defaultHp);

		myObjMethod = myObj.getClass().getMethod("getDefaultIp");
		int defaultIp = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the GryffindorWizard class should initialize the instance variable \"defaultIp\" correctly",
				500, defaultIp);

		myObjMethod = myObj.getClass().getMethod("getHp");
		int hp = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the GryffindorWizard class should initialize the instance variable \"hp\" correctly",
				900, hp);

		myObjMethod = myObj.getClass().getMethod("getIp");
		int ip = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the GryffindorWizard class should initialize the instance variable \"ip\" correctly",
				500, ip);

		myObjMethod = myObj.getClass().getMethod("getSpells");
		ArrayList<Spell> spells = (ArrayList<Spell>) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the GryffindorWizard class should initialize the instance variable \"spells\" correctly",
				0, spells.size());

		myObjMethod = myObj.getClass().getMethod("getInventory");
		ArrayList<Collectible> inv = (ArrayList<Collectible>) myObjMethod
				.invoke(myObj);
		assertEquals(
				"The constructor of the GryffindorWizard class should initialize the instance variable \"inventory\" correctly",
				0, inv.size());

		myObjMethod = myObj.getClass().getMethod("getTraitCooldown");
		int traitCooldown = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the GryffindorWizard class should initialize the instance variable \"traitCooldown\" correctly",
				0, traitCooldown);

	}

	@SuppressWarnings("unchecked")
	@Test(timeout = 100)
	public void testFirstConstructorHufflepuffWizardInitialization()
			throws Exception {
		Constructor<?> constructor = Class.forName(
				"harrypotter.model.character.HufflepuffWizard").getConstructor(
				String.class);
		Object myObj = constructor.newInstance("Hufflepuff");

		Method myObjMethod = myObj.getClass().getMethod("getName");
		String name = (String) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the HufflepuffWizard class should initialize the instance variable \"name\" correctly",
				"Hufflepuff", name);

		myObjMethod = myObj.getClass().getMethod("getDefaultHp");
		int defaultHp = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the HufflepuffWizard class should initialize the instance variable \"defaultHp\" correctly",
				1000, defaultHp);

		myObjMethod = myObj.getClass().getMethod("getDefaultIp");
		int defaultIp = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the HufflepuffWizard class should initialize the instance variable \"defaultIp\" correctly",
				450, defaultIp);

		myObjMethod = myObj.getClass().getMethod("getHp");
		int hp = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the HufflepuffWizard class should initialize the instance variable \"hp\" correctly",
				1000, hp);

		myObjMethod = myObj.getClass().getMethod("getIp");
		int ip = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the HufflepuffWizard class should initialize the instance variable \"ip\" correctly",
				450, ip);

		myObjMethod = myObj.getClass().getMethod("getSpells");
		ArrayList<Spell> spells = (ArrayList<Spell>) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the HufflepuffWizard class should initialize the instance variable \"spells\" correctly",
				0, spells.size());

		myObjMethod = myObj.getClass().getMethod("getInventory");
		ArrayList<Collectible> inv = (ArrayList<Collectible>) myObjMethod
				.invoke(myObj);
		assertEquals(
				"The constructor of the HufflepuffWizard class should initialize the instance variable \"inventory\" correctly",
				0, inv.size());

		myObjMethod = myObj.getClass().getMethod("getTraitCooldown");
		int traitCooldown = (int) myObjMethod.invoke(myObj);
		assertEquals(
				"The constructor of the HufflepuffWizard class should initialize the instance variable \"traitCooldown\" correctly",
				0, traitCooldown);

	}

	@Test(timeout = 1000)
	public void testFirstTaskMapChampionCount() throws Exception {

		for (int c = 0; c < 50; c++) {

			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			FirstTask firstTask = new FirstTask(tempChampions);

			int correctChamps = 4;
			int currentChamps = 0;
			Cell[][] currentMap = firstTask.getMap();

			for (int i = 0; i < currentMap.length; i += 9) {
				for (int j = 0; j < currentMap[i].length; j += 9) {
					if (currentMap[i][j] instanceof ChampionCell)
						currentChamps++;
				}
			}

			assertEquals("Number of champions in the map is incorrect or they are not placed in the corners.",
					correctChamps, currentChamps);
		}
	}

	@Test(timeout = 1000)
	public void testFirstTaskMapChampionLocation() throws Exception {
		for (int i = 0; i < 100; i++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			FirstTask firstTask = new FirstTask(tempChampions);
			Cell[][] currentMap = firstTask.getMap();

			tempChampions = firstTask.getChampions();
			assertTrue("Corner cells should contain champions",
					currentMap[9][0] instanceof ChampionCell);

			assertTrue(
					"The first champion position is incorrect.\n"
					+ "Note: the coordinates of the first champion should match the bottom left cell of the 2D-array"
					+ " regardless of his/her coordinates in the map.",
					((Wizard) tempChampions.get(0)).getName().equals(
					((Wizard) ((ChampionCell) currentMap[9][0]).getChamp())
							.getName()));

			assertTrue("Corner cells should contain champions",
					currentMap[9][9] instanceof ChampionCell);

			assertTrue("The second champion position is incorrect.\n"
					+ "Note: the coordinates of the second champion should match the bottom right cell of the 2D-array"
					+ " regardless of his/her coordinates in the map.",
					((Wizard) tempChampions.get(1)).getName().equals(
					((Wizard) ((ChampionCell) currentMap[9][9]).getChamp())
							.getName()));

			assertTrue("Corner cells should contain champions",
					currentMap[0][9] instanceof ChampionCell);

			assertTrue("The third champion position is incorrect.\n"
					+ "Note: the coordinates of the third champion should match the top right cell of the 2D-array"
					+ " regardless of his/her coordinates in the map.",
					((Wizard) tempChampions.get(2)).getName().equals(
					((Wizard) ((ChampionCell) currentMap[0][9]).getChamp())
							.getName()));

			assertTrue("Corner cells should contain champions",
					currentMap[0][0] instanceof ChampionCell);

			assertTrue("The fourth champion position is incorrect.\n"
					+ "Note: the coordinates of the fourth champion should match the top left cell of the 2D-array"
					+ " regardless of his/her coordinates in the map.",
					((Wizard) tempChampions.get(3)).getName().equals(
					((Wizard) ((ChampionCell) currentMap[0][0]).getChamp())
							.getName()));
		}

	}

	@Test(timeout = 5000)
	public void testFirstTaskMapObstacleHPRange() throws Exception {
		ArrayList<Integer> hpValues = new ArrayList<Integer>();

		for (int c = 0; c < 10000; c++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			FirstTask firstTask = new FirstTask(tempChampions);

			Cell[][] currentMap = firstTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof ObstacleCell) {
						assertTrue(
								"Obstacle used in obstacle cell is not a physical obstacle",
								((ObstacleCell) currentMap[i][j]).getObstacle() instanceof PhysicalObstacle);
						assertTrue("Obstacle HP is not within desired range",
								((ObstacleCell) currentMap[i][j]).getObstacle()
										.getHp() >= 200
										&& ((ObstacleCell) currentMap[i][j])
												.getObstacle().getHp() <= 300);
						hpValues.add(((ObstacleCell) currentMap[i][j])
								.getObstacle().getHp());
					}
				}
			}
			
			if(c > 500 && hpValues.contains(300)
					&& hpValues.contains(200))
					break;
			
		}
		assertTrue("Obstacle HP range is incorrect", hpValues.contains(300)
				&& hpValues.contains(200));
	}

	@Test(timeout = 2000)
	public void testFirstTaskMapPotionCount() throws Exception {
		for (int c = 0; c < 50; c++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			FirstTask firstTask = new FirstTask(tempChampions);

			int correctPotions = 10;
			int currentPotions = 0;
			Cell[][] currentMap = firstTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof CollectibleCell)
						currentPotions++;
				}
			}

			assertEquals("Number of potions in the map is incorrect",
					correctPotions, currentPotions);
		}
	}

	@Test(timeout = 5000)
	public void testFirstTaskMapPotionRandomlyChosen() throws Exception {
		int potionsNumber = 6;
		int[] potionCount = new int[potionsNumber];
		int[] potionCountPrv = new int[potionsNumber];
		int[] potionCountCur = new int[potionsNumber];
		int identicalCollections = 0;

		for (int c = 0; c < 1000; c++) {
			potionCountPrv = potionCountCur;
			potionCountCur = new int[potionsNumber];
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			FirstTask firstTask = new FirstTask(tempChampions);

			ArrayList<Potion> potions = firstTask.getPotions();
			assertEquals(
					"The loaded potions ArrayList doesn't contain the same number of potions given in the CSV file",
					potionsNumber, potions.size());

			Cell[][] currentMap = firstTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof CollectibleCell) {
						for (int k = 0; k < potions.size(); k++) {
							if (((Potion) ((CollectibleCell) currentMap[i][j])
									.getCollectible()).getName().equals(
									potions.get(k).getName())) {
								potionCount[k]++;
								potionCountCur[k]++;
								break;
							}
						}
					}
				}
			}
			if (identicalArrays(potionCountCur, potionCountPrv)) {
				identicalCollections++;
			}

		}

		for (int i = 0; i < potionCount.length; i++) {
			assertTrue("Choosing potions is not properly randomized ",
					potionCount[i] > 0);
		}

		assertTrue("Choosing potions is not properly randomized ",
				identicalCollections < 50);
	}

	@Test(timeout = 100)
	public void testInstanceVariableChampionCell() throws Exception {
		testInstanceVariablesArePresent(ChampionCell.class, "champ", true);
		testInstanceVariablesArePrivate(ChampionCell.class, "champ");
	}

	@Test(timeout = 100)
	public void testInstanceVariableChampionCellGetter() throws Exception {
		testGetterMethodExistsInClass(ChampionCell.class, "getChamp",
				Champion.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableChampionCellSetter() throws Exception {
		testSetterMethodExistsInClass(ChampionCell.class, "setChamp",
				Champion.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCollectible() throws Exception {
		testInstanceVariablesArePresent(Collectible.class, "name", true);
		testInstanceVariablesArePrivate(Collectible.class, "name");
	}

	@Test(timeout = 100)
	public void testInstanceVariableCollectibleGetter() throws Exception {
		testGetterMethodExistsInClass(Collectible.class, "getName",
				String.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableCollectibleSetter() throws Exception {
		testSetterMethodExistsInClass(Collectible.class, "setName",
				String.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableHpObstacle() throws Exception {
		testInstanceVariablesArePresent(Obstacle.class, "hp", true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableHpObstacleGetter() throws Exception {
		testGetterMethodExistsInClass(Obstacle.class, "getHp", int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableHpObstacleSetter() throws Exception {
		testSetterMethodExistsInClass(Obstacle.class, "setHp", int.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableHufflepuffWizardInherited()
			throws Exception {
		testInstanceVariablesArePresent(HufflepuffWizard.class, "name", false);
		testInstanceVariablesArePresent(HufflepuffWizard.class, "defaultHp",
				false);
		testInstanceVariablesArePresent(HufflepuffWizard.class, "defaultIp",
				false);
		testInstanceVariablesArePresent(HufflepuffWizard.class, "hp", false);
		testInstanceVariablesArePresent(HufflepuffWizard.class, "ip", false);
		testInstanceVariablesArePresent(HufflepuffWizard.class, "spells", false);
		testInstanceVariablesArePresent(HufflepuffWizard.class, "inventory",
				false);
		testInstanceVariablesArePresent(HufflepuffWizard.class, "location",
				false);
		testInstanceVariablesArePresent(HufflepuffWizard.class,
				"traitCooldown", false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableObstacleCell() throws Exception {
		testInstanceVariablesArePresent(ObstacleCell.class, "obstacle", true);
		testInstanceVariablesArePrivate(ObstacleCell.class, "obstacle");
	}

	@Test(timeout = 100)
	public void testInstanceVariableObstacleGetter() throws Exception {
		testGetterMethodExistsInClass(ObstacleCell.class, "getObstacle",
				Obstacle.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableObstacleSetter() throws Exception {
		testSetterMethodExistsInClass(ObstacleCell.class, "setObstacle",
				Obstacle.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableRelocatingSpellRange() throws Exception {
		testInstanceVariablesArePresent(RelocatingSpell.class, "range", true);
		testInstanceVariablesArePrivate(RelocatingSpell.class, "range");
	}

	@Test(timeout = 100)
	public void testInstanceVariableRelocatingSpellRangeGetter()
			throws Exception {
		testGetterMethodExistsInClass(RelocatingSpell.class, "getRange",
				int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableRelocatingSpellRangeSetter()
			throws Exception {
		testSetterMethodExistsInClass(RelocatingSpell.class, "setRange",
				int.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariablesGryffindorWizardInherited()
			throws Exception {
		testInstanceVariablesArePresent(GryffindorWizard.class, "name", false);
		testInstanceVariablesArePresent(GryffindorWizard.class, "defaultHp",
				false);
		testInstanceVariablesArePresent(GryffindorWizard.class, "defaultIp",
				false);
		testInstanceVariablesArePresent(GryffindorWizard.class, "hp", false);
		testInstanceVariablesArePresent(GryffindorWizard.class, "ip", false);
		testInstanceVariablesArePresent(GryffindorWizard.class, "spells", false);
		testInstanceVariablesArePresent(GryffindorWizard.class, "inventory",
				false);
		testInstanceVariablesArePresent(GryffindorWizard.class, "location",
				false);
		testInstanceVariablesArePresent(GryffindorWizard.class,
				"traitCooldown", false);

	}

	@Test(timeout = 100)
	public void testInstanceVariableSlytherinWizardTraitDiretion()
			throws Exception {
		testInstanceVariablesArePresent(SlytherinWizard.class,
				"traitDirection", true);
		testInstanceVariablesArePrivate(SlytherinWizard.class, "traitDirection");
	}

	@Test(timeout = 100)
	public void testInstanceVariableSlytherinWizardTraitDiretionGetter()
			throws Exception {
		testGetterMethodExistsInClass(SlytherinWizard.class,
				"getTraitDirection", Direction.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableSpellCoolDownGetter() throws Exception {
		testGetterMethodExistsInClass(Spell.class, "getCoolDown", int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableSpellCoolDownSetter() throws Exception {
		testSetterMethodExistsInClass(Spell.class, "setCoolDown", int.class,
				true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableSpellCost() throws Exception {
		testInstanceVariablesArePresent(Spell.class, "cost", true);
		testInstanceVariablesArePrivate(Spell.class, "cost");
	}

	@Test(timeout = 100)
	public void testInstanceVariableSpellDefaultCooldownGetter()
			throws Exception {
		testGetterMethodExistsInClass(Spell.class, "getDefaultCooldown",
				int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableSpellDefaultCooldownSetter()
			throws Exception {
		testSetterMethodExistsInClass(Spell.class, "setDefaultCooldown",
				int.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableSpellName() throws Exception {
		testInstanceVariablesArePresent(Spell.class, "name", true);
		testInstanceVariablesArePrivate(Spell.class, "name");
	}

	@Test(timeout = 100)
	public void testInstanceVariablesRelocatingSpellInherited() throws Exception{
		testInstanceVariablesArePresent(RelocatingSpell.class, "name", false);
		testInstanceVariablesArePresent(RelocatingSpell.class, "cost", false);
		testInstanceVariablesArePresent(RelocatingSpell.class, "coolDown",
				false);
		testInstanceVariablesArePresent(RelocatingSpell.class,
				"defultCooldown", false);

	}

	@Test(timeout = 100)
	public void testInstanceVariablesThirdTaskInherited() throws Exception{
		testInstanceVariablesArePresent(ThirdTask.class, "champions", false);
		testInstanceVariablesArePresent(ThirdTask.class, "currentChamp", false);
		testInstanceVariablesArePresent(ThirdTask.class, "map", false);
		testInstanceVariablesArePresent(ThirdTask.class, "allowedMoves", false);
		testInstanceVariablesArePresent(ThirdTask.class, "traitActivated",
				false);
		testInstanceVariablesArePresent(ThirdTask.class, "potions", false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTaskAllowedMoves() throws Exception {
		testInstanceVariablesArePresent(Task.class, "allowedMoves", true);
		testInstanceVariablesArePrivate(Task.class, "allowedMoves");
	}

	@Test(timeout = 100)
	public void testInstanceVariableTaskAllowedMovesGetter() throws Exception {
		testGetterMethodExistsInClass(Task.class, "getAllowedMoves", int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTaskAllowedMovesSetter() throws Exception {
		testSetterMethodExistsInClass(Task.class, "setAllowedMoves", int.class,
				true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTaskChampions() throws Exception {
		testInstanceVariablesArePresent(Task.class, "champions", true);
		testInstanceVariablesArePrivate(Task.class, "champions");
	}

	@Test(timeout = 100)
	public void testInstanceVariableTaskChampionsGetter() throws Exception {
		ArrayList<Champion> q = new ArrayList<>();
		testGetterMethodExistsInClass(Task.class, "getChampions", q.getClass());
	}

	@Test(timeout = 100)
	public void testInstanceVariableTaskChampionsSetter() throws Exception {
		ArrayList<Champion> q = new ArrayList<>();
		testSetterMethodExistsInClass(Task.class, "setChampions", q.getClass(),
				false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTaskCurrentChamp() throws Exception {
		testInstanceVariablesArePresent(Task.class, "currentChamp", true);
		testInstanceVariablesArePrivate(Task.class, "currentChamp");
	}

	@Test(timeout = 100)
	public void testInstanceVariableTaskCurrentChampGetter() throws Exception {
		testGetterMethodExistsInClass(Task.class, "getCurrentChamp",
				Champion.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTaskCurrentChampSetter() throws Exception {
		testSetterMethodExistsInClass(Task.class, "setCurrentChamp",
				Champion.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTaskMap() throws Exception {
		testInstanceVariablesArePresent(Task.class, "map", true);
		testInstanceVariablesArePrivate(Task.class, "map");
	}

	@Test(timeout = 100)
	public void testInstanceVariableTaskMapGetter() throws Exception {
		testGetterMethodExistsInClass(Task.class, "getMap", Cell[][].class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTaskMapSetter() throws Exception {
		testSetterMethodExistsInClass(Task.class, "setMap", Cell[][].class,
				false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTaskPotions() throws Exception {
		testInstanceVariablesArePresent(Task.class, "potions", true);
		testInstanceVariablesArePrivate(Task.class, "potions");
	}

	@Test(timeout = 100)
	public void testInstanceVariableTaskPotionsSetter() throws Exception {
		ArrayList<Potion> e = new ArrayList<>();

		testSetterMethodExistsInClass(Task.class, "setPotions", e.getClass(),
				false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTaskTraitActivated() throws Exception {
		testInstanceVariablesArePresent(Task.class, "traitActivated", true);
		testInstanceVariablesArePrivate(Task.class, "traitActivated");
	}

	@Test(timeout = 100)
	public void testInstanceVariableTaskTraitActivatedGetter() throws Exception {
		testGetterMethodExistsInClass(Task.class, "isTraitActivated",
				boolean.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTaskTraitActivatedSetter() throws Exception {
		testSetterMethodExistsInClass(Task.class, "setTraitActivated",
				boolean.class, true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTaskTraitPotionsGetter() throws Exception {
		ArrayList<Potion> e = new ArrayList<>();
		testGetterMethodExistsInClass(Task.class, "getPotions", e.getClass());
	}

	@Test(timeout = 100)
	public void testInstanceVariableTournamentChampions() throws Exception {
		testInstanceVariablesArePresent(Tournament.class, "champions", true);
		testInstanceVariablesArePrivate(Tournament.class, "champions");
	}

	@Test(timeout = 100)
	public void testInstanceVariableTournamentChampionsGetter()
			throws Exception {
		ArrayList<Champion> e = new ArrayList<>();
		testGetterMethodExistsInClass(Tournament.class, "getChampions",
				e.getClass());
	}

	@Test(timeout = 100)
	public void testInstanceVariableTournamentChampionsSetter()
			throws Exception {
		ArrayList<Champion> e = new ArrayList<>();
		testSetterMethodExistsInClass(Tournament.class, "setChampions",
				e.getClass(), false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableTournamentSpells() throws Exception {
		testInstanceVariablesArePresent(Tournament.class, "spells", true);
		testInstanceVariablesArePrivate(Tournament.class, "spells");
	}

	@Test(timeout = 100)
	public void testInstanceVariableTournamentSpellsGetter() throws Exception {
		ArrayList<Spell> e = new ArrayList<>();
		testGetterMethodExistsInClass(Tournament.class, "getSpells",
				e.getClass());
	}

	@Test(timeout = 100)
	public void testInstanceVariableTournamentSpellsSetter() throws Exception {
		ArrayList<Spell> e = new ArrayList<>();
		testSetterMethodExistsInClass(Tournament.class, "setSpells",
				e.getClass(), false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardDefaultHp() throws Exception {
		testInstanceVariablesArePresent(Wizard.class, "defaultHp", true);
		testInstanceVariablesArePrivate(Wizard.class, "defaultHp");
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardDefaultIp() throws Exception {
		testInstanceVariablesArePresent(Wizard.class, "defaultIp", true);
		testInstanceVariablesArePrivate(Wizard.class, "defaultIp");
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardHp() throws Exception {
		testInstanceVariablesArePresent(Wizard.class, "hp", true);
		testInstanceVariablesArePrivate(Wizard.class, "hp");
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardInventoryGetter() throws Exception {
		testGetterMethodExistsInClass(Wizard.class, "getInventory",
				ArrayList.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardInventorySetter() throws Exception {
		testSetterMethodExistsInClass(Wizard.class, "setInventory",
				ArrayList.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardIp() throws Exception {
		testInstanceVariablesArePresent(Wizard.class, "ip", true);
		testInstanceVariablesArePrivate(Wizard.class, "ip");
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardLocationGetter() throws Exception {
		testGetterMethodExistsInClass(Wizard.class, "getLocation", Point.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardLocationSetter() throws Exception {
		testSetterMethodExistsInClass(Wizard.class, "setLocation", Point.class,
				true);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardName() throws Exception {
		testInstanceVariablesArePresent(Wizard.class, "name", true);
		testInstanceVariablesArePrivate(Wizard.class, "name");
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardSpellsGetter() throws Exception {
		testGetterMethodExistsInClass(Wizard.class, "getSpells",
				ArrayList.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardSpellsSetter() throws Exception {
		testSetterMethodExistsInClass(Wizard.class, "setSpells",
				ArrayList.class, false);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardTraitCooldownGetter()
			throws Exception {
		testGetterMethodExistsInClass(Wizard.class, "getTraitCooldown",
				int.class);
	}

	@Test(timeout = 100)
	public void testInstanceVariableWizardTraitCooldownSetter()
			throws Exception {
		testSetterMethodExistsInClass(Wizard.class, "setTraitCooldown",
				int.class, true);
	}

	@Test(timeout = 1000)
	public void testLoadedPotions() throws Exception {

		GryffindorWizard c1 = new GryffindorWizard("1");
		HufflepuffWizard c2 = new HufflepuffWizard("2");
		RavenclawWizard c3 = new RavenclawWizard("3");
		SlytherinWizard c4 = new SlytherinWizard("4");
		ArrayList<Champion> tempChampions = new ArrayList<Champion>();
		tempChampions.add(c1);
		tempChampions.add(c2);
		tempChampions.add(c3);
		tempChampions.add(c4);
		ArrayList<Potion> correctPotions = new ArrayList<Potion>();
		correctPotions.add(new Potion("Felix Felicis", 1000));
		correctPotions.add(new Potion("Pepperup Potion", 500));
		correctPotions.add(new Potion("Skele-Gro", 200));
		correctPotions.add(new Potion("Amortentia", 100));
		correctPotions.add(new Potion("Senzu", 700));
		correctPotions.add(new Potion("Thunder Bolt", 1700));

		FirstTask firstTask = new FirstTask(tempChampions);

		ArrayList<Potion> testPotions = firstTask.getPotions();

		assertEquals(
				"The loaded potions ArrayList doesn't contain the same number of potions given in the CSV file",
				correctPotions.size(), testPotions.size());

		for (int i = 0; i < correctPotions.size(); i++) {

			assertEquals(
					"Potion name is not loaded correctly from the CSV file",
					((Potion) correctPotions.get(i)).getName(),
					((Potion) testPotions.get(i)).getName());

			assertEquals(
					"Potion amount is not loaded correctly from the CSV file",
					((Potion) correctPotions.get(i)).getAmount(),
					((Potion) testPotions.get(i)).getAmount());
		}
	}

	@Test(timeout = 1000)
	public void testLoadSpellsMethod() throws Exception {

		PrintWriter spellsWriter = new PrintWriter("SpellsTest.csv");
		spellsWriter.println("DMG,A,1500,30,6");
		spellsWriter.println("REL,B,3000,5,6");
		spellsWriter.println("REL,C,1000,1,2");
		spellsWriter.println("HEL,D,3000,10,2");
		spellsWriter.println("DMG,F,3000,30,3");
		spellsWriter.println("HEL,E,500,10,3");

		spellsWriter.close();
		Tournament t = null;
		try{
			t = new Tournament();
		}catch(Exception e){
			fail("Failed to create a new instance of the Tournment class.\nAn Exception was raised: "+e.toString());
		}
		t.getSpells().clear();

		Method method = Tournament.class.getDeclaredMethod("loadSpells",
				new Class[] { String.class });
		method.setAccessible(true);
		method.invoke(t, "SpellsTest.csv");
		ArrayList<Spell> correctSpells = new ArrayList<Spell>();
		correctSpells.add(new DamagingSpell("A", 1500, 6, 30));
		correctSpells.add(new RelocatingSpell("B", 3000, 6, 5));
		correctSpells.add(new RelocatingSpell("C", 1000, 2, 1));
		correctSpells.add(new HealingSpell("D", 3000, 2, 10));
		correctSpells.add(new DamagingSpell("F", 3000, 3, 30));
		correctSpells.add(new HealingSpell("E", 500, 3, 10));
		ArrayList<Spell> testSpells = t.getSpells();

		assertEquals(
				"The loaded spells ArrayList doesn't contain the same number of spells given in the CSV file",
				correctSpells.size(), testSpells.size());

		for (int i = 0; i < correctSpells.size(); i++) {
			assertTrue(
					"The loaded spells ArrayList doesn't contain the same number of "
							+ correctSpells
									.get(i)
									.getClass()
									.getName()
									.toLowerCase()
									.substring(
											0,
											correctSpells.get(i).getClass()
													.getName().length() - 5)
							+ " spells given in the CSV file",
					correctSpells.get(i).getClass().getName()
							.equals(testSpells.get(i).getClass().getName()));

			assertEquals(
					"Spell name is not loaded correctly from the CSV file",
					((Spell) correctSpells.get(i)).getName(),
					((Spell) testSpells.get(i)).getName());

			assertEquals(
					"Spell cost is not loaded correctly from the CSV file",
					((Spell) correctSpells.get(i)).getCost(),
					((Spell) testSpells.get(i)).getCost());

			assertEquals(
					"Spell cooldown is not loaded correctly from the CSV file",
					((Spell) correctSpells.get(i)).getCoolDown(),
					((Spell) testSpells.get(i)).getCoolDown());

			if ((correctSpells.get(i)) instanceof DamagingSpell)
				assertEquals(
						"Spell damage amount is not loaded correctly from the CSV file",
						((DamagingSpell) correctSpells.get(i))
								.getDamageAmount(), ((DamagingSpell) testSpells
								.get(i)).getDamageAmount());

			if ((correctSpells.get(i)) instanceof HealingSpell)
				assertEquals(
						"Spell healing amount is not loaded correctly from the CSV file",
						((HealingSpell) correctSpells.get(i))
								.getHealingAmount(), ((HealingSpell) testSpells
								.get(i)).getHealingAmount());

			if ((correctSpells.get(i)) instanceof RelocatingSpell)
				assertEquals(
						"Spell range is not loaded correctly from the CSV file",
						((RelocatingSpell) correctSpells.get(i)).getRange(),
						((RelocatingSpell) testSpells.get(i)).getRange());

		}
	}

	@Test(timeout = 100)
	public void testMethodInInterfaceChampion() {
		testIsInterface(Champion.class);
	}

	@Test(timeout = 100)
	public void testMethodInInterfaceMethodUseTrait() {
		testMethodExistsInClass(Champion.class, "useTrait", true, Void.TYPE);
	}

	@Test(timeout = 100)
	public void testMethodInRavenclawWizardTrait() {
		testMethodExistsInClass(RavenclawWizard.class, "useTrait", true,
				Void.TYPE);
	}

	@Test(timeout = 100)
	public void testMethodInSlytherinWizardTrait() {
		testMethodExistsInClass(SlytherinWizard.class, "useTrait", true,
				Void.TYPE);
	}

	@Test(timeout = 100)
	public void testMethodLoadPotionsInTask() throws Exception {
		testMethodExistsInClass(Task.class, "loadPotions", true, Void.TYPE,
				String.class);
		testMethodIsPrivate(Task.class, "loadPotions", Void.TYPE, String.class);
	}

	@Test(timeout = 100)
	public void testMethodLoadSpellsInTournament() throws Exception {
		testMethodExistsInClass(Tournament.class, "loadSpells", true,
				Void.TYPE, String.class);
		testMethodIsPrivate(Tournament.class, "loadSpells", Void.TYPE,
				String.class);
	}

	@Test(timeout = 100)
	public void testMethodreadMapInThirdTask() throws Exception {
		testMethodExistsInClass(ThirdTask.class, "readMap", true, Void.TYPE,
				String.class);
		testMethodIsPrivate(ThirdTask.class, "readMap", Void.TYPE, String.class);
	}

	@Test(timeout = 100)
	public void testSecondConstructorWizard() throws Exception {
		@SuppressWarnings("rawtypes")
		Class[] inputs = { String.class, int.class, int.class };
		testConstructorExists(Wizard.class, inputs);
	}

	@Test(timeout = 5000)
	public void testSecondTaskMapMerpersonDmgHpRandom() throws Exception {
		ArrayList<Integer> hpValues = new ArrayList<Integer>();
		ArrayList<Integer> dmgValues = new ArrayList<Integer>();

		for (int c = 0; c < 1000; c++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			SecondTask secondTask = new SecondTask(tempChampions);

			Cell[][] currentMap = secondTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof ObstacleCell) {
						assertTrue(
								"Obstacle used in obstacle cell is not a Merperson",
								((ObstacleCell) currentMap[i][j]).getObstacle() instanceof Merperson);

						assertTrue("Merperson HP is not within desired range",
								((ObstacleCell) currentMap[i][j]).getObstacle()
										.getHp() >= 200
										&& ((ObstacleCell) currentMap[i][j])
												.getObstacle().getHp() <= 300);

						if (!hpValues
								.contains(((ObstacleCell) currentMap[i][j])
										.getObstacle().getHp()))
							hpValues.add(((ObstacleCell) currentMap[i][j])
									.getObstacle().getHp());

						assertTrue(
								"Merperson damage is not within desired range",
								((Merperson) ((ObstacleCell) currentMap[i][j])
										.getObstacle()).getDamage() >= 100
										&& ((Merperson) ((ObstacleCell) currentMap[i][j])
												.getObstacle()).getDamage() <= 300);

						if (!dmgValues
								.contains(((Merperson) ((ObstacleCell) currentMap[i][j])
										.getObstacle()).getDamage()))
							dmgValues
									.add(((Merperson) ((ObstacleCell) currentMap[i][j])
											.getObstacle()).getDamage());

					}
				}
			}
			
			if(c > 100 && hpValues.size() > 10 && dmgValues.size() > 20)
				break;
			
		}
		assertTrue("Obstacle HP randomization is not done correctly",
				hpValues.size() > 10);

		assertTrue("Obstacle damage randomization is not done correctly",
				dmgValues.size() > 20);

	}

	@Test(timeout = 2000)
	public void testSecondTaskMapObstacleCount() throws Exception {

		for (int c = 0; c < 100; c++) {

			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			SecondTask secondTask = new SecondTask(tempChampions);

			int correctObstacles = 40;
			int currentObstacles = 0;
			Cell[][] currentMap = secondTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof ObstacleCell)
						currentObstacles++;
				}
			}

			assertEquals("Number of obstacles in the map is incorrect",
					correctObstacles, currentObstacles);
		}
	}

	@Test(timeout = 5000)
	public void testSecondTaskMapPotionRandomlyChosen() throws Exception {
		int potionsNumber = 6;
		int[] potionCount = new int[potionsNumber];
		int[] potionCountPrv = new int[potionsNumber];
		int[] potionCountCur = new int[potionsNumber];
		int identicalCollections = 0;

		for (int c = 0; c < 1000; c++) {
			potionCountPrv = potionCountCur;
			potionCountCur = new int[potionsNumber];

			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			SecondTask secondTask = new SecondTask(tempChampions);

			ArrayList<Potion> potions = secondTask.getPotions();
			assertEquals(
					"The loaded potions ArrayList doesn't contain the same number of potions given in the CSV file",
					potionsNumber, potions.size());

			Cell[][] currentMap = secondTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof CollectibleCell) {
						for (int k = 0; k < potions.size(); k++) {
							if (((Potion) ((CollectibleCell) currentMap[i][j])
									.getCollectible()).getName().equals(
									potions.get(k).getName())) {
								potionCount[k]++;
								potionCountCur[k]++;
								break;
							}
						}
					}
				}
			}

			if (identicalArrays(potionCountCur, potionCountPrv)) {
				identicalCollections++;
			}

		}

		for (int i = 0; i < potionCount.length; i++) {
			assertTrue(" Choosing potions is not properly randomized ",
					potionCount[i] > 0);
		}

		assertTrue("Choosing potions is not properly randomized ",
				identicalCollections < 50);
	}

	@Test(timeout = 2000)
	public void testSecondTaskMapTreasureCorrectlyAssigned() throws Exception {

		for (int c = 0; c < 100; c++) {

			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);

			switch (c / 25) {
			case 0:
				break;
			case 1:
				tempChampions.remove(0);
				break;
			case 2:
				tempChampions.remove(0);
				tempChampions.remove(0);
				break;
			case 3:
				tempChampions.remove(0);
				tempChampions.remove(0);
				tempChampions.remove(0);
				break;
			}

			SecondTask secondTask = new SecondTask(tempChampions);
			tempChampions = secondTask.getChampions();

			int correctTreasures = tempChampions.size();
			Cell[][] currentMap = secondTask.getMap();
			int[] assignedTreasures = new int[tempChampions.size()];
			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof TreasureCell) {
						for (int k = 0; k < tempChampions.size(); k++) {
							if (tempChampions.get(k) == ((TreasureCell) currentMap[i][j])
									.getOwner()) {
								assignedTreasures[k] = 1;
							}
						}

					}
				}
			}

			assertEquals(
					"Number of correctly assigned treasures to their corresponding champion in the map is incorrect",
					correctTreasures, sumArray(assignedTreasures));
		}
	}

	@Test(timeout = 2000)
	public void testSecondTaskMapTreasureCount() throws Exception {

		for (int c = 0; c < 100; c++) {

			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);

			switch (c / 25) {
			case 0:
				break;
			case 1:
				tempChampions.remove(0);
				break;
			case 2:
				tempChampions.remove(0);
				tempChampions.remove(0);
				break;
			case 3:
				tempChampions.remove(0);
				tempChampions.remove(0);
				tempChampions.remove(0);
				break;
			}

			SecondTask secondTask = new SecondTask(tempChampions);
			tempChampions = secondTask.getChampions();

			int correctTreasures = tempChampions.size();
			int currentTreasures = 0;
			Cell[][] currentMap = secondTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof TreasureCell)
						currentTreasures++;
				}
			}

			assertEquals("Number of treasures in the map is incorrect",
					correctTreasures, currentTreasures);
		}
	}

	@Test(timeout = 5000)
	public void testSecondTaskMerpersonDmgHpRange() throws Exception {
		ArrayList<Integer> hpValues = new ArrayList<Integer>();
		ArrayList<Integer> dmgValues = new ArrayList<Integer>();

		for (int c = 0; c < 10000; c++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			SecondTask secondTask = new SecondTask(tempChampions);

			Cell[][] currentMap = secondTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof ObstacleCell) {
						assertTrue(
								"Obstacle used in obstacle cell is not a Merperson",
								((ObstacleCell) currentMap[i][j]).getObstacle() instanceof Merperson);
						assertTrue("Merperson HP is not within desired range",
								((ObstacleCell) currentMap[i][j]).getObstacle()
										.getHp() >= 200
										&& ((ObstacleCell) currentMap[i][j])
												.getObstacle().getHp() <= 300);
						hpValues.add(((ObstacleCell) currentMap[i][j])
								.getObstacle().getHp());

						assertTrue(
								"Merperson damage is not within desired range",
								((Merperson) ((ObstacleCell) currentMap[i][j])
										.getObstacle()).getDamage() >= 100
										&& ((Merperson) ((ObstacleCell) currentMap[i][j])
												.getObstacle()).getDamage() <= 300);
						dmgValues
								.add(((Merperson) ((ObstacleCell) currentMap[i][j])
										.getObstacle()).getDamage());
					}
				}
			}
			
			if(c > 1000 && (hpValues.contains(300)
					&& hpValues.contains(200))
					&& (dmgValues.contains(300) 
					&& dmgValues.contains(100)))
					break;
			
		}
		assertTrue("Merperson HP range is incorrect", hpValues.contains(300)
				&& hpValues.contains(200));
		assertTrue("Merperson damage range is incorrect",
				dmgValues.contains(300) && dmgValues.contains(100));
	}

	@Test(timeout = 2000)
	public void testThirdTaskMapPotionCount() throws Exception {
		for (int c = 0; c < 100; c++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			ThirdTask thirdTask = new ThirdTask(tempChampions);

			int correctPotions = 10;
			int currentPotions = 0;
			Cell[][] currentMap = thirdTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof CollectibleCell)
						currentPotions++;
				}
			}

			assertEquals("Number of potions in the map is incorrect",
					correctPotions, currentPotions);
		}
	}

	@Test(timeout = 5000)
	public void testThirdTaskMapPotionRandomlyChosen() throws Exception {
		int potionsNumber = 6;
		int[] potionCount = new int[potionsNumber];
		int[] potionCountPrv = new int[potionsNumber];
		int[] potionCountCur = new int[potionsNumber];
		int identicalCollections = 0;

		for (int c = 0; c < 1000; c++) {
			potionCountPrv = potionCountCur;
			potionCountCur = new int[potionsNumber];

			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			ThirdTask thirdTask = new ThirdTask(tempChampions);

			ArrayList<Potion> potions = thirdTask.getPotions();
			assertEquals(
					"The loaded potions ArrayList doesn't contain the same number of potions given in the CSV file",
					potionsNumber, potions.size());

			Cell[][] currentMap = thirdTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (currentMap[i][j] instanceof CollectibleCell) {
						for (int k = 0; k < potions.size(); k++) {
							if (((Potion) ((CollectibleCell) currentMap[i][j])
									.getCollectible()).getName().equals(
									potions.get(k).getName())) {
								potionCount[k]++;
								potionCountCur[k]++;
								break;
							}
						}
					}
				}
			}

			if (identicalArrays(potionCountCur, potionCountPrv)) {
				identicalCollections++;
			}

		}

		for (int i = 0; i < potionCount.length; i++) {
			assertTrue("Choosing potions is not properly randomized ",
					potionCount[i] > 0);
		}

		assertTrue("Choosing potions is not properly randomized ",
				identicalCollections < 50);
	}

	@Test(timeout = 2000)
	public void testThirdTaskReadMap() throws Exception {

		for (int c = 0; c < 20; c++) {
			GryffindorWizard c1 = new GryffindorWizard("1");
			HufflepuffWizard c2 = new HufflepuffWizard("2");
			RavenclawWizard c3 = new RavenclawWizard("3");
			SlytherinWizard c4 = new SlytherinWizard("4");
			ArrayList<Champion> tempChampions = new ArrayList<Champion>();
			tempChampions.add(c1);
			tempChampions.add(c2);
			tempChampions.add(c3);
			tempChampions.add(c4);
			switch (c / 5) {
			case 0:
				break;
			case 1:
				tempChampions.remove(0);
				break;
			case 2:
				tempChampions.remove(0);
				tempChampions.remove(0);
				break;
			case 3:
				tempChampions.remove(0);
				tempChampions.remove(0);
				tempChampions.remove(0);
				break;
			}
			Collections.shuffle(tempChampions);
			ThirdTask thirdTask = new ThirdTask(tempChampions);
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++)
					thirdTask.getMap()[i][j] = new EmptyCell();
			}
			int[][] generatedMap = generateRandomMap(4, 20, 10);
			String[] map = convertMapToString(generatedMap).split("\n");
			PrintWriter mapWriter = new PrintWriter("mapTest.csv");
			for (int i = 0; i < 10; i++)
				mapWriter.println(map[i]);
			mapWriter.close();

			Method method = ThirdTask.class.getDeclaredMethod("readMap",
					new Class[] { String.class });
			method.setAccessible(true);
			method.invoke(thirdTask, "mapTest.csv");
			Cell[][] currentMap = thirdTask.getMap();

			for (int i = 0; i < currentMap.length; i++) {
				for (int j = 0; j < currentMap[i].length; j++) {
					if (generatedMap[i][j] == 0)
						assertTrue(
								"Map is not correctly loaded from the CSV file. Cell should be empty.",
								currentMap[i][j] instanceof EmptyCell);
					if (generatedMap[i][j] >= 1 && generatedMap[i][j] <= 4) {

						if (tempChampions.size() >= generatedMap[i][j])
							assertTrue(
									"Map is not correctly loaded from the CSV file. Cell should contain a champion.",
									currentMap[i][j] instanceof ChampionCell
											&& ((Wizard) ((ChampionCell) currentMap[i][j])
													.getChamp())
													.getName()
													.equals(((Wizard) tempChampions
															.get(generatedMap[i][j] - 1))
															.getName()));
						else
							assertTrue(
									"Map is not correctly loaded from the CSV file. Cell should be empty.",
									currentMap[i][j] instanceof EmptyCell);
					}
					if (generatedMap[i][j] == 5)
						assertTrue(
								"Map is not correctly loaded from the CSV file. Cell should contain a wall.",
								currentMap[i][j] instanceof WallCell);
					if (generatedMap[i][j] == 6)
						assertTrue(
								"Map is not correctly loaded from the CSV file. Cell should contain an obstacle.",
								currentMap[i][j] instanceof ObstacleCell);
					if (generatedMap[i][j] == 7)
						assertTrue(
								"Map is not correctly loaded from the CSV file. Cell should contain the cup.",
								currentMap[i][j] instanceof CupCell);

				}
			}
		}

	}

	// ############################################# Helper methods ############################################# //
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

	private static boolean containsMethodName(Method[] methods, String name) {
		for (Method method : methods) {
			if (method.getName().equals(name))
				return true;
		}
		return false;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void testConstructorExists(Class aClass, Class[] inputs) {
		boolean thrown = false;
		try {
			aClass.getConstructor(inputs);
		} catch (NoSuchMethodException e) {
			thrown = true;
		}

		if (inputs.length > 0) {
			String msg = "";
			int i = 0;
			do {
				msg += inputs[i].getSimpleName() + " and ";
				i++;
			} while (i < inputs.length);

			msg = msg.substring(0, msg.length() - 4);

			assertFalse(
					"Missing constructor with " + msg + " parameter"
							+ (inputs.length > 1 ? "s" : "") + " in "
							+ aClass.getName() + " class.",

					thrown);
		} else
			assertFalse(
					"Missing constructor with zero parameters in "
							+ aClass.getName() + " class.",

					thrown);

	}

	@SuppressWarnings("rawtypes")
	private void testClassIsAbstract(Class aClass) {
		assertTrue("You should not be able to create new instances from "
				+ aClass.getName() + " class.",
				Modifier.isAbstract(aClass.getModifiers()));
	}

	private void testIsInterface(Class<Champion> aClass) {
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
	private void testClassIsSubClass(Class subClass, Class superClass) {
		assertEquals(subClass.getName() + " class should inherit from "
				+ superClass.getName() + ".", superClass,
				subClass.getSuperclass());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void testMethodIsPrivate(Class aClass, String methodName,
			Class returnType, Class... inputTypes) {
		Method m = null;
		try {
			m = aClass.getDeclaredMethod(methodName, inputTypes);
			assertTrue(methodName + " method in class " + aClass.getName()
					+ " should not be accessed outside that class",
					2 == m.getModifiers());

		} catch (NoSuchMethodException e) {

		}

	}

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

	public boolean identicalArrays(int[] a, int[] b) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i])
				return false;
		}
		return true;
	}

	public int[][] generateRandomMap(int championNumber, int obstacleNumber,
			int wallNumber) {
		int[][] tempMap = new int[10][10];
		int count = 0;
		while (count < championNumber) {
			int randomX = (int) (Math.random() * 10);
			int randomY = (int) (Math.random() * 10);
			if (tempMap[randomX][randomY] == 0) {
				tempMap[randomX][randomY] = count + 1;
				count++;
			}
		}
		count = 0;
		while (count < obstacleNumber) {
			int randomX = (int) (Math.random() * 10);
			int randomY = (int) (Math.random() * 10);
			if (tempMap[randomX][randomY] == 0) {
				tempMap[randomX][randomY] = 6;
				count++;
			}
		}
		count = 0;
		while (count < wallNumber) {
			int randomX = (int) (Math.random() * 10);
			int randomY = (int) (Math.random() * 10);
			if (tempMap[randomX][randomY] == 0) {
				tempMap[randomX][randomY] = 5;
				count++;
			}
		}
		count = 0;
		while (count < 1) {
			int randomX = (int) (Math.random() * 10);
			int randomY = (int) (Math.random() * 10);
			if (tempMap[randomX][randomY] == 0) {
				tempMap[randomX][randomY] = 7;
				count++;
			}
		}
		return tempMap;
	}

	public String convertMapToString(int[][] map) {
		String result = "";
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				result += map[i][j];
				if (j != 9)
					result += ",";
			}
			if (i != 9)
				result += "\n";
		}
		return result;
	}

}
