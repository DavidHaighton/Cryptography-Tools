import java.util.EventObject;

public class PlainTextChangedEvent extends EventObject
{
    private String plainText;
    public PlainTextChangedEvent(Object src, String plainText)
    {
        super(src);
        this.plainText = plainText;
    }

    public String getPlainText()
    {
        return plainText;
    }
}
