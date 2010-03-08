package game.scrabble.model;

import game.scrabble.controller.CaseChangedEvent;
import game.scrabble.controller.CaseListener;

import javax.swing.event.EventListenerList;

/**
 * @author BERNIGAUD Côme
 */
public class Case {
	
	private int letterMultiplier,wordMultiplier;
	private Letter letter;
    private EventListenerList listeners = new EventListenerList();

	public Case() {
		this.letterMultiplier = 1;
		this.wordMultiplier = 1;
		this.letter = null;
	}
	public Case(int lM) {
		this();
		letterMultiplier = lM;
	}
	public Case(int lM, int wM) {
		this(lM);
		if(lM!=1 && wM !=1)
			throw new IllegalArgumentException();
		wordMultiplier = wM;
	}
	
	public boolean setLetter(Letter l) {
		if(this.isEmpty()) {
			letter = l;
	        fireCaseChanged();
			return true;
		} else {
			return false;
		}
	}

	public int getScore() {
		if (this.letter == null) return 0;
		return letter.getScore()*letterMultiplier;
	}
	public int getLetterMultiplier() {
	    return letterMultiplier;
	}
	public int getWordMultiplier() {
		return wordMultiplier;
	}
	public boolean isEmpty() {
		return (letter==null);
	}
	public Letter getLetter() {
		return letter;
	}
	public void hasBeenUsed() {
	    letterMultiplier = 1;
	    wordMultiplier = 1;
        fireCaseChanged();
	}
    
    public void addListener(CaseListener listener) {
        listeners.add(CaseListener.class, listener);
    }
    
    public void removeListener(CaseListener l) {
         listeners.remove(CaseListener.class, l);
    }
    
    public void fireCaseChanged() {
        CaseListener[] listenerList = (CaseListener[])listeners.getListeners(CaseListener.class);
        
        for(CaseListener listener : listenerList){
            listener.caseChanged(new CaseChangedEvent(this, this));
        }
    }
	
	@Override
	public String toString() {
	    if (letter==null) {
	        if(letterMultiplier!=1) {
	            return ""+letterMultiplier+"L";
	        } else if(wordMultiplier!=1) {
	            return ""+wordMultiplier+"W";
	        } else
	            return "-";
	    } else
	        return letter.toString();
	}
}
