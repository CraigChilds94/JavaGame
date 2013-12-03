package gameobjects;


/**
 * Collidables
 * @author craig
 */
public abstract class CollidableObject extends Collidable {
	
	/**
	 * Middle man for collidables (for multiple inheritance)
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
    public CollidableObject(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
      
}