package me.afarrukh.miniproject.ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

/**
 * 
 * @author Abdullah
 * @description A UI object is an object shown to the user on the main menu, for instance a button for the main menu that starts the game
 */
public abstract class UIObject {

	protected float x, y;
	protected int width, height;
	protected Rectangle hitbox;
	protected boolean hovering = false;
	
	public UIObject(float x, float y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		hitbox = new Rectangle((int) x, (int) y, width, height);
	}
	
	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void onClick(); //A UI Object will be able to recognise when a user is hovering/clicking on it

	
	public void onMouseMove(MouseEvent e) {
		if(hitbox.contains(e.getX(), e.getY()))
			hovering = true;
		else
			hovering = false;
	}
	
	public void onMouseRelease(MouseEvent e) {
		if(hovering)
			onClick(); //Will call the UI object's method that decides what happens when it is clicked on
	}
	
	//Getter/Setter methods
	
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

	public float getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isHovering() {
		return hovering;
	}

	public void setHovering(boolean hovering) {
		this.hovering = hovering;
	}
	
	
}
