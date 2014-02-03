package gui;

import game.Game;
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

public class Menu extends Drawable {
	
	public ArrayList<Collidable> menuItems;
	public int selection = 0;
	
	private GameInputListener gil;
	private MenuInputEvent mie;
	
	public Menu() {
		super(0,0,0,0);
		menuItems = new ArrayList<Collidable>();
		gil = new GameInputListener();
		mie = new MenuInputEvent(this);
	}

	@Override
	public void render(Graphics g) {
		for(Collidable item : menuItems) {
			((MenuItem)item).render(g);
		}
	}

	@Override
	public void update(GameContainer container, float delta) throws SlickException {
		
		gil.listen(container.getInput(), mie);
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
