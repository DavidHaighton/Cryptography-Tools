package Core;

import javax.swing.border.TitledBorder;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * A default cryptography pane used for String types.
 */
public class DefaultAppStringPane extends CryptoPane<String,String,String>
{

    private final JTextArea cipherTextArea = new JTextArea(),
            plainTextArea = new JTextArea();
    private final JScrollPane cipherScrollPane = new JScrollPane(cipherTextArea),
            plainScrollPane = new JScrollPane(plainTextArea);
    private final JTextField keyField = new JTextField();
    private final JPanel keyPanel = new JPanel(),
        encryptPanel = new JPanel();


    /**
     * Constructs DecoderPane
     *
     * @param model being used to do the math
     * @param acceptableModes modes being allowed in the model (solve for key, solve for cipher, solve for plain)
     */
    public DefaultAppStringPane(SymmetricModel<String,String,String> model, int acceptableModes, SymCryptoPartType mode)
    {
        super(model,acceptableModes,mode);
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
        syncToModel();
    }


    /**
     * called when the the gui part should be enabled or disabled
     *
     * @param isEnabled if it should be disabled or enabled
     */
    @Override
    protected void setKeyEnabled(boolean isEnabled)
    {
        this.keyField.setEditable(isEnabled);
    }

    /**
     * called when the the gui part should be enabled or disabled
     *
     * @param isEnabled if it should be disabled or enabled
     */
    @Override
    protected void setCipherEnabled(boolean isEnabled)
    {
        this.cipherTextArea.setEditable(isEnabled);
    }

    /**
     * called when the the gui part should be enabled or disabled
     *
     * @param isEnabled if it should be disabled or enabled
     */
    @Override
    protected void setPlainEnabled(boolean isEnabled)
    {
        this.plainTextArea.setEditable(isEnabled);
    }

    /**
     * Called when the model changes this types value
     *
     * @param e the event that triggered it
     */
    @Override
    protected void updateKey(ModelChangedEvent e)
    {
        this.keyField.setText((String)e.getPart());
    }

    /**
     * Called when the model changes this types value
     *
     * @param e the event that triggered it
     */
    @Override
    protected void updatePlain(ModelChangedEvent e)
    {
        this.plainTextArea.setText((String)e.getPart());
    }

    /**
     * Called when the model changes this types value
     *
     * @param e the event that triggered it
     */
    @Override
    protected void updateCipher(ModelChangedEvent e)
    {
        cipherTextArea.setText((String)e.getPart());
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
            if(getOutputMode()!=srcType)
            {
                getModel().setCryptographyPart(text,srcType);//reads from the one that changes

                getModel().updateCryptoPart(getOutputMode());//updates the output
            }

        }
    }



}
