package gameobjects;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public abstract class DrawableImage extends Drawable {
	
	public Image img;
	
	public DrawableImage(float x, float y, float width, float height, String imgPath) throws SlickException {
		super(x, y, width, height);
		img = new Image(imgPath);
		width = img.getWidth();
		height = img.getHeight();
	}
	
	public void render(Graphics g) {
		g.drawImage(img, x, y);
	}

}
