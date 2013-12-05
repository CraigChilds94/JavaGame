package managers;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import entities.Player;
import entities.Ship;
import gameobjects.Wave;

public class BossWave extends Wave {

	public BossWave(float diffMod, Player player) throws SlickException { 
		super(diffMod, player);
		this.initialNumEnemies = 0;
		this.enemies.add(new MegaShip(200, -500));
	}
	
}
