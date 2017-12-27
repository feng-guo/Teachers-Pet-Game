class PlayableCharacter extends Character {
    private String description; //description of the character
    private String status; //Status would be the status condition
    private boolean fainted;
    private int experience; //EXP points
    private int level;
    //Possibly dumb stats to display??
    PlayableCharacter(int health, int attack, int intelligence, int defence, int speed, String type, String name, String description, Move[] moveset, String ability) {
      super(health, attack, intelligence, defence, speed, type, name, moveset, ability);
      this.description = description;
      this.status = "";
      this.fainted = false;
      this.level = 1;
    }

    public void levelUp(int healthIncrease, int attackIncrease, int intelligenceIncrease, int defenceIncrease, int speedIncrease) {
        changeInitialHealth(healthIncrease);
        changeAttack(attackIncrease);
        changeIntelligence(intelligenceIncrease);
        changeDefence(defenceIncrease);
        changeSpeed(speedIncrease);
    }

    //Status methods
    public String getStatus() {
      return status;
    }
    public void setStatus(String status) {
      this.status = status;
    }
    public void resetStatus() {
      status = "";
    }
    public void faintCharacter() {
        fainted = true;
        setCurrentHealth(0);
    }

    public boolean isFainted() {
        return fainted;
    }

    //Experience methods
    public int getExperience() {
      return experience;
    }
    public void changeExperience(int change) {
      experience += change;
      while (experience > 20*level) {
          experience -= 20*level;
          level++;
      }
    }
}
