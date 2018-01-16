package mini_games.mathcontest;

import java.util.Scanner;

public class Questions extends Thread{

    int points;
    int numQuestions = 0;

    @Override
    public void run(){
        int num1, num2, operator, answer;
        double userAnswer;
        Scanner input = new Scanner(System.in);

        //keeps track of time
        Clock clock = new Clock();
        clock.start();

        do {
            numQuestions++;
            num1 = (int) (Math.random() * 100);
            num2 = (int) (Math.random() * 100);
            operator = (int) Math.ceil(Math.random() * 4);
            if (operator == 1) {
                System.out.println(numQuestions + ". What is " + num1 + " + " + num2);
                answer = num1 + num2;
            } else if (operator == 2) {
                System.out.println(numQuestions + ". What is " + num1 + " - " + num2);
                answer = num1 - num2;
            } else if (operator == 3) {
                num1 = (int) (Math.random() * 12);
                num2 = (int) (Math.random() * 12);
                System.out.println(numQuestions + ". What is " + num1 + " x " + num2);
                answer = num1 * num2;
            } else {
                if(num2 == 0){
                    num2 = (int)Math.ceil(Math.random() * 99);
                }
                if (num1 % num2 != 0) {
                    do {
                        num1 = (int) (Math.random() * 100);
                        num2 = (int) Math.ceil(Math.random() * 99);
                    } while (num1 % num2 != 0);
                }
                System.out.println(numQuestions + ". What is " + num1 + " / " + num2);
                answer = num1 / num2;
            }
            userAnswer = input.nextDouble();
            if (userAnswer == answer) {
                points++;
            }
        } while(clock.getState() != State.TERMINATED);
    }

    public int getPoints(){
        return points;
    }

    public int getNumQuestions(){
        return numQuestions;
    }

    public double getPercentage(){
        return Math.round((double)points / numQuestions * 100);
    }
}
