// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IdentSepItem.java

package wrapper.comum;


// Referenced classes of package wrapper.comum:
//            IdentItem, Token

public class IdentSepItem
    implements IdentItem
{

    public IdentSepItem(Token token)
    {
        this.token = token;
    }

    public Token getToken()
    {
        return token;
    }

    private Token token;
}
