package Battling;

abstract class Move {
    //Base stats for a move
    private int power; //Base power of a move
    private double hitChance;
    private int maxPowerPoints;

    //Battling.Move attributes
    private String name;
    private String type;
    private String attackType;


    Move(String name, int power, double hitChance, String type, String attackType, int maxPowerPoints) {
        this.name = name;
        this.power = power;
        this.hitChance = hitChance;
        this.type = type;
        this.attackType = attackType;
        this.maxPowerPoints = maxPowerPoints;
    }

    public int getPower() {
        return power;
    }

    public double getHitChance() {
        return hitChance;
    }

    public String getName() {
        return name;
    }

    public String getAttackType() {
        return attackType;
    }

    public String getType() {
        return type;
    }

    public int getMaxPowerPoints() {
        return maxPowerPoints;
    }
}
