package battle;


//Author @Feng and now Yash + Sihan
/* Battle classes are an object itself
 * Runs a loop in battle itself (To be added)
 * Need to add switching, inventory, fleeing, and move selection
 */

import java.util.ArrayList;

import characters.Character;
import characters.NonPlayableCharacter;
import characters.PlayableCharacter;
import characters.Squad;
import game.Handler;
import items.*;

public class Battle {
  //Objects that need to be saved here
  private Handler handler;	
	
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
  private boolean playerMovedFirst, playerAttacked;

  // BIGGO STRING
  private String outputText;

  private String turnNumberString;
  private String[] selectionStrings;
  private String[] characterStrings; //Used for switching out

  private boolean playerChoicePhase, playerPickAttackPhase, playerAttackChoicePhase, playerSwitchPhase, playerPickCharacterPhase, playerInventoryPhase, playerInventoryChoicePhase;
  private boolean forceSwitchCharacterPhase;
  private ArrayList<String> textArrayList = new ArrayList<>();


  Battle (Handler handler, PlayableCharacter player, NonPlayableCharacter opponent, Squad squad, Inventory inventory) {
    //Constructor that requires some math
	this.handler = handler;
    this.player = player; //Saves the player
    this.opponent = opponent; //Saves the opponent
    this.playerInventory = inventory;
    this.squad = squad;
    
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
      useStatItem(playerHatItem);
    }
    if (playerShirtItem != null) {
      useStatItem(playerShirtItem);
    }
    if (playerPantsItem != null) {
      useStatItem(playerPantsItem);
    }
    if (playerShoesItem != null) {
      useStatItem(playerShoesItem);
    }

    //Ints
    this.opponentHealth = opponent.getInitialHealth();
    this.opponentCurrentHealth = opponent.getCurrentHealth();
    this.opponentAttack = opponent.getAttack();
    this.opponentIntelligence = opponent.getIntelligence();
    this.opponentDefence = opponent.getDefence();
    this.opponentSpeed = opponent.getSpeed();
    
    //Strings
	this.opponentName = handler.getGame().getCurrentOpponentName();
    //this.opponentName = opponent.getName();
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

    this.turnNumberString = null;
    this.selectionStrings = new String[4];
    selectionStrings[0] = "null";
    selectionStrings[1] = "null";
    selectionStrings[2] = "null";
    selectionStrings[3] = "null";

    this.playerChoicePhase = true;
    this.playerPickAttackPhase = false;
    this.playerAttackChoicePhase = false;
    this.playerSwitchPhase = false;
    this.playerPickCharacterPhase = false;
    this.playerInventoryPhase = false;
    this.playerInventoryChoicePhase = false;
    this.forceSwitchCharacterPhase = false;

