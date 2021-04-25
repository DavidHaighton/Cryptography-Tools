package Framework;

/**
 * Class that should be used for asymmetric algorithms
 */
public abstract class AsymmetricAlgorithm extends CryptoAlgorithm
{
    public static final String PLAIN_TEXT="PLAIN_TEXT",
            PUBLIC_KEY="PUBLIC_KEY",
            PRIVATE_KEY="PRIVATE_KEY",
            CIPHER_TEXT="CIPHER_TEXT";
    private final String[] modes= {PLAIN_TEXT,PRIVATE_KEY, PUBLIC_KEY, CIPHER_TEXT};

    protected String plain, privateKey, publicKey, cipher;

    @Override
    protected void unWrapData()
    {
        plain = getSafeText(PLAIN_TEXT);
        privateKey = getSafeText(PRIVATE_KEY);
        publicKey = getSafeText(PUBLIC_KEY);
        cipher = getSafeText(CIPHER_TEXT);

    }



    @Override
    protected void wrapData()
    {
        this.data.put(PLAIN_TEXT,plain);
        this.data.put(PRIVATE_KEY,privateKey);
        this.data.put(PUBLIC_KEY,publicKey);
        this.data.put(CIPHER_TEXT,cipher);
    }

    @Override
    public String[] getModes()
    {
        return modes;

    }

    public AsymmetricAlgorithm(String name)
    {
        super(name);
    }
}