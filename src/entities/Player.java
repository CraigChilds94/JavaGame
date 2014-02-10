package entities;

import game.Game;
import input.GameInputListener;
import input.PlayerInputEvent;

import java.util.Arrays;
import java.util.List;

import managers.GameStateManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import ammunition.Ammunition;
import animation.Animation;
import animation.Frame;
import core.Collidable;
import core.DrawableImage;
import core.Entity;
import core.GameState;
import weapons.DoubleRocketLauncher;
import weapons.RocketLauncher;
import weapons.Weapon;

/**
 *
 * @author craig
 */
public class Player extends Entity {
    
    public Weapon gun;
    public int exp, level = 1, lives = 3;
    public List<Integer> expStages;
    private float deltaSpeedX = 0f, deltaSpeedY = 0f, startx, starty;
    private GameInputListener gil;
    
    private Animation animation;
    
    /**
     * Construct a new player for the game
     * @param startx
     * @param starty
     * @throws SlickException
     */
    public Player(float startx, float starty) throws SlickException {
        super(startx, starty, 30, 30, "res/player/ship-32.png");
        
        this.startx = startx;
        this.starty = starty;
        
        gun = new RocketLauncher(this, "Destroyer");
        speed = speed / 2;
        
        // Could probably read all this information from file when saving is added
        expStages = Arrays.asList(
        		250, 500, 2000, 4000, 10000, 25000
        );
        
    	gil = new GameInputListener();
    	animation = new Animation(new Frame(0, 0, 1000f, this.img, this), 10f, 1f, 0);
    	animation.setLoop(true);
    }
    
    
    /**
     * Update the player
     */
    @Override
    public void update(GameContainer container, float delta) throws SlickException {
    	expCheck();
    	this.delta = delta;
    	this.c = container;
        
        gil.listen(container.getInput(), new PlayerInputEvent(this));
        x += deltaSpeedX;
        y += deltaSpeedY;
        animation.update(container, delta);
        gun.update(c, delta);
        
        deltaSpeedX = 0;
        deltaSpeedY = 0;
        img.setRotation(0f);
    }
    
    /**
     * Render the player
     */
    @Override
    public void render(Graphics g) {
        animation.render(g);
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
    
    public float getXPos() {
    	return this.x;
    }
    
    public float getYPos() {
    	return this.y;
    }
    
    private void onLevelUp() throws SlickException {
    	this.img = new Image("res/player/ship-" + level + "-32.png");
    	gun = new DoubleRocketLauncher(this, "The beast");
    }
    
    /**
     * When the player gets a kill
     * @param val, the amount of exp collected
     */
    public void onKill(float expOutput) {
    	exp += expOutput;
    }
    
    /**
     * Check to see if we need to progress in level
     * @throws SlickException
     */
    private void expCheck() throws SlickException {
    	if(exp == expStages.get(0)) {
    		level = 2;
    		onLevelUp();
    		exp++;
    	}
    }
    
    /**
     * Respawn the player
     */
    private void respawn() {
    	this.x = startx;
    	this.y = starty;
    }

    /**
	 * This is called when the MoveUp event occurs
	 */
	@Override
	public void moveUp() {
		img.setRotation(0f);
		deltaSpeedY = -(delta * speed);
	}

	/**
	 * This is called when the MoveDown event occurs
	 */
	@Override
	public void moveDown() {
		img.setRotation(-180f);
		deltaSpeedY = (delta * speed);
	}

	/**
	 * This is called when the MoveLeft event occurs
	 */
	@Override
	public void moveLeft() {
		img.setRotation(-90f);
		deltaSpeedX = -(delta * speed);
	}

	/**
	 * This is called when the MoveRight event occurs
	 */
	@Override
	public void moveRight() {
		img.setRotation(90f);
		deltaSpeedX = (delta * speed);
	}

	
	@Override
	public boolean moveToPoint(float x, float y) {
		int newX = (int)x;
		int newY = (int)y;
		int curX = (int)this.x;
		int curY = (int)this.y;

		if (curX < newX) {
			moveRight();
		} else if (curX > newX) {
			moveLeft();
		}

		if (curY < newY) {
			moveDown();
		} else if (curY > newY) {
			moveUp();
		}

		return (curX == newX && curY == newY);
	}
	
	@Override
	public void onCollision(Collidable o) {
    	if(lives < 1) {
    		try {
				GameStateManager.set(GameState.GAMEOVER);
			} catch (Exception e) {}
    		return;
    	}
		this.lives--;
    	this.respawn();
    }
}