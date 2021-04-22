package Framework;
import java.util.*;


public class ModelEvent<Data extends CryptoData> extends EventObject
{
    private final Data data;
    private final Set<String> updatedParts;
    /**
     * Constructs a prototypical Event.
     *
     * @param source the object on which the Event initially occurred
     * @throws IllegalArgumentException if source is null
     */
    public ModelEvent(Object source, Data data, String... updatedParts)
    {
        super(source);
        this.data = data;
        this.updatedParts = new HashSet<>(updatedParts.length);
        for(String part: updatedParts)
        {
            this.updatedParts.add(part);
        }

    }

    public final Data getCryptoData()
    {
        return data;
    }

    public final Set<String> getUpdatedParts()
    {
        return updatedParts;
    }


}
