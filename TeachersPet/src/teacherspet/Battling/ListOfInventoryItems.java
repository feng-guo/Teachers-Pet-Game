import java.util.ArrayList;

public class ListOfInventoryItems {
    private ArrayList<Item> itemList;

    ListOfInventoryItems() {
        itemList = new ArrayList<Item>();
        //stat item - held
        itemList.add(new StatItem("Glasses", "Held","Intelligence",0.1));
        itemList.add(new StatItem("Dumbbells", "Held","Attack",0.1));
        itemList.add(new StatItem("Notes", "Held","Defense",0.1));
        itemList.add(new StatItem("Calculator", "Held","Intelligence",0.1));
        //stat item - clothing
        itemList.add(new StatItem("Jordans", "Clothing","Speed",0.1));
        itemList.add(new StatItem("Yeezys", "Clothing","Speed",0.15));
        itemList.add(new StatItem("Headband", "Clothing","Intelligence",0.1));
        itemList.add(new StatItem("Headphones", "Clothing","Health",0.1));
        itemList.add(new StatItem("Stussy Sweater", "Clothing","Attack",0.2));
        //heal item
        itemList.add(new HealItem("Caf Food", 5));
        itemList.add(new HealItem("Tim Hortons Coffee", 10));
        itemList.add(new HealItem("McDonald's Burger", 20));
        itemList.add(new HealItem("Bubble Tea", 25));
        itemList.add(new HealItem("Starbucks Frappuccino", 30));
        itemList.add(new HealItem("Bulk Barn Candy", 40));
        itemList.add(new HealItem("Ramen", 50));
        //capture item
        itemList.add(new CaptureItem("Aced test", 0.8));
        itemList.add(new CaptureItem("Aced quiz", 0.5));
        itemList.add(new CaptureItem("\"Carry\" on project", 0.75));
    }
}
