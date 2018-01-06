package battle;

class ProtectMove extends Move {
    ProtectMove(String name, double hitChance, String type, int maxPowerPoints, int priority) {
        super(name, 0, hitChance, type, "None", maxPowerPoints, priority);
    }
}
