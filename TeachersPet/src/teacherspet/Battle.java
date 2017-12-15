//Author @Feng

class Battle extends Interaction {
  //Player Stats
  int playerHealth;
  int playerCurrentHealth;
  int playerAttack;
  int playerIntelligence;
  int playerDefence;
  int playerSpeed;
  String playerName;
  String playerType;

  //NPC Stats
  int opponentHealth;
  int opponentCurrentHealth;
  int opponentAttack;
  int opponentIntelligence;
  int opponentDefence;
  int opponentSpeed;
  String opponentName;
  String opponentType;

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

  public void determineOrder() {
    if (playerSpeed > opponentSpeed) {
      //Run player code
      if (!battleEnd)
    }
  }

}
