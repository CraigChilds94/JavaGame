package animation;

import java.util.HashMap;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.Collidable;
import core.DrawableImage;

public class Animation extends DrawableImage {
	
	public DrawableImage obj;
	public float length = 1f, animationSpeed = 1f, time = 0f;
	public HashMap<Integer, Image> frames;
	private Image current;
	
	public Animation(DrawableImage obj, float length, float speed, float startTime) throws SlickException {
		super(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight(), obj.imagePath);
		this.obj = obj;
		this.frames = new HashMap<Integer, Image>();
		this.frames.put(0, img);
		this.length = length;
		this.animationSpeed = speed;
		this.time = startTime;
		this.current = img;
	}
	
	public void addFrame(int time, String imgPath) throws SlickException {
		frames.put(time, new Image(imgPath));
	}
	
	public void setObjectToAnimate(DrawableImage obj) {
		this.obj = obj;
	}
	
	public Image getCurrentFrame() {
		System.out.println((int)time);
		if(frames.containsKey((int)time)) {
			current = frames.get((int)time);
		}
		
		return current;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(current, obj.getX(), obj.getY());
	}

	@Override
	public void update(GameContainer container, float delta) throws SlickException {
		if(time < length + delta) {
			time += (delta * animationSpeed) / 1000;
		} else {
			time = 0f;
		}
		getCurrentFrame();
	}

	@Override
	public void onCollision(Collidable o) {}
}
