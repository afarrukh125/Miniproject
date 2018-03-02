package me.afarrukh.miniproject.states;

import java.awt.Graphics;

import me.afarrukh.miniproject.Game;
import me.afarrukh.miniproject.entities.actors.Player;
import me.afarrukh.miniproject.maps.Map;
import me.afarrukh.miniproject.tiles.Tile;

//This is the state of the main game

public class GameState extends State{
	
	private Player player;
	private Map map;

	public GameState(Game game) {
		super(game);
		player = new Player(game, 100, 100);
		map = new Map("res/maps/map1.map");
	}
	
	@Override
	public void tick() {
		map.tick();
		player.tick();
		
	}

	@Override
	public void render(Graphics g) {
		map.render(g); //Rendering the map before the player ensures the player travels on TOP of tiles rather than below them
		player.render(g); //Now rendering the player onto the screen
		
	}

	
}
