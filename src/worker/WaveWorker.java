package worker;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import entities.Player;
import managers.WaveManager;

public class WaveWorker implements Runnable {
	
	private WaveManager wm;
	private GameContainer con;
	private float delta;
	private Player player;
	
	public WaveWorker(WaveManager wm, GameContainer con, float delta, Player p) {
		this.wm = wm;
		this.con = con;
		this.delta = delta;
		this.player = p;
	}
	
	@Override
	public void run() {
		// TODO
	}

}
