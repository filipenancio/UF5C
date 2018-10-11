// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Tokenizador.java

package wrapper.tokenizador;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import wrapper.comum.Token;

// Referenced classes of package wrapper.tokenizador:
//            TokensCtrl, Padrao, Substring

public class Tokenizador
{

    public Tokenizador()
    {
        padroes = new ArrayList();
        tokensCtrl = new TokensCtrl();
    }

    public void setTokenizarRestos(boolean b)
    {
        tokenizarRestos = b;
    }

    public void setTipoDefault(int tipo)
    {
        tipoDefault = tipo;
    }

    public void addPadroes(Padrao padrao)
    {
        padroes.add(padrao);
    }

    public Token[] tokenizar(String texto)
    {
        if(padroes.size() == 0)
        {
            throw new RuntimeException("N\343o existem padr\365es definidos.");
        } else
        {
            LinkedList tokenList = tokenizar(texto, 0);
            return (Token[])tokenList.toArray(new Token[tokenList.size()]);
        }
    }

    private LinkedList tokenizar(String texto, int indexSeparador)
    {
        LinkedList tokenList = new LinkedList();
        texto = texto.trim();
        if(texto.length() == 0)
            return new LinkedList();
        if(indexSeparador >= padroes.size())
        {
            if(tokenizarRestos)
            {
                Token token = tokensCtrl.getToken(texto);
                token.setTipo(tipoDefault);
                tokenList.add(token);
            }
        } else
        {
            Padrao padrao = (Padrao)padroes.get(indexSeparador);
            LinkedList substrings = getSubstrings(padrao, texto);
            for(Iterator iterator = substrings.iterator(); iterator.hasNext();)
            {
                Substring subs = (Substring)iterator.next();
                if(subs.isTokeniza())
                {
                    Token token = tokensCtrl.getToken(subs.getValor());
                    token.setTipo(padrao.getTipo());
                    tokenList.add(token);
                } else
                {
                    LinkedList novaTokenList = tokenizar(subs.getValor(), indexSeparador + 1);
                    tokenList.addAll(novaTokenList);
                }
            }

        }
        return tokenList;
    }

    private LinkedList getSubstrings(Padrao padrao, String texto)
    {
        LinkedList substrings = new LinkedList();
        Pattern pattern = padrao.getPattern();
        Matcher matcher = pattern.matcher(texto);
        int pos;
        int posInicioMatch;
        String valorToken;
        for(pos = 0; matcher.find(); pos = posInicioMatch + valorToken.length())
        {
            posInicioMatch = matcher.start();
            String valorAnterior = texto.substring(pos, posInicioMatch);
            valorToken = matcher.group();
            Substring subsNoTok = new Substring(valorAnterior, false);
            Substring subsTok = new Substring(valorToken, true);
            substrings.addLast(subsNoTok);
            if(padrao.isTokeniza())
                substrings.addLast(subsTok);
        }

        String valorAnterior = texto.substring(pos, texto.length());
        Substring subsNoTok = new Substring(valorAnterior, false);
        substrings.addLast(subsNoTok);
        return substrings;
    }

    private ArrayList padroes;
    private TokensCtrl tokensCtrl;
    private boolean tokenizarRestos;
    private int tipoDefault;
}
