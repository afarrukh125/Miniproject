package me.afarrukh.miniproject.entities.actors;

import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.constants.Constants;
import me.afarrukh.miniproject.entities.Entity;
import me.afarrukh.miniproject.tiles.Tile;

/*
 * This class extends the entity class and can be further subclassed into classes which define
 * things such as our main character, monsters, enemy characters and so on.
 */

public abstract class Actor extends Entity{
	
	public static final float DEFAULT_MOVESPEED = Constants.ACTORMS;
	public static final int DEFAULT_ACTOR_WIDTH = 64,
							DEFAULT_ACTOR_HEIGHT = 64,
							DEFAULT_ACTOR_ATKPOW = 28;
	
	protected float speed;
	protected int attackPower;
	
	protected float xMove, yMove; //How much the characters move by in pixels
	
	//Other attributes
	private String name;
    private String type;
    private int maxHp;
    private int Hp;
    private int HpRegen;
    private double charEnergy;

    //Attributes pertaining to battle
    private int resistance;
    private int attackAccuracy;
    
	public Actor(Manager manager, float x, float y, int width, int height) {
		
		super(manager, x, y, width, height);
		speed = DEFAULT_MOVESPEED;
		attackPower = DEFAULT_ACTOR_ATKPOW;
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
		return manager.getMap().getTile(x, y).isSolid();
	}

	//Getter/Setter methods for this class
	public float getxMove() {
		return xMove;
	}

	public int getAttackPower() {
		return attackPower;
	}

	public void setAttackPower(int attackPower) {
		this.attackPower = attackPower;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getHp() {
		return Hp;
	}

	public void setHp(int hp) {
		Hp = hp;
	}

	public int getHpRegen() {
		return HpRegen;
	}

	public void setHpRegen(int hpRegen) {
		HpRegen = hpRegen;
	}

	public double getEnergy() {
		return charEnergy;
	}

	public void setEnergy(double charEnergy) {
		this.charEnergy = charEnergy;
	}

	public int getResistance() {
		return resistance;
	}

	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

	public int getAttackAccuracy() {
		return attackAccuracy;
	}

	public void setAttackAccuracy(int attackAccuracy) {
		this.attackAccuracy = attackAccuracy;
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
