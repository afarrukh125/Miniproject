package me.afarrukh.miniproject.tiles;

import me.afarrukh.miniproject.constants.Constants;
import me.afarrukh.miniproject.gfx.Animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 
 * @author Abdullah
 * The tile class essentially defines a region in which we can traverse.
 * It is simply the terrain handler for the game. We will have many of these on which the character can walk on
 * A tile has a method called isSolid() which returns whether or not the tile is traversable.
 */
public class Tile {

	//STATIC CONSTANTS
	
	public static final Tile[] tiles = new Tile[256]; //This tile array will hold every single tile in our game
	public static final Tile grassTile = new GrassTile(0); //A tile with id 0 is a grass tile
	public static Tile rockTile = new RockTile(1); //Rock tiles have id 1
	public static Tile waterTile = new WaterTile(2);
	public static Tile lavaTile = new LavaTile(3);
	public static Tile sandTile = new SandTile(4);
	
	//CLASS 
	public static final int TILEWIDTH = Constants.TILEWIDTH,
							TILEHEIGHT = Constants.TILEHEIGHT; //These are the dimensions for a tile.
	
	Animation animation;
	private final int id;
	
	Tile(BufferedImage[] images, int id) {
		this.animation = new Animation(Constants.DEFAULT_TILE_REFRESH_SPEED, images);
		this.id = id;
		
		tiles[id] = this;
	}
	
	
	public void tick() {
		animation.tick();
	}
	
	/**
	 * 
	 * @param g - The graphics object that will render our tile
	 * @param x - The x coordinate of where this tile is to be rendered
	 * @param y - The y coordinate of where this tile is to be rendered
	 */
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(animation.getCurrentFrame(), x, y, TILEWIDTH, TILEHEIGHT, null);
	}
	public int getId() {
		return id;
	}
	
	public boolean isSolid() { //Determines if this tile is traversable by an entity
							   //If a tile is solid then it is not traversable by entities
		return false; //By default it is traversable
	}

	public int getMaxAnimationFrames() {
		return this.animation.getFrames().length;
	}
}
