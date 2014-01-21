package gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.GameState;

public class MenuItemImage extends MenuItem {

	Image normal, hover, current;
	
	public MenuItemImage(float x, float y, float width, float height, String[] images, GameState actionState) throws SlickException {
		super(null, x, y, width, height, null, actionState);
		
		normal = new Image(images[0]);
		hover = new Image(images[1]);
		
		current = normal;
	}
	
	/**
	 * Render the MenuItem
	 */
	@Override
	public void render(Graphics g) {
		g.drawImage(current, x, y);
	}

}
