package game.scrabble.view;

import game.scrabble.controller.Controller;
import game.scrabble.model.Board;

import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {

    public BoardPanel(Controller c) {
        LayoutManager layout = new GridLayout(Board.SIZE,Board.SIZE);
        this.setLayout(layout);
        for(int i=0;i<Board.SIZE;i++)
            for(int j=0;j<Board.SIZE;j++)
                add((CaseButton) c.addCaseListener(i, j, new CaseButton(Integer.toString(i))));
    }

}
