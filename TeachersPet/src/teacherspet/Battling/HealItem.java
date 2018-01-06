class HealItem extends Item {
    private String type;
    private int change;

    HealItem(String name, String type, int change) {
        super(name);
        this.type = type;
        this.change = change;
    }

    public String getType() {
        return type;
    }
    public int getChange(){
        return change;
    }
}
