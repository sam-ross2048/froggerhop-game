
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;

/**
 * Something 'physical' that is relevant to the game structure.
 * @author SamRoss
 */
public abstract class Sprite {

	/**
	 * Width of the game screen.
	 */
	public static final int SCREEN_WIDTH = 1024;
	/**
	 * Height of the game screen.
	 */
	public static final int SCREEN_HEIGHT = 768;
	/**
	 * Height of all relevant sprites.
	 */
	public final static int SPRITE_HEIGHT = 48;

	private float xPos;
	private float yPos;
	private Image image;
	private BoundingBox box;
	private boolean killsYou;
	private boolean isRiding = false;
	private boolean ridable = false;
	private boolean solid = false;
	private boolean canMoveFrog = false;

	/**
	 * General constructor for all sprites.
	 * 
	 * @param imageSrc The image source.
	 * @param x        Initial x co-ordinate of the sprite.
	 * @param y        Initial y co-ordinate of the sprite.
	 * @param length   Horizontal length of the sprite.
	 * @param killsYou Whether or not the sprite can kill the frog.
	 */
	public Sprite(String imageSrc, float x, float y, int length, boolean killsYou) throws SlickException {
		xPos = x;
		yPos = y;
		image = new Image(imageSrc);
		this.killsYou = killsYou;
		this.box = new BoundingBox(x, y, length, SPRITE_HEIGHT);
	}

	/**
	 * General render method for all sprites.
	 */
	public void render() throws SlickException {
		image.drawCentered(xPos, yPos);
	}

	/**
	 * Abstract method to update the sprite when this particular sprite does not
	 * move the frog.
	 * 
	 * @param input User input.
	 * @param delta Time (in milliseconds) since last update.
	 */
	public abstract void update(Input input, int delta) throws SlickException;

	/**
	 * Abstract method to update the sprite when the sprite can move the frog.
	 * 
	 * @param input  User input.
	 * @param delta  Time (in milliseconds) since last update.
	 * @param player Frog character.
	 */
	public abstract void update(Input input, int delta, Character player) throws SlickException;

	/**
	 * General method for sprites to ride each other.
	 * 
	 * @param delta      Time (in milliseconds) since last update.
	 * @param otherSpeed Speed of the sprite that is being ridden.
	 * @param startPos   Whether or not the sprite being ridden is moving right.
	 */
	public void ride(int delta, float otherSpeed, boolean startPos) {
	}

	/**
	 * Gets image of sprite.
	 * 
	 * @return image of sprite.
	 */
	public Image getImage() {
		return image;
	}

	/**
	 * Sets x co-ordinate of sprite.
	 * 
	 * @param x new x co-ordinate.
	 */
	public void setX(float x) {
		xPos = x;
	}

	/**
	 * Gets x co-ordinate of sprite
	 * 
	 * @return x co-ordinate.
	 */
	public float getX() {
		return xPos;
	}

	/**
	 * Sets y co-ordinate of sprite.
	 * 
	 * @param y new y co-ordinate.
	 */
	public void setY(float y) {
		yPos = y;
	}

	/**
	 * Gets y co-ordinate of the sprite
	 * 
	 * @return y co-ordinate
	 */
	public float getY() {
		return yPos;
	}

	/**
	 * Sets new x,y co-ordinates for bounding box.
	 * 
	 * @param x new x co-ordinate.
	 * @param y new y co-ordinate.
	 */
	public void setBox(float x, float y) {
		this.box.setX(x);
		this.box.setY(y);
	}

	/**
	 * Get bounding box.
	 * 
	 * @return bounding box.
	 */
	public BoundingBox getBox() {
		return box;
	}

	/**
	 * Gets whether or not the sprite can kill the frog.
	 * 
	 * @return whether or not the sprite can kill the frog.
	 */
	public boolean getKillsYou() {
		return killsYou;
	}

	/**
	 * Gets whether or not the sprite is riding another sprite.
	 * 
	 * @return whether or not the sprite is riding another sprite.
	 */
	public boolean getIsRiding() {
		return isRiding;
	}

	/**
	 * Sets whether or not the sprite is riding another sprite.
	 * 
	 * @param newVal New value of whether sprite is riding another sprite.
	 */
	public void setIsRiding(boolean newVal) {
		isRiding = newVal;
	}

	/**
	 * Gets whether or not the sprite can be ridden by other sprites.
	 * 
	 * @return whether or not the sprite can be ridden by other sprites.
	 */
	public boolean getRidable() {
		return ridable;
	}

	/**
	 * Sets whether or not the sprite can be ridden by other sprites
	 * 
	 * @param newVal New value of whether sprite can be ridden by other sprites.
	 */
	public void setRidable(boolean newVal) {
		ridable = newVal;
	}

	/**
	 * Gets whether or not a sprite is solid and cannot be moved into.
	 * 
	 * @return whether or not a sprite is solid and cannot be moved into.
	 */
	public boolean getSolid() {
		return solid;
	}

	/**
	 * Sets whether or not a sprite is solid and cannot be moved into.
	 * 
	 * @param newVal New value of if sprite is solid and cannot be moved into.
	 */
	public void setSolid(boolean newVal) {
		solid = newVal;
	}

	/**
	 * Gets whether or not the sprite can move the frog character.
	 * 
	 * @return whether or not the sprite can move the frog character.
	 */
	public boolean getCanMoveFrog() {
		return canMoveFrog;
	}

	/**
	 * Sets whether or not the sprite can move the frog character.
	 * 
	 * @param newVal New value of whetherthe sprite can move the frog character
	 */
	public void setCanMoveFrog(boolean newVal) {
		canMoveFrog = newVal;
	}
}
