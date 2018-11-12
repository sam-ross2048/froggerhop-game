import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * 
 * @author SamRoss The game world containing all sprites and the update of all
 *         sprites.
 */

public class World {

	private Character player;
	private ExtraLife extraLife;
	private final float[] frogStartPosition = { 512, 720 };
	private final float[] lifeStart = { 24, 744 };
	private final static int NUM_LIVES = 3;
	private final static int NUM_FINISH_SPOTS = 5;
	private final static int TILE_SIZE = 48;
	private final static int LIFE_SIZE = 24;
	private final int distanceBetweenLives = 32;
	private int levelCount = 0;

	private final int finishesStartPosition[] = {120, 48};
	private final int lengthLongObjects[] = { 132, 228, 144 };
	private final float speedVehicles[] = { 0.15f, 0.5f, 0.2f, 0.05f, 0.1f, 0.07f, 0.085f };

	// Arrays containing all sprites that are not the character or extra life.

	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private ArrayList<FinishFrog> finishes = new ArrayList<FinishFrog>();
	private ArrayList<Life> lives = new ArrayList<Life>();
	private ArrayList<GeneralLog> logs = new ArrayList<GeneralLog>();

	/**
	 * Initialises the game "world".
	 */
	public World() throws SlickException {
		// Perform initialisation logic

		// read CSV file containing data about (some) sprites.
		readCSV();

		// Instantiate the frog and extra life sprites.
		player = new Character(frogStartPosition[0], frogStartPosition[1], 48, lives);
		extraLife = new ExtraLife(0, 0, TILE_SIZE, false, logs);
		float finishX = finishesStartPosition[0], finishY = finishesStartPosition[1];
		for (int i = 0; i < NUM_FINISH_SPOTS; i++) {
			finishes.add(new FinishFrog(finishX, finishY, 2 * TILE_SIZE, false));
			finishX += 4 * TILE_SIZE;
		}

		float addition = 0;
		for (int i = 0; i < NUM_LIVES; i++) {
			lives.add(new Life(lifeStart[0] + addition, lifeStart[1], LIFE_SIZE, false));
			addition += distanceBetweenLives;
		}

	}

	/**
	 * Reads in the majority of sprites from a CSV file and adds to relevant arrays.
	 */
	private void readCSV() {
		String filename = "assets/levels/" + levelCount + ".lvl";
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String info;
			while ((info = br.readLine()) != null) {
				String cells[] = info.split(",");
				switch (cells[0]) {
				case "water":
					sprites.add(new WaterTile(Float.parseFloat(cells[1]), Float.parseFloat(cells[2]), TILE_SIZE));
					break;
				case "grass":
					sprites.add(new GrassTile(Float.parseFloat(cells[1]), Float.parseFloat(cells[2]), TILE_SIZE));
					break;
				case "tree":
					sprites.add(new Tree(Float.parseFloat(cells[1]), Float.parseFloat(cells[2]), TILE_SIZE));
					break;
				case "bus":
					sprites.add(new Bus(Float.parseFloat(cells[1]), Float.parseFloat(cells[2]),
							Boolean.parseBoolean(cells[3]), speedVehicles[0], TILE_SIZE));
					break;
				case "racecar":
					sprites.add(new Racecar(Float.parseFloat(cells[1]), Float.parseFloat(cells[2]),
							Boolean.parseBoolean(cells[3]), speedVehicles[1], TILE_SIZE));
					break;
				case "bike":
					sprites.add(new Bike(Float.parseFloat(cells[1]), Float.parseFloat(cells[2]),
							Boolean.parseBoolean(cells[3]), speedVehicles[2], TILE_SIZE));
					break;
				case "bulldozer":
					sprites.add(new Bulldozer(Float.parseFloat(cells[1]), Float.parseFloat(cells[2]),
							Boolean.parseBoolean(cells[3]), speedVehicles[3], TILE_SIZE));
					break;
				case "log":
					sprites.add(new Log(Float.parseFloat(cells[1]), Float.parseFloat(cells[2]),
							Boolean.parseBoolean(cells[3]), speedVehicles[4], lengthLongObjects[0]));
					logs.add(new Log(Float.parseFloat(cells[1]), Float.parseFloat(cells[2]),
							Boolean.parseBoolean(cells[3]), speedVehicles[4], lengthLongObjects[0]));
					break;
				case "longLog":
					sprites.add(new LongLog(Float.parseFloat(cells[1]), Float.parseFloat(cells[2]),
							Boolean.parseBoolean(cells[3]), speedVehicles[5], lengthLongObjects[1]));
					logs.add(new LongLog(Float.parseFloat(cells[1]), Float.parseFloat(cells[2]),
							Boolean.parseBoolean(cells[3]), speedVehicles[5], lengthLongObjects[1]));
					break;
				case "turtle":
					sprites.add(new Turtle(Float.parseFloat(cells[1]), Float.parseFloat(cells[2]),
							Boolean.parseBoolean(cells[3]), speedVehicles[6], lengthLongObjects[2]));
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets up a new level once one has been completed.
	 */
	public void newLevel() throws SlickException {

		levelCount++;
		// Clears existing sprites arrays.
		sprites.clear();
		finishes.clear();
		logs.clear();
		// Once both levels have been completed, stop the game.
		if (levelCount > 1) {
			System.exit(0);
		}
		// Re-updates sprites.
		readCSV();
		player.resetPlayer();
		extraLife = new ExtraLife(0, 0, TILE_SIZE, false, logs);
		float finishX = finishesStartPosition[0], finishY = finishesStartPosition[1];
		for (int i = 0; i < NUM_FINISH_SPOTS; i++) {
			finishes.add(new FinishFrog(finishX, finishY, 2 * TILE_SIZE, false));
			finishX += 4 * TILE_SIZE;
		}
	}

	/**
	 * Updates all of the sprites in the game.
	 * 
	 * @param input user input
	 * @param delta time (in milliseconds) since last update.
	 */
	public void update(Input input, int delta) throws SlickException {

		// Checks whether player is colliding with killable/ridable/extra life objects..
		player.collisionChecker(sprites, finishes, extraLife);
		// Updates player position based upon key presses.
		player.update(input, delta);
		// Updates extra life position and visibility.
		extraLife.update(input, delta);

		// Updates sprite positions.
		for (int i = 0; i < sprites.size(); i++) {
			if (sprites.get(i).getCanMoveFrog()) {
				sprites.get(i).update(input, delta, player);
			} else {
				sprites.get(i).update(input, delta);
			}
		}

		// Check whether a level has been completed.
		boolean levelComplete = true;
		for (int i = 0; i < finishes.size(); i++) {
			if (!finishes.get(i).getFinished()) {
				levelComplete = false;
			}
		}
		if (levelComplete) {
			newLevel();
		}
	}

	/**
	 * Renders all of the sprites in the game.
	 * 
	 * @param g Graphics used to render.
	 */
	public void render(Graphics g) throws SlickException {

		// Drawing majority of sprites.
		for (int i = 0; i < sprites.size(); i++) {
			sprites.get(i).render();
		}
		// Drawing Character.
		player.render();
		// Drawing extra life.
		extraLife.render();
		// Draw finished spots.
		for (int i = 0; i < finishes.size(); i++) {
			if (finishes.get(i).getFinished()) {
				finishes.get(i).render();
			}
		}
		// Draw remaining lives.
		for (int i = 0; i < lives.size(); i++) {
			if (!lives.get(i).getLost()) {
				lives.get(i).render();
			}
		}
	}
}
