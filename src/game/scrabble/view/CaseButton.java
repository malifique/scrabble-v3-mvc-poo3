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
        colors[0] = Color.GRAY;
        colors[1] = Color.CYAN;
        colors[2] = Color.BLUE;
        colors[3] = Color.ORANGE;
        colors[4] = Color.RED;
    }

    private int posX,posY;
    
    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public CaseButton(String string, int i, int j) {
        super(string);
        setMargin(new Insets(1,1,1,1));
        this.posX=i;
        this.posY=j;
    }

    public void caseChanged(CaseChangedEvent event) {
        setText(Character.toString(event.getLetter()));
        if(!event.isEmpty())
            setBackground(Color.WHITE);
        else
            setBackground(colors[(event.getWordMultiplier()>1?1+event.getWordMultiplier():event.getLetterMultiplier()-1)]);
    }
}
