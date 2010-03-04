package game.scrabble.view;

import game.scrabble.controller.Controller;

import java.awt.event.*;

import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {

    private JLabel playerName;
    private BoardPanel boardPane;
    
    public GamePanel(Controller c) {
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        playerName = new JLabel("Player ???");
        boardPane = new BoardPanel(c);
        add(playerName);
        add(boardPane);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        
    }
}
