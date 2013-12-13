package gui;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import core.Collidable;
import core.Drawable;
import core.MouseCollider;
import wavedefender.WaveDefender;

public class Menu extends Drawable {
	
	public ArrayList<Collidable> menuItems;
	public int selection = 0;
	
	public Menu() {
		super(0,0,0,0);
		menuItems = new ArrayList<Collidable>();
	}

	@Override
	public void render(Graphics g) {
		for(Collidable item : menuItems) {
			((MenuItem)item).render(g);
		}
	}

	@Override
	public void update(GameContainer container, float delta) throws SlickException {
		Input i = container.getInput();
		
		if(i.isKeyPressed(Input.KEY_DOWN)) {
			if(selection < menuItems.size() - 1) {
				selection++;
			}
		} else if (i.isKeyPressed(Input.KEY_UP)) {
			if(selection > 0) {
				selection--;
			}
		}
		
		((MenuItem)menuItems.get(selection)).setActive();
		
		for(Collidable item : menuItems) {
			((MenuItem)item).update(container, delta);
		}
		
		if(i.isKeyPressed(Input.KEY_ENTER)) {
			WaveDefender.gamestate = ((MenuItem)menuItems.get(selection)).getActionState();
		}
	}

	@Override
	public void onCollision(Collidable o) {}
	
	public void addMenuItem(MenuItem item) {
		menuItems.add((Collidable) item);
	}
}
