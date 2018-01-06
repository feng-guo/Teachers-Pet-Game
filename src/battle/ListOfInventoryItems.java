package battle;

import java.util.ArrayList;

public class ListOfInventoryItems {
    private ArrayList<Item> itemList;

    ListOfInventoryItems() {
        itemList = new ArrayList<Item>();
        //stat item - held
        itemList.add(new StatItem("Glasses", "Increases Intelligence by 10%","Held","Intelligence",1.1));
        itemList.add(new StatItem("Dumbbells", "Increases Attack by 10%","Held","Attack",1.1));
        itemList.add(new StatItem("Notes", "Increases Defense by 10%","Held","Defense",1.1));
        itemList.add(new StatItem("Calculator", "Increases Intelligence by 10%","Held","Intelligence",1.1));
        itemList.add(new StatItem("Starbucks Card", "Increases Health by 6% of Max Health per turn","Held", "Health", 1.06));
        //stat item - clothing
        itemList.add(new StatItem("Jordans", "Increases Speed by 10%","Clothing","Speed",1.1));
        itemList.add(new StatItem("Yeezys", "Increases Speed by 15%","Clothing","Speed",1.15));
        itemList.add(new StatItem("Headband", "Increases Intelligence by 10%","Clothing","Intelligence",1.1));
        itemList.add(new StatItem("Headphones", "Increases Max Health by 10%","Clothing","Health",1.1));
        itemList.add(new StatItem("Stussy Sweater", "Increases Attack by 20%","Clothing","Attack",1.2));
        //heal item
        itemList.add(new HealItem("Caf Food", "Increases HP by 5","HP", 5));
        itemList.add(new HealItem("McDonald's Burger", "Increases HP by 20","HP", 20));
        itemList.add(new HealItem("Bubble Tea", "Increases HP by 25","HP", 25));
        itemList.add(new HealItem("Bulk Barn Candy", "Increases HP by 40","HP", 40));
        itemList.add(new HealItem("Tim Hortons Coffee", "Increases PP for one  move by 10","PP", 10));
        itemList.add(new HealItem("Starbucks Frappuccino", "Fully restores PP for one move","PP", 100)); //full restore of PP for a certain move
        itemList.add(new HealItem("Red bull", "Cures sleep status", "Sleep", 0));
        itemList.add(new HealItem("Ramen", "Cures burn status","Burn", 0));
        itemList.add(new HealItem("Doritos", "Cures poison status","Poison", 0));
        itemList.add(new HealItem("Tea", "Cures stun status","Stun", 0));
        itemList.add(new HealItem("Sushi", "Fully restores health and cures status","Full restore", 0));
        itemList.add(new HealItem("Chicken nuggets (20)", "Revives a pokemon with half their max health","Half revive", 0));
        itemList.add(new HealItem("Chicken nuggets (50)", "Revives a pokemon with their max health","Full revive", 0));
        //capture item
        itemList.add(new CaptureItem("Aced test", "80% chance of attracting a playable character onto your team",0.8));
        itemList.add(new CaptureItem("Aced quiz", "50% chance of attracting a playable character onto your team",0.5));
        itemList.add(new CaptureItem("\"Carry\" on project", "75% chance of attracting a playable character onto your team",0.75));
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
