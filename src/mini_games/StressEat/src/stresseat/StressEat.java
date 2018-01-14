package mini_games.StressEat.src.stresseat;

public class StressEat{ //implements clock, points
    
    public static void main(String[] args) {
        Clock clock = new Clock();
        FoodDrop food = new FoodDrop();
        clock.start();
        food.start();

    }

    // Randomly generate food
    public static void createFood() {
    }

    // Drop food
    public static void movement() {
    }
}
