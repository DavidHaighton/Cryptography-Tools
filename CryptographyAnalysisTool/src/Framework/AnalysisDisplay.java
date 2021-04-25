package Framework;

import javax.swing.*;

/**
 * A subclass of display panel used to display the algorithms in action
 */
public class AnalysisDisplay extends JPanel
{
    private final Model model;
    private final String name;
    private final SimpleController controller;
    public AnalysisDisplay(final CryptoAlgorithm algorithm)
    {
        this.name = algorithm.getName();
        this.model = new Model(algorithm);
        this.controller = new SimpleController(model);
    }


    /**
     * The model of the display
     * @return
     */
    public final Model getModel()
    {
        return model;
    }

    /**
     * returns the name of the algorithm display
     * @return the name of the algorithm display
     */
    @Override
    public final String getName()
    {
        return name;
    }

    /**
     * returns the controller
     * @return the controller
     */
    protected final SimpleController getController()
    {
        return this.controller;
    }



}
