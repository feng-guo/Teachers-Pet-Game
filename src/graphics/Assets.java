package graphics;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int charWidth = 16;
	private static final int charHeight = 21;
	private static final int tileWidth = 16;
	private static final int tileHeight = 16;

	
	//public static BufferedImage feng_down_1, feng_down_2, feng_down_3;
	public static BufferedImage grass_1, grass_2, rock;
	public static BufferedImage[] feng_down, feng_up, feng_left, feng_right, logo;
	
	
	public static void init() {
		SpriteSheet fengSheet = new SpriteSheet(ImageLoader.loadImage("/textures/feng_textures.png"));
		SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tile_sheet.png"));
		SpriteSheet grassSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tile_textures.png"));
		
		logo = new BufferedImage[2];
		logo[0] = ImageLoader.loadImage("/textures/RHHSLogo.png");
		logo[1] = ImageLoader.loadImage("/textures/RHHSLogoDown.png");
		
		feng_down = new BufferedImage[3];
		feng_up = new BufferedImage[3];
		feng_left = new BufferedImage[3];
		feng_right = new BufferedImage[3];
		
		feng_down[0] = fengSheet.crop(charWidth*0, 0, charWidth, charHeight);
		feng_down[1] = fengSheet.crop(charWidth*1, 0, charWidth, charHeight);
		feng_down[2] = fengSheet.crop(charWidth*2, 0, charWidth, charHeight);
		
		feng_left[0] = fengSheet.crop(charWidth*3, 0, charWidth, charHeight);
		feng_left[1] = fengSheet.crop(charWidth*4, 0, charWidth, charHeight);
		feng_left[2] = fengSheet.crop(charWidth*5, 0, charWidth, charHeight);
		
		feng_right[0] = fengSheet.crop(charWidth*6, 0, charWidth, charHeight);
		feng_right[1] = fengSheet.crop(charWidth*7, 0, charWidth, charHeight);
		feng_right[2] = fengSheet.crop(charWidth*8, 0, charWidth, charHeight);
		
		feng_up[0] = fengSheet.crop(charWidth*9, 0, charWidth, charHeight);
		feng_up[1] = fengSheet.crop(charWidth*10, 0, charWidth, charHeight);
		feng_up[2] = fengSheet.crop(charWidth*11, 0, charWidth, charHeight);
		
		grass_1 = grassSheet.crop(0, 0, tileWidth, tileHeight);
		grass_2 = grassSheet.crop(tileWidth, 0, tileWidth, tileHeight);
		
		rock = tileSheet.crop(17, 17*26, tileWidth, tileHeight);

	}
}
