package worker;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import entities.Player;

public class PlayerWorker implements Runnable {
	
	private Player p;
	private float delta;
	private GameContainer con;
	
	public PlayerWorker(Player p, GameContainer con, float delta) {
		this.p = p;
		this.delta = delta;
		this.con = con;
	}
	
	@Override
	public void run() {
		// TODO
	}

}
