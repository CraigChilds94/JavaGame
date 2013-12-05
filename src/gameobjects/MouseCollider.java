package gameobjects;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public class MouseCollider extends Collidable {
	
	public boolean mouseClicked = false;
	
	public MouseCollider(float x, float y) {
		super(x, y, 20, 20);
	}

	@Override
	public void onCollision(Collidable o) {}
	
	public void updatePosition(GameContainer con) {
		Input input = con.getInput();
		x = input.getMouseX();
		y = input.getMouseY();
		mouseClicked = input.isMouseButtonDown(0);
		setBounds(x, y, 20, 20);
	}

}
