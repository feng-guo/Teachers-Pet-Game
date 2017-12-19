package src;

class StatusMove extends Move {
    String statusEffect; //Sets the status of the person affected
    StatusMove (String name, double hitChance, String type, String statusEffect, int maxPowerPoints, int priority) {
        super(name, 0, hitChance, type, "none", maxPowerPoints, priority);
        this.statusEffect = statusEffect;
    }

    public String getStatusEffect() {
        return statusEffect;
    }
}
