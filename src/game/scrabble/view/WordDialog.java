package game.scrabble.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WordDialog extends JDialog implements ActionListener {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private static String[] positions = { "horizontal", "vertical" };

	private JTextField word;
	private JComboBox orientationComboBox;

	public WordDialog(ScrabbleFrame owner) {
		super(owner, true);

		word = new JTextField(20);
		JPanel orientationPanel = new JPanel();
		orientationPanel.setLayout(new FlowLayout());
		orientationComboBox = new JComboBox(positions);
		orientationComboBox.setPreferredSize(new Dimension(100, 20));
		orientationPanel.add(new JLabel("Orientation"));
		orientationPanel.add(orientationComboBox);
		add(orientationPanel, BorderLayout.NORTH);

		JPanel wordPanel = new JPanel();
		wordPanel.setLayout(new FlowLayout());
		wordPanel.add(new JLabel("Word"));
		wordPanel.add(word);
		add(wordPanel);
		JButton j = new JButton("OK");
		j.setPreferredSize(new Dimension(20, 20));
		j.addActionListener(this);
		add(j, BorderLayout.SOUTH);

		setLocationRelativeTo(null);
		setSize(300, 150);
		setTitle("Enter your word");
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		((ScrabbleFrame) this.getOwner()).playWord(word.getText(),
				(0 == orientationComboBox.getSelectedIndex()));
		this.dispose();
	}
}
