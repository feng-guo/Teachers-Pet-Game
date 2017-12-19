class AttackMove extends Move {
    //This class of moves are for attacks that do damage
    //Additional effects??
    //StatChangeMove against the other player?
    AttackMove(String name, int power, double hitChance, String type, String attackType, int maxPowerPoints, int priority) {
        super(name, power, hitChance, type, attackType, maxPowerPoints, priority);
    }
}
