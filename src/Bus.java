
import org.newdawn.slick.SlickException;

/**
 * A bus that moves across the screen.
 * 
 * @author SamRoss
 */
public class Bus extends Vehicle {

	/**
	 * Constructor for the bus.
	 * 
	 * @param x            initial x co-ordinate of the bus.
	 * @param y            initial y co-ordinate of the bus.
	 * @param startPos     whether or not bus is moving to the right.
	 * @param speed        speed of the bus.
	 * @param spriteLength length of the bus.
	 */
	public Bus(float x, float y, boolean startPos, float speed, int spriteLength) throws SlickException {
		super("assets/bus.png", x, y, startPos, speed, spriteLength, true);
	}
}
