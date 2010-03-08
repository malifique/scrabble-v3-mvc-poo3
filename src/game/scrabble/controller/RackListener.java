package game.scrabble.controller;

import java.util.EventListener;

public interface RackListener extends EventListener {
    public void rackChanged(RackChangedEvent event);
}
