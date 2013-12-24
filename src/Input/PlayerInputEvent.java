package Input;

import org.newdawn.slick.SlickException;

import entities.Player;



public class PlayerInputEvent extends GameInputEvent {
	
	private Player player;
	
	public PlayerInputEvent(Player p) {
		player = p;
	}
	
	public void doActionOnDown(String key) throws SlickException {
		switch(key) {
			case "UP": 
				player.moveUp();
				break;
			case "DOWN":
				player.moveDown();
				break;
			case "LEFT": 
				player.moveLeft();
				break;
			case "RIGHT":
				player.moveRight();
				break;
			default: break;
		}
	}

	public void doActionOnPressed(String key) throws SlickException {
		switch(key) {
			case "FIRE":
				player.gun.fire();
				break;
			default: break;
		}
	}

}
