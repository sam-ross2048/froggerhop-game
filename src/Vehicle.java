import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * General vehicle class used for moving sprites.
 * 
 * @author SamRoss
 *
 */
public class Vehicle extends Sprite {
	private float speed;
	private boolean startPos;

	/**
	 * General vehicle constructor.
	 * 
	 * @param imageSrc     image source.
	 * @param x            initial x co-ordinate of the vehicle.
	 * @param y            initial y co-ordinate of the vehicle.
	 * @param startPos     whether or not the vehicle is moving to the right.
	 * @param speed        speed of vehicle.
	 * @param spriteLength length of vehicle.
	 * @param killsYou     whether or not the vehicle kills the frog.
	 */
	public Vehicle(String imageSrc, float x, float y, boolean startPos, float speed, int spriteLength, boolean killsYou)
			throws SlickException {
		super(imageSrc, x, y, spriteLength, killsYou);
		this.startPos = startPos;
		this.speed = speed;
	}

	/**
	 * General vehicle update method.
	 * 
	 * @param input user input
	 * @param delta time (in milliseconds) since last update
	 */
	public void update(Input input, int delta) throws SlickException {

		if (startPos) {
			// if vehicle travels from the left...
			if ((getX() + delta * speed) < SCREEN_WIDTH + getBox().getWidth() / 2) {
				setX(getX() + delta * speed);
				setBox(getX(), getY());
			} else {
				setX(-getBox().getWidth() / 2);
				setBox(getX(), getY());
			}
		} else {
			// if vehicle travels from the right...
			if ((getX() - delta * speed) > (-getBox().getWidth() / 2)) {
				setX(getX() - delta * speed);
				setBox(getX(), getY());
			} else {
				setX(SCREEN_WIDTH + getBox().getWidth() / 2);
				setBox(getX(), getY());
			}
		}
	}

	/**
	 * General update method for when vehicle needs to move frog.
	 */
	public void update(Input input, int delta, Character player) throws SlickException {
	}

	/**
	 * Gets whether or not vehicle is moving to the right.
	 * 
	 * @return whether or not vehicle is moving to the right.
	 */
	public boolean getStartPos() {
		return startPos;
	}

	/**
	 * Sets whether or not vehicle is moving to the right.
	 * 
	 * @param direction new direction that vehicle is travelling in.
	 */
	public void setStartPos(boolean direction) {
		startPos = direction;
	}

	/**
	 * Gets speed of vehicle
	 * 
	 * @return speed of vehicle.
	 */
	public float getSpeed() {
		return speed;
	}
}
