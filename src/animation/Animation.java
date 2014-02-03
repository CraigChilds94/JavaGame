package animation;

import java.util.HashMap;

import list.LinkedList;
import list.Node;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import core.Collidable;
import core.DrawableImage;

/**
 * Animation helper class which allows you to define a set of images 
 * which represent frames to walk through over each update
 * @author craig
 */
public class Animation extends DrawableImage {
	
	public Frame startingFrame, current;
	public float length = 1f, animationSpeed = 1f, time = 0f;
	public HashMap<Integer, Frame> frames;
	public boolean visible = true;
	public LinkedList<Frame> frameList;
	private boolean loops;
	
	/**
	 * Construct a new animation
	 * @param startingFrame The frame which the animatio will start on
	 * @param length The length of the animation before it will reloop
	 * @param speed The speed of the animation
	 * @param startTime Where in the animation it will start
	 * @throws SlickException 
	 */
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
		current.visit();
	}
	
	/**
	 * Add a new frame to the animation
	 * @param f The frame
	 * @throws SlickException
	 */
	public void addFrame(Frame f) throws SlickException {
		frameList.add(f);
		if(this.loops) {
			frameList.getTail().next = frameList.getHead();
		}
	}
	
	/**
	 * Get the frame which the animation is currently showing
	 * @return The frame
	 */
	public Frame getCurrentFrame() {
		if(frameList.getCurrent() != null) {
			current = frameList.getCurrent().getData();
		}
		return current;
	}
	
	/**
	 * Move to the next frame and return it
	 * @return the next frame
	 */
	public Frame getNextFrame() {
		Node next = frameList.getNext();
		if(next != null) {
			current = (Frame) next.getData();
		}
		return current;
	}

	/**
	 * Render the animation
	 */
	@Override
	public void render(Graphics g) {
		if(!visible) {
			return;
		}
		
		g.drawImage(current.obj.img, current.obj.getX(), current.obj.getY());
	}

	/**
	 * Update the animation
	 */
	@Override
	public void update(GameContainer container, float delta) throws SlickException {
		if(!visible) {
			return;
		}
		
		// move on over time unless a new frame needs to be drawn
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

	/**
	 * Does not require collision detection at this level
	 */
	@Override
	public void onCollision(Collidable o) {}
	
	/**
	 * Set whether or not this is a looping animation
	 * @param flag true or false
	 */
	public void setLoop(boolean flag) {
		this.loops = flag;
	}
}
