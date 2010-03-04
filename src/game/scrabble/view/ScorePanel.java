package game.scrabble.view;

import game.scrabble.controller.Controller;
import game.scrabble.model.PlayerChangedEvent;
import game.scrabble.model.PlayerListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class ScorePanel extends JPanel {

    JLabel score;
    
    public ScorePanel(Controller c) {
        LayoutManager layout = new FlowLayout();
        this.setLayout(layout);
        for(int i=0;i<2;i++) {
            PlayerLabel pl = new PlayerLabel();
            add(pl);
            c.addPlayerListener(i,pl);
        }
        setBackground(Color.YELLOW);
    }

}
