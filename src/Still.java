import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * Class for still sprites to inherit from.
 * 
 * @author SamRoss
 *
 */
public class Still extends Sprite {

	/**
	 * Constructor for still sprite.
	 * 
	 * @param imageSrc     image source
	 * @param x            x co-ordinate
	 * @param y            y co-ordinate
	 * @param spriteLength horizontal length of sprite.
	 * @param killsYou     whether or not the still sprite kills frog.
	 */
	public Still(String imageSrc, float x, float y, int spriteLength, boolean killsYou) throws SlickException {
		super(imageSrc, x, y, spriteLength, killsYou);
	}

	/**
	 * Empty default update method for still tiles.
	 */
	public void update(Input input, int delta, Character player) {
	}

	/**
	 * Empty default update method for still tiles.
	 */
	public void update(Input input, int delta) throws SlickException {
	}
}
