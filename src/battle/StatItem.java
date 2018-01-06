package battle;

class StatItem extends Item {
    private String type; //held item or clothing item
    private String statAffected;
    private double multiplier;

    StatItem(String name, String description, String type, String statAffected, double multiplier) {
        super(name, description);
        this.type = type;
        this.statAffected = statAffected;
        this.multiplier = multiplier;
    }

    public String getType(){
        return type;
    }

    public String getStatAffected(){
        return statAffected;
    }

    public double getMultiplier(){
        return multiplier;
    }
}
