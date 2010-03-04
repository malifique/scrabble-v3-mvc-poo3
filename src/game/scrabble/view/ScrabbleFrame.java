package game.scrabble.view;

import game.scrabble.controller.Controller;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

public class ScrabbleFrame extends JFrame {

    ScrabbleFrame(Controller c) {
        setLayout(new BorderLayout());
        add(new GamePanel(c),BorderLayout.CENTER);
        add(new ScorePanel(c),BorderLayout.NORTH);
        add(new RackPanel(c),BorderLayout.SOUTH);
        setSize(440, 500);
        setName("Scrabble4ever");
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
