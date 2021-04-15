import java.util.EventObject;

public class PlainUpdatedEvent extends EventObject
{
    private String plainText;
    public PlainUpdatedEvent(Object src, String plainText)
    {
        super(src);
        this.plainText = plainText;
    }

    public String getPlainText()
    {
        return plainText;
    }
}
