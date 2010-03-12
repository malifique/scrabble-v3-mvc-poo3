package game.scrabble.controller;

import game.scrabble.model.Game;

import java.io.FileNotFoundException;

public class Controller {

    private Game game;
    
    public Controller() {
        game = null;
    }
    
    public void startGame(int nb_players, String dico) throws FileNotFoundException {
        game = new Game(nb_players,dico);
    }
    
    public void init() {
        game.init();
    }
    
    public void playTurn(int x, int y, boolean horizontal, String word) {
        game.playTurn(x, y, horizontal, word); // ajouter try catch
    }
    
    public CaseListener addCaseListener(int x, int y, CaseListener l) {
        game.getBoard().addCaseListener(x, y, l);
        return l;
    }
    
    public RackListener addRackListener(int p,RackListener r) {
        game.addRackListener(p,r);
        return r;
    }
    
    public RackListener addRackListener(RackListener r) {
        for(int i=0;i<game.getNbPlayers();i++)
            game.addRackListener(i,r);
        return r;
    }

    public PlayerListener addPlayerListener(int i, PlayerListener pl) {
        game.addPlayerListener(i,pl);
        return pl;
    }

    public PlayerListener addPlayerListener(PlayerListener pl) {
        for(int i=0;i<game.getNbPlayers();i++)
            game.addPlayerListener(i,pl);
        return pl;
    }
    
    
}
