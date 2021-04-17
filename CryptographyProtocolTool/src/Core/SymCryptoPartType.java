package Core;

public enum SymCryptoPartType
{
    KEY(1),
    CIPHER(2),
    PLAIN(4);

    public final int value;
    SymCryptoPartType(int value)
    {
        this.value = value;
    }

    public final static int ALL=7;
}
