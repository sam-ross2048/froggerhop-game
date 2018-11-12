import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Generally details the functionality of all log classes.
 * 
 * @author SamRoss
 */
public class GeneralLog extends Transport {

	/**
	 * Constructor for a general log.
	 * 
	 * @param imageSrc     image source.
	 * @param x            initial x co-ordinate of the general log.
	 * @param y            initial y co-ordinate of the general log.
	 * @param startPos     whether or not log is moving to the right.
	 * @param speed        speed of the log.
	 * @param spriteLength length of the log
	 * @param killsYou     whether or not log kills you.
	 */
	public GeneralLog(String imageSrc, float x, float y, boolean startPos, float speed, int spriteLength,
			boolean killsYou) throws SlickException {
		super(imageSrc, x, y, startPos, speed, spriteLength, killsYou);
		setCanMoveFrog(true);
	}

	/**
	 * Updates the log if it does not need to carry the character.
	 * 
	 * @param input user input.
	 * @param delta time (in milliseconds) since last update.
	 */
	public void update(Input input, int delta) throws SlickException {
		super.update(input, delta);
	}

	/**
	 * Updates the log if it needs to carry the character.
	 * 
	 * @param input  user input.
	 * @param delta  time (in milliseconds) since last update
	 * @param player frog character.
	 */
	public void update(Input input, int delta, Character player) throws SlickException {

		super.update(input, delta);
		carrySprite(player, delta);
	}
}
