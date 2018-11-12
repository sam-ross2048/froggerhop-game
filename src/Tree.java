import org.newdawn.slick.SlickException;

/**
 * A solid tree that the frog cannot move into.
 * 
 * @author SamRoss
 *
 */
public class Tree extends Still {

	/**
	 * Constructor for the tree.
	 * 
	 * @param x            x co-ordinate of the tree
	 * @param y            y co-ordinate of the tree.
	 * @param spriteLength horizontal length of the tree.
	 */
	public Tree(float x, float y, int spriteLength) throws SlickException {
		super("assets/tree.png", x, y, spriteLength, false);
		setSolid(true);
	}

}
