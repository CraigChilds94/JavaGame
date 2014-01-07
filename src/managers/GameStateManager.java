package managers;

import java.util.Arrays;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.GameManager;
import core.GameState;
import entities.Player;
import gamestate.State;

public class GameStateManager extends GameManager {

	public static GameState state = GameState.MENU;
	private HashMap<GameState, State> states;
	
	public GameStateManager() {
		states = new HashMap<GameState, State>();
	}
	
	public void addState(GameState label, State state) {
		states.put(label, state);
	}
	
	@Override
	public void update(GameContainer c, float delta, Player p) throws SlickException {
		((State)states.get(state)).update(c, delta);
	}

	@Override
	public void render(Graphics g) {
		((State)states.get(state)).render(g);
	}

}
