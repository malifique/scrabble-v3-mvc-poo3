package game.scrabble.controller;

import game.scrabble.model.Letter;

import java.util.ArrayList;
import java.util.EventObject;

public class RackChangedEvent extends EventObject{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    ArrayList<Letter> letters;
    
    @SuppressWarnings("unchecked")
    public RackChangedEvent(Object source, ArrayList<Letter> l){
        super(source);
        
        this.letters = (ArrayList<Letter>) l.clone();
    }

    public ArrayList<Letter> getLetters() {
        return letters;
    }
}