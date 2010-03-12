package game.scrabble.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

public class WordDialog extends JDialog implements ActionListener {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static String[] positions = {"horizontal","vertical"};
    
    private JTextField word;
    //private JCheckBox horizontal;
    private JButton horizontal;
    private int actualPosition;
    
    public WordDialog(ScrabbleFrame owner) {
        super(owner,true);
        setLayout(new FlowLayout());
        word = new JTextField(20);
        //horizontal = new JCheckBox("horizontal");
        actualPosition = 0;
        horizontal = new JButton(positions[0]);
        horizontal.addActionListener(this);
        horizontal.setFocusable(false);
        add(horizontal);
        add(word);
        JButton j = new JButton("OK");
        add(j);
        j.addActionListener(this);
        setSize(500, 100);
        setVisible(true);
        setTitle("Enter your word");
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if(arg0.getSource().equals(horizontal)) {
            actualPosition = (actualPosition+1)%positions.length;
            horizontal.setText(positions[actualPosition]);
        } else {
            System.out.println(horizontal.isSelected());
            ((ScrabbleFrame) this.getOwner()).playWord(word.getText(),(horizontal.getText()=="horizontal"));
            this.dispose();
        }
    }
}
