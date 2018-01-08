package battle;

<<<<<<< HEAD
import java.awt.Color;
import java.awt.Graphics;

//Author @Feng
=======
//Author @Feng and now Yash + Sihan
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
/* Battle classes are an object itself
 * Runs a loop in battle itself (To be added)
 * Need to add switching, inventory, fleeing, and move selection
 */

import java.util.InputMismatchException; //Prevents people like Samyar from intentionally throwing in bad inputs. Will be replaced.

import game.Handler;
import java.util.Scanner; //Will be replaced

class Battle /*extends Interaction*/ {
  //Objects that need to be saved here
  private PlayableCharacter player; //Since it is an object, when the values of the objects are changed, they stay changed
  private NonPlayableCharacter opponent; //Same for this one
  private Inventory playerInventory; //Interactions in the inventory during the battle are used here
  private Squad squad; //Used for switching out characters

  //Player Stats. This only exist in the battle. This is for changes during a battle that do not affect future battles.
  //Also, it is easier to save the variables here because it is easier to retrieve them

  //Player variables
  //Player integer stats
  private int playerHealth;
  private int playerCurrentHealth;
  private int playerAttack;
  private int playerIntelligence;
  private int playerDefence;
  private int playerSpeed;

  //Player String variables
  private String playerName; //Names are for display purposes when GUI is implemented
  private String playerType; //For multiplier + stab calculations
  private String playerStatus; //Has one status to prevent handling too many statuses
  private String playerAbility; //Affects the battle through different ways

  //Battle dependent number values
  private int playerStatBoost; //Sets a maximum as to how much all stats of a character can be boosted (up to 4 times)
  private int playerStatusTurns; //The amount of turns that a pokemon has a set status
  private double playerProtectChance; //Protect chance goes down every consecutive use
  private double playerFleeChance;

  //Battle dependent player boolean variables
  private boolean playerProtected; //Determines if the player has shielded a move
  private boolean playerFainted;
  private boolean playerAbilityTriggered; //boolean for abilities that can only be triggered once

  //Player Item variables
  private StatItem playerHeldItem;
  private StatItem playerHatItem;
  private StatItem playerShirtItem;
  private StatItem playerPantsItem;
  private StatItem playerShoesItem;

  //NPC integer stats
  private int opponentHealth;
  private int opponentCurrentHealth;
  private int opponentAttack;
  private int opponentIntelligence;
  private int opponentDefence;
  private int opponentSpeed;

  //NPC String variables
  private String opponentName; //Names are for display purposes when GUI is implemented
  private String opponentType;
  private String opponentStatus;
  private String opponentAbility;

  //NPC battle dependent number variables
  private int opponentStatBoost;
  private int opponentStatusTurns;
  private double opponentProtectChance;
  private double opponentFleeChance;

  //NPC battle dependent boolean variables
  private boolean opponentProtected;
  private boolean opponentFainted;
  private boolean opponentAbilityTriggered;

  //NPC Item variable
  private StatItem opponentHeldItem;

  //Battling variables
  private int partySize; //Size of the player party
  private int numberOfFaintedStudents; //Once this number is equal to the party size, the game is over
  private boolean battleEnd; //Exits the game loop once over
  private boolean playerLoses;
  private boolean opponentLoses;
  private boolean playerFled;
  private boolean opponenetFled;
  private String effectivenessText;
  private int battleTurns;

  // BIGGO STRING
  private String outputText;

  private Handler handler;


  Battle (PlayableCharacter player, NonPlayableCharacter opponent, Squad squad, Inventory inventory, Handler handler) {
<<<<<<< HEAD
	  
    //Constructor that requires some math
    this.player = player; //Saves the player
    this.opponent = opponent; //Saves the opponent
=======
    this.player = player;
    this.opponent = opponent;
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
    this.playerInventory = inventory;
    this.squad = squad;
    this.handler = handler;
    
    /*
    The reason why I save the variables here is because the stats in battles are reset once the battle ends
    Therefore, it would be better to just keep them in the class itself
    */
    
    //Integer stats
    this.playerHealth = player.getInitialHealth();
    this.playerCurrentHealth = player.getCurrentHealth();
    this.playerAttack = player.getAttack();
    this.playerIntelligence = player.getIntelligence();
    this.playerDefence = player.getDefence();
    this.playerSpeed = player.getSpeed();
    
    //Strings
    this.playerName = player.getName();
    this.playerType = player.getType();
    this.playerStatus = player.getStatus();
    this.playerAbility = player.getAbility();
    
    //Battle number variables
    this.playerStatBoost = 0;
    this.playerStatusTurns = 0;
    this.playerProtectChance = 1.00;
    this.playerFleeChance = 0.25;
    
    //Battle boolean variables
    this.playerProtected = false;
    this.playerFainted = player.isFainted();
    this.playerAbilityTriggered = false;
    
    //Player items
    this.playerHeldItem = player.getHeldItem();
    this.playerHatItem = player.getHatItem();
    this.playerShirtItem = player.getShirtItem();
    this.playerPantsItem = player.getPantsItem();
    this.playerShoesItem = player.getShoesItem();

    //Ints
    this.opponentHealth = opponent.getInitialHealth();
    this.opponentCurrentHealth = opponent.getCurrentHealth();
    this.opponentAttack = opponent.getAttack();
    this.opponentIntelligence = opponent.getIntelligence();
    this.opponentDefence = opponent.getDefence();
    this.opponentSpeed = opponent.getSpeed();
    
    //Strings
    this.opponentName = opponent.getName();
    this.opponentType = opponent.getType();
    this.opponentStatus = null;
    this.opponentAbility = opponent.getAbility();
    
    //Battle number variables
    this.opponentStatBoost = 0;
    this.opponentStatusTurns = 0;
    //this.opponentFleeChance = opponent.getFleeChance();
    this.opponentProtectChance = 1.00;
    
    //Battle boolean variables
    this.opponentProtected = false;
    this.opponentFainted = false;
    this.opponentAbilityTriggered = false;
    
    //Item
    this.opponentHeldItem = opponent.getHeldItem();

    //Battle variables
    this.partySize = squad.getSize();
    this.numberOfFaintedStudents = squad.getNumberOfFaintedStudents();
    battleEnd = false;
    playerLoses = false;
    opponentLoses = false;
    playerFled = false;
    this.battleTurns = 1;

    this.handler = handler;
  }

  private void changeCharacter(PlayableCharacter player) {
    this.player = player; //Updates the player
    //For switching out students
    //Integer stats
    this.playerHealth = player.getInitialHealth();
    this.playerCurrentHealth = player.getCurrentHealth();
    this.playerAttack = player.getAttack();
    this.playerIntelligence = player.getIntelligence();
    this.playerDefence = player.getDefence();
    this.playerSpeed = player.getSpeed();
    //Strings
    this.playerName = player.getName();
    this.playerType = player.getType();
    this.playerStatus = player.getStatus();
    this.playerAbility = player.getAbility();
    //Battle number variables
    this.playerStatBoost = 0;
    this.playerStatusTurns = 0;
    this.playerProtectChance = 1.00;
    this.playerFleeChance = 0.25;
    //Battle boolean variables
    this.playerProtected = false;
    this.playerFainted = player.isFainted();
    this.playerAbilityTriggered = false;
    //Player items
    this.playerHeldItem = player.getHeldItem();
    this.playerHatItem = player.getHatItem();
    this.playerShirtItem = player.getShirtItem();
    this.playerPantsItem = player.getPantsItem();
    this.playerShoesItem = player.getShoesItem();
    //Code for switch in animation goes here
  }

