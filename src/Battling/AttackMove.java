package Battling;

class AttackMove extends Move {
    //This class of moves are for attacks that do damage
    AttackMove(String name, int power, double hitChance, String type, String attackType, int maxPowerPoints) {
        super(name, power, hitChance, type, attackType, maxPowerPoints);
    }
}
