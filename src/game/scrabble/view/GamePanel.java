package game.scrabble.view;

import game.scrabble.controller.Controller;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class GamePanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private PlayerLabel playerName;
    private BoardPanel boardPane;
    
    public GamePanel(Controller c, ScrabbleFrame sf) {
        super();
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        playerName = new PlayerLabel(false);
        c.addPlayerListener(playerName);
        boardPane = new BoardPanel(c,sf);
        add(playerName);
        add(boardPane);
    }

    public BoardPanel getBoardPane() {
        return boardPane;
    }
}
