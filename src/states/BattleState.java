package states;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import battle.BattleRunner;
import game.Handler;
import graphics.Animation;
import graphics.Assets;

public class BattleState extends State{


	private int count = 0;

	private BattleRunner battleTest;
	private int answer = -1;
	boolean textLoading = false;
	private Animation shake;

	private boolean menuScreen = false, characterSelectionScreen;
	private boolean[][] menu = new boolean[2][2];
	private boolean[][] characterSelection = new boolean[3][2];
	private int x, y;
	private boolean cannotSwitchCharacter;

	public BattleState(Handler handler, Graphics g) {
		super(handler);
		//handler.setWorld(null);
		battleTest = new BattleRunner();

		shake = new Animation(300, Assets.feng_down);
		menu[0][0] = false;
		menu[0][1] = false;
		menu[1][0] = false;
		menu[1][1] = false;

		characterSelection[0][0] = false;
		characterSelection[0][1] = false;
		characterSelection[1][0] = false;
		characterSelection[1][1] = false;
		characterSelection[2][0] = false;
		characterSelection[2][1] = false;

	}

	@Override
	public void tick() {
		shake.tick();

		// if (battleTest.isBattleEnd()) {
		// 	State.setState(handler.getGame().getGameState());
		// 	return;
		// }
		if (!battleTest.battleStart) {
			return;
		}
		if (characterSelectionScreen) {
			if (handler.getKeyManager().up) {
				if (y != 0) {
					characterSelection[y][x] = false;
					y--;
					characterSelection[y][x] = true;
					handler.getKeyManager().up = false;
				}
			} else if (handler.getKeyManager().down) {
				if (y != 2) {
					characterSelection[y][x] = false;
					y++;
					characterSelection[y][x] = true;
					handler.getKeyManager().down = false;
				}
			} else if (handler.getKeyManager().left) {
				if (x != 0) {
					characterSelection[y][x] = false;
					x--;
					characterSelection[y][x] = true;
					handler.getKeyManager().left = false;
				}
			} else if (handler.getKeyManager().right) {
				if (x != 1) {
					characterSelection[y][x] = false;
					x++;
					characterSelection[y][x] = true;
					handler.getKeyManager().right = false;
				}
			} else if (handler.getKeyManager().enter) {
				if (y == 0) {
					if (x == 0) {
						answer = 1;
					} else if (x == 1) {
						answer = 2;
					}
				} else if (y == 1) {
					if (x == 0) {
						answer = 3;
					} else if (x == 1) {
						answer = 4;
					}
				} else if (y == 2) {
					if (x == 0) {
						answer = 5;
					} else if (x == 1) {
						answer = 6;
					}
				}
				if (battleTest.getSquad().getCharacter(answer) != null) {
					characterSelectionScreen = false;
					menuScreen = false;
					characterSelection[0][0] = false;
					characterSelection[0][1] = false;
					characterSelection[1][0] = false;
					characterSelection[1][1] = false;
					characterSelection[2][0] = false;
					characterSelection[2][1] = false;
					x = 0;
					y = 0;
					handler.getKeyManager().enter = false;
				} else {
					System.out.println("Sorry but no");
				}
			} else if (handler.getKeyManager().backspace) {
				answer = 10;
				characterSelectionScreen = false;
				menuScreen = false;
				characterSelection[0][0] = false;
				characterSelection[0][1] = false;
				characterSelection[1][0] = false;
				characterSelection[1][1] = false;
				characterSelection[2][0] = false;
				characterSelection[2][1] = false;
				x = 0;
				y = 0;
				handler.getKeyManager().backspace = false;
			}
		}
		if (!textLoading && !battleTest.getSelectionStrings(0).equals("null")) {
			if (handler.getKeyManager().up) {
				if (y != 0) {
					menu[y][x] = false;
					y--;
					menu[y][x] = true;
					handler.getKeyManager().up = false;
				}
			} else if (handler.getKeyManager().down) {
				if (y != 1) {
					menu[y][x] = false;
					y++;
					menu[y][x] = true;
					handler.getKeyManager().down = false;
				}
			} else if (handler.getKeyManager().left) {
				if (x != 0) {
					menu[y][x] = false;
					x--;
					menu[y][x] = true;
					handler.getKeyManager().left = false;
				}
			} else if (handler.getKeyManager().right) {
				if (x != 1) {
					menu[y][x] = false;
					x++;
					menu[y][x] = true;
					handler.getKeyManager().right = false;
				}
			} else if (handler.getKeyManager().enter) {
				if (y == 0) {
					 if (x == 0) {
					 	answer = 1;
					 } else if (x == 1) {
						 answer = 2;
					 }
				} else if (y == 1) {
					if (x == 0) {
						answer = 3;
					} else if (x == 1) {
						answer = 4;
					}
				}
				menuScreen = false;
				menu[0][0] = false;
				menu[0][1] = false;
				menu[1][0] = false;
				menu[1][1] = false;
				x = 0;
				y = 0;
				handler.getKeyManager().enter = false;
			} else if (handler.getKeyManager().backspace) {
				answer = 10;
				menuScreen = false;
				menu[0][0] = true;
				menu[0][1] = false;
				menu[1][0] = false;
				menu[1][1] = false;
				x = 0;
				y = 0;
				handler.getKeyManager().backspace = false;
			}
		}

		if (handler.getKeyManager().enter && textLoading) {
			if (count < battleTest.getTextArrayList().get(0).length()*4) {
				count = battleTest.getTextArrayList().get(0).length() * 4 + 200;
			} else {
				count = 100000;
			}
			handler.getKeyManager().enter = false;
			handler.getKeyManager().backspace = false;
		} else if (textLoading) {
			//Nothing should happen if the text is currently being displayed
			handler.getKeyManager().enter = false;
			handler.getKeyManager().backspace = false;
			return;
		} else if (answer != -1) {
			battleTest.runPhase(answer);
			answer = -1;
			handler.getKeyManager().enter = false;
			handler.getKeyManager().backspace = false;
		}
	}

