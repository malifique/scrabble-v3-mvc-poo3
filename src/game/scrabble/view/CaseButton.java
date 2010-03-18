package game.scrabble.view;

import java.awt.Color;
import java.awt.Insets;

import game.scrabble.controller.CaseChangedEvent;
import game.scrabble.controller.CaseListener;

import javax.swing.JButton;

public class CaseButton extends JButton implements CaseListener {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private static Color colors[];

	static {
		colors = new Color[5];
		colors[0] = new Color(24, 57, 30);
		colors[1] = new Color(30, 127, 203);
		colors[2] = new Color(0, 0, 255);
		colors[3] = new Color(199, 44, 72);
		colors[4] = new Color(187, 11, 11);
	}

	private int posX, posY;

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public CaseButton(String string, int i, int j) {
		super(string);
		setMargin(new Insets(1, 1, 1, 1));
		this.posX = i;
		this.posY = j;
	}

	public void caseChanged(CaseChangedEvent event) {
		setText(Character.toString(event.getLetter()));
		if (!event.isEmpty())
			setBackground(new Color(255, 203, 96));
		else
			setBackground(colors[(event.getWordMultiplier() > 1 ? 1 + event
					.getWordMultiplier() : event.getLetterMultiplier() - 1)]);
	}
}
