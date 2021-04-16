package Core;

import FrequencyAnalyzer.AnalyzerPane;
import MonoAlphabetic.MonoAlphabeticPane;
import Playfair.PlayfairPane;
import Vigenere.VigenereDecoderPane;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DecoderPane extends JComponent
{
    private final String name, description;
    private List<JComponent> menus = new ArrayList<>(4);

    public DecoderPane(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
    public abstract void setInFocus(boolean inFocus);
    public final List<JComponent> getMenus()
    {
        return this.menus;
    }

    public final void addMenu(JComponent menu)
    {
        this.menus.add(menu);
    }

    public final void removeMenu(JComponent menu)
    {
        this.menus.remove(menu);
    }


    @Override
    public String getName()
    {
        return this.name;
    }

    public String getDescription()
    {
        return description;
    }


    public void showPopupFrame(int defaultClose)
    {
        JFrame frame = new JFrame(this.getDescription());
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

    public static final DecoderPane[] decoderPanels ={
            new AnalyzerPane(),
            new MonoAlphabeticPane(),
            new PlayfairPane(),
            new VigenereDecoderPane()
    };

}
