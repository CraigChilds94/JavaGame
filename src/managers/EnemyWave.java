package managers;

import entities.Ship;
import gameobjects.Entity;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.SlickException;

/**
 *
 * @author craig
 */
public class EnemyWave {
    private ArrayList<Entity> enemies;
    private int baseNumEnemies, numEnemies;
    
    public EnemyWave(float difficulty, int from, int to) throws SlickException {
        enemies = new ArrayList<Entity>();
        baseNumEnemies = 5;
        numEnemies = (int) (baseNumEnemies * difficulty);
        for(int i = 0; i < numEnemies; i++) {
            int x = new Random().nextInt(to) + from;
            enemies.add(new Ship(x, -(new Random().nextInt(300)) - 50));
        }
    }
    
    public ArrayList<Entity> getEnemies() {
        return this.enemies;
    }
}
