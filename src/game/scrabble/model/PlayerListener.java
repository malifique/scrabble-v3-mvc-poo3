package game.scrabble.model;

import java.util.EventListener;

public interface PlayerListener extends EventListener {
    public void playerChanged(PlayerChangedEvent event);
}
