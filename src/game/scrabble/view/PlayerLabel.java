package game.scrabble.view;

import game.scrabble.controller.PlayerChangedEvent;
import game.scrabble.controller.PlayerListener;

import javax.swing.JLabel;

public class PlayerLabel extends JLabel implements PlayerListener {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private boolean showScore;
    
    PlayerLabel(boolean showScore) {
        this.showScore = showScore;
    }
    
    @Override
    public void playerChanged(PlayerChangedEvent event) {
        if(showScore)
            setText(event.getName()+" - "+event.getScore()+" points ");
        else
            setText(event.getName());
    }

}
