package game.scrabble.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JTextPane;

public class WordDialog extends JDialog implements ActionListener {

    private JTextPane word;
    private JCheckBox horizontal;

    public WordDialog(ScrabbleFrame owner) {
        super(owner,true);
        setLayout(new FlowLayout());
        word = new JTextPane();
        
        horizontal = new JCheckBox("horizontal");
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
        System.out.println(horizontal.isSelected());
        ((ScrabbleFrame) this.getOwner()).playWord(word.getText(),horizontal.isSelected());
        this.dispose();
    }
}
