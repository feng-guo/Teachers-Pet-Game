package items;

import java.util.ArrayList;

public class ListOfInventoryItems {
    private ArrayList<Item> itemList;

    public ListOfInventoryItems() {
        itemList = new ArrayList<Item>();
        //stat items.items - held
        itemList.add(new StatItem("Glasses", "Increases Intelligence by 10%","Held","Intelligence",1.1));
        itemList.add(new StatItem("Dumbbells", "Increases Attack by 10%","Held","Attack",1.1));
        itemList.add(new StatItem("Notes", "Increases Defense by 10%","Held","Defense",1.1));
        itemList.add(new StatItem("Calculator", "Increases Intelligence by 10%","Held","Intelligence",1.1));
        itemList.add(new StatItem("Starbucks Card", "Increases Health by 6% of Max Health per turn","Held", "Health", 1.06));
        //stat items.items - clothing
        itemList.add(new StatItem("Jordans", "Increases Speed by 10%","Shoes","Speed",1.1));
        itemList.add(new StatItem("Yeezys", "Increases Speed by 15%","Shoes","Speed",1.15));
        itemList.add(new StatItem("Headband", "Increases Intelligence by 10%","Hat","Intelligence",1.1));
        itemList.add(new StatItem("Headphones", "Increases Max Health by 10%","Hat","Health",1.1));
        itemList.add(new StatItem("Stussy Sweater", "Increases Attack by 20%","Shirt","Attack",1.2));
        itemList.add(new StatItem("Champion Sweater", "Increases Attack by 15%", "Shirt", "Attack", 1.15));
        itemList.add(new StatItem("Adidas Pants", "Increases Max Health by 5%", "Pants", "Health", 1.05));
        itemList.add(new StatItem("Sweatpants", "Increases Defense by 20/s%", "Pants", "Defense", 1.2));
        //heal items.items
        itemList.add(new HealItem("Caf Food", "Increases HP by 5","HP", 5));
        itemList.add(new HealItem("McDonald's Burger", "Increases HP by 20","HP", 20));
        itemList.add(new HealItem("Bubble Tea", "Increases HP by 25","HP", 25));
        itemList.add(new HealItem("Bulk Barn Candy", "Increases HP by 40","HP", 40));
        itemList.add(new HealItem("Red Bull", "Cures sleep status", "Sleep", 0));
        itemList.add(new HealItem("Ramen", "Cures burn status","Burn", 0));
        itemList.add(new HealItem("Doritos", "Cures poison status","Poison", 0));
        itemList.add(new HealItem("Tea", "Cures stun status","Stun", 0));
        itemList.add(new HealItem("Sushi", "Fully restores health and cures status","Full restore", 0));
        itemList.add(new HealItem("Chicken nuggets (20pc)", "Revives a pokemon with half their max health","Half revive", 0));
        itemList.add(new HealItem("Chicken nuggets (50pc)", "Revives a pokemon with their max health","Full revive", 0));
    }

    public Item retrieveItem(String name) {
        for (int i=0; i<itemList.size(); i++) {
            if (name.equals(itemList.get(i).getName())) {
                return itemList.get(i);
            }
        }
        return null;
    }
}