  //Fix this method
<<<<<<< HEAD
  private int drawX, drawY;
  
  public void runBattle(Graphics g) {
	  drawX = 20; 
	  drawY = 20;
    g.drawString("Turn number " + battleTurns, drawX, drawY);
=======
  public void runBattle() {
    //Turn number output should be its own string
    System.out.println("Turn number " + battleTurns);

    //Protect, status and ability handling
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
    if (playerProtected) {
      playerProtectChance /= 2;
      playerProtected = false;
    }
    if (opponentProtected) {
      opponentProtectChance /= 2;
      opponentProtected = false;
    }
    if (playerStatus != null) {
      if (playerStatus.equals("Sleep")) {
        attemptWakeUp(player, g);
      }
    }
    if (opponentStatus != null) {
      if (opponentStatus.equals("Sleep")) {
        attemptWakeUp(opponent, g);
      }
    }
    if (!playerAbilityTriggered && opponentStatBoost > -5) {
      if (playerAbility.equals("Demoralize")) {
        opponentIntelligence /= 2;
        opponentStatBoost--;
        g.drawString(opponentName + " is demoralized. Their intelligence fell!", drawX, drawY);
        playerAbilityTriggered = true;
      }
    }
    if (!opponentAbilityTriggered && playerStatBoost > -5) {
      if (opponentAbility.equals("Demoralize")) {
        playerIntelligence /= 2;
        playerStatBoost--;
        g.drawString(playerName + " is demoralized. Their intelligence fell!", drawX, drawY);
        opponentAbilityTriggered = true;
      }
    }
    if (playerAbility.equals("Speed Boost") && playerStatBoost < 5) {
      playerSpeed *= 2;
      playerStatBoost++;
      g.drawString(playerName + "'s Speed Boost! Their speed increased!", drawX, drawY);
    }
    if (opponentAbility.equals("Speed Boost") && opponentStatBoost < 5) {
      opponentSpeed *= 2;
      opponentStatBoost++;
      g.drawString(opponentName + "'s Speed Boost! Their speed increased!", drawX, drawY);
    }
<<<<<<< HEAD
    Scanner input = new Scanner(System.in);
=======


    //Scanner input = new Scanner(System.in);
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
    //KeyBoardListener keyBoardListener = new KeyBoardListener();
    //Code below would probably have to be put in another method or loops
    int answer;
    boolean exitLoop = false;
    do {
<<<<<<< HEAD
    	g.setColor(Color.black);
    	g.fillRect(0, 0, 200, 200);
      g.drawString(playerName + " " + playerCurrentHealth + "/" + playerHealth, drawX, drawY);
      g.drawString(opponentName + " " + opponentCurrentHealth + "/" + opponentHealth, drawX, drawY);
      g.drawString("What would you like to do", drawX, drawY);
      g.drawString("Fight (1)", drawX, drawY);
      g.drawString("Inventory (2)", drawX, drawY);
      g.drawString("Squad (3)", drawX, drawY);
      g.drawString("Run (4)", drawX, drawY);
      System.out.println("boi");
=======
      //Displays the health of both opponents. This could be a string output too
      System.out.println(playerName + " " + playerCurrentHealth + "/" + playerHealth);
      System.out.println(opponentName + " " + opponentCurrentHealth + "/" + opponentHealth);
      //Separate method for menu selection
      System.out.println("What would you like to do");
      System.out.println("Fight (1)");
      System.out.println("Inventory (2)");
      System.out.println("Squad (3)");
      System.out.println("Run (4)");

      //Takes user input
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
      do {
        try {
          answer = input.nextInt();
          if (answer != -1) {
          }
        } catch (InputMismatchException e) {
          //Forces the loop to run again
          answer = -1;
        }
      } while (answer < 1 || answer > 4);
      if (answer == 1) {
    	  System.out.println("detected");
        g.drawString("What move would you like to use", drawX, drawY);
        //Display the moves
        for (int i = 0; i < 4; i++) {
          g.drawString(player.getMove(i).getName() + " " + player.getPowerPoints(i) + "/" + player.getMove(i).getMaxPowerPoints() + " (" + (i + 1) + ")", drawX, drawY);
        }
        do {
          try {
            answer = input.nextInt();
          } catch (InputMismatchException e) {
            answer = -1;
          }
        } while (answer < 1 || answer > 4);
        int opponentMove = determineOpponentMove();
        int moveFirst = determineOrder(player.getMove(answer - 1), opponent.getMove(opponentMove));
        player.setPowerPoints(answer - 1, -1);

        if (moveFirst == -1) {
          if (playerStatus != null) {
            if (playerStatus.equals("Sleep")) {
              g.drawString(playerName + " is asleep!", drawX, drawY);
            } else if (Math.random() < 0.25 && playerStatus.equals("Stun")){
              g.drawString(playerName + " is stunned!", drawX, drawY);
            } else {
              //Player moves if it is not stunned or asleep
              g.drawString(playerName + " used " + player.getMove(answer - 1).getName(), drawX, drawY);
              determineAttackType(player.getMove(answer - 1), player, g);
            }
          } else {
            g.drawString(playerName + " used " + player.getMove(answer - 1).getName(), drawX, drawY);
            determineAttackType(player.getMove(answer - 1), player, g);
            //Protecting will be handled in the move methods
          }
          if (opponentCurrentHealth > 0) {
            //Can't go if the opponent is dead
            if (opponentStatus != null) {
              if (opponentStatus.equals("Sleep")) {
                g.drawString(opponentName + " is asleep!", drawX, drawY);
              } else if (Math.random() < 0.25 && opponentStatus.equals("Stun")) {
                g.drawString(opponentName + " is stunned!", drawX, drawY);
              } else {
                g.drawString(opponentName + " used " + opponent.getMove(opponentMove).getName(), drawX, drawY);
                determineAttackType(opponent.getMove(opponentMove), opponent, g);
              }
            } else {
              g.drawString(opponentName + " used " + opponent.getMove(opponentMove).getName(), drawX, drawY);
              determineAttackType(opponent.getMove(opponentMove), opponent, g);
            }
          }
        } else if (moveFirst == 1) {
          if (opponentStatus != null) {
            if (opponentStatus.equals("Sleep")) {
              g.drawString(opponentName + " is asleep!", drawX, drawY);
            } else if (Math.random() < 0.25 && opponentStatus.equals("Stun")) {
              g.drawString(opponentName + " is stunned!", drawX, drawY);
            } else {
              g.drawString(opponentName + " used " + opponent.getMove(opponentMove).getName(), drawX, drawY);
              determineAttackType(opponent.getMove(opponentMove), opponent, g);
            }
          } else {
            g.drawString(opponentName + " used " + opponent.getMove(opponentMove).getName(), drawX, drawY);
            determineAttackType(opponent.getMove(opponentMove), opponent, g);
          }
          if (playerStatus != null) {
            if (playerStatus.equals("Sleep")) {
              g.drawString(playerName + " is asleep!", drawX, drawY);
            } else if (Math.random() < 0.25 && playerStatus.equals("Stun")){
              g.drawString(playerName + " is stunned!", drawX, drawY);
            } else {
              //Player moves if it is not stunned or asleep
              g.drawString(playerName + " used " + player.getMove(answer - 1).getName(), drawX, drawY);
              determineAttackType(player.getMove(answer - 1), player, g);
            }
          } else {
            g.drawString(playerName + " used " + player.getMove(answer - 1).getName(), drawX, drawY);
            determineAttackType(player.getMove(answer - 1), player, g);
            //Protecting will be handled in the move methods
          }
        }
        exitLoop = true;
      } else if (answer == 2) {
        g.drawString("Inventory items", drawX, drawY);
        playerInventory.displayItems();
        g.drawString("Would you like to use an item (1/2)", drawX, drawY);
        do {
          try {
            answer = input.nextInt();
          } catch (InputMismatchException e) {
            answer = -1;
          }
        } while (answer < 1 || answer > 2);
        if (answer == 1) {
          boolean itemUsed = false;
          do {
            do {
              try {
                answer = input.nextInt();
              } catch (InputMismatchException e) {
                answer = -1;
              }
            } while (answer < 1 || answer > playerInventory.getInventorySize());
            Item item = playerInventory.getItem(answer);
            if (item instanceof CaptureItem) {
              itemUsed = true;
              //Code to capture the opponent (another method please)
            } else if (item instanceof HealItem) {
              itemUsed = true;
              if(((HealItem) item).getType().equals("HP")){
                  HP(player, (HealItem) item);
              }else if(((HealItem) item).getType().equals("PP")){
                  //yet to create method for this
              } else if(((HealItem) item).getType().equals("Sleep") || ((HealItem) item).getType().equals("Burn") || ((HealItem) item).getType().equals("Poison") || ((HealItem) item).getType().equals("Stun")){
                cureStatus(player, (HealItem)item);
              } else if(((HealItem) item).getType().equals("Half revive") || ((HealItem) item).getType().equals("Full revive")){
                revive(player, (HealItem)item);
              }
              //ALL IN DIFFERENT METHODS (pp and hp can be same method)
            } else if (item instanceof StatItem) {
<<<<<<< HEAD
              g.drawString("You can use that here!", drawX, drawY);
            }
=======
              System.out.println("You can use that here!");
              useStatItem(player, (StatItem)item);
              }
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
            if (itemUsed) {
              playerInventory.useItem(playerInventory.getItemName(answer));
            }
          } while (!itemUsed);
        }
      } else if (answer == 3) {
        //Check out the squad
        squad.displaySquad();
        g.drawString("", drawX, drawY);
        g.drawString("Would you like to switch in a squad member (1/2)", drawX, drawY);
        do {
          try {
            answer = input.nextInt();
          } catch (InputMismatchException e) {
            answer = 20;
          }
        } while (answer < 1 || answer > 2);
        if (answer == 1) {
          g.drawString("Who would you like to switch in", drawX, drawY);
          boolean pickLoop = false;
          do {
            do {
              try {
                answer = input.nextInt();
              } catch (InputMismatchException e) {
                answer = -1;
              }
            } while (answer < 1 || answer > squad.getSize());

            if (squad.getCharacter(answer - 1).getCurrentHealth() > 0) {
              changeCharacter(squad.getCharacter(answer - 1));
              pickLoop = true;
            } else {
              g.drawString("That person is dead", drawX, drawY);
            }
          } while (!pickLoop);
          int opponentMove = determineOpponentMove();
          g.drawString(opponentName + " used " + opponent.getMove(opponentMove).getName(), drawX, drawY);
          determineAttackType(opponent.getMove(opponentMove), opponent, g);
          exitLoop = true;
        }
      } else if (answer == 4) {
        if (Math.random() < 0.25) {
          battleEnd = true;
          playerFled = true;
          exitLoop = true;
        }
      }
    } while (!exitLoop);
    exitLoop = false;
    if (playerCurrentHealth > 0) {
      if (playerHeldItem.equals("Starbucks Card") && playerCurrentHealth != playerHealth) {
        if (playerCurrentHealth + playerHealth*0.06 > playerHealth) {
          player.resetCurrentHealth();
          playerHealth = player.getCurrentHealth();
        } else {
          player.changeCurrentHealth((int)(playerHealth*0.06));
          playerCurrentHealth = player.getCurrentHealth();
        }
        System.out.println(playerName + " bought a Starbucks drink and recovered some health!");
      }
    }
    if (opponentCurrentHealth > 0) {
      if (opponentHeldItem.equals("Starbucks Card") && opponentCurrentHealth != opponentHealth) {
        if (opponentCurrentHealth + opponentHealth*0.06 > opponentHealth) {
          opponentCurrentHealth = opponentHealth;
        } else {
          opponentCurrentHealth += (int)(opponentHealth*0.06);
        }
        System.out.println(opponentName + " bought a Starbucks drink and recovered some health!");
      }
    }
    if (playerStatus != null && playerCurrentHealth > 0) {
      if (playerStatus.equals("Burned")) {
        burnPerson(player);
      } else if (playerStatus.equals("Poisoned")) {
        poisonPerson(player);
      }
    }
    if (opponentStatus != null && opponentCurrentHealth > 0) {
      if (opponentStatus.equals("Burned")) {
        burnPerson(opponent);
      } else if (opponentStatus.equals("Poisoned")) {
        poisonPerson(opponent);
      }
    }
    if (playerCurrentHealth == 0) {
      numberOfFaintedStudents++;
    }
    if (numberOfFaintedStudents == partySize && opponentCurrentHealth == 0) {
      g.drawString("Everyone died", drawX, drawY);
      battleEnd = true;
    } else if (numberOfFaintedStudents == partySize) {
      g.drawString("Your party died", drawX, drawY);
      battleEnd = true;
      playerLoses = true;
    } else if (opponentCurrentHealth == 0) {
      g.drawString("Good job you passed", drawX, drawY);
      battleEnd = true;
      opponentLoses = true;
    }
    if (playerCurrentHealth != 0 && playerStatus != null) {
      playerStatusTurns++;
    }
    if (opponentCurrentHealth != 0 && opponentStatus != null) {
      opponentStatusTurns++;
    }
    if (!battleEnd) {
      do {
        //Might throw this into another method
        if (playerCurrentHealth == 0) {
          g.drawString("Your student broke down", drawX, drawY);
          squad.displaySquad();
          do {
            try {
              answer = input.nextInt();
            } catch (InputMismatchException e) {
              answer = -1;
            }
          } while (answer < 1 || answer > squad.getSize());
          if (squad.getCharacter(answer - 1).getCurrentHealth() > 0) {
            changeCharacter(squad.getCharacter(answer - 1));
            exitLoop = true;
          } else {
            g.drawString("That student is dead.", drawX, drawY);
          }
        } else {
          exitLoop = true;
        }
      } while (!exitLoop);
    }
    battleTurns++;
    g.drawString("", drawX, drawY);
  }

