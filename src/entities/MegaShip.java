package entities;

import org.newdawn.slick.SlickException;

public class MegaShip extends Ship {

	public MegaShip(float start_x, float start_y) throws SlickException {
		super(start_x, start_y);
		this.health = 1000;
		this.speed = speed / 2;
	}

}
