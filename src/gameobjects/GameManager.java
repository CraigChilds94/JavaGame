package gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import entities.Player;

public abstract class GameManager {
	
	public abstract void update(GameContainer c, float delta, Player p) throws SlickException;
	public abstract void render(Graphics g);

}
