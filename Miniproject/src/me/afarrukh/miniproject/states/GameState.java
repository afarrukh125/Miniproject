package me.afarrukh.miniproject.states;

import java.awt.Graphics;
import me.afarrukh.miniproject.gfx.Assets;

//This is the state of the main game

public class GameState extends State{

	public GameState() {
		
	}
	
	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.archer, 0, 0, null);
		g.drawImage(Assets. mage, 85, 85, null);
	}

	
}
