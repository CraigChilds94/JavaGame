package tile;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import core.Collidable;

public class Dirt extends Tile {
	
	public Dirt(float x, float y) throws SlickException {
		super(x, y, "dirt");
		walkable = false;
	}
	
	@Override
	public void update(GameContainer container, float delta) throws SlickException {
		// TODO Auto-generated method stub

	}

	@Override
	public void onCollision(Collidable o) {
		// TODO Auto-generated method stub

	}

}
