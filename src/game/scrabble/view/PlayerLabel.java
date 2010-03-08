package game.scrabble.view;

import game.scrabble.controller.PlayerChangedEvent;
import game.scrabble.controller.PlayerListener;

import javax.swing.JLabel;

public class PlayerLabel extends JLabel implements PlayerListener {

    @Override
    public void playerChanged(PlayerChangedEvent event) {
        setText(event.getName()+" - "+event.getScore()+" points ");
    }

}
