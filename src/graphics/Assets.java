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


	//public static BufferedImage images

	// Initialise each image and buffer it in
	public static BufferedImage /*grass_1,*/ grass_2, rock, path, tree, gymTile, blackScreen,
	floor, wall, blackBlock, battleBackground, characterSelect, inventorySelect, hallFloor, locker, lockerTop, stairs,
	openDoor, closedDoor, sideDoor, indoorWindowOpen, indoorWindowClosed, bench,
	hallCouch, hallChairLowLeft, hallChairLowRight, hallChairLowUp, hallChairHigh, hallTableLow, hallTableHigh,
	cafTableUp, cafTableSide, vending1, vending2,
	studentDeskRight, studentDeskUp,
	studentChairRight, studentChairLeft, studentChairUp,
	teacherDeskSide, teacherDeskUp,
	announcementBoard, announcementBoard2, greenBoard, brownBoard,
	labBench, labTableSide,labTableUp, labStool, labShelf,
	guidanceChairSide, guidanceChairBack, guidanceCouch,
	counterSide, counterUp, longTableSide, longTableUp, longTableSideTop, longTableUpTop,
    laptop, computer,
	foodBanner, happyFace, sadFace, blackBoard, writingBlackBoard, projectorScreen, loadingBackground, selectionArrow, court;

	public static BufferedImage[] player_down, player_up, player_left, player_right,
								feng_down, feng_up, feng_left, feng_right,
								angela_down, angela_up, angela_left, angela_right,
								bill_down, bill_up, bill_left, bill_right,
								johann_down, johann_up, johann_left, johann_right,
								joyce_down, joyce_up, joyce_left, joyce_right,
								misha_down, misha_up, misha_left, misha_right,
								sihan_down, sihan_up, sihan_left, sihan_right,
								yash_down, yash_up, yash_left, yash_right,
								aaron_down, aaron_up, aaron_left, aaron_right,
								alston_down, alston_up, alston_left, alston_right,
								michael_down, michael_up, michael_left, michael_right,
								nikhil_down, nikhil_up, nikhil_left, nikhil_right,
								rosemary_down, rosemary_up, rosemary_left, rosemary_right,
								randomNiner_down, randomNiner_up, randomNiner_left, randomNiner_right,
								timmerman_down, timmerman_up, timmerman_left, timmerman_right,
								choi_down, choi_up, choi_left, choi_right,
								shim_down, shim_up, shim_left, shim_right,
								harris_down, harris_up, harris_left, harris_right,
								kostanenko_down, kostanenko_up, kostanenko_left, kostanenko_right,
								gissing_down, gissing_up, gissing_left, gissing_right;
								
	public static BufferedImage[] logo, cars;
	public static BufferedImage[][] tileArray, foodArray;

	// Get fonts
	public static Font font24, font16, font12, font10, font8;

	// Start as Feng
	public static String characterName = "Rosemary";

	public static void init() {
		
		loadFont();
		loadSprites();

		// Load all image files below
		cars = new BufferedImage[3];
		cars[0] = ImageLoader.loadImage("/red_car.png");
		cars[1] = ImageLoader.loadImage("/blue_car.png");
		cars[2] = ImageLoader.loadImage("/green_car.jpg");

		
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
		
		court = ImageLoader.loadImage("/court.png");
		selectionArrow = ImageLoader.loadImage("/selection_arrow.png");
		characterSelect = ImageLoader.loadImage("/playerSelect.png");
		inventorySelect = ImageLoader.loadImage("/inventory_screen.png");
		loadingBackground = ImageLoader.loadImage("/loadingBackground.png");
		foodBanner = ImageLoader.loadImage("/foodBanner.png");
		happyFace = ImageLoader.loadImage("/happyFace.png");
		sadFace = ImageLoader.loadImage("/sadFace.png");
		blackBoard = ImageLoader.loadImage("/Indoor_Entities/General_Classroom_Items/Clean_Blackboard.png");
		writingBlackBoard = ImageLoader.loadImage("/Indoor_Entities/General_Classroom_Items/Writing_Blackboard.png");
		projectorScreen = ImageLoader.loadImage("/Indoor_Entities/General_Classroom_Items/Projector_Screen.png");
		blackScreen = ImageLoader.loadImage("/blackBoard.png");
		
		battleBackground = ImageLoader.loadImage("/game_background.png");

		//floor = floorSheet.crop(32 * 8 - 9, 32 * 28 - 3, 32, 32);
		floor = ImageLoader.loadImage("/floor_tile.jpg");
		tree = ImageLoader.loadImage("/Outdoor_Entities/Tree.png");
		wall = ImageLoader.loadImage ("/Indoor_Entities/White_Walls.png");
		stairs = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Stairs.png");

		locker = ImageLoader.loadImage("/Indoor_Entities/Hallway_Items/Locker.png");
		lockerTop = ImageLoader.loadImage("/Indoor_Entities/Hallway_Items/Locker_Top.png");
		indoorWindowOpen = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Hallway_Window_Open.png");
		indoorWindowClosed = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Hallway_Window_Closed.png");
		openDoor = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Open_Door.png");
		sideDoor = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Side_Door.png");
		closedDoor = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Closed_Door.png");
		bench = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Bench.png");

		hallCouch = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Hall_Couch.png");
		hallChairLowRight = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Hall_Chair_Right.png");
		hallChairLowLeft = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Hall_Chair_Left.png");
		hallChairLowUp = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Hall_Chair_Front.png");
		hallTableLow = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Hall_Table_Low.png");
		hallTableHigh = ImageLoader.loadImage ("/Indoor_Entities/Hallway_Items/Hall_Table_High.png");

		cafTableSide = ImageLoader.loadImage ("/Indoor_Entities/Cafeteria/Caf_Table_Long_Left.png");
		cafTableUp = ImageLoader.loadImage ("/Indoor_Entities/Cafeteria/Caf_Table_Long_Up.png");
		vending1 = ImageLoader.loadImage ("/Indoor_Entities/Cafeteria/Vending1.png");
		vending2 = ImageLoader.loadImage ("/Indoor_Entities/Cafeteria/Vending2.png");

		studentDeskUp = ImageLoader.loadImage ("/Indoor_Entities/General_Classroom_Items/Desk_Chair_Front.png");
		studentDeskRight = ImageLoader.loadImage ("/Indoor_Entities/General_Classroom_Items/Desk_Chair_Right.png");
		teacherDeskUp= ImageLoader.loadImage ("/Indoor_Entities/General_Classroom_Items/Teacher_Desk_Up.png");
		teacherDeskSide = ImageLoader.loadImage ("/Indoor_Entities/General_Classroom_Items/Teacher_Desk_Side.png");
		studentChairRight = ImageLoader.loadImage ("/Indoor_Entities/General_Classroom_Items/Chair_Right.png");
		studentChairLeft = ImageLoader.loadImage ("/Indoor_Entities/General_Classroom_Items/Chair_Left.png");
		studentChairUp = ImageLoader.loadImage ("/Indoor_Entities/General_Classroom_Items/Chair_Front.png");

		labBench = ImageLoader.loadImage ("/Indoor_Entities/Subject_Specific_Items/Science/Lab_Bench.png");
		labTableSide = ImageLoader.loadImage ("/Indoor_Entities/Subject_Specific_Items/Science/Lab_Island_Side.png");
		labTableUp = ImageLoader.loadImage ("/Indoor_Entities/Subject_Specific_Items/Science/Lab_Island_Front.png");
		labStool = ImageLoader.loadImage ("/Indoor_Entities/Subject_Specific_Items/Science/Stool.png");
		labShelf = ImageLoader.loadImage ("/Indoor_Entities/Subject_Specific_Items/Science/Shelf.png");

		counterSide = ImageLoader.loadImage ("/Indoor_Entities/Guidance_Office/Counter_Side.png");
		counterUp = ImageLoader.loadImage ("/Indoor_Entities/Guidance_Office/Counter_Up.png");
		announcementBoard = ImageLoader.loadImage ("/Indoor_Entities/Guidance_Office/Announcement_Board.png");
		announcementBoard2 = ImageLoader.loadImage ("/Indoor_Entities/Guidance_Office/Announcement_Board2.png");
		greenBoard = ImageLoader.loadImage ("/Indoor_Entities/Guidance_Office/Green_Board.png");
		brownBoard = ImageLoader.loadImage ("/Indoor_Entities/Guidance_Office/Brown_Board.png");
		guidanceChairSide = ImageLoader.loadImage ("/Indoor_Entities/Guidance_Office/Chair_Side.png");
		guidanceChairBack = ImageLoader.loadImage ("/Indoor_Entities/Guidance_Office/Chair_Back.png");
		guidanceCouch = ImageLoader.loadImage ("/Indoor_Entities/Guidance_Office/Guidance_Couch.png");

		longTableSide = ImageLoader.loadImage ("/Indoor_Entities/General_Classroom_Items/Long_Table_Side.png");
		longTableUp = ImageLoader.loadImage ("/Indoor_Entities/General_Classroom_Items/Long_Table_Up.png");
		longTableSideTop = ImageLoader.loadImage ("/Indoor_Entities/General_Classroom_Items/Long_Table_Side_Top.png");
		longTableUpTop = ImageLoader.loadImage ("/Indoor_Entities/General_Classroom_Items/Long_Table_Up_Top.png");

		computer = ImageLoader.loadImage ("/Indoor_Entities/Subject_Specific_Items/Computer_Science/Computer.png");
        laptop = ImageLoader.loadImage ("/Indoor_Entities/Subject_Specific_Items/Computer_Science/Laptop.png");

		logo = new BufferedImage[2];
		logo[0] = ImageLoader.loadImage("/logo_up.png");
		logo[1] = ImageLoader.loadImage("/logo_down.png");


		//grass_1 = grassSheet.crop(0, 0, tileWidth, tileHeight);
		//grass_2 = grassSheet.crop(tileWidth, 0, tileWidth, tileHeight);
		path = grassSheet.crop(0, tileWidth, tileWidth, tileHeight);

		tileArray = new BufferedImage[60][27];
		for (int i = 0; i < 60; i ++) {
			for (int j = 0; j < 27; j++) {
				tileArray[i][j] = tileSheet.crop(i *(tileWidth + 1), j *(tileHeight + 1), tileWidth, tileHeight);
			}
		}
		rock = tileArray[7][7];
		blackBlock = tileArray[0][0];

//		gymTile =  tileArray [10][3];
//
//		gymTile = tileArray [30][2];

		//hallFloor = tileArray[58][14];
		
		
		gymTile = ImageLoader.loadImage("/gym_tile.png");

	}

	public static void loadFont() {
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
	}

	// ALL SPRITE INITIALISATION BELOW

	public static void loadSprites() {
		SpriteSheet playerSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/" + characterName + "/" + characterName.toLowerCase() + "_spritesheet.png"));
		SpriteSheet fengSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/Feng/feng_spritesheet.png"));
		SpriteSheet angelaSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/Angela/angela_spritesheet.png"));
		SpriteSheet billSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/Bill/bill_spritesheet.png"));
		SpriteSheet johannSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/Johann/johann_spritesheet.png"));
		SpriteSheet joyceSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/Joyce/joyce_spritesheet.png"));
		SpriteSheet mishaSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/Misha/misha_spritesheet.png"));
		SpriteSheet sihanSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/Sihan/sihan_spritesheet.png"));
		SpriteSheet yashSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/Yash/yash_spritesheet.png"));
		SpriteSheet aaronSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/Aaron/aaron_spritesheet.png"));
		SpriteSheet alstonSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/Alston/alston_spritesheet.png"));
		SpriteSheet michaelSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/Michael/michael_spritesheet.png"));
		SpriteSheet nikhilSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/Nikhil/nikhil_spritesheet.png"));
		SpriteSheet rosemarySheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/Rosemary/rosemary_spritesheet.png"));
		SpriteSheet randomNinerSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/RandomNiner/niner_spritesheet.png"));
		SpriteSheet choiSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/MrChoi/choi_spritesheet.png"));
		SpriteSheet timmermanSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/MrTimmerman/timmerman_spritesheet.png"));
		SpriteSheet shimSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/MrShim/shim_spritesheet.png"));
		SpriteSheet kostanenkoSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/MsKostanenko/kostanenko_spritesheet.png"));
		SpriteSheet harrisSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/MrHarris/harris_spritesheet.png"));
		SpriteSheet gissingSheet = new SpriteSheet(ImageLoader.loadImage ("/Sprite_Images/sprites/characters/MrGissing/gissing_spritesheet.png"));

		player_down = new BufferedImage[3];
		player_up = new BufferedImage[3];
		player_left = new BufferedImage[3];
		player_right = new BufferedImage[3];

		feng_down = new BufferedImage[3];
		feng_up = new BufferedImage[3];
		feng_left = new BufferedImage[3];
		feng_right = new BufferedImage[3];
		
		angela_down = new BufferedImage[3];
		angela_up = new BufferedImage[3];
		angela_left = new BufferedImage[3];
		angela_right = new BufferedImage[3];
		
		bill_down = new BufferedImage[3];
		bill_up = new BufferedImage[3];
		bill_left = new BufferedImage[3];
		bill_right = new BufferedImage[3];
		
		johann_down = new BufferedImage[3];
		johann_up = new BufferedImage[3];
		johann_left = new BufferedImage[3];
		johann_right = new BufferedImage[3];
		
		joyce_down = new BufferedImage[3];
		joyce_up = new BufferedImage[3];
		joyce_left = new BufferedImage[3];
		joyce_right = new BufferedImage[3];
		
		misha_down = new BufferedImage[3];
		misha_up = new BufferedImage[3];
		misha_left = new BufferedImage[3];
		misha_right = new BufferedImage[3];
		
		sihan_down = new BufferedImage[3];
		sihan_up = new BufferedImage[3];
		sihan_left = new BufferedImage[3];
		sihan_right = new BufferedImage[3];
		
		yash_down = new BufferedImage[3];
		yash_up = new BufferedImage[3];
		yash_left = new BufferedImage[3];
		yash_right = new BufferedImage[3];

		aaron_down = new BufferedImage[3];
		aaron_up = new BufferedImage[3];
		aaron_left = new BufferedImage[3];
		aaron_right = new BufferedImage[3];

		alston_down = new BufferedImage[3];
		alston_up = new BufferedImage[3];
		alston_left = new BufferedImage[3];
		alston_right = new BufferedImage[3];

		michael_down = new BufferedImage[3];
		michael_up = new BufferedImage[3];
		michael_left = new BufferedImage[3];
		michael_right = new BufferedImage[3];

		nikhil_down = new BufferedImage[3];
		nikhil_up = new BufferedImage[3];
		nikhil_left = new BufferedImage[3];
		nikhil_right = new BufferedImage[3];


		rosemary_down = new BufferedImage[3];
		rosemary_up = new BufferedImage[3];
		rosemary_left = new BufferedImage[3];
		rosemary_right = new BufferedImage[3];

		randomNiner_down = new BufferedImage[3];
		randomNiner_up = new BufferedImage[3];
		randomNiner_left = new BufferedImage[3];
		randomNiner_right = new BufferedImage[3];

		timmerman_down = new BufferedImage[3];
		timmerman_up = new BufferedImage[3];
		timmerman_left = new BufferedImage[3];
		timmerman_right = new BufferedImage[3];

		choi_down = new BufferedImage[3];
		choi_up = new BufferedImage[3];
		choi_left = new BufferedImage[3];
		choi_right = new BufferedImage[3];

		shim_down = new BufferedImage[3];
		shim_up = new BufferedImage[3];
		shim_left = new BufferedImage[3];
		shim_right = new BufferedImage[3];

		harris_down = new BufferedImage[3];
		harris_up = new BufferedImage[3];
		harris_left = new BufferedImage[3];
		harris_right = new BufferedImage[3];

		kostanenko_down = new BufferedImage[3];
		kostanenko_up = new BufferedImage[3];
		kostanenko_left = new BufferedImage[3];
		kostanenko_right = new BufferedImage[3];

		gissing_down = new BufferedImage[3];
		gissing_up = new BufferedImage[3];
		gissing_left = new BufferedImage[3];
		gissing_right = new BufferedImage[3];
		
		for (int i = 0; i < 3; i++) {
			player_down[i] = playerSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			feng_down[i] = fengSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			angela_down[i] = angelaSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			bill_down[i] = billSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			johann_down[i] = johannSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			joyce_down[i] = joyceSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			misha_down[i] = mishaSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			sihan_down[i] = sihanSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			yash_down[i] = yashSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			aaron_down[i] = aaronSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			alston_down[i] = alstonSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			michael_down[i] = michaelSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			nikhil_down[i] = nikhilSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			rosemary_down[i] = rosemarySheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			randomNiner_down[i] = randomNinerSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			timmerman_down[i] = timmermanSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			choi_down[i] = choiSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			shim_down[i] = shimSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			harris_down[i] = harrisSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			kostanenko_down[i] = kostanenkoSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);
			gissing_down[i] = gissingSheet.crop((charWidth + 1 )*i, 0, charWidth, charHeight);

		}
		for (int i = 0; i < 3; i++) {
			player_left[i] = playerSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			feng_left[i] = fengSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			angela_left[i] = angelaSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			bill_left[i] = billSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			johann_left[i] = johannSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			joyce_left[i] = joyceSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			misha_left[i] = mishaSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			sihan_left[i] = sihanSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			yash_left[i] = yashSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			aaron_left[i] = aaronSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			alston_left[i] = alstonSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			michael_left[i] = michaelSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			nikhil_left[i] = nikhilSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			rosemary_left[i] = rosemarySheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			randomNiner_left[i] = randomNinerSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			timmerman_left[i] = timmermanSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			choi_left[i] = choiSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			shim_left[i] = shimSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			harris_left[i] = harrisSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			kostanenko_left[i] = kostanenkoSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);
			gissing_left[i] = gissingSheet.crop(charWidth*(i+3) + 1, 0, charWidth - 1, charHeight);

		}
		for (int i = 0; i < 3; i++) {
			player_right[i] = playerSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			feng_right[i] = fengSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			angela_right[i] = angelaSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			bill_right[i] = billSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			johann_right[i] = johannSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			joyce_right[i] = joyceSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			misha_right[i] = mishaSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			sihan_right[i] = sihanSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			yash_right[i] = yashSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			aaron_right[i] = aaronSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			alston_right[i] = alstonSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			michael_right[i] = michaelSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			nikhil_right[i] = nikhilSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			rosemary_right[i] = rosemarySheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			randomNiner_right[i] = randomNinerSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			timmerman_right[i] = timmermanSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			choi_right[i] = choiSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			shim_right[i] = shimSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			harris_right[i] = harrisSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			kostanenko_right[i] = kostanenkoSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
			gissing_right[i] = gissingSheet.crop(charWidth*(i+6) - 2, 0, charWidth - 1, charHeight);
		}
		for (int i = 0; i < 3; i++) {
			player_up[i] = playerSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			feng_up[i] = fengSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			angela_up[i] = angelaSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			bill_up[i] = billSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			johann_up[i] = johannSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			joyce_up[i] = joyceSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			misha_up[i] = mishaSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			sihan_up[i] = sihanSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			yash_up[i] = yashSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			aaron_up[i] = aaronSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			alston_up[i] = alstonSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			michael_up[i] = michaelSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			nikhil_up[i] = nikhilSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			rosemary_up[i] = yashSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			randomNiner_up[i] = randomNinerSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			timmerman_up[i] = timmermanSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			choi_up[i] = choiSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			shim_up[i] = shimSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			harris_up[i] = harrisSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			kostanenko_up[i] = kostanenkoSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);
			gissing_up[i] = gissingSheet.crop(charWidth*(i+9) - 3, 0, charWidth, charHeight);

		}

