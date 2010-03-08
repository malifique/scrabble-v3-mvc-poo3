package game.scrabble.view;

import game.scrabble.controller.Controller;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class ScorePanel extends JPanel {

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
