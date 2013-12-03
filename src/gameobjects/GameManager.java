package gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import entities.Player;

/**
 * Defines game managers
 * @author Craig
 */
public abstract class GameManager {
	
	/**
	 * For updating a game manager object
	 * @param c
	 * @param delta
	 * @param p
	 * @throws SlickException
	 */
	public abstract void update(GameContainer c, float delta, Player p) throws SlickException;
	
	/**
	 * Maybe we need to render some stuff in this manager?
	 * @param g
	 */
	public abstract void render(Graphics g);
}
