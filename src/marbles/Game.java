/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marbles;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author sojicute
 */
public class Game {
    Scanner sc = new Scanner(System.in);
    Random random = new Random();
    
    private Player firstPlayer, secondPlayer, currentPlayer;
    private int currentBet;

    private static final String EVEN = "even";
    private static final String ODD = "odd";
    
    private static final int COUNT_TO_WIN = 20;
    private static final int MIN_BET = 1;
    
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

    public Game(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }
    
    
    public void init() {
        System.out.println("Начало игры!");
        System.out.println("Игрок №1 "+firstPlayer.getName());
        System.out.println("Игрок №2 "+secondPlayer.getName());
        currentPlayer = firstPlayer;
    }
    
    public void turnMakeBet() {
        int playerBet;
        
        if ("AI".equals(currentPlayer.getPlayerType())) {
            System.out.println(currentPlayer.getName()+" думает над ставкой");
            
            
            playerBet = random.nextInt(currentPlayer.getMarbles()-MIN_BET)+ MIN_BET;
            System.out.println(currentPlayer.getName()+" сделал ставку");
        } else {
            System.out.println(currentPlayer.getName()+", введите число которое хотите поставить!");

            while ((playerBet = sc.nextInt()) < MIN_BET || playerBet > currentPlayer.getMarbles()) {
                System.out.println("Неверно! Введите число от 1 до " + currentPlayer.getMarbles());
            }
            
            System.out.println(currentPlayer.getName()+", вы поставили ставку в размере "+ playerBet +" шар(ов)");
        }
        
        this.currentBet = playerBet;
        
        nextPlayer();
    }
    
    
    

    
    public void turnGuessBet() {
        String playerGuess;
        
        if ("AI".equals(currentPlayer.getPlayerType())) {
            playerGuess = isEven(random.nextInt(100));
            System.out.println(currentPlayer.getName()+" пытается угадать и говорит что число " + playerGuess);
        } else {
            System.out.println(currentPlayer.getName()+", угадайте четное или нечетное число, для этого введите even если четное и odd если нечетное");
            
            while (!((playerGuess = sc.next()).equals(EVEN) || playerGuess.equals(ODD))) {
                System.out.println("Неверно! Введите even или odd!");
            }
        }
        
        
        if (isEven(currentBet).equals(playerGuess)) {
            currentPlayer.setMarbles(currentPlayer.getMarbles() + currentBet);
            oppositePlayer(currentPlayer).setMarbles(oppositePlayer(currentPlayer).getMarbles() - currentBet);
            System.out.println("Верно! Игрок " + currentPlayer.getName()+ " получает "+currentBet+ " шар(ов)");
        } else {
            currentPlayer.setMarbles(currentPlayer.getMarbles() - currentBet);
            oppositePlayer(currentPlayer).setMarbles(oppositePlayer(currentPlayer).getMarbles()  + currentBet);
            System.out.println("Неудача! Игрок " + currentPlayer.getName()+ " теряет "+currentBet+ " шар(ов)");
        }
    }
    
    private Player oppositePlayer(Player player) {
        if(player == firstPlayer) {
            return secondPlayer;
        } 
        return firstPlayer ;
    }
    
    private void nextPlayer() {
        if(currentPlayer == firstPlayer) {
            currentPlayer = secondPlayer;
        } else {
            currentPlayer = firstPlayer;
        }
    }
    
    private String isEven(int count) {
        if (count % 2 == 0) {
            return EVEN;
        }
        return ODD;
    }
    
    public boolean isWin() {
        if (currentPlayer.getMarbles() >= COUNT_TO_WIN ) {
            System.out.println("Победедитель "+ currentPlayer.getName());
            return true;
        }
        return false;
    }
    
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
