package Battling;//Author @Feng
/* Battling.Battle classes are an object itself
 * Runs a loop in battle itself (To be added)
 * Need to add switching, inventory, fleeing, and move selection
 */

class Battle /*extends Interaction*/ {
  //Battling.Character objects
  private PlayableCharacter player; //Since it is an object, when the values of the objects are changed, they stay changed
  private NonPlayableCharacter opponent; //Same for this one

  //Player Stats. This only exist in the battle. This is for changes during a battle that do not affect future battles.
  //Also, it is easier to save the variables here because it is easier to retrieve them
  private int playerHealth;
  private int playerCurrentHealth;
  private int playerAttack;
  private int playerIntelligence;
  private int playerDefence;
  private int playerSpeed;
  private String playerName; //Names are for display purposes when GUI is implemented
  private String playerType;
  private String playerStatus; //Will be factored in later

  //NPC Stats. Same deal as above
  private int opponentHealth;
  private int opponentCurrentHealth;
  private int opponentAttack;
  private int opponentIntelligence;
  private int opponentDefence;
  private int opponentSpeed;
  private String opponentName; //Names are for display purposes when GUI is implemented
  private String opponentType;
  private String opponentStatus;

  //Battling.Battle variables
  private int partySize; //Size of the player party
  private int numberOfFaintedStudents; //Once this number is equal to the party size, the game is over
  private boolean battleEnd; //Exits the game loop once over
  private boolean playerLoses;
  private boolean opponentLoses;

  Battle (PlayableCharacter player, NonPlayableCharacter opponent, int partySize, int numberOfFaintedStudents) {
    //Constructor that requires some math
    this.player = player; //Saves the player
    this.opponent = opponent; //Saves the opponent
    /*
    The reason why I save the variables here is because the stats in battles are reset once the battle ends
    Therefore, it would be better to just keep them in the class itself
    */
    this.playerHealth = player.getInitialHealth();
    this.playerCurrentHealth = player.getCurrentHealth();
    this.playerAttack = player.getAttack();
    this.playerIntelligence = player.getIntelligence();
    this.playerDefence = player.getDefence();
    this.playerSpeed = player.getSpeed();
    this.playerName = player.getName();
    this.playerType = player.getType();

    this.opponentHealth = opponent.getInitialHealth();
    this.opponentCurrentHealth = opponent.getCurrentHealth();
    this.opponentAttack = opponent.getAttack();
    this.opponentIntelligence = opponent.getIntelligence();
    this.opponentDefence = opponent.getDefence();
    this.opponentSpeed = opponent.getSpeed();
    this.opponentName = opponent.getName();
    this.opponentType = opponent.getType();

    this.partySize = partySize;
    this.numberOfFaintedStudents = numberOfFaintedStudents;
    battleEnd = false;
    playerLoses = false;
    opponentLoses = false;
  }

  public void changeCharacter(PlayableCharacter player, Move opponentMove) {
    this.player = player; //Updates the player
    //For switching out students
    this.playerHealth = player.getInitialHealth();
    this.playerCurrentHealth = player.getCurrentHealth();
    this.playerAttack = player.getAttack();
    this.playerIntelligence = player.getIntelligence();
    this.playerDefence = player.getDefence();
    this.playerSpeed = player.getSpeed();
    this.playerName = player.getName();
    this.playerType = player.getType();

    //Code for switch in animation goes here
    //Because the player is switched, the computer gets to attack the player
    determineAttackType(opponentMove, opponent);
  }

  public void runBattle(int selection) {
    //0 = choose move
  }

