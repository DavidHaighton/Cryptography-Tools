package Framework;

/**
 * a one way cryptography function that takes plain text and produces cipher text
 */
public abstract class HashAlgorithm extends CryptoAlgorithm
{
    public static final String PLAIN_TEXT="PLAIN_TEXT",
            CIPHER_TEXT="CIPHER";
    private final String[] modes= {CIPHER_TEXT, PLAIN_TEXT};

    protected String plain, key, cipher;

    @Override
    protected void unWrapData()
    {
        plain = getSafeText(PLAIN_TEXT);
        cipher = getSafeText(CIPHER_TEXT);

    }



    @Override
    protected void wrapData()
    {
        this.data.put(PLAIN_TEXT,plain);
        this.data.put(CIPHER_TEXT,cipher);
    }

    @Override
    public String[] getModes()
    {
        return modes;

    }

    public HashAlgorithm(String name)
    {
        super(name);
    }
}
