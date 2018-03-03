package me.afarrukh.miniproject.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import me.afarrukh.miniproject.Handler;

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

	protected Handler handler;
	protected float x, y; //Making it float achieves smooth movement in our game. Furthermore, the calculations we do in our game will not give whole numbers.
	protected int width, height; //This is the size of the entity
	protected Rectangle hitbox; //The interactable boundaries of which the player can detect if a terrain (tile) is interactable or not
	
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		hitbox = new Rectangle(0, 0, width, height);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		for(Entity e: handler.getMap().getEntityManager().getEntities()) {
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
	
	
	
}