  public void determineOrder(Move playerMove, Move opponentMove) {
    //This code decides who goes first based on their speed
    if (playerSpeed > opponentSpeed) {
      //Code to say what the player used
      determineAttackType(playerMove, player);
      //Code to say if it was successful
      if (!battleEnd) {
        determineAttackType(opponentMove, opponent);
      } else {
        //Code here for opponent being defeated
      }
    } else if (playerSpeed < opponentSpeed) {
      determineAttackType(opponentMove, opponent);
      //Code to say if it was successful
      if (!battleEnd) {
        //Code to say what the player used
        determineAttackType(playerMove, player);
        //Code to say if it was successful
      } else {
        //Code for when the players lose
        //Spawn them in the guidance office ??
      }
    } else {
      //In case of a tie breaker
      int decision = (int)Math.floor(Math.random()*2);
      if (decision == 0) {
        //Code to say what the player uses
        determineAttackType(playerMove, player);
        //Code to say if it was successful
        if (!battleEnd) {
          //Code to say what the opponent uses
          determineAttackType(opponentMove, opponent);
          //Code to say if it was successful
        }
      } else {
        //Code to say what the opponent uses
        determineAttackType(opponentMove, opponent);
        //Code to say if it was successful
        if (!battleEnd) {
          //Code to say what the player uses
          determineAttackType(playerMove, player);
          //Code to say if it was successful
        } else {
          //Code for when the players lose
          //Spawn them in the guidance office ??
        }
      }
    }
  }

  public void determineAttackType(Move move, Character user) {
    int attacker = -1; //Needs to be initialized
    //This code determines who is attacking
    //0 is the player, 1 is the opponent
    if (user instanceof PlayableCharacter) {
      attacker = 0;
    } else if (user instanceof NonPlayableCharacter) {
      attacker = 1;
    }
    //This code determines what type of moves method should be used
    //The move itself has to be casted into the appropriate subclass
    if (move instanceof AttackMove) {
      attackMove((AttackMove) move, attacker);
    } else if (move instanceof HealthMove) {
      selfMove((HealthMove) move, attacker);
    } else if (move instanceof  StatChangeMove && move.getHitChance() == 2.0) {
      selfStatMove((StatChangeMove)move, attacker); //To be added
    } else if (move instanceof StatChangeMove) {
      statChangeMove((StatChangeMove) move, attacker);
    } else if (move instanceof StatusMove) {
      statusMove((StatusMove) move, attacker);
    }
  }

  public void attackMove (AttackMove move, int attacker) {
    int attackerStatUsed = 0; //Determines whether attack or intelligence is used
    int defence = 0; //Determines whose defence to use
    double multiplier; //Multiplier for typing and STAB
    //This if statement determines whose stats to used to calculate damage
    if (attacker == 0) {
      //If attacker is 0, then the player is attacking. If it is 1, the opponent is attacking
      if (move.getAttackType().equals("Attack")) {
        attackerStatUsed = playerAttack;
      } else {
        attackerStatUsed = playerIntelligence;
      }
      defence = opponentDefence;
    } else if (attacker == 1) {
      if (move.getAttackType().equals("Attack")) {
        attackerStatUsed = opponentAttack;
      } else {
        attackerStatUsed = opponentIntelligence;
      }
      defence = playerDefence;
    }
    multiplier = determineMultiplier(attacker, move);


    int damageDealt;
    damageDealt = (int)(Math.ceil((move.getPower() * (attackerStatUsed/defence))/10) * multiplier);

    //Add code to have more stuff when a student faints
    if (Math.random() < move.getHitChance()) {
      //Determines whether the attack hits or not
      if (attacker == 0) {
        if (opponentCurrentHealth - damageDealt < 0) {
          opponent.faintCharacter();
          this.battleEnd = true;
        } else {
          opponent.changeCurrentHealth(-damageDealt);
          opponentCurrentHealth = opponent.getCurrentHealth();
        }
      } else if (attacker == 1){
        if (playerCurrentHealth - damageDealt < 0) {
          player.faintCharacter();
          numberOfFaintedStudents++;
          if (numberOfFaintedStudents == partySize) {
            battleEnd = true;
          }
        } else {
          player.changeCurrentHealth(-damageDealt);
          playerCurrentHealth = player.getCurrentHealth();
        }
      }
    }

    //Additional effects to a move?
    //Add more code here I guess
  }

