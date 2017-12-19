package src;

class StatChangeMove extends Move {
    private int multiplier; //Changes how much a stat changes by
    private String statType;

    StatChangeMove (String name, double hitChance, String type, int multiplier, String statType, int maxPowerPoints, int priority) {
        super(name, 0, hitChance, type, "none", maxPowerPoints, priority);
        this.multiplier = multiplier;
        this.statType = statType;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public String getStatType() {
        return statType;
    }
}
