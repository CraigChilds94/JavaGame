package gamestate;

import managers.GameStateManager;
import managers.LevelManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import pathfinding.Node;
import pathfinding.Pathfinder;
import core.GameState;

public class LevelSelectState extends State {

	public Pathfinder p;
	public int width, height;
	private boolean loaded = false;
	
	public LevelSelectState() {}
	
	@Override
	public void update(GameContainer c, float delta) throws SlickException {
		Input i = c.getInput();
		if(i.isKeyPressed(i.KEY_SPACE)) {
			LevelManager.current = 1;
			try {
				GameStateManager.set(GameState.PLAYING);
			} catch (Exception e) {}
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawString("Level Select", 100, 100);
		g.drawString("Press space to play", 100, 170);
	}

	@Override
	public void onLoad() throws Exception, SlickException {}
	
	@Override
	public void onLeave() {}

}
