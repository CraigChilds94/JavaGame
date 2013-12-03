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

import ammunition.Rocket;
import entities.Player;
import gameobjects.Ammunition;
import gameobjects.Collidable;
import gameobjects.Drawable;
import gameobjects.Weapon;

/**
 *
 * @author craig
 */
public class RocketLauncher extends Weapon {
    

    public RocketLauncher(Player p, String name) {
        super(name);
        width = 10;
        height = 20;
    }
    
    public void fire() throws SlickException {
        bullets.add(new Rocket(x, y, -0.5f));
    }
    
    public void render(Graphics g) {
        //g.setColor(new Color(200, 200, 200));
        //g.fillRect(x - (width / 2), y, width, height);
        for(Collidable rocket : bullets) {
            if(((Drawable)rocket).visible) {
                ((Drawable)rocket).render(g);
            }
        }
    }
    
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