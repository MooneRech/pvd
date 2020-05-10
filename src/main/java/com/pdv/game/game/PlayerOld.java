package com.pdv.game.game;

import java.util.Scanner;

public class PlayerOld {
    protected int orderNumber;

    protected String name;

    protected int guessCount;

    protected boolean guessed;

    protected int guessNumber;

//   =============================
//             FUNCTIONS
//   =============================

    public void tellYourName(Scanner scn) {
        System.out.print("Player " + orderNumber + " tell your name: ");
        name = scn.next();
    }

    public int readNumber(Scanner scn) {
        int number;
        boolean isValueCorrect = false;

        System.out.println("Player " + orderNumber +"(" + this.name + ")" + " guess a number!");

        while(!isValueCorrect) {
            try {
                number = scn.nextInt();
                if (number >=0 && number <= 100) {
                    isValueCorrect = true;
                    guessCount++;
                    guessNumber = number;
                } else {
                    System.out.println("The number should be from 0 to 100");
                }
            } catch(Exception e) {
                System.out.println("Plaese enter valid number");
                scn.next();
            }
        }

        return guessNumber;
    }

    @Override
    public String toString() {
        return orderNumber + ". " + name + ": " + guessCount + " guesses";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGuessCount() {
        return guessCount;
    }

    public void setGuessCount(int guessCount) {
        this.guessCount = guessCount;
    }

    public boolean isGuessed() {
        return guessed;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }

    public int getGuessNumber() {
        return guessNumber;
    }

    public void setGuessNumber(int guessNumber) {
        this.guessNumber = guessNumber;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
}
