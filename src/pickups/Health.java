package pickups;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import entities.Player;
import game.Game;

/**
 * We always need to pickup some more health, right?
 * @author Craig
 *
 */
public class Health extends Pickup {
	
	/**
	 * Construct a new health obj
	 * @param x
	 * @param y
	 * @throws SlickException
	 */
	public Health(float x, float y) throws SlickException {
		super(x, y, 0, 0, "res/pickups/health-32.png");
		width = img.getWidth();
		height = img.getHeight();

	}
	
	/**
	 * do some shit
	 */
	@Override
	public void doFunctionality(Player p) {
		Game.baseHealth += modifierValue;
	}

	/**
	 * update this brah!
	 */
	@Override
	public void update(GameContainer container, float delta) throws SlickException {
		y += delta * speed;
		if(y > container.getHeight()) {
			visible = false;
		}
	}

}
