package Framework;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple components are what makes using the framework easy
 * They are JComponent wrappers with standard methods
 */
public abstract class SimpleComponent extends JComponent
{
    private List<SimpleComponentListener> listeners = new ArrayList<>(1);
    private boolean isEnableWrite=true, valueBeingSet=false;
    public SimpleComponent()
    {
        this.setLayout(new GridLayout(1,1));
    }

    /**
     * sets the value of the component. Only actually does it when it's safe to do
     * @param value sets the value
     */
    public final void setValue(final String value)
    {
        valueBeingSet=true;
        boolean prevState= this.isEnableWrite();
        this.setEnableWrite(false);
        this._setValue(value);
        this.setEnableWrite(prevState);
        valueBeingSet=false;
    }

    /**
     * sets the value of the component. only gets called when safe to do
     * @param value the value of the component
     */
    protected abstract void _setValue(final String value);

    /**
     * returns the value of the component
     * @return the value of the component
     */
    public abstract String getValue();

    /**
     * enabled or disabled the component so it can't be or can be written to
     * @param write if it can be written to
     */
    public void setEnableWrite(final boolean write)
    {
        this.isEnableWrite=write;
        this.setEnabled(write);
    }

    /**
     * returns true if the component can be written to
     * @return true if the component can be written to
     */
    public final boolean isEnableWrite()
    {
        return this.isEnableWrite;
    }


    /**
     * update all listeners
     */
    protected final void updateAllListeners()
    {
        if(valueBeingSet)
        {
            return;
        }
        String value = this.getValue();
        SimpleChangedEvent event = new SimpleChangedEvent(this,value);
        for(SimpleComponentListener listener: listeners)
        {
            listener.valueChanged(event);
        }
    }

    /**
     * add the observer to the component
     * @param listener the observer
     */
    public final void addListener(SimpleComponentListener listener)
    {
        this.listeners.add(listener);
    }

    /**
     * remove the listener from component
     * @param listener the listener
     */
    public final void removeListener(SimpleComponentListener listener)
    {
        this.listeners.remove(listener);
    }

}
