package game.scrabble.view;

import game.scrabble.controller.Controller;
import game.scrabble.model.Board;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class BoardPanel extends JPanel implements ActionListener {

    private int aX,aY;
    private ScrabbleFrame scrabbleFrame;
    
    public int getActiveX() {
        return aX;
    }

    public int getActiveY() {
        return aY;
    }

    public BoardPanel(Controller c,ScrabbleFrame sf) {
        LayoutManager layout = new GridLayout(Board.SIZE,Board.SIZE);
        this.setLayout(layout);
        for(int i=0;i<Board.SIZE;i++)
            for(int j=0;j<Board.SIZE;j++) {
                CaseButton cb = new CaseButton(Integer.toString(i),i,j);
                c.addCaseListener(i, j, cb);
                add(cb);
                cb.addActionListener(this);
            }
        scrabbleFrame = sf;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        
        aX = ((CaseButton) arg0.getSource()).getPosX();
        aY = ((CaseButton) arg0.getSource()).getPosY();
        new WordDialog(scrabbleFrame);
    }

}
