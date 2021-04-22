package MonoAlphabetic;

import java.util.*;

public class MonoAlphabeticAlgorithm implements SymmetricAlgorithm<String, Map<Character,Character>, String>
{
    private List<ModelChangeListener> modelChangeListeners = new ArrayList<>(7);
    private Predictor predictor = new Predictor();
    public MonoAlphabeticAlgorithm()
    {

    }


    public void predict(String cipher, Map<Character,Character> relation)
    {
        cipher.toLowerCase();
        for(Map.Entry<Character,Character> predictEntry: this.predictor.makePrediction(cipher).entrySet())
        {
            relation.put(predictEntry.getKey(),predictEntry.getValue());
        }
    }

    /**
     * Generates key from a cipher and plain. Returns null if it doesn't work
     *
     * @param s  the cipher
     * @param s2 the plain
     * @return the cipher or null
     */
    @Override
    public Map<Character, Character> generateKey(String s, String s2)
    {
        if(s.length()!=s2.length())
        {
            return getAlphabet();
        }
        Map<Character,Character> mapping = new HashMap<>();
        for(int i=0;i<s.length();i++)
        {
            char c1=s.charAt(i),c2=s2.charAt(i);
            if(mapping.containsKey(c2))
            {
                if(mapping.get(c1)!=c2)
                {
                    return getAlphabet();
                }
                mapping.putIfAbsent(c1,c2);
            }
        }
        for(char c='a';c<='z';c++)
        {
            mapping.putIfAbsent(c,c);
        }
        return mapping;
    }

    /**
     * Generates plain from a cipher and key. Returns null if it doesn't work
     *
     * @param s                     the cipher
     * @param characterMap the key
     * @return plain or null
     */
    @Override
    public String generatePlain(String s, Map<Character, Character> characterMap)
    {
        return generateText(s,characterMap);
    }

    private String generateText(String s, Map<Character, Character> characterMap)
    {
        //because of how annoying it would be to switch, both plain and cipher text are calculated the same way
        StringBuilder builder = new StringBuilder(s.length());
        s=s.toLowerCase();
        for(char c: s.toCharArray())
        {
            if(c<'a'||c>'z')
            {
                builder.append(characterMap.get(c));
            }
            else
            {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    /**
     * Generates cipher from plain and key. Returns null if it doesn't work
     *
     * @param s                     plain
     * @param characterMap key
     * @return cipher
     */
    @Override
    public String generateCipher(String s, Map<Character, Character> characterMap)
    {
        return generateText(s,characterMap);
    }

    /**
     * returns name of the algorithms
     *
     * @return the name of the algorithm
     */
    @Override
    public String getName() {
        return "MonoAlphabetic";
    }

    /**
     * returns short name of the algorithm
     *
     * @return short name of the algorithm
     */
    @Override
    public String getShortName() {
        return "MonoAlpha";
    }

    private static Map<Character, Character> getAlphabet()
    {
        Map<Character,Character> alphabet = new HashMap<>(26);
        for(char c='a';c<='z';c++)
        {
            alphabet.put(c,c);
        }
        return alphabet;
    }
}
