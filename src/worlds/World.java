package worlds;

import java.awt.Graphics;

import entities.EntityManager;
import entities.creatures.NPC;
import entities.creatures.Player;
import entities.statics.*;
import game.Game;
import game.Handler;
import graphics.Assets;
import tiles.Tile;
import utils.Utils;

public class World {

	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] tiles;

	// Entities
	private EntityManager entityManager;


	public World(Handler handler, String path) {

        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, spawnX * Tile.TILE_WIDTH, spawnY * Tile.TILE_WIDTH));

        if (path.equals("res/worlds/world1.txt")) {
            loadTopHall();
        } else if (path.equals("res/worlds/world2.txt")) {
            loadBottomHall();
        } else if (path.equals("res/worlds/cafeteria.txt")) {
            loadCafeteria();
        } else if (path.equals("res/worlds/guidance.txt")) {
            loadGuidance();
        } else if (path.equals("res/worlds/compsci.txt")) {
            loadCompSci();
        } else if (path.equals("res/worlds/drama.txt")) {
            loadDrama();
        } else if (path.equals("res/worlds/english.txt")) {
            loadEnglish();
        } else if (path.equals("res/worlds/gym.txt")) {
            loadGym();
        } else if (path.equals("res/worlds/math.txt")) {
            loadMath();
        } else {
            loadScience();
        }

		loadWorld(path);

		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);


	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void tick() {
		entityManager.tick();
	}

	public void render(Graphics g) {

		// SMART RENDERING
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);


		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x, y).render(g,
						(int)(x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
						(int)(y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
			}
		}

		entityManager.render(g);

	}

	public Tile getTile(int x, int y) {
		//System.out.println(x + "," + y);
		if(x < 0 || y < 0 || x >= width || y >= height) {
			return Tile.grassTile;
		}

		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null) {
			return Tile.nullTile;
		}
		return t;

	}

	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");

		// First 4 numbers in file are the values
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);

		tiles = new int[width][height];

		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public void loadTopHall() {

		//entityManager.addEntity(new NPC(handler, Assets.angela_down, 100, 250, 100));
		entityManager.addEntity(new NPC(handler, Assets.angela_down, Assets.angela_up, Assets.angela_left, Assets.angela_right, 100, 250, 100));
		entityManager.addEntity(new NPC(handler, Assets.feng_down, Assets.feng_up, Assets.feng_left, Assets.feng_right, 100, 400, 100));
		entityManager.addEntity(new NPC(handler, Assets.bill_down, Assets.bill_up, Assets.bill_left, Assets.bill_right, 100, 550, 100));
		entityManager.addEntity(new NPC(handler, Assets.joyce_down, Assets.joyce_up, Assets.joyce_left, Assets.joyce_right, 100, 700, 100));
		entityManager.addEntity(new NPC(handler, Assets.yash_down, Assets.yash_up, Assets.yash_left, Assets.yash_right, 100, 600, 300));
		entityManager.addEntity(new NPC(handler, Assets.sihan_down, Assets.sihan_up, Assets.sihan_left, Assets.sihan_right, 100, 1000, 100));
		entityManager.addEntity(new NPC(handler, Assets.misha_down, Assets.misha_up, Assets.misha_left, Assets.misha_right, 100, 1000, 300));
		entityManager.addEntity(new NPC(handler, Assets.johann_down, Assets.johann_up, Assets.johann_left, Assets.johann_right, 100, 1300, 100));


		//HALLWAY
		//Walls and windows

		//Lockers & windows
		for (int x = 2; x < 9; x++) {
			entityManager.addEntity(new IndoorWindowClosed(handler, (40 * x), 10));
		}
		for (int x = 15; x < 26; x++) {
			entityManager.addEntity(new Locker(handler, (30 * x), 20));
		}
		for (int x = 28; x < 40; x++) {
			entityManager.addEntity(new Locker(handler, (30 * x), 20));
		}
		for (int x = 32; x < 39; x++) {
			entityManager.addEntity(new IndoorWindowOpen(handler, (40 * x + 15), 10));
		}

		for (int x = 35; x < 45; x++) {
			entityManager.addEntity(new Locker(handler, (30 * x), 275));
		}
		for (int x = 35; x < 45; x++) {
			entityManager.addEntity(new Locker(handler, (30 * x), 290));
		}

		//Bench
		for (int x = 5; x < 38; x++) {
			entityManager.addEntity(new Bench(handler, (10 * x), 60));
		}
		for (int x = 127; x < 160; x++) {
			entityManager.addEntity(new Bench(handler, (10 * x), 60));
		}

		//Doors
//		entityManager.addEntity(new HallwayDoorOpen(handler, (40 * 10), 10, "res/worlds/world2.txt", 410, 60));
//		entityManager.addEntity(new HallwayDoorOpen(handler, (40 * 20 - 9), 10, "res/worlds/world2.txt", 800, 60));
//		entityManager.addEntity(new HallwayDoorOpen(handler, (40 * 30 + 10), 10, "res/worlds/world2.txt", 1217, 90));
		
		entityManager.addEntity(new HallwayDoorOpen(handler, (40 * 10), 10, "res/worlds/math.txt", 0, 0));
		entityManager.addEntity(new HallwayDoorOpen(handler, (40 * 10), 10, "res/worlds/math.txt", 0, 0));
		entityManager.addEntity(new HallwayDoorOpen(handler, (40 * 10), 10, "res/worlds/math.txt", 0, 0));

		

		//Hall Library Chairs/Tables
		entityManager.addEntity(new HallChairLowUp(handler, 828, 343));
		entityManager.addEntity(new HallChairLowLeft(handler, 800, 370));
		entityManager.addEntity(new HallChairLowUp(handler, 828, 390));
		entityManager.addEntity(new HallTableHigh(handler, 820, 360));

		entityManager.addEntity(new HallChairLowUp(handler, 808, 463));
		entityManager.addEntity(new HallChairLowLeft(handler, 780, 490));
		entityManager.addEntity(new HallChairLowUp(handler, 808, 510));
		entityManager.addEntity(new HallChairLowRight(handler, 835, 490));
		entityManager.addEntity(new HallTableHigh(handler, 800, 480));

		entityManager.addEntity(new HallChairLowUp(handler, 638, 503));
		entityManager.addEntity(new HallChairLowLeft(handler, 610, 530));
		entityManager.addEntity(new HallChairLowUp(handler, 638, 550));
		entityManager.addEntity(new HallChairLowRight(handler, 665, 530));
		entityManager.addEntity(new HallTableHigh(handler, 630, 520));

		entityManager.addEntity(new HallCouch(handler, 550, 400));
		entityManager.addEntity(new HallTableLow(handler, 600, 400));

		//Stairs
		entityManager.addEntity(new Stairs(handler, 384, 320, "res/worlds/world2.txt", 545, 205));
	}
	
	public void loadBottomHall() {
		
		entityManager.addEntity(new NPC(handler, Assets.angela_down, Assets.angela_up, Assets.angela_left, Assets.angela_right, 100, 250, 100));
		entityManager.addEntity(new NPC(handler, Assets.feng_down, Assets.feng_up, Assets.feng_left, Assets.feng_right, 100, 400, 100));
		entityManager.addEntity(new NPC(handler, Assets.bill_down, Assets.bill_up, Assets.bill_left, Assets.bill_right, 100, 550, 100));
		entityManager.addEntity(new NPC(handler, Assets.joyce_down, Assets.joyce_up, Assets.joyce_left, Assets.joyce_right, 100, 700, 60));
		entityManager.addEntity(new NPC(handler, Assets.yash_down, Assets.yash_up, Assets.yash_left, Assets.yash_right, 100, 350, 300));
		entityManager.addEntity(new NPC(handler, Assets.sihan_down, Assets.sihan_up, Assets.sihan_left, Assets.sihan_right, 100, 1000, 100));
		entityManager.addEntity(new NPC(handler, Assets.misha_down, Assets.misha_up, Assets.misha_left, Assets.misha_right, 100, 1140, 440));
		entityManager.addEntity(new NPC(handler, Assets.johann_down, Assets.johann_up, Assets.johann_left, Assets.johann_right, 100, 1300, 100));

	    //HALLWAY
		//Walls and windows

		//entityManager.addEntity(new NPC(handler, "Feng2", 100, 400, 150));

		//Lockers & windows
		for (int x = 2; x < 9; x++) {
			entityManager.addEntity(new IndoorWindowClosed(handler, (40 * x), 10));
		}
		for (int x = 12; x < 19; x++) {
			entityManager.addEntity(new IndoorWindowClosed(handler, (40 * x - 4), 10));
		}
		for (int x = 28; x < 39; x++) {
			entityManager.addEntity(new Locker(handler, (30 * x), 20));
		}
		for (int x = 42; x < 53; x++) {
			entityManager.addEntity(new Locker(handler, (30 * x + 5), 50));
		}

		for (int x = 29; x < 37; x++) {
			entityManager.addEntity(new Locker(handler, (30 * x - 4), 400));
		}
		for (int y = 16; y < 24; y++) {
		    entityManager.addEntity(new LockerTop(handler, (41 * 29 + 11), (17 * y - 5)));
		}
		for (int y = 10; y < 13; y++) {
		    entityManager.addEntity(new LockerTop(handler, (36 * 29 - 4), (16 * y + 6)));
		}

		entityManager.addEntity(new LockerTop(handler, (40 * 29 + 8), 20));
		entityManager.addEntity(new LockerTop(handler, (40 * 29 + 8), 37));

		for (int x = 30; x < 32; x++) {
		    entityManager.addEntity(new IndoorWindowOpen(handler, (40 * x + 18), 390));
		}

		//Bench
        for (int x = 5; x < 38; x++) {
		    entityManager.addEntity(new Bench(handler, (10 * x), 60));
		}
		for (int x = 46; x < 78; x++) {
		    entityManager.addEntity(new Bench(handler, (10 * x - 5), 60));
		}

		//Doors
        entityManager.addEntity(new HallwayDoorOpen(handler, (40 * 10), 10, "res/worlds/world1.txt", 400, 60));
		entityManager.addEntity(new HallwayDoorOpen(handler, (40 * 20 - 9), 10, "res/worlds/world1.txt", 800, 60));
		entityManager.addEntity(new HallwayDoorOpen(handler, (40 * 31 - 24), 40, "res/worlds/world1.txt", 1217, 60));

		//Stairs
        entityManager.addEntity(new Stairs(handler, 576, 193, "res/worlds/world1.txt", 490, 310));
	}

    public void loadCafeteria() {

        entityManager.addEntity(new NPC(handler, Assets.angela_down, Assets.angela_up, Assets.angela_left, Assets.angela_right, 100, 250, 100));

        //Caf Chairs Left
        for (int y = 21; y < 25; y++) {
            entityManager.addEntity(new HallChairLowLeft(handler, (20 + (int)(Math.random() * 5)), (30 * y)));
        }
        for (int y = 8; y < 10; y++) {
            entityManager.addEntity(new HallChairLowLeft(handler, (20 + (int)(Math.random() * 5)), (30 * y)));
        }
        for (int y = 4; y < 6; y++) {
            entityManager.addEntity(new HallChairLowLeft(handler, (20 + (int)(Math.random() * 5)), (40 * y)));
        }

        for (int y = 16; y <22; y++) {
            entityManager.addEntity(new HallChairLowLeft(handler, (170 + (int)(Math.random() * 5)), (30 * y)));
        }
        for (int y = 12; y < 14; y++) {
            entityManager.addEntity(new HallChairLowLeft(handler, (170 + (int)(Math.random() * 5)), (40 * y)));
        }
        for (int y = 7; y < 11; y++) {
            entityManager.addEntity(new HallChairLowLeft(handler, (170 + (int)(Math.random() * 5)), (30 * y)));
        }
        for (int y = 3; y < 5; y++) {
            entityManager.addEntity(new HallChairLowLeft(handler, (170 + (int)(Math.random() * 5)), (40 * y)));
        }

        for (int y = 20; y < 25; y++) {
            entityManager.addEntity(new HallChairLowLeft(handler, (320 + (int)(Math.random() * 5)), (30 * y)));
        }
        for (int y = 12; y < 14; y++) {
            entityManager.addEntity(new HallChairLowLeft(handler, (320 + (int)(Math.random() * 5)), (40 * y)));
        }
        for (int y = 10; y < 12; y++) {
            entityManager.addEntity(new HallChairLowLeft(handler, (320 + (int)(Math.random() * 5)), (30 * y)));
        }
        for (int y = 4; y < 6; y++) {
            entityManager.addEntity(new HallChairLowLeft(handler, (320 + (int)(Math.random() * 5)), (40 * y)));
        }

        for (int y = 18; y <23; y++) {
            entityManager.addEntity(new HallChairLowLeft(handler, (470 + (int)(Math.random() * 5)), (30 * y)));
        }
        for (int y = 7; y < 9; y++) {
            entityManager.addEntity(new HallChairLowLeft(handler, (470 + (int)(Math.random() * 5)), (40 * y)));
        }
        for (int y = 9; y < 10; y++) {
            entityManager.addEntity(new HallChairLowLeft(handler, (470 + (int)(Math.random() * 5)), (30 * y)));
        }
        for (int y = 4; y < 6; y++) {
            entityManager.addEntity(new HallChairLowLeft(handler, (470 + (int)(Math.random() * 5)), (40 * y)));
        }

        //Caf Chairs Right
        for (int y = 20; y < 25; y++) {
            entityManager.addEntity(new HallChairLowRight(handler, (65 + (int)(Math.random() * 5)), (30 * y)));
        }
        for (int y = 12; y < 14; y++) {
            entityManager.addEntity(new HallChairLowRight(handler, (65 + (int)(Math.random() * 5)), (40 * y)));
        }
        for (int y = 10; y < 12; y++) {
            entityManager.addEntity(new HallChairLowRight(handler, (65 + (int)(Math.random() * 5)), (30 * y)));
        }
        for (int y = 3; y < 6; y++) {
            entityManager.addEntity(new HallChairLowRight(handler, (65 + (int)(Math.random() * 5)), (40 * y)));
        }

        for (int y = 18; y <23; y++) {
            entityManager.addEntity(new HallChairLowRight(handler, (215 + (int)(Math.random() * 5)), (30 * y)));
        }
        for (int y = 7; y < 9; y++) {
            entityManager.addEntity(new HallChairLowRight(handler, (215 + (int)(Math.random() * 5)), (40 * y)));
        }
        for (int y = 9; y < 10; y++) {
            entityManager.addEntity(new HallChairLowRight(handler, (215 + (int)(Math.random() * 5)), (30 * y)));
        }
        for (int y = 3; y < 6; y++) {
            entityManager.addEntity(new HallChairLowRight(handler, (215 + (int)(Math.random() * 5)), (40 * y)));
        }

        for (int y = 12; y < 14; y++) {
            entityManager.addEntity(new HallChairLowRight(handler, (365 + (int)(Math.random() * 5)), (40 * y)));
        }
        for (int y = 8; y < 10; y++) {
            entityManager.addEntity(new HallChairLowRight(handler, (365 + (int)(Math.random() * 5)), (30 * y)));
        }
        for (int y = 3; y < 6; y++) {
            entityManager.addEntity(new HallChairLowRight(handler, (365 + (int)(Math.random() * 5)), (40 * y)));
        }

        for (int y = 16; y <22; y++) {
            entityManager.addEntity(new HallChairLowRight(handler, (515 + (int)(Math.random() * 5)), (30 * y)));
        }
        for (int y = 12; y < 14; y++) {
            entityManager.addEntity(new HallChairLowRight(handler, (515 + (int)(Math.random() * 5)), (40 * y)));
        }
        for (int y = 7; y < 11; y++) {
            entityManager.addEntity(new HallChairLowRight(handler, (515 + (int)(Math.random() * 5)), (30 * y)));
        }
        for (int y = 3; y < 5; y++) {
            entityManager.addEntity(new HallChairLowRight(handler, (515 + (int)(Math.random() * 5)), (40 * y)));
        }

        for (int y = 21; y < 25; y++) {
            entityManager.addEntity(new HallChairLowRight(handler, 320, (30 * y)));
        }

        for (int x = 20; x < 26; x++) {
            entityManager.addEntity(new HallChairLowUp(handler, (35 * x), (110 + (int)(Math.random() * 5))));
        }
        for (int x = 15; x < 19; x++) {
            entityManager.addEntity(new HallChairLowUp(handler, (50 * x), (140 + (int)(Math.random() * 5))));
        }

        for (int x = 17; x < 20; x++) {
            entityManager.addEntity(new HallChairLowUp(handler, (45 * x), (195 + (int)(Math.random() * 5))));
        }
        for (int x = 22; x < 28; x++) {
            entityManager.addEntity(new HallChairLowUp(handler, (30 * x), (230 + (int)(Math.random() * 5))));
        }

        //Caf tables
        for (int x = 2; x < 6; x++) {
            for (int y = 2; y < 7; y++) {
                entityManager.addEntity(new CafTableSide(handler, (150 * x - 260), (57 * y)));
            }
            for (int y = 8; y < 13; y++) {
                entityManager.addEntity(new CafTableSide(handler, (150 * x - 260), (57 * y)));
            }
        }

        for (int x = 11; x < 16; x++) {
            for (int y = 2; y < 4; y++) {
                entityManager.addEntity(new CafTableUp(handler, (57 * x + 20), (80 * y - 30)));
            }
        }

        //Doors
        entityManager.addEntity(new HallwayDoorClosed(handler, 650, 10));
        entityManager.addEntity(new HallwayDoorOpen(handler, 950, 10, "res/worlds/world2.txt", 20, 100));
        entityManager.addEntity(new HallwayDoorClosed(handler, 340, 10));

        //Vending machines
        entityManager.addEntity(new Vending2(handler, 450, 20));
        entityManager.addEntity(new Vending1(handler, 520, 20));
        entityManager.addEntity(new Vending2(handler, 740, 20));
        entityManager.addEntity(new Vending2(handler, 780, 20));
        entityManager.addEntity(new Vending2(handler, 820, 20));
        entityManager.addEntity(new Vending2(handler, 860, 20));
    }

    public void loadGuidance() {

	    //Counter
        for (int y = 2; y < 6; y++) {
            entityManager.addEntity(new CounterSide(handler, 180, (50 * y + 70)));
        }
        for (int x = 2; x < 6; x++) {
            entityManager.addEntity(new CounterUp(handler, (x * 50 + 102), 170));
        }
        for (int y = 2; y < 6; y++) {
            entityManager.addEntity(new CounterSide(handler, 385, (50 * y + 70)));
        }
        for (int y = 1; y < 6; y++) {
            entityManager.addEntity(new CounterSide(handler, 0, (50 * y)));
        }

        //Chairs
        entityManager.addEntity(new GuidanceChairBack(handler, 280, 190));
        entityManager.addEntity(new GuidanceChairSide(handler, 365, 240));
        entityManager.addEntity(new GuidanceChairSide(handler, 370, 300));
        entityManager.addEntity(new GuidanceCouch(handler, 320, 50));

        //Announcement Boards
        entityManager.addEntity(new AnnouncementBoard(handler, 200, 20));
        entityManager.addEntity(new AnnouncementBoard2(handler, 250, 20));
        entityManager.addEntity(new GreenBoard(handler, 300, 20));

        //Waiting Area
        entityManager.addEntity(new HallTableHigh(handler, 250, 80));
        entityManager.addEntity(new HallChairLowUp(handler, 258, 115));
        entityManager.addEntity(new HallChairLowLeft(handler, 230, 90));

        //Door
        entityManager.addEntity(new HallwayDoorOpen(handler, 50, 10, "res/worlds/world2.txt", 555, 350));
        entityManager.addEntity(new HallwayDoorClosed(handler, 90, 10));

    }

    public void loadCompSci() {

    }

    public void loadDrama() {

    }

    public void loadEnglish() {

    }

    public void loadGym() {

    }

    public void loadMath() {

	    //Desks
        for (int y = 1; y < 4; y++) {
            for (int x = 1; x < 5; x++) {
                entityManager.addEntity(new StudentDeskUp(handler, (x * 40), (y * 80 + 35)));
            }
            for (int x = 2; x < 6; x++) {
                entityManager.addEntity(new StudentDeskUp(handler, (x * 40 + 180), (y * 80 + 35)));
            }
        }

        //Chairs
        for (int y = 1; y < 4; y++) {
            for (int x = 1; x < 5; x++) {
                entityManager.addEntity(new StudentChairUp(handler, (x * 40 + 10), (y * 80 + 65)));
            }
            for (int x = 7; x < 11; x++) {
                entityManager.addEntity(new StudentChairUp(handler, (x * 40 - 10), (y * 80 + 65)));
            }
        }

        //Teacher's Desk and Chair
        entityManager.addEntity(new TeacherDeskUp(handler, 270, 75));

	    //Blackboard
        entityManager.addEntity(new Blackboard(handler, 220, 20));
        entityManager.addEntity(new Blackboard(handler, 270, 20));
        entityManager.addEntity(new WritingBlackboard(handler, 320, 20));

        //Door
        entityManager.addEntity(new HallwayDoorOpen(handler, 100, 10, "res/worlds/world1.txt", 20, 100));

    }

    public void loadScience() {

    }

}
