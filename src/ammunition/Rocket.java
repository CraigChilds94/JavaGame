/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ammunition;

import gameobjects.Ammunition;
import gameobjects.Collidable;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Rocket type of ammo
 * @author craig
 */
public class Rocket extends Ammunition {
	
    public Rocket(float x, float y, float speed) throws SlickException {
        super(x - 2, y, 4, 4, speed, 20, "res/bullet-16.png");
    }

    @Override
    public void update(GameContainer container, float delta) {
        this.y += delta * speed;
        this.setBounds(x, y, width, height);
    }

    @Override
    public void onCollision(Collidable o) {
        this.destroy();
    }
}