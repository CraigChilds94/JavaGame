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

import pathfinding.Node;
import pathfinding.PathNavigator;
import core.GameState;
import tile.PathableTilemap;
import tile.TileMap;
import worker.PlayerWorker;
import worker.WaveWorker;
import entities.Player;
import game.Game;

public class PlayingState extends State {
	
	public WaveManager wm;
    public LevelManager lm;
    public Player p;
    public PathableTilemap tilemap;
    PathNavigator navi;
	
	public PlayingState() throws SlickException {
		p = new Player(0, 3*32);
    	lm = new LevelManager();
    	lm.addLevel(new GameLevel(0f,0f,0f,0f));
        wm = new WaveManager(p, 10);
	}
	
	@Override
	public void update(GameContainer c, float delta) throws SlickException {
		tilemap.update(c, delta);
		
		if(navi.current() != null) {
			boolean a = p.moveToPoint(navi.current().x * 32, navi.current().y * 32);
			if(a) {
				navi.next();
			}
		}
		
		if(navi.atEnd()) {
			navi.addPath(tilemap.p.findPath(3, 7, 2, 12));
		}
		
		p.update(c, delta);
		//lm.update(c, delta, p);
		//wm.update(c, delta, p);

        
        if(Game.baseHealth < 0) {
        	try {
				GameStateManager.set(GameState.GAMEOVER);
			} catch (Exception e) {}
        }
        
        if(c.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
        	try {
				GameStateManager.set(GameState.PAUSED);
			} catch (Exception e) {}
        }
	}

	@Override
	public void render(Graphics g) {
		//lm.render(g);
        tilemap.render(g);
        p.render(g);
        //wm.render(g);
        
        g.setColor(new Color(100, 100, 200));
        g.drawString("WAVE:" + (WaveManager.waveNumber + 1), 10, 30);
        g.drawString("Base Health:" + Game.baseHealth, 10, 50);
	}

	@Override
	public void onLoad() throws Exception, SlickException {
        tilemap = new PathableTilemap("test", 0, 0, Game.WIDTH, Game.HEIGHT);
        navi = new PathNavigator(tilemap.p.findPath(3, 0, 3, 7));
        for(Node n : tilemap.p.findPath(3, 7, 2, 12)){
        	System.out.println(n.x + " : " + n.y);
		}
	}
	
	@Override
	public void onLeave() {}

}