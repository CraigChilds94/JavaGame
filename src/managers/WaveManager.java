package managers;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import wavedefender.WaveDefender;
import entities.Player;
import gameobjects.GameManager;
import gameobjects.GameState;
import gameobjects.Wave;

public class WaveManager extends GameManager {
	
	public static int waveNumber = 0, numWaves = 0;
	private ArrayList<Wave> waves;
	private Player player;
	
	public WaveManager(Player p, int num) throws SlickException {
		waves = new ArrayList<Wave>();
		player = p;
		numWaves = num;
		generateWaves();
	}
	
	private void generateWaves() throws SlickException {
		float diff = 1;
		for(int i = 0; i < numWaves; i++) {
			waves.add(new Wave(diff, player));
			diff += 0.7f;
		}
	}
	
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

	@Override
	public void render(Graphics g) {
		if(waves.size() == 0) {
			return;
		}
		
		waves.get(0).render(g);
	}

}
