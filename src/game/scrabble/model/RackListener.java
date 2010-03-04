package game.scrabble.model;

import java.util.EventListener;

public interface RackListener extends EventListener {
    public void rackChanged(RackChangedEvent event);
}
