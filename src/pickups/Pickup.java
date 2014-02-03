package pickups;

import org.newdawn.slick.SlickException;

import core.Collidable;
import core.DrawableImage;
import entities.Player;

/**
 * OOOO Pickups
 * @author Craig
 */
public abstract class Pickup extends DrawableImage {
	
	public float speed = 0.01f;
	public float modifierValue = 50;
	
	/**
	 * Constructs a pickup-able object
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param imgPath
	 * @throws SlickException
	 */
	public Pickup(float x, float y, float width, float height, String imgPath) throws SlickException {
		super(x, y, width, height, imgPath);
	}
	
	/**
	 * what do we do when we collide with a player?
	 * @param o
	 */
	@Override
	public void onCollision(Collidable o) {
		if(o instanceof Player) {
			doFunctionality((Player) o);
			this.visible = false;
		}
		
	}
	
	/**
	 * Lets make the sub class decide what we do when we collide with a player
	 * @param p
	 */
	public abstract void doFunctionality(Player p);

}
