package stresseat;

public class StressEat{ //implements clock, points
    
    public static void main(String[] args) {
        Clock clock = new Clock();
        FoodDrop food = new FoodDrop();
        clock.start();
        food.start();

    }
}