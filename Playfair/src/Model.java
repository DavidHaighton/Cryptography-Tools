import java.awt.*;
import java.util.*;

public class Model
{
    public final static int SIZE=5;

    private char[][] square = new char[5][5];
    private Map<Character, Point> squareLookup = new HashMap<>(SIZE*SIZE+1);
    private String cipher="", plainText="";

    private java.util.List<PlayfairView> playfairListenerList = new ArrayList<>(1);
    private java.util.List<KeyChangeListener> keyChangeListeners = new ArrayList<>(1);

    public Model()
    {
        //default to letters of the alphabet
        StringBuilder builder = new StringBuilder(26);
        for(char c = 'a'; c<='z'; c++)
        {
            builder.append(c);
        }
        setKey(cipher.toString());
    }

    public void setKey(String key)
    {
        squareLookup.clear();
        key = key.toLowerCase().replaceAll(" ","");
        squareLookup.put('j',new Point(-1,-1));//skip j
        //fill in with provided key
        int index=0;
        for(char c: key.toCharArray())
        {
             if(squareLookup.containsKey(c))
             {
                 continue;
             }
             int x=index%SIZE,y=Math.floorDiv(index,SIZE);
             squareLookup.put(c,new Point(x,y));
             square[x][y] = c;
             index++;
        }
        //do alphabet for the rest
        for(char c='a';c<='z';c++)
        {
            if(squareLookup.containsKey(c))
            {
                continue;
            }
            int x=index%SIZE,y=Math.floorDiv(index,SIZE);
            squareLookup.put(c,new Point(x,y));
            square[x][y] = c;
            index++;
        }
        squareLookup.put('j',squareLookup.get('i'));
        for(KeyChangeListener listener: keyChangeListeners)
        {
            listener.keyChanged(new KeyChangedEvent(this,square));
        }
        generatePlainText();
    }


    public void setCipher(String cipher)
    {

        cipher = cipher.toLowerCase().replaceAll(" ", "");//remove spaces

        if ((cipher.length() & 1) == 1) //if odd, add spare x to avoid error
        {
            return;
        }
        this.cipher =cipher;
        generatePlainText();
    }

    public char[][] getSquare()
    {
        return square;
    }



    private void generatePlainText()
    {
        StringBuilder builder = new StringBuilder(cipher.length());
        for(int i=0; i<cipher.length();i+=2)
        {
            char first=cipher.charAt(i),second=cipher.charAt(i+1);
            builder.append(getPlainChar(first,second));
            builder.append(getPlainChar(second,first));
        }
        this.plainText=builder.toString();

        for(PlayfairView view : playfairListenerList)
        {
            view.plainTextChanged(new PlainTextChangedEvent(this,plainText));
        }
    }


    private char getPlainChar(char focus, char other)
    {
        Point focusPoint= squareLookup.get(focus), otherPoint = squareLookup.get(other);

        if(focusPoint.y==otherPoint.y) //is row
        {
            return square[((focusPoint.x+1)%SIZE+SIZE)%SIZE][focusPoint.y];
        }
        else if(focusPoint.x==otherPoint.x)//is column
        {
            return square[focusPoint.x][((focusPoint.y+1)%SIZE+SIZE)%SIZE];
        }
        else //is rectangle
        {
            return square[otherPoint.x][focusPoint.y];
        }
    }

    public void addPlayFairView(PlayfairView view)
    {
        this.playfairListenerList.add(view);
    }
    public void removePlayFairView(PlayfairView view)
    {
        this.playfairListenerList.remove(view);
    }

    public void addKeyChangeListener(KeyChangeListener view)
    {
        this.keyChangeListeners.add(view);
    }
    public void removeKeyChangeListener(KeyChangeListener view)
    {
        this.keyChangeListeners.remove(view);
    }

}
