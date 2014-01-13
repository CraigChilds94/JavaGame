package entities;

import org.newdawn.slick.SlickException;

public class MegaShip extends Ship {

	/**
	 * Construct a mini-boss ship
	 * @param start_x
	 * @param start_y
	 * @throws SlickException
	 */
	public MegaShip(float start_x, float start_y) throws SlickException {
		super(start_x, start_y, 1f);
		this.health = 1000;
		this.speed = speed / 2;
		expOutput = 100;
	}

}
