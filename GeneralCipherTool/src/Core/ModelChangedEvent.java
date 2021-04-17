package Core;

import java.util.EventObject;

/**
 * Event that is produced when the model changes
 */
public class ModelChangedEvent extends EventObject
{
    private final SymCryptoPartType type;
    private final Object part;
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ModelChangedEvent(Object source, SymCryptoPartType type, Object part)
    {
        super(source);
        this.type = type;
        this.part = part;
    }

    /**
     * returns the type of the part of the crypto process
     * @return type
     */
    public SymCryptoPartType getType()
    {
        return type;
    }

    /**
     * returns the part of the cryptographic process
     * @return the part of the cruptographic process
     */
    public Object getPart(){return this.part;}
}
