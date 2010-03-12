package game.scrabble.model;

import game.scrabble.controller.PlayerChangedEvent;
import game.scrabble.controller.PlayerListener;

import javax.swing.event.EventListenerList;

/**
 * 
 * @author BERNIGAUD Côme
 *
 */
public class Player {

    private Rack rack;
    private int score;
    private String name;
    private static int NUMBER=1;
    private EventListenerList listeners = new EventListenerList();

    public Player(Bag bag) {
        this.score = 0;
        this.rack = new Rack();
        while(rack.length()<7) {
            rack.addLetter(bag.getLetter());
        }
        this.name="Player "+NUMBER++;
    }
    public Player(Bag bag,String n) {
        this(bag);
        name=n;
    }
    
    public Rack getRack() {
        return rack;
    }
    
    public String getName() {
        return name;
    }
    
    public int getScore() {
        return score;
    }
    public int addScore(int s) {
        score+=s;
        firePlayerChanged();
        return score;
    }
    
    public void addListener(PlayerListener listener) {
        listeners.add(PlayerListener.class, listener);
    }
    
    public void removeListener(PlayerListener l) {
         listeners.remove(PlayerListener.class, l);
    }
    
    public void firePlayerChanged() {
        PlayerListener[] listenerList = (PlayerListener[])listeners.getListeners(PlayerListener.class);
        
        for(PlayerListener listener : listenerList){
            listener.playerChanged(new PlayerChangedEvent(this, score, name));
        }
        rack.fireRackChanged();
    }

    /** function playTurn(Board board, Bag bag)
     * return true if the game is finished
     */
    /*public boolean playTurn(Board board, Bag bag) {
        Scanner scan = new Scanner(System.in);
        printState(board);
        int wordScore = 0;
        boolean end=false;
        do {
            System.out.println("What do you want to do? (x y word [h|v], help for more options)");
            String result = scan.nextLine();
            
            String args[] = result.split(" ");
            if(args[0].equalsIgnoreCase("endgame")) {
                System.out.println("End of the game");
                System.out.println(board);
                System.out.println(name+" have "+score+" points");
                return true;
            } else if(args[0].equalsIgnoreCase("help")) {
                System.out.println("Adding a word on the board : x y word [h|v]");
                System.out.println("Changing letters : change letters");
                System.out.println("Ending the game : endgame");
                continue;
            }
            if(args[0].equalsIgnoreCase("change")) {
                changeLetters(args[1], bag);
                end=true;
            } else {
                try {
                    wordScore = 0;
                    int x = Integer.parseInt(args[0])-1;
                    int y = Integer.parseInt(args[1])-1;
                    String word = args[2];
                    boolean horizontal = (args[3].equalsIgnoreCase("h")?true:false);
                    wordScore = board.addWord(x, y, horizontal, word, rack);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Missing argument. Type help for more information.");
                } catch (NumberFormatException e) {
                    System.out.println("Bad command. Type help for more information.");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error : "+e.getMessage());
                }
            }
            if (wordScore == 0) {
                System.out.println("This action isn't possible.");
            }
        } while((wordScore == 0)&&!end);
        System.out.println("You win "+wordScore+" points");
        score += wordScore;
        while(rack.length()<Rack.SIZE) {
            rack.addLetter(bag.getLetter());
        }
        return false;
    }*/
    
    protected void changeLetters(String letters,Bag bag) {
        for(int i=0;i<letters.length();++i) {
            if (rack.getLetter(letters.charAt(i))==null)
                throw new IllegalArgumentException("Impossible de changer "+letters.charAt(i)+" : lettre non trouvée dans le rack");
        }
        while(rack.length()<Rack.SIZE) {
            rack.addLetter(bag.getLetter());
        }
    }
    /**
     * function printState
     * Show the state of the player, the board and his rack
     * @param board
     */
    /*protected void printState(Board board) {
        System.out.println("Turn of "+name);
        System.out.println("You have "+score+" points");
        System.out.println("Here is the actual state of the board :");
        System.out.println(board);
        System.out.println("Here is the actual state of your rack :");
        System.out.println(rack);
    }*/

}
