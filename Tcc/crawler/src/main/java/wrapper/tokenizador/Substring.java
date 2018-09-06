// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Substring.java

package wrapper.tokenizador;


public class Substring
{

    public Substring(String s, boolean tokeniza)
    {
        valor = s;
        this.tokeniza = tokeniza;
    }

    public boolean isTokeniza()
    {
        return tokeniza;
    }

    public String getValor()
    {
        return valor;
    }

    private boolean tokeniza;
    private String valor;
}
