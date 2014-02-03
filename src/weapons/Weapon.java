package weapons;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Collidable;
import entities.Player;

/**
 * Weapons, yummy
 * @author craig
 */
public abstract class Weapon {
    
    public String name;
    public float x, y, width, height;
    public ArrayList<Collidable> bullets = new ArrayList<Collidable>();
    
    /**
     * Construct a weapon
     * @param name
     */
    public Weapon(String name) {
        this.name = name;
    }
    
    /**
     * Each weapon fires differently!
     * @throws SlickException
     */
    public abstract void fire() throws SlickException;
    
    /**
     * Lets make this drawable
     * @param g
     */
    public abstract void render(Graphics g);
    
    /**
     * Update it!!!
     * @param con
     * @param delta
     * @throws SlickException
     */
    public abstract void update(GameContainer con, float delta) throws SlickException;
}
