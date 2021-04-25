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
    private final Font font = new Font(Font.MONOSPACED,Font.PLAIN,12);
    private float languagePercent;
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
            int languageWidth = (int)(getWidth()*languagePercent);

            g.setColor(Color.DARK_GRAY);
            g.fillRect(0,0,getWidth(),getHeight());

            if(languageWidth>width)
            {
                g.setColor(Color.ORANGE);
                g.fillRect(0,0,languageWidth,getHeight());
                g.setColor(Color.GREEN);
                g.fill3DRect(0,0,width,getHeight(),true);
            }
            else if(languageWidth<width)
            {
                g.setColor(Color.GREEN);
                g.fill3DRect(0,0,width,getHeight(),true);
                g.setColor(Color.ORANGE);
                g.fillRect(0,0,languageWidth,getHeight());
            }
            else
            {
                g.setColor(Color.BLACK);
                g.fill3DRect(0,0,width,getHeight(),true);
            }




        }
    };

    public FrequencyPanel(char letter, float languagePercent)
    {
        character = letter;
        this.languagePercent = languagePercent/100;
        letterLabel = new JLabel(letter+"");
        countLabel = new JLabel("000");
        letterLabel.setFont(font);
        countLabel.setFont(font);

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
        String output=String.valueOf((float)this.count/(float)totalCount*100f);
        while(output.length()<3)
        {
            output= "0"+output;
        }
        output = output.substring(0,3);
        output+="%";

        this.countLabel.setText(output);
        graph.repaint();
    }

    public static void setTotal(int total)
    {
        totalCount=total;
    }

}
