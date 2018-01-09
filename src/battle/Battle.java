package battle;

import java.awt.Color;
import java.awt.Graphics;


//Author @Feng and now Yash + Sihan
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
  private states.UpdateBattleText textUpdater;


  Battle (PlayableCharacter player, NonPlayableCharacter opponent, Squad squad, Inventory inventory, Handler handler, states.UpdateBattleText textUpdater) {
    //Constructor that requires some math
    this.player = player; //Saves the player
    this.opponent = opponent; //Saves the opponent
    this.playerInventory = inventory;
    this.squad = squad;
    this.handler = handler;
    this.textUpdater = textUpdater;
    
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
    if (playerHatItem != null) {
      useStatItem(player, playerHatItem);
    }
    if (playerShirtItem != null) {
      useStatItem(player, playerShirtItem);
    }
    if (playerPantsItem != null) {
      useStatItem(player, playerPantsItem);
    }
    if (playerShoesItem != null) {
      useStatItem(player, playerShoesItem);
    }

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
    if (playerHatItem != null) {
      useStatItem(player, playerHatItem);
    }
    if (playerShirtItem != null) {
      useStatItem(player, playerShirtItem);
    }
    if (playerPantsItem != null) {
      useStatItem(player, playerPantsItem);
    }
    if (playerShoesItem != null) {
      useStatItem(player, playerShoesItem);
    }
  }

  private int drawX, drawY;
  
  public void runBattle() {
      drawX = 20;
	  drawY = 20;
      //Turn number output should be its own string
      textUpdater.updateString("Turn number " + battleTurns, drawX, drawY);
      textUpdater.tick();

    //Protect, status and ability handling
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
        attemptWakeUp(player);
      }
    }
    if (opponentStatus != null) {
      if (opponentStatus.equals("Sleep")) {
        attemptWakeUp(opponent);
      }
    }
    if (!playerAbilityTriggered && opponentStatBoost > -5) {
      if (playerAbility.equals("Demoralize")) {
        opponentIntelligence /= 2;
        opponentStatBoost--;
        textUpdater.updateString(opponentName + " is demoralized. Their intelligence fell!", drawX, drawY);
        playerAbilityTriggered = true;
      }
    }
    if (!opponentAbilityTriggered && playerStatBoost > -5) {
      if (opponentAbility.equals("Demoralize")) {
        playerIntelligence /= 2;
        playerStatBoost--;
        textUpdater.updateString(playerName + " is demoralized. Their intelligence fell!", drawX, drawY);
        opponentAbilityTriggered = true;
      }
    }
    if (playerAbility.equals("Speed Boost") && playerStatBoost < 5) {
      playerSpeed *= 2;
      playerStatBoost++;
      textUpdater.updateString(playerName + "'s Speed Boost! Their speed increased!", drawX, drawY);
    }
    if (opponentAbility.equals("Speed Boost") && opponentStatBoost < 5) {
      opponentSpeed *= 2;
      opponentStatBoost++;
      textUpdater.updateString(opponentName + "'s Speed Boost! Their speed increased!", drawX, drawY);
    }

    //Code below would probably have to be put in another method or loops
    int answer;
    boolean exitLoop = false;
    do {
        /*g.setColor(Color.black);
    	g.fillRect(0, 0, 200, 200);*/
        //Displays the health of both opponents. This could be a string output too
        textUpdater.updateString(playerName + " " + playerCurrentHealth + "/" + playerHealth, drawX, drawY);
        textUpdater.updateString(opponentName + " " + opponentCurrentHealth + "/" + opponentHealth, drawX, drawY);
        textUpdater.updateString("What would you like to do", drawX, drawY);
        textUpdater.updateString("Fight (1)", drawX, drawY);
        textUpdater.updateString("Inventory (2)", drawX, drawY);
        textUpdater.updateString("Squad (3)", drawX, drawY);
        textUpdater.updateString("Run (4)", drawX, drawY);


      //Takes user input

      do {
        try {
          answer = determineAnswer(handler);
        } catch (InputMismatchException e) {
          //Forces the loop to run again
          answer = -1;
        }
      } while (answer < 1 || answer > 4);
      if (answer == 1) {
    	  System.out.println("detected");
        textUpdater.updateString("What move would you like to use", drawX, drawY);
        //Display the moves
        for (int i = 0; i < 4; i++) {
          textUpdater.updateString(player.getMove(i).getName() + " " + player.getPowerPoints(i) + "/" + player.getMove(i).getMaxPowerPoints() + " (" + (i + 1) + ")", drawX, drawY);
        }
        do {
          try {
            answer = determineAnswer(handler);
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
              textUpdater.updateString(playerName + " is asleep!", drawX, drawY);
            } else if (Math.random() < 0.25 && playerStatus.equals("Stun")){
              textUpdater.updateString(playerName + " is stunned!", drawX, drawY);
            } else {
              //Player moves if it is not stunned or asleep
              textUpdater.updateString(playerName + " used " + player.getMove(answer - 1).getName(), drawX, drawY);
              determineAttackType(player.getMove(answer - 1), player);
            }
          } else {
            textUpdater.updateString(playerName + " used " + player.getMove(answer - 1).getName(), drawX, drawY);
            determineAttackType(player.getMove(answer - 1), player);
            //Protecting will be handled in the move methods
          }
          if (opponentCurrentHealth > 0) {
            //Can't go if the opponent is dead
            if (opponentStatus != null) {
              if (opponentStatus.equals("Sleep")) {
                textUpdater.updateString(opponentName + " is asleep!", drawX, drawY);
              } else if (Math.random() < 0.25 && opponentStatus.equals("Stun")) {
                textUpdater.updateString(opponentName + " is stunned!", drawX, drawY);
              } else {
                textUpdater.updateString(opponentName + " used " + opponent.getMove(opponentMove).getName(), drawX, drawY);
                determineAttackType(opponent.getMove(opponentMove), opponent);
              }
            } else {
              textUpdater.updateString(opponentName + " used " + opponent.getMove(opponentMove).getName(), drawX, drawY);
              determineAttackType(opponent.getMove(opponentMove), opponent);
            }
          }
        } else if (moveFirst == 1) {
          if (opponentStatus != null) {
            if (opponentStatus.equals("Sleep")) {
              textUpdater.updateString(opponentName + " is asleep!", drawX, drawY);
            } else if (Math.random() < 0.25 && opponentStatus.equals("Stun")) {
              textUpdater.updateString(opponentName + " is stunned!", drawX, drawY);
            } else {
              textUpdater.updateString(opponentName + " used " + opponent.getMove(opponentMove).getName(), drawX, drawY);
              determineAttackType(opponent.getMove(opponentMove), opponent);
            }
          } else {
            textUpdater.updateString(opponentName + " used " + opponent.getMove(opponentMove).getName(), drawX, drawY);
            determineAttackType(opponent.getMove(opponentMove), opponent);
          }
          if (playerStatus != null) {
            if (playerStatus.equals("Sleep")) {
              textUpdater.updateString(playerName + " is asleep!", drawX, drawY);
            } else if (Math.random() < 0.25 && playerStatus.equals("Stun")){
              textUpdater.updateString(playerName + " is stunned!", drawX, drawY);
            } else {
              //Player moves if it is not stunned or asleep
              textUpdater.updateString(playerName + " used " + player.getMove(answer - 1).getName(), drawX, drawY);
              determineAttackType(player.getMove(answer - 1), player);
            }
          } else {
            textUpdater.updateString(playerName + " used " + player.getMove(answer - 1).getName(), drawX, drawY);
            determineAttackType(player.getMove(answer - 1), player);
            //Protecting will be handled in the move methods
          }
        }
        exitLoop = true;
      } else if (answer == 2) {
        textUpdater.updateString("Inventory items", drawX, drawY);
        playerInventory.displayItems();
        textUpdater.updateString("Would you like to use an item (1/2)", drawX, drawY);
        do {
          try {
            answer = determineAnswer(handler);
          } catch (InputMismatchException e) {
            answer = -1;
          }
        } while (answer < 1 || answer > 2);
        if (answer == 1) {
          boolean itemUsed = false;
          do {
            do {
              try {
                answer = determineAnswer(handler);
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
              HealItem itemToUse = (HealItem) item;
              switch (itemToUse.getType()) {
                case "HP":
                  HP(player, itemToUse);
                  break;
                case "PP":
                  //yet to create method for this
                  break;
                case "Half revive":
                case "Full revive":
                  revive(player, itemToUse);
                  break;
                default:
                  cureStatus(player, itemToUse);
                  break;
              }
              //ALL IN DIFFERENT METHODS (pp and hp can be same method)
            } else if (item instanceof StatItem) {
              textUpdater.updateString("You can use that here!", drawX, drawY);
              useStatItem(player, (StatItem)item);
              }
            if (itemUsed) {
              playerInventory.useItem(playerInventory.getItemName(answer));
            }
          } while (!itemUsed);
        }
      } else if (answer == 3) {
        //Check out the squad
        squad.displaySquad();
        textUpdater.updateString("", drawX, drawY);
        textUpdater.updateString("Would you like to switch in a squad member (1/2)", drawX, drawY);
        do {
          try {
            answer = determineAnswer(handler);
          } catch (InputMismatchException e) {
            answer = 20;
          }
        } while (answer < 1 || answer > 2);
        if (answer == 1) {
          textUpdater.updateString("Who would you like to switch in", drawX, drawY);
          boolean pickLoop = false;
          do {
            do {
              try {
                answer = determineAnswer(handler);
              } catch (InputMismatchException e) {
                answer = -1;
              }
            } while (answer < 1 || answer > squad.getSize());

            if (squad.getCharacter(answer - 1).getCurrentHealth() > 0) {
              changeCharacter(squad.getCharacter(answer - 1));
              pickLoop = true;
            } else {
              textUpdater.updateString("That person is dead", drawX, drawY);
            }
          } while (!pickLoop);
          int opponentMove = determineOpponentMove();
          textUpdater.updateString(opponentName + " used " + opponent.getMove(opponentMove).getName(), drawX, drawY);
          determineAttackType(opponent.getMove(opponentMove), opponent);
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
      if (playerHeldItem.getName().equals("Starbucks Card") && playerCurrentHealth != playerHealth) {
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
      if (opponentHeldItem.getName().equals("Starbucks Card") && opponentCurrentHealth != opponentHealth) {
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
      textUpdater.updateString("Everyone died", drawX, drawY);
      battleEnd = true;
    } else if (numberOfFaintedStudents == partySize) {
      textUpdater.updateString("Your party died", drawX, drawY);
      battleEnd = true;
      playerLoses = true;
    } else if (opponentCurrentHealth == 0) {
      textUpdater.updateString("Good job you passed", drawX, drawY);
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
          textUpdater.updateString("Your student broke down", drawX, drawY);
          squad.displaySquad();
          do {
            try {
              answer = determineAnswer(handler);
            } catch (InputMismatchException e) {
              answer = -1;
            }
          } while (answer < 1 || answer > squad.getSize());
          if (squad.getCharacter(answer - 1).getCurrentHealth() > 0) {
            changeCharacter(squad.getCharacter(answer - 1));
            exitLoop = true;
          } else {
            textUpdater.updateString("That student is dead.", drawX, drawY);
          }
        } else {
          exitLoop = true;
        }
      } while (!exitLoop);
    }
    battleTurns++;
    textUpdater.updateString("", drawX, drawY);
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


  private void determineAttackType(Move move, Character user) {
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
      attackMove((AttackMove) move, attacker);
    } else if (move instanceof ProtectMove) {
      protectMove(attacker);
    } else if (move instanceof HealthMove) {
      healthMove((HealthMove) move, attacker);
    } else if (move instanceof StatChangeMove) {
      if (((StatChangeMove)move).getTarget().equals("Self")) {
        //Whenever the target is the user, the attacker uses the move on itself and therefore the attacker would be "technically" the other person
        //Because the move itself does not affect the attacker, this is okay
        statChangeMove((StatChangeMove)move, -attacker);
      } else {
        //The target is other person
        statChangeMove((StatChangeMove) move, attacker);
      }
    } else if (move instanceof StatusMove) {
      if (((StatusMove)move).getTarget().equals("Self")) {
        //Whenever the target is the user, the attacker uses the move on itself and therefore the attacker would be "technically" the other person
        //Because the move itself does not affect the attacker, this is okay
        statusMove((StatusMove) move, -attacker);
      } else {
        statusMove((StatusMove)move, attacker);
      }
    } else if (move instanceof  SleepTalkMove) {
      if (attacker == -1) {
        if (playerStatus != null) {
          if (playerStatus.equals("Sleep")) {
            boolean attackTrue = false;
            do {
              int randomMove = (int) (Math.random()*4);
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
              int randomMove = (int) (Math.random()*4);
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


  private void attackMove(AttackMove move, int attacker) {
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
    if (attacker == 1) {
      if (playerAbility.equals("Clown")) {
        if (Math.random() < 0.25) {
          damageDealt = damageDealt / 2;
          textUpdater.updateString(playerName + " is clowning around.", drawX, drawY);
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
            textUpdater.updateString(opponentName + " is clowning around.", drawX, drawY);
        }
      } else if (opponentAbility.equals("Avoidant")) {
        determineHit *= 4;
      }
      if (opponentAbility.equals("Extreme Luck")) {
        determineHit /= 2;
      }
    }

    //Add code to have more stuff when a student faints
    if (determineHit < move.getHitChance()) {
      //Determines whether the attack hits or not
      if (attacker == -1) {
        if (!opponentProtected) {
          attackTriggered = true;
          if (effectivenessText != null) {
            textUpdater.updateString(effectivenessText, drawX, drawY);
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
                  opponentDefence = opponentDefence * 2;
                  textUpdater.updateString("The opponent is persistent!", drawX, drawY);
                  textUpdater.updateString("Opponent defence rose sharply!", drawX, drawY);
                  opponentAbilityTriggered = true;
                  break;
                case "Distressed":
                  opponentIntelligence = opponentIntelligence * 2;
                  textUpdater.updateString("The opponent is distressed!", drawX, drawY);
                  textUpdater.updateString("Player intelligence rose sharply!", drawX, drawY);
                    opponentAbilityTriggered = true;
                case "Protective":
                  opponentSpeed *= 2;
                  opponentAttack *= 2;
                  textUpdater.updateString(opponentName + " is protective!", drawX, drawY);
                  textUpdater.updateString(opponentName + "'s speed rose sharply!", drawX, drawY);
                  textUpdater.updateString(opponentName + "'s attack rose sharply!", drawX, drawY);
                  opponentAbilityTriggered = true;
                  break;
              }
            }
          }
        } else {
          textUpdater.updateString("The opponent protected!", drawX, drawY);
        }
      } else if (attacker == 1) {
        if (!playerProtected) {
          attackTriggered = true;
          if (effectivenessText != null) {
            textUpdater.updateString(effectivenessText, drawX, drawY);
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
                  playerDefence = playerDefence * 2;
                  textUpdater.updateString("The player is persistent!", drawX, drawY);
                  textUpdater.updateString("Player defence rose sharply!", drawX, drawY);
                  playerAbilityTriggered = true;
                  break;
                case "Distressed":
                  playerIntelligence = playerIntelligence * 2;
                  textUpdater.updateString("The player is distressed!", drawX, drawY);
                  textUpdater.updateString("Player intelligence rose sharply!", drawX, drawY);
                  playerAbilityTriggered = true;
                  break;
                case "Protective":
                  playerSpeed *= 2;
                  playerAttack *= 2;
                    textUpdater.updateString(playerName + " is protective!", drawX, drawY);
                    textUpdater.updateString(playerName + "'s speed rose sharply!", drawX, drawY);
                    textUpdater.updateString(playerName + "'s attack rose sharply!", drawX, drawY);
                  playerAbilityTriggered = true;
                  break;
              }
            }
          }
        }
      } else {
        textUpdater.updateString("The player protected!", drawX, drawY);
      }
    } else {
      textUpdater.updateString("The move missed!", drawX, drawY);
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
            healthMove((HealthMove) additionalEffect, attacker);
        } else if (additionalEffect instanceof StatChangeMove) {
            statChangeMove((StatChangeMove) additionalEffect, attacker * 2);
        } else if (additionalEffect instanceof StatusMove) {
            statusMove((StatusMove) additionalEffect, attacker * 2);
        }
      }
    }
  }

  private void protectMove(int attacker) {
    if (attacker == -1) {
      if (Math.random() < playerProtectChance) {
        playerProtected = true;
      } else {
        textUpdater.updateString("The protect failed", drawX, drawY);
      }
    } else if (attacker == 1) {
      if (Math.random() < opponentProtectChance) {
        opponentProtected = true;
      } else {
        textUpdater.updateString("The protect failed", drawX, drawY);
      }
    }
  }

  private void statChangeMove(StatChangeMove move, int attacker) {
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
        if (attacker < 0) {
            if (move.getTarget().equals("Opponent")) {
                if (!opponentProtected) {
                    if (playerStatBoost > -5) {
                        attackTriggered = true;
                        switch (move.getStatType()) {
                            case "Attack":
                                opponentAttack /= move.getMultiplier();
                                textUpdater.updateString(opponentName + "'s attack fell!", drawX, drawY);
                                break;
                            case "Intelligence":
                                opponentIntelligence /= move.getMultiplier();
                                textUpdater.updateString(opponentName + "'s intelligence fell!", drawX, drawY);
                                break;
                            case "Defence":
                                opponentDefence /= move.getMultiplier();
                                textUpdater.updateString(opponentName + "'s defence fell!", drawX, drawY);
                                break;
                            case "Speed":
                                opponentSpeed /= move.getMultiplier();
                                textUpdater.updateString(opponentName + "'s speed fell!", drawX, drawY);
                                break;
                        }
                        opponentStatBoost--;
                    } else {
                        textUpdater.updateString("That stat cannot be lowered anymore!", drawX, drawY);
                    }
                } else {
                    textUpdater.updateString(opponentName + " protected from the attack!", drawX, drawY);
                }
            } else if (move.getTarget().equals("Self")) {
                if (playerStatBoost < 5) {
                    attackTriggered = true;
                    switch (move.getStatType()) {
                        case "Attack":
                            playerAttack *= move.getMultiplier();
                            textUpdater.updateString(opponentName + "'s attack increased!", drawX, drawY);
                            break;
                        case "Intelligence":
                            opponentIntelligence *= move.getMultiplier();
                            textUpdater.updateString(opponentName + "'s intelligence increased!", drawX, drawY);
                            break;
                        case "Defence":
                            opponentDefence *= move.getMultiplier();
                            textUpdater.updateString(opponentName + "'s defence increased!", drawX, drawY);
                            break;
                        case "Speed":
                            opponentSpeed *= move.getMultiplier();
                            textUpdater.updateString(opponentName + "'s speed increased!", drawX, drawY);
                            break;
                    }
                    playerStatBoost++;
                } else {
                    textUpdater.updateString("That stat cannot be raised anymore!", drawX, drawY);
                }
            }
        } else if (attacker > 0) {
            if (move.getTarget().equals("Opponent")) {
                if (!playerProtected) {
                    if (playerStatBoost > -5) {
                        attackTriggered = true;
                        switch (move.getStatType()) {
                            case "Attack":
                                playerAttack /= move.getMultiplier();
                                textUpdater.updateString(playerName + "'s attack fell!", drawX, drawY);
                                break;
                            case "Intelligence":
                                playerIntelligence /= move.getMultiplier();
                                textUpdater.updateString(playerName + "'s intelligence fell!", drawX, drawY);
                                break;
                            case "Defence":
                                playerDefence /= move.getMultiplier();
                                textUpdater.updateString(playerName + "'s defence fell!", drawX, drawY);
                                break;
                            case "Speed":
                                playerSpeed /= move.getMultiplier();
                                textUpdater.updateString(playerName + "'s speed fell!", drawX, drawY);
                                break;
                        }
                    } else {
                        textUpdater.updateString("That stat cannot be lowered anymore", drawX, drawY);
                    }
                } else {
                    textUpdater.updateString(playerName + " protected!", drawX, drawY);
                }
            } else if (move.getTarget().equals("Self")) {
                if (opponentStatBoost < 5) {
                    attackTriggered = true;
                    switch (move.getStatType()) {
                        case "Attack":
                            opponentAttack *= move.getMultiplier();
                            textUpdater.updateString(opponentName + "'s attack increased!", drawX, drawY);
                            break;
                        case "Intelligence":
                            opponentIntelligence *= move.getMultiplier();
                            textUpdater.updateString(opponentName + "'s intelligence increased!", drawX, drawY);
                            break;
                        case "Defence":
                            opponentDefence *= move.getMultiplier();
                            textUpdater.updateString(opponentName + "'s defence increased!", drawX, drawY);
                            break;
                        case "Speed":
                            opponentSpeed *= move.getMultiplier();
                            textUpdater.updateString(opponentName + "'s speed increased!", drawX, drawY);
                            break;
                    }
                }
            }
        }
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
          healthMove((HealthMove) additionalEffect, attacker);
        } else if (additionalEffect instanceof StatChangeMove) {
            statChangeMove((StatChangeMove) additionalEffect, attacker * 2);
        } else if (additionalEffect instanceof StatusMove) {
            statusMove((StatusMove) additionalEffect, attacker * 2);
        }
      }
    }
  }

  private void healthMove(HealthMove move, int attacker) {
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
          healthMove((HealthMove) additionalEffect, attacker);
        } else if (additionalEffect instanceof StatChangeMove) {
            statChangeMove((StatChangeMove) additionalEffect, attacker * 2);
        } else if (additionalEffect instanceof StatusMove) {
            statusMove((StatusMove) additionalEffect, attacker * 2);
        }
      }
    }
  }

  private void statusMove(StatusMove move, int attacker) {
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
            textUpdater.updateString("The opponent fell asleep.", drawX, drawY);
          } else {
            textUpdater.updateString("The opponent was " + move.getStatusEffect() + "ed.", drawX, drawY);
          }
        } else {
          textUpdater.updateString("The opponent protected", drawX, drawY);
        }
      } else if (attacker > 0){
        if (!playerProtected) {
          player.setStatus(move.getStatusEffect());
          playerStatus = player.getStatus();
          if (move.getStatusEffect().equals("Sleep")) {
            textUpdater.updateString("The player fell asleep.", drawX, drawY);
          } else {
            textUpdater.updateString("The player was " + move.getStatusEffect() + "ed.", drawX, drawY);
          }
        } else {
          textUpdater.updateString("The player protected", drawX, drawY);
        }
      }
    } else {
      System.out.println("The move missed!");
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

  private void attemptWakeUp(Character person) {
    double wakeUpChance = Math.random();
    if (person.getAbility().equals("Extreme Luck")) {
      wakeUpChance/= 2;
    }
    if (person instanceof PlayableCharacter) {
      if (wakeUpChance < 0.1 || playerStatusTurns > 5) {
        playerStatus = null;
        playerStatusTurns = 0;
        player.setStatus(null);
        textUpdater.updateString(playerName + " woke up.", drawX, drawY);
      }
    } else if (person instanceof NonPlayableCharacter) {
      if (wakeUpChance < 0.1 || opponentStatusTurns > 5) {
        opponentStatus = null;
        opponentStatusTurns = 0;
        textUpdater.updateString(opponentName + " woke up.", drawX, drawY);
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
	  //textUpdater.updateString(outputText);
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
  }

  public void HP(PlayableCharacter player, HealItem item){
    if(playerHealth - player.getCurrentHealth() >= item.getChange()) {
      player.changeCurrentHealth(item.getChange());
    }else{
      player.setCurrentHealth(playerHealth);
    }
    playerCurrentHealth = player.getCurrentHealth();
  }


  public void cureStatus(PlayableCharacter player, HealItem item){
      if(item.getType().equals(playerStatus)){
          player.resetStatus();
          playerStatus = player.getStatus();
      }
  }

  public void revive(PlayableCharacter player, HealItem item){
    if (player.isFainted()) {
      if(item.getType().equals("Half revive")) {
        player.setCurrentHealth(playerHealth/2);
      } else if(item.getType().equals("Full revive")) {
        player.resetCurrentHealth();
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