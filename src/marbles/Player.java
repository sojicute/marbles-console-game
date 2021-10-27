/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marbles;

/**
 * Player entity
 * 
 * @author sojicute
 */
public class Player {
    private String name;
    private String playerType;
    private int marbles = 10;
    
    /**
    * Create new player with name
    * 
    * 
    * @param name set player name
    */
    public Player(String name) {
        this.name = name;
    }
    
    /**
    * Create new player with default param name = "AI" and playerType = "AI"
    */
    public Player() {
        this.name = "AI";
        this.playerType = "AI";
    }
     
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMarbles() {
        return marbles;
    }

    public void setMarbles(int marbles) {
        this.marbles = marbles;
    }
    
    /**
    * Get playerType of current player
    * 
    * @return player playerType
    */ 
    public String getPlayerType() {
        return playerType;
    }
    
    /**
    * Set name of current player
    * 
    * @param playerType have default value "AI"
    */ 
    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", playerType=" + playerType + ", marbles=" + marbles + '}';
    }
    
    
}
