package game.scrabble.view;

import game.scrabble.controller.Controller;

import java.awt.event.*;

import javax.swing.*;

public class GamePanel extends JPanel {

    private PlayerLabel playerName;
    private BoardPanel boardPane;
    
    public GamePanel(Controller c) {
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        playerName = new PlayerLabel();
        c.addPlayerListener(playerName);
        boardPane = new BoardPanel(c);
        add(playerName);
        add(boardPane);
    }
}
