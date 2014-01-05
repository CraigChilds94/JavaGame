package input;

import org.newdawn.slick.SlickException;

public abstract class GameInputEvent {
	public abstract void doActionOnDown(String key) throws SlickException;
	public abstract void doActionOnPressed(String key) throws SlickException;
}