// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Padrao.java

package wrapper.tokenizador;

import java.util.regex.Pattern;

public class Padrao
{

    public Padrao(String regex, boolean tokeniza, boolean separador, int tipo)
    {
        this.regex = regex;
        this.tokeniza = tokeniza;
        this.separador = separador;
        pattern = Pattern.compile(regex);
        this.tipo = tipo;
    }

    public int getTipo()
    {
        return tipo;
    }

    public String getRegex()
    {
        return regex;
    }

    public boolean isTokeniza()
    {
        return tokeniza;
    }

    public boolean isSeparador()
    {
        return separador;
    }

    public Pattern getPattern()
    {
        return pattern;
    }

    private String regex;
    private boolean tokeniza;
    private boolean separador;
    private Pattern pattern;
    private int tipo;
}
