package mini_games.mathcontest;

public class Clock extends Thread{

    @Override
    public void run(){
       long time = System.nanoTime();
       do {
           if (System.nanoTime() - time >= 60000000000l) {
               return;
           }
       }while(System.nanoTime() - time < 60000000000l);
    }
}