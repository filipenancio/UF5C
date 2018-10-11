// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TokenizadorElementoTexto.java

package wrapper.parser;

import java.io.PrintStream;
import wrapper.comum.TipoToken;
import wrapper.comum.Token;
import wrapper.tokenizador.Padrao;
import wrapper.tokenizador.Tokenizador;

public class TokenizadorElementoTexto extends Tokenizador
{

    public TokenizadorElementoTexto()
    {
        String regexEmail = "[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})";
        String regexHifenInterno = "(\\w)+(-(\\w)+)+";
        String regexNumero = "(\\d)+([.|,](\\d)+)*";
        String regexEspacoBranco = "\\s";
        String regexCaracteresEspeciais = "(?i)[^0-9a-z\341\351\355\363\372\340\350\354\362\371\342\352\356\364\373\343\365\347]";
        addPadroes(new Padrao(regexEmail, true, false, TipoToken.EMAIL.ordinal()));
        addPadroes(new Padrao(regexHifenInterno, true, false, TipoToken.HIFEN_INTERNO.ordinal()));
        addPadroes(new Padrao(regexNumero, true, false, TipoToken.NUMERO.ordinal()));
        addPadroes(new Padrao(regexEspacoBranco, false, false, TipoToken.ESPACO_BRANCO.ordinal()));
        addPadroes(new Padrao(regexCaracteresEspeciais, true, true, TipoToken.CARACTERE_ESPECIAL.ordinal()));
        setTokenizarRestos(true);
        setTipoDefault(TipoToken.WORD.ordinal());
    }

    public static void main(String args[])
    {
        TokenizadorElementoTexto tokenizador = new TokenizadorElementoTexto();
        Token tokens[] = tokenizador.tokenizar("fernando@gmail.com - Metal\347lica, (The Beatles, Iron Maidem)");
        Token atoken[];
        int j = (atoken = tokens).length;
        for(int i = 0; i < j; i++)
        {
            Token token = atoken[i];
            TipoToken tipoTokenValues[] = TipoToken.values();
            System.out.println((new StringBuilder()).append(tipoTokenValues[token.getTipo()]).append(" : ").append(token.getValor()).toString());
        }

    }
}
