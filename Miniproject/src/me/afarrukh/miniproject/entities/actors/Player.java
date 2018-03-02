package me.afarrukh.miniproject.entities.actors;

import java.awt.Graphics;

import me.afarrukh.miniproject.Handler;
import me.afarrukh.miniproject.gfx.Assets;

public class Player extends Actor {
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Actor.DEFAULT_ACTOR_WIDTH, Actor.DEFAULT_ACTOR_HEIGHT);
	}

	@Override
	public void tick() {
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
		
		g.drawImage(Assets.fighter, (int) (x - handler.getGameCamera().getxOffSet()), (int) (y - handler.getGameCamera().getyOffSet()), width, height, null);
		
		
	}
	
	
	
	

}
