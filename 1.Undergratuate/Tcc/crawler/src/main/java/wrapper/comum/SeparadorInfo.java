// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SeparadorInfo.java

package wrapper.comum;


public class SeparadorInfo
{

    public SeparadorInfo()
    {
    }

    public boolean isTextoAntes()
    {
        return textoAntes;
    }

    public void setTextoAntes(boolean textoAntes)
    {
        this.textoAntes = textoAntes;
    }

    public boolean isTextoDepois()
    {
        return textoDepois;
    }

    public void setTextoDepois(boolean textoDepois)
    {
        this.textoDepois = textoDepois;
    }

    private boolean textoAntes;
    private boolean textoDepois;
}
