package CommonDisplays;

import Components.SimpleTextInput;
import Framework.AnalysisDisplay;
import Framework.AsymmetricAlgorithm;
import Framework.SymmetricAlgorithm;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class AsymmetricBasic extends AnalysisDisplay
{
    private final SimpleTextInput plainInput= SimpleTextInput.factory(SimpleTextInput.AREA),
            privateKey = SimpleTextInput.factory(SimpleTextInput.FIELD),
            publicKey = SimpleTextInput.factory(SimpleTextInput.FIELD),
            cipherInput = SimpleTextInput.factory(SimpleTextInput.AREA);
    public AsymmetricBasic(AsymmetricAlgorithm algorithm)
    {
        super(algorithm);

        getController().addSimpleComponent(privateKey,AsymmetricAlgorithm.PRIVATE_KEY);
        getController().addSimpleComponent(publicKey,AsymmetricAlgorithm.PUBLIC_KEY);
        getController().addSimpleComponent(plainInput, SymmetricAlgorithm.PLAIN_TEXT);
        getController().addSimpleComponent(cipherInput, SymmetricAlgorithm.CIPHER_TEXT);

        this.setLayout(new GridLayout(1,2,1,1));

        JPanel leftPanel = new JPanel();
        this.add(leftPanel);
        leftPanel.setLayout(new BorderLayout(1,1));
        leftPanel.add(privateKey,BorderLayout.NORTH);
        leftPanel.add(plainInput,BorderLayout.CENTER);
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout(1,1));
        rightPanel.add(publicKey);
        rightPanel.add(cipherInput);
        this.add(rightPanel);

        privateKey.setBorder(new TitledBorder("Private Key"));
        publicKey.setBorder(new TitledBorder("Public Key"));
        plainInput.setBorder(new TitledBorder("Plain"));
        cipherInput.setBorder(new TitledBorder("Cipher"));

    }
}