    this.playerMovedFirst = false;
    this.playerAttacked = false;
    this.characterStrings = new String[6];
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
      useStatItem(playerHatItem);
    }
    if (playerShirtItem != null) {
      useStatItem(playerShirtItem);
    }
    if (playerPantsItem != null) {
      useStatItem(playerPantsItem);
    }
    if (playerShoesItem != null) {
      useStatItem(playerShoesItem);
    }
  }
  
  public void runBattleTurn(int phase) {

	System.out.println(handler.getGame().getCurrentOpponentName());
    if (phase > 0) {
      selectionStrings[0] = "null";
      if (phase - 1 == 0) {
        playerPickAttackPhase = true;
        playerPickAttack();
        playerChoicePhase = false;
        playerAttacked = true;
      } else if (phase - 1 == 1) {
        playerInventoryPhase = true;
        playerPickInventory();
        playerChoicePhase = false;
        playerAttacked = false;
      } else if (phase - 1 == 2) {
        playerSwitchPhase = true;
        playerSwitchCharacter();
        playerChoicePhase = false;
        playerAttacked = false;
      } else if (phase - 1 == 3) {
        playerRun();
        playerChoicePhase = false;
        playerAttacked = false;
      }
      return;
    }
    //Turn number output should be its own string
    turnNumberString = "Turn number: " + battleTurns;

    if (playerProtected) {
      playerProtectChance /= 2;
      playerProtected = false;
    }
    if (opponentProtected) {
      opponentProtectChance /= 2;
      opponentProtected = false;
    }
    //Protect, status and ability handling
    if (phase != -10) {
      if (battleTurns == 1) {
        textArrayList.add(opponent.getSpeech() + ".");
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
      //Whenever a string needs to be drawn, it should call for a repaint
      if (!playerAbilityTriggered && opponentStatBoost < 5) {
        if (playerAbility.equals("Annoying")) {
          opponentAttack *= 2;
          opponentStatBoost++;
          textArrayList.add(playerName + " is annoying! " + opponentName + "'s attack increased!");
          playerAbilityTriggered = true;
        }
      }
      if (!opponentAbilityTriggered && playerStatBoost < 5) {
        if (opponentAbility.equals("Annoying")) {
          playerAttack *= 2;
          playerStatBoost++;
          textArrayList.add(opponentName + " is annoying! " + playerName + "'s attack increased!");
          opponentAbilityTriggered = true;
        }
      }
      if (!playerAbilityTriggered && opponentStatBoost > -5) {
        if (playerAbility.equals("Demoralize")) {
          opponentIntelligence /= 2;
          opponentStatBoost--;
          textArrayList.add(opponentName + " is demoralized. Their intelligence fell!");
          playerAbilityTriggered = true;
        }
      }
      if (!opponentAbilityTriggered && playerStatBoost > -5) {
        if (opponentAbility.equals("Demoralize")) {
          playerIntelligence /= 2;
          playerStatBoost--;
          textArrayList.add(playerName + " is demoralized. Their intelligence fell!");
          opponentAbilityTriggered = true;
        }
      }
      if (!playerAbilityTriggered && opponentStatBoost > -5) {
        if (playerAbility.equals("Friendly")) {
          opponentDefence /= 2;
          opponentStatBoost--;
          textArrayList.add(playerName + " is friendly. " + opponentName + "'s defence fell!");
          playerAbilityTriggered = true;
        }
      }
      if (!opponentAbilityTriggered && playerStatBoost > -5) {
        if (opponentAbility.equals("Friendly")) {
          playerDefence /= 2;
          playerStatBoost--;
          textArrayList.add(opponentName + " is friendly. " + playerName + "'s defence fell!");
          opponentAbilityTriggered = true;
        }
      }
      if (playerAbility.equals("Speed Boost") && playerStatBoost < 5) {
        playerSpeed *= 2;
        playerStatBoost++;
        textArrayList.add(playerName + " got a Speed Boost! Their speed increased!");
      }
      if (opponentAbility.equals("Speed Boost") && opponentStatBoost < 5) {
        opponentSpeed *= 2;
        opponentStatBoost++;
        textArrayList.add(opponentName + " got a Speed Boost! Their speed increased!");
      }
      if (playerAbility.equals("Power Boost") && playerStatBoost < 5) {
        playerAttack *= 2;
        playerStatBoost++;
        textArrayList.add(playerName + " got a Power Boost! Their attack increased!");
      }
      if (opponentAbility.equals("Power Boost") && opponentStatBoost < 5) {
        opponentAttack *= 2;
        opponentStatBoost++;
        textArrayList.add(opponentName + " got a Power Boost! Their attack increased!");
      }
    }
    //Displays the health of both opponents. This could be a string output too
    selectionStrings[0] = "Fight";
    selectionStrings[1] = "Inventory";
    selectionStrings[2] = "Squad";
    selectionStrings[3] = "Run";
  }

  public void goBackInMenu() {
    this.playerChoicePhase = true;

    this.playerPickAttackPhase = false;
    this.playerAttackChoicePhase = false;
    this.playerSwitchPhase = false;
    this.playerPickCharacterPhase = false;
    this.playerInventoryPhase = false;
    this.playerInventoryChoicePhase = false;
    if (playerCurrentHealth != 0) {
      forceSwitchCharacterPhase = false;
    }
    runBattleTurn(-10);
  }

  public void runAnotherTurn() {
    this.playerChoicePhase = true;

    this.playerPickAttackPhase = false;
    this.playerAttackChoicePhase = false;
    this.playerSwitchPhase = false;
    this.playerPickCharacterPhase = false;
    this.playerInventoryPhase = false;
    this.playerInventoryChoicePhase = false;
    if (playerCurrentHealth != 0) {
      forceSwitchCharacterPhase = false;
    }
    runBattleTurn(-2);
  }

  public void playerPickAttack() {
    playerAttackChoicePhase = true;
    playerAttacked = true;
    for (int i = 0; i < 4; i++) {
      selectionStrings[i] = player.getMove(i).getName() + " " + player.getPowerPoints(i) + "/" + player.getMove(i).getMaxPowerPoints();
    }
  }

  public void playerUseAttack(int choice) {
    //Move displays should be handled differently
    selectionStrings[0] = "null";
    playerPickAttackPhase = false;
    playerAttackChoicePhase = false;
    int opponentMove = determineOpponentMove();
    int moveFirst = determineOrder(player.getMove(choice - 1), opponent.getMove(opponentMove));
    player.setPowerPoints(choice - 1, -1);
    if (moveFirst == -1) {
      if (playerStatus != null) {
        if (playerStatus.equals("Sleep") && !(player.getMove(choice - 1) instanceof SleepTalkMove)) {
          textArrayList.add(playerName + " is sound asleep.");
        } else if (Math.random() < 0.25 && playerStatus.equals("Stun")) {
        	textArrayList.add(playerName + " is stunned! They can't move.");
        } else {
        		//Player moves if it is not stunned or asleep
          textArrayList.add(playerName + " used " + player.getMove(choice - 1).getName() + ".");
          determineAttackType(player.getMove(choice - 1), player);
        }
      } else {
        textArrayList.add(playerName + " used " + player.getMove(choice - 1).getName() + ".");
      }
      if (opponentCurrentHealth > 0) {
        //Can't go if the opponent is dead
        if (opponentStatus != null) {
          if (opponentStatus.equals("Sleep") && !(opponent.getMove(choice - 1) instanceof SleepTalkMove)) {
            textArrayList.add(opponentName + " is sound asleep.");
          } else if (Math.random() < 0.25 && opponentStatus.equals("Stun")) {
            textArrayList.add(opponentName + " is stunned! They can't move!");
          } else
            textArrayList.add(opponentName + " used " + opponent.getMove(opponentMove).getName() + ".");
          determineAttackType(opponent.getMove(opponentMove), opponent);
        } else {
        textArrayList.add(opponentName + " used " + opponent.getMove(opponentMove).getName() + ".");
        determineAttackType(opponent.getMove(opponentMove), opponent);
       }
      } else {
        textArrayList.add(opponentName + " fainted!");
      }
    } else if(moveFirst == 1) {
      if (opponentStatus != null) {
        if (opponentStatus.equals("Sleep") && !(opponent.getMove(choice -1) instanceof SleepTalkMove)) {
          textArrayList.add(opponentName + " is sound asleep.");
        } else if (Math.random() < 0.25 && opponentStatus.equals("Stun")) {
          textArrayList.add(opponentName + " is stunned! They can't move!");
        } else {
          textArrayList.add(opponentName + " used " + opponent.getMove(opponentMove).getName() + ".");
          determineAttackType(opponent.getMove(opponentMove), opponent);
        }
      } else {
        textArrayList.add(opponentName + " used " + opponent.getMove(opponentMove).getName() + ".");
        determineAttackType(opponent.getMove(opponentMove), opponent);
      }
      if (playerCurrentHealth > 0) {
        if (playerStatus != null) {
          if (playerStatus.equals("Sleep") && !(player.getMove(choice -1) instanceof SleepTalkMove)) {
            textArrayList.add(playerName + " is sound asleep.");
          } else if (Math.random() < 0.25 && playerStatus.equals("Stun")) {
            textArrayList.add(playerName + " is stunned! They can't move!");
          } else {
            //Player moves if it is not stunned or asleep
            textArrayList.add(playerName + " used " + player.getMove(choice - 1).getName() + ".");
            determineAttackType(player.getMove(choice - 1), player);
          }
        } else {
          textArrayList.add(playerName + " used " + player.getMove(choice - 1).getName() + ".");
          determineAttackType(player.getMove(choice - 1), player);
        }
      } else {
        textArrayList.add(playerName + " fainted!");
      }
    }
    endTurn();
  }

  public void playerPickInventory() {
    playerInventoryChoicePhase = true;
  }

  public void useInventoryItem(int answer) {
    boolean itemUsed = false;
    Item item = playerInventory.getItem(answer);
    textArrayList.add(playerName + " used " + playerInventory.getItemName(answer));
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
      textArrayList.add("You can't use that here!");
    }
    if (itemUsed) {
      playerInventory.useItem(playerInventory.getItemName(answer));
      playerInventoryChoicePhase = false;
      opponentTurn();
    }
  }

  private void HP(PlayableCharacter player, HealItem item){
      if(playerHealth - player.getCurrentHealth() >= item.getChange()) {
          player.changeCurrentHealth(item.getChange());
      }else{
          player.setCurrentHealth(playerHealth);
      }
      playerCurrentHealth = player.getCurrentHealth();
      textArrayList.add(playerName + "'s health was increased by " + item.getChange() + " HP.");
  }

  private void PP(){
      //select move to apply it to
      //add change, cap it
  }

  private void revive(PlayableCharacter player, HealItem item){
      if (player.isFainted()) {
          if(item.getType().equals("Half revive")) {
             player.setCurrentHealth(playerHealth/2);
             textArrayList.add(playerName + " was revived with half health.");
          } else if(item.getType().equals("Full revive")) {
              player.resetCurrentHealth();
              textArrayList.add(playerName + " was revived with full health.");
          }
          playerCurrentHealth = player.getCurrentHealth();
          numberOfFaintedStudents--;
          player.reviveCharacter();
      }
  }

  private void cureStatus(PlayableCharacter player, HealItem item){
      if(item.getType().equals(playerStatus)) {
          if (playerStatus.equals("Sleep")) {
              textArrayList.add(playerName + " " + " woke up.");
          } else {
              textArrayList.add(playerName + "'s status was cured.");
          }
          player.resetStatus();
          playerStatus = player.getStatus();
      }else{
          textArrayList.add("There has been no previous damage to your status.");
          textArrayList.add("Using" + item.getName() + " was not very effective...");
      }
  }

  private void useStatItem(StatItem item) {
      if (item.getStatAffected().equals("Speed")) {
          playerSpeed *= item.getMultiplier();
          textArrayList.add("The player's speed was increased by " + (item.getMultiplier() - 1) * 100 + "%");
      } else if (item.getStatAffected().equals("Attack")) {
          playerAttack *= item.getMultiplier();
          textArrayList.add("The player's attack was increased by " + (item.getMultiplier() - 1)* 100 + "%");
      } else if (item.getStatAffected().equals("Defence")) {
          playerDefence *= item.getMultiplier();
          textArrayList.add("The player's defence was increased by " + (item.getMultiplier() - 1) * 100 + "%");
      } else if (item.getStatAffected().equals("Intelligence")) {
          playerIntelligence *= item.getMultiplier();
          textArrayList.add("The player's intelligence was increased by " + (item.getMultiplier() - 1) * 100 + "%");
      } else if (item.getStatAffected().equals("Health")) {
          playerCurrentHealth += playerHealth * item.getMultiplier();
          textArrayList.add("The player's health was increased by " + (item.getMultiplier() - 1) * 100 + "%");
      }
  }

  public void playerSwitchCharacter() {
    int squadSize = squad.getSize();
    for (int i=0; i<squadSize; i++) {
      PlayableCharacter character = squad.getCharacter(i);
      characterStrings[i] = character.getName() + " " + character.getCurrentHealth() + "/" + character.getInitialHealth();
    }
    //I don't think I ever use this ^^
    playerPickCharacterPhase = true;
  }

  public void playerPickCharacter(int choice) {
    if (squad.getCharacter(choice) != null) {
      if (squad.getCharacter(choice).getCurrentHealth() > 0) {
        changeCharacter(squad.getCharacter(choice));
        playerSwitchPhase = false;
        if (!forceSwitchCharacterPhase) {
          opponentTurn();
        } else if (forceSwitchCharacterPhase) {
          forceSwitchCharacterPhase = false;
          runAnotherTurn();
        }
      }
    }
  }

  public void playerRun() {
    if (Math.random() < playerFleeChance) {
      playerFled = true;
      playerLoses = true;
      battleEnd = true;
      textArrayList.add("Ran successfully!");
    } else {
      goBackInMenu();
      textArrayList.add("Could not run!");
      opponentTurn();
    }
  }

  private void opponentTurn() {
    playerAttacked = false;
    int moveUsed = determineOpponentMove();
    textArrayList.add(opponentName + " used " + opponent.getMove(moveUsed).getName() + ".");
    determineAttackType(opponent.getMove(moveUsed), opponent);
    if (playerCurrentHealth == 0) {
      textArrayList.add(playerName + " fainted!");
    }
    endTurn();
  }

  
  public void endTurn() {
    if (playerCurrentHealth > 0 && playerHeldItem != null) {
      if (playerHeldItem.getName().equals("Starbucks Card") && playerCurrentHealth != playerHealth) {
        if (playerCurrentHealth + playerHealth*0.06 > playerHealth) {
          player.resetCurrentHealth();
          playerHealth = player.getCurrentHealth();
        } else {
          player.changeCurrentHealth((int)(playerHealth*0.06));
          playerCurrentHealth = player.getCurrentHealth();
        }
        textArrayList.add(playerName + " bought a Starbucks drink and recovered some health!");
      }
    }
    if (opponentCurrentHealth > 0 && opponentHeldItem != null) {
      if (opponentHeldItem.getName().equals("Starbucks Card") && opponentCurrentHealth != opponentHealth) {
        if (opponentCurrentHealth + opponentHealth*0.06 > opponentHealth) {
          opponentCurrentHealth = opponentHealth;
        } else {
          opponentCurrentHealth += (int)(opponentHealth*0.06);
        }
        textArrayList.add(opponentName + " bought a Starbucks drink and recovered some health!");
      }
    }
    if (playerStatus != null && playerCurrentHealth > 0) {
      if (playerStatus.equals("Burn")) {
        burnPerson(player);
      } else if (playerStatus.equals("Poison")) {
        poisonPerson(player);
      }
    }
    if (opponentStatus != null && opponentCurrentHealth > 0) {
      if (opponentStatus.equals("Burn")) {
        burnPerson(opponent);
      } else if (opponentStatus.equals("Poison")) {
        poisonPerson(opponent);
      }
    }
    if (playerCurrentHealth == 0) {
      numberOfFaintedStudents++;
    }
    if (numberOfFaintedStudents == partySize && opponentCurrentHealth < 1) {
      textArrayList.add("Everyone died.");
      battleEnd = true;
    } else if (numberOfFaintedStudents == partySize) {
      textArrayList.add("Your party died.");
      battleEnd = true;
      playerLoses = true;
    } else if (opponentCurrentHealth < 1) {
      textArrayList.add("Great job!");
      textArrayList.add("You passed!");
      battleEnd = true;
      opponentLoses = true;
    }
    if (playerCurrentHealth != 0 && playerStatus != null) {
      playerStatusTurns++;
    }
    if (opponentCurrentHealth != 0 && opponentStatus != null) {
      opponentStatusTurns++;
    }
    battleTurns++;

    if (!battleEnd) {
      if (playerAbility.equals("Osmosis")) {
        if (opponentCurrentHealth == 0) {
          playerIntelligence *= 2;
        }
      }
      if (opponentAbility.equals("Osmosis")) {
        if (playerCurrentHealth == 0) {
          opponentIntelligence *= 2;
        }
      }
    }
    if (!battleEnd && playerCurrentHealth > 0) {
      textArrayList.add("");
    }
    if (!battleEnd && playerCurrentHealth == 0) {
      goBackInMenu();
      forceSwitchCharacterPhase = true;
      runBattleTurn(3);
    } else if (!battleEnd) {
      runAnotherTurn();
    }
  }

  public void setBattleEnd(boolean battleEnd) {
	this.battleEnd = battleEnd;
}

  //Calculations
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
      playerMovedFirst = true;
      return -1;
    } else if (playerMove.getPriority() < opponentMove.getPriority()) {
      playerMovedFirst = false;
      return 1;
    } else if (tempPlayerSpeed > tempOpponentSpeed) {
      playerMovedFirst = true;
      return -1;
    } else if (tempPlayerSpeed < tempOpponentSpeed) {
      playerMovedFirst = false;
      return 1;
    } else {
      //In case of a tie breaker
      int decision = (int)Math.floor(Math.random()*2);
      if (decision == 0) {
        playerMovedFirst = true;
        return -1;
      } else {
        playerMovedFirst = false;
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
      statChangeMove((StatChangeMove) move, attacker);
    } else if (move instanceof StatusMove) {
      statusMove((StatusMove)move, attacker);
    } else if (move instanceof  SleepTalkMove) {
      if (attacker == -1) {
        if (playerStatus != null) {
          if (playerStatus.equals("Sleep")) {
            boolean attackTrue = false;
            int randomMove;
            do {
              randomMove = (int) (Math.random()*4);
              if (!(player.getMove(randomMove) instanceof SleepTalkMove)) {
                Move moveUsed = player.getMove(randomMove);
                attackTrue = true;
                determineAttackType(moveUsed, player);
              }
            } while (!attackTrue);
            textArrayList.add(playerName + " used " + player.getMove(randomMove).getName() + ".");
          } else {
            textArrayList.add("The move failed!");
          }
        } else {
          textArrayList.add("The move failed!");
        }
      } else if (attacker == 1) {
        if (opponentStatus != null) {
          if (opponentStatus.equals("Sleep")) {
            boolean attackTrue = false;
            int randomMove;
            do {
              randomMove = (int) (Math.random()*4);
              if (!(opponent.getMove(randomMove) instanceof SleepTalkMove)) {
                Move moveUsed = opponent.getMove(randomMove);
                attackTrue = true;
                determineAttackType(moveUsed, opponent);
              }
            } while (!attackTrue);
            textArrayList.add(opponentName + " used " + opponent.getMove(randomMove).getName() + ".");
          } else {
            textArrayList.add("The move failed!");
          }
        } else {
          textArrayList.add("The move failed!");
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
    
    if (defence < 0) {
    		defence = 0;
    }
	damageDealt = (int)(Math.ceil((move.getPower() * (attackerStatUsed/defence+1))/10) * multiplier);


    double determineHit = Math.random();
    //Factors in the clown ability
    //I should probably throw this into another method after we add a lot of abilities
    if (attacker == 1) {
      if (playerAbility.equals("Clown")) {
        if (Math.random() < 0.25) {
          damageDealt = damageDealt / 2;
          textArrayList.add(playerName + " is clowning around.");
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
            textArrayList.add(opponentName + " is clowning around.");
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
            textArrayList.add(effectivenessText);
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
                  textArrayList.add(opponentName + " is persistent!");
                  textArrayList.add(opponentName + "'s defence rose sharply!");
                  opponentAbilityTriggered = true;
                  break;
                case "Distressed":
                  opponentIntelligence = opponentIntelligence * 2;
                  textArrayList.add(opponentName + " is distressed!");
                  textArrayList.add(opponentName + "'s intelligence rose sharply!");
                  opponentAbilityTriggered = true;
                  break;
                case "Protective":
                  opponentSpeed *= 2;
                  opponentAttack *= 2;
                  textArrayList.add(opponentName + " is protective!");
                  textArrayList.add(opponentName + "'s speed rose sharply!");
                  textArrayList.add(opponentName + "'s attack rose sharply!");
                  opponentAbilityTriggered = true;
                  break;
              }
            }
          }
        } else {
          textArrayList.add(opponentName + " protected!");
        }
      } else if (attacker == 1) {
        if (!playerProtected) {
          attackTriggered = true;
          if (effectivenessText != null) {
            textArrayList.add(effectivenessText);
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
                  textArrayList.add(playerName + " is persistent!");
                  textArrayList.add(playerName + "'s defence rose sharply!");
                  playerAbilityTriggered = true;
                  break;
                case "Distressed":
                  playerIntelligence = playerIntelligence * 2;
                  textArrayList.add(playerName + " is distressed!");
                  textArrayList.add(playerName + "'s intelligence rose sharply!");
                  playerAbilityTriggered = true;
                  break;
                case "Protective":
                  playerSpeed *= 2;
                  playerAttack *= 2;
                    textArrayList.add(playerName + " is protective!");
                    textArrayList.add(playerName + "'s speed rose sharply!");
                    textArrayList.add(playerName + "'s attack rose sharply!");
                  playerAbilityTriggered = true;
                  break;
              }
            }
          }
        } else {
          textArrayList.add(playerName + " protected!");
        }
      }
    } else {
      textArrayList.add("The move missed!");
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

  public boolean isOpponentAbilityTriggered() {
	return opponentAbilityTriggered;
}

  private void protectMove(int attacker) {
    if (attacker == -1) {
      if (Math.random() < playerProtectChance) {
        playerProtected = true;
      } else {
        textArrayList.add("The protect failed.");
      }
    } else if (attacker == 1) {
      if (Math.random() < opponentProtectChance) {
        opponentProtected = true;
      } else {
        textArrayList.add("The protect failed.");
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
                                textArrayList.add(opponentName + "'s attack fell!");
                                break;
                            case "Intelligence":
                                opponentIntelligence /= move.getMultiplier();
                                textArrayList.add(opponentName + "'s intelligence fell!");
                                break;
                            case "Defence":
                                opponentDefence /= move.getMultiplier();
                                textArrayList.add(opponentName + "'s defence fell!");
                                break;
                            case "Speed":
                                opponentSpeed /= move.getMultiplier();
                                textArrayList.add(opponentName + "'s speed fell!");
                                break;
                        }
                        opponentStatBoost--;
                    } else {
                        textArrayList.add("That stat cannot be lowered anymore!");
                    }
                } else {
                    textArrayList.add(opponentName + " protected from the attack!");
                }
            } else if (move.getTarget().equals("Self")) {
                if (playerStatBoost < 5) {
                    attackTriggered = true;
                    switch (move.getStatType()) {
                        case "Attack":
                            playerAttack *= move.getMultiplier();
                            textArrayList.add(playerName + "'s attack increased!");
                            break;
                        case "Intelligence":
                            opponentIntelligence *= move.getMultiplier();
                            textArrayList.add(playerName + "'s intelligence increased!");
                            break;
                        case "Defence":
                            opponentDefence *= move.getMultiplier();
                            textArrayList.add(playerName + "'s defence increased!");
                            break;
                        case "Speed":
                            opponentSpeed *= move.getMultiplier();
                            textArrayList.add(playerName + "'s speed increased!");
                            break;
                    }
                    playerStatBoost++;
                } else {
                    textArrayList.add("That stat cannot be raised anymore!");
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
                                textArrayList.add(playerName + "'s attack fell!");
                                break;
                            case "Intelligence":
                                playerIntelligence /= move.getMultiplier();
                                textArrayList.add(playerName + "'s intelligence fell!");
                                break;
                            case "Defence":
                                playerDefence /= move.getMultiplier();
                                textArrayList.add(playerName + "'s defence fell!");
                                break;
                            case "Speed":
                                playerSpeed /= move.getMultiplier();
                                textArrayList.add(playerName + "'s speed fell!");
                                break;
                        }
                    } else {
                        textArrayList.add("That stat cannot be lowered anymore!");
                    }
                } else {
                    textArrayList.add(playerName + " protected!");
                }
            } else if (move.getTarget().equals("Self")) {
                if (opponentStatBoost < 5) {
                    attackTriggered = true;
                    switch (move.getStatType()) {
                        case "Attack":
                            opponentAttack *= move.getMultiplier();
                            textArrayList.add(opponentName + "'s attack increased!");
                            break;
                        case "Intelligence":
                            opponentIntelligence *= move.getMultiplier();
                            textArrayList.add(opponentName + "'s intelligence increased!");
                            break;
                        case "Defence":
                            opponentDefence *= move.getMultiplier();
                            textArrayList.add(opponentName + "'s defence increased!");
                            break;
                        case "Speed":
                            opponentSpeed *= move.getMultiplier();
                            textArrayList.add(opponentName + "'s speed increased!");
                            break;
                    }
                } else {
                  textArrayList.add("Stats cannot be raised anymore!");
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

    double determineAdditionalHit = Math.random();
    if (attacker == 1) {
      if (opponentAbility.equals("Extreme Luck")) {
        determineAdditionalHit /= 2;
      }
    } else if (attacker == -1) {
      if (opponentAbility.equals("Extreme Luck")) {
        determineAdditionalHit /= 2;
      }
    }

    if (move.getAdditionalEffect() != null) {
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
        if (move.getTarget().equals("Self")) {
          if (playerStatus == null) {
            playerStatusTurns = 0;
            player.setStatus(move.getStatusEffect());
            playerStatus = player.getStatus();
            if (move.getStatusEffect().equals("Sleep")) {
              textArrayList.add(playerName + " fell asleep.");
            } else if (move.getStatusEffect().equals("Stun")) {
              textArrayList.add(playerName + " was stunned! They may not be able to move.");
            } else {
              textArrayList.add(playerName + " was " + move.getStatusEffect() + "ed.");
            }
          } else if (playerStatus.equals(move.getStatusEffect())) {
            if (attacker != -2) {
              if (move.getStatusEffect().equals("Sleep")) {
                textArrayList.add(playerName + " is already asleep.");
              } else if (move.getStatusEffect().equals("Stun")) {
                textArrayList.add(playerName + " is already stunned.");
              } else {
                textArrayList.add(playerName + " is already " + move.getStatusEffect().toLowerCase() + "ed.");
              }
            }
          } else {
            playerStatusTurns = 0;
            player.setStatus(move.getStatusEffect());
            playerStatus = player.getStatus();
            if (move.getStatusEffect().equals("Sleep")) {
              textArrayList.add(playerName + " fell asleep.");
            } else if (move.getStatusEffect().equals("Stun")) {
              textArrayList.add(playerName + " was stunned! They may not be able to move.");
            } else {
              textArrayList.add(playerName + " was " + move.getStatusEffect().toLowerCase() + "ed.");
            }
          }
        } else if (move.getTarget().equals("Opponent")) {
          if (!opponentProtected) {
            if (opponentStatus == null) {
              opponentStatusTurns = 0;
              opponentStatus = move.getStatusEffect();
              if (move.getStatusEffect().equals("Sleep")) {
                textArrayList.add(opponentName + " fell asleep.");
              } else if (move.getStatusEffect().equals("Stun")) {
                textArrayList.add(opponentName + " was stunned! They may not be able to move.");
              } else {
                textArrayList.add(opponentName + " was " + move.getStatusEffect().toLowerCase() + "ed.");
              }
            } else if (opponentStatus.equals(move.getStatusEffect())) {
              if (attacker != -2) {
                if (move.getStatusEffect().equals("Sleep")) {
                  textArrayList.add(opponentName + " is already asleep.");
                } else if (move.getStatusEffect().equals("Stun")) {
                  textArrayList.add(opponentName + " is already stunned.");
                } else {
                  textArrayList.add(opponentName + " is already " + move.getStatusEffect().toLowerCase() + "ed.");
                }
              }
            } else {
              opponentStatusTurns = 0;
              opponentStatus = move.getStatusEffect();
              if (move.getStatusEffect().equals("Sleep")) {
                textArrayList.add(opponentName + " fell asleep.");
              } else if (move.getStatusEffect().equals("Stun")) {
                textArrayList.add(opponentName + " was stunned! They may not be able to move.");
              } else {
                textArrayList.add(opponentName + " was " + move.getStatusEffect().toLowerCase() + "ed.");
              }
            }
          } else {
            textArrayList.add(opponentName + " protected.");
          }
        }
      } else if (attacker > 0) {
        if (move.getTarget().equals("Self")) {
          if (opponentStatus == null) {
            opponentStatusTurns = 0;
            opponentStatus = move.getStatusEffect();
            if (move.getStatusEffect().equals("Sleep")) {
              textArrayList.add(opponentName + " fell asleep.");
            } else if (move.getStatusEffect().equals("Stun")) {
              textArrayList.add(opponentName + " was stunned! They may not be able to move.");
            } else {
              textArrayList.add(opponentName + " was " + move.getStatusEffect().toLowerCase() + "ed.");
            }
          } else if (opponentStatus.equals(move.getStatusEffect())) {
            if (attacker != 2) {
              if (move.getStatusEffect().equals("Sleep")) {
                textArrayList.add(opponentName + " is already asleep.");
              } else if (move.getStatusEffect().equals("Stun")) {
                textArrayList.add(opponentName + " is already stunned.");
              } else {
                textArrayList.add(opponentName + " is already " + move.getStatusEffect().toLowerCase() + "ed.");
              }
            }
          } else {
            opponentStatusTurns = 0;
            opponentStatus = move.getStatusEffect();
            if (move.getStatusEffect().equals("Sleep")) {
              textArrayList.add(opponentName + " fell asleep.");
            } else if (move.getStatusEffect().equals("Stun")) {
              textArrayList.add(opponentName + " was stunned! They may not be able to move.");
            } else {
              textArrayList.add(opponentName + " was " + move.getStatusEffect().toLowerCase() + "ed.");
            }
          }
        } else if (move.getTarget().equals("Opponent")) {
          if (!playerProtected) {
            if (playerStatus == null) {
              playerStatusTurns = 0;
              player.setStatus(move.getStatusEffect());
              playerStatus = player.getStatus();
              if (move.getStatusEffect().equals("Sleep")) {
                textArrayList.add(playerName + " fell asleep.");
              } else if (move.getStatusEffect().equals("Stun")) {
                textArrayList.add(playerName + " was stunned! They may not be able to move.");
              } else {
                textArrayList.add(playerName + " was " + move.getStatusEffect().toLowerCase() + "ed.");
              }
            } else if (playerStatus.equals(move.getStatusEffect())) {
              if (attacker != 2) {
                if (move.getStatusEffect().equals("Sleep")) {
                  textArrayList.add(playerName + " is already asleep.");
                } else if (move.getStatusEffect().equals("Stun")) {
                  textArrayList.add(playerName + " is already stunned.");
                } else {
                  textArrayList.add(playerName + " is already " + move.getStatusEffect().toLowerCase() + "ed.");
                }
              }
            } else {
              playerStatusTurns = 0;
              player.setStatus(move.getStatusEffect());
              playerStatus = player.getStatus();
              if (move.getStatusEffect().equals("Sleep")) {
                textArrayList.add(playerName + " fell asleep.");
              } else if (move.getStatusEffect().equals("Stun")) {
                textArrayList.add(playerName + " was stunned! They may not be able to move.");
              } else {
                textArrayList.add(playerName + " was " + move.getStatusEffect().toLowerCase() + "ed.");
              }
            }
          } else {
            textArrayList.add(playerName + " protected.");
          }
        }
      }
    } else {
      textArrayList.add("The move missed!");
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
            multiplier *= 2;
          } else if (move.getType().equals("English")) {
            multiplier /= 2;
          }
          break;
        case "Science":
          if (move.getType().equals("Technology")) {
            multiplier *= 2;
          } else if (move.getType().equals("Math")) {
            multiplier /= 2;
          }
          break;
        case "Technology":
          if (move.getType().equals("English")) {
            multiplier *= 2;
          } else if (move.getType().equals("Science")) {
            multiplier /= 2;
          }
          break;
        case "English":
          if (move.getType().equals("Math")) {
            multiplier *= 2;
          } else if (move.getType().equals("Technology")) {
            multiplier /= 2;
          }
          break;
      }
      if (multiplier > 1) {
        effectivenessText = "It's super effective!";
      } else if (multiplier < 1) {
        effectivenessText = "It's not very effective...";
      }
      if (playerType.equals(move.getType())) {
        multiplier *= 1.5; //Same Type Attack Bonus (STAB)
      }
      if (playerStatus != null) {
        if (playerStatus.equals("Burned")) {
          multiplier *= 0.5; //Burned halves damage
        }
      }
    }
    if (attacker == 1) {
      switch (playerType) {
        case "Math":
          if (move.getType().equals("Science")) {
            multiplier *= 2;
          } else if (move.getType().equals("English")) {
            multiplier /= 2;
          }
          break;
        case "Science":
          if (move.getType().equals("Technology")) {
            multiplier *= 2;
          } else if (move.getType().equals("Math")) {
            multiplier /= 2;
          }
          break;
        case "Technology":
          if (move.getType().equals("English")) {
            multiplier *= 2;
          } else if (move.getType().equals("Science")) {
            multiplier /= 2;
          }
          break;
        case "English":
          if (move.getType().equals("Math")) {
            multiplier *= 2;
          } else if (move.getType().equals("Technology")) {
            multiplier /= 2;
          }
          break;
      }
      if (multiplier > 1) {
        effectivenessText = "It's super effective!";
      } else if (multiplier < 1) {
        effectivenessText = "It's not very effective...";
      }
      if (opponentType.equals(move.getType())) {
        multiplier *= 1.5; //Same Type Attack Bonus (STAB)
      }
      if (opponentStatus != null) {
        if (opponentStatus.equals("Burned")) {
          multiplier *= 0.5; //Burn does half damage
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
        playerCurrentHealth = player.getCurrentHealth();
      } else {
        person.changeCurrentHealth(-(int)burn);
        playerCurrentHealth = player.getCurrentHealth();
      }
      textArrayList.add(playerName + " was hurt from its burn!");
    } else if (person instanceof NonPlayableCharacter) {
      burn = opponentHealth/12;
      if (opponentCurrentHealth - (int)burn < 0) {
        opponent.faintCharacter();
        opponentCurrentHealth = opponent.getCurrentHealth();
      } else {
        person.changeCurrentHealth(-(int)burn);
        opponentCurrentHealth = opponent.getCurrentHealth();
      }
      textArrayList.add(opponentName + " was hurt from its burn!");
    }
  }

  private void poisonPerson(Character person) {
    double poison;
    if (person instanceof PlayableCharacter) {
      poison = playerHealth/15 * playerStatusTurns;
      if (playerCurrentHealth - (int)poison < 0) {
        player.faintCharacter();
        playerFainted = true;
        playerCurrentHealth = player.getCurrentHealth();
      } else {
        person.changeCurrentHealth(-(int)poison);
        playerCurrentHealth = player.getCurrentHealth();
      }
      textArrayList.add(playerName + " was hurt from poison!");
    } else if (person instanceof NonPlayableCharacter) {
      poison = opponentHealth/15 * opponentStatusTurns;
      if (opponentCurrentHealth - (int)poison < 0) {
        opponent.faintCharacter();
        opponentCurrentHealth = opponent.getCurrentHealth();
      } else {
        person.changeCurrentHealth(-(int)poison);
        opponentCurrentHealth = opponent.getCurrentHealth();
      }
      textArrayList.add(opponentName + " was hurt from poison!");
    }
  }

  private void attemptWakeUp(Character person) {
    double wakeUpChance = Math.random();
    if (person.getAbility().equals("Extreme Luck")) {
      wakeUpChance/= 2;
    }
    if (person instanceof PlayableCharacter) {
      if (wakeUpChance < 0.1 || playerStatusTurns > 3) {
        playerStatus = null;
        playerStatusTurns = 0;
        player.setStatus(null);
        textArrayList.add(playerName + " woke up.");
      }
    } else if (person instanceof NonPlayableCharacter) {
      if (wakeUpChance < 0.1 || opponentStatusTurns > 3) {
        opponentStatus = null;
        opponentStatusTurns = 0;
        textArrayList.add(opponentName + " woke up.");
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

  boolean isPlayerChoicePhase() {
    return playerChoicePhase;
  }

  boolean isPlayerAttackChoicePhase() {
    return playerAttackChoicePhase;
  }

  boolean isPlayerInventoryPhase() {
    return playerInventoryPhase;
  }

  boolean isPlayerSwitchPhase() {
    return playerSwitchPhase;
  }

  boolean isPlayerInventoryChoicePhase() {
    return playerInventoryChoicePhase;
  }

  boolean isPlayerPickAttackPhase() {
    return playerPickAttackPhase;
  }

  boolean isPlayerPickCharacterPhase() {
    return playerPickCharacterPhase;
  }
  
  ArrayList<String> getTextArrayList() {
    return textArrayList;
  }

  String getSelectionStrings(int i) {
    return selectionStrings[i];
  }

  boolean isPlayerAttacked() {
    return playerAttacked;
  }

  boolean isPlayerMovedFirst() {
    return playerMovedFirst;
  }

  String[] getCharacterStrings() {
    return characterStrings;
  }
  
  void setOpponentName(String name) {
	  opponentName = name;
  }
}