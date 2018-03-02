package me.afarrukh.miniproject.entities.actors;

import java.awt.Graphics;

import me.afarrukh.miniproject.Game;
import me.afarrukh.miniproject.gfx.Assets;

public class Player extends Actor {

	private Game game; //We need a game object here so we can access our keymanager object
	
	public Player(Game game, float x, float y) {
		super(x, y, Actor.DEFAULT_ACTOR_WIDTH, Actor.DEFAULT_ACTOR_HEIGHT);
		this.game = game;
	}

	@Override
	public void tick() {
		getInput();
		move();
	}
	
	public void getInput() {
		xMove = 0;
		yMove = 0;
		
		if(game.getKeyManager().up) {
			yMove = -speed;
		}
		if(game.getKeyManager().down) {
			yMove = speed;
		}
		if(game.getKeyManager().left) {
			xMove = -speed;
		}
		if(game.getKeyManager().right) {
			xMove = speed;
		}
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.mage, (int) x, (int) y, width, height, null);
		
		
	}
	
	
	
	

}
