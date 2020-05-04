package com.pdv.game.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author IR00300
 */
public class Game {

    private int randNumber;
    private int playerCount;
    private List<Player> playerList;
    boolean hasWinner;
    boolean playTheGame;
    boolean gameInProcess;
    private ResponseModel responseModel;
    private int lesserValue;
    private Boolean startFromBeginning;

    Scanner scn = new Scanner(System.in);

    Game() {
        gameInProcess = false;
        startFromBeginning = false;
        responseModel = new ResponseModel();
    }

    public void initGame() {
        playTheGame = true;
        hasWinner = false;
        gameInProcess = false;
        playerList = new ArrayList<>();
        lesserValue = -1;
        initNumber();
        responseModel.setInitPlayers(true);
    }

    public ResponseModel addPlayer(String username) {
        Player player = new Player();
        player.setName(username);
        playerList.add(player);
        responseModel.setId(playerList.size()-1);
        responseModel.setCount(playerCount - playerList.size());
        if(playerCount == playerList.size()) {
            responseModel.setInProgress(true);
        }
        return responseModel;
    }

    public ResponseModel enterNumber(int id, int number) {
        Player player = playerList.get(id);
        player.guessNumber(number);
        String messages;

        if(!player.isGuessed()) {

            if(number > randNumber) {
                messages = "The number is lesser than yours (your number " + number + ")";
            } else if (number < randNumber) {
                messages = "The number is greater than yours (your number " + number + ")";
            } else {
                messages = "You guessed!";
                player.setGuessed(true);

                if( lesserValue == -1 || lesserValue > player.getGuessCount()) {
                    lesserValue = player.getGuessCount();
                }
            }
        } else {
            messages = "";
            responseModel.setGuessed(player.isGuessed());
        }

        gameInProcess = false;
        for (Player value : playerList) {
            if(!value.isGuessed()) {
                gameInProcess = true;
                break;
            }
        }

        responseModel.setId(id);
        responseModel.setGuessed(player.isGuessed());
        responseModel.setMessage(messages);
        responseModel.setInProgress(gameInProcess);
        return responseModel;
    }

    public ResponseModel endGame() {
        startFromBeginning = true;
        String message = "";

        for(Player player : playerList) {
            message = message + player.toString();
        }

        message = message + findWinner();
        ResponseModel responseModel = new ResponseModel();
        responseModel.setMessage(message);
        return responseModel;
    }

    public String findWinner() {
        String message = "";

        for(Player player : playerList) {
            if( player.getGuessCount() == lesserValue) {
                message = message + "Player " + player.getName() + " wins with " + player.getGuessCount() + " guesses." + System.lineSeparator();
            }
        }
        return message;
    }

    public void initNumber() {
        Random rand = new Random();
        randNumber = rand.nextInt((100 - 0) + 1);
        System.out.println(randNumber);
    }

//   =============================
//         GETTERS & SETTERS
//   =============================

    public Boolean getStartFromBeginning() {
        return startFromBeginning;
    }

    public void setStartFromBeginning(Boolean startFromBeginning) {
        this.startFromBeginning = startFromBeginning;
    }

    public ResponseModel getResponseModel() {
        return responseModel;
    }

    public void setResponseModel(ResponseModel responseModel) {
        this.responseModel = responseModel;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public int getRandNumber() {
        return randNumber;
    }

    public void setRandNumber(int randNumber) {
        this.randNumber = randNumber;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

}
