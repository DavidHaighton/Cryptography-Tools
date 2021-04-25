package Algorithms;

import CommonDisplays.SymmetricBasic;
import Framework.AnalysisDisplay;
import Framework.SymmetricAlgorithm;

/**
 * Example of how to make a very simple caesar algorithm
 */
public class CaesarAlgorithm extends SymmetricAlgorithm
{
    public CaesarAlgorithm()
    {
        super("Caesar");
    }

    @Override
    protected void solve()
    {
        int plainSize = plain.length(),
        keySize = key.length(),
        cipherSize = cipher.length();

        if(mode.equals(KEY) && plainSize==cipherSize)
        {
            key=getKey();
            return;
        }

        int shift=0;
        try
        {
            shift = Integer.parseInt(key);
        }catch(Exception e)
        {
            return;
        }

        if(mode.equals(CIPHER_TEXT))
        {
            cipher = shiftString(plain,shift);
        }
        else if(mode.equals(PLAIN_TEXT))
        {
            plain = shiftString(cipher,-shift);
        }

    }


    private String getKey()
    {
        if(cipher.length()==0){return "";}
        int diff= cipher.charAt(0)-plain.charAt(0);;
        for (int i=0;i<plain.length();i++)
        {
            int iterdiff = cipher.charAt(i)-plain.charAt(i);
            if(diff!=iterdiff){return "NOT POSSIBLE";}
        }
        return diff+"";
    }

    private String shiftString(String input, int shift)
    {
        StringBuilder builder = new StringBuilder(input.length());
        input=input.toLowerCase();
        for(char c: input.toCharArray())
        {
            builder.append(shiftChar(c,shift));
        }
        return builder.toString();
    }



    private char shiftChar(char c, int shift)
    {
        if(c>'z'||c<'a') return c;
        char output =(char)(((c-'a')+shift)%26+'a');
        while(output<'a')
        {
            output+=26;
        }
        return output;
    }



    @Override
    protected boolean isValidInput()
    {
        int count = cipher.length()==0?1:0;
        count+= key.length()==0?1:0;
        count+= cipher.length()==0?1:0;
        return true;
        //return count>=2;
    }

    public static AnalysisDisplay getDisplay()
    {
        return new SymmetricBasic(new CaesarAlgorithm());
    }
}
