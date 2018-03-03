package me.afarrukh.miniproject.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 
 * @author Abdullah
 * @description This class is a basic button loaded from an IMAGE. 
 * There may be other classes in this package which load buttons off text
 */

public class UIBasicButton extends UIObject {
	
	private BufferedImage[] images; //The array of images that belong to this UI button
	private ClickListener clicker;

	public UIBasicButton(float x, float y, int width, int height, BufferedImage[] images, ClickListener clicker) {
		super(x, y, width, height);
		this.images = images;
		this.clicker = clicker;
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		if(hovering)
			g.drawImage(images[1], (int) x, (int) y, width, height, null);
		else
			g.drawImage(images[0], (int) x, (int) y, width, height, null);
	}

	@Override
	public void onClick() {
		clicker.onClick();
	}

}
