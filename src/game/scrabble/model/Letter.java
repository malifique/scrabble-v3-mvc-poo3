package game.scrabble.model;

/**
 * 
 * @author BERNIGAUD Côme
 *
 */
public class Letter {

    private int score;
    private char symbol;
    public Letter(int s, char sym) {
        if(s<0) {
            throw new IllegalArgumentException();
        }
        score = s;
        symbol = Character.toUpperCase(sym);
    }
    public int getScore() {
        return score;
    }
    public char getSymbol() {
        return symbol;
    }
    public boolean equals(Letter l) {
        return ((l.getSymbol()==this.symbol)&&(l.getScore()==score));
    }
    public boolean equals(char c) {
        return this.symbol==Character.toUpperCase(c);
    }
    
    protected void setSymbol(char c) {
        this.symbol = c;
    }
    
    @Override
    public String toString() {
        return ""+symbol;
    }
}
