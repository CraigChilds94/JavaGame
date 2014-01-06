package tile;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import core.Collidable;

public class Air extends Tile {

	public Air(float x, float y) throws SlickException {
		super(x, y, "air");
	}

	@Override
	public void update(GameContainer container, float delta) throws SlickException {}

	@Override
	public void onCollision(Collidable o) {}

}
