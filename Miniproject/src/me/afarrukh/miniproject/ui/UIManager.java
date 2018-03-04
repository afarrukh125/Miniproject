package me.afarrukh.miniproject.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import me.afarrukh.miniproject.Manager;

/**
 * 
 * @author Abdullah
 * @description Similar to the EntityManager, this class stores all the UI objects to be used in the game
 */
public class UIManager {

	
	private Manager manager;
	private ArrayList<UIObject> objects;
	
	public UIManager(Manager manager) {
		this.manager = manager;
		objects = new ArrayList<UIObject>();
	}
	
	public void tick() {
		for(UIObject o: objects)
			o.tick();
	}
	
	public void render(Graphics g) {
		for(UIObject o: objects)
			o.render(g);
	}
	
	public void onMouseMove(MouseEvent e) {
		for(UIObject o: objects)
			o.onMouseMove(e);
	}
	
	public void onMouseRelease(MouseEvent e) {
		for(UIObject o: objects)
			o.onMouseRelease(e);
	}
	
	public void addObject(UIObject o) {
		objects.add(o);
	}
	
	public void removeObject(UIObject o) {
		objects.remove(o);
	}

	public Manager getHandler() {
		return manager;
	}

	public void setHandler(Manager manager) {
		this.manager = manager;
	}

	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}
	
	
}
