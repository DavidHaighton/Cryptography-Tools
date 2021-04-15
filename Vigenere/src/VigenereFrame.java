import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class VigenereFrame extends JFrame implements PlainTextView
{
    private Model model = new Model();

    private JPanel keyPanel = new JPanel(), knownPanel = new JPanel();
    private TitledBorder keyBorder = new TitledBorder("Key"),
        cipherBorder = new TitledBorder("Cipher"),
        plainBorder = new TitledBorder("Plain Text");



    private JTextArea cipherTextArea = new JTextArea(),
            plainTextArea = new JTextArea();
    private JTextField keyField = new JTextField();
    private JSpinner offsetSpinner = new JSpinner();

    private JScrollPane cipherScrollPane = new JScrollPane(cipherTextArea),
            plainScrollPane = new JScrollPane(plainTextArea);
    public VigenereFrame()
    {
        super("Vigenere Cipher Tool");
        model.addPlainTextView(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(1,2,2,0));
        this.setBackground(Color.BLACK);

        //left side
        this.add(knownPanel);
        knownPanel.setLayout(new BorderLayout());
        knownPanel.add(keyPanel,BorderLayout.NORTH);
        keyPanel.setBorder(keyBorder);
        keyPanel.setLayout(new BorderLayout());
        keyPanel.add(keyField,BorderLayout.CENTER);

        SpinnerNumberModel spinnerModel = new SpinnerNumberModel();
        spinnerModel.setStepSize(1);
        spinnerModel.setMinimum(0);

        offsetSpinner.setModel(spinnerModel);
        keyPanel.add(offsetSpinner, BorderLayout.EAST);

        cipherScrollPane.setBorder(cipherBorder);
        knownPanel.add(cipherScrollPane);
        //right side
        plainScrollPane.setBorder(plainBorder);
        this.add(plainScrollPane);
        plainTextArea.setEditable(false);

        cipherTextArea.setLineWrap(true);
        plainTextArea.setLineWrap(true);

        this.cipherTextArea.getDocument().addDocumentListener(new CipherHandler());
        this.keyField.getDocument().addDocumentListener(new KeyHandler());
        this.offsetSpinner.addChangeListener(e->{model.setKeyWrapOffset((int)offsetSpinner.getValue());});

        setSize(600,300);
        show();
    }


    @Override
    public void plainTextUpdated(PlainUpdatedEvent evt)
    {
        this.plainTextArea.setText(evt.getPlainText());
    }

    public static void main(String[] args) {
        new VigenereFrame();
    }

    private class CipherHandler implements DocumentListener
    {

        @Override
        public void insertUpdate(DocumentEvent e)
        {
            updateCipher();
        }

        @Override
        public void removeUpdate(DocumentEvent e)
        {
            updateCipher();
        }

        @Override
        public void changedUpdate(DocumentEvent e)
        {
            updateCipher();
        }

        private void updateCipher()
        {
            model.setCipher(cipherTextArea.getText());
            model.updatePlain();
        }
    }

    private class KeyHandler implements DocumentListener
    {

        @Override
        public void insertUpdate(DocumentEvent e)
        {
            updateCipher();
        }

        @Override
        public void removeUpdate(DocumentEvent e)
        {
            updateCipher();
        }

        @Override
        public void changedUpdate(DocumentEvent e)
        {
            updateCipher();
        }
        private void updateCipher()
        {
            model.setKey(keyField.getText());
            model.updatePlain();
        }
    }
}
