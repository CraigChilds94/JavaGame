package gameobjects;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import entities.Player;
import entities.Ship;
import pickups.Health;

public class Wave extends GameManager {
	
	public boolean finished = false;
	
	private float initialNumEnemies = 5;
	private float initialNumPickups = 1;
	
	private ArrayList<Entity> enemies, dead;
	private ArrayList<Pickup> pickups, collected;
	
	private Player p;
	
	public Wave(float diffMod, Player player) throws SlickException {
		initialNumEnemies *= diffMod;
		initialNumPickups *= diffMod;
		p = player;
		initialiseEnemyList();
		initialisePickupList();
	}
	
	private void initialiseEnemyList() throws SlickException {
		enemies = new ArrayList<Entity>();
		for(int i = 0; i < initialNumEnemies; i++) {
			enemies.add(new Ship(new Random().nextInt(450) + 30, -(new Random().nextInt(400)) - 50));
		}
	}
	
	private void initialisePickupList() throws SlickException {
		pickups = new ArrayList<Pickup>();
		for(int i = 0; i < initialNumPickups; i++) {
			//pickups.add(new Health(new Random().nextInt(300) + 30, -300));
		}
	}
	
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
			System.out.println(pup.getY());
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

	public void render(Graphics g) {
		for(Entity e : enemies) {
			e.render(g);
		}
		
		for(Pickup pup : pickups) {
			pup.render(g);
		}
	}

}
