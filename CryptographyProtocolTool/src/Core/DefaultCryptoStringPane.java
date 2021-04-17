package Core;

import javax.swing.border.TitledBorder;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * A default cryptography pane used for String types.
 */
public class DefaultCryptoStringPane extends CryptoPane implements ModelChangeListener{

    private SymCryptoPartType mode;
    private final SymmetricModel<String,String,String> model;
    private final JTextArea cipherTextArea = new JTextArea(),
            plainTextArea = new JTextArea();
    private final JScrollPane cipherScrollPane = new JScrollPane(cipherTextArea),
            plainScrollPane = new JScrollPane(plainTextArea);
    private final JTextField keyField = new JTextField();
    private final JPanel keyPanel = new JPanel(),
        encryptPanel = new JPanel();
    private final JMenu typeMenu = new JMenu("Solve for type");
    private final JRadioButton keyButton = new JRadioButton("Key"),
        cipherButton = new JRadioButton("Cipher"),
        plainButton = new JRadioButton("Plain");
    private final ButtonGroup typeGroup = new ButtonGroup();


    /**
     * Constructs DecoderPane
     *
     * @param model being used to do the math
     * @param acceptableModes modes being allowed in the model (solve for key, solve for cipher, solve for plain)
     */
    public DefaultCryptoStringPane(SymmetricModel<String,String,String> model, int acceptableModes, SymCryptoPartType mode)
    {
        super(model.getAlgorithm().getShortName(), model.getAlgorithm().getName());
        //setup model
        this.model = model;
        this.model.addModelListener(this);
        //sanitize input
        if(acceptableModes>7||acceptableModes<1 || ((mode.value|acceptableModes)!=acceptableModes))
        {
            throw new IllegalArgumentException("the acceptable modes must contain the current mode");
        }
        //setup menu
        this.mode = mode;
        this.addMenu(typeMenu);
        setupRadioButton(keyButton,SymCryptoPartType.KEY, acceptableModes);
        setupRadioButton(cipherButton,SymCryptoPartType.CIPHER, acceptableModes);
        setupRadioButton(plainButton,SymCryptoPartType.PLAIN, acceptableModes);
        disableInputFromOutput();

        //gui-setup
        this.setLayout(new GridLayout(1,2,1,1));
        //gui-left side
        this.add(encryptPanel);
        encryptPanel.setLayout(new BorderLayout(1,3));
        encryptPanel.add(keyPanel,BorderLayout.NORTH);
        keyPanel.setBorder(new TitledBorder("Key"));
        keyPanel.setLayout(new GridLayout(1,1));
        keyPanel.add(keyField);

        encryptPanel.add(plainScrollPane, BorderLayout.CENTER);
        plainScrollPane.setBorder(new TitledBorder("Plain Text"));

        //gui-right side
        this.add(cipherScrollPane);
        cipherScrollPane.setBorder(new TitledBorder("Cipher Text"));
        //gui-general
        this.setSize(500,500);

        //Controller-setup
        DocumentHandler controller = new DocumentHandler();
        keyField.getDocument().addDocumentListener(controller);
        plainTextArea.getDocument().addDocumentListener(controller);
        cipherTextArea.getDocument().addDocumentListener(controller);

    }

    /**
     * can setup up a radio button according to the following properties
     * @param radioButton radiobutton to be setup
     * @param mode the mode the radio button sets the app to
     * @param acceptableModes the acceptable modes allowed
     */
    private void setupRadioButton(JRadioButton radioButton, SymCryptoPartType mode,int acceptableModes)
    {
        if((acceptableModes&mode.value)!=0)
        {

            typeMenu.add(radioButton);
            radioButton.setSelected(true);
            typeGroup.add(radioButton);
            radioButton.addActionListener(e->{this.mode = mode; disableInputFromOutput();});
        }
    }

    /**
     *
     * @param inFocus if the panel is in or out of focus
     */
    @Override
    public void setInFocus(boolean inFocus) { }

    /**
     * called when the model is changed
     * @param event the event to trigger the change
     */
    @Override
    public void modelChanged(ModelChangedEvent event)
    {
        String text = (String)event.getPart();
        switch(mode)
        {
            case KEY:
                keyField.setText(text);
                break;
            case PLAIN:
                plainTextArea.setText(text);
                break;
            default:
                cipherTextArea.setText(text);
        }
    }


    /**
     * disables the output textbox and leaves all the other text boxes enabled
     */
    private void disableInputFromOutput()
    {
        cipherTextArea.setEditable(mode!=SymCryptoPartType.CIPHER);
        plainTextArea.setEditable(this.mode!=SymCryptoPartType.PLAIN);
        keyField.setEditable(this.mode!=SymCryptoPartType.KEY);
    }


    /**
     * deals with when the text boxes change
     */
    private class DocumentHandler implements DocumentListener
    {

        @Override
        public void insertUpdate(DocumentEvent e)
        {
            updateModel(e);
        }

        @Override
        public void removeUpdate(DocumentEvent e)
        {
            updateModel(e);
        }

        @Override
        public void changedUpdate(DocumentEvent e)
        {
            updateModel(e);
        }

        /**
         * updates the model text in the correct part and updates the output
         * @param e the event to trigger it
         */
        private void updateModel(DocumentEvent e)
        {
            SymCryptoPartType srcType;
            String text;
            if(e.getDocument()==cipherTextArea.getDocument())
            {
                text=cipherTextArea.getText();
                srcType = SymCryptoPartType.CIPHER;
            }
            else if(e.getDocument()==plainTextArea.getDocument())
            {
                text=plainTextArea.getText();
                srcType = SymCryptoPartType.PLAIN;
            }
            else //must be key
            {
                text = keyField.getText();
                srcType = SymCryptoPartType.KEY;
            }
            if(mode!=srcType)
            {
                model.setCryptographyPart(text,srcType);//reads from the one that changes

                model.updateCryptoPart(mode);//updates the output
            }

        }
    }



}
