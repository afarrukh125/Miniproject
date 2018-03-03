package me.afarrukh.miniproject.maps;

import java.awt.Graphics;

import me.afarrukh.miniproject.Handler;
import me.afarrukh.miniproject.tiles.Tile;
import me.afarrukh.miniproject.utils.UtilityTasks;

/**
 * 
 * @author Abdullah
 * This defines a map for the game. A traversable zone containing many tiles to give a particular style for the map.
 */
public class Map {

	private Handler handler;
	private int width, height; //In terms of tiles this is the size of the map. Note that this is NOT in terms of pixels
	private int spawnX, spawnY; //Determines which tile the player will arrive for this particular map/world.
	private int[][] tiles; //We require a multidimensional array to manage the tile positions
	
	public Map(Handler handler, String path) {
		this.handler = handler;
		loadMap(path);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		//The first four lines don't visually change anything but changes the efficiency of the program.
		//Using math.max with 0 as one of the parameters ensures we always get a positive number, or math.min ensures we get far right
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffSet() / Tile.TILEWIDTH); //We don't want to render tiles with negative coordinates
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffSet() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffSet() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffSet() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart; y<yEnd; y++) {
			for(int x = xStart; x<xEnd ; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffSet()), 
										(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffSet()));
				//We must use the TILE.TILEWIDTH/HEIGHT to match the units, otherwise only pixels are rendered
			}
		}
	}
	
	public Tile getTile(int x, int y) { 
		
		if(x<0 || y <0 || x>=width || y>=height)
			return Tile.grassTile;
		//Find the ID in the instance variable tiles and get what tile id it is
		Tile t = Tile.tiles[tiles[x][y]]; //Finds the tile location in tile position x and y
		if(t == null) //Return a grass tile if there is no tile in that region
			return Tile.grassTile;
		return t;
		
	}
	
	private void loadMap(String path) {
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

	public int getSpawnX() {
		return spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}	
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
		
}
