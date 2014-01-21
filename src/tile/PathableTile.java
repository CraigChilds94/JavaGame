package tile;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import pathfinding.Node;

public class PathableTile extends Node {
	Tile tile;
	
	public PathableTile(int x, int y, Tile t) {
		tile = t;
		walkable = t.walkable;
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g) {
		tile.render(g);
	}
	
	public void update(GameContainer container, float delta) throws SlickException {
		tile.update(container, delta);
	}
}
