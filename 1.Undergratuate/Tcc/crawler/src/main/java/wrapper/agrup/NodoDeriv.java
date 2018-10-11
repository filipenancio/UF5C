// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NodoDeriv.java

package wrapper.agrup;

import java.util.Iterator;
import java.util.List;
import wrapper.comum.Token;

// Referenced classes of package wrapper.agrup:
//            Nodo

public class NodoDeriv extends Nodo
{

    public NodoDeriv(Nodo n1, Nodo n2)
    {
        super(n1.getIndexInicial(), n2.getIndexFinal());
        List tokens = n1.getTokensEmComum();
        for(Iterator iterator = tokens.iterator(); iterator.hasNext();)
        {
            Token token = (Token)iterator.next();
            if(n2.contemToken(token))
                addTokenEmComum(token);
        }

        List tiposDeToken = n1.getTiposDeTokenEmComum();
        for(Iterator iterator1 = tiposDeToken.iterator(); iterator1.hasNext();)
        {
            Integer tipo = (Integer)iterator1.next();
            if(n2.contemTipoDeToken(tipo))
                addTipoDeTokenEmComum(tipo);
        }

    }
}
