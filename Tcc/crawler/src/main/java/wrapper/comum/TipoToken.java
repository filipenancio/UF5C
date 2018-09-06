// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TipoToken.java

package wrapper.comum;


public final class TipoToken extends Enum
{

    private TipoToken(String s, int i)
    {
        super(s, i);
    }

    public static TipoToken[] values()
    {
        TipoToken atipotoken[];
        int i;
        TipoToken atipotoken1[];
        System.arraycopy(atipotoken = ENUM$VALUES, 0, atipotoken1 = new TipoToken[i = atipotoken.length], 0, i);
        return atipotoken1;
    }

    public static TipoToken valueOf(String s)
    {
        return (TipoToken)Enum.valueOf(wrapper/comum/TipoToken, s);
    }

    public static final TipoToken EMAIL;
    public static final TipoToken DATA;
    public static final TipoToken CARACTERE_ESPECIAL;
    public static final TipoToken ESPACO_BRANCO;
    public static final TipoToken WORD;
    public static final TipoToken HIFEN_INTERNO;
    public static final TipoToken NUMERO;
    private static final TipoToken ENUM$VALUES[];

    static 
    {
        EMAIL = new TipoToken("EMAIL", 0);
        DATA = new TipoToken("DATA", 1);
        CARACTERE_ESPECIAL = new TipoToken("CARACTERE_ESPECIAL", 2);
        ESPACO_BRANCO = new TipoToken("ESPACO_BRANCO", 3);
        WORD = new TipoToken("WORD", 4);
        HIFEN_INTERNO = new TipoToken("HIFEN_INTERNO", 5);
        NUMERO = new TipoToken("NUMERO", 6);
        ENUM$VALUES = (new TipoToken[] {
            EMAIL, DATA, CARACTERE_ESPECIAL, ESPACO_BRANCO, WORD, HIFEN_INTERNO, NUMERO
        });
    }
}
