package me.afarrukh.miniproject.entities.actors;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import me.afarrukh.miniproject.Handler;
import me.afarrukh.miniproject.entities.Entity;
import me.afarrukh.miniproject.gfx.Animation;
import me.afarrukh.miniproject.gfx.Assets;

public class Player extends Actor {
	
	//Animations
	private Animation animDown, animUp, animLeft, animRight, animStill;
	private long prevAttackTimer, attackCooldown = 200, attackTimer = attackCooldown;
	
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Actor.DEFAULT_ACTOR_WIDTH, Actor.DEFAULT_ACTOR_HEIGHT);
		
		hitbox.x = 10;
		hitbox.y = 12;
		hitbox.width = 48;
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
		
		//Attack
		checkAttacks();
	}
	
	private void checkAttacks() {
		//We are checking if the user is pressing any of the attack keys
		
		attackTimer += System.currentTimeMillis() - prevAttackTimer;
		prevAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown) 
			return;
		
		Rectangle collisionBox = getCollisionBounds(0, 0);
		
		Rectangle attackRectangle = new Rectangle();
		
		int arSize = 20; //The size of the rectangle which checks for nearby entities
		attackRectangle.width = arSize;
		attackRectangle.height = arSize;
		
		if(handler.getKeyManager().atkUp) {
			attackRectangle.x = collisionBox.x + collisionBox.width / 2 - arSize/2;
			attackRectangle.y = collisionBox.y - arSize;
		}else if(handler.getKeyManager().atkDown) {
			attackRectangle.x = collisionBox.x + collisionBox.width / 2 - arSize/2;
			attackRectangle.y = collisionBox.y + collisionBox.height;
		}else if(handler.getKeyManager().atkLeft) {
			attackRectangle.x = collisionBox.x - arSize;
			attackRectangle.y = collisionBox.y + collisionBox.height / 2 - arSize /2;
		}else if(handler.getKeyManager().atkRight) {
			attackRectangle.x = collisionBox.x + collisionBox.width;
			attackRectangle.y = collisionBox.y + collisionBox.height / 2 - arSize /2;
		}else {
			return;
		}
		
		attackTimer = 0; //Resetting the timer to setup the next attack and putting it on cooldown
		
		for(Entity e: handler.getMap().getEntityManager().getEntities()) {
			if(e.equals(this)) //cannot attack ourselves
				continue;
			if(e.getCollisionBounds(0, 0).intersects(attackRectangle)) {
				e.hurt(1);
				return; //We only want to hit one entity at a time. It will hit the first entity in the list that is nearest to it and return
			}
		}
		
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
	public void die() {
		System.out.println("Player has died.");
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
