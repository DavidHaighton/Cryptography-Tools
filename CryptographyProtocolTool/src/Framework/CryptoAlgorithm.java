package Framework;

import java.util.Map;

/**
 * The algorithm class all cryptography algorithm classes must inherit
 */
public abstract class CryptoAlgorithm
{
    private final String name;
    protected Map<String,String> data;
    public CryptoAlgorithm(String name)
    {
        this.name = name;
    }
    protected String mode;

    protected abstract void solve();

    protected abstract boolean isValidInput();

    /**
     * Called to solve for one of the data points in the map. The mode specifies which
     * @param data all the data input
     * @param mode what to solve for
     * @return the new map with a solution
     */
    public final Map<String,String> completeData(Map<String,String> data, String mode)
    {
        this.mode = mode;
        this.data = data;
        unWrapData();
        if(isValidInput())
        {
            solve();
        }
        wrapData();
        return this.data;
    }

    /**
     * Used to unwrap the data from the map into easier to manage variables
     */
    protected abstract  void unWrapData();

    /**
     * Used to wrap the easier to manage variables into the map
     */
    protected abstract void wrapData();


    /**
     * a method used to get a value from the data. If the value is null, the method changes it to an empty String
     * @param type the key for the map
     * @return either the value for the key or an empty string
     */
    protected String getSafeText(String type)
    {
        return this.data.containsKey(type)?this.data.get(type):"";
    }

    /**
     * Returns an array of acceptable modes for the given algorithm
     * @return the modes the algorithm can be in
     */
    public abstract String[] getModes();

    /**
     * the name of the algorithm
     * @return the name of the algorithm
     */
    public final String getName()
    {
        return this.name;
    }
}
