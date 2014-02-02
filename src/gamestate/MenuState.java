package gamestate;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.GameState;
import entities.Player;
import gui.Menu;
import gui.MenuItem;

public class MenuState extends State {
	
	private Menu menu;
	
	/**
	 * Construct a new Menu State
	 * Initialise any Menu stuff
	 */
	public MenuState() {
		Color[] colors = new Color[]{
    			// Normal
    			new Color(255, 255, 255),
    			// Selected
    			new Color(106,159,235),
    			//Pressed
    			new Color(157,227,116)
    	};
		menu = new Menu();
        menu.addMenuItem(new MenuItem("Play", 220, 100, 200, 20, colors, GameState.LEVEL_SELECT));
        menu.addMenuItem(new MenuItem("Options", 220, 150, 200, 20, colors, GameState.OPTIONS).setDisabled(true));
        menu.addMenuItem(new MenuItem("Guide", 220, 200, 200, 20, colors, GameState.GUIDE));
        menu.addMenuItem(new MenuItem("Exit", 220, 250, 200, 20, colors, GameState.EXIT));
        
        int i = 5;
        int width = 4;
        System.out.println("x:" + ((i - 1) % width));

        System.out.println("y:" + (i / width));
	}
	
	/**
	 * Update the menu
	 */
	@Override
	public void update(GameContainer c, float delta) throws SlickException {
		menu.update(c, delta);
	}

	/**
	 * Render the menu
	 */
	@Override
	public void render(Graphics g) {
		menu.render(g);
	}
}
