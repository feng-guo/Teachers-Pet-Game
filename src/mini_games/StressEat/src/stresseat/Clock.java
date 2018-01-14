package mini_games.StressEat.src.stresseat;

public class Clock extends Thread {

    @Override
    public void run() {
        for (int i = 60; i >= 0; i--) {
            System.out.println(i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
