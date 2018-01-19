package graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	private BufferedImage sheet;
	
	// Allow spritesheet
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}
	
	// Allows cropping
	public BufferedImage crop(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}
}
