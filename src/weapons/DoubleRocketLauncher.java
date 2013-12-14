package weapons;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Weapon;
import entities.Player;
import core.Collidable;

public class DoubleRocketLauncher extends Weapon {

	private RocketLauncher gun1, gun2;
	
	public DoubleRocketLauncher(Player p,String name) {
		super(name);
		gun1 = new RocketLauncher(p, "gun1");
		gun2 = new RocketLauncher(p, "gun2");
	}

	@Override
	public void fire() throws SlickException {
		System.out.println(gun1);
		gun1.fire();
		gun2.fire();
	}

	@Override
	public void render(Graphics g) {
		gun1.render(g);
		gun2.render(g);
	}

	@Override
	public void update(GameContainer con, float delta) throws SlickException {
		gun1.update(con, delta);
		gun1.posOffset = -10;
		gun2.update(con, delta);
		gun2.posOffset = 10;
		
		this.bullets = new ArrayList<Collidable>(gun1.bullets);
		this.bullets.addAll(gun2.bullets);
	}

}
