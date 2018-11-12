import org.newdawn.slick.SlickException;

/**
 * Racecar that moves across the screen and can kill the frog.
 * 
 * @author SamRoss
 */
public class Racecar extends Vehicle {

	/**
	 * Constructor for the racecar.
	 * 
	 * @param x            initial x co-ordinate.
	 * @param y            initial y co-ordinate.
	 * @param startPos     whether or not racecar moves to the right.
	 * @param speed        speed of racecar.
	 * @param spriteLength horizontal length of racecar.
	 */
	public Racecar(float x, float y, boolean startPos, float speed, int spriteLength) throws SlickException {
		super("assets/racecar.png", x, y, startPos, speed, spriteLength, true);
	}

}
