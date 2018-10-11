// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IdentTipoTokenItem.java

package wrapper.comum;


// Referenced classes of package wrapper.comum:
//            IdentItem

public class IdentTipoTokenItem
    implements IdentItem
{

    public IdentTipoTokenItem(Integer tipo)
    {
        this.tipo = tipo;
    }

    public Integer getTipo()
    {
        return tipo;
    }

    private Integer tipo;
}
