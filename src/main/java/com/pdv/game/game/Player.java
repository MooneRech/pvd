package com.pdv.game.game;


/**
 *
 * @author IR00300
 */
public class Player {

    private int orderNumber;

    private String name;

    private int guessCount;

    private boolean guessed;

    private int guessNumber;

//   =============================
//             FUNCTIONS
//   =============================

    public void guessNumber(int number) {
        guessCount++;
        guessNumber = number;
    }

    @Override
    public String toString() {
        return (orderNumber + 1) + ". " + name + ": " + guessCount + " guesses" + System.lineSeparator();
    }

//   =============================
//         GETTERS & SETTERS
//   =============================

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
