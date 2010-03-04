package game.scrabble.model;

import java.util.EventListener;

public interface CaseListener extends EventListener {
    public void caseChanged(CaseChangedEvent event);
}

