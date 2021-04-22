package Framework;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;
import java.util.*;

public abstract class CustomComponent<Type> extends JComponent
{
    private List<JComponent> components = new ArrayList(1);

    private List<CustomComponentListener<Type>> listeners = new ArrayList(1);

    public CustomComponent(String name)
    {
        this.setBorder(new TitledBorder(name));
    }
    private boolean isEditable=true;

    public void setEditable(boolean editable)
    {
        for(JComponent component: components)
        {
            if(component instanceof CustomComponent)
            {
                ((CustomComponent)component).setEditable(editable);
            }
            else if(component instanceof JTextComponent)
            {
                ((JTextComponent)component).setEditable(editable);
            }
            else
            {
                component.setEnabled(editable);
            }
        }
        isEditable= editable;
    }

    public boolean isEditable()
    {
        return isEditable;
    }


    public abstract void setValue(Type value);

    public abstract Type getValue();


    public final void addCustomComponentListener(CustomComponentListener<Type> listener)
    {
        this.listeners.add(listener);
    }

    public final void removeCustomComponentListener(CustomComponentListener<Type> listener)
    {
        this.listeners.remove(listener);
    }

    protected final void notifyCustomComponentListeners()
    {
        CustomComponentValueChanged<Type> event = new CustomComponentValueChanged<>(this,this.getValue());
        for(CustomComponentListener listener:listeners)
        {
            listener.CryptoComponentValueChanged(event);
        }
    }




}
