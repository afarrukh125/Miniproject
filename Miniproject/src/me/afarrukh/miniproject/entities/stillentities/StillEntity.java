package me.afarrukh.miniproject.entities.stillentities;

import me.afarrukh.miniproject.Handler;
import me.afarrukh.miniproject.entities.Entity;

/**
 * 
 * @author Abdullah
 * @description A static entity is an entity that does not move, for instance a tree, or boulder
 */
public abstract class StillEntity extends Entity {

	public StillEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
}
