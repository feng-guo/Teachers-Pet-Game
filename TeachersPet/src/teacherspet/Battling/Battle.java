//Author @Feng
/* Battle classes are an object itself
 * Runs a loop in battle itself (To be added)
 * Need to add switching, inventory, fleeing, and move selection
 */

import java.util.InputMismatchException; //Prevents people like Samyar from intentionally throwing in bad inputs. Will be replaced.
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

  //NPC Stats. Same deal as above
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

  //Battling variables
  private int partySize; //Size of the player party
  private int numberOfFaintedStudents; //Once this number is equal to the party size, the game is over
  private boolean battleEnd; //Exits the game loop once over
  private boolean playerLoses;
  private boolean opponentLoses;
  private boolean playerFled;
  private boolean opponenetFled;
  private String effectivenessText;

  Battle (PlayableCharacter player, NonPlayableCharacter opponent, Squad squad, Inventory inventory) {
    //Constructor that requires some math
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

    //Battle variables
    this.partySize = squad.getSize();
    this.numberOfFaintedStudents = squad.getNumberOfFaintedStudents();
    battleEnd = false;
    playerLoses = false;
    opponentLoses = false;
    playerFled = false;
  }

  public void changeCharacter(PlayableCharacter player) {
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
    //Code for switch in animation goes here
  }

  //Fix this method
  public void runBattle() {
    System.out.println();
    if (playerProtected) {
      playerProtectChance = playerProtectChance / 2;
      playerProtected = false;
    }
    if (opponentProtected) {
      opponentProtectChance = opponentProtectChance / 2;
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
    Scanner input = new Scanner(System.in);
    //KeyBoardListener keyBoardListener = new KeyBoardListener();
    //Code below would probably have to be put in another method or loops
    int answer = 0;
    boolean exitLoop = false;
    do {
      System.out.println(playerName + " " + playerCurrentHealth + "/" + playerHealth);
      System.out.println(opponentName + " " + opponentCurrentHealth + "/" + opponentHealth);
      System.out.println("What would you like to do");
      System.out.println("Fight (1)");
      System.out.println("Inventory (2)");
      System.out.println("Squad (3)");
      System.out.println("Run (4)");
      do {
        try {
          answer = input.nextInt();
        } catch (InputMismatchException e) {
        }
      } while (answer < 1 || answer > 4);
      if (answer == 1) {
        System.out.println("What move would you like to use");
        //Display the moves
        for (int i = 0; i < 4; i++) {
          System.out.println(player.getMove(i).getName() + " " + player.getPowerPoints(i) + "/" + player.getMove(i).getMaxPowerPoints() + " (" + (i + 1) + ")");
        }
        do {
          try {
            answer = input.nextInt();
          } catch (InputMismatchException e) {
          }
        } while (answer < 1 || answer > 4);
        int opponentMove = determineOpponentMove();
        int moveFirst = determineOrder(player.getMove(answer - 1), opponent.getMove(opponentMove));
        player.setPowerPoints(answer - 1, -1);
        if (moveFirst == -1) {
          if (playerStatus != null) {
            if (playerStatus.equals("Sleep")) {
              System.out.println(playerName + " is asleep");
            }
          } else {
            System.out.println(playerName + " used " + player.getMove(answer - 1).getName());
            determineAttackType(player.getMove(answer - 1), player);
            //Protecting will be handled in the move methods
          }
          if (!opponentLoses || !(opponentStatus.equals("Sleep"))) {
            //Can't go if the opponent is dead
            try {
              if (opponentStatus.equals("Stunned")) {
                //Decides if the opponent can go if they're stunned
                if (Math.random() < 0.75) {
                  System.out.println(opponentName + " used " + opponent.getMove(opponentMove).getName());determineAttackType(opponent.getMove(opponentMove), opponent);
                  determineAttackType(opponent.getMove(opponentMove), opponent);
                } else {
                  System.out.println("Opponent is stunned!");
                }
              } else {
                System.out.println(opponentName + " used " + opponent.getMove(opponentMove).getName());
                determineAttackType(opponent.getMove(opponentMove), opponent);
              }
            } catch (NullPointerException e) {
              System.out.println(opponentName + " used " + opponent.getMove(opponentMove).getName());
              determineAttackType(opponent.getMove(opponentMove), opponent);
            }
          } else if (opponentStatus != null) {
            if (opponentStatus.equals("Sleep")) {
              System.out.println(opponentName + " is asleep!");
            }
          }
        } else if (moveFirst == 1) {
          if (opponentStatus != null) {
            if (opponentStatus.equals("Sleep")) {
              System.out.println(opponentName + " is asleep.");
            }
          } else {
            System.out.println(opponentName + " used " + opponent.getMove(opponentMove).getName());
            determineAttackType(opponent.getMove(opponentMove), opponent);
            //Protecting will be handled in the move methods
          }
          if (playerCurrentHealth > 0 || !playerFainted || !(playerStatus.equals("Sleep"))) {
            //Can't go if the opponent is dead
            try {
              if (playerStatus.equals("Stun")) {
                //Decides if the opponent can go if they're stunned
                if (Math.random() < 0.75) {
                  System.out.println(opponentName + " used " + opponent.getMove(opponentMove).getName());
                  determineAttackType(player.getMove(answer - 1), player);
                } else {
                  System.out.println("Player is stunned!");
                }
              } else {
                System.out.println(playerName + " used " + player.getMove(answer - 1).getName());
                determineAttackType(player.getMove(answer - 1), player);
              }
            } catch (NullPointerException e) {
              //Catches a null player status. Better than checking null in an if statement to prevent duplicate attacks
              System.out.println(playerName + " used " + player.getMove(answer - 1).getName());
              determineAttackType(player.getMove(answer - 1), player);
            }
          } else if (playerStatus != null) {
            if (playerStatus.equals("Sleep")) {
              System.out.println("Your character is sleeping!");
            }
          }
        }
        exitLoop = true;
      } else if (answer == 2) {
        System.out.println("Inventory items");
        //Inventory interactions
      } else if (answer == 3) {
        //Check out the squad
        squad.displaySquad();
        System.out.println();
        System.out.println("Would you like to switch in a squad member (1/2)");
        do {
          try {
            answer = input.nextInt();
          } catch (InputMismatchException e) {
          }
        } while (answer < 1 || answer > 2);
        if (answer == 1) {
          System.out.println("Who would you like to switch in");
          boolean pickLoop = false;
          do {
            do {
              try {
                answer = input.nextInt();
              } catch (InputMismatchException e) {
              }
            } while (answer < 1 || answer > squad.getSize());

            if (squad.getCharacter(answer - 1).getCurrentHealth() > 0) {
              changeCharacter(squad.getCharacter(answer - 1));
              pickLoop = true;
            } else {
              System.out.println("That person is dead");
            }
          } while (!pickLoop);
          int opponentMove = determineOpponentMove();
          System.out.println(opponentName + " used " + opponent.getMove(opponentMove).getName());
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
    if (playerStatus != null) {
      if (playerStatus.equals("Burned")) {
        burnPerson(player);
      } else if (playerStatus.equals("Poisoned")) {
        poisonPerson(player);
      }
    }
    if (opponentStatus != null) {
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
      System.out.println("Everyone died");
      battleEnd = true;
    } else if (numberOfFaintedStudents == partySize) {
      System.out.println("Your party died");
      battleEnd = true;
      playerLoses = true;
    } else if (opponentCurrentHealth == 0) {
      System.out.println("Good job you passed");
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
          System.out.println("Your student broke down");
          squad.displaySquad();
          do {
            try {
              answer = input.nextInt();
            } catch (InputMismatchException e) {
            }
          } while (answer < 1 || answer > squad.getSize());
          if (squad.getCharacter(answer - 1).getCurrentHealth() > 0) {
            changeCharacter(squad.getCharacter(answer - 1));
            exitLoop = true;
          } else {
            System.out.println("That student is dead.");
          }
        } else {
          exitLoop = true;
        }
      } while (!exitLoop);
    }
  }

  public int determineOrder(Move playerMove, Move opponentMove) {
    int tempPlayerSpeed = playerSpeed;
    int tempOpponentSpeed = opponentSpeed;
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

  public void determineAttackType(Move move, Character user) {
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
      protectMove((ProtectMove)move, attacker);
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
    }
  }

  //Finish this method
  public void attackMove (AttackMove move, int attacker) {
    int attackerStatUsed = 0; //Determines whether attack or intelligence is used
    int defence = 0; //Determines whose defence to use
    double multiplier; //Multiplier for typing, status effects and STAB
    //This if statement determines whose stats to used to calculate damage
    if (attacker == -1) {
      //If attacker is -1, then the player is attacking. If it is 1, the opponent is attacking
      if (move.getAttackType().equals("Attack")) {
        attackerStatUsed = playerAttack;
      } else if (move.getAttackType().equals("Intelligence")) {
        attackerStatUsed = playerIntelligence;
      }
      defence = opponentDefence;
    } else if (attacker == 1) {
      if (move.getAttackType().equals("Attack")) {
        attackerStatUsed = opponentAttack;
      } else if (move.getAttackType().equals("Intelligence")) {
        attackerStatUsed = opponentIntelligence;
      }
      defence = playerDefence;
    }
    //This multiplier method determines the multiplier from STAB, statuses, and type advantages
    effectivenessText = null;
    multiplier = determineMultiplier(attacker, move);

    //Calculating the damage of the attack move
    int damageDealt;
    damageDealt = (int)(Math.ceil((move.getPower() * (attackerStatUsed/defence+1))/10) * multiplier);

    //Factors in the clown ability
    //I should probably throw this into another method after we add a lot of abilities
    if (playerAbility.equals("Clown") && attacker == 1) {
      if (Math.random() < 0.25) {
        damageDealt = damageDealt/2;
      }
    } else if (opponentAbility.equals("Clown") && attacker == -1) {
      if (Math.random() < 0.25) {
        damageDealt = damageDealt/2;
      }
    }

    //Add code to have more stuff when a student faints
    if (Math.random() < move.getHitChance()) {
      //Determines whether the attack hits or not
      if (attacker == -1) {
        if (!opponentProtected) {
          if (effectivenessText != null) {
            System.out.println(effectivenessText);
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
                  System.out.println("The opponent is persistent!");
                  System.out.println("Opponent defence rose sharply!");
                  opponentAbilityTriggered = true;
                  break;
                case "Distressed":
                  opponentIntelligence = opponentIntelligence * 2;
                  System.out.println("The opponent is distressed!");
                  System.out.println("Player intelligence rose sharply!");
                  opponentAbilityTriggered = true;
                  break;
              }
            }
          }
        } else {
          System.out.println("The opponent protected!");
        }
      } else if (attacker == 1) {
        if (!playerProtected) {
          if (effectivenessText != null) {
            System.out.println(effectivenessText);
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
                  System.out.println("The player is persistent!");
                  System.out.println("Player defence rose sharply!");
                  playerAbilityTriggered = true;
                  break;
                case "Distressed":
                  playerIntelligence = playerIntelligence * 2;
                  System.out.println("The player is distressed!");
                  System.out.println("Player intelligence rose sharply!");
                  playerAbilityTriggered = true;
                  break;
              }
            }
          }
        }
      } else {
        System.out.println("The player protected!");
      }
    } else {
      System.out.println("The move missed!");
    }

    //Additional effect of moves
    if (move.getAdditionalEffect() != null) {
      Move additionalEffect = move.getAdditionalEffect();
      if (Math.random() < additionalEffect.getHitChance()) {
        if (additionalEffect instanceof HealthMove) {
          healthMove((HealthMove) additionalEffect, attacker);
        } else if (additionalEffect instanceof StatChangeMove) {
          if (((StatChangeMove) additionalEffect).getTarget().equals("Self")) {
            statChangeMove((StatChangeMove) additionalEffect, -attacker * 2);
          } else {
            statChangeMove((StatChangeMove) additionalEffect, attacker * 2);
          }
        } else if (additionalEffect instanceof StatusMove) {
          if (((StatusMove) additionalEffect).getTarget().equals("Self")) {
            statusMove((StatusMove) additionalEffect, -attacker * 2);
          } else {
            statusMove((StatusMove) additionalEffect, attacker * 2);
          }
        }
      }
    }
  }

  public void protectMove (ProtectMove move, int attacker) {
    if (attacker == -1) {
      if (Math.random() < playerProtectChance) {
        playerProtected = true;
      } else {
        System.out.println("The protect failed");
      }
    } else if (attacker == 1) {
      if (Math.random() < opponentProtectChance) {
        opponentProtected = true;
      } else {
        System.out.println("The protect failed");
      }
    }
  }

  public void statChangeMove(StatChangeMove move, int attacker) {
    //This code is for moves that only change the stats of the opposing person
    if (Math.random() < move.getHitChance()) {
      //Determines whether or not the attack lands
      if (attacker < 0 && !opponentProtected) {
        if (opponentStatBoost>-5 && move.getTarget().equals("Opponent")) {
          if (move.getStatType().equals("Attack")) {
            opponentAttack = opponentAttack / move.getMultiplier();
          } else if (move.getStatType().equals("Intelligence")) {
            opponentIntelligence = opponentIntelligence / move.getMultiplier();
          } else if (move.getStatType().equals("Defence")) {
            opponentIntelligence = opponentIntelligence / move.getMultiplier();
          } else if (move.getStatType().equals("Speed")) {
            opponentSpeed = opponentSpeed / move.getMultiplier();
          }
          opponentStatBoost--;
        } else if (opponentStatBoost<5 && move.getTarget().equals("Self")) {
          if (move.getStatType().equals("Attack")) {
            opponentAttack = opponentAttack * move.getMultiplier();
          } else if (move.getStatType().equals("Intelligence")) {
            opponentIntelligence = opponentIntelligence * move.getMultiplier();
          } else if (move.getStatType().equals("Defence")) {
            opponentIntelligence = opponentIntelligence * move.getMultiplier();
          } else if (move.getStatType().equals("Speed")) {
            opponentSpeed = opponentSpeed * move.getMultiplier();
          }
          opponentStatBoost++;
        } else if (opponentProtected) {
          System.out.println(opponentName + "protected from the attack.");
        } else {
          System.out.println("The stat cannot be changed anymore?");
        }
      } else if (attacker > 0 && !playerProtected) {
        if (playerStatBoost>-5 && move.getTarget().equals("Opponent")) {
          if (move.getStatType().equals("Attack")) {
            playerAttack = playerAttack / move.getMultiplier();
          } else if (move.getStatType().equals("Intelligence")) {
            playerIntelligence = playerIntelligence / move.getMultiplier();
          } else if (move.getStatType().equals("Defence")) {
            playerIntelligence = playerIntelligence / move.getMultiplier();
          } else if (move.getStatType().equals("Speed")) {
            playerSpeed = playerSpeed / move.getMultiplier();
          }
          playerStatBoost--;
        } else if (playerStatBoost<5 && move.getTarget().equals("Self")) {
          if (move.getStatType().equals("Attack")) {
            playerAttack = playerAttack * move.getMultiplier();
          } else if (move.getStatType().equals("Intelligence")) {
            playerIntelligence = playerIntelligence * move.getMultiplier();
          } else if (move.getStatType().equals("Defence")) {
            playerIntelligence = playerIntelligence * move.getMultiplier();
          } else if (move.getStatType().equals("Speed")) {
            playerSpeed = playerSpeed * move.getMultiplier();
          }
          playerStatBoost++;
        } else {
          System.out.println("The stat cannot be changed anymore?");
        }
      }
    } else if (attacker%2 == 0){
      //Empty else if
      //This else if is for an attack with a secondary effect.
    } else {
      System.out.println("The move missed!");
    }
  }

  public void healthMove(HealthMove move, int attacker) {
    //Doesn't check hit chance because self healing is 100% successful
    if (attacker == -1) {
      if (move.getHeal() != -2) {
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
      }
    } else if (attacker == 1) {
      if (move.getHeal() != -2) {
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
      }
    }
  }

  public void statusMove(StatusMove move, int attacker) {
    //A move that changes the status of the move.
    if (Math.random() < move.getHitChance()) {
      //Checks hit chance
      if (attacker == -1) {
        if (!opponentProtected) {
          opponentStatus = move.getStatusEffect();
          if (move.getStatusEffect().equals("Sleep")) {
            System.out.println("The opponent fell asleep.");
          } else {
            System.out.println("The opponent was " + move.getStatusEffect() + "ed.");
          }
        } else {
          System.out.println("The opponent protected");
        }
      } else if (attacker == 1){
        if (!playerProtected) {
          player.setStatus(move.getStatusEffect());
          playerStatus = player.getStatus();
          if (move.getStatusEffect().equals("Sleep")) {
            System.out.println("The player fell asleep.");
          } else {
            System.out.println("The player was " + move.getStatusEffect() + "ed.");
          }
        } else {
          System.out.println("The player protected");
        }
      }
    } else {
      //The move misses
    }
  }

  public double determineMultiplier(int attacker, AttackMove move) {
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

  public void burnPerson(Character person) {
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

  public void poisonPerson(Character person) {
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

  public void attemptWakeUp(Character person) {
    if (person instanceof PlayableCharacter) {
      if (Math.random() < 0.1 || playerStatusTurns > 5) {
        playerStatus = null;
        playerStatusTurns = 0;
        player.setStatus(null);
        System.out.println(playerName + " woke up.");
      }
    } else if (person instanceof NonPlayableCharacter) {
      if (Math.random() < 0.1 || opponentStatusTurns > 5) {
        opponentStatus = null;
        opponentStatusTurns = 0;
        System.out.println(opponentName + " woke up.");
      }
    }
  }

  public int determineOpponentMove() {
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
}
