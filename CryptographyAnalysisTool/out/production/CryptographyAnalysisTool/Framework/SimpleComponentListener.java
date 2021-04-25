package Framework;

/**
 * Observer interface for simple components
 */
public interface SimpleComponentListener
{
    /**
     * Called when a simple components value changes
     * @param event the event to trigger it
     */
    public void valueChanged(SimpleChangedEvent event);
}
