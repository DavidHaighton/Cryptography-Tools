package Core;

/**
 * The cryptography algorithm interface.
 * It outlines the relationship between the key, plain and cipher.
 * @param <Cipher> The type of cipher
 * @param <Key> The type of key
 * @param <Plain> The type of plain
 */
public interface CryptoAlgo<Cipher, Key, Plain>
{
    /**
     * Generates key from a cipher and plain. Returns null if it doesn't work
     * @param cipher the cipher
     * @param plain  the plain
     * @return the cipher or null
     */
    public Key generateKey(Cipher cipher,Plain plain);

    /**
     * Generates plain from a cipher and key. Returns null if it doesn't work
     * @param cipher the cipher
     * @param key the key
     * @return plain or null
     */
    public Plain generatePlain(Cipher cipher, Key key);

    /**
     * Generates cipher from plain and key. Returns null if it doesn't work
     * @param plain plain
     * @param key key
     * @return cipher
     */
    public Cipher generateCipher(Plain plain, Key key );

    /**
     * returns name of the algorithms
     * @return the name of the algorithm
     */
    public String getName();

    /**
     * returns short name of the algorithm
     * @return short name of the algorithm
     */
    public String getShortName();
}
