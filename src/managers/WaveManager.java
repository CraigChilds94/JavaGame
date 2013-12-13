package managers;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.GameManager;
import core.GameState;
import wavedefender.WaveDefender;
import waves.BossWave;
import waves.Wave;
import entities.Player;

/**
 * Manage the waves
 * @author Craig
 *
 */
public class WaveManager extends GameManager {
	
	public static int waveNumber = 0, numWaves = 0;
	private ArrayList<Wave> waves;
	private Player player;
	
	/**
	 * Construct a new Wave Manager
	 * @param p Is the player 
	 * @param num The number of waves
	 * @throws SlickException
	 */
	public WaveManager(Player p, int num) throws SlickException {
		waves = new ArrayList<Wave>();
		player = p;
		numWaves = num;
		generateWaves();
	}
	
	/**
	 * Generate the waves for this manager
	 * @throws SlickException
	 */
	private void generateWaves() throws SlickException {
		float diff = 1;
		for(int i = 1; i <= numWaves; i++) {
			
			if(i % 5 == 0 && i > 1) {
				waves.add(new BossWave(diff, player));
			} else {
				waves.add(new Wave(diff, player));
			}
			
			diff += 0.7f;
		}
	}
	
	/**
	 * Update the wave
	 */
	@Override
	public void update(GameContainer c, float delta, Player p) throws SlickException {
		if(waves.size() == 0) {
			WaveDefender.gamestate = GameState.COMPLETED;
			return;
		}
		
		waves.get(0).update(c, delta, p);
		if(waves.get(0).finished) {
			waveNumber++;
			waves.remove(0);
		}
	}
	
	/**
	 * Render the Wave
	 */
	@Override
	public void render(Graphics g) {
		if(waves.size() == 0) {
			return;
		}
		
		waves.get(0).render(g);
	}
}
