package Framework;

import java.util.EventObject;

/**
 * The event for when an algorithm analyzed the cryptography data and produced a solution
 */
public class AnalyzedEvent extends EventObject
{

    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public AnalyzedEvent(Object source)
    {
        super(source);
    }
}
