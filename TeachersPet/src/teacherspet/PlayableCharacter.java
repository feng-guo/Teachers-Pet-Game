/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherspet;

class PlayableCharacter extends Character {
    String description; //description of the character
    String status; //Status would be the status condition
    int experience; //EXP points
    //Possibly dumb stats to display??
    PlayableCharacter(int health, int attack, int intelligence, int defence, int speed, String type, String name, String description) {
      super(health, attack, intelligence, defence, speed, type, name);
      this.description = description;
      this.status = "";
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

    //Experience methods
    public int getExperience() {
      return experience;
    }
    public void changeExperience(int change) {
      experience += change;
    }
}
