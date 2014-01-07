package gamestate;

import levels.GameLevel;
import managers.GameStateManager;
import managers.LevelManager;
import managers.WaveManager;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import core.GameState;
import tile.TileMap;
import entities.Player;
import game.Game;

public class PlayingState extends State {
	
	public WaveManager wm;
    public LevelManager lm;
    public Player p;
    public TileMap tilemap;
	
	public PlayingState() throws SlickException {
		p = new Player(300, 400);
    	lm = new LevelManager();
    	lm.addLevel(new GameLevel(0f,0f,0f,0f));
        wm = new WaveManager(p, 10);
        tilemap = new TileMap("test", 0, 0, Game.WIDTH, Game.HEIGHT);
	}
	
	@Override
	public void update(GameContainer c, float delta) throws SlickException {
		tilemap.update(c, delta);
        p.update(c, delta);
        wm.update(c, delta, p);
        lm.update(c, delta, p);

        
        if(Game.baseHealth < 0) {
            GameStateManager.state = GameState.GAMEOVER;
        }
        
        if(c.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
        	GameStateManager.state = GameState.PAUSED;
        }
	}

	@Override
	public void render(Graphics g) {
		lm.render(g);
        tilemap.render(g);
        p.render(g);
        wm.render(g);
        
        g.setColor(new Color(100, 100, 200));
        g.drawString("WAVE:" + (WaveManager.waveNumber + 1), 10, 30);
        g.drawString("Base Health:" + Game.baseHealth, 10, 50);
	}

}
