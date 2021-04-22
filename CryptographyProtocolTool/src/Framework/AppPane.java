package Framework;

import FrequencyAnalyzer.AnalyzerPane;
import MonoAlphabetic.MonoAlphabeticPane;
import Playfair.PlayfairPane;
import Vigenere.VigenereAppPane;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Standard Panel that all main cryptography panels must inherit from to be shown in the complete application
 */
public abstract class AppPane extends JComponent
{
    private final String shortName, name;
    private List<JComponent> menus = new ArrayList<>(4);

    /**
     * Constructs DecoderPane
     * @param shortName short short name of the algorithm
     * @param name name of the algorithm
     */
    public AppPane(String shortName, String name)
    {
        this.shortName = shortName;
        this.name = name;
    }

    /**
     * Called this panel is set in or out of focus.
     * It should clean other windows up if it is out of focus and can display windows if it is in focus
     * @param inFocus if the panel is in or out of focus
     */
    public void setInFocus(boolean inFocus){}

    /**
     * returns list of components to be put on Menu bar when set to focus
     * @return list of components to be put on menu bar
     */
    public final List<JComponent> getMenus()
    {
        return this.menus;
    }

    /**
     * adds menu to be put on menu bar when set to focus
     * @param menu component
     */
    public final void addMenu(JComponent menu)
    {
        this.menus.add(menu);
    }

    /**
     * removes menu to be put on menu bar when set to focus
     * @param menu component
     */
    public final void removeMenu(JComponent menu)
    {
        this.menus.remove(menu);
    }


    /**
     * returns the short name of the algorithm
     * @return the short name of the algorithm
     */
    public final String getShortName()
    {
        return this.shortName;
    }

    /**
     * returns the name of the algorithm
     * @return the name of the algorithm
     */
    @Override
    public final String getName()
    {
        return name;
    }


    /**
     * shows the decoder pane in a non-blocking pop up frame
     * @param defaultClose what the frame should do when it closes (HINT: look at JFrame.)
     */
    public final void showPopupFrame(int defaultClose)
    {
        JFrame frame = new JFrame(this.getName());
        frame.setSize(this.getSize());
        frame.setDefaultCloseOperation(defaultClose);
        frame.add(this);
        if(!this.getMenus().isEmpty())
        {
            JMenuBar bar = new JMenuBar();
            frame.setJMenuBar(bar);
            for(JComponent component: this.getMenus())
            {
                bar.add(component);
            }
        }
        frame.show();
    }

    /**
     * DecoderPane that come with the application
     */
    public static final AppPane[] CRYPTO_PANELS ={
            new AnalyzerPane(),
            new MonoAlphabeticPane(),
            new PlayfairPane(),
            new VigenereAppPane()
    };

}
