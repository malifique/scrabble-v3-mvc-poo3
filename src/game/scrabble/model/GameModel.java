package game.scrabble.model;

import game.scrabble.controller.CaseListener;
import game.scrabble.controller.PlayerListener;
import game.scrabble.controller.RackListener;

public interface GameModel {

    void init();

    boolean lookupDictionary(String word);

    boolean playTurn(int x, int y, boolean horizontal, String word);

    void skipTurn(String letters);

    void changeCurrentPlayerName(String name);

    void addRackListener(int p, RackListener r);

    int getNbPlayers();

    void addPlayerListener(int i, PlayerListener pl);

    void addCaseListener(int x, int y, CaseListener l);

}
