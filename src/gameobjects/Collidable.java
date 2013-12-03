package gameobjects;

import java.util.List;
import org.newdawn.slick.geom.Rectangle;

/**
 * Determines Collidable game entities
 * @author craig
 */
public abstract class Collidable extends Rectangle {
    
    public Collidable(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    
    /**
     * Listens out for a collision between this object and one from a list
     * from the list parameter.
     * @param list 
     */
    public void listenForCollisions(List<Collidable> list) {
        for(Collidable o : list) {
            if(this.intersects(o)) {
                this.onCollision(o);
            }
        }
    }
    
    /**
     * Listens out for a collision between this object and one
     * from the list parameter.
     * @param Collidable object 
     */
    public void listenForCollisions(Collidable o) {
        if(this.intersects(o)) {
            this.onCollision(o);
        }
    }
    
    /**
     * A method to be defined that is called when this object collides with another
     * @param o 
     */
    public abstract void onCollision(Collidable o);
}