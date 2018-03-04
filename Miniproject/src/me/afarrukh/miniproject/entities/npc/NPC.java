package me.afarrukh.miniproject.entities.npc;

import java.awt.Graphics;

import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.entities.Entity;
/**
 * @author Abdullah
 * @description This represents a non playable character that the user can interact with.
 */
public class NPC extends Entity {

	public NPC(Manager manager, float x, float y, int width, int height) {
		super(manager, x, y, width, height);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
	}

	@Override
	public void die() {
		
	}
	


}
