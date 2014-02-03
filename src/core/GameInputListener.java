package core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class GameInputListener {
	
	public HashMap<String, Set<Integer>> bindings;
	
	/**
	 * Construct a new game input listener
	 * and set up all of the bindings
	 */
	public GameInputListener() {
		bindings = new HashMap<String, Set<Integer>>();
		// UP
		bindings.put("UP", new HashSet<Integer>(
				Arrays.asList(Input.KEY_UP)
		));
		
		// DOWN
		bindings.put("DOWN", new HashSet<Integer>(
				Arrays.asList(Input.KEY_DOWN)
		));
		
		// LEFT
		bindings.put("LEFT", new HashSet<Integer>(
				Arrays.asList(Input.KEY_LEFT)
		));
		
		// RIGHT
		bindings.put("RIGHT", new HashSet<Integer>(
				Arrays.asList(Input.KEY_RIGHT)
		));
		
		// ESCAPE
		bindings.put("ESC", new HashSet<Integer>(
				Arrays.asList(Input.KEY_ESCAPE)
		));
		
		// FIRE
		bindings.put("FIRE", new HashSet<Integer>(
				Arrays.asList(Input.KEY_SPACE)
		));
	}
	
	/**
	 * Listen for input events
	 * @param input The games input
	 * @param event	The event to look for
	 * @throws SlickException
	 */
	public void listen(Input input, GameInputEvent event) throws SlickException {
		for(String key : bindings.keySet()) {
			for(int i : bindings.get(key)) {
				if(input.isKeyDown(i)) {
					event.doActionOnDown(key);
				}
				
				if(input.isKeyPressed(i)) {
					event.doActionOnPressed(key);
				}
			}
		}
	}
}
