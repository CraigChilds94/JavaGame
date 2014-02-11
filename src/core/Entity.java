package core;

import game.Game;

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
     * @param startx
     * @param starty
     * @param width
     * @param height
     * @param imgPath
     * @throws SlickException
     */
    public Entity(float startx, float starty, float width, float height, String imgPath) throws SlickException {
        super(startx, starty, width, height, imgPath);
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
     * Make the entity take damage
     * @param damage
     */
    public void takeDamage(float damage) {
        health -= damage;
        if(health < 0) {
            alive = false;
        }
    }
    
    /**
     * Check if the entity is within the bounds of the game screen
     * @return true if in bounds, false otherwise
     */
    public boolean inBounds() {
    	return (x > 0 && x < Game.WIDTH) && (y > 0 && y < Game.HEIGHT);
    }
    
    public abstract void moveUp();
    public abstract void moveDown();
    public abstract void moveLeft();
    public abstract void moveRight();
    public abstract boolean moveToPoint(float x, float y);
    public abstract void onCollision(Collidable o);
    
}
