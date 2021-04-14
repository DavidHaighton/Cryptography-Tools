import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class CharacterMapPanel extends JPanel implements RelationView
{
    private final JLabel cipherLabel = new JLabel();
    private JTextField plainField = new JTextField();

    private final char cipherLetter;
    private Model model;
    public CharacterMapPanel(char cipherLetter, Model model)
    {
        this.cipherLetter = cipherLetter;
        this.setLayout(new GridLayout(1,2));
        this.add(cipherLabel);
        this.add(plainField);

        this.cipherLabel.setText(cipherLetter+"");
        char plainLetter = model.getRelation().get(cipherLetter);
        this.setPlainChar(plainLetter);
        this.model = model;

        plainField.getDocument().addDocumentListener(new PlainHandler());
        model.addRelationView(this);
    }

    public char getCipherChar()
    {
        return this.cipherLetter;
    }


    private void setPlainChar(char plain)
    {
        this.plainField.setText(plain+"");
    }

    public char getPlainChar()
    {
        return plainField.getText().charAt(0);
    }

    @Override
    public void relationChanged(RelationChangedEvent evt)
    {
        this.setPlainChar(evt.getRelation().get(this.getCipherChar()));
    }

    private class PlainHandler implements DocumentListener
    {

        @Override
        public void insertUpdate(DocumentEvent e)
        {
            handle();
        }

        @Override
        public void removeUpdate(DocumentEvent e)
        {

        }

        @Override
        public void changedUpdate(DocumentEvent e)
        {
            handle();
        }

        private void handle()
        {
            if(!plainField.getText().equals(""))
                model.changeLetterRelation(cipherLetter,getPlainChar());
        }
    }

}
