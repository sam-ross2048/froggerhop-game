import org.newdawn.slick.SlickException;

/**
 * A life that the frog character has/has lost.
 * 
 * @author SamRoss
 */
public class Life extends Still {

	private boolean beenLost = false;

	/**
	 * Constructor for a life.
	 * 
	 * @param x            x co-ordinate of the life.
	 * @param y            y co-ordinate of the life.
	 * @param spriteLength horizontal length of the life.
	 * @param killsYou     whether or not colliding with the life kills you.
	 */
	public Life(float x, float y, int spriteLength, boolean killsYou) throws SlickException {
		super("assets/lives.png", x, y, spriteLength, killsYou);
	}

	/**
	 * Gets whether the life has been lost or not.
	 * 
	 * @return whether the life has been lost or not.
	 */
	public boolean getLost() {
		return beenLost;
	}

	/**
	 * Sets a life to having been lost or gained.
	 * 
	 * @param newVal New value of whether life has been lost or not.
	 */
	public void setLost(boolean newVal) {
		beenLost = newVal;
	}

}
