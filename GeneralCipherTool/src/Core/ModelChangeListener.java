package Core;

/**
 * Interface designed to listen to model
 */
public interface ModelChangeListener
{
    /**
     * called when the model changes
     * @param event the event to trigger the change
     */
    public void modelChanged(ModelChangedEvent event);
}
