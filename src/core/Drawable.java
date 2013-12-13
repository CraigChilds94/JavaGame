package core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * Defines object as drawable
 * @author craig
 */
public abstract class Drawable extends CollidableObject {
    
    public boolean visible = true;
    
    /**
     * Construct a new object which must be renderable
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Drawable(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    
    public abstract void render(Graphics g);
    public abstract void update(GameContainer container, float delta) throws SlickException;
}
