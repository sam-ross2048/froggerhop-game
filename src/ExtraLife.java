import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

import java.util.ArrayList;
import java.util.Random;

/**
 * Extra life for the character.
 * 
 * @author SamRoss
 *
 */
public class ExtraLife extends Sprite {
	private static int MIN_TIME = 25;
	private static int MAX_TIME = 35;
	private int elapsedTime = 0;
	private int generationTime = getRandomNumberInRange(MIN_TIME, MAX_TIME);
	private boolean visible = false;
	private ArrayList<GeneralLog> logs;
	private int randomLogIndex;
	private int bounceTime = 0;
	private boolean goingRight = true;

	/**
	 * Constructor for extra life.
	 * 
	 * @param x        initial x co-ordinate.
	 * @param y        initial y co-ordinate.
	 * @param length   horizontal length of the extra life.
	 * @param killsYou whether or not extra life kills you.
	 * @param logs     sprites on which extra life can generate.
	 */
	public ExtraLife(float x, float y, int length, boolean killsYou, ArrayList<GeneralLog> logs) throws SlickException {
		super("assets/extralife.png", x, y, length, killsYou);
		this.logs = logs;
		randomLogIndex = getRandomNumberInRange(0, logs.size() - 1);
		setX(logs.get(randomLogIndex).getX());
		setY(logs.get(randomLogIndex).getY());
		setBox(getX(), getY());
	}

	/**
	 * Updates extra life's position and visibility.
	 * 
	 * @param input user input.
	 * @param delta time (in milliseconds) since last update.
	 */
	public void update(Input input, int delta) throws SlickException {

		elapsedTime += delta;
		bounceTime += delta;

		// Determine whether extra life should bounce along log.
		if (bounceTime >= 2000) {
			bounceTime = 0;
			attemptMove();
		}
		// Check if extra life should be visible yet.
		if (elapsedTime >= generationTime * 1000) {
			elapsedTime = 0;
			visible = true;
			generationTime = getRandomNumberInRange(MIN_TIME, MAX_TIME);
		}
		// Check if extra life has expired.
		if (elapsedTime >= 14000 && visible) {
			visible = false;
			randomLogIndex = getRandomNumberInRange(0, logs.size() - 1);
			setX(logs.get(randomLogIndex).getX());
			setY(logs.get(randomLogIndex).getY());
			setBox(getX(), getY());
		}
		// Makes extra life ride the log.
		ride(delta, logs.get(randomLogIndex).getSpeed(), logs.get(randomLogIndex).getStartPos());

		// Updating log positions.
		for (int i = 0; i < logs.size(); i++) {
			logs.get(i).update(input, delta);
		}
	}

	public void update(Input input, int delta, Character player) throws SlickException {
	}

	private int getRandomNumberInRange(int min, int max) {
		Random randTime = new Random();
		return randTime.nextInt((max - min) + 1) + min;
	}

	/**
	 * Draws the extra life if visible.
	 */
	public void render() throws SlickException {
		if (visible) {
			getImage().drawCentered(getX(), getY());
		}
	}

	/**
	 * Makes extra life ride its log.
	 * 
	 * @param delta      time (in milliseconds) since last update.
	 * @param otherSpeed speed of sprite being ridden.
	 * @param startPos   direction of travel of sprite being ridden.
	 */
	public void ride(int delta, float otherSpeed, boolean startPos) {
		if (startPos) {
			// if ridden sprite is moving from the left...
			if ((getX() + delta * otherSpeed) < SCREEN_WIDTH + logs.get(randomLogIndex).getBox().getWidth() / 2) {
				setX(getX() + delta * otherSpeed);
				setBox(getX(), getY());
			} else {
				setX(-logs.get(randomLogIndex).getBox().getWidth() / 2);
				setBox(getX(), getY());
			}
		} else {
			// if ridden sprite is moving from the right...
			if ((getX() - delta * otherSpeed) > -logs.get(randomLogIndex).getBox().getWidth() / 2) {
				setX(getX() - delta * otherSpeed);
				setBox(getX(), getY());
			} else {
				setX(SCREEN_WIDTH + logs.get(randomLogIndex).getBox().getWidth() / 2);
				setBox(getX(), getY());
			}
		}
	}

	/**
	 * Checks which way the extra life can bounce and then makes it bounce.
	 */
	public void attemptMove() {

		// Ghost used to check whether a potential move would be valid.
		BoundingBox ghost;

		if (goingRight) {
			ghost = new BoundingBox(getX() + SPRITE_HEIGHT, getY(), SPRITE_HEIGHT, SPRITE_HEIGHT);
			if (ghost.intersects(logs.get(randomLogIndex).getBox())) {
				setX(getX() + SPRITE_HEIGHT);
				setBox(getX(), getY());
			} else {
				goingRight = false;
			}
		}
		if (!goingRight) {
			ghost = new BoundingBox(getX() - SPRITE_HEIGHT, getY(), SPRITE_HEIGHT, SPRITE_HEIGHT);
			if (ghost.intersects(logs.get(randomLogIndex).getBox())) {
				setX(getX() - SPRITE_HEIGHT);
				setBox(getX(), getY());
			} else {
				goingRight = true;
			}
		}
	}

	/**
	 * Gets whether or not extra life is visible.
	 * 
	 * @return whether or not extra life is visible.
	 */
	public boolean getVisible() {
		return visible;
	}

	/**
	 * Sets whether or not extra life is visible.
	 * 
	 * @param newVal new value of whether extra life is visible.
	 */
	public void setVisible(boolean newVal) {
		visible = newVal;
	}

}
