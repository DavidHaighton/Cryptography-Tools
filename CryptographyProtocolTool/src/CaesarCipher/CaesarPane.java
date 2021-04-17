package CaesarCipher;

import Core.*;

import javax.swing.*;

public class CaesarPane extends DefaultAppStringPane
{
    public CaesarPane()
    {
        super(new SymmetricModel(new CaesarAlgorithm(),"","",""), SymCryptoPartType.ALL, SymCryptoPartType.KEY);
    }

    public static void main(String[] args)
    {
        new CaesarPane().showPopupFrame(JFrame.EXIT_ON_CLOSE);
    }
}
