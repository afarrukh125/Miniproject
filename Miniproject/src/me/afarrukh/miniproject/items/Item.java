package me.afarrukh.miniproject.items;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.gfx.Visuals;

public class Item {

	//Item Manager - items to be added here
	private static final Item[] items = new Item[256];
	public static final Item woodItem = new Item(Visuals.tree, "Wood", 0);
	public static final Item rockItem = new Item(Visuals.rockPile, "Rock", 1);
	public static final Item gemItem = new Item(Visuals.gem, "Gem", 2);
	
	private static final int ITEM_WIDTH = 32;
	private static final int ITEM_HEIGHT = 32;
	
	private Manager manager;
	private BufferedImage texture; //This is the image that represents this pickup
	private String name;
	private final int id;
	
	private final Rectangle bounds;
	
	private int x;
	private int y;
	private int instances;
	private boolean pickedUp = false;
	
	private Item(BufferedImage texture, String name, int id) {
		this.texture = texture;
		this.name = name;
		this.id = id;
		instances = 1; //Amount of instances of this item that the player has
		
		bounds = new Rectangle(x, y, ITEM_WIDTH, ITEM_HEIGHT);
		
		items[id] = this; //Defines what item will be rendered.
	}
	
	public void tick() {
		if(manager.getMap().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds)) {
			pickedUp = true;
			manager.getMap().getEntityManager().getPlayer().getInventory().addItem(this);
		}
	}
	
	public void render(Graphics g) {
		if(manager == null) //If we do not have a manager then just return and do not render this object
			return;
		render(g, (int) (x - manager.getGameCamera().getxOffSet()), (int) (y - manager.getGameCamera().getyOffSet()));
	}
	
	private void render(Graphics g, int x, int y) {
		g.drawImage(texture, x, y, ITEM_WIDTH, ITEM_HEIGHT, null);
	}
	
	/**
	 * @param x
	 * @param y
	 * @return an Item object to be copied
	 * 
	 * This method copies an item from one place and clones it to another item object in the x and y positions
	 */
	public Item createCopy(int x, int y) {
		Item i = new Item(texture, name, id);
		i.setPosition(x, y);
		return i;
	}
	
	public Item createCopy(int instances) {
		Item i = new Item(texture, name, id);
		i.setPickedUp(true);
		i.setInstances(instances);
		return i;
	}
	
	private void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		bounds.x = x;
		bounds.y = y;
	}

	//Getter/Setter methods
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getInstances() {
		return instances;
	}

	public void setInstances(int instances) {
		this.instances = instances;
	}

	public int getId() {
		return id;
	}

	public boolean isPickedUp() {
		return pickedUp;
	}

	private void setPickedUp(boolean pickedUp) {
		this.pickedUp = pickedUp;
	}
	
	
	
	
	
	
}
