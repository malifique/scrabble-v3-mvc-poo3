package game.scrabble.controller;

import java.util.EventListener;

public interface PlayerListener extends EventListener {
    public void playerChanged(PlayerChangedEvent event);
}
