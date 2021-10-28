/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marbles;

import java.util.Random;
import java.util.Scanner;
import static marbles.Marbles.EVEN;
import static marbles.Marbles.ODD;

/**
 *
 * @author sojicute
 */
public class Game {
    private Player firstPlayer, secondPlayer, currentPlayer;
    private int currentBet;
    
    static final int COUNT_TO_WIN = 20;
    static final int MIN_BET = 1;
    
    public int getCurrentBet() {
        return currentBet;
    }

    public void setCurrentBet(int currentBet) {
        this.currentBet = currentBet;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    
    /**
     * Create game Player1 vs Player2
     * 
     * @param firstPlayer
     * @param secondPlayer 
     */
    public Game(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }
    
    
    /**
     *  Initializes the game
     */
    public void init() {
        System.out.println("Начало игры!");
        System.out.println("Игрок №1 "+firstPlayer.getName());
        System.out.println("Игрок №2 "+secondPlayer.getName());
        currentPlayer = firstPlayer;
    }
    
    
    /**
     * Exchanges marbles between players
     * 
     * @param playerGuess have a value even or odd
     */
    public void transferMarbles(String playerGuess) {
        if (isEven(currentBet).equals(playerGuess)) {

   
            if (oppositePlayer(currentPlayer).getMarbles() >= currentBet) {
                currentPlayer.setMarbles(currentPlayer.getMarbles() + currentBet);
                oppositePlayer(currentPlayer).setMarbles(oppositePlayer(currentPlayer).getMarbles() - currentBet);
            } else {
                currentPlayer.setMarbles(currentPlayer.getMarbles() + oppositePlayer(currentPlayer).getMarbles());
                oppositePlayer(currentPlayer).setMarbles(0);        
            }
            System.out.println("Верно! Игрок " + currentPlayer.getName()+ " получает "+currentBet+ " шар(ов)");
        } else {
            
            if (currentPlayer.getMarbles() >= currentBet) {
                currentPlayer.setMarbles(currentPlayer.getMarbles() - currentBet);
                oppositePlayer(currentPlayer).setMarbles(oppositePlayer(currentPlayer).getMarbles()  + currentBet);
            } else {
                oppositePlayer(currentPlayer).setMarbles(oppositePlayer(currentPlayer).getMarbles()  + currentPlayer.getMarbles());
                currentPlayer.setMarbles(0);
            }
            System.out.println("Неудача! Игрок " + currentPlayer.getName()+ " теряет "+currentBet+ " шар(ов)");
        }
    }
    /**
    * Return an opponent based on the player arg
    * 
    * @param player set currentPlayer
    * @return firstPlayer or secondPlayer
    */
    private Player oppositePlayer(Player player) {
        if(player == firstPlayer) {
            return secondPlayer;
        } 
        return firstPlayer ;
    }
    
    /**
    * Select an opponent based on the current Player
    */
    public void nextPlayer() {
        if(currentPlayer == firstPlayer) {
            currentPlayer = secondPlayer;
        } else {
            currentPlayer = firstPlayer;
        }
    }
    
    
    /**
     * Determines the winner based on the number of balls
     * 
     * @return boolean
     */
    public boolean isWinner() {
        if (currentPlayer.getMarbles() == COUNT_TO_WIN && oppositePlayer(currentPlayer).getMarbles() == 0 ) {
            System.out.println("Победедитель "+ currentPlayer.getName());
            return true;
        } else if (oppositePlayer(currentPlayer).getMarbles() == COUNT_TO_WIN && currentPlayer.getMarbles() == 0) {
            System.out.println("Победедитель "+ oppositePlayer(currentPlayer).getName());
            return true;
        }
        return false;
    }
    
    /**
     * Determines the parity of a number
     * 
     * @param count 
     * @return
     */
    public String isEven(int count) {
        if (count % 2 == 0) {
            return EVEN;
        }
        return ODD;
    }
    
    /**
     * Show stat players: name, count marbles
     * 
     * @return format string
     */
    public String showStat() {
        return String.format(
                "\n--------------------\n"
                + "%s: %d \n"
                + "%s: %d \n"
                +"--------------------\n",
                firstPlayer.getName(), firstPlayer.getMarbles(),
                secondPlayer.getName(), secondPlayer.getMarbles()
            );
    }
}
