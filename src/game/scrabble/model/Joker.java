package game.scrabble.model;

/**
 * 
 * @author BERNIGAUD Côme
 *
 */
public class Joker extends Letter {

    public Joker() {
        super(0, '*');
    }
    
    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public boolean equals(Letter l) {
        return this.getClass().isInstance(l);
    }   
    @Override
    public boolean equals(char c) {
        if(getSymbol()=='*') {
            this.setSymbol(c);
            return true;
        } else
            return super.equals(c);
    }
    
    public void reinit () {
        this.setSymbol('*');
    }
    
    @Override
    public String toString() {
        return "*";
    }
}
