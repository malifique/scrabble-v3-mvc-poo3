package game.scrabble.controller;

import java.util.EventObject;

public class PlayerChangedEvent extends EventObject{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int score;
    private String name;
    
    public PlayerChangedEvent(Object source, int s, String n){
        super(source);
        
        this.score = s;
        this.name = n;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
}