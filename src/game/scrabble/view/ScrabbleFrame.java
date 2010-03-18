package game.scrabble.view;

import game.scrabble.controller.Controller;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ScrabbleFrame extends JFrame {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private GamePanel gp;
	private Controller c;

	ScrabbleFrame(final Controller c) {
		this.c = c;
		setLayout(new BorderLayout());
		gp = new GamePanel(c, this);
		add(gp, BorderLayout.CENTER);
		add(new ScorePanel(c), BorderLayout.NORTH);
		add(new RackPanel(c), BorderLayout.SOUTH);
		setSize(440, 500);
		setTitle("Scrabble4ever");
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu fileMenu = new JMenu("Menu");
		menuBar.add(fileMenu);

		JMenuItem helpMenuItem = new JMenuItem("Help");
		helpMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(null, "Scrabble Help\nClick on a case to put a word", "Help",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		fileMenu.add(helpMenuItem);

		JMenuItem aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(null, "Scrabble v3 by\nBERNIGAUD Come\nand\nRUL Mathieu", "About",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		fileMenu.add(aboutMenuItem);

		JMenuItem quitMenuItem = new JMenuItem("Quit");
		quitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				System.exit(0);
			}
		});
		fileMenu.add(quitMenuItem);

		JMenu playerMenu = new JMenu("Player");
		menuBar.add(playerMenu);

		JMenuItem skipMenuItem = new JMenuItem("Skip turn and change letter");
		skipMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String letters = JOptionPane.showInputDialog(null,
						"Select letters to chnage", "Change letters",
						JOptionPane.QUESTION_MESSAGE);
				c.skipTurn(letters);
			}
		});
		playerMenu.add(skipMenuItem);

		JMenuItem changeMenuItem = new JMenuItem("Change name");
		changeMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String newName = JOptionPane.showInputDialog(null,
						"Change your player name", "Change player name",
						JOptionPane.QUESTION_MESSAGE);

				if (newName.isEmpty()) {
					JOptionPane.showMessageDialog(Frame.getFrames()[0],
							"Your name is empty", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					c.changeCurrentPlayerName(newName);
				}

			}
		});
		playerMenu.add(changeMenuItem);

		JMenu gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);

		JMenuItem lookupMenuItem = new JMenuItem("Lookup word");
		lookupMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String word = JOptionPane.showInputDialog(null,
						"Enter your word", "Lookup word",
						JOptionPane.QUESTION_MESSAGE);

				if (word.isEmpty()) {
					JOptionPane.showMessageDialog(Frame.getFrames()[0],
							"Your word is empty", "Error",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, word + " is "
							+ (c.lookupDictionary(word) ? "" : "not ")
							+ "in dictionary.", "Lookup word",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		gameMenu.add(lookupMenuItem);
	}

	public void playWord(String word, boolean horizontal) {
		try {
			/*
			System.out.println("word " + word + " horizontal : " + horizontal
					+ " (" + gp.getBoardPane().getActiveX() + ","
					+ gp.getBoardPane().getActiveY() + ")");
			*/
			c.playTurn(gp.getBoardPane().getActiveX(), gp.getBoardPane()
					.getActiveY(), horizontal, word);
		} catch (IllegalArgumentException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "error",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	public static void main(String[] args) {
		String dico = "";
		if (args.length > 0)
			dico = args[0];
		else
			dico = "./etc/anglais.txt";
		Controller c = new Controller();
		try {
			c.startGame(2, dico);
			ScrabbleFrame s = new ScrabbleFrame(c);
			s.setVisible(true);
			c.init();
		} catch (FileNotFoundException e) {
			System.out.println("Le dico n'a pas été trouvé");
		}
	}

}
