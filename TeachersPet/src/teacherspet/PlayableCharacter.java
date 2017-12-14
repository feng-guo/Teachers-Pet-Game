/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teacherspet;

/**
 *
 * @author sihan
 */
class PlayableCharacter extends Character {
    String description; 
    //Possibly dumb stats to display??
    
    public void levelUp(int changeHealth, int changeAttack, int changeIntelligence, int changeDefence, int changeSpeed) {
        changeInitialHealth(changeHealth);
        changeAttack(changeAttack);
        changeIntelligence(intelligenceIncrease)
    }
    
    
}
