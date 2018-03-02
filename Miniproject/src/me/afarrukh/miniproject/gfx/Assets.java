package me.afarrukh.miniproject.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	public static BufferedImage archer, fighter, mage;
	private static final int width = 32, height = 32;
	
	public static void init() {
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/images/spritesheet.png"));
		
		archer = sheet.crop(0, 0, width, height);
		fighter = sheet.crop(32, 0, width, height);
		mage = sheet.crop(64, 0, width, height);
		
	}
	
}
