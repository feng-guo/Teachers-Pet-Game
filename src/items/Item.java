package items;

public abstract class Item {
    private String name;
    private String description;
    private String itemType;

    Item (String name, String description, String type) {
        this.name = name;
        this.description = description;
        this.itemType = type;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getItemType() {
        return itemType;
    }
}
