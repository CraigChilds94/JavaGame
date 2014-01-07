package gamestate;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import entities.Player;

public abstract class State {

	public abstract void update(GameContainer c, float delta) throws SlickException;

	public abstract void render(Graphics g);

}
