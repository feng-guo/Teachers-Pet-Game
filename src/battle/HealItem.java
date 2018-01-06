package battle;

class HealItem extends Item {
    private String type;
    private int change;

    HealItem(String name, String description, String type, int change) {
        super(name,description);
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
