<<<<<<< HEAD:TeachersPet/src/teacherspet/Battling/AttackMove.java
package Battling;
=======
package battleFiles;
>>>>>>> 8a59da7531493e8d36c42b5ceaaf5dcb3db6531a:TeachersPet/src/teacherspet/Battling/battleFiles/AttackMove.java

class AttackMove extends Move {
    //This class of moves are for attacks that do damage
    //Additional effects??
    //battleFiles.StatChangeMove against the other player?
    Move additionalEffect;


    AttackMove(String name, int power, double hitChance, String type, String attackType, int maxPowerPoints, int priority, Move move) {
        super(name, power, hitChance, type, attackType, maxPowerPoints, priority);
        if (move != null) {
            this.additionalEffect = move;
        }
    }

    public Move getAdditionalEffect() {
        return additionalEffect;
    }
}
