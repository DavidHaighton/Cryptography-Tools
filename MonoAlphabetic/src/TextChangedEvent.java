import java.util.EventObject;

public class TextChangedEvent extends EventObject
{
    private final String plainText;
    public TextChangedEvent(Object src, String plainText)
    {
        super(src);
        this.plainText = plainText;
    }

    public String getPlainText()
    {
        return this.plainText;
    }
}
