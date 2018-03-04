package me.afarrukh.miniproject.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import me.afarrukh.miniproject.Manager;

/*
 * We want to create an entity class which defines what
 * an object to be painted onto the display is.
 * An entity may not necessarily be painted onto the screen, it may be invisible but
 * most likely it will be displayed.
 * 
 * We make this an abstract class in order to ensure that when used, only specific 
 * subclasses of this entity can be instantiated
 */
public abstract class Entity {
	
	public static final int DEFAULT_HEALTH = 40;
	
	protected Manager manager;
	protected float x, y; //Making it float achieves smooth movement in our game. Furthermore, the calculations we do in our game will not give whole numbers.
	protected int width, height; //This is the size of the entity
	protected int health;
	protected boolean active = true;
	protected Rectangle hitbox; //The interactable boundaries of which the player can detect if a terrain (tile) is interactable or not
	
	
	public Entity(Manager manager, float x, float y, int width, int height) {
		this.manager = manager;
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		this.health = DEFAULT_HEALTH;
		
		hitbox = new Rectangle(0, 0, width, height);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
	
	public void hurt(int amount) {
		health -= amount;
		System.out.println("Entity has been attacked for " +amount+ " hp. Current hp is " +health);
		if(health <= 0) {
			health = - 1;
			active = false;
			die();
		}
		
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity e: manager.getMap().getEntityManager().getEntities()) {
			if(e.equals(this)) //Checks if the entity that is being checked for collisions is not itself
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
				return true; //If the rectangles of the entity's hitbox intersect with the collisionbounds then there is a collision
		}
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x+hitbox.x+xOffset), (int) (y+hitbox.y+yOffset), hitbox.width, hitbox.height);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	
}
