package Framework;

public interface ModelListener<Data extends CryptoData>
{
    public void modelChanged(ModelEvent<Data> event);
    public void modelSync(ModelEvent<Data> event);
}
