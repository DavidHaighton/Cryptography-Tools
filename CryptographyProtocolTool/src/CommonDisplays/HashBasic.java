package CommonDisplays;

import Components.SimpleTextInput;
import Framework.AnalysisDisplay;
import Framework.CryptoAlgorithm;
import Framework.HashAlgorithm;
import Framework.SymmetricAlgorithm;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Simplest Hash Algorithm view there is
 */
public class HashBasic extends AnalysisDisplay
{
    private final SimpleTextInput plainInput= SimpleTextInput.factory(SimpleTextInput.AREA),
            cipherInput = SimpleTextInput.factory(SimpleTextInput.AREA);
    public HashBasic(HashAlgorithm algorithm)
    {
        super(algorithm);

        getController().addSimpleComponent(plainInput, HashAlgorithm.PLAIN_TEXT);
        getController().addSimpleComponent(cipherInput, HashAlgorithm.CIPHER_TEXT);

        this.setLayout(new GridLayout(1,2,1,1));

        this.add(plainInput,BorderLayout.CENTER);

        this.add(cipherInput);

        plainInput.setBorder(new TitledBorder("Plain"));
        cipherInput.setBorder(new TitledBorder("Cipher"));

    }


}
