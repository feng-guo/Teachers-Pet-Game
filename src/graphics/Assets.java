package graphics;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Assets {

	private static final int charWidth = 15;
	private static final int charHeight = 19;
	private static final int tileWidth = 16;
	private static final int tileHeight = 16;


	//public static BufferedImage feng_down_1, feng_down_2, feng_down_3;

	public static BufferedImage /*grass_1,*/ grass_2, rock, path, tree, 
	floor, wall, blackBlock, battleBackground, hallFloor, locker, lockerTop,
	openDoor, closedDoor, indoorWindowOpen, indoorWindowClosed, bench, 
	hallCouch, hallChairLowLeft, hallChairLowRight, hallChairLowUp, hallChairHigh, hallTableLow, hallTableHigh,
	foodBanner, happyFace, sadFace;

	public static BufferedImage[] feng_down, feng_up, feng_left, feng_right, logo;
	public static BufferedImage[][] tileArray, foodArray;
	public static Font font24, font16, font12, font10, font8;


	public static void init() {
		
		File fontFile = new File("./fonts/PokeFont.ttf");
		String filePath = fontFile.getAbsolutePath().substring(0, fontFile.getAbsolutePath().indexOf(".")) + "res/fonts/PokeFont.ttf";
		try {
			font24 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(filePath))).deriveFont(Font.PLAIN, 24);
			font16 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(filePath))).deriveFont(Font.PLAIN, 16);
			font12 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(filePath))).deriveFont(Font.PLAIN, 12);
			font10 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(filePath))).deriveFont(Font.PLAIN, 10);
			font8 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(filePath))).deriveFont(Font.PLAIN, 8);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		
		String characterName = "Misha";

		SpriteSheet fengSheet = new SpriteSheet(ImageLoader.loadImage("/Sprite_Images/sprites/characters/" + characterName + "/" + characterName.toLowerCase() + "_spritesheet.png"));
		SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/tile_sheet.png"));
		SpriteSheet grassSheet = new SpriteSheet(ImageLoader.loadImage("/tile_textures.png"));
		SpriteSheet floorSheet = new SpriteSheet(ImageLoader.loadImage("/sample4.png"));
		SpriteSheet foodSheet = new SpriteSheet(ImageLoader.loadImage("/foodSheet.png"));
		
		foodArray = new BufferedImage[3][4];
		for (int i = 0; i < 3; i ++) {
			for (int j = 0; j < 4; j++) {
				foodArray[i][j] = foodSheet.crop(i * 128, j * 128, 128, 128);
			}
		}
		
		foodBanner = ImageLoader.loadImage("/foodBanner.png");
		happyFace = ImageLoader.loadImage("/happyFace.png");
		sadFace = ImageLoader.loadImage("/sadFace.png");

		
		
		battleBackground = ImageLoader.loadImage("/game_background.png");

		floor = floorSheet.crop(32 * 8 - 9, 32 * 28 - 3, 32, 32);
		tree = ImageLoader.loadImage("/textures/Outdoor_Entities/Tree.png");
		wall = ImageLoader.loadImage ("/textures/Indoor_Entities/White_Walls.png");

		locker = ImageLoader.loadImage("/textures/Indoor_Entities/Hallway_Items/Locker.png");
		lockerTop = ImageLoader.loadImage("/textures/Indoor_Entities/Hallway_Items/Locker_Top.png");
		indoorWindowOpen = ImageLoader.loadImage ("/textures/Indoor_Entities/Hallway_Items/Hallway_Window_Open.png");
		indoorWindowClosed = ImageLoader.loadImage ("/textures/Indoor_Entities/Hallway_Items/Hallway_Window_Closed.png");
		openDoor = ImageLoader.loadImage ("/textures/Indoor_Entities/Hallway_Items/Open_Door.png");
		closedDoor = ImageLoader.loadImage ("/textures/Indoor_Entities/Hallway_Items/Closed_Door.png");
		bench = ImageLoader.loadImage ("/textures/Indoor_Entities/Hallway_Items/Bench.png");

		hallCouch = ImageLoader.loadImage ("/textures/Indoor_Entities/Hallway_Items/Hall_Couch.png");
		hallChairLowRight = ImageLoader.loadImage ("/textures/Indoor_Entities/Hallway_Items/Hall_Chair_Right.png");
		hallChairLowLeft = ImageLoader.loadImage ("/textures/Indoor_Entities/Hallway_Items/Hall_Chair_Left.png");
		hallChairLowUp = ImageLoader.loadImage ("/textures/Indoor_Entities/Hallway_Items/Hall_Chair_Front.png");
		hallChairHigh = ImageLoader.loadImage ("/textures/Indoor_Entities/Hallway_Items/High_Chair.png");
		hallTableLow = ImageLoader.loadImage ("/textures/Indoor_Entities/Hallway_Items/Hall_Table_Low.png");
		hallTableHigh = ImageLoader.loadImage ("/textures/Indoor_Entities/Hallway_Items/Hall_Table_High.png");

		tree = ImageLoader.loadImage("/Outdoor_Entities/Tree.png");
		wall = ImageLoader.loadImage ("/Indoor_Entities/White_Walls.png");

		locker = ImageLoader.loadImage("/Indoor_Entities/Hallway_Items/Locker.png");
		indoorWindowOpen = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Hallway_Window_Open.png");
		indoorWindowClosed = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Hallway_Window_Closed.png");
		openDoor = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Open_Door.png");
		closedDoor = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Closed_Door.png");
		bench = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Bench.png");

		hallCouch = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Hall_Couch.png");
		hallChairLowRight = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Hall_Chair_Right.png");
		hallChairLowLeft = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Hall_Chair_Left.png");
		hallChairLowUp = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Hall_Chair_Front.png");
		hallChairHigh = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/High_Chair.png");
		hallTableLow = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Hall_Table_Low.png");
		hallTableHigh = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Hall_Table_High.png");

		logo = new BufferedImage[2];
		logo[0] = ImageLoader.loadImage("/RHHSLogo.png");
		logo[1] = ImageLoader.loadImage("/RHHSLogoDown.png");

		feng_down = new BufferedImage[3];
		feng_up = new BufferedImage[3];
		feng_left = new BufferedImage[3];
		feng_right = new BufferedImage[3];

		feng_down[0] = fengSheet.crop(charWidth*0, 0, charWidth, charHeight);
		feng_down[1] = fengSheet.crop(charWidth*1, 0, charWidth, charHeight);
		feng_down[2] = fengSheet.crop(charWidth*2, 0, charWidth, charHeight);

		feng_left[0] = fengSheet.crop(charWidth*3 + 1, 0, charWidth - 1, charHeight);
		feng_left[1] = fengSheet.crop(charWidth*4 + 1, 0, charWidth - 1, charHeight);
		feng_left[2] = fengSheet.crop(charWidth*5 + 1, 0, charWidth - 1, charHeight);

		feng_right[0] = fengSheet.crop(charWidth*6 - 2, 0, charWidth - 1, charHeight);
		feng_right[1] = fengSheet.crop(charWidth*7 - 2, 0, charWidth - 1, charHeight);
		feng_right[2] = fengSheet.crop(charWidth*8 - 2, 0, charWidth - 1, charHeight);

		feng_up[0] = fengSheet.crop(charWidth*9 - 3, 0, charWidth, charHeight);
		feng_up[1] = fengSheet.crop(charWidth*10 - 3, 0, charWidth, charHeight);
		feng_up[2] = fengSheet.crop(charWidth*11 - 3, 0, charWidth, charHeight);

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
		blackBlock = tileArray[0][0];
		//hallFloor = tileArray[58][14];

	}
}
