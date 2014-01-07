package game;
import entities.Player;
import gamestate.CompletedState;
import gamestate.GameoverState;
import gamestate.GuideState;
import gamestate.MenuState;
import gamestate.PauseState;
import gamestate.PlayingState;
import gui.Menu;
import gui.MenuItem;

import java.util.logging.Level;
import java.util.logging.Logger;

import levels.GameLevel;
import managers.GameStateManager;
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
    public static int baseHealth = 100;
    
    public GameStateManager gsm;
    
    public static int WIDTH = 640, HEIGHT = 480;

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
        gsm = new GameStateManager();
        gsm.addState(GameState.MENU, new MenuState());
        gsm.addState(GameState.PAUSED, new PauseState());
        gsm.addState(GameState.COMPLETED, new CompletedState());
        gsm.addState(GameState.GAMEOVER, new GameoverState());
        gsm.addState(GameState.PLAYING, new PlayingState());
        gsm.addState(GameState.GUIDE, new GuideState());
        System.out.println("Game initialized!");
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
    	
    	gsm.update(container, delta, null);        
    }

    /**
     * Render everything!!
     */
    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {
    	gsm.render(g);
    }
    
}