  public void statChangeMove(StatChangeMove move, int attacker) {
    //This code is for moves that only change the stats of the opposing person
    if (Math.random() < move.getHitChance()) {
      //Determines whether or not the attack lands
      if (attacker == 0) {
        if (move.getStatType().equals("Attack")) {
          opponentAttack = opponentAttack/move.getMultiplier();
        } else if (move.getStatType().equals("Intellgence")) {
          opponentIntelligence = opponentIntelligence/move.getMultiplier();
        } else if (move.getStatType().equals("Defence")) {
          opponentIntelligence = opponentIntelligence/move.getMultiplier();
        } else if (move.getStatType().equals("Speed")) {
          opponentSpeed = opponentSpeed/move.getMultiplier();
        }
      } else if (attacker == 1) {
        if (move.getStatType().equals("Attack")) {
          playerAttack = playerAttack/move.getMultiplier();
        } else if (move.getStatType().equals("Intellgence")) {
          playerIntelligence = playerIntelligence/move.getMultiplier();
        } else if (move.getStatType().equals("Defence")) {
          playerIntelligence = playerIntelligence/move.getMultiplier();
        } else if (move.getStatType().equals("Speed")) {
          playerSpeed = playerSpeed/move.getMultiplier();
        }
      }
    }
  }

  public void selfMove(StatChangeMove move, int attacker) {
    //Because the move is used on the user, the move never misses and does not need to check for hit chance
    if (attacker == 0) {
      switch (move.getStatType()) {
        case "Health":
          if (playerCurrentHealth + ((HealthMove) move).getHeal() > playerHealth) {
            player.resetCurrentHealth();
            playerCurrentHealth = playerHealth;
          } else {
            player.changeCurrentHealth(((HealthMove) move).getHeal()); //Cast the move into the health subclass
          }
          break;
        case "Attack":
          playerAttack = playerAttack / move.getMultiplier();
          break;
        case "Intellgence":
          playerIntelligence = playerIntelligence / move.getMultiplier();
          break;
        case "Defence":
          playerIntelligence = playerIntelligence / move.getMultiplier();
          break;
        case "Speed":
          playerSpeed = playerSpeed / move.getMultiplier();
          break;
      }
    } else if (attacker == 1) {
      switch (move.getStatType()) {
        case "Health":
          if (opponentCurrentHealth + ((HealthMove) move).getHeal() > opponentHealth) {
            opponent.resetCurrentHealth();
            opponentCurrentHealth = opponentHealth;
          } else {
            opponent.changeCurrentHealth(((HealthMove) move).getHeal()); //Cast the move into the health subclass
          }
          break;
        case "Attack":
          opponentAttack = opponentAttack / move.getMultiplier();
          break;
        case "Intellgence":
          opponentIntelligence = opponentIntelligence / move.getMultiplier();
          break;
        case "Defence":
          opponentIntelligence = opponentIntelligence / move.getMultiplier();
          break;
        case "Speed":
          opponentSpeed = opponentSpeed / move.getMultiplier();
          break;
      }
    }
  }

  public void statusMove(StatusMove move, int attacker) {
    //A move that changes the status of the move.
    if (Math.random() < move.getHitChance()) {
      //Checks hit chance
      if (attacker == 0) {
        opponentStatus = move.getStatusEffect();
      } else {
        player.setStatus(move.getStatusEffect());
        playerStatus = player.getStatus();
      }
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
    if (attacker == 0) {
      if (playerType.equals(move.getType())) {
        multiplier = multiplier * 1.5; //Same Type Attack Bonus (STAB)
      }
      if (playerStatus.equals("Burned")) {
        multiplier = multiplier * 0.5; //Burned halves damage
      }
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
    }
    if (attacker == 1) {
      if (opponentType.equals(move.getType())) {
        multiplier = multiplier * 1.5; //Same Type Attack Bonus (STAB)
      }
      if (opponentStatus.equals("Burned")) {
        multiplier = multiplier * 0.5; //Burn does half damage
      }
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
    }
    return multiplier;
  }

  public boolean isBattleEnd() {
    return battleEnd;
  }

  public void selfStatMove(StatChangeMove move, int attacker) {

  }
}
