package waves;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Entity;
import core.GameManager;
import entities.Player;
import entities.Ship;
import pickups.Health;
import pickups.Pickup;

/**
 * Holds information about a wave
 * @author Craig
 */
public class Wave extends GameManager {
	
	public boolean finished = false;
	
	protected float initialNumEnemies = 5;
	
	protected ArrayList<Entity> enemies, dead;	
	protected Player p;
	
	/**
	 * Construct a new Wave
	 * @param diffMod
	 * @param player
	 * @throws SlickException
	 */
	public Wave(float diffMod, Player player) throws SlickException {
		initialNumEnemies *= diffMod;
		p = player;
		initialiseEnemyList(diffMod);
	}
	
	/**
	 * Generate enemy list
	 * @throws SlickException
	 */
	protected void initialiseEnemyList(float diff) throws SlickException {
		enemies = new ArrayList<Entity>();
		for(int i = 0; i < initialNumEnemies; i++) {
			enemies.add(new Ship(
					new Random().nextInt(450) + 30,
					-(new Random().nextInt(400)) - 50,
					diff
			));
		}
	}
	
	/**
	 * Update everything on the wave
	 */
	public void update(GameContainer container, float delta, Player p) throws SlickException {
		this.p = p;
		dead = new ArrayList<Entity>();
		for(Entity e : enemies) {
			e.listenForCollisions(p.gun.bullets);
			e.update(container, delta);
			if(!e.alive) {
                dead.add(e);
            }
		}
		enemies.removeAll(dead);
		
		if(enemies.isEmpty()) {
			finished = true;
		}
	}

	/**
	 * Render the enemies
	 */
	public void render(Graphics g) {
		for(Entity e : enemies) {
			e.render(g);
		}
	}

}
