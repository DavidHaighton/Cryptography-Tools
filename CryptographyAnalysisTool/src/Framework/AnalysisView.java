package Framework;

/**
 * The interface required to observe to the model
 */
public interface AnalysisView
{
    /**
     * the data in the model changed
     * @param e the event to cause it
     */
    public void dataUpdated(final AnalyzedEvent e);
}
