package CommonDisplays;

import Framework.*;
import Components.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * A Display class that features a very basic layout and allows for a simple layout for symmetric algorithms
 */
public class SymmetricBasic extends AnalysisDisplay
{
    private final SimpleTextInput plainInput= SimpleTextInput.factory(SimpleTextInput.AREA),
            keyInput = SimpleTextInput.factory(SimpleTextInput.FIELD),
            cipherInput = SimpleTextInput.factory(SimpleTextInput.AREA);
    public SymmetricBasic(SymmetricAlgorithm algorithm)
    {
        super(algorithm);

        getController().addSimpleComponent(keyInput,SymmetricAlgorithm.KEY);
        getController().addSimpleComponent(plainInput, SymmetricAlgorithm.PLAIN_TEXT);
        getController().addSimpleComponent(cipherInput, SymmetricAlgorithm.CIPHER_TEXT);

        this.setLayout(new GridLayout(1,2,1,1));

        JPanel leftPanel = new JPanel();
        this.add(leftPanel);
        leftPanel.setLayout(new BorderLayout(1,1));
        leftPanel.add(keyInput,BorderLayout.NORTH);
        leftPanel.add(plainInput,BorderLayout.CENTER);

        this.add(cipherInput);

        keyInput.setBorder(new TitledBorder("Key"));
        plainInput.setBorder(new TitledBorder("Plain"));
        cipherInput.setBorder(new TitledBorder("Cipher"));

    }
}
