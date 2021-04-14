import java.util.*;

public class Predictor
{
    private List<FreqMapping> mapping;
    public Predictor()
    {
        Scanner reader = new Scanner(getClass().getResourceAsStream("letter_histogram.txt"));
        mapping = new ArrayList<>(26);
        while(reader.hasNextLine())
        {
            String[] sides = reader.nextLine().split(":");
            char letter = sides[0].charAt(0);
            float frequency = Float.parseFloat(sides[1]);
            mapping.add(new FreqMapping(letter,frequency));
        }
        Collections.sort(mapping);
    }


    public Map<Character,Character> makePrediction(String cipher)
    {
        Map<Character,Integer> frequency = new HashMap<>(26);
        for(char c: cipher.toCharArray())
        {
            if(c<'a'||c>'z')
            {
                continue;
            }
            frequency.putIfAbsent(c,0);
            frequency.put(c,frequency.get(c)+1);
        }
        List<FreqMapping> ciphermapping  = new ArrayList<>(26);
        for(Map.Entry<Character,Integer> entry: frequency.entrySet())
        {
            ciphermapping.add(new FreqMapping(entry.getKey(),entry.getValue()));
        }
        Collections.sort(ciphermapping);

        Map<Character,Character> prediction = new HashMap<>(26);
        int diff = mapping.size()-ciphermapping.size();
        for(int i=ciphermapping.size()-1;i>=0;i--)
        {
            int mappingIndex=i+diff;
            prediction.put(ciphermapping.get(i).getCharacter(),mapping.get(mappingIndex).getCharacter());
        }
        return prediction;
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
