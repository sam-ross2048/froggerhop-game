
import org.newdawn.slick.SlickException;

/**
 * A log that moves across the screen that can carry the frog.
 * 
 * @author SamRoss
 *
 */
public class Log extends GeneralLog {

	/**
	 * Constructor for the log.
	 * 
	 * @param x            initial x co-ordinate of the log.
	 * @param y            initial y co-ordinate of the log.
	 * @param startPos     whether or not the log moves to the right.
	 * @param speed        speed of the log.
	 * @param spriteLength length of the log.
	 */
	public Log(float x, float y, boolean startPos, float speed, int spriteLength) throws SlickException {
		super("assets/log.png", x, y, startPos, speed, spriteLength, false);
		setRidable(true);
	}
}
