import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AnalyzerFrame extends JFrame implements AnalyzerView
{
    private Model model = new Model();
    private JTextArea cypher = new JTextArea();
    private JButton searchButton = new JButton("Search");
    private JPanel textPanel = new JPanel(),
    histogramPanel = new JPanel();
    private JLabel selected = new JLabel("--selected--");

    private List<FrequencyPanel> frequencyPanels = new ArrayList<>(27);

    public AnalyzerFrame()
    {
        super("Character Frequency Analysis Tool");

        this.setLayout(new GridLayout(1,2,2,2));
        this.add(textPanel);
        textPanel.setLayout(new BorderLayout());
        this.add(histogramPanel);
        histogramPanel.setLayout(new GridLayout(26,1,0,1));
        for(int i=0;i<26;i++)
        {
            frequencyPanels.add(new FrequencyPanel((char)('a'+i)));
            histogramPanel.add(frequencyPanels.get(frequencyPanels.size()-1));
        }

        textPanel.add(selected,BorderLayout.NORTH);
        textPanel.add(searchButton,BorderLayout.SOUTH);
        searchButton.addActionListener(new SearchHandler());
        textPanel.add(cypher,BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        model.addView(this);

        setSize(500,500);
        show();
    }
    @Override
    public void update(ModelChangedEvent evt)
    {
        FrequencyPanel.setTotal(evt.getTotalLetters());
        if(evt.getSource() instanceof Model)
        {
            Model m = (Model) evt.getSource();
            selected.setText(m.getSelectedText());
        }
        for(FrequencyPanel panel: frequencyPanels)
        {
            int num = evt.getHistogram().containsKey(panel.getLetter())?evt.getHistogram().get(panel.getLetter()):0;
            panel.setCount(num);
        }
    }


    public static void main(String[] args) {
        AnalyzerFrame frame = new AnalyzerFrame();
    }

    class SearchHandler implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selected = cypher.getSelectedText();
            if(selected==null)
            {
                selected="";
            }
            model.selectText(selected);
        }
    }
}
