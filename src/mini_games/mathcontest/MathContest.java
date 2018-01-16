package mini_games.mathcontest;

import java.util.Scanner;

public class MathContest {
    public static void main(String args[]){
        Clock clock = new Clock(); //create clock thread
        Questions questions = new Questions(); //create questions thread

        //intro
        System.out.println("Welcome to the math contest!");
        System.out.println("Are you ready to improve your intelligence?");

        //start questions and clock
        questions.start();
        clock.start();

        //check if clock is finished
        do{
            if(clock.getState()== Thread.State.TERMINATED) {
                // questions.interrupt(); //interrupts questions thread to display final points
                System.out.println("Thanks for playing!");
                System.out.println("Your score was " + questions.getPoints() + " out of " + questions.getNumQuestions());
                System.out.println("Your percentage is " + questions.getPercentage() + "%");
            }
        }while(clock.getState() != Thread.State.TERMINATED);
    }
}
