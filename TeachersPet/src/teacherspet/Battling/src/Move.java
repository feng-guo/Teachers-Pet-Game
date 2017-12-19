package src;

abstract class Move {
    //Base stats for a move
    private int power; //Base power of a move
    private double hitChance;
    private int maxPowerPoints;
    private int priority;

    //Battling.Move attributes
    private String name;
    private String type;
    private String attackType;


    Move(String name, int power, double hitChance, String type, String attackType, int maxPowerPoints, int priority) {
        this.name = name;
        this.power = power;
        this.hitChance = hitChance;
        this.type = type;
        this.attackType = attackType;
        this.maxPowerPoints = maxPowerPoints;
        this.priority = priority;
    }

    public int getPower() {
        return power;
    }

    public double getHitChance() {
        return hitChance;
    }

    public int getPriority () {
        return priority;
    }

    public int getMaxPowerPoints() {
        return maxPowerPoints;
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
}
