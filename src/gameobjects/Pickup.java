package gameobjects;

import org.newdawn.slick.SlickException;

import entities.Player;

public abstract class Pickup extends DrawableImage {
	
	public float speed = 0.01f;
	public float modifierValue = 50;
	public Pickup(float x, float y, float width, float height, String imgPath) throws SlickException {
		super(x, y, width, height, imgPath);
	}

	@Override
	public void onCollision(Collidable o) {
		if(o instanceof Player) {
			doFunctionality((Player) o);
			this.visible = false;
		}
		
	}
	
	public abstract void doFunctionality(Player p);

}
