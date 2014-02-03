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
	private Color normal, hover, current, disabled, selected;
	
	/**
	 * Construct a new menu item
	 * @param value - The text that appears 
	 * @param x - it's x position
	 * @param y - it's y position
	 * @param width - it's width
	 * @param height - it's height
	 * @param colorPallette - The colors that will be used for each state
	 * @param actionState - What the state becomes when this button is pressed
	 */
	public MenuItem(String value, float x, float y, float width, float height, Color[] colorPallette, GameState actionState) {
		super(x, y, width, height);
		this.value = value;
		normal = colorPallette[0];
		selected = colorPallette[1];
		hover = colorPallette[2];
		current = normal;
		disabled = new Color(170,170,170);
		this.actionState = actionState;
	}
	
	/**
	 * Render the MenuItem
	 */
	@Override
	public void render(Graphics g) {
		g.setColor(current);
		g.drawString(value, x, y);
		active = false;
	}

	/**
	 * Update the MenuItem
	 */
	@Override
	public void update(GameContainer container, float delta) throws SlickException {
		if(enabled) {
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
					try {
						GameStateManager.set(getActionState());
					} catch (Exception e) {}
				}
				
				current = hover;
			}
			
			
		} else {
			current = disabled;
		}
		
	}

	/**
	 * Called when this object collides with something
	 */
	@Override
	public void onCollision(Collidable o) {
		active = true;
		current = hover;
	}	
	
	/**
	 * Set the MenuItem to active
	 */
	public void setActive() {
		active = true;
		current = selected;
	}
	
	/**
	 * Get the action state of this MenuItem
	 * @return
	 */
	public GameState getActionState() {
		return actionState;
	}
	
	/**
	 * Set whether or not this item is disabled
	 * @param f - true or false
	 * @return this - allows chaining
	 */
	public MenuItem setDisabled(boolean f) {
		enabled = !f;
		return this;
	}
}
