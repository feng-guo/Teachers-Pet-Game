class StatItem extends Item {
    private String type;
    private String statAffected;
    private int change;

    StatItem(String name, String type, String statAffected, int change) {
        super(name);
        this.type = type;
        this.statAffected = statAffected;
        this.change = change;
    }

    public String getType(){return type;}

    public String getStatAffected(){return statAffected;}

    public int getChange(){return change;}
}
