package Core;

import java.util.*;

/**
 * Symmetric Cryptography Algorithm MVC Model
 * It uses a cryptography algorithm to generate different parts of the cryptography process
 * @param <Cipher> Cipher type
 * @param <Key> Key type
 * @param <Plain> Plain type
 */
public class SymmetricModel<Cipher, Key, Plain>
{
    private List<ModelChangeListener> listenerList = new ArrayList<>(1);
    private final CryptoAlgo<Cipher, Key, Plain> algorithm;

    private Cipher cipher;
    private Key key;
    private Plain plain;

    /**
     *constructs model based on a cryptography algorithm and initial values
     * @param algorithm
     */
    public SymmetricModel(CryptoAlgo<Cipher, Key, Plain> algorithm, Cipher initCipher, Key initKey, Plain initPlain)
    {
        this.algorithm = algorithm;
        cipher = initCipher;
        key = initKey;
        plain = initPlain;
    }


    /**
     * sets a part of the cryptography process to the specified value
     * @param part the part
     * @param type the type of the part
     */
    public void setCryptographyPart(Object part, SymCryptoPartType type)
    {
        switch (type)
        {
            case KEY:
                key = (Key) part;
                break;
            case PLAIN:
                plain = (Plain) part;
                break;
            default:
                cipher = (Cipher) part;
                break;
        }
    }

    /**
     * updates the cryptography part specified and updates all model listeners
     * @param type the type of the part that needs to be updated
     */
    public void updateCryptoPart(SymCryptoPartType type)
    {
        Object part;
        switch(type)
        {
            case KEY:
                key=algorithm.generateKey(cipher,plain);
                part =key;
                break;
            case PLAIN:
                plain=algorithm.generatePlain(cipher,key);
                part=plain;
                break;
            default:
                cipher=algorithm.generateCipher(plain,key);
                part = cipher;
                break;
        }
        updateAllModelListeners(type,part);
    }

    /**
     * returns the asymmetric key
     * @return the key
     */
    public Key getKey()
    {
        return key;
    }

    /**
     * returns the cipher
     * @return the cipher
     */
    public Cipher getCipher()
    {
        return cipher;
    }

    /**
     * returns the plain
     * @return the plain
     */
    public Plain getPlain()
    {
        return plain;
    }

    /**
     * returns the algorithm used to encrypt and decrypt data
     * @return the algorithm
     */
    public CryptoAlgo<Cipher, Key, Plain> getAlgorithm()
    {
        return this.algorithm;
    }

    /**
     * updates all listeners that model has changed
     * @param type part type that changed
     */
    private void updateAllModelListeners(SymCryptoPartType type, Object part)
    {
        for(ModelChangeListener listener: listenerList)
        {
            listener.modelChanged(new ModelChangedEvent(this,type,part));
        }
    }

    /**
     * adds model listener to listen for when the model changes
     * @param listener listener
     */
    public void addModelListener(ModelChangeListener listener)
    {
        this.listenerList.add(listener);
    }

    /**
     * removes model listener from model
     * @param listener model listener
     */
    public void removeModelListener(ModelChangeListener listener)
    {
        this.listenerList.remove(listener);
    }
}
