package gameobjects;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import entities.Player;

/**
 *
 * @author craig
 */
public abstract class Weapon {
    
    public String name;
    public float x, y, width, height;
    public ArrayList<Collidable> bullets = new ArrayList<Collidable>();
    
    public Weapon(String name) {
        this.name = name;
    }
    
    public abstract void fire() throws SlickException;
    public abstract void render(Graphics g);
    public abstract void update(Player p, GameContainer con, float delta) throws SlickException;
}
