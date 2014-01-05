package animation;

import java.util.HashMap;

import list.LinkedList;
import list.Node;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import core.Collidable;
import core.DrawableImage;

public class Animation extends DrawableImage {
	
	public Frame startingFrame, current;
	public float length = 1f, animationSpeed = 1f, time = 0f;
	public HashMap<Integer, Frame> frames;
	public boolean visible = true;
	public LinkedList<Frame> frameList;
	private boolean loops;
	
	public Animation(Frame startingFrame, float length, float speed, float startTime) throws SlickException {
		super(startingFrame.obj.getX(), startingFrame.obj.getY(), startingFrame.obj.getWidth(), startingFrame.obj.getHeight(), startingFrame.obj.imagePath);
		this.startingFrame = startingFrame;
		
		this.frameList = new LinkedList<Frame>(
				new Node<Frame>(
						this.startingFrame,
						null,
						null
				)
		);

		this.animationSpeed = speed;
		getCurrentFrame();
	}
	
	public void addFrame(Frame f) throws SlickException {
		frameList.add(f);
		if(this.loops) {
			frameList.getTail().next = frameList.getHead();
		}
	}
	
	public Frame getCurrentFrame() {
		if(frameList.getCurrent() != null) {
			current = frameList.getCurrent().getData();
		}
		return current;
	}
	
	public Frame getNextFrame() {
		Node next = frameList.getNext();
		if(next != null) {
			current = (Frame) next.getData();
		}
		return current;
	}

	@Override
	public void render(Graphics g) {
		if(!visible) {
			return;
		}
		
		g.drawImage(current.obj.img, current.obj.getX(), current.obj.getY());
	}

	@Override
	public void update(GameContainer container, float delta) throws SlickException {
		if(!visible) {
			return;
		}
		
		if(time < getCurrentFrame().duration + delta) {
			time += (delta * animationSpeed) / 1000;
		} else {
			time = 0f;
			getNextFrame();
			if(current == null) {
				visible = false;
				return;
			}
			current.visit();
		}
	}

	@Override
	public void onCollision(Collidable o) {}
	
	public void setLoop(boolean flag) {
		this.loops = flag;
	}
}
