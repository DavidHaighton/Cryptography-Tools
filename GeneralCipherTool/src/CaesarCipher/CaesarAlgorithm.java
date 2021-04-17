package CaesarCipher;

import Core.*;

import java.util.*;

public class CaesarAlgorithm implements CryptoAlgo<String,String,String>
{
    private static final int NEXT=1,PREV=-1;
    private static final String NO_OUTPUT="";
    private static final int NUMBER_OF_WORDS=10000;
    private static final int NONE_FOUND=27;

    private Set<String> wordBank = new HashSet<>(NUMBER_OF_WORDS);
    public CaesarAlgorithm()
    {
        Scanner wordReader = new Scanner(getClass().getResourceAsStream("/Resources/word_list.txt"));
        while(wordReader.hasNextLine())
        {
            String word = wordReader.nextLine().toLowerCase();
            wordBank.add(word);
        }
    }
    /**
     * Generates key from a cipher and plain. Returns null if it doesn't work
     *
     * @param cipher  the cipher
     * @param plain the plain
     * @return the cipher or null
     */
    @Override
    public String generateKey(String cipher, String plain)
    {
        if(plain.isEmpty())
        {
            int guess = attemptKeyGuess(cipher);
            return guess==NONE_FOUND?"":(""+guess);
        }

        if(cipher.isEmpty()||cipher.length()!=plain.length())
        {
            return NO_OUTPUT;
        }



        return String.valueOf(cipher.charAt(0)-plain.charAt(0));
    }

    private int attemptKeyGuess(String cipher)
    {
        String[] words= cipher.toLowerCase().split(" ");
        return _attemptKeyGuess(words, 0,0);
    }

    private int _attemptKeyGuess(String[] words,int wordIndex,int shift)
    {
        if(shift==26) //if shifted through and nothing found
        {
            return NONE_FOUND;
        }
        String guess = shiftText(words[wordIndex],shift*PREV);
        if(wordBank.contains(guess))
        {
            if(wordIndex==words.length-1)//if all words agree, it is successful
            {
                return shift;
            }
            else
            {
                return _attemptKeyGuess(words,wordIndex+1,shift);
            }
        }
        return _attemptKeyGuess(words,0,shift+1);//if all else fails, start from the top again
    }

    /**
     * Generates plain from a cipher and key. Returns null if it doesn't work
     *
     * @param cipher  the cipher
     * @param key the key
     * @return plain or null
     */
    @Override
    public String generatePlain(String cipher, String key)
    {
        if(key.isEmpty())
        {
            int offsetGuess = attemptKeyGuess(cipher);
            return offsetGuess==NONE_FOUND?"":shiftText(cipher,offsetGuess*PREV);
        }
        return generateText(cipher,key,PREV);
    }

    /**
     * Generates cipher from plain and key. Returns null if it doesn't work
     *
     * @param plain  plain
     * @param key key
     * @return cipher
     */
    @Override
    public String generateCipher(String plain, String key)
    {
        return generateText(plain,key,NEXT);
    }

    private String generateText(String text, String key, int direction)
    {
        int offset=0;
        try
        {
            offset=Integer.parseInt(key);
        }
        catch(Exception e)
        {
            return NO_OUTPUT;
        }
        if(offset<0)
        {
            offset+=26;
        }

        if(offset<0||offset>26)
        {
            return NO_OUTPUT;
        }

        text=text.toLowerCase();
        return shiftText(text,direction*offset);

    }

    private String shiftText(String text, int shift)
    {
        StringBuilder builder = new StringBuilder(text.length());
        for(char c: text.toCharArray())
        {
            if(c<'a'||c>'z'){builder.append(c);continue;}//skip letters
            char output = shiftLetter(c,shift);
            builder.append(output);
        }

        return builder.toString();
    }
    private char shiftLetter(char letter, int offset)
    {
        char output = (char)((letter-'a'+offset)%26+'a');
        while(output<'a')
        {
            output= (char)(output+26);
        }
        return output;
    }

    /**
     * returns name of the algorithms
     *
     * @return the name of the algorithm
     */
    @Override
    public String getName()
    {
        return "Caesar Cipher";
    }

    /**
     * returns short name of the algorithm
     *
     * @return short name of the algorithm
     */
    @Override
    public String getShortName()
    {
        return "Caesar";
    }




}
