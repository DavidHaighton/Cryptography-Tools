import java.util.EventObject;
import java.util.Map;

public class RelationChangedEvent extends EventObject
{
    private final Map<Character,Character> relation;
    public RelationChangedEvent(Object src, Map<Character,Character> relation)
    {
        super(src);
        this.relation = relation;
    }

    public Map<Character,Character> getRelation()
    {
        return relation;
    }
}
