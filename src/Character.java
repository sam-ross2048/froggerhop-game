import java.util.ArrayList;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

/**
 * The user-controlled frog character that is the 'hero' of the game.
 * 
 * @author SamRoss
 *
 */
public class Character extends Sprite {

	private static int MAX_LIVES = 3;
	private static final int[] FROG_START_POSITION = { 512, 720 };
	private int numLives = MAX_LIVES;
	private ArrayList<Life> lives;
	private ArrayList<Sprite> sprites;

	/**
	 * Constructor for the character.
	 * 
	 * @param x            initial x co-ordinates of the character.
	 * @param y            initial y co-ordinates of the character.
	 * @param spriteLength horizontal length of the character.
	 * @param life         objects that can be lost or not lost.
	 */
	public Character(float x, float y, int spriteLength, ArrayList<Life> lives) throws SlickException {
		super("assets/frog.png", x, y, spriteLength, false);
		this.lives = lives;
	}

	/**
	 * Checks collisions between character and other sprites.
	 * 
	 * @param sprites   majority of the sprites in the game.
	 * @param finishes  spots that the frog is trying to reach.
	 * @param extraLife the extra life.
	 */
	public void collisionChecker(ArrayList<Sprite> sprites, ArrayList<FinishFrog> finishes, ExtraLife extraLife) {

		this.sprites = sprites;
		// Check whether or not the character is riding another sprite.
		setIsRiding(false);
		for (int i = 0; i < sprites.size(); i++) {
			if (this.getBox().intersects(sprites.get(i).getBox()) && sprites.get(i).getRidable()) {
				setIsRiding(true);
			}
		}

		// Check if the player collides with another sprite that can kill it.
		for (int i = 0; i < sprites.size(); i++) {
			if (this.getBox().intersects(sprites.get(i).getBox()) && sprites.get(i).getKillsYou() && !getIsRiding()) {
				loseLives();
				resetPlayer();
				break;
			}
		}

		// Check if the player collides with a finish spot.
		for (int i = 0; i < finishes.size(); i++) {
			if (this.getBox().intersects(finishes.get(i).getBox()) && finishes.get(i).getFinished()) {
				loseLives();
				resetPlayer();
			} else if (this.getBox().intersects(finishes.get(i).getBox()) && !finishes.get(i).getFinished()) {
				finishes.get(i).setFinished(true);
				this.setX(512);
				this.setY(720);
				this.setBox(getX(), getY());
			}
		}

		// Check if player collects an extra life.
		if (this.getBox().intersects(extraLife.getBox()) && extraLife.getVisible()) {
			gainLives();
			extraLife.setVisible(false);
		}

		// If player is out of lifes, exit game.
		if (numLives < 0) {
			System.exit(0);
		}
	}

	/**
	 * Causes character to lose a life.
	 */
	public void loseLives() {
		numLives--;
		for (int i = lives.size() - 1; i >= 0; i--) {
			if (!lives.get(i).getLost()) {
				lives.get(i).setLost(true);
				break;
			}
		}
	}

	/**
	 * Causes character to gain a life.
	 */
	public void gainLives() {
		int i;
		for (i = 0; i < lives.size(); i++) {
			if (lives.get(i).getLost()) {
				lives.get(i).setLost(false);
				break;
			}
		}
		// check if the player has collected the max possible number of lives
		if (i != MAX_LIVES) {
			numLives++;
		}
	}

	/**
	 * Causes character to be pushed by another sprite.
	 * 
	 * @param delta      time (in milliseconds) since last update.
	 * @param otherSpeed the pushing sprites speed.
	 * @param startPos   whether or not the pushing sprite is moving to the right.
	 */
	public void getPushed(int delta, float otherSpeed, boolean startPos) {
		if (startPos) {
			// if pusher is moving from the left...
			if ((getX() + delta * otherSpeed) < SCREEN_WIDTH) {
				setX(getX() + delta * otherSpeed);
				setBox(getX(), getY());
			} else {
				loseLives();
				resetPlayer();
			}
		} else {
			// if pusher is moving from the right...
			if ((getX() - delta * otherSpeed) > 0) {
				setX(getX() - delta * otherSpeed);
				setBox(getX(), getY());
			} else {
				loseLives();
				resetPlayer();
			}
		}
	}

