package game.scrabble.view;

import game.scrabble.controller.Controller;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ScrabbleFrame extends JFrame {
    
    private GamePanel gp;
    private Controller c;
    
    ScrabbleFrame(Controller c) {
        this.c = c;
        setLayout(new BorderLayout());
        gp = new GamePanel(c,this);
        add(gp,BorderLayout.CENTER);
        add(new ScorePanel(c),BorderLayout.NORTH);
        add(new RackPanel(c),BorderLayout.SOUTH);
        setSize(440, 500);
        setTitle("Scrabble4ever");
    }
    
    public void playWord(String word,boolean horizontal) {
        try {
            c.playTurn(gp.getBoardPane().getActiveX(), gp.getBoardPane().getActiveY(), horizontal, word);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "error", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        String dico="";
        if(args.length > 0)
            dico=args[0];
        else
            dico="./anglais.txt";
        Controller c = new Controller();
        try {
            c.startGame(2, dico);
            ScrabbleFrame s = new ScrabbleFrame(c);
            s.setVisible(true);
            c.init();
        } catch (FileNotFoundException e) {
            System.out.println("Le dico n'a pas été trouvé");
        }
    }
    
}
