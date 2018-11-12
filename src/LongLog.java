
import org.newdawn.slick.SlickException;

/**
 * A long log that moves across the screen and can carry the frog.
 * 
 * @author SamRoss
 */
public class LongLog extends GeneralLog {

	/**
	 * Constructor for the long log.
	 * 
	 * @param x            initial x co-ordinate.
	 * @param y            initial y co-ordinate.
	 * @param startPos     whether or not the log is moving to the right.
	 * @param speed        speed of the log.
	 * @param spriteLength length of the log.
	 */
	public LongLog(float x, float y, boolean startPos, float speed, int spriteLength) throws SlickException {
		super("assets/longlog.png", x, y, startPos, speed, spriteLength, false);
		setRidable(true);
	}
}
