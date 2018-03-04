package me.afarrukh.miniproject.items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import me.afarrukh.miniproject.Manager;

public class Item {

	public static final int ITEM_WIDTH = 32, ITEM_HEIGHT = 32, PICKED_UP = -1;
	
	protected Manager manager;
	protected BufferedImage texture; //This is the image that represents this pickup
	protected String name;
	protected final int id;
	
	protected int x, y, instances;
	
	public Item(BufferedImage texture, String name, int id) {
		this.texture = texture;
		this.name = name;
		this.id = id;
		instances = 1; //Amount of instances of this item that the player has
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(manager == null) //If we do not have a handler then just return and do not render this object
			return;
		render(g, (int) (x - manager.getGameCamera().getxOffSet()), (int) (y - manager.getGameCamera().getyOffSet()));
	}
	
	public void render(Graphics g,int x, int y) {
		g.drawImage(texture, x, y, ITEM_WIDTH, ITEM_HEIGHT, null);
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
	
	
}
