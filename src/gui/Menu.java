package gui;

import input.GameInputListener;
import input.MenuInputEvent;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;


import core.Collidable;
import core.Drawable;
import core.MouseCollider;
import wavedefender.Game;

public class Menu extends Drawable {
	
	public ArrayList<Collidable> menuItems;
	public int selection = 0;
	
	private GameInputListener gil;
	
	public Menu() {
		super(0,0,0,0);
		menuItems = new ArrayList<Collidable>();
		gil = new GameInputListener();
	}

	@Override
	public void render(Graphics g) {
		for(Collidable item : menuItems) {
			((MenuItem)item).render(g);
		}
	}

	@Override
	public void update(GameContainer container, float delta) throws SlickException {
		gil.listen(container.getInput(), new MenuInputEvent(this));
		
		((MenuItem)menuItems.get(selection)).setActive();
		
		for(Collidable item : menuItems) {
			((MenuItem)item).update(container, delta);
		}
	}

	@Override
	public void onCollision(Collidable o) {}
	
	public void addMenuItem(MenuItem item) {
		menuItems.add((Collidable) item);
	}
}
