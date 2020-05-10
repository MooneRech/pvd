package com.pdv.game.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameOld {
    protected int randNumber;
    protected int playerCount;
    protected Player winner;
    protected List<Player> playerList;
    protected boolean hasWinner;
    protected boolean playTheGame;

    public void initGame() {

        Scanner scn = new Scanner(System.in);
        playTheGame = true;

        while(playTheGame) {
            System.out.println("How many players would like to play the game?");

            while(true) {
                try {
                    playerCount = scn.nextInt();
                    break;
                } catch(Exception e) {
                    System.out.println("Please enter valid number!");
                    scn.next();
                }
            }

            playerList = new ArrayList<>();
            winner = new Player();
            hasWinner = false;

            System.out.println("====================================================");

            for(int i = 0; i < playerCount; i++) {
                Player player = new Player();

                player.setOrderNumber(i+1);
                player.setGuessCount(0);
                player.setGuessed(false);
                player.tellYourName(scn);

                playerList.add(player);
            }

            startGame(scn);
        }

        scn.close();
    }

    private void startGame(Scanner scn) {
        initNumber();
        System.out.println("====================================================");
        boolean gameInProcess = true;

        while(gameInProcess) {
            gameInProcess = false;
            for(Player player: playerList) {

                if(!player.isGuessed()) {
                    int number = player.readNumber(scn);
                    gameInProcess = true;

                    if(number > randNumber) {
                        System.out.println("The number is lesser than yours");
                        System.out.println("----------------------------------------------------");
                    } else if (number < randNumber) {
                        System.out.println("The number is greater than yours");
                        System.out.println("----------------------------------------------------");
                    } else {
                        System.out.println("You guessed!");
                        System.out.println("----------------------------------------------------");
                        player.setGuessed(true);

                        if(!hasWinner) {
                            hasWinner = true;
                            winner = player;
                        }
                    }
                }
            }
        }

        endGame(scn);
    }

    private void endGame(Scanner scn) {
        System.out.println("====================================================");

        for(Player player : playerList) {
            System.out.println(player.toString());
        }

        System.out.println(winner.getName() + " wins with " + winner.getGuessCount() + " guesses.");
        System.out.println("====================================================");

        System.out.println("Wold you like to play another game? (y/n)");
        String ynVal;
        while(true) {
            ynVal = scn.next();

            if("Y".equals(ynVal) || "y".equals(ynVal)) {
                System.out.println("====================================================");
                break;
            } else if ("N".equals(ynVal) || "n".equals(ynVal)) {
                playTheGame = false;
                System.out.println("Bye!");
                break;
            } else {
                System.out.println("Please enter valid answer (y/n)!");
            }
        }
    }

    public int initNumber() {
        Random rand = new Random();
        randNumber = rand.nextInt((100 - 0) + 1);
        return randNumber;
    }

//   =============================
//         GETTERS & SETTERS
//   =============================

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