	@Override
	public void render(Graphics g) {
		if (!battleTest.battleStart) {
			return;
		}
		count++;
		//g.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawImage(Assets.battleBackground, 0, 0, null);
		if (battleTest.getPlayer().getName().length() > 15) {
			g.setFont(Assets.font10);
		} else if (battleTest.getPlayer().getName().length() > 10) {
			g.setFont(Assets.font12);
		} else {
			g.setFont(Assets.font16);
		}
		g.drawString(battleTest.getPlayer().getName(), 350, 210);
		if (battleTest.getOpponent().getName().length() > 15) {
			g.setFont(Assets.font10);
		} else if (battleTest.getOpponent().getName().length() > 10) {
			g.setFont(Assets.font12);
		} else {
			g.setFont(Assets.font16);
		}
		g.drawString(battleTest.getOpponent().getName(), 50, 70);
		//g.setFont(new Font("Arial", Font.PLAIN, 17));
		g.setFont(Assets.font12);
		g.drawString(Integer.toString(battleTest.getPlayer().getCurrentHealth()), 475, 255);
		g.drawString(Integer.toString(battleTest.getPlayer().getInitialHealth()), 530, 255);
		g.drawString(Integer.toString(battleTest.getOpponent().getCurrentHealth()), 50, 90);

		//g.setFont(Assets.font8);
		//g.drawString("", 200, 50);

		if (battleTest.getPlayer().getStatus() != null) {
			g.setFont(Assets.font8);
			g.drawString(battleTest.getPlayer().getStatus(), 350, 255);
		}
		if (battleTest.getOpponentStatus() != null) {
			//g.setFont(Assets.font8);
			//g.drawString(battleTest.getOpponentStatus(), 10, 400);
		}


		if (battleTest.getBattle().isOpponentAbilityTriggered() == true) {
			//System.out.println("Currently returns: True");
			g.drawImage(shake.getCurrentFrame(), 390, 20, 120, 150, null);
		} else {
			g.drawImage(Assets.feng_down[0], 390, 20, 120, 150, null);
		}

		g.setColor(Color.BLACK);
		if (!menuScreen) {
			menu[0][0] = true;
			menu[0][1] = false;
			menu[1][0] = false;
			menu[1][1] = false;
			if (!characterSelectionScreen) {
				x = 0;
				y = 0;
			}
		}
		if (!characterSelectionScreen) {
			characterSelection[0][0] = true;
			characterSelection[0][1] = false;
			characterSelection[1][0] = false;
			characterSelection[1][1] = false;
			characterSelection[2][0] = false;
			characterSelection[2][1] = false;
			if (!menuScreen) {
				x = 0;
				y = 0;
			}
		}
		if (menuScreen) {
			if (y == 0) {
				if (x == 0) {
					g.fillRect(22, 312, 5, 5);
				} else if (x == 1) {
					g.fillRect(290, 312, 5, 5);
				}
			} else if (y == 1) {
				if (x == 0) {
					g.fillRect(22, 362, 5, 5);
				} else if (x == 1) {
					g.fillRect(290, 362, 5, 5);
				}
			}
		}
		if (!characterSelectionScreen) {
			characterSelection[0][0] = true;
			characterSelection[0][1] = false;
			characterSelection[1][0] = false;
			characterSelection[1][1] = false;
			characterSelection[2][0] = false;
			characterSelection[2][1] = false;
		}
		if (battleTest.getTextArrayList().size() > 0) {
			textLoading = true;
			//g.setFont(new Font("Arial", Font.PLAIN, 20));
			g.setFont(Assets.font16);
			if (count/4 < battleTest.getTextArrayList().get(0).length()) {
				if (battleTest.getTextArrayList().get(0).length() > 25) {
					g.setFont(Assets.font12);
				}
				g.drawString(battleTest.getTextArrayList().get(0).substring(0, count/4), 25, 320);
			} else {
				if (battleTest.getTextArrayList().get(0).length() > 25) {
					g.setFont(Assets.font12);
				}
				g.drawString(battleTest.getTextArrayList().get(0), 25, 320);
			}
		}
		if (battleTest.getTextArrayList().size() > 0) {
			if (count/4 > battleTest.getTextArrayList().get(0).length() + 100 ) {
				battleTest.getTextArrayList().remove(0);
				count = 0;
			}
		}
		if (battleTest.getTextArrayList().size() == 0) {
			textLoading = false;
			count = 0;
			if (!battleTest.getSelectionStrings(0).equals("null")) {
				menuScreen = true;
				g.setFont(Assets.font12);
				if (battleTest.getSelectionStrings(0).length() > 20) {
					g.setFont(Assets.font10);
				} else {
					g.setFont(Assets.font12);
				}
				g.drawString(battleTest.getSelectionStrings(0), 30, 320);
				if (battleTest.getSelectionStrings(1).length() > 20) {
					g.setFont(Assets.font10);
				} else {
					g.setFont(Assets.font12);
				}
				g.drawString(battleTest.getSelectionStrings(1), 300, 320);
				if (battleTest.getSelectionStrings(2).length() > 20) {
					g.setFont(Assets.font10);
				} else {
					g.setFont(Assets.font12);
				}
				g.drawString(battleTest.getSelectionStrings(2), 30, 370);
				if (battleTest.getSelectionStrings(3).length() > 20) {
					g.setFont(Assets.font10);
				} else {
					g.setFont(Assets.font12);
				}
				g.drawString(battleTest.getSelectionStrings(3), 300, 370);
			} else if (battleTest.isPlayerSwitchPhase()) {
				menuScreen = false;
				characterSelectionScreen = true;
				g.drawImage(Assets.characterSelect, 0, 0, null);

				try {
					g.setColor(Color.BLACK);
					g.drawString(battleTest.getSquad().getCharacter(0).getName(), 75, 50);
					g.drawString(battleTest.getSquad().getCharacter(1).getName(), 365, 50);
					g.drawString(battleTest.getSquad().getCharacter(2).getName(), 75, 175);
					g.drawString(battleTest.getSquad().getCharacter(3).getName(), 365, 175);
					g.drawString(battleTest.getSquad().getCharacter(4).getName(), 75, 300);
					g.drawString(battleTest.getSquad().getCharacter(5).getName(), 365, 300);
				} catch (NullPointerException e) {}
				try {
					g.drawImage(Assets.feng_down[0], 35, 35, null);
					g.drawImage(Assets.feng_down[0], 325, 35, null);
					g.drawImage(Assets.feng_down[0], 35, 160, null);
					g.drawImage(Assets.feng_down[0], 325, 160, null);
					g.drawImage(Assets.feng_down[0], 35, 285, null);
					g.drawImage(Assets.feng_down[0], 325, 285, null);
				} catch (NullPointerException e) {};
				
				g.setColor(new Color(150, 150, 150));
				Graphics2D g2 = (Graphics2D) g;
				g2.setStroke(new BasicStroke(5F));
				
				if (y == 0) {
					if (x == 0) {
						g2.drawRect(20, 25, 270, 100);
					} else if (x == 1) {
						g2.drawRect(310, 25, 270, 100);
					}
				} else if (y == 1) {
					if (x == 0) {
						g2.drawRect(20, 150, 270, 100);
					} else if (x == 1) {
						g2.drawRect(310, 150, 270, 100);
					}
				} else if (y == 2) {
					if (x == 0) {
						g2.drawRect(20, 275, 270, 100);
					} else if (x == 1) {
						g2.drawRect(310, 275, 270, 100);
					}
				}
			} else if (battleTest.isInventoryChoicePhase()) {
				//draw inventory
			}
		}
	}

	public BattleRunner getBattleTest() {
		return battleTest;
	}
}
