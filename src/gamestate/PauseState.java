package gamestate;

import managers.GameStateManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import core.GameState;
import entities.Player;
import game.Game;

public class PauseState extends State {

	@Override
	public void update(GameContainer c, float delta) throws SlickException {
		if(c.getInput().isKeyPressed(Input.KEY_SPACE)) {
			try {
				GameStateManager.set(GameState.PLAYING);
			} catch (Exception e) {}
        }
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(200, 100, 100));
        g.drawString("Game Paused", 100, 100);
        g.setColor(new Color(200, 200, 200));
        g.drawString("Press Space to resume!", 100, Game.HEIGHT / 2);
	}

	@Override
	public void onLoad() throws Exception, SlickException {}
	
	@Override
	public void onLeave() {}

}
