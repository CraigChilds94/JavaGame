package tile;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import pathfinding.Pathfinder;

public class PathableTilemap extends TileMap {

	private Pathfinder p;
	private ArrayList<PathableTile> tiles;
	
	public PathableTilemap(String filename, float x, float y, float width, float height) throws NumberFormatException, SlickException {
		super(filename, x, y, width, height);
		p = new Pathfinder((int) width);
		tiles = generatePathMap();
	}
	
	public ArrayList<PathableTile> generatePathMap() throws NumberFormatException, SlickException {
		ArrayList<PathableTile> temp = new ArrayList<PathableTile>();
		int x = 0;
		int y = 0;
		for(String[] line : input) {
			for(String id : line) {
				temp.add(new PathableTile(x, y, getTileById(Integer.parseInt(id), y * tileSize, x * tileSize)));
				x++;
			}
			x = 0;
			y++;
		}
		return temp;
	}
	
	@Override
	public void render(Graphics g) {
		for(PathableTile t : tiles) {
			t.render(g);
		}
	}

	@Override
	public void update(GameContainer container, float delta) throws SlickException {
		for(PathableTile t : tiles) {
			t.update(container, delta);
		}
	}
}
