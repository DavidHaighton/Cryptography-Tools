package Framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The core MVC model of the application.
 * It works with all algorithms and Views
 */
public class Model
{
    private final CryptoAlgorithm algorithm;
    private List<AnalysisView> views =new ArrayList<>(3);
    private Map<String,String> data = new HashMap<>();

    private String mode;

    /**
     * constructs the model off of the algorithm
     * @param algorithm the algorithm
     */
    public Model(final CryptoAlgorithm algorithm)
    {
        this.algorithm = algorithm;
        mode = algorithm.getModes()[0];
    }

    /**
     * sets the mode of the model
     * @param mode the mode of the model
     */
    public final void setMode(String mode)
    {
        this.mode = mode;
    }

    /**
     * returns the mode of the model
     * @return the mode of the model
     */
    public final String getMode()
    {
        return this.mode;
    }

    /**
     * updates the data in the model and solves for the mode
     * @param key the type of data
     * @param value the value of the data
     */
    public final void updateData(final String key, final String value)
    {
        this.data.put(key,value);
        this.data=this.algorithm.completeData(this.data, this.getMode());
        updateAllViews();
    }

    /**
     * returns the data value for the type
     * @param key the type
     * @return the data value
     */
    public final String getData(final String key)
    {
        return this.data.get(key);
    }

    /**
     * updates all views at once saying data changed
     */
    private final void updateAllViews()
    {
        AnalyzedEvent event = new AnalyzedEvent(this);
        for(AnalysisView view: views)
        {
            view.dataUpdated(event);
        }
    }

    /**
     * add a view to the model
     * @param view the view to add
     */
    public final void addView(final AnalysisView view)
    {
        views.add(view);
    }

    /**
     * remove a view from the model
     * @param view the view to remove
     */
    public final void removeView(AnalysisView view)
    {
        views.remove(view);
    }

    /**
     * get the algorithm the model is using
     * @return the algorithm the model is using
     */
    public final CryptoAlgorithm getAlgorithm()
    {
        return this.algorithm;
    }
}
