package game.scrabble.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * The bag containing letters 
 * @author BERNIGAUD Côme
 *
 */
public class Bag {
    
    private ArrayList<Letter> letters;
    public Bag() {
        letters = new ArrayList<Letter>();
    }
    public Bag(ArrayList<Letter> letters) {
        this();
        if(letters==null) throw new IllegalArgumentException();
        this.letters = letters;
    }
    public void addLetter(Letter l) {
        letters.add(l);
    }
    public Letter getLetter() {
        Collections.shuffle(letters);
        Iterator<Letter> i = letters.iterator();
        if(!i.hasNext()) return null;
        Letter l = i.next();
        i.remove();
        return l;
    }
    public boolean isEmpty() {
        return letters.isEmpty();
    }
}
