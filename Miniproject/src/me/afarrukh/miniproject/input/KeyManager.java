package me.afarrukh.miniproject.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys;
	
	public boolean up, down, left, right; //Movement controls
	public boolean atkUp, atkDown, atkLeft, atkRight; //Attack controls
	
	public KeyManager() {
		keys = new boolean[256]; //Reasonable size for keys being pressed
		
	}
	
	public void tick() {
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		
		atkUp = keys[KeyEvent.VK_W];
		atkDown = keys[KeyEvent.VK_S];
		atkLeft = keys[KeyEvent.VK_A];
		atkRight = keys[KeyEvent.VK_D];
		
	}
	
	/*
	 * The keyboard input system revolves primarily around the keys being pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true; //Keys being pressed is set to true at first when it is pressed
		//System.out.println("Pressed.");
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false; //When a key is released then it is no longer selected and so we set this to false
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	

}
