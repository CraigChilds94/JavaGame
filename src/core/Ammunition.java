package core;

import org.newdawn.slick.SlickException;

import entities.Player;

/**
 *
 * @author craig
 */
public abstract class Ammunition extends DrawableImage {
    public float speed, damage;
    public Player player;
    
    /**
     * Construct a new type of ammo
     * @param x
     * @param y
     * @param width
     * @param height
     * @param speed
     * @param damage
     * @param imgPath
     * @throws SlickException
     */
    public Ammunition(float x, float y, float width, float height, float speed, float damage, String imgPath) throws SlickException {
        super(x, y, width, height, imgPath);
        this.speed = speed;
        this.damage = damage;
    }
    
    /**
     * Make this ammo disappear
     */
    public void destroy() {
        this.visible = false;
    }
    
    /**
     * Set the player of this ammo
     * @param p
     */
    public void setPlayer(Player p) {
    	this.player = p;
    }
    
    /**
     * Called when the hit kills the target
     * @param e
     */
    public void wasKillingHit(Entity e) {
    	this.player.onKill(e.expOutput);
    }
}
