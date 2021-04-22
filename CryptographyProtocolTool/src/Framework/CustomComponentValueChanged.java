package Framework;

import java.util.EventObject;

public class CustomComponentValueChanged<Type> extends EventObject
{
    private final Type value;
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public CustomComponentValueChanged(Object source, Type value) {
        super(source);
        this.value = value;
    }

    public final Type getValue()
    {
        return value;
    }
}
