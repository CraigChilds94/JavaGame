package animation;

import org.newdawn.slick.Image;

import core.DrawableImage;

public class Frame {
	
	public float newx, newy, duration;
	public DrawableImage obj;
	private Image img;
	
	public Frame(float newx, float newy, float duration, Image img, DrawableImage obj) {
		this.newx = newx;
		this.newy = newy;
		this.duration = duration;
		this.obj = obj;
		this.img = img;
	}
	
	public void visit() {
		this.obj.img = img;
		this.obj.imagePath = img.getResourceReference();
	}
}
