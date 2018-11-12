import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * A bulldozer that moves across screen that can push the frog.
 * 
 * @author SamRoss
 */
public class Bulldozer extends Vehicle{

	/**
	 * Constructor for the bulldozer.
	 * 
	 * @param x            initial x co-ordinate of the bulldozer.
	 * @param y            initial y co-ordinate of the bulldozer.
	 * @param startPos     whether or not the bike moves to the right.
	 * @param speed        speed of the bulldozer.
	 * @param spriteLength length of the bulldozer.
	 */
	public Bulldozer(float x, float y, boolean startPos, float speed, int spriteLength) throws SlickException {
		super("assets/bulldozer.png", x, y, startPos, speed, spriteLength, false);
		setSolid(true);
		setCanMoveFrog(true);
	}

	/**
	 * Updates the bulldozer's position (as well as whether it is pushing the frog).
	 */
	public void update(Input input, int delta, Character player) throws SlickException {
		super.update(input, delta);
		pushSprite(player, delta);
	}

	/**
	 * Pushes the frog if bulldozer and frog collide.
	 */
	public void pushSprite(Character other, int delta) {
		if (getBox().intersects(other.getBox())) {
			other.getPushed(delta, getSpeed(), getStartPos());
		}
	}

}
