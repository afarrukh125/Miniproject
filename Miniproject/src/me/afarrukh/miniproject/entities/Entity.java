package me.afarrukh.miniproject.entities;

import java.awt.Graphics;

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

	protected float x, y; //Making it float achieves smooth movement in our game. Furthermore, the calculations we do in our game will not give whole numbers.
	protected int width, height; //This is the size of the entity
	
	public Entity(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);

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
