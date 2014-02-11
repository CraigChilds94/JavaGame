package gamestate;

import game.Game;
import gui.Menu;
import gui.MenuItem;
import gui.MenuItemAction;
import managers.GameStateManager;
import managers.LevelManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import pathfinding.Node;
import pathfinding.Pathfinder;
import core.GameState;

public class LevelSelectState extends State {

	public Pathfinder p;
	public int width, height;
	private boolean loaded = false;
	private Menu menu;
	
	public LevelSelectState() {
		Color[] colors = new Color[]{
    			// Normal
    			new Color(255, 255, 255),
    			// Selected
    			new Color(106,159,235),
    			// Hover
    			new Color(157,227,116)
    	};
		
		menu = new Menu();
		for(int i = 1; i <= Game.levelCount; i++) {
			
			final int num = i;
			menu.addMenuItem(new MenuItem("Level " + i, 100, 100 * i, 100, 100, colors, new MenuItemAction() {
				public void onAction(MenuItem item) {
					System.out.println("Hello");
					Game.level = num;
				}			
			}));
		}
		
	}
	
	@Override
	public void update(GameContainer c, float delta) throws SlickException {
		menu.update(c, delta);
	}

	@Override
	public void render(Graphics g) {
		menu.render(g);
	}

	@Override
	public void onLoad() throws Exception, SlickException {}
	
	@Override
	public void onLeave() {}

}
