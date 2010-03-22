package game.scrabble.view;

import game.scrabble.controller.Controller;
import game.scrabble.controller.RackChangedEvent;
import game.scrabble.controller.RackListener;
import game.scrabble.model.Board;
import game.scrabble.model.Letter;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RackPanel extends JPanel implements RackListener {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private JPanel rack;

	public RackPanel() {
		setLayout(new FlowLayout());
		rack = new JPanel();
		rack.setLayout(new GridLayout(1, 7, 5, 0));
		add(new JLabel("Rack "));
		add(rack);
	}

	public RackPanel(Controller c) {
		this();
		c.addRackListener(this);
	}

	@Override
	public void rackChanged(RackChangedEvent event) {
		rack.removeAll();

		for (Letter letter : event.getLetters()) {
			CaseButton cb = new CaseButton(letter.toString(), 0, 0);
			cb.setBackground(new Color(255, 203, 96));
			rack.add(cb);
		}
	}

}
