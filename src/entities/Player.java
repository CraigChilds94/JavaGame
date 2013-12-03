package entities;

import gameobjects.Entity;
import gameobjects.Weapon;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import weapons.RocketLauncher;

/**
 *
 * @author craig
 */
public class Player extends Entity {
    
    public Weapon gun;
    
    public Player(float start_x, float start_y) throws SlickException {
        super(start_x, start_y, 30, 30, "res/ship-32.png");
        gun = new RocketLauncher(this, "Destroyer");
    }
    
    @Override
    public void update(GameContainer container, float delta) throws SlickException {
        this.c = container;
        Input input = container.getInput();
        img.setRotation(0f);
        
        if(input.isKeyDown(Input.KEY_LEFT)) {
        	img.setRotation(-6f);
        	x -= delta * speed;
        }
        
        if(input.isKeyDown(Input.KEY_RIGHT)) {
        	img.setRotation(6f);
        	x += delta * speed;
        }
        
        if(input.isKeyDown(Input.KEY_UP)) {
        	y -= delta * speed;
        }
        
        if(input.isKeyDown(Input.KEY_DOWN)) {
            y += delta * speed;
        }
        
        if(input.isKeyPressed(Input.KEY_SPACE)) {
            gun.fire();
        }
        
        gun.update(this, c, delta);
    }

    @Override
    public void render(Graphics g) {
    	g.drawImage(img, x, y);
        gun.render(g);
    }    
}