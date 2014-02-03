package gamestate;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import entities.Player;
import game.Game;

public class CompletedState extends State {

	@Override
	public void update(GameContainer c, float delta) throws SlickException {}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(100, 100, 200));
        g.drawString("CONGRATULATIONS!!! WELL DONE!!!!", 100, Game.HEIGHT / 2);
	}

	@Override
	public void onLoad() throws Exception, SlickException {}

	@Override
	public void onLeave() {}

}
