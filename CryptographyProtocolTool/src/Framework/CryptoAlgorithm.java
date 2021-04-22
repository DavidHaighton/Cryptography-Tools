package Framework;

import java.util.Set;

public interface CryptoAlgorithm<Data extends CryptoData>
{
    /**
     * Given the data from the model, the Crypto-Algorithm will solve for the requested data in the data object
     * @param data the data to perform calculations on
     * @return the complete data object
     */
    public Data solveFor(Data data);

    /**
     * returns the initial state of the application
     * @return the initial state of the application
     */
    public Data getDefaultState();

    /**
     * returns a set of the valid tasks/modes that the algorithm can perfom
     * @return the set of valid modes for the algorithm
     */
    public Set<String> getValidModes();

    /**
     * returns the full name of the algorithm
     * @return the full name of the algorithm
     */
    public String getName();

    /**
     * returns the short form of the name for the algorithm
     * @return the short form of the name for the algorithm
     */
    public String getShortName();
}
