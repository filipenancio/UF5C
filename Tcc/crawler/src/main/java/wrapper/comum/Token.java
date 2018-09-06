// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Token.java

package wrapper.comum;


public class Token
    implements Comparable
{

    public Token(String valor, int id)
    {
        this.id = id;
        this.valor = valor;
        validoComoSeparador = false;
        tipo = -1;
    }

    public int getId()
    {
        return id;
    }

    public boolean isValidoComoSeparador()
    {
        return validoComoSeparador;
    }

    public void setTipo(int tipo)
    {
        this.tipo = tipo;
    }

    public int getTipo()
    {
        return tipo;
    }

    public void setValidoComoSeparador(boolean validoComoSeparador)
    {
        this.validoComoSeparador = validoComoSeparador;
    }

    public String getValor()
    {
        return valor;
    }

    public int compareTo(Token t2)
    {
        if(id > t2.id)
            return 1;
        return id >= t2.id ? 0 : -1;
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((Token)obj);
    }

    private String valor;
    private int id;
    private int tipo;
    private boolean validoComoSeparador;
}
