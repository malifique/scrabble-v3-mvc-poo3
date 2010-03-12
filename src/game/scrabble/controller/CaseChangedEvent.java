package game.scrabble.controller;

import game.scrabble.model.Case;

import java.util.EventObject;

public class CaseChangedEvent extends EventObject{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int letterMultiplier,wordMultiplier;
    private char letter;
    
    public CaseChangedEvent(Object source, Case c){
        super(source);
        
        this.letterMultiplier = c.getLetterMultiplier();
        this.wordMultiplier = c.getWordMultiplier();
        if(c.getLetter()!=null)
            this.letter = c.getLetter().getSymbol();
        else
            this.letter = ' ';
    }

    public int getLetterMultiplier() {
        return letterMultiplier;
    }

    public int getWordMultiplier() {
        return wordMultiplier;
    }

    public char getLetter() {
        return letter;
    }
    
    public boolean isEmpty() {
        return (letter==' ');
    }
}
