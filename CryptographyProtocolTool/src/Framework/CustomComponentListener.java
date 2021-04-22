package Framework;

public interface CustomComponentListener<Type>
{
    public void CryptoComponentValueChanged(CustomComponentValueChanged<Type> event);
}
