package entities;

import game.Game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import core.Entity;

/**
 *
 * @author craig
 */
public class Ship extends Entity {
    
    private boolean dir = true;
    private int damage = 20;
    private float starting_x, moveDistance;
    
    /**
     * Construct a new enemy ship
     * @param start_x
     * @param start_y
     * @throws SlickException
     */
    public Ship(float start_x, float start_y, float diff_mod) throws SlickException {
        super(start_x, start_y, 25, 25, "res/enemies/enemy-1-32.png");
        speed = 0.03f * diff_mod;
        health = 19 * diff_mod;
        starting_x = start_x;
        moveDistance = 20;
        expOutput = 50 * diff_mod;
    }
    
    /**
     * Update the ship
     */
    @Override
    public void update(GameContainer container, float delta) {
        this.delta = delta;
    	if(y > container.getHeight() + height) {
            Game.baseHealth -= this.damage;
            this.alive = false;
        }
        
        if(dir &&x < starting_x + moveDistance) {
        	moveRight();
        } else if(!dir && x > starting_x - moveDistance){
        	moveLeft();
        }
        
        moveDown();
    }

    /**
     * This is called when the MoveUp event occurs
     */
	@Override
	public void moveUp() {}
	
	/**
	 * This is called when the MoveDown event occurs
	 */
	@Override
	public void moveDown() {
		y += delta * speed;
	}

	/**
	 * This is called when the MoveLeft event occurs
	 */
	@Override
	public void moveLeft() {
		img.setRotation(6f);
    	x -= delta * (speed / 2);
        if(x < starting_x - moveDistance) {
            dir = true;
        }
	}

	/**
	 * This is called when the MoveRight event occurs
	 */
	@Override
	public void moveRight() {
		img.setRotation(-6f);
    	x += delta * (speed / 2);
        if(x > starting_x + moveDistance) {
            dir = false;
        }
	}

	@Override
	public boolean moveToPoint(float x, float y) {
		if(this.x < x) {
			moveRight();
		} else if(this.x > x) {
			moveLeft();
		}
		
		if(this.y < y) {
			moveDown();
		} else if(this.y > y) {
			moveUp();
		}
		
		if(this.x == x && this.y == y) {
			return true;
		}
		
		return false;
	}
}