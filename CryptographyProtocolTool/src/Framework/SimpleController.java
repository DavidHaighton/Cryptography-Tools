package Framework;

import java.util.*;

/**
 * Controller for the SimpleComponents. Allows them to communicate to the model without error
 */
public class SimpleController implements SimpleComponentListener, AnalysisView{

    private Map<SimpleComponent, String> componentChangeMap = new HashMap<>(3);

    private Model m;
    public SimpleController(Model m)
    {
        this.m = m;
    }
    private String origin = "";

    /**
     * connects the simple component with the model
     * @param component the component
     * @param type the piece of the data being listened to
     */
    public void addSimpleComponent(SimpleComponent component, String type)
    {
        component.addListener(this);
        m.addView(this);
        this.componentChangeMap.put(component,type);
    }

    @Override
    public void dataUpdated(AnalyzedEvent e)
    {
        for(Map.Entry<SimpleComponent, String> entry: componentChangeMap.entrySet())
        {
            if(!entry.getValue().equals(origin))
                entry.getKey().setValue(m.getData(entry.getValue()));
        }

    }

    @Override
    public void valueChanged(SimpleChangedEvent event)
    {
        SimpleComponent component =(SimpleComponent)event.getSource();
        origin = componentChangeMap.get(component);
        m.updateData(componentChangeMap.get(component),component.getValue());
    }
}
