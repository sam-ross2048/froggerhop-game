import org.newdawn.slick.SlickException;

/**
 * The goal location of the character.
 * 
 * @author SamRoss
 *
 */
public class FinishFrog extends Still {

	private boolean finished = false;

	/**
	 * Constructor for the finish spot.
	 * 
	 * @param x            x co-ordinate.
	 * @param y            y co-ordinate.
	 * @param spriteLength horizontal length of finish spot
	 * @param killsYou     whether or not spot kills you.
	 */
	public FinishFrog(float x, float y, int spriteLength, boolean killsYou) throws SlickException {
		super("assets/frog.png", x, y, spriteLength, killsYou);
	}

	/**
	 * Gets whether spot has been finished or not.
	 * 
	 * @return whether spot has been finished or not.
	 */
	public boolean getFinished() {
		return finished;
	}

	/**
	 * Sets whether or not spot has been finished.
	 * 
	 * @param newVal New value of whether spot has been finished.
	 */
	public void setFinished(boolean newVal) {
		finished = newVal;
	}

}
