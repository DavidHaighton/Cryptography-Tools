package Framework;

import java.util.*;

public class Model<Data extends CryptoData>
{
    private final List<ModelListener<Data>> listeners = new ArrayList<>(1);
    private final CryptoAlgorithm<Data> algorithm;
    private Data storedData;

    public Model(CryptoAlgorithm<Data> algorithm)
    {
        this.algorithm = algorithm;
    }


    public final void sync()
    {
        storedData = algorithm.getDefaultState();
        notifyAllListeners(storedData);
    }

    public final void updateModelData(String type, Object data)
    {
        storedData.getData().put(type, data);
        storedData = algorithm.solveFor(storedData);
        notifyAllListeners(storedData);
    }

    public final CryptoAlgorithm<Data> getAlgorithm()
    {
        return algorithm;
    }

    public final void addModelChangeListener(ModelListener<Data> listener)
    {
        listeners.add(listener);
    }

    public final void removeModelChangeListener(ModelListener<Data> listener)
    {
        listeners.remove(listener);
    }

    protected final void notifyAllListeners(Data data)
    {
        ModelEvent<Data> event = new ModelEvent<>(this,data);
        for(ModelListener<Data> listener:listeners)
        {
            listener.modelChanged(event);
        }
    }
}
