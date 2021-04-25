package Components;

import Framework.SimpleComponent;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;

/**
 * A SimpleComponent that takes the place of the
 */
public class SimpleTextInput extends SimpleComponent
{
    public final static String AREA="AREA", FIELD="FIELD";

    private JTextComponent component;
    private SimpleTextInput(JTextComponent component)
    {
        this.add(component);
        this.component = component;
        this.component.getDocument().addDocumentListener(new DocumentHandler());
    }

    private SimpleTextInput(JTextComponent component, JComponent parent)
    {
        this.add(parent);
        this.component =component;
        this.component.getDocument().addDocumentListener(new DocumentHandler());
    }

    @Override
    public void setEnableWrite(boolean write) {
        super.setEnableWrite(write);
        this.component.setEditable(write);
    }

    @Override
    protected void _setValue(String value)
    {
        this.component.setText(value);
    }

    @Override
    public String getValue()
    {
        return this.component.getText();
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
            updateAllListeners();
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
            updateAllListeners();
        }

        /**
         * Gives notification that an attribute or set of attributes changed.
         *
         * @param e the document event
         */
        @Override
        public void changedUpdate(DocumentEvent e)
        {
            updateAllListeners();
        }
    }

    public static SimpleTextInput factory(String componentType)
    {
        switch (componentType)
        {
            case AREA:
                JTextArea area = new JTextArea();
                return new SimpleTextInput(area,new JScrollPane(area));
            default:
                return new SimpleTextInput(new JTextField());
        }
    }
}
