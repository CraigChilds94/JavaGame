package levels;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
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
		generateImages(2);
	}

	@Override
	public void render(Graphics g) {
		for(DrawableImage img : levelImages) {
			img.render(g);
		}
	}
	
	@Override
	public void update(GameContainer container, float delta) throws SlickException {
		for(DrawableImage img : levelImages) {
			img.update(container, delta);
		}
	}

	@Override
	public void onCollision(Collidable o) {}

	public void setPlayer(Player p) {
		player = p;
	}
	
	private void generateImages(int numImages) throws SlickException {
		float diff = 0;
		Image image = new Image("res/scenery/space-bg.png");
		for(int i = 0; i < numImages; i++) {
			DrawableImage img = new DrawableImage(0, diff, image) {

				@Override
				public void update(GameContainer container, float delta) throws SlickException {
					y += 0.5f;
					
					if(y > container.getHeight()) {
						y = -img.getHeight();
					}
				}

				@Override
				public void onCollision(Collidable o) {}
				
			};
			diff -= img.getHeight();
			levelImages.add(img);
		}
	}
}
