package me.afarrukh.miniproject.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	public static BufferedImage grass, rock, water, lava, sand, tree;
	public static BufferedImage[] archer_up, archer_down, archer_left, archer_right, archer_still;
	public static BufferedImage[] mage_up, mage_down, mage_left, mage_right, mage_still, mage_attack;
	public static BufferedImage[] startBtn;
	
	private static final int width = 44, height = 44;
	
	public static void init() {
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/images/spritesheet.png"));
		
		startBtn = new BufferedImage[2];
		startBtn[0] = sheet.crop(width*4, height, width*2, height);
		startBtn[1] = sheet.crop(width*4, height*2, width*2, height);
		
		//Rendering the mage
		mage_up = new BufferedImage[2];
		mage_up[0] = sheet.crop(width*2, height*5, width, height);
		mage_up[1] = sheet.crop(width*3, height*5, width, height);
		
		mage_down = new BufferedImage[2];
		mage_down[0] = sheet.crop(0, height*5, width, height);
		mage_down[1] = sheet.crop(width, height*5, width, height);
		
		mage_left = new BufferedImage[2];
		mage_left[0] = sheet.crop(width*2, height*6, width, height);
		mage_left[1] = sheet.crop(width*3, height*6, width, height);
		
		mage_right = new BufferedImage[2];
		mage_right[0] = sheet.crop(0, height*6, width, height);
		mage_right[1] = sheet.crop(width, height*6, width, height);
		
		mage_still = new BufferedImage[2];
		mage_still[0] = sheet.crop(width*2, 0, width, height);
		mage_still[1] = sheet.crop(width, height*4, width, height);
		
		mage_attack = new BufferedImage[4];
		mage_attack[0] = sheet.crop(0, height*7, width, height);
		mage_attack[1] = sheet.crop(width, height*7, width, height);
		mage_attack[2] = sheet.crop(width*2, height*7, width, height);
		mage_attack[3] = sheet.crop(width*3, height*7, width, height);
		
		
		//Rendering the archer
		archer_up = new BufferedImage[2];
		archer_up[0] = sheet.crop(width*2, height*2, width, height);
		archer_up[1] = sheet.crop(width*3, height*2, width, height);
		
		archer_down = new BufferedImage[2];
		archer_down[0] = sheet.crop(0, height*2, width, height);
		archer_down[1] = sheet.crop(width, height*2, width, height);
		
		archer_left = new BufferedImage[2];
		archer_left[0] = sheet.crop(width*2, height*3, width, height);
		archer_left[1] = sheet.crop(width*3, height*3, width, height);
		
		archer_right = new BufferedImage[2];
		archer_right[0] = sheet.crop(0, height*3, width, height);
		archer_right[1] = sheet.crop(width, height*3, width, height);
		
		archer_still = new BufferedImage[2];
		archer_still[0] = sheet.crop(0, height*3, width, height);
		archer_still[1] = sheet.crop(0, height*4, width, height);
				
		
		//Assets for the textures
		water = sheet.crop(width*3, 0, width, height);
		lava = sheet.crop(width*2, height, width, height);
		grass = sheet.crop(width, height, width, height);
		rock = sheet.crop(0, height, width, height);
		sand = sheet.crop(width*3, height, width, height);
		tree = sheet.crop(width*4, 0, width, height);
	}

	public static int getWidth() {
		return width;
	}

	public static int getHeight() {
		return height;
	}
	
	
	
}
