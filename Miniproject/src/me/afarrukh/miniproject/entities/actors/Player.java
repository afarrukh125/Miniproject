package me.afarrukh.miniproject.entities.actors;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import me.afarrukh.miniproject.Handler;
import me.afarrukh.miniproject.gfx.Animation;
import me.afarrukh.miniproject.gfx.Assets;

public class Player extends Actor {
	
	//Animations
	private Animation animDown, animUp, animLeft, animRight, animStill;
	
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Actor.DEFAULT_ACTOR_WIDTH, Actor.DEFAULT_ACTOR_HEIGHT);
		
		hitbox.x = 2;
		hitbox.y = 12;
		hitbox.width = 56;
		hitbox.height = 56;
		
		//Animations
		animDown = new Animation(250, Assets.archer_down);
		animUp = new Animation(250, Assets.archer_up);
		animLeft = new Animation(250, Assets.archer_left);
		animRight = new Animation(250, Assets.archer_right);
		animStill = new Animation(250, Assets.archer_still);
		
	}

	@Override
	public void tick() {
		//Animations
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		animStill.tick();
		//Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
	}
	
	public void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().up) {
			yMove = -speed;
		}
		if(handler.getKeyManager().down) {
			yMove = speed;
		}
		if(handler.getKeyManager().left) {
			xMove = -speed;
		}
		if(handler.getKeyManager().right) {
			xMove = speed;
		}
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffSet()), (int) (y - handler.getGameCamera().getyOffSet()), width, height, null);
		
		//		g.setColor(Color.RED);
//		
//		g.fillRect((int) (x + hitbox.x - handler.getGameCamera().getxOffSet()),
//				   (int) (y + hitbox.y - handler.getGameCamera().getyOffSet()), 
//						   hitbox.width, hitbox.height);
		
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		if(xMove < 0) {
			//if we are moving to the left
			return animLeft.getCurrentFrame();
		}else if(xMove > 0) {
			return animRight.getCurrentFrame();
		}else if(yMove < 0) {
			return animUp.getCurrentFrame();
		}else if(yMove > 0){
			return animDown.getCurrentFrame();
		}else {
			return animStill.getCurrentFrame();
		}
	}
	
	
	
	

}
