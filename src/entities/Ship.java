/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import gameobjects.Entity;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import wavedefender.WaveDefender;

/**
 *
 * @author craig
 */
public class Ship extends Entity {
    
    private boolean dir = true;
    private int damage = 20;
    private float starting_x, moveDistance;
     
    public Ship(float start_x, float start_y) throws SlickException {
        super(start_x, start_y, 25, 25, "res/enemy-1-32.png");
        speed = 0.03f;
        health = 19;
        starting_x = start_x;
        moveDistance = 20;
    }

    @Override
    public void update(GameContainer container, float delta) {
        if(y > container.getHeight() + height) {
            WaveDefender.baseHealth -= this.damage;
            this.alive = false;
        }
        
        if(dir &&x < starting_x + moveDistance) {
        	img.setRotation(-6f);
        	x += delta * (speed / 2);
            if(x > starting_x + moveDistance) {
                dir = false;
            }
        } else if(!dir && x > starting_x - moveDistance){
        	img.setRotation(6f);
        	x -= delta * (speed / 2);
            if(x < starting_x - moveDistance) {
                dir = true;
            }
        }
        
        y += delta * speed;
    }
}