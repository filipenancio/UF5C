// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Identidade.java

package wrapper.comum;

import java.util.*;

// Referenced classes of package wrapper.comum:
//            Token

public class Identidade
{

    public Token[] getTokensEmComum()
    {
        return (Token[])tokensEmComum.toArray(new Token[tokensEmComum.size()]);
    }

    public Integer[] getTiposDeTokenEmComum()
    {
        return (Integer[])tiposDeTokenEmComum.toArray(new Integer[tiposDeTokenEmComum.size()]);
    }

    public Identidade()
    {
        tokensEmComum = new HashSet();
        tiposDeTokenEmComum = new HashSet();
    }

    public void addToken(Token token)
    {
        tokensEmComum.add(token);
    }

    public void addTipoDeToken(Integer tipo)
    {
        tiposDeTokenEmComum.add(tipo);
    }

    public String getKey()
    {
        Token arrTokens[] = (Token[])tokensEmComum.toArray(new Token[tokensEmComum.size()]);
        Arrays.sort(arrTokens);
        Integer arrTipos[] = (Integer[])tiposDeTokenEmComum.toArray(new Integer[tiposDeTokenEmComum.size()]);
        Arrays.sort(arrTipos);
        String key = "TK:";
        Token atoken[];
        int j = (atoken = arrTokens).length;
        for(int i = 0; i < j; i++)
        {
            Token token = atoken[i];
            key = (new StringBuilder(String.valueOf(key))).append(token.getId()).append(".").toString();
        }

        key = (new StringBuilder(String.valueOf(key))).append("TP:").toString();
        for(Iterator iterator = tiposDeTokenEmComum.iterator(); iterator.hasNext();)
        {
            Integer tipo = (Integer)iterator.next();
            key = (new StringBuilder(String.valueOf(key))).append(tipo).append(".").toString();
        }

        return key;
    }

    private HashSet tokensEmComum;
    private HashSet tiposDeTokenEmComum;
}
