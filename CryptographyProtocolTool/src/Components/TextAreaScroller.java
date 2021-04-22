package Components;

import Framework.CustomComponent;
import Framework.CustomComponentValueChanged;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class TextAreaScroller extends CustomComponent<String>
{
    private JTextArea text= new JTextArea();
    private JScrollPane scrollPane = new JScrollPane(text);
    public TextAreaScroller(String name)
    {
        super(name);
        this.setLayout(new GridLayout(1,1));
        this.add(scrollPane);
        text.getDocument().addDocumentListener(new DocumentHandler());
        text.setLineWrap(true);
    }

    @Override
    public void setValue(String value)
    {
        text.setText(value);
    }

    @Override
    public void setEditable(boolean editable)
    {
        text.setEditable(editable);
    }

    @Override
    public boolean isEditable()
    {
        return text.isEditable();
    }

    @Override
    public String getValue()
    {
        return text.getText();
    }

    private class DocumentHandler implements DocumentListener
    {

        /**
         * Gives notification that there was an insert into the document.  The
         * range given by the DocumentEvent bounds the freshly inserted region.
         *
         * @param e the document event
         */
        @Override
        public void insertUpdate(DocumentEvent e)
        {
            update();
        }

        /**
         * Gives notification that a portion of the document has been
         * removed.  The range is given in terms of what the view last
         * saw (that is, before updating sticky positions).
         *
         * @param e the document event
         */
        @Override
        public void removeUpdate(DocumentEvent e)
        {
            update();
        }

        /**
         * Gives notification that an attribute or set of attributes changed.
         *
         * @param e the document event
         */
        @Override
        public void changedUpdate(DocumentEvent e)
        {
            update();
        }

        private void update()
        {
            notifyCustomComponentListeners();
        }
    }

}
