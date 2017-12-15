class NonPlayableCharacter extends Character {
  String speech;
  boolean defeated; //Tells whether or not the character has been defeated

  NonPlayableCharacter(int health, int attack, int intelligence, int defence, int speed, String type, String name, String speech) {
    super(health, attack, intelligence, defence, speed, type, name);
    this.speech = speech;
    this.defeated = false;
  }

  public String getSpeech() {
    return speech;
  }

  public boolean getDefeated() {
    return defeated;
  }
  public void setDefeated(boolean defeated) {
    this.defeated = defeated;
  }
}
