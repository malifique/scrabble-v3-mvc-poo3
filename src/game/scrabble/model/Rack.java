package game.scrabble.model;

import game.scrabble.controller.RackChangedEvent;
import game.scrabble.controller.RackListener;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.event.EventListenerList;

/**
 * 
 * @author BERNIGAUD Côme
 *
 */
public class Rack {
    
    public static int SIZE = 7;

    ArrayList<Letter> letters;
    private EventListenerList listeners = new EventListenerList();
    
    public Rack() {
        // TODO Auto-generated constructor stub
        letters = new ArrayList<Letter>();
    }
    
    public void addLetter(Letter l) {
        if(l==null) return;
        if (l instanceof Joker) {
            ((Joker) l).reinit();
        }
        letters.add(l);
        fireRackChanged();
    }
    
    public Letter getLetter(int i) {
        try {
            Letter l = letters.remove(i);
            fireRackChanged();
            return l;
        } catch (Exception e) {
            return null;
        }
    }
    public Letter getLetter(char c) {
        int i = this.contains(c); 
        if(i>=0)
            return this.getLetter(i);
        else
            return null;
    }
    
    public void addListener(RackListener listener) {
        listeners.add(RackListener.class, listener);
    }
    
    public void removeListener(RackListener l) {
         listeners.remove(RackListener.class, l);
    }
    
    public void fireRackChanged() {
        RackListener[] listenerList = (RackListener[])listeners.getListeners(RackListener.class);
        
        for(RackListener listener : listenerList){
            listener.rackChanged(new RackChangedEvent(this, letters));
        }
    }
    
    /*public Letter[] get(String word) {
        Letter[] result = new Letter[word.length()];
        int j = 0;
        for (int i=0;i<word.length();i++) {
            if((j = this.contains(word.charAt(i)))>=0)
                    result[i] = letters.remove(j);
            else {
                this.addLetters(result);
                result = null;
                break;
            }
        }
        return result;
    }*/

    protected int contains(char c) {
        for(int i=0;i<letters.size();i++)
            if(!(letters.get(i) instanceof Joker))
                if(letters.get(i).equals(Character.toUpperCase(c))) {
                    return i;
                }
        for(int i=0;i<letters.size();i++)
            if(letters.get(i).equals(Character.toUpperCase(c))) {
                return i;
            }        
        return -1;
    }
    
    public void addLetters(Collection<Letter> ls) {
        letters.addAll(ls);
        fireRackChanged();
    }
    public int size() {
        return letters.size();
    }
    public int length() {
        return this.size();
    }
    
    public String toString() {
        String result = "";
        for(int i=0;i<letters.size();i++)
            result += letters.get(i).getSymbol();
        return result;
    }
}
