package stresseat;

<<<<<<< HEAD
import java.util.concurrent.TimeUnit;
=======
public class StressEat implements Timer, MiniGamePoints {
>>>>>>> 0e372810c23a29608ddacbb2b5c878a82bbc4ef0

public class StressEat implements Clock, Points{ //implements clock, points
    private int points;
    
    public boolean countDown() throws InterruptedException{
        for(int i = 60; i >= 0; i--){
            TimeUnit.SECONDS.sleep(1);
        }
        return false; //time's up
    }
    
    public int addPoints(){
        return ++points;
    }
    
    public int subtractPoints(){
        return --points;
    }
    
    public static void main(String[] args) {
<<<<<<< HEAD
      
=======
>>>>>>> 0e372810c23a29608ddacbb2b5c878a82bbc4ef0
    }

    // Randomly generate food
    public static void createFood() {
    }

    // Drop food
    public static void movement() {
    }

}
