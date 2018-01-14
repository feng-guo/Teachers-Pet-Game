package graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Assets {

	private static final int charWidth = 16;
	private static final int charHeight = 21;
	private static final int tileWidth = 16;
	private static final int tileHeight = 16;


	//public static BufferedImage feng_down_1, feng_down_2, feng_down_3;
	public static BufferedImage /*grass_1,*/ grass_2, rock, path, tree, floor, hallFloor, locker, battleBackground;

	public static BufferedImage[] feng_down, feng_up, feng_left, feng_right, logo;
	public static BufferedImage[][] tileArray;
	public static Font font24, font16, font12;


	public static void init() {
		
		File bb = new File("./fonts/PokeFont.ttf");
		String filePath = bb.getAbsolutePath().substring(0, bb.getAbsolutePath().indexOf(".")) + "res/fonts/PokeFont.ttf";
		try {
			font24 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(filePath))).deriveFont(Font.PLAIN, 24);
			font16 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(filePath))).deriveFont(Font.PLAIN, 16);
			font12 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(filePath))).deriveFont(Font.PLAIN, 12);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		SpriteSheet fengSheet = new SpriteSheet(ImageLoader.loadImage("/textures/feng_textures.png"));
		SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tile_sheet.png"));
		SpriteSheet grassSheet = new SpriteSheet(ImageLoader.loadImage("/textures/tile_textures.png"));
		SpriteSheet floorSheet = new SpriteSheet(ImageLoader.loadImage("/textures/sample4.png"));

		battleBackground = ImageLoader.loadImage("/textures/game_background.png");

		tree = ImageLoader.loadImage("/textures/Outdoor_Entities/Tree.png");
		locker = ImageLoader.loadImage("/textures/Indoor_Entities/Hallway_Items/Locker.png");
		floor = floorSheet.crop(32 * 8 - 9, 32 * 28 - 3, 32, 32);

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

		//grass_1 = grassSheet.crop(0, 0, tileWidth, tileHeight);
		//grass_2 = grassSheet.crop(tileWidth, 0, tileWidth, tileHeight);
		path = grassSheet.crop(0, tileWidth, tileWidth, tileHeight);

		tileArray = new BufferedImage[10][10];
		for (int i = 0; i < 10; i ++) {
			for (int j = 0; j < 10; j++) {
				tileArray[i][j] = tileSheet.crop(i *(tileWidth + 1), j *(tileHeight + 1), tileWidth, tileHeight);
			}
		}
		rock = tileArray[7][7];
		//hallFloor = tileArray[58][14];

	}
}
