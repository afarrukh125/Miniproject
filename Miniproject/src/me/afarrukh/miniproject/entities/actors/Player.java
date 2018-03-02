package me.afarrukh.miniproject.entities.actors;

import java.awt.Graphics;

import me.afarrukh.miniproject.gfx.Assets;

public class Player extends Actor {

	public Player(float x, float y) {
		super(x, y);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.mage, (int) x, (int) y, null);
		
		
	}
	
	
	
	

}
