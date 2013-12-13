package gui;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Collidable;
import core.Drawable;
import core.GameState;

public class MenuItem extends Drawable {
	
	private String value;
	private GameState actionState = GameState.MENU;
	private boolean active = false;
	private Color normal, hover, current;
	
	public MenuItem(String value, float x, float y, float width, float height, Color[] colorPallette, GameState actionState) {
		super(x, y, width, height);
		this.value = value;
		normal = colorPallette[0];
		hover = colorPallette[1];
		current = normal;
		this.actionState = actionState;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(current);
		g.drawString(value, x, y);
		active = false;
	}

	@Override
	public void update(GameContainer container, float delta) throws SlickException {
		current = normal;
		if(active) {
			current = hover;
		}
	}

	@Override
	public void onCollision(Collidable o) {}	
	
	public void setActive() {
		active = true;
	}
	
	public GameState getActionState() {
		return actionState;
	}
}
