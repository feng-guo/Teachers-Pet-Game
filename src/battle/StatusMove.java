package battle;

public class StatusMove extends Move {
    String statusEffect; //Sets the status of the person affected
    String target;
    StatusMove (String name, double hitChance, String type, String statusEffect, int maxPowerPoints, int priority, String target) {
        super(name, 0, hitChance, type, "none", maxPowerPoints, priority);
        this.statusEffect = statusEffect;
        this.target = target;
    }

    public String getStatusEffect() {
        return statusEffect;
    }

    public String getTarget() {
        return target;
    }
}
