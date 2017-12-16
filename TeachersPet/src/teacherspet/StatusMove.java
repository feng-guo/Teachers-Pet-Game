class StatusMove extends Move {
    String statusEffect;
    StatusMove (String name, double hitChance, String type, String statusEffect) {
        super(name, 0, hitChance, type, "none");
        this.statusEffect = statusEffect;
    }

    public String getStatusEffect() {
        return statusEffect;
    }
}
