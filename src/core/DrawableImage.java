package core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Same as Drawable but uses images
 * @author Craig
 */
public abstract class DrawableImage extends Drawable {
	
	public Image img;
	
	/**
	 * Construct a drawable which uses an image
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param imgPath
	 * @throws SlickException
	 */
	public DrawableImage(float x, float y, float width, float height, String imgPath) throws SlickException {
		super(x, y, width, height);
		img = new Image(imgPath);
		width = img.getWidth();
		height = img.getHeight();
	}
	
	/**
	 * Render the image
	 */
	public void render(Graphics g) {
		g.drawImage(img, x, y);
	}

}
