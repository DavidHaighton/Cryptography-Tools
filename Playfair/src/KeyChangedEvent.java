import java.util.EventObject;

public class KeyChangedEvent extends EventObject
{

    private char[][] key;
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public KeyChangedEvent(Object source, char[][] key) {
        super(source);
        this.key = key;
    }

    public char[][] getKey()
    {
        return key;
    }
}
