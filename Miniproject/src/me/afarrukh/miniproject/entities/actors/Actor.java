package me.afarrukh.miniproject.entities.actors;

import me.afarrukh.miniproject.Handler;
import me.afarrukh.miniproject.entities.Entity;

/*
 * This class extends the entity class and can be further subclassed into classes which define
 * things such as our main character, monsters, enemy characters and so on.
 */

public abstract class Actor extends Entity{
	
	public static final int DEFAULT_HEALTH = 100;
	public static final float DEFAULT_MOVESPEED = 4.0f;
	public static final int DEFAULT_ACTOR_WIDTH = 32,
							DEFAULT_ACTOR_HEIGHT = 32;
	
	protected int health;
	protected float speed;
	protected float xMove, yMove; //How much the characters move by in pixels
	
	public Actor(Handler handler, float x, float y, int width, int height) {
		
		super(handler, x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_MOVESPEED;
		xMove = 0;
		yMove = 0;
		
	}
	
	public void move() {
		x += xMove;
		y += yMove;
	}

	//Getter/Setter methods for this class
	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float moveSpeed) {
		this.speed = moveSpeed;
	}
}
