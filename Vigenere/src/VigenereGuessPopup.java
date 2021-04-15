import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.CharArrayReader;
import java.util.*;
import java.util.List;

public class VigenereGuessPopup
{
    private  List<Character> populationPlainLetters = new ArrayList<>(7);
    JFrame frame;

    public VigenereGuessPopup()
    {
        setupLogic();

        frame = new JFrame("Guesser Helper");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        frame.removeAll();
                    }
                }
        );
        frame.setSize(500,250);
        frame.setVisible(false);
    }

    public void show(String cipher)
    {
        int keyLength=0;
        if(keyLength==cipher.length())
        {
            JOptionPane.showMessageDialog(null,"NO TEXT TO ANALYZE","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            keyLength=Integer.parseInt(JOptionPane.showInputDialog("How Many characters in your key?", ""));
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,"NOT A NUMBER","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(keyLength*3>cipher.length())
        {
            JOptionPane.showMessageDialog(null,"NOT A BIG ENOUGH NUMBER","ERROR",JOptionPane.ERROR_MESSAGE);
            return;
        }

        List<Character> topCharacters = new ArrayList<>(keyLength);
        List<String> parts = generateCipherParts(cipher,keyLength);
        for(String part:parts)
        {
            topCharacters.add(generateTopCharacter(part));
        }

        showWindow(topCharacters);

    }

    private void showWindow(List<Character> topCharacters)
    {
        frame.setLayout(new GridLayout(topCharacters.size()+1,populationPlainLetters.size()+1));
        frame.add(new JPanel());//fill top left with nothing
        for(char c:populationPlainLetters)
        {
            JPanel panel = new JPanel();
            panel.setBackground(Color.ORANGE);
            JLabel label = new JLabel(c+"");
            panel.add(label);
            frame.add(panel);
        }

        Color rowColor = Color.lightGray;
        for(char cipher: topCharacters)
        {
            JPanel temppanel = new JPanel();
            temppanel.setBackground(Color.red);
            JLabel label = new JLabel(cipher+"");
            temppanel.add(label);
            frame.add(temppanel);
            for(char plain: populationPlainLetters)
            {
                temppanel = new JPanel();
                temppanel.setBackground(rowColor);
                JLabel keyLabel = new JLabel(getKeyChar(cipher,plain)+"");
                //keyLabel.setFont(new Font());
                temppanel.add(keyLabel);
                frame.add(temppanel);
            }
            rowColor = rowColor==Color.lightGray?Color.WHITE:Color.lightGray;
        }

        frame.setVisible(true);

    }


    private char getKeyChar(char cipher, char plain)
    {
        return (char)((cipher-plain+26)%26+'a');
    }

    private List<String> generateCipherParts(String cipher, int keyLength)
    {
        char[] chars = cipher.toCharArray();
        List<StringBuilder> builders = new ArrayList<>(keyLength);
        int size = (int)Math.ceil(chars.length/keyLength);
        for(int i=0;i<keyLength;i++)
        {
            builders.add(new StringBuilder(size));
        }

        for(int i=0;i<chars.length;i++)
        {
            builders.get(i%keyLength).append(chars[i]);
        }
        List<String> output = new ArrayList<>(keyLength);
        for(StringBuilder builder: builders)
        {
            output.add(builder.toString());
        }
        return output;

    }
    private char generateTopCharacter(String cipherParts)
    {
        //make histogram
        Map<Character,Integer> map = new HashMap<>(26);
        for(char character: cipherParts.toCharArray())
        {
            if(character>'z'||character<'a')
            {
                continue;
            }
            map.putIfAbsent(character,0);
            map.put(character,map.get(character)+1);
        }
        //find max
        FreqMapping charFreq = new FreqMapping('a',0);
        for(Map.Entry<Character,Integer> entry: map.entrySet())
        {
            if(charFreq.getFrequency()<entry.getValue())
            {
                charFreq = new FreqMapping(entry.getKey(),entry.getValue());
            }
        }
        return charFreq.character;

    }


    private void setupLogic()
    {
        Scanner reader = new Scanner(getClass().getResourceAsStream("letter_histogram.txt"));
        List<FreqMapping> mapping = new ArrayList<>(26);
        mapping = new ArrayList<>(26);
        while(reader.hasNextLine())
        {
            String[] sides = reader.nextLine().split(":");
            char letter = sides[0].charAt(0);
            float frequency = Float.parseFloat(sides[1]);
            mapping.add(new FreqMapping(letter,frequency));
        }
        Collections.sort(mapping);
        for(int i =1;i<8; i++)
        {
            populationPlainLetters.add(mapping.get(mapping.size()-i).character);
        }
    }
    private class FreqMapping implements Comparable<FreqMapping>
    {
        private final float frequency;
        private final char character;
        public FreqMapping(char character, float frequency)
        {
            this.frequency = frequency;
            this.character = character;
        }

        public float getFrequency()
        {
            return frequency;
        }
        public char getCharacter()
        {
            return character;
        }

        @Override
        public int compareTo(FreqMapping o)
        {
            if(this.frequency<o.frequency)
            {
                return -1;
            }
            else if(this.frequency==o.frequency)
            {
                return 0;
            }
            return 1;
        }
    }

}
