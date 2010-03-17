package game.scrabble.model;

import game.scrabble.controller.PlayerListener;
import game.scrabble.controller.RackListener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 
 * @author BERNIGAUD Côme
 * 
 */
public class Game {

	public static final String PATH_BAG = "./etc/bag.txt";
	private Board board;
	private Player players[];
	private Bag bag;
	private int currentPlayer;

	public Game(int nb_players, String dico) throws FileNotFoundException {
		players = new Player[nb_players];
		bag = new Bag(Game.getLetters());
		for (int i = 0; i < nb_players; i++)
			players[i] = new Player(bag);
		board = new Board(dico);
	}

	public Game(Bag bag, Player players[], Board board) {
		this.bag = bag;
		this.players = players;
		this.board = board;
	}

	public boolean playTurn(int x, int y, boolean horizontal, String word) {
		int turnScore = board.addWord(x, y, horizontal, word,
				players[currentPlayer].getRack());
		if (turnScore != 0) {
			players[currentPlayer].addScore(turnScore);
			while (players[currentPlayer].getRack().length() < Rack.SIZE) {
				players[currentPlayer].getRack().addLetter(bag.getLetter());
			}
			currentPlayer = (currentPlayer + 1) % players.length;
			players[currentPlayer].firePlayerChanged();
			return true;
		}
		return false;
	}

	public void changeCurrentPlayerName(String name) {
		players[currentPlayer].setName(name);
		players[currentPlayer].firePlayerChanged();
	}

	public void skipTurn() {
		currentPlayer = (currentPlayer + 1) % players.length;
		players[currentPlayer].firePlayerChanged();
	}

	public Board getBoard() {
		return board;
	}

	public void play() {
		for (int i = 0; !players[i++].playTurn(board, bag);) {
			if (i >= players.length)
				i = 0;
		}
	}

	/**
	 * @author Mathieu Rul
	 * @return letters of a classic scrabble bag
	 */
	public static ArrayList<Letter> getLetters() {
		ArrayList<Letter> letters = new ArrayList<Letter>();
		BufferedReader bagText;
		String line = "";
		try {
			// Open the bag file
			bagText = new BufferedReader(new FileReader(new File(PATH_BAG)));
			if (bagText == null)
				throw new FileNotFoundException("File not found: " + PATH_BAG);

			// Fill the bag array
			do {
				line = bagText.readLine();
				if (line != null) {
					StringTokenizer st = new StringTokenizer(line);

					while (st.hasMoreTokens()) {
						String strLetter = st.nextToken();
						if (st.hasMoreTokens()) {
							if (st.hasMoreTokens()) {
								Integer count = Integer
										.parseInt(st.nextToken());
								Integer score = Integer
										.parseInt(st.nextToken());
								if (strLetter.equalsIgnoreCase("*")) {
									for (; 0 != count; count--)
										letters.add(new Joker());
								} else {
									Letter l = new Letter(score, strLetter
											.charAt(0));
									for (; 0 != count; count--)
										letters.add(l);
								}
							}
						}
					}
				}
			} while (line != null);
			bagText.close();
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			return getDefaultLetters();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return getDefaultLetters();
		}
		return letters;
	}

	public static ArrayList<Letter> getDefaultLetters() {
		ArrayList<Letter> letters = new ArrayList<Letter>();
		// ajouter le contenu classique d'un jeu de scrabble
		/*
		 * 2 jokers (0 point) 1 point : E (12), A (9), I (9), O (8), R (6), N
		 * (6), T (6), L (4), S (4) U (4) 2 points : D (4), G (3) 3 points : B
		 * (2), C (2), M (2), P (2) 4 points : F (2), H (2), V (2), W (2), Y (2)
		 * 5 points : K (1) 8 points : J (1), X (1) 10 points : Q (1), Z (1)
		 */
		int i;
		Letter a = new Letter(1, 'a');
		Letter I = new Letter(1, 'i');
		Letter e = new Letter(1, 'e');
		Letter o = new Letter(1, 'o');
		Letter r = new Letter(1, 'r');
		Letter n = new Letter(1, 'n');
		Letter t = new Letter(1, 't');
		Letter l = new Letter(1, 'l');
		Letter s = new Letter(1, 's');
		Letter u = new Letter(1, 'u');
		for (i = 0; i < 9; i++)
			letters.add(a);
		for (i = 0; i < 9; i++)
			letters.add(I);
		for (i = 0; i < 12; i++)
			letters.add(e);
		for (i = 0; i < 8; i++)
			letters.add(o);
		for (i = 0; i < 6; i++)
			letters.add(r);
		for (i = 0; i < 6; i++)
			letters.add(n);
		for (i = 0; i < 6; i++)
			letters.add(t);
		for (i = 0; i < 4; i++)
			letters.add(l);
		for (i = 0; i < 4; i++)
			letters.add(s);
		for (i = 0; i < 4; i++)
			letters.add(u);

		Letter d = new Letter(2, 'd');
		Letter g = new Letter(2, 'g');
		for (i = 0; i < 4; i++)
			letters.add(d);
		for (i = 0; i < 3; i++)
			letters.add(g);

		Letter b = new Letter(2, 'b');
		Letter c = new Letter(2, 'c');
		Letter m = new Letter(2, 'm');
		Letter p = new Letter(2, 'p');
		for (i = 0; i < 2; i++)
			letters.add(b);
		for (i = 0; i < 2; i++)
			letters.add(c);
		for (i = 0; i < 2; i++)
			letters.add(m);
		for (i = 0; i < 2; i++)
			letters.add(p);

		Letter f = new Letter(4, 'f');
		Letter h = new Letter(4, 'h');
		Letter v = new Letter(4, 'v');
		Letter w = new Letter(4, 'w');
		Letter y = new Letter(4, 'y');
		for (i = 0; i < 2; i++)
			letters.add(f);
		for (i = 0; i < 2; i++)
			letters.add(h);
		for (i = 0; i < 2; i++)
			letters.add(v);
		for (i = 0; i < 2; i++)
			letters.add(w);
		for (i = 0; i < 2; i++)
			letters.add(y);

		letters.add(new Letter(5, 'k'));
		letters.add(new Letter(8, 'j'));
		letters.add(new Letter(8, 'x'));
		letters.add(new Letter(10, 'q'));
		letters.add(new Letter(10, 'z'));

		letters.add(new Joker());
		letters.add(new Joker());

		return letters;
	}

	public void addRackListener(int p, RackListener r) {
		players[p].getRack().addListener(r);
		players[p].getRack().fireRackChanged();
	}

	public void init() {
		players[currentPlayer].firePlayerChanged();
		players[currentPlayer].getRack().fireRackChanged();
	}

	public int getNbPlayers() {
		return players.length;
	}

	public void addPlayerListener(int i, PlayerListener pl) {
		players[i].addListener(pl);
		players[i].firePlayerChanged();

	}

	/*
	 * public static void main(String[] args) { Player players[] = new
	 * ConsolePlayer[2]; Bag bag = new Bag(Game.getLetters()); players[0] = new
	 * ConsolePlayer(bag); players[1] = new ConsolePlayer(bag); try { String
	 * dico=""; if(args.length > 0) dico=args[0]; else dico="./anglais.txt";
	 * Game game = new Game(bag , players, new Board(dico)); game.play(); }
	 * catch (FileNotFoundException e) {
	 * System.out.println("ERROR : Unable to find the dictionary."); } }
	 */
}
