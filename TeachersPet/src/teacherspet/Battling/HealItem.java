class HealItem extends Item {
    private int change;

    HealItem(String name, int change) {
        super(name);
        this.change = change;
    }

    public int getChange(){return change;}
}
