package gui;

import managers.GameStateManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import core.Collidable;
import core.CollidableObject;
import core.Drawable;
import core.GameState;

public class MenuItem extends Drawable {
	
	private String value;
	private GameState actionState = GameState.MENU;
	private boolean active = false, enabled = true;
	private Color normal, hover, current, disabled;
	
	public MenuItem(String value, float x, float y, float width, float height, Color[] colorPallette, GameState actionState) {
		super(x, y, width, height);
		this.value = value;
		normal = colorPallette[0];
		hover = colorPallette[1];
		current = normal;
		disabled = new Color(170,170,170);
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
		if(enabled) {
			active = false;
			
			current = normal;
			Input input = container.getInput();
			
			CollidableObject m = new CollidableObject(input.getMouseX(), input.getMouseY(), 1, 1) {

				@Override
				public void onCollision(Collidable o) {
					o.onCollision(this);
				}
				
			};
			m.listenForCollisions(this);
			
			if(active) {
				if(input.isMousePressed(0)) {
					GameStateManager.state = getActionState();
				} else {
					current = hover;
				}
			}
		} else {
			current = disabled;
		}
		
	}

	@Override
	public void onCollision(Collidable o) {
		active = true;
	}	
	
	public void setActive() {
		active = true;
	}
	
	public GameState getActionState() {
		return actionState;
	}
	
	public MenuItem setDisabled(boolean f) {
		enabled = !f;
		return this;
	}
}
