package game.scrabble.view;

import game.scrabble.controller.Controller;
import game.scrabble.model.RackChangedEvent;
import game.scrabble.model.RackListener;

import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class RackPanel extends JPanel implements RackListener {

    private JLabel rack; 
    
    public RackPanel() {
        LayoutManager layout = new FlowLayout();
        this.setLayout(layout);
        // créer un label rack par joueur, l'ajouter en listener.
        rack = new JLabel("Rack "+"");
        add(rack);
    }
    
    public RackPanel(Controller c) {
        this();
        c.addRackListener(this);
    }

    @Override
    public void rackChanged(RackChangedEvent event) {
        rack.setText("Rack "+event.getLetters());
    }

}
