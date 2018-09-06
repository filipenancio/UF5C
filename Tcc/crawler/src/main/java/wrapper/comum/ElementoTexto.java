// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ElementoTexto.java

package wrapper.comum;

import wrapper.tokenizador.Tokenizador;

// Referenced classes of package wrapper.comum:
//            Path, Token

public class ElementoTexto
{

    public ElementoTexto(String texto, Path path, Tokenizador tokenizador)
    {
        this.texto = texto;
        this.path = path;
        tokens = tokenizador.tokenizar(texto);
    }

    public String getTexto()
    {
        return texto;
    }

    public Path getPath()
    {
        return path;
    }

    public Token[] getTokens()
    {
        return tokens;
    }

    private String texto;
    private Path path;
    private Token tokens[];
}
