import Algorithms.CaesarAlgorithm;
import Framework.AnalysisDisplay;
import javax.swing.*;
import java.awt.*;

/**
 * The actual application itself
 */
public class Application extends JFrame
{
    private AnalysisDisplay[] tabs = {CaesarAlgorithm.getDisplay()};//put algorithm displays here

    private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
    private final JMenuBar menuBar = new JMenuBar();
    public Application()
    {
        super("Cryptography Analysis Tool");
        this.setLayout(new GridLayout(1,1));
        this.add(tabbedPane);
        this.setJMenuBar(menuBar);

        for(AnalysisDisplay tab: tabs)
        {
            tabbedPane.addTab(tab.getName(),tab);
        }
        tabbedPane.addChangeListener(e->{});

        updateSelectedTab();


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        show();
    }

    private void updateSelectedTab()
    {
        menuBar.removeAll();
        AnalysisDisplay ad = (AnalysisDisplay)tabbedPane.getSelectedComponent();
        JMenu modeMenu = new JMenu("Mode");
        ButtonGroup group = new ButtonGroup();
        boolean first=true;
        for(String mode: ad.getModel().getAlgorithm().getModes())
        {
            JRadioButton radioButton = new JRadioButton(mode);
            radioButton.addActionListener(e->ad.getModel().setMode(mode));
            group.add(radioButton);
            modeMenu.add(radioButton);
            if(first)
            {
                first=false;
                radioButton.setSelected(true);
            }
        }
        menuBar.add(modeMenu);

    }


    public static void main(String[] args)
    {
        Application app = new Application();
    }
}
