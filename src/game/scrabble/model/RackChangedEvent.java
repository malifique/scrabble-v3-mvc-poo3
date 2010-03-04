package game.scrabble.model;

import java.util.ArrayList;
import java.util.EventObject;

public class RackChangedEvent extends EventObject{
    ArrayList<Letter> letters;
    
    public RackChangedEvent(Object source, ArrayList<Letter> l){
        super(source);
        
        this.letters = (ArrayList<Letter>) l.clone();
    }

    public ArrayList<Letter> getLetters() {
        return letters;
    }
}