package CaesarCipher;

import Core.*;

import javax.swing.*;

public class Algorithm implements CryptoAlgo<String,String,String>
{
    private static final int NEXT=1,PREV=-1;
    private static final String NO_OUTPUT="";

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
        if(cipher.isEmpty()||plain.isEmpty() || cipher.length()!=plain.length())
        {
            return NO_OUTPUT;
        }



        return String.valueOf(cipher.charAt(0)-plain.charAt(0));
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

        text.toLowerCase();

        StringBuilder builder = new StringBuilder(text.length());
        for(char c: text.toCharArray())
        {
            if(c<'a'||c>'z'){continue;}//skip letters
            char output = (char)((c-'a'+offset*direction)%26+'a');
            builder.append(output);
        }
        System.out.println(builder.toString());

        return builder.toString();
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
