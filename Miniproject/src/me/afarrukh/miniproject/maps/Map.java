package me.afarrukh.miniproject.maps;

import java.awt.Graphics;

import me.afarrukh.miniproject.tiles.Tile;
import me.afarrukh.miniproject.utils.UtilityTasks;

public class Map {
	
	private int width, height; //In terms of tiles this is the size of the map. Note that this is NOT in terms of pixels
	private int spawnX, spawnY; //Determines which tile the player will arrive for this particular map/world.
	private int[][] tiles; //We require a multidimensional array to manage the tile positions
	
	public Map(String path) {
		
		loadWorld(path);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		for(int y = 0; y < height; y++) {
			for(int x = 0; x<width; x++) {
				getTile(x, y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT); //We must use the TILE.TILEWIDTH/HEIGHT to match the units, otherwise only pixels are rendered
			}
		}
	}
	
	public Tile getTile(int x, int y) { //Find the ID in the instance variable tiles and get what tile id it is
		Tile t = Tile.tiles[tiles[x][y]]; //Finds the tile location in tile position x and y
		if(t == null) //Return a grass tile if there is no tile in that region
			return Tile.grassTile;
		return t;
	}
	
	private void loadWorld(String path) {
		String file = UtilityTasks.loadFileAsString(path);
		String[] tokens = file.split("\\s+");//Splits the file into string array elements if it is empty space or new line
		
		width = UtilityTasks.parseInt(tokens[0]);
		height = UtilityTasks.parseInt(tokens[1]);
		spawnX = UtilityTasks.parseInt(tokens[2]);
		spawnY = UtilityTasks.parseInt(tokens[3]);
		
		//Now reading data from file regarding world data
		
		tiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = UtilityTasks.parseInt(tokens[(x + y * width) + 4]); //Adding 4 to account for first few elements
			}
		}
	}
		
}
