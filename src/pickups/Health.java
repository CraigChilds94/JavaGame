package pickups;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import entities.Player;
import gameobjects.Pickup;
import wavedefender.WaveDefender;

public class Health extends Pickup {
	
	public Health(float x, float y)
			throws SlickException {
		super(x, y, 0, 0, "res/health-32.png");
		width = img.getWidth();
		height = img.getHeight();

	}

	@Override
	public void doFunctionality(Player p) {
		WaveDefender.baseHealth += modifierValue;
	}

	@Override
	public void update(GameContainer container, float delta) throws SlickException {
		y += delta * speed;
		if(y > container.getHeight()) {
			visible = false;
		}
	}

}
