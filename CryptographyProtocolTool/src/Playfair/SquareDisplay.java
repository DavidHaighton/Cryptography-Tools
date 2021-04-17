package Playfair;
import javax.swing.*;
import java.awt.*;

public class SquareDisplay extends JPanel implements KeyChangeListener
{
    private JLabel[][] labels = new JLabel[Model.SIZE][Model.SIZE];

    private Model model;
    public SquareDisplay(Model model)
    {
        this.model = model;
        this.setBackground(Color.WHITE);
        model.addKeyChangeListener(this);
        this.setLayout(new GridLayout(Model.SIZE, Model.SIZE,1,1));
        for(int j=0;j<Model.SIZE;j++)
        {
            for(int i=0;i<Model.SIZE; i++)
            {
                JPanel panel = new JPanel();
                panel.setBackground(Color.WHITE);
                JLabel label = new JLabel(model.getSquare()[i][j]+"");
                panel.add(label);

                labels[i][j] = label;
                this.add(label);
            }
        }
    }

    private void update(char[][] key)
    {
        for(int j=0;j<Model.SIZE;j++)
        {
            for(int i=0;i<Model.SIZE; i++)
            {
                labels[i][j].setText(model.getSquare()[i][j]+"");
            }
        }
    }

    @Override
    public void keyChanged(KeyChangedEvent event)
    {
        this.update(event.getKey());

    }

}
