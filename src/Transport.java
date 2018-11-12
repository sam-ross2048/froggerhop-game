import org.newdawn.slick.SlickException;

public class Transport extends Vehicle{

	public Transport(String imageSrc, float x, float y, boolean startPos, float speed, int spriteLength,
			boolean killsYou) throws SlickException {
		super(imageSrc, x, y, startPos, speed, spriteLength, killsYou);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Allows transport to carry another sprite.
	 * 
	 * @param other another sprite that may be getting carried by the log.
	 * @param delta time (in milliseconds) since last update.
	 */
	public void carrySprite(Sprite other, int delta) {

		if (this.getBox().intersects(other.getBox())) {
			other.setIsRiding(true);
			other.ride(delta, getSpeed(), getStartPos());
		}
	}
}
