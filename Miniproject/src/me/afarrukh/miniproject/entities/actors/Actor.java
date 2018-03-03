package me.afarrukh.miniproject.entities.actors;

import me.afarrukh.miniproject.Handler;
import me.afarrukh.miniproject.entities.Entity;
import me.afarrukh.miniproject.tiles.Tile;

/*
 * This class extends the entity class and can be further subclassed into classes which define
 * things such as our main character, monsters, enemy characters and so on.
 */

public abstract class Actor extends Entity{
	
	public static final int DEFAULT_HEALTH = 100;
	public static final float DEFAULT_MOVESPEED = 4.0f;
	public static final int DEFAULT_ACTOR_WIDTH = 64,
							DEFAULT_ACTOR_HEIGHT = 64;
	
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
		if(!checkEntityCollisions(xMove, 0f))
			moveX();
		if(!checkEntityCollisions(0f, yMove))
			moveY();
	}
	
	
	//tx and ty refer to temporary x and y variables in which we check the collision status of a tile
	public void moveX() {
		if(xMove > 0) { //Right side with collision detection
			int tx = (int) (x + xMove + hitbox.x + hitbox.width) /Tile.TILEWIDTH; //Dividing by tile width to convert between pixels and tile units
			
			if(!collisionWithTile(tx, (int) (y + hitbox.y) /Tile.TILEHEIGHT) &&
					!collisionWithTile(tx, (int) (y + hitbox.y + hitbox.height) / Tile.TILEHEIGHT)) {
				//&& statement checks both upper and lower right corner of hitbox
				x += xMove; //Only move if the tile being moved to is not solid
			}
			else {
				//Subtracted 1 in below line to allow sliding, surprisingly not required in moving left
				x = tx * Tile.TILEWIDTH - hitbox.x - hitbox.width - 1; //Ensures the hitbox matches perfectly
				//We are taking away the hitbox x and the width so the character does not instantly align with the tile
			}
				
		} else if(xMove < 0) { //Left side movement with collision detection
			int tx = (int) (x + xMove + hitbox.x) /Tile.TILEWIDTH; //Dividing by tile width to convert between pixels and tile units
			
			if(!collisionWithTile(tx, (int) (y + hitbox.y) /Tile.TILEHEIGHT) &&
					!collisionWithTile(tx, (int) (y + hitbox.y + hitbox.height) / Tile.TILEHEIGHT)) {
				//&& statement checks both upper and lower right corner of hitbox
				x += xMove; //Only move if the tile being moved to is not solid
			}
			else {
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - hitbox.x;
			}
		}
	}
	
	public void moveY() {
		if(yMove < 0) { //Up side with collision detection
			int ty = (int) (y + yMove + hitbox.y) /Tile.TILEHEIGHT;
			
			if(!collisionWithTile((int) (x + hitbox.x)/Tile.TILEWIDTH, ty) &&
					!collisionWithTile((int) (x + hitbox.x + hitbox.width)/Tile.TILEWIDTH, ty)) { //Upper left hand of hitbox
				y += yMove;
			}
			else {
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - hitbox.y;
			}
				
		} else if(yMove > 0) { //Down side with collision detection
			int ty = (int) (y + yMove + hitbox.y + hitbox.height) /Tile.TILEHEIGHT; //For the lower portion of the hitbox, we add the hitbox height
			
			if(!collisionWithTile((int) (x + hitbox.x)/Tile.TILEWIDTH, ty) &&
					!collisionWithTile((int) (x + hitbox.x + hitbox.width)/Tile.TILEWIDTH, ty)) { //Upper left hand of hitbox
				y += yMove;
			}
			else {
				y = ty * Tile.TILEHEIGHT - hitbox.y - hitbox.height - 1; //Subtracting 1 to remove sliding problem (also exists in moving right)
			}
		}
	}
	
	protected boolean collisionWithTile(int x, int y) {
		return handler.getMap().getTile(x, y).isSolid();
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