	/**
	 * Causes character to ride another sprite.
	 * 
	 * @param delta      time (in milliseconds) since last update.
	 * @param otherSpeed other sprite's speed.
	 * @param startPos   whether or not other sprite is moving to the right.
	 */
	public void ride(int delta, float otherSpeed, boolean startPos) {
		if (startPos) {
			// if pusher is moving from the left...
			if ((getX() + delta * otherSpeed) < SCREEN_WIDTH) {
				setX(getX() + delta * otherSpeed);
				setBox(getX(), getY());
			}
		} else {
			// if pusher is moving from the right...
			if ((getX() - delta * otherSpeed) > 0) {
				setX(getX() - delta * otherSpeed);
				setBox(getX(), getY());
			}
		}
	}

	/**
	 * Updates character's position based upon user inputs.
	 * 
	 * @param input user input.
	 * @param delta time (in milliseconds) since last update.
	 */
	public void update(Input input, int delta) throws SlickException {

		// Ghost used to check whether character would move onto solid object
		BoundingBox ghost;

		if (input.isKeyPressed(Input.KEY_UP) && this.getY() - SPRITE_HEIGHT >= 0) {
			ghost = new BoundingBox(getX(), getY() - SPRITE_HEIGHT, SPRITE_HEIGHT, SPRITE_HEIGHT);
			if (checkValid(ghost)) {
				this.setY(this.getY() - SPRITE_HEIGHT);
			}
		}

		if (input.isKeyPressed(Input.KEY_DOWN) && this.getY() + SPRITE_HEIGHT < SCREEN_HEIGHT) {
			ghost = new BoundingBox(getX(), getY() + SPRITE_HEIGHT, SPRITE_HEIGHT, SPRITE_HEIGHT);
			if (checkValid(ghost)) {
				this.setY(this.getY() + SPRITE_HEIGHT);
			}
		}

		if (input.isKeyPressed(Input.KEY_LEFT) && this.getX() - SPRITE_HEIGHT >= 0) {
			ghost = new BoundingBox(getX() - SPRITE_HEIGHT, getY(), SPRITE_HEIGHT, SPRITE_HEIGHT);
			if (checkValid(ghost)) {
				setX(this.getX() - SPRITE_HEIGHT);
			}
		}
		if (input.isKeyPressed(Input.KEY_RIGHT) && this.getX() + SPRITE_HEIGHT <= SCREEN_WIDTH) {
			ghost = new BoundingBox(getX() + SPRITE_HEIGHT, getY(), SPRITE_HEIGHT, SPRITE_HEIGHT);
			if (checkValid(ghost)) {
				setX(getX() + SPRITE_HEIGHT);
			}
		}
		this.setBox(this.getX(), this.getY());
	}

	public void update(Input input, int delta, Character player) throws SlickException {
	}

	/**
	 * Checks whether or not a move is valid.
	 * 
	 * @param ghost where the character would be if move was valid.
	 * @return whether or not the move is valid
	 */
	public boolean checkValid(BoundingBox ghost) {

		for (int i = 0; i < this.sprites.size(); i++) {
			if (this.sprites.get(i).getSolid() && ghost.intersects(this.sprites.get(i).getBox())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Resets the character's position to initial position.
	 */
	public void resetPlayer() {
		setX(FROG_START_POSITION[0]);
		setY(FROG_START_POSITION[1]);
		setBox(getX(), getY());
	}

	/**
	 * Gets number of lives remaining.
	 * 
	 * @return number of lives remaining.
	 */
	public int getLives() {
		return numLives;
	}

	/**
	 * Sets number of lives
	 * 
	 * @param newVal new number of lives.
	 */
	public void setLives(int newVal) {
		numLives = newVal;
	}
}
