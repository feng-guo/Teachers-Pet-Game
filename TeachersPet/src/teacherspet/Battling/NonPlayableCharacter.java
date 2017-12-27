class NonPlayableCharacter extends Character {
  private String speech; //Something they say when they start a battle
  private boolean defeated; //Tells whether or not the character has been defeated
  private int experience; //Experience the user gains

  NonPlayableCharacter(int health, int attack, int intelligence, int defence, int speed, String type, String name, String speech, Move[] moveset, int experience, String ability) {
    super(health, attack, intelligence, defence, speed, type, name, moveset, ability);
    this.speech = speech;
    this.defeated = false;
    this.experience = experience;
  }

  public String getSpeech() {
    return speech;
  }


  public boolean getDefeated() {
    return defeated;
  }
  //Faints a character to defeat them
  public void faintCharacter() {
    defeated = true;
    setCurrentHealth(0);
  }

  //Experience
  public int getExperience() {
    return experience;
  }
}
