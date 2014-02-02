package tile;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import pathfinding.Node;
import pathfinding.Pathfinder;

public class PathableTilemap extends TileMap {

	public Pathfinder p;
	private ArrayList<Node> tiles;
	
	public PathableTilemap(String filename, float x, float y, float width, float height) throws NumberFormatException, SlickException {
		super(filename, x, y, width, height);
		p = new Pathfinder((int) width);
		tiles = generatePathMap();
		p.linkNodes(tiles);
	}
	
	public ArrayList<Node> generatePathMap() throws NumberFormatException, SlickException {
		ArrayList<Node> temp = new ArrayList<Node>();
		int x = 0;
		int y = 0;
		for(String[] line : input) {
			for(String id : line) {
				PathableTile t = new PathableTile(x, y, getTileById(Integer.parseInt(id), x * tileSize, y * tileSize));
				temp.add(t);
				x++;
			}
			x = 0;
			y++;
		}
		return temp;
	}
	
	@Override
	public void render(Graphics g) {
		for(Node t : tiles) {
			((PathableTile)t).render(g);
		}
	}

	@Override
	public void update(GameContainer container, float delta) throws SlickException {
		for(Node t : tiles) {
			((PathableTile)t).update(container, delta);
		}
	}
}
