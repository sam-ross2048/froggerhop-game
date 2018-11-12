import org.newdawn.slick.SlickException;

/**
 * Grass tile.
 * @author SamRoss
 */
public class GrassTile extends Still{

	/**
	 * Constructor for the grass tile.
	 * @param x x co-ordinate of the grass tile.
	 * @param y y co-ordinate of the grass tile.
	 * @param spriteLength length of the tile.
	 */
	public GrassTile(float x, float y, int spriteLength) throws SlickException {
		super("assets/grass.png", x, y, spriteLength, false);
	}
}
