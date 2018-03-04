package me.afarrukh.miniproject.entities.stillentities;

import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.entities.Entity;

/**
 * 
 * @author Abdullah
 * @description A static entity is an entity that does not move, for instance a tree, or boulder
 */
public abstract class StillEntity extends Entity {

	public StillEntity(Manager manager, float x, float y, int width, int height) {
		super(manager, x, y, width, height);
	}
	
	
}
