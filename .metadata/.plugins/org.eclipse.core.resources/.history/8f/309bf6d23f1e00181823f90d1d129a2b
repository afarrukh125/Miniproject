package me.afarrukh.miniproject.states;

import java.awt.Graphics;

import me.afarrukh.miniproject.Game;
import me.afarrukh.miniproject.entities.actors.Player;

//This is the state of the main game

public class GameState extends State{
	
	private Player player;

	public GameState(Game game) {
		super(game);
		player = new Player(100, 100);
	}
	
	@Override
	public void tick() {
		
		player.tick();
		
	}

	@Override
	public void render(Graphics g) {
		
		player.render(g);
		
	}

	
}
