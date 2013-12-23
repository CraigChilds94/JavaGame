package entities;

import java.util.Arrays;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import core.Entity;
import core.GameInputListener;
import core.Weapon;
import weapons.DoubleRocketLauncher;
import weapons.RocketLauncher;

/**
 *
 * @author craig
 */
public class Player extends Entity {
    
    public Weapon gun;
    public int exp, level = 1;
    public List<Integer> expStages;
    private float deltaSpeedX = 0f, deltaSpeedY = 0f;
    
    /**
     * Construct a new player for the game
     * @param start_x
     * @param start_y
     * @throws SlickException
     */
    public Player(float start_x, float start_y) throws SlickException {
        super(start_x, start_y, 30, 30, "res/player/ship-32.png");
        gun = new RocketLauncher(this, "Destroyer");
        speed = speed / 2;
        
        // Could probably read all this information from file when saving is added
        expStages = Arrays.asList(
        		250, 500, 2000, 4000, 10000, 25000
        );
    }
    
    /**
     * Update the player
     */
    @Override
    public void update(GameContainer container, float delta) throws SlickException {
    	expCheck();
    	this.delta = delta;
    	this.c = container;
    	GameInputListener gil = new GameInputListener();
        img.setRotation(0f);
        deltaSpeedX = 0;
        deltaSpeedY = 0;
        
        gil.listen(container.getInput(), new PlayerInputEvent(this));
        
        x += deltaSpeedX;
        y += deltaSpeedY;
        gun.update(c, delta);
    }
    
    /**
     * Render the player
     */
    @Override
    public void render(Graphics g) {
    	g.drawImage(img, x, y);
        gun.render(g);
    } 
    
    /**
     * Get the deltaX speed of the player
     * @return the deltaspeed
     */
    public float getDeltaX() {
    	return deltaSpeedX;
    }
    
    /**
     * Get the deltaY speed of the player
     * @return the deltaspeed
     */
    public float getDeltaY() {
    	return deltaSpeedY;
    }
    
    private void onLevelUp() throws SlickException {
    	this.img = new Image("res/player/ship-" + level + "-32.png");
    	gun = new DoubleRocketLauncher(this, "The beast");
    }
    
    /**
     * When the player gets a kill
     * @param val, the amount of exp collected
     */
    public void onKill(int val) {
    	exp += val;
    }
    
    private void expCheck() throws SlickException {
    	if(exp == expStages.get(0)) {
    		level = 2;
    		onLevelUp();
    		exp++;
    	}
    }

	@Override
	public void moveUp() {
		deltaSpeedY = -(delta * speed);
	}

	@Override
	public void moveDown() {
		deltaSpeedY = (delta * speed);
	}

	@Override
	public void moveLeft() {
		img.setRotation(-6f);
    	deltaSpeedX = -(delta * speed);
	}

	@Override
	public void moveRight() {
		img.setRotation(6f);
    	deltaSpeedX = (delta * speed);
	}
}