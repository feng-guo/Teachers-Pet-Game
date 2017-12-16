class StatChangeMove extends Move {
    private int multiplier;
    private String statType;

    StatChangeMove (String name, double hitChance, String type, int multiplier, String statType) {
        super(name, 0, hitChance, type, "none");
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
