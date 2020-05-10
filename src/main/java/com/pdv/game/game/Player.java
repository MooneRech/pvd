package com.pdv.game.game;

public class Player  extends PlayerOld {

    public Player() {
        setGuessCount(0);
        setGuessed(false);

    }

    public void guessNumber(int number) {
        guessCount++;
        guessNumber = number;
    }

    @Override
    public String toString() {
        return orderNumber + ". " + name + ": " + guessCount + " guesses" + System.lineSeparator();
    }

}