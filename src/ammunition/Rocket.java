package ammunition;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import core.Ammunition;
import core.Collidable;

/**
 * Rocket type of ammo
 * @author craig
 */
public class Rocket extends Ammunition {
	
	/**
	 * Construct a Rocket as a new type of ammo
	 * @param x starting pos
	 * @param y starting pos
	 * @param speed
	 * @throws SlickException
	 */
    public Rocket(float x, float y, float speed) throws SlickException {
        super(x - 2, y, 4, 4, speed, 20, "res/weapons/bullet-16.png");
    }
    
    /**
     * Update the rockets info
     */
    @Override
    public void update(GameContainer container, float delta) {
        this.y += delta * speed;
        this.setBounds(x, y, width, height);
    }

    /**
     * If this collides with an object do something
     */
    @Override
    public void onCollision(Collidable o) {
    	this.destroy();
    }
}