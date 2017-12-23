class AttackMove extends Move {
    //This class of moves are for attacks that do damage
    //Additional effects??
    //StatChangeMove against the other player?
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
