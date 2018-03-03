package me.afarrukh.miniproject.entities.actors;

import java.awt.Color;
import java.awt.Graphics;

import me.afarrukh.miniproject.Handler;
import me.afarrukh.miniproject.gfx.Assets;

public class Player extends Actor {
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Actor.DEFAULT_ACTOR_WIDTH, Actor.DEFAULT_ACTOR_HEIGHT);
		
		hitbox.x = 2;
		hitbox.y = 12;
		hitbox.width = 24;
		hitbox.height = 24;
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
//		g.setColor(Color.RED);
//		
//		g.fillRect((int) (x + hitbox.x - handler.getGameCamera().getxOffSet()),
//				   (int) (y + hitbox.y - handler.getGameCamera().getyOffSet()), 
//						   hitbox.width, hitbox.height);
		
	}
	
	
	
	

}
