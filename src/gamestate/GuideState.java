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
    	g.drawString(" - Use arrow keys to move", 120, 140);
    	g.drawString(" - Space bar to shoot", 120, 155);
    	g.drawString(" - Escape key to pause the game", 120, 170);
    	g.drawString("Press Escape to go back to the menu", 100, 250);
	}

	@Override
	public void onLoad() throws Exception, SlickException {}
	
	@Override
	public void onLeave() {}
}
