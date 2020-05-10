package com.pdv.game.controller;

public class ResponseModel {

    private int id;

    private String message;

    private boolean hasWinner;

    private boolean guessed;

    private boolean inProgress;

    private boolean initPlayers;

    private int count;

    public ResponseModel() {
        setHasWinner(false);
        setInProgress(false);
        setInitPlayers(false);
    }

//   =============================
//         GETTERS & SETTERS
//   =============================

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isInitPlayers() {
        return initPlayers;
    }

    public void setInitPlayers(boolean initPlayers) {
        this.initPlayers = initPlayers;
    }

    public boolean getInProgress() {
        return inProgress;
    }

    public void setInProgress(boolean inProgress) {
        this.inProgress = inProgress;
    }

    public boolean getGuessed() {
        return guessed;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }

    public boolean getHasWinner() {
        return hasWinner;
    }

    public void setHasWinner(boolean hasWinner) {
        this.hasWinner = hasWinner;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
