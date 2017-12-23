//Author @Feng
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
  private String playerStatus; //Has one status to prevent too many statuses
  private String playerAbility;
  private int playerStatBoost; //Sets a maximum as to how much all stats of a character can be boosted (up to 4 times)
  private int playerStatusTurns; //The amount of turns that a pokemon has a set status
  private boolean playerProtected; //Determines if the player has shielded a move
  private boolean playerFainted;

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
  private String opponentAbility;
  private int opponentStatBoost;
  private int opponentStatusTurns;
  private boolean opponentProtected;
  private boolean opponentFainted;

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
    this.playerStatus = player.getStatus();
    this.playerAbility = player.getAbility();
    this.playerStatBoost = 0;
    this.playerProtected = false;
    this.playerFainted = player.isFainted();

    this.opponentHealth = opponent.getInitialHealth();
    this.opponentCurrentHealth = opponent.getCurrentHealth();
    this.opponentAttack = opponent.getAttack();
    this.opponentIntelligence = opponent.getIntelligence();
    this.opponentDefence = opponent.getDefence();
    this.opponentSpeed = opponent.getSpeed();
    this.opponentName = opponent.getName();
    this.opponentType = opponent.getType();
    this.opponentStatus = "";
    this.opponentAbility = opponent.getAbility();
    this.opponentStatBoost = 0;
    this.opponentStatusTurns = 0;
    this.opponentProtected = false;
    this.opponentFainted = false;

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
    this.playerStatus = player.getStatus();
    this.playerStatBoost = 0;

    //Code for switch in animation goes here
    //Because the player is switched, the computer gets to attack the player
    determineAttackType(opponentMove, opponent);
  }

  public void runBattle(int selection) {
    //0 = choose move
  }

  public void determineOrder(Move playerMove, Move opponentMove) {
    int tempPlayerSpeed = playerSpeed;
    int tempOpponentSpeed = opponentSpeed;
    if (playerStatus.equals("Slowed")) {
      tempPlayerSpeed = playerSpeed/2;
    } else if (opponentStatus.equals("Slowed")) {
      tempOpponentSpeed = opponentSpeed/2;
    }
    //This code decides who goes first based on their speed
    if (playerMove.getPriority() > opponentMove.getPriority()) {
      //If the player has a higher priority move
      determineAttackType(playerMove, player);
      if (!battleEnd && !playerProtected) {
        determineAttackType(opponentMove, opponent);
      } else if (playerProtected && !battleEnd) {
        //Player protected
      } else {
        //The opponent was defeated
      }
    } else if (playerMove.getPriority() < opponentMove.getPriority()) {
      //If the opponent has a higher priority move
      if (!battleEnd && !opponentProtected) {
        determineAttackType(playerMove, player);
      } else if (opponentProtected && !playerFainted) {
        //Player protected
      } else {
        //The player fainted
      }
    } else if (tempPlayerSpeed > tempOpponentSpeed) {
      //Code to say what the player used
      determineAttackType(playerMove, player);
      //Code to say if it was successful
      if (!battleEnd) {
        determineAttackType(opponentMove, opponent);
      } else {
        //Code here for opponent being defeated
      }
    } else if (tempPlayerSpeed < tempOpponentSpeed) {
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
        //Whenever the target is the user, the attacker uses the move on themself and therefore the attacker would be "technically" the other person
        statChangeMove((StatChangeMove)move, -attacker);
      } else {
        statChangeMove((StatChangeMove) move, attacker);
      }
    } else if (move instanceof StatusMove) {
      if (((StatusMove)move).getTarget().equals("Self")) {
        statusMove((StatusMove) move, -attacker);
      } else {
        statusMove((StatusMove)move, attacker);
      }
    }
  }

  public void attackMove (AttackMove move, int attacker) {
    int attackerStatUsed = 0; //Determines whether attack or intelligence is used
    int defence = 0; //Determines whose defence to use
    double multiplier; //Multiplier for typing, status effects and STAB
    //This if statement determines whose stats to used to calculate damage
    if (attacker == -1) {
      //If attacker is -1, then the player is attacking. If it is 1, the opponent is attacking
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
    damageDealt = (int)(Math.ceil((move.getPower() * (attackerStatUsed/defence+1))/10) * multiplier);
    if (player.getAbility().equals("Clown") && attacker == 1) {
      if (Math.random() < 0.25) {
        damageDealt = damageDealt/2;
      }
    } else if (opponent.getAbility().equals("Clown") && attacker == -1) {
      if (Math.random() < 0.25) {
        damageDealt = damageDealt/2;
      }
    }

    //Add code to have more stuff when a student faints
    if (Math.random() < move.getHitChance()) {
      //Determines whether the attack hits or not
      if (attacker == -1) {
        if (opponentCurrentHealth - damageDealt < 0) {
          opponent.faintCharacter();
          opponentLoses = true;
          this.battleEnd = true;
        } else {
          opponent.changeCurrentHealth(-damageDealt);
          opponentCurrentHealth = opponent.getCurrentHealth();
          if (opponentCurrentHealth < opponentHealth/4) {
            switch (opponentAbility) {
              case "Persistent":
                opponentDefence = opponentDefence*2;
                break;
              case "Distressed":
                opponentIntelligence = opponentIntelligence*2;
                break;
            }
          }
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
          if (playerCurrentHealth < playerHealth/4) {
            switch (playerAbility) {
              case "Persistent":
                playerDefence = playerDefence*2;
                break;
              case "Distressed":
                playerIntelligence = playerIntelligence*2;
                break;
            }
          }
        }
      }
    } else {
      //The move missed
    }

    if (Math.random() < move.getHitChance()/10) {
      //The additional effect is always 10 percent of the original hit chance
      if (move.getAdditionalEffect() != null) {
        Move additionalEffect = move.getAdditionalEffect();
        if (additionalEffect instanceof HealthMove) {
          healthMove((HealthMove) additionalEffect, attacker);
        } else if (additionalEffect instanceof StatChangeMove) {
          if (((StatChangeMove) additionalEffect).getTarget().equals("Self")) {
            statChangeMove((StatChangeMove) additionalEffect, -attacker);
          } else {
            statChangeMove((StatChangeMove) additionalEffect, attacker);
          }
        } else if (additionalEffect instanceof StatusMove) {
          if (((StatusMove) additionalEffect).getTarget().equals("Self")) {
            statusMove((StatusMove) additionalEffect, -attacker);
          } else {
            statusMove((StatusMove) additionalEffect, attacker);
          }
        }
      }
    }
  }

  public void protectMove (ProtectMove move, int attacker) {
    if (Math.random() < move.getHitChance()) {
      if (attacker == -1) {
        playerProtected = true;
      } else if (attacker == 1) {
        opponentProtected = true;
      }
    }
  }

  public void statChangeMove(StatChangeMove move, int attacker) {
    //This code is for moves that only change the stats of the opposing person
    if (Math.random() < move.getHitChance()) {
      //Determines whether or not the attack lands
      if (attacker == -1) {
        if (opponentStatBoost>-5) {
          if (move.getStatType().equals("Attack")) {
            opponentAttack = opponentAttack / move.getMultiplier();
          } else if (move.getStatType().equals("Intellgence")) {
            opponentIntelligence = opponentIntelligence / move.getMultiplier();
          } else if (move.getStatType().equals("Defence")) {
            opponentIntelligence = opponentIntelligence / move.getMultiplier();
          } else if (move.getStatType().equals("Speed")) {
            opponentSpeed = opponentSpeed / move.getMultiplier();
          }
          opponentStatBoost--;
        } else {
          //Stat cannot be lowered
        }
      } else if (attacker == 1) {
        if (playerStatBoost>-5) {
          if (move.getStatType().equals("Attack")) {
            playerAttack = playerAttack / move.getMultiplier();
          } else if (move.getStatType().equals("Intellgence")) {
            playerIntelligence = playerIntelligence / move.getMultiplier();
          } else if (move.getStatType().equals("Defence")) {
            playerIntelligence = playerIntelligence / move.getMultiplier();
          } else if (move.getStatType().equals("Speed")) {
            playerSpeed = playerSpeed / move.getMultiplier();
          }
          playerStatBoost--;
        } else {
          //Stat cannot be lowered
        }
      }
    } else {
      //The move missed
    }
  }

  public void healthMove(HealthMove move, int attacker) {
    //Doesn't check hit chance because self healing is 100% successful
    if (attacker == -1) {
      if (playerCurrentHealth + move.getHeal() > playerHealth) {
        player.resetCurrentHealth();
        playerCurrentHealth = player.getCurrentHealth();
      } else {
        player.changeCurrentHealth(move.getHeal());
        playerCurrentHealth = player.getCurrentHealth();
      }
    } else if (attacker == 1) {
      if (opponentCurrentHealth + move.getHeal() > opponentHealth) {
        opponent.resetCurrentHealth();
        opponentCurrentHealth = opponent.getCurrentHealth();
      } else {
        opponent.changeCurrentHealth(move.getHeal());
        opponentCurrentHealth = opponent.getCurrentHealth();
      }
    }
  }

  public void statusMove(StatusMove move, int attacker) {
    //A move that changes the status of the move.
    if (Math.random() < move.getHitChance()) {
      //Checks hit chance
      if (attacker == -1) {
        opponentStatus = move.getStatusEffect();
      } else if (attacker == 1){
        player.setStatus(move.getStatusEffect());
        playerStatus = player.getStatus();
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
      if (playerType.equals(move.getType())) {
        multiplier = multiplier * 1.5; //Same Type Attack Bonus (STAB)
      }
      if (playerStatus.equals("Burned")) {
        multiplier = multiplier * 0.5; //Burned halves damage
      }
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

  public void burnPlayer(Character person) {
    double burn;
    if (person instanceof PlayableCharacter) {
      burn = playerHealth/12;
      if (playerCurrentHealth - (int)burn < 0) {
        player.faintCharacter();
        playerFainted = true;
        numberOfFaintedStudents++;
        if (numberOfFaintedStudents == partySize) {
          battleEnd = true;
        }
      } else {
        person.changeCurrentHealth(-(int)burn);
        playerCurrentHealth = player.getCurrentHealth();
      }
    } else if (person instanceof NonPlayableCharacter) {
      burn = opponentHealth/12;
      if (opponentCurrentHealth - (int)burn < 0) {
        opponent.faintCharacter();
        opponentLoses = true;
        battleEnd = true;
      } else {
        person.changeCurrentHealth(-(int)burn);
        opponentCurrentHealth = opponent.getCurrentHealth();
      }
    }
  }

  public void poisonPlayer(Character person) {
    double poison;
    if (person instanceof PlayableCharacter) {
      poison = playerHealth/15 * playerStatusTurns;
      if (playerCurrentHealth - (int)poison < 0) {
        player.faintCharacter();
        playerFainted = true;
        numberOfFaintedStudents++;
        if (numberOfFaintedStudents == partySize) {
          battleEnd = true;
        }
      } else {
        person.changeCurrentHealth(-(int)poison);
        playerCurrentHealth = player.getCurrentHealth();
      }
    } else if (person instanceof NonPlayableCharacter) {
      poison = opponentHealth/15 * opponentStatusTurns;
      if (opponentCurrentHealth - (int)poison < 0) {
        opponent.faintCharacter();
        opponentLoses = true;
        battleEnd = true;
      } else {
        person.changeCurrentHealth(-(int)poison);
        opponentCurrentHealth = opponent.getCurrentHealth();
      }
    }
  }

  public void wakeUp(Character person) {
    if (person instanceof PlayableCharacter) {
      playerStatus = "";
      playerStatusTurns = 0;
    } else if (person instanceof  NonPlayableCharacter) {
      opponentStatus = "";
      opponentStatusTurns = 0;
    }
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
