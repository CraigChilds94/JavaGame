package input;

import game.Game;
import gui.Menu;
import gui.MenuItem;
import managers.GameStateManager;

import org.newdawn.slick.SlickException;


public class MenuInputEvent extends GameInputEvent {
	private Menu menu;
	
	public MenuInputEvent(Menu menu) {
		this.menu = menu;
	}
	
	@Override
	public void doActionOnDown(String key) throws SlickException {}

	@Override
	public void doActionOnPressed(String key) throws SlickException {
		if(key.equals("ACTION")) {
			try {
				GameStateManager.set(((MenuItem)menu.menuItems.get(menu.selection)).getActionState());
			} catch (Exception e) {}
			return;
		}
		
		if(key.equals("DOWN")) {
			if(menu.selection < menu.menuItems.size() - 1) {
				menu.selection++;
			}
			return;
		}
		
		if(key.equals("UP")) {
			if(menu.selection > 0) {
				menu.selection--;
			}
			return;
		}
	}

}
