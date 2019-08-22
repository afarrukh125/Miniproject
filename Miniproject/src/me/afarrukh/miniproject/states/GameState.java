package me.afarrukh.miniproject.states;

import java.awt.Graphics;
import java.util.Random;

import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.maps.Map;
import me.afarrukh.miniproject.utils.UtilityTasks;

//This is the state of the main game

public class GameState extends State{
	
	private final Map map;

	public GameState() {
		super(Manager.getInstance());
		int mapIndex = getRandomMapFromLibrary();
		map = new Map("res/maps/map"+mapIndex+".map");
		Manager.getInstance().setMap(map);
	}

	/**
	 *
	 * @return an integer pertaining to a random number for the map
	 */
	private int getRandomMapFromLibrary() {
		Random random = new Random();
		int rng = random.nextInt(UtilityTasks.getLatestMapIndex());
		System.out.println("Loaded map number " +rng);
		return rng;
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
