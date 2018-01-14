package mini_games.mathcontest;

import java.util.Scanner;

public class MathContest {
    public static void main(String args[]){
        Clock clock = new Clock();
        Questions questions = new Questions();

        System.out.println("Welcome to the math contest!");
        System.out.println("Are you ready to improve your intelligence?");
        questions.start();
        clock.start();

        do{
            if(clock.getState()== Thread.State.TERMINATED) {
                questions.interrupt();
                System.out.println("Thanks for playing!");
                System.out.println("Your score was " + questions.getPoints());
                questions.stop();
            }
        }while(clock.getState() != Thread.State.TERMINATED);
    }
}
