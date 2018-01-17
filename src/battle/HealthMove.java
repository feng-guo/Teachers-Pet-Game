package battle;

public class HealthMove extends StatChangeMove {
    //This move only allows a user to heal themselves
    private int heal;
    private Move additionalEffect;

    HealthMove (String name, String type, int multiplier, String statType, int heal, int maxPowerPoints, int priority, Move additionalEffect) {
        super(name,1.0, type, multiplier, statType, maxPowerPoints, priority, "self");
        this.heal = heal;
        this.additionalEffect = additionalEffect;
    }

    public int getHeal() {
        return heal;
    }

    public Move getAdditionalEffect() {
        return additionalEffect;
    }
}
