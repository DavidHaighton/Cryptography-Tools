import java.util.*;
public class Model
{
    private String cipher="",
    key="",
    plain="";

    private List<PlainTextView> plainTextViewList = new ArrayList<>(1);

    private int keyWrapOffset;

    public Model()
    {
    }

    public void setKeyWrapOffset(int keyWrapOffset)
    {
        this.keyWrapOffset = keyWrapOffset;
        updatePlain();
    }
    public int getKeyWrapOffset()
    {
        return keyWrapOffset;
    }

    public void setCipher(String cipher)
    {
        this.cipher = cipher.toLowerCase();
    }

    public String getCipher()
    {
        return this.cipher;
    }

    public void setKey(String key)
    {
        this.key = key.toLowerCase();
    }
    public String getKey()
    {
        return this.key;
    }

    public void updatePlain()
    {
        if(key.length()==0)
        {
            plain = cipher;
        }
        else
        {
            calculate();
        }

        for(PlainTextView view: plainTextViewList)
        {
            view.plainTextUpdated(new PlainUpdatedEvent(this,plain));
        }
    }

    private void calculate()
    {
        //match lengths by repeat
        StringBuilder plainBuilder = new StringBuilder(this.cipher.length());
        char[] cipherArray = this.cipher.toCharArray();
        char[] keyArray = this.key.toCharArray();
        for(int index=0;index<cipherArray.length; index++)
        {
            char keyChar= keyArray[(index+keyWrapOffset)%keyArray.length],
                    cipherChar=cipherArray[index];

            if((cipherChar>'z'||cipherChar<'a')||(keyChar>'z'||keyChar<'a')) //skip non-alphabetic characters
            {
                continue;
            }

            char plainChar = getPlainChar(cipherChar,keyChar);
            plainBuilder.append(plainChar);
        }
        plain = plainBuilder.toString();
    }

    private char getPlainChar(char cipher, char key)
    {
        return (char)((cipher-key+26)%26+'a');
    }

    public void addPlainTextView(PlainTextView view)
    {
        this.plainTextViewList.add(view);
    }
    public void removePlainTextView(PlainTextView view)
    {
        this.plainTextViewList.remove(view);
    }



}
