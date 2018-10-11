// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TokensCtrl.java

package wrapper.tokenizador;

import java.util.Hashtable;
import wrapper.comum.Token;

public class TokensCtrl
{

    public TokensCtrl()
    {
        hash = new Hashtable();
        idCtrl = 0;
    }

    public Token getToken(String valorToken)
    {
        if(!hash.containsKey(valorToken))
            hash.put(valorToken, new Token(valorToken, ++idCtrl));
        return (Token)hash.get(valorToken);
    }

    private Hashtable hash;
    private int idCtrl;
}
