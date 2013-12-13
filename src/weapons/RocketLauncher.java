/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package weapons;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Ammunition;
import core.Collidable;
import core.Drawable;
import core.Weapon;
import ammunition.Rocket;
import entities.Player;

/**
 * Well we need a weapon don't we?
 * @author craig
 */
public class RocketLauncher extends Weapon {
    
    /**
     * Construct a rocket launcher, oooo that sounds fun!
     * @param p
     * @param name
     */
    public RocketLauncher(Player p, String name) {
        super(name);
        width = 10;
        height = 20;
    }
    
    /**
     * Fire it!! (Bored of documenting, can you tell?)
     */
    public void fire() throws SlickException {
        bullets.add(new Rocket(x, y, -1f));
    }
    
    /**
     * Render the awesomenesss..... oh wait this might be in the player image,
     * oh well, render the bullets then...
     */
    public void render(Graphics g) {
        for(Collidable rocket : bullets) {
            if(((Drawable)rocket).visible) {
                ((Drawable)rocket).render(g);
            }
        }
    }
    
    /**
     * Update the weapon
     */
    public void update(Player p, GameContainer con, float delta) throws SlickException {
        x = p.getX() + p.getWidth() / 2;
        y = p.getY();
        
        ArrayList<Rocket> toDestroy = new ArrayList<Rocket>();
        
        for(Collidable rocket : bullets) {
            try {
				if(!((Drawable)rocket).visible) {
				    toDestroy.add((Rocket)rocket);
				} else {
				    ((Drawable)rocket).update(con, delta);
				    if(rocket.getY() < 0) {
				        ((Ammunition)rocket).destroy();
				    }
				}
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
        }
        
        bullets.removeAll(toDestroy);
    }
}
