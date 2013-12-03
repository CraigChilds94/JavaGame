package gameobjects;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

/**
 *
 * @author craig
 */
public abstract class Entity extends DrawableImage {
    public float health = 100;
    public float speed = 0.4f;
    public boolean alive = true;
    public GameContainer c;
    
    public Entity(float start_x, float start_y, float width, float height, String imgPath) throws SlickException {
        super(start_x, start_y, width, height, imgPath);
    }
    
    public void update(GameContainer container, float delta, ArrayList<Entity> collidables) {
        this.c = container;
        speed *= delta;
        this.setBounds(x, y, width, height);
    }
       
    public void onCollision(Collidable o) {
        if(o instanceof Ammunition) {
            takeDamage(((Ammunition)o).damage);
            o.onCollision(this);
        }
    }
    
    public void takeDamage(float damage) {
        health -= damage;
        if(health < 0) {
            alive = false;
        }
    }
    
}
