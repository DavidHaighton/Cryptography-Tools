import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AnalyzerFrame extends JFrame implements AnalyzerView
{
    private Model model = new Model();
    private JTextArea cipherTextArea = new JTextArea();
    private JButton searchButton = new JButton("Search");
    private JPanel textPanel = new JPanel(),
    histogramPanel = new JPanel();
    JScrollPane scrollPane = new JScrollPane(cipherTextArea);
    private JLabel selected = new JLabel("--selected--");

    private java.util.List<FrequencyPanel> frequencyPanels = new ArrayList<>(27);

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
            char current = (char)('a'+i);
            frequencyPanels.add(new FrequencyPanel(current, model.getPopulationLetterFrequency(current)));
            histogramPanel.add(frequencyPanels.get(frequencyPanels.size()-1));
        }
        cipherTextArea.setLineWrap(true);

        textPanel.add(selected,BorderLayout.NORTH);
        textPanel.add(searchButton,BorderLayout.SOUTH);
        searchButton.addActionListener(new SearchHandler());
        textPanel.add(scrollPane,BorderLayout.CENTER);
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
            String selected = cipherTextArea.getSelectedText();
            if(selected==null)
            {
                selected="";
            }

            if(selected.equals(""))
            {
                selected = cipherTextArea.getText();
            }

            model.selectText(selected);
        }
    }
}
