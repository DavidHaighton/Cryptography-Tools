package CaesarCipher;

import Components.TextAreaScroller;
import Framework.AppPane;
import Framework.Binding;
import Framework.CryptoData;
import Framework.Model;

import javax.swing.*;
import java.awt.*;

public class CaesarDisplay extends AppPane
{

    TextAreaScroller cipherArea = new TextAreaScroller("Cipher"),
            plainArea = new TextAreaScroller("Plain"),
            keyArea = new TextAreaScroller("Key");
    public CaesarDisplay()
    {
        super("","");
        this.setLayout(new GridLayout(1,2));
        JPanel leftPanel = new JPanel();
        this.add(leftPanel);
        leftPanel.setLayout(new GridLayout(2,1));
        leftPanel.add(keyArea);
        leftPanel.add(plainArea);
        this.add(cipherArea);

        Model<Symm> model = new Model(new CaesarAlgorithm());

        Binding.bind<>();


    }



    public static void main(String[] args)
    {
        new CaesarDisplay().showPopupFrame(JFrame.EXIT_ON_CLOSE);
    }
}
