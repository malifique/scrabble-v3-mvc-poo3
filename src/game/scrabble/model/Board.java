package game.scrabble.model;

import java.io.FileNotFoundException;
import java.util.HashSet;

/**
 * 
 * @author BERNIGAUD Côme
 *
 */
public class Board {

    public static int SIZE = 15;
    public static int BONUS = 50; // points for a scrabble
    
    private Case[][] grid;
    private Dictionary dico;
    
    public Board(String dicoName) throws FileNotFoundException {
        grid = new Case[SIZE][SIZE];
        dico = new Dictionary(dicoName);
        initClassicGrid();
    }
    
    public Letter getLetter(int x, int y) {
        return grid[x][y].getLetter();
    }
    
    public void addCaseListener(int x, int y, CaseListener cl) {
        grid[x][y].addListener(cl);
        grid[x][y].fireCaseChanged();
    }
    
    public void initClassicGrid() {
        int[][] g = {{5,1,1,2,1,1,1,5},
                     {1,4,1,1,1,3,1,1},
                     {1,1,4,1,1,1,2,1},
                     {2,1,1,4,1,1,1,2},
                     {1,1,1,1,4,1,1,1},
                     {1,3,1,1,1,3,1,1},
                     {1,1,2,1,1,1,2,1},
                     {5,1,1,2,1,1,1,4}
                    };
        for(int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++) {
                int l = g[(i<7?i:14-i)][(j<7?j:14-j)];
                grid[i][j] = new Case((l<4?l:1),(l>3?l%3+1:1));
            }
        }
    }
    
    /**
     * add a Word on the board.
     * @param x
     * @param y
     * @param horizontal
     * @param word The word that should be added
     * @param rack The rack of the player who add the word
     * @return 0 if adding failed, the score of the word if adding successed
     */
    public int addWord(int x, int y, boolean horizontal, String word, Rack rack) throws IllegalArgumentException {
        int c = (horizontal?1:0);
        int l = (horizontal?0:1);
        boolean goodPlace = false;
        
        if(word==null||rack==null)
            throw new IllegalArgumentException("Null argument");
        
        if(!dico.contains(word))
            throw new IllegalArgumentException("This word doesn't exist");
        if(!this.isEmptyOrOut(x-c,y-l))
            throw new IllegalArgumentException("Bad position");
        if(!this.isEmptyOrOut(x+(c*word.length()), y+(l*word.length())))
            throw new IllegalArgumentException("Bad position");
        if(x+(c*word.length())>SIZE||y+(l*word.length())>SIZE)
            throw new IllegalArgumentException("Bad position");
        if(grid[SIZE/2][SIZE/2].isEmpty()&&(
                (x<=7)&&(y<=7)&&(y+l*word.length()>=7)&&(x+c*word.length()>=7)
                ))
           goodPlace = true;
        else if(grid[SIZE/2][SIZE/2].isEmpty())
            throw new IllegalArgumentException("Bad position : the first word must cover the centrale case");

        int score = 0;
        int crossScore = 0;
        int i=0;
        int multiplier=1;
        int sizeOfRack = rack.size();
        HashSet<Letter> letters = new HashSet<Letter>();
        int xi=x,yi=y;
        String error = "";
        for(i=0;i<word.length();++i) {
            if(!grid[x][y].isEmpty()&&!grid[x][y].getLetter().equals(word.charAt(i))) {
                i=word.length()+1;
                error="The letters of the board does not fit";
            } else if(grid[x][y].isEmpty()) {
                Letter letter = rack.getLetter(word.charAt(i));
                if (letter==null) {
                    i=word.length()+1;
                    error="The letters of the rack does not fit";
                } else {
                    letters.add(letter);
                    int cross = checkCrossWord(x,y,horizontal,letter);
                    if(cross < 0) {
                        i=word.length()+1;
                        error="A word formed does not exist";
                    } else
                        crossScore += cross;
                }
            } else
                goodPlace = true;
            x += c;
            y += l;
        }
        x=xi;y=yi;
        if(i>word.length()) {
            rack.addLetters(letters);
            throw new IllegalArgumentException(error);
        }
        if(crossScore==0&&!goodPlace) {
            rack.addLetters(letters);
            throw new IllegalArgumentException("Your word is isolated");
        }            
        if(sizeOfRack==rack.size()){
            rack.addLetters(letters);
            throw new IllegalArgumentException("You don't use anything in the rack");
        }
        rack.addLetters(letters);
        for(i=0;i<word.length();++i) {
            if(grid[x][y].isEmpty()) {
                Letter letter = rack.getLetter(word.charAt(i));
                grid[x][y].setLetter(letter);
            }
            score += grid[x][y].getScore();
            multiplier *= grid[x][y].getWordMultiplier();
            grid[x][y].hasBeenUsed();
            x += c;
            y += l;
        }
        if(rack.length()==0)
            crossScore += BONUS;
        return score*multiplier+crossScore;
    }
    protected int checkCrossWord(int x, int y, boolean vertical, Letter letter) {
        int c = (vertical?0:1);
        int l = (vertical?1:0);
        int i = x,j = y;
        while (!isEmptyOrOut(i - c, j - l)) {
            i -= c;
            j -= l;
        }
        String word = "";
        int score = 0;
        while ((!isEmptyOrOut(i, j))||(x==i&&y==j)) {
            if(x==i&&y==j) {
                word += letter.getSymbol();
                score += letter.getScore()*grid[x][y].getLetterMultiplier();
            }else {
                word += grid[i][j].getLetter().getSymbol();
                score += grid[i][j].getScore();
            }
            i += c;
            j += l;
        }
        if (word.length()<=1)
            return 0;
        else if (!dico.contains(word))
            return -1;
        else
            return score;
    }
    protected boolean isEmptyOrOut(int x, int y) {
        if((x<0)||(y<0)||(x>=SIZE)||(y>=SIZE))
            return true;
        else
            return grid[x][y].isEmpty();
    }
    
    @Override
    /*public String toString() {
        String result = "";
        for(int y=0;y<SIZE;y++){
            for(int x=0;x<SIZE;x++) {
                result += "|"+grid[x][y];
            }
            result += "|\n";
        }
        return result;
    }*/
    public String toString() {
        String result = "    01 02 03 04 05 06 07 08 09 10 11 12 13 14 15    \n\n";

        for (int i = 0; i < grid.length; i++) {
            result += (i > 8 ? (i + 1) : "0" + (i + 1)) + " ";
            for (int j = 0; j < grid[i].length; j++) {
            String letter = grid[i][j].toString();
            result += ((letter.length() > 1) ? " " : "  ") + letter;
            }
            result += "  " + (i > 8 ? (i + 1) : "0" + (i + 1)) + "\n";
        }
        result += "\n    01 02 03 04 05 06 07 08 09 10 11 12 13 14 15    \n";
        return result;   
    }

}
