package tile;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.DrawableImage;

public abstract class Tile extends DrawableImage {
	
	public String name;
	
	public Tile(float x, float y, String n) throws SlickException {
		super(x, y, new Image("res/tiles/" + n + ".png"));
		name = n;
	}
}
