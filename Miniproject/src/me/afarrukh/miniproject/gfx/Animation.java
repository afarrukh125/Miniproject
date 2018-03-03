package me.afarrukh.miniproject.gfx;

import java.awt.image.BufferedImage;
/**
 * 
 * @author Abdullah
 * This loops an animation for a given entity/tile in MILLISECONDS
 *  
 */
public class Animation {

	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	
	public Animation(int speed, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
		
	}
	
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed) {
			index++;
			timer = 0;
			if(index >= frames.length) //Loops the animation
				index = 0;
		}
	}
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
}
