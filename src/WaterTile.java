import org.newdawn.slick.SlickException;

/**
 * Water tile.
 * 
 * @author SamRoss
 *
 */
public class WaterTile extends Still {

	/**
	 * Constructor for water tile.
	 * 
	 * @param x            x co-ordinate.
	 * @param y            y co-ordinate.
	 * @param spriteLength horizontal length of water tile.
	 */
	public WaterTile(float x, float y, int spriteLength) throws SlickException {
		super("assets/water.png", x, y, spriteLength, true);
	}

}
