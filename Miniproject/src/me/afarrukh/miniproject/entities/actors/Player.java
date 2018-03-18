package me.afarrukh.miniproject.entities.actors;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.constants.Constants;
import me.afarrukh.miniproject.entities.Entity;
import me.afarrukh.miniproject.gfx.Animation;
import me.afarrukh.miniproject.gfx.Visuals;
import me.afarrukh.miniproject.inventory.Inventory;

public class Player extends Actor {
	
	//Animations
	protected Animation animDown, animUp, animLeft, animRight, animStill, animAtk;
	private long prevAttackTimer, attackCooldown = Constants.playerAtkCd, attackTimer = attackCooldown;
	public boolean attacking; //boolean which determines if animation for attacking is played
	private int charLuck; //If they are a playable character, (at the very least) their attack accuracy has a luck factor
	
	//Inventory
	private Inventory inventory;
	
	public Player(Manager manager, float x, float y) {
		super(manager, x, y, Actor.DEFAULT_ACTOR_WIDTH, Actor.DEFAULT_ACTOR_HEIGHT);
		
		Random random = new Random();
        int rng = random.nextInt(10) - 4; //This luck factor can be negative

        this.charLuck = rng;
        
		hitbox.x = 10;
		hitbox.y = 12;
		hitbox.width = 48;
		hitbox.height = 56;
		
		//Animations
		animDown = new Animation(250, Visuals.mage_down);
		animUp = new Animation(250, Visuals.mage_up);
		animLeft = new Animation(250, Visuals.mage_left);
		animRight = new Animation(250, Visuals.mage_right);
		animStill = new Animation(250, Visuals.mage_still);
		animAtk = new Animation(93, Visuals.mage_attack);
		
		inventory = new Inventory(manager);
		
	}

	@Override
	public void tick() {
		//Animations
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		animStill.tick();
		animAtk.tick();
		//Movement
		getInput();
		move();
		manager.getGameCamera().centerOnEntity(this);
		
		//Attack
		checkAttacks();
		
		//Inventory
		inventory.tick();
	}
	
	/**
	 * Checks if the player is pressing one of the attack keys
	 */
	private void checkAttacks() {
		//We are checking if the user is pressing any of the attack keys
		
		attackTimer += System.currentTimeMillis() - prevAttackTimer;
		prevAttackTimer = System.currentTimeMillis();
		if(attackTimer < attackCooldown) 
			return;
		
		if(inventory.isActive())
			return;
		
		Rectangle collisionBox = getCollisionBounds(0, 0);
		
		Rectangle attackRectangle = new Rectangle();
		
		int arSize = 20; //The size of the rectangle which checks for nearby entities
		attackRectangle.width = arSize;
		attackRectangle.height = arSize;
		
		if(manager.getKeyManager().atkUp) {
			attackRectangle.x = collisionBox.x + collisionBox.width / 2 - arSize/2;
			attackRectangle.y = collisionBox.y - arSize;
		}else if(manager.getKeyManager().atkDown) {
			attackRectangle.x = collisionBox.x + collisionBox.width / 2 - arSize/2;
			attackRectangle.y = collisionBox.y + collisionBox.height;
		}else if(manager.getKeyManager().atkLeft) {
			attackRectangle.x = collisionBox.x - arSize;
			attackRectangle.y = collisionBox.y + collisionBox.height / 2 - arSize /2;
		}else if(manager.getKeyManager().atkRight) {
			attackRectangle.x = collisionBox.x + collisionBox.width;
			attackRectangle.y = collisionBox.y + collisionBox.height / 2 - arSize /2;
		}else {
			return;
		}
		
		attackTimer = 0; //Resetting the timer to setup the next attack and putting it on cooldown
		
		for(Entity e: manager.getMap().getEntityManager().getEntities()) {
			if(e.equals(this)) //cannot attack ourselves
				continue;
			if(e.getCollisionBounds(0, 0).intersects(attackRectangle)) {
				e.hurt(this.getAttackPower());
				return; //We only want to hit one entity at a time. It will hit the first entity in the list that is nearest to it and return
			}
		}
		
	}
	
	public void getInput() {
		
		if(inventory.isActive())
			return;
		
		xMove = 0;
		yMove = 0;
		attacking = false;
		if(manager.getKeyManager().up) {
			yMove = -speed;
		}if(manager.getKeyManager().down) {
			yMove = speed;
		}if(manager.getKeyManager().left) {
			xMove = -speed;
		}if(manager.getKeyManager().right) {
			xMove = speed;
		}
	}
	
	@Override
	public void die() {
		System.out.println("Player has died.");
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(getCurrentAnimationFrame(), (int) (x - manager.getGameCamera().getxOffSet()), (int) (y - manager.getGameCamera().getyOffSet()), width, height, null);
		
		//		g.setColor(Color.RED);
//		
//		g.fillRect((int) (x + hitbox.x - handler.getGameCamera().getxOffSet()),
//				   (int) (y + hitbox.y - handler.getGameCamera().getyOffSet()), 
//						   hitbox.width, hitbox.height);
		
		
		
	}

	//We want this to render on top of everything
	public void postRender(Graphics g) {
		inventory.render(g);
		manager.getGame().getGameTimer().render(g);
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
		}else if(manager.getKeyManager().isAttacking()) {
			return animAtk.getCurrentFrame();
		}else {
			return animStill.getCurrentFrame();
		}
	}

	public int getCharLuck() {
		return charLuck;
	}

	public void setCharLuck(int charLuck) {
		this.charLuck = charLuck;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	
	

}
