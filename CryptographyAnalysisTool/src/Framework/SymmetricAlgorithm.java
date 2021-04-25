package Framework;

/**
 * Symmetric algorithm class that should be used for any Symmetric Algorithm being written
 */
public abstract class SymmetricAlgorithm extends CryptoAlgorithm
{
    public static final String PLAIN_TEXT="PLAIN_TEXT",
            KEY="KEY",
            CIPHER_TEXT="CIPHER_TEXT";
    private final String[] modes= {PLAIN_TEXT,KEY,CIPHER_TEXT};

    protected String plain, key, cipher;

    @Override
    protected void unWrapData()
    {
        plain = getSafeText(PLAIN_TEXT);
        key = getSafeText(KEY);
        cipher = getSafeText(CIPHER_TEXT);

    }



    @Override
    protected void wrapData()
    {
        this.data.put(PLAIN_TEXT,plain);
        this.data.put(KEY,key);
        this.data.put(CIPHER_TEXT,cipher);
    }

    @Override
    public String[] getModes()
    {
        return modes;

    }

    public SymmetricAlgorithm(String name)
    {
        super(name);
    }
}
