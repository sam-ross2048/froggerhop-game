import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * A turtle that moves across the screen and can carry the frog.
 * 
 * @author SamRoss
 *
 */
public class Turtle extends Transport {

	private int timeElapsed = 0;
	private boolean visible = true;
	private final int timeVisibleFor = 7000;
	private final int timeNotVisible = 2000;

	/**
	 * Constructor for the turtle.
	 * 
	 * @param x            initial x co-ordinate of turtle.
	 * @param y            initial y co-ordinate of turtle.
	 * @param startPos     whether or not the turtle moves to the right.
	 * @param speed        speed of the turtle.
	 * @param spriteLength horizontal length of the turtle.
	 */
	public Turtle(float x, float y, boolean startPos, float speed, int spriteLength) throws SlickException {
		super("assets/turtles.png", x, y, startPos, speed, spriteLength, false);
		setRidable(true);
		setCanMoveFrog(true);
	}

	/**
	 * Update the turtle's state.
	 * 
	 * @param input  user input.
	 * @param delta  time (in milliseconds) since last update.
	 * @param player frog character.
	 */
	public void update(Input input, int delta, Character player) throws SlickException {

		// Check whether turtle should be visible or not.
		timeElapsed += delta;
		if (timeElapsed >= timeVisibleFor && visible) {
			timeElapsed = 0;
			setRidable(false);
			visible = false;
		} else if (timeElapsed >= timeNotVisible && !visible) {
			timeElapsed = 0;
			setRidable(true);
			visible = true;
		}

		// Update turtle's position.
		super.update(input, delta);
		// Check whether turtle is carrying a sprite.
		carrySprite(player, delta);
	}

	/**
	 * Renders the turtle, if visible.
	 */
	public void render() throws SlickException {
		if (visible) {
			getImage().drawCentered(getX(), getY());
		}

	}
}
