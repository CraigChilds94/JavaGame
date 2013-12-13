package levels;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Collidable;
import core.Drawable;
import core.DrawableImage;
import entities.Player;

public class GameLevel extends Drawable {
	
	private Player player;
	private float speed;
	private ArrayList<DrawableImage> levelImages;
	
	
	public GameLevel(float x, float y, float width, float height) throws SlickException {
		super(x, y, width, height);
		levelImages = new ArrayList<DrawableImage>();
		generateImages();
	}

	@Override
	public void render(Graphics g) {
		for(DrawableImage img : levelImages) {
			img.render(g);
		}
	}
	
	@Override
	public void update(GameContainer container, float delta) throws SlickException {
		speed = 0.5f - player.getDeltaY();
		for(DrawableImage img : levelImages) {
			img.update(container, speed);
		}
	}

	@Override
	public void onCollision(Collidable o) {}

	public void setPlayer(Player p) {
		player = p;
	}
	
	private void generateImages() throws SlickException {
		DrawableImage levelImg1 = new DrawableImage(0, 0, 0, 0, "res/space-bg.png") {

			@Override
			public void update(GameContainer container, float delta) throws SlickException {
				y += delta;
				
				if(y > container.getHeight()) {
					y = -img.getHeight();
				}
			}

			@Override
			public void onCollision(Collidable o) {}
			
		};
		
		DrawableImage levelImg2 = new DrawableImage(0, -1000, 0, 0, "res/space-bg.png") {

			@Override
			public void update(GameContainer container, float delta) throws SlickException {
				y += delta;
				
				if(y > container.getHeight()) {
					y = -img.getHeight();
				}
			}

			@Override
			public void onCollision(Collidable o) {}
			
		};
		
		levelImages.add(levelImg1);
		levelImages.add(levelImg2);
	}
}
