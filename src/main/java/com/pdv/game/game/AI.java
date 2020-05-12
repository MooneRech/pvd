package com.pdv.game.game;

import java.util.Random;

public class AI extends PlayerOld {

    public AI() {
        guessCount = 0;
    }

    public Player findNumber(int number) {
        Player ai = new Player();
        Random rand = new Random();
        int guessNumber;
        int min = 0;
        int max = 100;

        ai.setName("AI");
        while(true) {
            guessNumber = rand.nextInt((max - min) +1) + min;
            System.out.println(guessNumber);
            guessCount++;
            if (guessNumber > number) {
                max = guessNumber - 1;
            } else if (guessNumber < number) {
                min = guessNumber + 1;
            } else {
                break;
            }
        }
        ai.setGuessed(true);
        ai.setGuessCount(guessCount);
        return ai;
    }

}
