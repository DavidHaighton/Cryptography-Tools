package Core;

import javax.swing.*;

/**
 * This Pane is for any cryptographic protocols to be shown.
 * The main difference between this pane and the last is this one allows for an easier time when implementing panes for algorithms
 */
public abstract class CryptoPane<Cipher, Key, Plain> extends AppPane implements ModelChangeListener
{
    private final JMenu typeMenu = new JMenu("Solve for type");
    private final JRadioButton keyButton = new JRadioButton("Key"),
            cipherButton = new JRadioButton("Cipher"),
            plainButton = new JRadioButton("Plain");
    private final ButtonGroup typeGroup = new ButtonGroup();


    private SymmetricModel model;
    private SymCryptoPartType mode;
    /**
     * Constructs the pane off of the model
     * @param model the model
     * @param acceptableModes which modes are acceptable
     * @param mode the current mode
     */
    public CryptoPane(SymmetricModel<String,String,String> model, int acceptableModes, SymCryptoPartType mode)
    {
        super(model.getAlgorithm().getShortName(), model.getAlgorithm().getName());
        //setup model
        model.addModelListener(this);
        this.model = model;
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
            radioButton.addActionListener(e->{this.mode = mode; setTypeOutput(mode);});
        }
    }

    protected void syncToModel()
    {
        setTypeOutput(mode);
        model.sync();
    }


    /**
     * called when the type of output changes  via the radio buttons
     * @param type the type of output
     */
    private final void setTypeOutput(SymCryptoPartType type)
    {
        setKeyEnabled(type != SymCryptoPartType.KEY);
        setCipherEnabled(type != SymCryptoPartType.CIPHER);
        setPlainEnabled(type != SymCryptoPartType.PLAIN);
    }

    /**
     * called when the the gui part should be enabled or disabled
     * @param isEnabled if it should be disabled or enabled
     */
    protected void setKeyEnabled(boolean isEnabled){}
    /**
     * called when the the gui part should be enabled or disabled
     * @param isEnabled if it should be disabled or enabled
     */
    protected void setCipherEnabled(boolean isEnabled){}
    /**
     * called when the the gui part should be enabled or disabled
     * @param isEnabled if it should be disabled or enabled
     */
    protected void setPlainEnabled(boolean isEnabled){}


    /**
     * called when the model is changed
     * @param event the event to trigger the change
     */
    @Override
    public void modelChanged(ModelChangedEvent event)
    {
        String text = (String)event.getPart();
        switch(getOutputMode())
        {
            case KEY:
                updateKey(event);
                break;
            case PLAIN:
                updatePlain(event);
                break;
            default:
                updateCipher(event);
        }
    }

    /**
     * Called when the model changes this types value
     * @param e the event that triggered it
     */
    protected void updateKey(ModelChangedEvent e){}
    /**
     * Called when the model changes this types value
     * @param e the event that triggered it
     */
    protected void updatePlain(ModelChangedEvent e){}
    /**
     * Called when the model changes this types value
     * @param e the event that triggered it
     */
    protected void updateCipher(ModelChangedEvent e){}

    /**
     * returns the symmetric model
     * @return the symmetric model
     */
    public SymmetricModel<Cipher, Key, Plain> getModel()
    {
        return model;
    }

    /**
     * returns the output mode
     * @return the output mode
     */
    public SymCryptoPartType getOutputMode()
    {
        return mode;
    }






}
