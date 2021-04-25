package Framework;

import java.util.EventObject;

/**
 * Called when a Simple Component is changed
 */
public class SimpleChangedEvent extends EventObject
{

    private final String value;
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public SimpleChangedEvent(final Object source, final String value)
    {
        super(source);
        this.value=value;
    }
}
