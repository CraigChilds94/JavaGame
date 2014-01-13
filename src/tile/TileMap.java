package tile;

import input.File;

import java.io.IOException;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Collidable;
import core.Drawable;

public class TileMap extends Drawable {

	private ArrayList<Tile> tiles;
	private ArrayList<String[]> input;
	
	public static int tileSize = 32;
	
	public static final int AIR = 0,
					        BRICK1 = 1,
					        BRICK2 = 2,
					        DIRT = 3;
	
	public TileMap(String filename, float x, float y, float width, float height) throws NumberFormatException, SlickException {
		super(x, y, width, height);
		input = getInput(filename);
		tiles = generateMap();
	}

	@Override
	public void render(Graphics g) {
		for(Tile t : tiles) {
			t.render(g);
		}
	}

	@Override
	public void update(GameContainer container, float delta) throws SlickException {
		for(Tile t : tiles) {
			t.update(container, delta);
		}
	}

	@Override
	public void onCollision(Collidable o) {}
	
	private ArrayList<String[]> getInput(String filename) {
		File f = new File("res/tilemaps/", filename, ".tmap");
		try {
			return f.read(",");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private ArrayList<Tile> generateMap() throws NumberFormatException, SlickException {
		ArrayList<Tile> temp = new ArrayList<Tile>();
		int x = 0;
		int y = 0;
		for(String[] line : input) {
			for(String id : line) {
				temp.add(getTileById(Integer.parseInt(id), y, x));
				x += tileSize;
			}
			x = 0;
			y += tileSize;
		}
		return temp;
	}
	
	private Tile getTileById(int id, int x, int y) throws SlickException {
		switch(id) {
			case TileMap.AIR : return new Air(x, y);
			case TileMap.DIRT : return new Dirt(x, y);
			default: return new Air(x, y);
		}
	}

}
