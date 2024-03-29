package game.scrabble.controller;

import game.scrabble.model.Game;
import game.scrabble.model.GameModel;

import java.io.FileNotFoundException;

public class Controller {

	private GameModel game;

	public Controller() {
		game = null;
	}

	public void startGame(int nb_players, String dico) throws FileNotFoundException {
		game = new Game(nb_players, dico);
	}

	public void init() {
		game.init();
	}
	
	public boolean lookupDictionary(String word) {
		return game.lookupDictionary(word);
	}

	public void playTurn(int x, int y, boolean horizontal, String word) {
		game.playTurn(x, y, horizontal, word); // ajouter try catch
	}

	public void skipTurn(String letters) {
		game.skipTurn(letters);
	}

	public void changeCurrentPlayerName(String name) {
		game.changeCurrentPlayerName(name);
	}

	public CaseListener addCaseListener(int x, int y, CaseListener l) {
		game.addCaseListener(x, y, l);
		return l;
	}

	public RackListener addRackListener(int p, RackListener r) {
		game.addRackListener(p, r);
		return r;
	}

	public RackListener addRackListener(RackListener r) {
		for (int i = 0; i < game.getNbPlayers(); i++)
			game.addRackListener(i, r);
		return r;
	}

	public PlayerListener addPlayerListener(int i, PlayerListener pl) {
		game.addPlayerListener(i, pl);
		return pl;
	}

	public PlayerListener addPlayerListener(PlayerListener pl) {
		for (int i = 0; i < game.getNbPlayers(); i++)
			game.addPlayerListener(i, pl);
		return pl;
	}

}
