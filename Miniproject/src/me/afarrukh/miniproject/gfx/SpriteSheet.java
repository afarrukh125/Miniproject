package me.afarrukh.miniproject.gfx;

import java.awt.image.BufferedImage;

class SpriteSheet {

	private final BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}
}
