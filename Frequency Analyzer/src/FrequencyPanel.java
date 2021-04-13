import javax.swing.*;
import java.awt.*;
import java.util.*;

public class FrequencyPanel extends JPanel
{

    private static int totalCount=0;
    private JLabel countLabel,
    letterLabel;
    private int count=0;
    private char character;
    JPanel graph = new JPanel()
    {
        @Override
        public void paint(Graphics g)
        {
            if(totalCount==0)
            {
                totalCount=1;
            }
            int width = (int)getWidth()*count/(totalCount);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0,0,getWidth(),getHeight());
            g.setColor(Color.GREEN);
            g.fill3DRect(0,0,width,getHeight(),true);

        }
    };

    public FrequencyPanel(char letter)
    {
        character = letter;
        letterLabel = new JLabel(letter+"");
        countLabel = new JLabel("0");

        this.setLayout(new BorderLayout());
        this.add(letterLabel,BorderLayout.WEST);
        letterLabel.setMaximumSize(new Dimension(40,40));
        letterLabel.setMinimumSize(new Dimension(40,40));
        this.add(graph,BorderLayout.CENTER);
        this.add(countLabel, BorderLayout.EAST);
    }

    public char getLetter()
    {
        return this.character;
    }

    public void setCount(int count)
    {
        this.count = count;
        this.countLabel.setText(String.valueOf(this.count));
        graph.repaint();
    }

    public static void setTotal(int total)
    {
        totalCount=total;
    }

}