//		player_down[0] = playerSheet.crop(charWidth*0, 0, charWidth, charHeight);
//		player_down[1] = playerSheet.crop(charWidth*1, 0, charWidth, charHeight);
//		player_down[2] = playerSheet.crop(charWidth*2, 0, charWidth, charHeight);
//		player_left[0] = playerSheet.crop(charWidth*3 + 1, 0, charWidth - 1, charHeight);
//		player_left[1] = playerSheet.crop(charWidth*4 + 1, 0, charWidth - 1, charHeight);
//		player_left[2] = playerSheet.crop(charWidth*5 + 1, 0, charWidth - 1, charHeight);
//		player_right[0] = playerSheet.crop(charWidth*6 - 2, 0, charWidth - 1, charHeight);
//		player_right[1] = playerSheet.crop(charWidth*7 - 2, 0, charWidth - 1, charHeight);
//		player_right[2] = playerSheet.crop(charWidth*8 - 2, 0, charWidth - 1, charHeight);
//		player_up[0] = playerSheet.crop(charWidth*9 - 3, 0, charWidth, charHeight);
//		player_up[1] = playerSheet.crop(charWidth*10 - 3, 0, charWidth, charHeight);
//		player_up[2] = playerSheet.crop(charWidth*11 - 3, 0, charWidth, charHeight);

	}
}