package marbles;

import java.util.Scanner;

/**
 *
 * @author sojicute
 */
public class Marbles {
    
    
    void start () {
        Scanner sc = new Scanner(System.in);
        
//        System.out.println("Введите имя первого ирока!");
//        String firstName = sc.next();
//        Player p1 = new Player(firstName);
//        
//        System.out.println("Введите имя второго ирока!");
//        String secondName = sc.next();
//        Player p2 = new Player(secondName);
        
        
        Player p1 = new Player("Anton");
        Player p2 = new Player();

        Game game = new Game(p1, p2);
        
        game.init();
        int i = 0;
        while(true) {
            
            i++;
            System.out.println("Раунд "+ i);
            
            game.turnMakeBet();
            game.turnGuessBet();
            System.out.println(game.showStat());
            if (game.isWin()) {
                break;
            }
            
        }
        game.showStat();
    };
}
