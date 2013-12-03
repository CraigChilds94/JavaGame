package gameobjects;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import entities.Player;
import entities.Ship;
import pickups.Health;

/**
 * Holds information about a wave
 * @author Craig
 */
public class Wave extends GameManager {
	
	public boolean finished = false;
	
	private float initialNumEnemies = 5;
	private float initialNumPickups = 1;
	
	private ArrayList<Entity> enemies, dead;
	private ArrayList<Pickup> pickups, collected;
	
	private Player p;
	
	/**
	 * Construct a new Wave
	 * @param diffMod
	 * @param player
	 * @throws SlickException
	 */
	public Wave(float diffMod, Player player) throws SlickException {
		initialNumEnemies *= diffMod;
		initialNumPickups *= diffMod;
		p = player;
		initialiseEnemyList();
		initialisePickupList();
	}
	
	/**
	 * Generate enemy list
	 * @throws SlickException
	 */
	private void initialiseEnemyList() throws SlickException {
		enemies = new ArrayList<Entity>();
		for(int i = 0; i < initialNumEnemies; i++) {
			enemies.add(new Ship(new Random().nextInt(450) + 30, -(new Random().nextInt(400)) - 50));
		}
	}
	
	/**
	 * Generate pickup list
	 * @throws SlickException
	 */
	private void initialisePickupList() throws SlickException {
		pickups = new ArrayList<Pickup>();
		for(int i = 0; i < initialNumPickups; i++) {
			//pickups.add(new Health(new Random().nextInt(300) + 30, -300));
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
		
		collected = new ArrayList<Pickup>();
		for(Pickup pup : pickups) {
			pup.listenForCollisions(p);
			pup.update(container, delta);
			if(!pup.visible) {
				collected.add(pup);
			}
		}
		pickups.removeAll(collected);
		
		if(enemies.isEmpty()) {
			finished = true;
		}
	}

	/**
	 * Render the enemies and pickups
	 */
	public void render(Graphics g) {
		for(Entity e : enemies) {
			e.render(g);
		}
		
		for(Pickup pup : pickups) {
			pup.render(g);
		}
	}

}
