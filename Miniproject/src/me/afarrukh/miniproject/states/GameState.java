package me.afarrukh.miniproject.states;

import java.awt.Graphics;

import me.afarrukh.miniproject.Handler;
import me.afarrukh.miniproject.maps.Map;

//This is the state of the main game

public class GameState extends State{
	
	private Map map;

	public GameState(Handler handler) {
		super(handler);
		
		map = new Map(handler, "res/maps/map1.map");
		handler.setMap(map);
	}
	
	@Override
	public void tick() {
		map.tick();
		
	}

	@Override
	public void render(Graphics g) {
		map.render(g); //Rendering the map before the everything ensures that no entities are behind the map
		
	}

	
}
