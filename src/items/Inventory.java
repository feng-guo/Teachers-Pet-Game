package items;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Item> inventory;
    private ArrayList<Integer> numItems;

    public Inventory() {
        inventory = new ArrayList<Item>();
        numItems = new ArrayList<Integer>();
    }

    public Inventory (ArrayList<Item> list) {
        this.inventory = list;
        numItems = new ArrayList<Integer>();
    }

    //add item to inventory
    public void addItem(Item item){
        boolean flag = true;
        //if item is already existing, just update num item array
        for(int i = 0; i < inventory.size(); i++){
            if(item == inventory.get(i)){
                numItems.set(i, numItems.get(i)+1);
                flag = false;
            }
        }
        if(flag){
            inventory.add(item);
            numItems.add(1);
        }
    }

    //use item in inventory
    public Item useItem(String name){
        Item result = null;
        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i).getName().equals(name)) {
                if (1 == numItems.get(i)) {
                    result = inventory.get(i);
                    inventory.remove(i);
                    numItems.remove(i);
                } else {
                    result = inventory.get(i);
                    numItems.set(i, numItems.get(i) - 1);
                }
            }
        }
        return result;
    }

    public String getItemName(int index) {
        return inventory.get(index).getName();
    }

    public Item getItem(int index) {
        return inventory.get(index);
    }

    public Item getItem(String name) {
        for (int i = 0; i<inventory.size(); i++) {
            if (inventory.get(i).getName().equals(name)) {
                return inventory.get(i);
            }
        }
        return null;
    }

    public int getArrayNumberAtIndex(int index) {
        if (index > -1 && index<numItems.size()) {
            return numItems.get(index);
        }
        return -1;
    }

    public int getInventorySize() {
        return inventory.size();
    }
}