package managers;

import java.util.ArrayList;

import levels.GameLevel;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.GameManager;
import entities.Player;

public class LevelManager extends GameManager {
	
	public ArrayList<GameLevel> levels;
	public static int current = 1;
	private static int numLevels;
	
	public LevelManager() {
		levels = new ArrayList<GameLevel>();
		LevelManager.numLevels = 0;
	}
	
	public void addLevel(GameLevel l) {
		levels.add(l);
		LevelManager.numLevels++;
	}
	
	@Override
	public void update(GameContainer c, float delta, Player p) throws SlickException {
		GameLevel l = levels.get(LevelManager.current - 1);
		l.setPlayer(p);
		l.update(c, delta);
		
	}

	@Override
	public void render(Graphics g) {
		levels.get(LevelManager.current - 1).render(g);
		
	}
	
	public static void changeLevel(int newLevel) {
		if(newLevel > numLevels) {
			return;
		}
		
		LevelManager.current = newLevel;
	}
	
	public static void nextLevel() {
		LevelManager.changeLevel(++LevelManager.current);
	}
	
	public static void prevLevel() {
		LevelManager.changeLevel(--LevelManager.current);
	}

}
