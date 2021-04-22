package MonoAlphabetic;
import Framework.AppPane;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MonoAlphabeticPane extends AppPane implements DecoderView
{
    private final String PREDICT="predict";
    private JTextArea plainTextArea=new JTextArea(),
            cipherTextArea = new JTextArea();
    private JPanel mappingPanel = new JPanel(), textPanel=new JPanel();
    private JScrollPane  cipherPane,
            plainPane;
    private List<CharacterMapPanel> mapPanels = new ArrayList<>(26);
    private Model model = new Model();
    private JMenu predictMenu = new JMenu("Actions");
    public MonoAlphabeticPane()
    {
        super("MonoAlpha","Mono Alphabetic Tool");
        this.model.addDecoderView(this);

        this.addMenu(predictMenu);
        JButton tempButton = new JButton("Predict");
        tempButton.addActionListener(new MenuHandler());
        tempButton.setActionCommand(PREDICT);
        predictMenu.add(tempButton);


        this.setLayout(new GridLayout(1,2,2,2));
        this.add(mappingPanel);
        this.add(textPanel);

        textPanel.setLayout(new GridLayout(2,1,1,3));
        textPanel.setBackground(Color.BLACK);
        cipherPane = new JScrollPane(cipherTextArea);
        textPanel.add(cipherPane);
        cipherTextArea.getDocument().addDocumentListener(new CipherHandler());
        plainPane = new JScrollPane(plainTextArea);
        textPanel.add(plainPane);
        plainTextArea.setEditable(false);
        this.plainTextArea.setLineWrap(true);
        this.cipherTextArea.setLineWrap(true);

        this.mappingPanel.setLayout(new GridLayout(model.getRelation().size(),1));
        Color rowColor = Color.WHITE;
        for(Map.Entry<Character, Character> entry: model.getRelation().entrySet())
        {
            CharacterMapPanel cmp = new CharacterMapPanel(entry.getKey(), model);
            mappingPanel.add(cmp);
            cmp.setBackground(rowColor);
            rowColor = rowColor==Color.WHITE?Color.LIGHT_GRAY:Color.WHITE;
        }

        this.setSize(500,500);
    }
    @Override
    public void updateText(TextChangedEvent event)
    {
        if(event.getSource() instanceof Model)
        {
            Model m = (Model)event.getSource();
            plainTextArea.setText(event.getPlainText());
        }
    }

    @Override
    public void setInFocus(boolean inFocus)
    {

    }

    private class CipherHandler implements DocumentListener
    {


        @Override
        public void insertUpdate(DocumentEvent e)
        {
            handle();
        }

        @Override
        public void removeUpdate(DocumentEvent e)
        {
            handle();
        }

        @Override
        public void changedUpdate(DocumentEvent e)
        {
            handle();
        }

        private void handle()
        {
            model.setCipher(cipherTextArea.getText());
        }
    }

    private class MenuHandler implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            model.predict();

        }
    }

    public static void main(String[] args)
    {
        MonoAlphabeticPane pane = new MonoAlphabeticPane();
        pane.showPopupFrame(JFrame.EXIT_ON_CLOSE);

    }
}
