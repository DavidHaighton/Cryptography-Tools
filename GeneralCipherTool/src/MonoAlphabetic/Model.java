package MonoAlphabetic;
import java.util.*;

public class Model
{
    private List<DecoderView> decoderViews = new ArrayList<>(1);
    private List<RelationView> relationViews = new ArrayList(26);
    private Map<Character,Character> relation = new HashMap<>(26);
    private String cipher = "";
    private Predictor predictor = new Predictor();
    public Model()
    {


        for(char c='a';c<='z';c++)
        {
            relation.put(c,'a');
        }
    }

    public void setCipher(String cipher)
    {

        this.cipher = cipher.toLowerCase();
        for(DecoderView view: decoderViews)
        {
            view.updateText(new TextChangedEvent(this,this.getPlainText()));
        }
    }

    public String getCipher()
    {
        return cipher;
    }

    public String getPlainText()
    {
        char[] characters = cipher.toCharArray();
        for(int i=0;i<characters.length;i++)
        {
            //only do for alphabet characters
            if(relation.containsKey(characters[i]))
                characters[i] = relation.get(characters[i]);
        }

        return new String(characters);
    }

    public Map<Character, Character> getRelation()
    {
        return relation;
    }

    public void changeLetterRelation(char cipherLetter, char plainLetter)
    {
        relation.put(cipherLetter,plainLetter);
        String plainText = getPlainText();
        for(DecoderView view: decoderViews)
        {
            view.updateText(new TextChangedEvent(this,plainText));
        }
    }


    public void addDecoderView(DecoderView view)
    {
        decoderViews.add(view);
    }
    public void removeDecoderView(DecoderView view)
    {
        decoderViews.remove(view);
    }

    public void addRelationView(RelationView view)
    {
        relationViews.add(view);
    }
    public void removeRelationView(RelationView view)
    {
        relationViews.remove(view);
    }


    public void predict()
    {
        cipher.toLowerCase();
        for(Map.Entry<Character,Character> predictEntry: this.predictor.makePrediction(cipher).entrySet())
        {
            relation.put(predictEntry.getKey(),predictEntry.getValue());
        }

        for(RelationView relationView: relationViews)
        {
            relationView.relationChanged(new RelationChangedEvent(this,relation));
        }
        setCipher(cipher);
    }

}