  private int determineOrder(Move playerMove, Move opponentMove) {
    int tempPlayerSpeed = playerSpeed;
    if (opponentAbility.equals("Unaware")) {
      tempPlayerSpeed = player.getSpeed();
    }
    int tempOpponentSpeed = opponentSpeed;
    if (playerAbility.equals("Unaware")) {
      tempOpponentSpeed = opponent.getSpeed();
    }
    if (playerStatus != null) {
      if (playerStatus.equals("Stun")) {
        tempPlayerSpeed = playerSpeed / 2;
      }
    }
    if (opponentStatus != null) {
      if (opponentStatus.equals("Stun")) {
        tempOpponentSpeed = opponentSpeed / 2;
      }
    }
    //This code decides who goes first based on their speed
    //Returns an int to see who goes first. -1 is the player, 1 is the opponent
    if (playerMove.getPriority() > opponentMove.getPriority()) {
      return -1;
    } else if (playerMove.getPriority() < opponentMove.getPriority()) {
      return 1;
    } else if (tempPlayerSpeed > tempOpponentSpeed) {
      return -1;
    } else if (tempPlayerSpeed < tempOpponentSpeed) {
      return 1;
    } else {
      //In case of a tie breaker
      int decision = (int)Math.floor(Math.random()*2);
      if (decision == 0) {
        return -1;
      } else {
        return 1;
      }
    }
  }

<<<<<<< HEAD
  public void determineAttackType(Move move, Character user, Graphics g) {
=======
  private void determineAttackType(Move move, Character user) {
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
    int attacker = 0; //Needs to be initialized
    //This code determines who is attacking
    //-1 is the player, 1 is the opponent
    if (user instanceof PlayableCharacter) {
      attacker = -1;
    } else if (user instanceof NonPlayableCharacter) {
      attacker = 1;
    }
    //This code determines what type of moves method should be used
    //The move itself has to be casted into the appropriate subclass
    if (move instanceof AttackMove) {
      attackMove((AttackMove) move, attacker, g);
    } else if (move instanceof ProtectMove) {
<<<<<<< HEAD
      protectMove((ProtectMove)move, attacker, g);
=======
      protectMove(attacker);
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
    } else if (move instanceof HealthMove) {
      healthMove((HealthMove) move, attacker, g);
    } else if (move instanceof StatChangeMove) {
      if (((StatChangeMove)move).getTarget().equals("Self")) {
        //Whenever the target is the user, the attacker uses the move on itself and therefore the attacker would be "technically" the other person
        //Because the move itself does not affect the attacker, this is okay
        statChangeMove((StatChangeMove)move, -attacker, g);
      } else {
        //The target is other person
        statChangeMove((StatChangeMove) move, attacker, g);
      }
    } else if (move instanceof StatusMove) {
      if (((StatusMove)move).getTarget().equals("Self")) {
        //Whenever the target is the user, the attacker uses the move on itself and therefore the attacker would be "technically" the other person
        //Because the move itself does not affect the attacker, this is okay
        statusMove((StatusMove) move, -attacker, g);
      } else {
        statusMove((StatusMove)move, attacker, g);
      }
    } else if (move instanceof  SleepTalkMove) {
      if (attacker == -1) {
        if (playerStatus != null) {
          if (playerStatus.equals("Sleep")) {
            boolean attackTrue = false;
            do {
              int randomMove = (int) Math.random()*4;
              if (!(player.getMove(randomMove) instanceof SleepTalkMove)) {
                Move moveUsed = player.getMove(randomMove);
                attackTrue = true;
                determineAttackType(moveUsed, player);
              }
            } while (!attackTrue);
          } else {
            System.out.println("The move failed!");
          }
        } else {
          System.out.println("The move failed!");
        }
      } else if (attacker == 1) {
        if (opponentStatus != null) {
          if (opponentStatus.equals("Sleep")) {
            boolean attackTrue = false;
            do {
              int randomMove = (int) Math.random()*4;
              if (!(opponent.getMove(randomMove) instanceof SleepTalkMove)) {
                Move moveUsed = opponent.getMove(randomMove);
                attackTrue = true;
                determineAttackType(moveUsed, opponent);
              }
            } while (!attackTrue);
          } else {
            System.out.println("The move failed!");
          }
        } else {
          System.out.println("The move failed!");
        }
      }
    }
  }

<<<<<<< HEAD
  //Finish this method
  public void attackMove (AttackMove move, int attacker, Graphics g) {
=======
  private void attackMove(AttackMove move, int attacker) {
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
    int attackerStatUsed = 0; //Determines whether attack or intelligence is used
    int defence = 0; //Determines whose defence to use
    double multiplier; //Multiplier for typing, status effects and STAB
    //This if statement determines whose stats to used to calculate damage
    boolean attackTriggered = false;
    if (attacker == -1) {
      //If attacker is -1, then the player is attacking. If it is 1, the opponent is attacking
      if (move.getAttackType().equals("Attack")) {
        attackerStatUsed = playerAttack;
        if (opponentAbility.equals("Unaware")) {
          attackerStatUsed = player.getAttack();
        }
      } else if (move.getAttackType().equals("Intelligence")) {
        attackerStatUsed = playerIntelligence;
        if (opponentAbility.equals("Unaware")) {
          attackerStatUsed = player.getIntelligence();
        }
      }
      defence = opponentDefence;
      if (playerAbility.equals("Unaware")) {
        defence = opponent.getDefence();
      }
    } else if (attacker == 1) {
      if (move.getAttackType().equals("Attack")) {
        attackerStatUsed = opponentAttack;
        if (playerAbility.equals("Unaware")) {
          attackerStatUsed = player.getAttack();
        }
      } else if (move.getAttackType().equals("Intelligence")) {
        attackerStatUsed = opponentIntelligence;
        if (playerAbility.equals("Unaware")) {
          attackerStatUsed = player.getIntelligence();
        }
      }
      defence = playerDefence;
      if (opponentAbility.equals("Unaware")) {
        defence = player.getDefence();
      }
    }
    //This multiplier method determines the multiplier from STAB, statuses, and type advantages
    effectivenessText = null;
    multiplier = determineMultiplier(attacker, move);

    //Calculating the damage of the attack move
    int damageDealt;
    damageDealt = (int)(Math.ceil((move.getPower() * (attackerStatUsed/defence+1))/10) * multiplier);

    double determineHit = Math.random();
    //Factors in the clown ability
    //I should probably throw this into another method after we add a lot of abilities
<<<<<<< HEAD
    if (playerAbility.equals("Clown") && attacker == 1) {
      if (Math.random() < 0.25) {
        damageDealt = damageDealt/2;
        g.drawString(playerName + " is clowning around.", drawX, drawY);
      }
    } else if (opponentAbility.equals("Clown") && attacker == -1) {
      if (Math.random() < 0.25) {
        damageDealt = damageDealt/2;
        g.drawString(opponentName + " is clowning around.", drawX, drawY);
=======
    if (attacker == 1) {
      if (playerAbility.equals("Clown")) {
        if (Math.random() < 0.25) {
          damageDealt = damageDealt / 2;
          System.out.println(playerName + " is clowning around.");
        }
      } else if (playerAbility.equals("Avoidant")) {
        determineHit *= 4;
      }
      if (opponentAbility.equals("Extreme Luck")) {
        determineHit /= 2;
      }
    } else if (attacker == -1) {
      if (opponentAbility.equals("Clown")) {
        if (Math.random() < 0.25) {
          damageDealt = damageDealt / 2;
          System.out.println(opponentName + " is clowning around.");
        }
      } else if (opponentAbility.equals("Avoidant")) {
        determineHit *= 4;
      }
      if (opponentAbility.equals("Extreme Luck")) {
        determineHit /= 2;
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
      }
    }

    //Add code to have more stuff when a student faints
    if (determineHit < move.getHitChance()) {
      //Determines whether the attack hits or not
      if (attacker == -1) {
        if (!opponentProtected) {
          attackTriggered = true;
          if (effectivenessText != null) {
            g.drawString(effectivenessText, drawX, drawY);
          }
          if (opponentCurrentHealth - damageDealt < 0) {
            opponent.faintCharacter();
            opponentCurrentHealth = opponent.getCurrentHealth();
          } else {
            opponent.changeCurrentHealth(-damageDealt);
            opponentCurrentHealth = opponent.getCurrentHealth();
            if (opponentCurrentHealth < opponentHealth / 4 && !opponentAbilityTriggered) {
              switch (opponentAbility) {
                case "Persistent":
<<<<<<< HEAD
                  opponentDefence = opponentDefence * 2;
                  g.drawString("The opponent is persistent!", drawX, drawY);
                  g.drawString("Opponent defence rose sharply!", drawX, drawY);
                  opponentAbilityTriggered = true;
                  break;
                case "Distressed":
                  opponentIntelligence = opponentIntelligence * 2;
                  g.drawString("The opponent is distressed!", drawX, drawY);
                  g.drawString("Player intelligence rose sharply!", drawX, drawY);
=======
                  opponentDefence *= 2;
                  System.out.println(opponentName + " is persistent!");
                  System.out.println(opponentName + "'s defence rose sharply!");
                  opponentAbilityTriggered = true;
                  break;
                case "Distressed":
                  opponentIntelligence *= 2;
                  System.out.println(opponentName + " is distressed!");
                  System.out.println(opponentName + "'s intelligence rose sharply!");
                  opponentAbilityTriggered = true;
                  break;
                case "Protective":
                  opponentSpeed *= 2;
                  opponentAttack *= 2;
                  System.out.println(opponentName + " is protective!");
                  System.out.println(opponentName + "'s speed rose sharply!");
                  System.out.println(opponentName + "'s attack rose sharply!");
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
                  opponentAbilityTriggered = true;
                  break;
              }
            }
          }
        } else {
          g.drawString("The opponent protected!", drawX, drawY);
        }
      } else if (attacker == 1) {
        if (!playerProtected) {
          attackTriggered = true;
          if (effectivenessText != null) {
            g.drawString(effectivenessText, drawX, drawY);
          }
          if (playerCurrentHealth - damageDealt < 0) {
            player.faintCharacter();
            playerCurrentHealth = player.getCurrentHealth();
          } else {
            player.changeCurrentHealth(-damageDealt);
            playerCurrentHealth = player.getCurrentHealth();
            if (playerCurrentHealth < playerHealth / 4 && !playerAbilityTriggered) {
              switch (playerAbility) {
                case "Persistent":
<<<<<<< HEAD
                  playerDefence = playerDefence * 2;
                  g.drawString("The player is persistent!", drawX, drawY);
                  g.drawString("Player defence rose sharply!", drawX, drawY);
                  playerAbilityTriggered = true;
                  break;
                case "Distressed":
                  playerIntelligence = playerIntelligence * 2;
                  g.drawString("The player is distressed!", drawX, drawY);
                  g.drawString("Player intelligence rose sharply!", drawX, drawY);
=======
                  playerDefence *= 2;
                  System.out.println(playerName + " is persistent!");
                  System.out.println(playerName + "'s defence rose sharply!");
                  playerAbilityTriggered = true;
                  break;
                case "Distressed":
                  playerIntelligence *= 2;
                  System.out.println(playerName + " is distressed!");
                  System.out.println(playerName + "'s intelligence rose sharply!");
                  playerAbilityTriggered = true;
                  break;
                case "Protective":
                  playerSpeed *= 2;
                  playerAttack *= 2;
                  System.out.println(playerName + " is protective!");
                  System.out.println(playerName + "'s speed rose sharply!");
                  System.out.println(playerName + "'s attack rose sharply!");
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
                  playerAbilityTriggered = true;
                  break;
              }
            }
          }
        }
      } else {
        g.drawString("The player protected!", drawX, drawY);
      }
    } else {
      g.drawString("The move missed!", drawX, drawY);
    }

    double determineAdditionalHit = Math.random();
    if (attacker == 1) {
      if (playerAbility.equals("Avoidant")) {
        determineAdditionalHit *= 4;
      }
      if (opponentAbility.equals("Extreme Luck")) {
        determineAdditionalHit /= 2;
      }
    } else if (attacker == -1) {
      if (opponentAbility.equals("Avoidant")) {
        determineAdditionalHit *= 4;
      }
      if (opponentAbility.equals("Extreme Luck")) {
        determineAdditionalHit /= 2;
      }
    }
    //Additional effect of moves
    if (move.getAdditionalEffect() != null && attackTriggered) {
      Move additionalEffect = move.getAdditionalEffect();
      if (determineAdditionalHit < additionalEffect.getHitChance()) {
        if (additionalEffect instanceof HealthMove) {
          healthMove((HealthMove) additionalEffect, attacker, g);
        } else if (additionalEffect instanceof StatChangeMove) {
          if (((StatChangeMove) additionalEffect).getTarget().equals("Self")) {
            statChangeMove((StatChangeMove) additionalEffect, -attacker * 2, g);
          } else {
            statChangeMove((StatChangeMove) additionalEffect, attacker * 2, g);
          }
        } else if (additionalEffect instanceof StatusMove) {
          if (((StatusMove) additionalEffect).getTarget().equals("Self")) {
            statusMove((StatusMove) additionalEffect, -attacker * 2, g);
          } else {
            statusMove((StatusMove) additionalEffect, attacker * 2, g);
          }
        }
      }
    }
  }

<<<<<<< HEAD
  public void protectMove (ProtectMove move, int attacker, Graphics g) {
=======
  private void protectMove(int attacker) {
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
    if (attacker == -1) {
      if (Math.random() < playerProtectChance) {
        playerProtected = true;
      } else {
        g.drawString("The protect failed", drawX, drawY);
      }
    } else if (attacker == 1) {
      if (Math.random() < opponentProtectChance) {
        opponentProtected = true;
      } else {
        g.drawString("The protect failed", drawX, drawY);
      }
    }
  }

<<<<<<< HEAD
  public void statChangeMove(StatChangeMove move, int attacker, Graphics g) {
=======
  private void statChangeMove(StatChangeMove move, int attacker) {
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
    //This code is for moves that only change the stats of the opposing person
    boolean attackTriggered = false;
    double determineHit = Math.random();
    if (attacker == 1) {
      if (playerAbility.equals("Avoidant")) {
        determineHit *= 4;
      }
      if (opponentAbility.equals("Extreme Luck")) {
        determineHit /= 2;
      }
    } else if (attacker == -1) {
      if (opponentAbility.equals("Avoidant")) {
        determineHit *= 4;
      }
      if (opponentAbility.equals("Extreme Luck")) {
        determineHit /= 2;
      }
    }
    if (move.getTarget().equals("Self")) {
      determineHit = -1;
    }

    if (determineHit < move.getHitChance()) {
      //Determines whether or not the attack lands
      if (attacker < 0 && !opponentProtected) {
        attackTriggered = true;
        if (opponentStatBoost>-5 && move.getTarget().equals("Opponent")) {
          if (move.getStatType().equals("Attack")) {
<<<<<<< HEAD
            opponentAttack = opponentAttack / move.getMultiplier();
            g.drawString(opponentName + "'s attack fell!", drawX, drawY);
          } else if (move.getStatType().equals("Intelligence")) {
            opponentIntelligence = opponentIntelligence / move.getMultiplier();
            g.drawString(opponentName + "'s intelligence fell!", drawX, drawY);
          } else if (move.getStatType().equals("Defence")) {
            opponentDefence = opponentDefence / move.getMultiplier();
            g.drawString(opponentName + "'s defence fell!", drawX, drawY);
          } else if (move.getStatType().equals("Speed")) {
            opponentSpeed = opponentSpeed / move.getMultiplier();
            g.drawString(opponentName + "'s speed fell!", drawX, drawY);
=======
            opponentAttack /= move.getMultiplier();
            System.out.println(opponentName + "'s attack fell!");
          } else if (move.getStatType().equals("Intelligence")) {
            opponentIntelligence /= move.getMultiplier();
            System.out.println(opponentName + "'s intelligence fell!");
          } else if (move.getStatType().equals("Defence")) {
            opponentDefence /= move.getMultiplier();
            System.out.println(opponentName + "'s defence fell!");
          } else if (move.getStatType().equals("Speed")) {
            opponentSpeed /= move.getMultiplier();
            System.out.println(opponentName + "'s speed fell!");
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
          }
          opponentStatBoost--;
        } else if (opponentStatBoost<5 && move.getTarget().equals("Self")) {
          if (move.getStatType().equals("Attack")) {
<<<<<<< HEAD
            opponentAttack = opponentAttack * move.getMultiplier();
            g.drawString(opponentName + "'s attack increased!", drawX, drawY);
          } else if (move.getStatType().equals("Intelligence")) {
            opponentIntelligence = opponentIntelligence * move.getMultiplier();
            g.drawString(opponentName + "'s intelligence increased!", drawX, drawY);
          } else if (move.getStatType().equals("Defence")) {
            opponentDefence = opponentDefence * move.getMultiplier();
            g.drawString(opponentName + "'s defence increased!", drawX, drawY);
          } else if (move.getStatType().equals("Speed")) {
            opponentSpeed = opponentSpeed * move.getMultiplier();
            g.drawString(opponentName + "'s speed increased!", drawX, drawY);
=======
            opponentAttack *= move.getMultiplier();
            System.out.println(opponentName + "'s attack increased!");
          } else if (move.getStatType().equals("Intelligence")) {
            opponentIntelligence *= move.getMultiplier();
            System.out.println(opponentName + "'s intelligence increased!");
          } else if (move.getStatType().equals("Defence")) {
            opponentDefence *= move.getMultiplier();
            System.out.println(opponentName + "'s defence increased!");
          } else if (move.getStatType().equals("Speed")) {
            opponentSpeed *= move.getMultiplier();
            System.out.println(opponentName + "'s speed increased!");
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
          }
          opponentStatBoost++;
        } else if (opponentProtected) {
          g.drawString(opponentName + "protected from the attack.", drawX, drawY);
        } else {
          g.drawString("The stat cannot be changed anymore!", drawX, drawY);
        }
      } else if (attacker > 0 && !playerProtected) {
        attackTriggered = true;
        if (playerStatBoost>-5 && move.getTarget().equals("Opponent")) {
          if (move.getStatType().equals("Attack")) {
<<<<<<< HEAD
            playerAttack = playerAttack / move.getMultiplier();
            g.drawString(playerName + "'s attack fell!", drawX, drawY);
          } else if (move.getStatType().equals("Intelligence")) {
            playerIntelligence = playerIntelligence / move.getMultiplier();
            g.drawString(playerName + "'s intelligence fell!", drawX, drawY);
          } else if (move.getStatType().equals("Defence")) {
            playerDefence = playerDefence / move.getMultiplier();
            g.drawString(playerName + "'s defence fell!", drawX, drawY);
          } else if (move.getStatType().equals("Speed")) {
            playerSpeed = playerSpeed / move.getMultiplier();
            g.drawString(playerName + "'s speed fell!", drawX, drawY);
=======
            playerAttack /= move.getMultiplier();
            System.out.println(playerName + "'s attack fell!");
          } else if (move.getStatType().equals("Intelligence")) {
            playerIntelligence /= move.getMultiplier();
            System.out.println(playerName + "'s intelligence fell!");
          } else if (move.getStatType().equals("Defence")) {
            playerDefence /= move.getMultiplier();
            System.out.println(playerName + "'s defence fell!");
          } else if (move.getStatType().equals("Speed")) {
            playerSpeed /= move.getMultiplier();
            System.out.println(playerName + "'s speed fell!");
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
          }
          playerStatBoost--;
        } else if (playerStatBoost<5 && move.getTarget().equals("Self")) {
          if (move.getStatType().equals("Attack")) {
<<<<<<< HEAD
            playerAttack = playerAttack * move.getMultiplier();
            g.drawString(playerName + "'s attack increased!", drawX, drawY);
          } else if (move.getStatType().equals("Intelligence")) {
            playerIntelligence = playerIntelligence * move.getMultiplier();
            g.drawString(playerName + "'s intelligence increased!", drawX, drawY);
          } else if (move.getStatType().equals("Defence")) {
            playerDefence = playerDefence * move.getMultiplier();
            g.drawString(playerName + "'s defence increased!", drawX, drawY);
          } else if (move.getStatType().equals("Speed")) {
            playerSpeed = playerSpeed * move.getMultiplier();
            g.drawString(playerName + "'s speed increased!", drawX, drawY);
=======
            playerAttack *= move.getMultiplier();
            System.out.println(playerName + "'s attack increased!");
          } else if (move.getStatType().equals("Intelligence")) {
            playerIntelligence *= move.getMultiplier();
            System.out.println(playerName + "'s intelligence increased!");
          } else if (move.getStatType().equals("Defence")) {
            playerDefence *= move.getMultiplier();
            System.out.println(playerName + "'s defence increased!");
          } else if (move.getStatType().equals("Speed")) {
            playerSpeed *= move.getMultiplier();
            System.out.println(playerName + "'s speed increased!");
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
          }
          playerStatBoost++;
        } else {
          g.drawString("The stat cannot be changed anymore!", drawX, drawY);
        }
      }
    } else {
      g.drawString("The move missed!", drawX, drawY);
    }

    double determineAdditionalHit = Math.random();
    if (attacker == 1) {
      if (playerAbility.equals("Avoidant")) {
        determineAdditionalHit *= 4;
      }
      if (opponentAbility.equals("Extreme Luck")) {
        determineAdditionalHit /= 2;
      }
    } else if (attacker == -1) {
      if (opponentAbility.equals("Avoidant")) {
        determineAdditionalHit *= 4;
      }
      if (opponentAbility.equals("Extreme Luck")) {
        determineAdditionalHit /= 2;
      }
    }

    if (move.getAdditionalEffect() != null && attackTriggered) {
      Move additionalEffect = move.getAdditionalEffect();
      if (determineAdditionalHit < additionalEffect.getHitChance()) {
        if (additionalEffect instanceof HealthMove) {
          healthMove((HealthMove) additionalEffect, attacker, g);
        } else if (additionalEffect instanceof StatChangeMove) {
          if (((StatChangeMove) additionalEffect).getTarget().equals("Self")) {
            statChangeMove((StatChangeMove) additionalEffect, -attacker * 2, g);
          } else {
            statChangeMove((StatChangeMove) additionalEffect, attacker * 2, g);
          }
        } else if (additionalEffect instanceof StatusMove) {
          if (((StatusMove) additionalEffect).getTarget().equals("Self")) {
            statusMove((StatusMove) additionalEffect, -attacker * 2, g);
          } else {
            statusMove((StatusMove) additionalEffect, attacker * 2, g);
          }
        }
      }
    }
  }

<<<<<<< HEAD
  public void healthMove(HealthMove move, int attacker, Graphics g) {
=======
  private void healthMove(HealthMove move, int attacker) {
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
    //Doesn't check hit chance because self healing is 100% successful
    if (attacker == -1) {
      if (move.getHeal() > 0) {
        if (playerCurrentHealth + move.getHeal() > playerHealth) {
          player.resetCurrentHealth();
          playerCurrentHealth = player.getCurrentHealth();
        } else {
          player.changeCurrentHealth(move.getHeal());
          playerCurrentHealth = player.getCurrentHealth();
        }
      } else if (move.getHeal() == -2) {
        if (playerCurrentHealth + playerHealth/2 > playerHealth) {
          player.resetCurrentHealth();
          playerCurrentHealth = player.getCurrentHealth();
        } else {
          player.changeCurrentHealth(playerHealth/2);
          playerCurrentHealth = player.getCurrentHealth();
        }
      } else if (move.getHeal() == -1) {
        player.resetCurrentHealth();
        playerCurrentHealth = player.getCurrentHealth();
      }
    } else if (attacker == 1) {
      if (move.getHeal() > 0) {
        if (opponentCurrentHealth + move.getHeal() > opponentHealth) {
          opponent.resetCurrentHealth();
          opponentCurrentHealth = opponent.getCurrentHealth();
        } else {
          opponent.changeCurrentHealth(move.getHeal());
          opponentCurrentHealth = opponent.getCurrentHealth();
        }
      } else if (move.getHeal() == -2) {
        if (opponentCurrentHealth + opponentHealth/2 > opponentHealth) {
          opponent.resetCurrentHealth();
          opponentCurrentHealth = opponent.getCurrentHealth();
        } else {
          opponent.changeCurrentHealth(opponentHealth/2);
          opponentCurrentHealth = opponent.getCurrentHealth();
        }
      } else if (move.getHeal() == -1) {
        opponent.resetCurrentHealth();
        opponentCurrentHealth = opponent.getCurrentHealth();
      }
    }

    double determineAddtionalHit = Math.random();
    if (attacker == 1) {
      if (opponentAbility.equals("Extreme Luck")) {
        determineAddtionalHit /= 2;
      }
    } else if (attacker == -1) {
      if (opponentAbility.equals("Extreme Luck")) {
        determineAddtionalHit /= 2;
      }
    }

    if (move.getAdditionalEffect() != null) {
      Move additionalEffect = move.getAdditionalEffect();
      if (determineAddtionalHit < additionalEffect.getHitChance()) {
        if (additionalEffect instanceof HealthMove) {
          healthMove((HealthMove) additionalEffect, attacker, g);
        } else if (additionalEffect instanceof StatChangeMove) {
          if (((StatChangeMove) additionalEffect).getTarget().equals("Self")) {
            statChangeMove((StatChangeMove) additionalEffect, -attacker * 2, g);
          } else {
            statChangeMove((StatChangeMove) additionalEffect, attacker * 2, g);
          }
        } else if (additionalEffect instanceof StatusMove) {
          if (((StatusMove) additionalEffect).getTarget().equals("Self")) {
            statusMove((StatusMove) additionalEffect, -attacker * 2, g);
          } else {
            statusMove((StatusMove) additionalEffect, attacker * 2, g);
          }
        }
      }
    }
  }

<<<<<<< HEAD
  public void statusMove(StatusMove move, int attacker, Graphics g) {
=======
  private void statusMove(StatusMove move, int attacker) {
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
    //A move that changes the status of the move.
    //In a move, the status will always come last. Status moves will not have an additional effect.
    double determineHit = Math.random();
    if (attacker == 1) {
      if (playerAbility.equals("Avoidant")) {
        determineHit *= 4;
      }
      if (opponentAbility.equals("Extreme Luck")) {
        determineHit /= 2;
      }
    } else if (attacker == -1) {
      if (opponentAbility.equals("Avoidant")) {
        determineHit *= 4;
      }
      if (opponentAbility.equals("Extreme Luck")) {
        determineHit /= 2;
      }
    }

    if (move.getTarget().equals("Self")) {
      //A move against themselves will never miss.
      determineHit = -1;
    }

    if (determineHit < move.getHitChance()) {
      //Checks hit chance
      if (attacker < 0) {
        if (!opponentProtected) {
          opponentStatus = move.getStatusEffect();
          if (move.getStatusEffect().equals("Sleep")) {
            g.drawString("The opponent fell asleep.", drawX, drawY);
          } else {
            g.drawString("The opponent was " + move.getStatusEffect() + "ed.", drawX, drawY);
          }
        } else {
          g.drawString("The opponent protected", drawX, drawY);
        }
      } else if (attacker > 0){
        if (!playerProtected) {
          player.setStatus(move.getStatusEffect());
          playerStatus = player.getStatus();
          if (move.getStatusEffect().equals("Sleep")) {
            g.drawString("The player fell asleep.", drawX, drawY);
          } else {
            g.drawString("The player was " + move.getStatusEffect() + "ed.", drawX, drawY);
          }
        } else {
          g.drawString("The player protected", drawX, drawY);
        }
      }
    } else {
      //The move misses
    }
  }

  private double determineMultiplier(int attacker, AttackMove move) {
    double multiplier = 1; //Initially starts off at 1
    //This following code determines type effectiveness
    //Math is effective against english, ineffective against science
    //Science is effective against math, ineffective against technology
    //Technology is effective against science, ineffective against English
    //English is effective against technology, ineffective against math
    //To add: status multipliers
    if (attacker == -1) {
      //Switch statement can be probably converted to a compareTo method
      switch (opponentType) {
        case "Math":
          if (move.getType().equals("Science")) {
            multiplier = multiplier * 2;
          } else if (move.getType().equals("English")) {
            multiplier = multiplier / 2;
          }
          break;
        case "Science":
          if (move.getType().equals("Technology")) {
            multiplier = multiplier * 2;
          } else if (move.getType().equals("Math")) {
            multiplier = multiplier / 2;
          }
          break;
        case "Technology":
          if (move.getType().equals("English")) {
            multiplier = multiplier * 2;
          } else if (move.getType().equals("Science")) {
            multiplier = multiplier / 2;
          }
          break;
        case "English":
          if (move.getType().equals("Math")) {
            multiplier = multiplier * 2;
          } else if (move.getType().equals("Technology")) {
            multiplier = multiplier / 2;
          }
          break;
      }
      if (multiplier > 1) {
        effectivenessText = "Super effective";
      } else if (multiplier < 1) {
        effectivenessText = "Not very effective";
      }
      if (playerType.equals(move.getType())) {
        multiplier = multiplier * 1.5; //Same Type Attack Bonus (STAB)
      }
      if (playerStatus != null) {
        if (playerStatus.equals("Burned")) {
          multiplier = multiplier * 0.5; //Burned halves damage
        }
      }
    }
    if (attacker == 1) {
      switch (playerType) {
        case "Math":
          if (move.getType().equals("Science")) {
            multiplier = multiplier * 2;
          } else if (move.getType().equals("English")) {
            multiplier = multiplier / 2;
          }
          break;
        case "Science":
          if (move.getType().equals("Technology")) {
            multiplier = multiplier * 2;
          } else if (move.getType().equals("Math")) {
            multiplier = multiplier / 2;
          }
          break;
        case "Technology":
          if (move.getType().equals("English")) {
            multiplier = multiplier * 2;
          } else if (move.getType().equals("Science")) {
            multiplier = multiplier / 2;
          }
          break;
        case "English":
          if (move.getType().equals("Math")) {
            multiplier = multiplier * 2;
          } else if (move.getType().equals("Technology")) {
            multiplier = multiplier / 2;
          }
          break;
      }
      if (multiplier > 1) {
        effectivenessText = "Super effective";
      } else if (multiplier < 1) {
        effectivenessText = "Not very effective";
      }
      if (opponentType.equals(move.getType())) {
        multiplier = multiplier * 1.5; //Same Type Attack Bonus (STAB)
      }
      if (opponentStatus != null) {
        if (opponentStatus.equals("Burned")) {
          multiplier = multiplier * 0.5; //Burn does half damage
        }
      }
    }
    return multiplier;
  }

  private void burnPerson(Character person) {
    double burn;
    if (person instanceof PlayableCharacter) {
      burn = playerHealth/12;
      if (playerCurrentHealth - (int)burn < 0) {
        player.faintCharacter();
        playerFainted = true;
      } else {
        person.changeCurrentHealth(-(int)burn);
        playerCurrentHealth = player.getCurrentHealth();
      }
    } else if (person instanceof NonPlayableCharacter) {
      burn = opponentHealth/12;
      if (opponentCurrentHealth - (int)burn < 0) {
        opponent.faintCharacter();
      } else {
        person.changeCurrentHealth(-(int)burn);
        opponentCurrentHealth = opponent.getCurrentHealth();
      }
    }
  }

  private void poisonPerson(Character person) {
    double poison;
    if (person instanceof PlayableCharacter) {
      poison = playerHealth/15 * playerStatusTurns;
      if (playerCurrentHealth - (int)poison < 0) {
        player.faintCharacter();
        playerFainted = true;
      } else {
        person.changeCurrentHealth(-(int)poison);
        playerCurrentHealth = player.getCurrentHealth();
      }
    } else if (person instanceof NonPlayableCharacter) {
      poison = opponentHealth/15 * opponentStatusTurns;
      if (opponentCurrentHealth - (int)poison < 0) {
        opponent.faintCharacter();
      } else {
        person.changeCurrentHealth(-(int)poison);
        opponentCurrentHealth = opponent.getCurrentHealth();
      }
    }
  }

<<<<<<< HEAD
  public void attemptWakeUp(Character person, Graphics g) {
=======
  private void attemptWakeUp(Character person) {
    double wakeUpChance = Math.random();
    if (person.getAbility().equals("Extreme Luck")) {
      wakeUpChance/= 2;
    }
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
    if (person instanceof PlayableCharacter) {
      if (wakeUpChance < 0.1 || playerStatusTurns > 5) {
        playerStatus = null;
        playerStatusTurns = 0;
        player.setStatus(null);
        g.drawString(playerName + " woke up.", drawX, drawY);
      }
    } else if (person instanceof NonPlayableCharacter) {
      if (wakeUpChance < 0.1 || opponentStatusTurns > 5) {
        opponentStatus = null;
        opponentStatusTurns = 0;
        g.drawString(opponentName + " woke up.", drawX, drawY);
      }
    }
  }

  private int determineOpponentMove() {
    return (int)(Math.random()*4);
  }

  //Getters
  public boolean isBattleEnd() {
    return battleEnd;
  }

  public boolean isPlayerLoses() {
    return playerLoses;
  }

  public boolean isOpponentLoses() {
    return opponentLoses;
  }

  public boolean isPlayerFled() {
    return playerFled;
  }

  public boolean isOpponenetFled() {
    return opponenetFled;
  }

  public PlayableCharacter getPlayer() {
    return player;
  }

  public NonPlayableCharacter getOpponent() {
    return opponent;
  }

  public String getOpponentStatus() {
    return opponentStatus;
  }

  public String getPlayerStatus() {
    return playerStatus;
  }

  public String getOpponentName() {
    return opponentName;
  }

  public String getPlayerName() {
    return playerName;
  }

  public void setOutputText(String text) {
	  //g.drawString(outputText);
	  this.outputText = outputText + "\n" + text;
  }

  public String getOutputText() {
	  return outputText;
  }

  public int determineAnswer(Handler handler) {
	if (handler.getKeyManager().first) {
		return 1;
	} else if (handler.getKeyManager().second) {
		return 2;
	} else if (handler.getKeyManager().third) {
		return 3;
	} else if (handler.getKeyManager().fourth) {
		return 4;
	} else {
		return -1;
	}
<<<<<<< HEAD
=======

  }

  public void HP(PlayableCharacter player, HealItem item){
    if(playerHealth - player.getCurrentHealth() >= item.getChange()) {
      player.changeCurrentHealth(item.getChange());
    }else{
      player.setCurrentHealth(playerHealth);
    }
    playerCurrentHealth = player.getCurrentHealth();
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
  }


  public void cureStatus(PlayableCharacter player, HealItem item){
      if(item.getType().equals(playerStatus)){
          player.resetStatus();
          playerStatus = player.getStatus();
      }
  }

<<<<<<< HEAD
  public void revive(PlayableCharacter character, HealItem item){
    if(player.isFainted()){
      if(item.getType().equals("Half revive")){
        player.setCurrentHealth(playerHealth / 2);
      }else if(item.getType().equals("Full revive")){
              player.resetCurrentHealth();
=======
  public void revive(PlayableCharacter player, HealItem item){
    if (player.isFainted()) {
      if(item.getType().equals("Half revive")) {
        player.setCurrentHealth(playerHealth/2);
      } else if(item.getType().equals("Full revive")) {
        player.resetCurrentHealth();
>>>>>>> 6203f8672d3400e8fbf5a69bbc1e8b64e3152137
      }
      playerCurrentHealth = player.getCurrentHealth();
      numberOfFaintedStudents--;
      player.reviveCharacter();
    }
  }

  public void useStatItem(PlayableCharacter player, StatItem item){
      if(item.getStatAffected().equals("Speed")){
          playerSpeed *= item.getMultiplier();
      }else if(item.getStatAffected().equals("Attack")){
          playerAttack *= item.getMultiplier();
      }else if(item.getStatAffected().equals("Defense")){
          playerDefence *= item.getMultiplier();
      }else if(item.getStatAffected().equals("Intelligence")){
          playerIntelligence *= item.getMultiplier();
      }else if(item.getStatAffected().equals("Health")){
          playerCurrentHealth += playerHealth * item.getMultiplier();
      }
  }
}