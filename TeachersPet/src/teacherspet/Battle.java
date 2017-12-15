//Author @Feng

class Battle extends Interaction {
  PlayableCharacter player;
  NonPlayableCharacter opponent;

  //Player Stats
  int playerHealth;
  int playerCurrentHealth;
  int playerAttack;
  int playerIntelligence;
  int playerDefence;
  int playerSpeed;
  String playerName;
  String playerType;
  String status;

  //NPC Stats
  int opponentHealth;
  int opponentCurrentHealth;
  int opponentAttack;
  int opponentIntelligence;
  int opponentDefence;
  int opponentSpeed;
  String opponentName;
  String opponentType;
  String status;

  //Battle variables
  int partySize; //Size of the player party
  int numberOfFaintedStudents;
  boolean battleEnd;

  Battle (PlayableCharacter player, NonPlayableCharacter opponent, int partySize) {
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

    //
  }

  public void changeCharacter(PlayableCharacter player) {
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

  public int determineOrder() {
    if (playerSpeed > opponentSpeed) {
      return 1;
    } else if (playerSpeed < opponentSpeed) {
      return -1;
    } else {
      int decision = (int)Math.floor(Math.rand()*2);
      if (decision == 0) {
        return 1;
      } else {
        return -1;
      }
    }
  }

  public void determineAttackType(Move move, PlayableCharacter player) {
    if (move instanceof attack) {
      attackMove(move, player);
    } else if (move instanceof Self) {
      selfMove(move, player);
    } else if (move instanceof StatChange) {
      statChangeMove(move, player);
    } else if (move instanceof Status) {
      statusMove(move, player);
    }
  }

  public void attackMove (Move move, PlayableCharacter player, int attacker) {
    int statUsed;
    if (attacker == 0) {
      //If attacker is 0, then the player is attacking. If it is 1, the opponent is attacking
      
    } else if (attacker == 1) {

    }
  }

}
