package entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import core.Entity;
import core.Weapon;
import weapons.RocketLauncher;

/**
 *
 * @author craig
 */
public class Player extends Entity {
    
    public Weapon gun;
    private float deltaSpeedX = 0f, deltaSpeedY = 0f;
    
    /**
     * Construct a new player for the game
     * @param start_x
     * @param start_y
     * @throws SlickException
     */
    public Player(float start_x, float start_y) throws SlickException {
        super(start_x, start_y, 30, 30, "res/ship-32.png");
        gun = new RocketLauncher(this, "Destroyer");
        speed = speed / 2;
    }
    
    /**
     * Update the player
     */
    @Override
    public void update(GameContainer container, float delta) throws SlickException {
        this.c = container;
        Input input = container.getInput();
        img.setRotation(0f);
        deltaSpeedX = 0;
        deltaSpeedY = 0;
        
        if(input.isKeyDown(Input.KEY_LEFT)) {
        	img.setRotation(-6f);
        	deltaSpeedX = -(delta * speed);
        }
        
        if(input.isKeyDown(Input.KEY_RIGHT)) {
        	img.setRotation(6f);
        	deltaSpeedX = (delta * speed);
        }
        
        if(input.isKeyDown(Input.KEY_UP)) {
        	deltaSpeedY = -(delta * speed);
        }
        
        if(input.isKeyDown(Input.KEY_DOWN)) {
        	deltaSpeedY = (delta * speed);
        }
        
        if(input.isKeyPressed(Input.KEY_SPACE)) {
            gun.fire();
        }
        
        x += deltaSpeedX;
        y += deltaSpeedY;
        gun.update(this, c, delta);
    }
    
    /**
     * Render the player
     */
    @Override
    public void render(Graphics g) {
    	g.drawImage(img, x, y);
        gun.render(g);
    } 
    
    public float getDeltaX() {
    	return deltaSpeedX;
    }
    
    public float getDeltaY() {
    	return deltaSpeedY;
    }
}