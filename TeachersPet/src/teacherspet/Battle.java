//Author @Feng
/* Battle classes are an object itself
 * Runs a loop in battle itself (To be added)
 * Need to add switching, inventory, fleeing, and move selection
 */

class Battle /*extends Interaction*/ {
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
  private String playerName;
  private String playerType;
  private String playerStatus;

  //NPC Stats
  private int opponentHealth;
  private int opponentCurrentHealth;
  private int opponentAttack;
  private int opponentIntelligence;
  private int opponentDefence;
  private int opponentSpeed;
  private String opponentName;
  private String opponentType;
  private String opponentStatus;

  //Battle variables
  private int partySize; //Size of the player party
  private int numberOfFaintedStudents; //Once this number is equal to the party size, the game is over
  private boolean battleEnd;

  Battle (PlayableCharacter player, NonPlayableCharacter opponent, int partySize, int numberOfFaintedStudents) {
    this.player = player;
    this.opponent = opponent;
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
  }

  public void changeCharacter(PlayableCharacter player) {
    this.player = player;
    //For switching out students
    this.playerHealth = player.getInitialHealth();
    this.playerCurrentHealth = player.getCurrentHealth();
    this.playerAttack = player.getAttack();
    this.playerIntelligence = player.getIntelligence();
    this.playerDefence = player.getDefence();
    this.playerSpeed = player.getSpeed();
    this.playerName = player.getName();
    this.playerType = player.getType();

    //Runs the opponent attack code here
    //opponent move
  }

  public void determineOrder(Move playerMove, Move opponentMove) {
    if (playerSpeed > opponentSpeed) {
      determineAttackType(playerMove, player);
      if (!battleEnd) {
        determineAttackType(opponentMove, opponent);
      }
    } else if (playerSpeed < opponentSpeed) {
      determineAttackType(opponentMove, opponent);
      if (!battleEnd) {
        determineAttackType(playerMove, player);
      }
    } else {
      int decision = (int)Math.floor(Math.random()*2);
      if (decision == 0) {
        determineAttackType(playerMove, player);
        if (!battleEnd) {
          determineAttackType(opponentMove, opponent);
        }
      } else {
        determineAttackType(opponentMove, opponent);
        if (!battleEnd) {
          determineAttackType(playerMove, player);
        }
      }
    }
  }

  public void determineAttackType(Move move, Character user) {
    int attacker = -1;
    if (user instanceof PlayableCharacter) {
      attacker = 0;
    } else if (user instanceof NonPlayableCharacter) {
      attacker = 1;
    }
    if (move instanceof AttackMove) {
      attackMove((AttackMove) move, attacker);
    } else if (move instanceof HealthMove) {
      selfMove((HealthMove) move, attacker);
    } else if (move instanceof StatChangeMove) {
      statChangeMove((StatChangeMove) move, attacker);
    } else if (move instanceof StatusMove) {
      statusMove((StatusMove) move, attacker);
    }
  }

  public void attackMove (AttackMove move, int attacker) {
    int attackerStatUsed = 0; //Determines whether attack or intelligence is used
    int defence = 0; //Determines whose defence to use
    double multiplier; //Multiplier for typing
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

    //Add code to have more stuff when a pokemon faints
    if (Math.random() < move.getHitChance()) {
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
  }

  public void statChangeMove(StatChangeMove move, int attacker) {
    if (Math.random() < move.getHitChance()) {
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
    if (attacker == 0) {
      if (move.getStatType().equals("Health")) {
        if (playerCurrentHealth + ((HealthMove)move).getHeal() > playerHealth) {
          player.resetCurrentHealth();
          playerCurrentHealth = playerHealth;
        } else {
          player.changeCurrentHealth(((HealthMove)move).getHeal()); //Cast the move into the health subclass
        }
      } else if (move.getStatType().equals("Attack")) {
        playerAttack = playerAttack/move.getMultiplier();
      } else if (move.getStatType().equals("Intellgence")) {
        playerIntelligence = playerIntelligence/move.getMultiplier();
      } else if (move.getStatType().equals("Defence")) {
        playerIntelligence = playerIntelligence/move.getMultiplier();
      } else if (move.getStatType().equals("Speed")) {
        playerSpeed = playerSpeed/move.getMultiplier();
      }
    } else if (attacker == 1) {
      if (move.getStatType().equals("Health")) {
        if (opponentCurrentHealth + ((HealthMove)move).getHeal() > opponentHealth) {
          opponent.resetCurrentHealth();
          opponentCurrentHealth = opponentHealth;
        } else {
          opponent.changeCurrentHealth(((HealthMove)move).getHeal()); //Cast the move into the health subclass
        }
      } else if (move.getStatType().equals("Attack")) {
        opponentAttack = opponentAttack/move.getMultiplier();
      } else if (move.getStatType().equals("Intellgence")) {
        opponentIntelligence = opponentIntelligence/move.getMultiplier();
      } else if (move.getStatType().equals("Defence")) {
        opponentIntelligence = opponentIntelligence/move.getMultiplier();
      } else if (move.getStatType().equals("Speed")) {
        opponentSpeed = opponentSpeed/move.getMultiplier();
      }
    }
  }

  public void statusMove(StatusMove move, int attacker) {
    //A move that changes the status of the move. Does not currently implement hit chance
    if (Math.random() < move.getHitChance()) {
      if (attacker == 0) {
        opponentStatus = move.getStatusEffect();
      } else {
        player.setStatus(move.getStatusEffect());
        playerStatus = player.getStatus();
      }
    }
  }

  public double determineMultiplier(int attacker, AttackMove move) {
    double multiplier = 1;
    if (attacker == 0) {
      if (opponentType.equals("Math")) {
        if (move.getType().equals("Science")) {
          multiplier = multiplier*2;
        } else if (move.getType().equals("English")) {
          multiplier = multiplier/2;
        }
      } else if (opponentType.equals("Science")) {
        if (move.getType().equals("Technology")) {
          multiplier = multiplier*2;
        } else if (move.getType().equals("Math")) {
          multiplier = multiplier/2;
        }
      } else if (opponentType.equals("Technology")) {
        if (move.getType().equals("English")) {
          multiplier = multiplier*2;
        } else if (move.getType().equals("Science")) {
          multiplier = multiplier/2;
        }
      } else if (opponentType.equals("English")) {
        if (move.getType().equals("Math")) {
          multiplier = multiplier*2;
        } else if (move.getType().equals("Technology")) {
          multiplier = multiplier/2;
        }
      }
    }
    if (attacker == 1) {
      if (playerType.equals("Math")) {
        if (move.getType().equals("Science")) {
          multiplier = multiplier*2;
        } else if (move.getType().equals("English")) {
          multiplier = multiplier/2;
        }
      } else if (playerType.equals("Science")) {
        if (move.getType().equals("Technology")) {
          multiplier = multiplier*2;
        } else if (move.getType().equals("Math")) {
          multiplier = multiplier/2;
        }
      } else if (playerType.equals("Technology")) {
        if (move.getType().equals("English")) {
          multiplier = multiplier*2;
        } else if (move.getType().equals("Science")) {
          multiplier = multiplier/2;
        }
      } else if (playerType.equals("English")) {
        if (move.getType().equals("Math")) {
          multiplier = multiplier*2;
        } else if (move.getType().equals("Technology")) {
          multiplier = multiplier/2;
        }
      }
    }
    return multiplier;
  }

  public boolean isBattleEnd() {
    return battleEnd;
  }
}
