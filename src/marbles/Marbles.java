package marbles;

import java.util.Random;
import java.util.Scanner;

/**
 * Game logic class
 * 
 * @author sojicute
 */
public class Marbles {
    
    static final String EVEN = "even";
    static final String ODD = "odd";
/**
 * Game logic
 * 
 */
    void start () {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        
        System.out.println("Введите имя первого ирока!");
//        String firstName = sc.next();
        Player p1 = new Player("Anton");
        p1.setPlayerType("AI");
        
//        System.out.println("Введите имя второго ирока!");
//        String secondName = sc.next();
        Player p2 = new Player();
        
        Game game = new Game(p1, p2);
        
         
        game.init();
        
        
        while(true) {
            int playerBet;
            if ("AI".equals(game.getCurrentPlayer().getPlayerType())) {
                playerBet = random.nextInt((game.getCurrentPlayer().getMarbles()-1)+ 1)+ 1;
                System.out.println(game.getCurrentPlayer().getName()+" сделал ставку");
            }else {
                System.out.println("Игрок " +game.getCurrentPlayer().getName()+ " ведите вашу ставку от 1 до " + game.getCurrentPlayer().getMarbles());
                while ((playerBet = sc.nextInt()) < game.MIN_BET || playerBet > game.getCurrentPlayer().getMarbles()) {
                    System.out.println("Неверно! Введите число от 1 до " + game.getCurrentPlayer().getMarbles());
                }
            }
            game.setCurrentBet(playerBet);
            
            game.nextPlayer();
            
            String playerGuess;
            if ("AI".equals(game.getCurrentPlayer().getPlayerType())) {
                playerGuess = game.isEven(random.nextInt((100-1)+ 1)+ 1);
                System.out.println(game.getCurrentPlayer().getName()+" пытается угадать и говорит что число " + playerGuess);
            } else {
                System.out.println(game.getCurrentPlayer().getName()+", угадайте четное или нечетное число, для этого введите even если четное и odd если нечетное");
            
                while (!((playerGuess = sc.next()).equals(EVEN) || playerGuess.equals(ODD))) {
                    System.out.println("Неверно! Введите even или odd!");
                }
            }
            
            game.transferMarbles(playerGuess);
            
            System.out.println(game.showStat());
            
            if (game.isWinner()) {
                break;
            }
            
        }
        game.showStat();
    };
}
