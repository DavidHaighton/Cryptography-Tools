package Playfair;

import Framework.AppPane;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class PlayfairPane extends AppPane implements PlayfairView
{
    private Model model = new Model();
    private JPanel controlPanel = new JPanel(), keyPanel = new JPanel(), areaPanel = new JPanel();
    private TitledBorder squareBorder = new TitledBorder("Key Display"),
        keyBorder = new TitledBorder("Key Input"),
        cipherBorder = new TitledBorder("Cipher"),
        plainBorder = new TitledBorder("Plain text");
    private JTextField keyField = new JTextField();
    private JTextArea cipherTextArea = new JTextArea(),
        plainTextArea = new JTextArea();
    private JScrollPane cipherScrollPane = new JScrollPane(cipherTextArea),
        plainScrollPane = new JScrollPane(plainTextArea);
    private SquareDisplay squareDisplay = new SquareDisplay(model);

    public PlayfairPane()
    {
        super("Playfair","Playfair Decoder");
        model.addPlayFairView(this);
        this.setLayout(new GridLayout(1,2,1,0));

        //left side
        this.add(squareDisplay);
        squareDisplay.setBorder(squareBorder);
        //right side
        this.add(controlPanel);
        controlPanel.setLayout(new BorderLayout());

        controlPanel.add(keyPanel, BorderLayout.NORTH);
        keyPanel.setBorder(keyBorder);
        keyPanel.setLayout(new GridLayout(1,1));
        keyPanel.add(keyField);

        controlPanel.add(areaPanel);
        areaPanel.setLayout(new GridLayout(2,1));
        areaPanel.add(cipherScrollPane);
        cipherScrollPane.setBorder(cipherBorder);
        cipherTextArea.setLineWrap(true);
        areaPanel.add(plainScrollPane);
        plainScrollPane.setBorder(plainBorder);
        plainTextArea.setEditable(false);
        plainTextArea.setLineWrap(true);
        //controller
        cipherTextArea.getDocument().addDocumentListener(new CipherHandler());
        keyField.getDocument().addDocumentListener(new KeyHandler());

        setSize(600,300);
        show();
    }



    @Override
    public void plainTextChanged(PlainTextChangedEvent event)
    {
        plainTextArea.setText(event.getPlainText());
    }

    @Override
    public void setInFocus(boolean inFocus)
    {

    }

    private class KeyHandler implements DocumentListener
    {

        @Override
        public void insertUpdate(DocumentEvent e)
        {
            update();
        }

        @Override
        public void removeUpdate(DocumentEvent e)
        {
            update();
        }

        @Override
        public void changedUpdate(DocumentEvent e)
        {
            update();
        }

        private void update()
        {
            model.setKey(keyField.getText());
        }
    }

    private class CipherHandler implements DocumentListener
    {

        @Override
        public void insertUpdate(DocumentEvent e)
        {
            update();
        }

        @Override
        public void removeUpdate(DocumentEvent e)
        {
            update();
        }

        @Override
        public void changedUpdate(DocumentEvent e)
        {
            update();
        }
        private void update()
        {
            model.setCipher(cipherTextArea.getText());
        }
    }

    public static void main(String[] args)
    {
        PlayfairPane app = new PlayfairPane();
        app.showPopupFrame(JFrame.EXIT_ON_CLOSE);

    }
}
