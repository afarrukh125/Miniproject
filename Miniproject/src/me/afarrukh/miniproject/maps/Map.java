package me.afarrukh.miniproject.maps;

import java.awt.Graphics;
import java.io.IOException;
import java.util.Random;

import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.constants.Constants;
import me.afarrukh.miniproject.entities.EntityManager;
import me.afarrukh.miniproject.entities.actors.Mage;
import me.afarrukh.miniproject.entities.stillentities.RockPile;
import me.afarrukh.miniproject.entities.stillentities.Tree;
import me.afarrukh.miniproject.items.ItemManager;
import me.afarrukh.miniproject.mokapot.MokaConstants;
import me.afarrukh.miniproject.tiles.Tile;
import me.afarrukh.miniproject.utils.UtilityTasks;

/**
 * 
 * @author Abdullah
 * This defines a map for the game. A traversable zone containing many tileArray to give a particular style for the map.
 */
public class Map {

	private Manager manager;
	private int width, height; //In terms of tileArray this is the size of the map. Note that this is NOT in terms of pixels
	private int spawnX, spawnY; //Determines which tile the player will arrive for this particular map/world.
	private int[][] tiles; //We require a multidimensional array to manage the tile positions
	
	//Entities/Items
	private final EntityManager entityManager;
	private ItemManager itemManager;
	
	public Map(String path) throws IOException {
		this.manager = Manager.getInstance();
		entityManager = new EntityManager(manager, new Mage(manager, 100, 100));
		itemManager = new ItemManager(manager);

		loadMap(path);
		generateEntities();
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public void tick() {
		int xStart = (int) Math.max(0, manager.getGameCamera().getxOffSet() / Tile.TILEWIDTH); //We don't want to render tileArray with negative coordinates
		int xEnd = (int) Math.min(width, (manager.getGameCamera().getxOffSet() + manager.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, manager.getGameCamera().getyOffSet() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (manager.getGameCamera().getyOffSet() + manager.getHeight()) / Tile.TILEHEIGHT + 1);

		for(int y = yStart; y<yEnd; y++) {
			for(int x = xStart; x<xEnd ; x++) {
				Tile tile = getTile(x, y);
				if(tile.getMaxAnimationFrames() > 1) {
					tile.tick();
				}
				//We must use the TILE.TILEWIDTH/HEIGHT to match the units, otherwise only pixels are rendered
			}
		}

		itemManager.tick();
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		//The first four lines don't visually change anything but changes the efficiency of the program.
		//Using math.max with 0 as one of the parameters ensures we always get a positive number, or math.min ensures we get far right
		int xStart = (int) Math.max(0, manager.getGameCamera().getxOffSet() / Tile.TILEWIDTH); //We don't want to render tileArray with negative coordinates
		int xEnd = (int) Math.min(width, (manager.getGameCamera().getxOffSet() + manager.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, manager.getGameCamera().getyOffSet() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (manager.getGameCamera().getyOffSet() + manager.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart; y<yEnd; y++) {
			for(int x = xStart; x<xEnd ; x++) {
				getTile(x, y).render(g, (int) (x * Tile.TILEWIDTH - manager.getGameCamera().getxOffSet()), 
										(int) (y * Tile.TILEHEIGHT - manager.getGameCamera().getyOffSet()));
				//We must use the TILE.TILEWIDTH/HEIGHT to match the units, otherwise only pixels are rendered
			}
		}
		
		itemManager.render(g);
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y) { 
		
		if(x<0 || y <0 || x>=width || y>=height)
			return Tile.grassTile;
		//Find the ID in the instance variable tileArray and get what tile id it is
		Tile t = Tile.tileArray[tiles[x][y]]; //Finds the tile location in tile position x and y
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

	private void generateEntities() {
		int treeCount = 0;
		int rockCount = 0;
		for(int i = 0; i <= Constants.MAX_TREES; i++) { //Maximum number of trees
			Random random = new Random();
			int rng = random.nextInt(100);
			if(rng < Constants.TREE_PERCENTAGE) {
				//We want to ensure the trees generated are within map boundaries
				int rngx = random.nextInt(this.width * Tile.TILEWIDTH);
				int rngy = random.nextInt(this.height * Tile.TILEHEIGHT);
				entityManager.addEntity(new Tree(manager, rngx, rngy));
				treeCount += 1;
			}
		}
		for(int i = 0; i <= Constants.MAX_ROCKS; i++) { //Maximum number of rocks
			Random random = new Random();
			int rng = random.nextInt(100);
			if(rng < Constants.ROCK_PERCENTAGE) {
				//We want to ensure the rocks generated are within map boundaries
				int rngx = random.nextInt(this.width * Tile.TILEWIDTH);
				int rngy = random.nextInt(this.height * Tile.TILEHEIGHT);
				entityManager.addEntity(new RockPile(manager, rngx, rngy));
				rockCount += 1;
			}
		}
		System.out.println("Number of trees: " +treeCount);
		System.out.println("Number of rocks: " + rockCount);
	}

	public EntityManager getEntityManager() {
		return entityManager;
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

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}
		
}
