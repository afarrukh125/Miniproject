package me.afarrukh.miniproject.states;

import java.awt.Graphics;

import me.afarrukh.miniproject.Handler;
import me.afarrukh.miniproject.gfx.Assets;
import me.afarrukh.miniproject.ui.ClickListener;
import me.afarrukh.miniproject.ui.UIBasicButton;
import me.afarrukh.miniproject.ui.UIManager;

/**
 * 
 * @author Abdullah
 * This state controls what happens when the user is in the main menu of the game
 */
public class MenuState extends State{

	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIBasicButton(200, 200, 128, 64, Assets.startBtn, new ClickListener() {

			@Override
			public void onClick() {
				State.setState(handler.getGame().gameState);
			}}));
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
