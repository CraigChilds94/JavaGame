package wavedefender;
import entities.Player;
import gui.Menu;
import gui.MenuItem;

import java.util.logging.Level;
import java.util.logging.Logger;

import levels.GameLevel;
import managers.LevelManager;
import managers.WaveManager;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import tile.TileMap;
import core.GameState;

/**
 * Game game
 * @author craig
 */
public class Game extends BasicGame {
    
    public static String VERSION = "0.3";
    public Player p;
    public static int baseHealth = 100;
    public static GameState gamestate = GameState.MENU;
    
    public Menu menu;
    
    public WaveManager wm;
    public LevelManager lm;
    
    public static int WIDTH = 640, HEIGHT = 480;
    
    public TileMap tilemap;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Meh
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Game("Game - " + Game.VERSION));
            appgc.setDisplayMode(WIDTH, HEIGHT, false);
            appgc.setIcon("res/player/ship-16.png");
            //appgc.setShowFPS(false);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Construct a new game
     * @param title
     * @throws SlickException
     */
    public Game(String title) throws SlickException {
        super(title);
    }
    
    /**
     * Initialise game objs
     */
    @Override
    public void init(GameContainer container) throws SlickException {
    	System.out.println("Game initializing...");
    	Color[] colors = new Color[]{
    			// Normal
    			new Color(255, 255, 255),
    			// Selected
    			new Color(106,159,235),
    			//Pressed
    			new Color(157,227,116)
    	};
    	
    	menu = new Menu();
        menu.addMenuItem(new MenuItem("Play", 220, 100, 200, 20, colors, GameState.PLAYING));
        menu.addMenuItem(new MenuItem("Options", 220, 150, 200, 20, colors, GameState.OPTIONS));
        menu.addMenuItem(new MenuItem("Guide", 220, 200, 200, 20, colors, GameState.GUIDE));
        menu.addMenuItem(new MenuItem("Exit", 220, 250, 200, 20, colors, GameState.EXIT));
    	p = new Player(300, 400);
    	lm = new LevelManager();
    	lm.addLevel(new GameLevel(0f,0f,0f,0f));
        wm = new WaveManager(p, 10);
        System.out.println("Game initialized!");
        
        
        tilemap = new TileMap("test", 0, 0, WIDTH, HEIGHT);
    }

    /**
     * update everything!!
     */
    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        
    	// Pause when not focused
    	if(!container.hasFocus()) {
    		return;
    	}
    	
    	// MENU
        if(Game.gamestate == GameState.MENU) {
            menu.update(container, delta);
            return;
        }
        
        // PAUSED
        if(Game.gamestate == GameState.PAUSED) {
        	if(container.getInput().isKeyPressed(Input.KEY_SPACE)) {
                Game.gamestate = GameState.PLAYING;
            }
        	return;
        }
        
        if(Game.gamestate == GameState.GUIDE) {
        	if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
                Game.gamestate = GameState.MENU;
            }
        	return;
        }
        
        // GAME OVER
        if(Game.gamestate == GameState.GAMEOVER || Game.gamestate == GameState.COMPLETED) {
            return;
        }
        
        tilemap.update(container, delta);
        p.update(container, delta);
        wm.update(container, delta, p);
        lm.update(container, delta, p);

        
        if(Game.baseHealth < 0) {
            Game.gamestate = GameState.GAMEOVER;
        }
        
        if(container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            Game.gamestate = GameState.PAUSED;
        }
    }

    /**
     * Render everything!!
     */
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	
    	if(Game.gamestate == GameState.MENU) {
            menu.render(g);
            return;
        }
        
        if(Game.gamestate == GameState.PAUSED) {
        	g.setColor(new Color(200, 100, 100));
            g.drawString("Game Paused", 100, 100);
            g.setColor(new Color(200, 200, 200));
            g.drawString("Press Space to resume!", 100, container.getHeight() / 2);
            return;
        }
        
        if(Game.gamestate == GameState.GAMEOVER) {
            g.setColor(new Color(200, 100, 100));
            g.drawString("GAME OVERRRRR!", 100, container.getHeight() / 2);
            return;
        }
        
        if(Game.gamestate == GameState.COMPLETED) {
        	g.setColor(new Color(100, 100, 200));
            g.drawString("CONGRATULATIONS!!! WELL DONE!!!!", 100, container.getHeight() / 2);
            return;
        }
        
        if(Game.gamestate == GameState.GUIDE) {
        	g.drawString("Guide", 100, 100);
        	g.drawString(" - Use arrow keys to move", 120, 140);
        	g.drawString(" - Space bar to shoot", 120, 155);
        	g.drawString(" - Escape key to pause the game", 120, 170);
        	g.drawString("Press Escape to go back to the menu", 100, 250);
        	return;
        }
        
        
        lm.render(g);
        tilemap.render(g);
        p.render(g);
        wm.render(g);
        
        g.setColor(new Color(100, 100, 200));
        g.drawString("WAVE:" + (WaveManager.waveNumber + 1), 10, 30);
        g.drawString("Base Health:" + Game.baseHealth, 10, 50);

    }
    
}
