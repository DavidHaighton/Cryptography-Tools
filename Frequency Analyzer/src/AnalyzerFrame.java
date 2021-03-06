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

    private java.util.List<FrequencyPanel> frequencyPanels = new ArrayList<>(27);

    public AnalyzerFrame()
    {
        super("Character Frequency Analysis Tool");

        this.setLayout(new GridLayout(1,2,2,2));
        this.add(textPanel);
        textPanel.setLayout(new BorderLayout());
        this.add(histogramPanel);
        histogramPanel.setLayout(new GridLayout(26,1,0,1));

        Scanner scanner = new Scanner(getClass().getResourceAsStream("letter_histogram.txt"));
        Map<Character,Float> languageMapping = new HashMap<>(26);
        while(scanner.hasNextLine())
        {
            String s=scanner.nextLine();
            String[] sides=s.split(":");
            char letter = sides[0].charAt(0);
            float freq = Float.parseFloat(sides[1]);
            languageMapping.put(letter,freq);

        }

        for(int i=0;i<26;i++)
        {
            char c=(char)('a'+i);
            frequencyPanels.add(new FrequencyPanel(c,languageMapping.get(c)));
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
                selected=cypher.getText();
            }
            model.selectText(selected);
        }
    }
}
