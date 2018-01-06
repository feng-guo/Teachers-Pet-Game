import java.util.ArrayList;

public class ListOfInventoryItems {
    private ArrayList<Item> itemList;

    ListOfInventoryItems() {
        itemList = new ArrayList<Item>();
        //stat item - held
        itemList.add(new StatItem("Glasses", "Held","Intelligence",1.1));
        itemList.add(new StatItem("Dumbbells", "Held","Attack",1.1));
        itemList.add(new StatItem("Notes", "Held","Defense",1.1));
        itemList.add(new StatItem("Calculator", "Held","Intelligence",1.1));
        itemList.add(new StatItem("Starbuck's card", "Held", "Health", 1.06));
        //stat item - clothing
        itemList.add(new StatItem("Jordans", "Clothing","Speed",1.1));
        itemList.add(new StatItem("Yeezys", "Clothing","Speed",1.15));
        itemList.add(new StatItem("Headband", "Clothing","Intelligence",1.1));
        itemList.add(new StatItem("Headphones", "Clothing","Health",1.1));
        itemList.add(new StatItem("Stussy Sweater", "Clothing","Attack",1.2));
        //heal item
        itemList.add(new HealItem("Caf Food", "HP", 5));
        itemList.add(new HealItem("McDonald's Burger", "HP", 20));
        itemList.add(new HealItem("Bubble Tea", "HP", 25));
        itemList.add(new HealItem("Bulk Barn Candy", "HP", 40));
        itemList.add(new HealItem("Tim Hortons Coffee", "PP", 10));
        itemList.add(new HealItem("Starbucks Frappuccino", "PP", 100)); //full restore of PP for a certain move
        itemList.add(new HealItem("Red bull", "Sleep", 0));
        itemList.add(new HealItem("Ramen", "Burn", 0));
        itemList.add(new HealItem("Doritos", "Poison", 0));
        itemList.add(new HealItem("Tea", "Stun", 0));
        itemList.add(new HealItem("Sushi", "Full restore", 0));
        itemList.add(new HealItem("Chicken nuggets (20)", "Half revive", 0));
        itemList.add(new HealItem("Chicken nuggets (50)", "Full revive", 0));
        //capture item
        itemList.add(new CaptureItem("Aced test", 0.8));
        itemList.add(new CaptureItem("Aced quiz", 0.5));
        itemList.add(new CaptureItem("\"Carry\" on project", 0.75));
    }
}
