class NonPlayableCharacter extends Character {
  private String speech;
  private boolean defeated; //Tells whether or not the character has been defeated

  NonPlayableCharacter(int health, int attack, int intelligence, int defence, int speed, String type, String name, String speech, Move[] moveset) {
    super(health, attack, intelligence, defence, speed, type, name, moveset);
    this.speech = speech;
    this.defeated = false;
  }

  public String getSpeech() {
    return speech;
  }


  public boolean getDefeated() {
    return defeated;
  }
  public void faintCharacter() {
    defeated = true;
    setCurrentHealth(0);
  }
}
