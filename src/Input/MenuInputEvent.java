package Input;

import gui.Menu;
import gui.MenuItem;

import org.newdawn.slick.SlickException;

import wavedefender.WaveDefender;


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
			WaveDefender.gamestate = ((MenuItem)menu.menuItems.get(menu.selection)).getActionState();
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
