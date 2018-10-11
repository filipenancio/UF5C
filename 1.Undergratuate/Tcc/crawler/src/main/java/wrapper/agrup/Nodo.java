// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Nodo.java

package wrapper.agrup;

import java.util.*;
import wrapper.comum.Token;

public abstract class Nodo
{

    public Nodo(int indexInicial, int indexFinal)
    {
        this.indexInicial = indexInicial;
        this.indexFinal = indexFinal;
        tokensEmComum = new HashSet();
        tiposDeTokenEmComum = new HashSet();
    }

    public int getIndexInicial()
    {
        return indexInicial;
    }

    public int getIndexFinal()
    {
        return indexFinal;
    }

    public void addTokenEmComum(Token token)
    {
        tokensEmComum.add(token);
    }

    public void addTipoDeTokenEmComum(Integer i)
    {
        tiposDeTokenEmComum.add(i);
    }

    public List getTokensEmComum()
    {
        return new ArrayList(tokensEmComum);
    }

    public List getTiposDeTokenEmComum()
    {
        return new ArrayList(tiposDeTokenEmComum);
    }

    public boolean contemToken(Token token)
    {
        return tokensEmComum.contains(token);
    }

    public boolean contemTipoDeToken(Integer tipo)
    {
        return tiposDeTokenEmComum.contains(tipo);
    }

    private HashSet tokensEmComum;
    private HashSet tiposDeTokenEmComum;
    private int indexInicial;
    private int indexFinal;
}
