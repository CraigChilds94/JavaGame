package gamestate;

import managers.GameStateManager;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import core.GameState;
import entities.Player;
import game.Game;

public class GuideState extends State {

	@Override
	public void update(GameContainer c, float delta) throws SlickException {
		if(c.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			try {
				GameStateManager.set(GameState.MENU);
			} catch (Exception e) {}
        }
    	return;
	}

	@Override
	public void render(Graphics g) {
		g.drawString("Guide", 100, 100);
	}

	@Override
	public void onLoad() throws Exception, SlickException {}
	
	@Override
	public void onLeave() {}
}
