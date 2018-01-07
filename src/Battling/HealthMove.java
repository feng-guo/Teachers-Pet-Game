package Battling;

class HealthMove extends StatChangeMove {
    //This move only allows a user to heal themselves
    private int heal;

    HealthMove (String name, String type, int multiplier, String statType, int heal, int maxPowerPoints) {
        super(name,1.0, type, multiplier, statType, maxPowerPoints);
        this.heal = heal;
    }

    public int getHeal() {
        return heal;
    }
}
