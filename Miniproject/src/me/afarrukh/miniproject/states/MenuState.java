package me.afarrukh.miniproject.states;

import java.awt.Graphics;

import me.afarrukh.miniproject.Manager;
import me.afarrukh.miniproject.gfx.Visuals;
import me.afarrukh.miniproject.ui.ClickListener;
import me.afarrukh.miniproject.ui.UIBasicButton;
import me.afarrukh.miniproject.ui.UIManager;

/**
 * 
 * @author Abdullah
 * This state controls what happens when the user is in the main menu of the game
 */
public class MenuState extends State{

	private final UIManager uiManager;
	
	public MenuState(Manager manager) {
		super(manager);
		uiManager = new UIManager(manager);

		manager.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIBasicButton((manager.getWidth() / 2) - Visuals.getWidth()
						, 44, 128, 64, manager.getVisuals().startBtn, new ClickListener() {

			@Override
			public void onClick() {
				State.setState(manager.getGame().gameState);
			}})); //This button is set to change to the main game state upon being clicked
	}
	
	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}
	
	

}
