import Core.AppPane;

import javax.swing.*;
import java.awt.*;

public class CompleteCipherTool extends JFrame
{

    private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
    private JMenuBar menuBar = new JMenuBar();

    private AppPane panels[] = AppPane.CRYPTO_PANELS;

    public CompleteCipherTool()
    {
        super("General Cipher Tool");
        for(AppPane pane: panels)
        {
            tabbedPane.addTab(pane.getShortName(), null, pane, pane.getName());
        }

        this.setJMenuBar(menuBar);

        this.setLayout(new GridLayout(1,1));
        this.add(tabbedPane);
        tabbedPane.addChangeListener(e -> {this.updatePaneInFocus();});

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.show();

    }

    private void updatePaneInFocus()
    {

        menuBar.removeAll();
        for(AppPane temp: this.panels)
        {
            temp.setInFocus(false);
        }

        AppPane pane =((AppPane)tabbedPane.getSelectedComponent());
        pane.setInFocus(true);
        for(JComponent component: pane.getMenus())
        {
            menuBar.add(component);
        }
    }

    public static void main(String[] args) {
        CompleteCipherTool tool = new CompleteCipherTool();
    }
}
