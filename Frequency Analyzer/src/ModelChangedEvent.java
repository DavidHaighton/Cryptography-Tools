import java.util.EventObject;
import java.util.Map;

public class ModelChangedEvent extends EventObject
{
    private final int totalLetters;
    private final Map<Character,Integer> histogram;
    public ModelChangedEvent(Object src, Map<Character, Integer> histogram, int totalLetters)
    {
        super(src);
        this.totalLetters = totalLetters;
        this.histogram = histogram;
    }

    public int getTotalLetters()
    {
        return totalLetters;
    }

    public Map<Character,Integer> getHistogram()
    {
        return histogram;
    }

}
