import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * A motor bike that moves across the screen.
 * 
 * @author SamRoss
 */
public class Bike extends Vehicle {

	private int leftTurnAroundPoint = 24;
	private int rightTurnAroundPoint = 1000;

	/**
	 * Constructor for the bike.
	 * 
	 * @param x            initial x co-ordinate of bike.
	 * @param y            initial y co-ordinate of bike.
	 * @param startPos     whether or not bike is travelling right.
	 * @param speed        speed of the bike.
	 * @param spriteLength length in pixels of the bike.
	 */
	public Bike(float x, float y, boolean startPos, float speed, int spriteLength) throws SlickException {
		super("assets/bike.png", x, y, startPos, speed, spriteLength, true);
	}

	/**
	 * Updates the bike's position.
	 */
	public void update(Input input, int delta) {
		if (getStartPos()) {
			// if bike travels from the left...
			if ((getX() + delta * getSpeed()) < rightTurnAroundPoint) {
				setX(getX() + delta * getSpeed());
				setBox(getX(), getY());
			} else {
				setStartPos(false);
			}
		} else {
			// if bike travels from the right...
			if ((getX() - delta * getSpeed()) > leftTurnAroundPoint) {
				setX(getX() - delta * getSpeed());
				setBox(getX(), getY());
			} else {
				setStartPos(true);
			}
		}
	}
}
