import java.io.InputStream;
import java.net.URL;
import java.util.*;


//requirements
//read from file to get all english words and convert them into a map
public class Model
{
    private final Map<Character,Float> population = new HashMap<>(26);
    private List<AnalyzerView> views = new ArrayList(1);
    private String selectedText = "";
    private Map<String, Map<Character,Integer>> cache = new TreeMap<>();
    public Model()
    {
        //fill up the actual data of letters and their frequency
        Scanner histoReader = new Scanner(getClass().getResourceAsStream("letter_histogram.txt"));
        while (histoReader.hasNextLine())
        {
            String[] sides = histoReader.nextLine().split(":");
            char letter = sides[0].charAt(0); float freq = Float.parseFloat(sides[1]);
            population.put(letter,freq);
        }
    }

    public void selectText(String text)
    {
        this.selectedText = text.toLowerCase();
        int length=0;
        for(char c : text.toCharArray())
        {
            if(c>='a'||c<='z')
            {
                length++;
            }
        }
        for (AnalyzerView view :views)
        {
            view.update(new ModelChangedEvent(this,this.getFrequency(), length));
        }
    }

    public String getSelectedText()
    {
        return selectedText;
    }


    public Map<Character, Integer> getFrequency()
    {
        if(cache.containsKey(selectedText))//read from cache if available
        {
            return cache.get(selectedText);
        }
        //add to cache otherwise
        char[] characters = selectedText.toCharArray();
        Map<Character, Integer> mapping = new HashMap(26);
        for (char character : characters)
        {
            if(character>='a' && character<='z')
            {
                mapping.putIfAbsent(character,0);
                mapping.put(character,mapping.get(character)+1);
            }
        }
        cache.put(selectedText,mapping);
        //output character frequency mapping
        return mapping;
    }

    public float getPopulationLetterFrequency(char letter)
    {
        return population.get(letter);
    }

    public void removeView(AnalyzerView view)
    {
        this.removeView(view);
    }

    public void addView(AnalyzerView view)
    {
        views.add(view);
    }


}
