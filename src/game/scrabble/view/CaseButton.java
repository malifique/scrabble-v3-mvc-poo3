package game.scrabble.view;

import java.awt.Color;
import java.awt.Insets;

import game.scrabble.model.CaseChangedEvent;
import game.scrabble.model.CaseListener;

import javax.swing.JButton;

public class CaseButton extends JButton implements CaseListener {

    private static Color colors[];
    
    static {
        colors = new Color[5];
        colors[0] = Color.GRAY;
        colors[1] = Color.CYAN;
        colors[2] = Color.BLUE;
        colors[3] = Color.ORANGE;
        colors[4] = Color.RED;
    }
    
    public CaseButton(String string) {
        super(string);
        setMargin(new Insets(1,1,1,1));
    }

    @Override
    public void caseChanged(CaseChangedEvent event) {
        setText(Character.toString(event.getLetter()));
        setBackground(colors[(event.getWordMultiplier()>1?1+event.getWordMultiplier():event.getLetterMultiplier()-1)]);
    }
}
