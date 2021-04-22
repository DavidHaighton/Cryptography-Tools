package Framework;

import java.util.*;

public abstract class CryptoData
{
    protected final Map<String, Object> data = new HashMap<>(4);
    private final String mode;

    public CryptoData(String mode)
    {
        this.mode = mode;
    }

    public final Map<String, Object> getData()
    {
        return data;
    }

    public String[] getCryptoModes()
    {
        String[] modes = new String[data.size()];
        for(String mode: data.keySet())
        {
            modes[modes.length]=mode;
        }
        return modes;
    }

}
