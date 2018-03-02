package me.afarrukh.miniproject.entities.actors;

import me.afarrukh.miniproject.entities.Entity;

/*
 * This class extends the entity class and can be further subclassed into classes which define
 * things such as our main character, monsters, enemy characters and so on.
 */

public abstract class Actor extends Entity{
	
	protected int health;
	
	public Actor(float x, float y) {
		
		super(x, y);
		health = 10;
		
		
	}
}
