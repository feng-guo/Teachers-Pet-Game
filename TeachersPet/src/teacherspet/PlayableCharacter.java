/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherspet;

class PlayableCharacter extends Character {
    String description;
    //Possibly dumb stats to display??
    PlayableCharacter(int health, int attack, int intelligence, int defence, )

    public void levelUp(int healthIncrease, int attackIncrease, int intelligenceIncrease, int defenceIncrease, int speedIncrease) {
        changeInitialHealth(healthIncrease);
        changeAttack(attackIncrease);
        changeIntelligence(intelligenceIncrease);
        changeDefence(defenceIncrease);
        changeSpeed(speedIncrease);
    }


}
