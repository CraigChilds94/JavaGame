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

public class GameoverState extends State {

	@Override
	public void update(GameContainer c, float delta) throws SlickException {
		if(c.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            GameStateManager.state = GameState.MENU;
        }
	}

	@Override
	public void render(Graphics g) {
		g.setColor(new Color(200, 100, 100));
        g.drawString("GAME OVERRRRR!", 100, Game.HEIGHT / 2);
	}

}
