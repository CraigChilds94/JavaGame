package core;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import ammunition.Ammunition;

/**
 * Entity data type for collidable, drawable and movable objects
 * @author craig
 */
public abstract class Entity extends DrawableImage {
    public float health = 100;
    public float speed = 0.4f;
    public boolean alive = true;
    public GameContainer c;
    public float expOutput = 10;
    public float delta;
    
    /**
     * Construct a new Entity
     * @param start_x
     * @param start_y
     * @param width
     * @param height
     * @param imgPath
     * @throws SlickException
     */
    public Entity(float start_x, float start_y, float width, float height, String imgPath) throws SlickException {
        super(start_x, start_y, width, height, imgPath);
    }
    
    /**
     * Update the entity
     * @param container
     * @param delta
     * @param collidables
     */
    public void update(GameContainer container, float delta, ArrayList<Entity> collidables) {
        this.delta = delta;
    	this.c = container;
        speed *= delta;
        this.setBounds(x, y, width, height);
    }
       
    /**
     * What happens when an entity collides with some ammo?
     * @param o
     */
    public void onCollision(Collidable o) {
        if(o instanceof Ammunition) {
            takeDamage(((Ammunition)o).damage);
            o.onCollision(this);
            
            if(!alive) {
            	((Ammunition) o).wasKillingHit(this);
            }
        }
    }
    
    /**
     * Make the entity take damage
     * @param damage
     */
    public void takeDamage(float damage) {
        health -= damage;
        if(health < 0) {
            alive = false;
        }
    }
    
    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract boolean moveToPoint(float x, float y);
    
}
