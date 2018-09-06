// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NodoRegistro.java

package wrapper.agrup;

import java.util.Iterator;
import java.util.List;
import wrapper.comum.Registro;
import wrapper.comum.Token;

// Referenced classes of package wrapper.agrup:
//            Nodo

public class NodoRegistro extends Nodo
{

    public NodoRegistro(Registro r, int index)
    {
        super(index, index);
        List tokens = r.getCaracteresEspeciais();
        Token token;
        for(Iterator iterator = tokens.iterator(); iterator.hasNext(); addTokenEmComum(token))
            token = (Token)iterator.next();

        List tiposDeToken = r.getTiposDeTokenIdent();
        Integer tipo;
        for(Iterator iterator1 = tiposDeToken.iterator(); iterator1.hasNext(); addTipoDeTokenEmComum(tipo))
            tipo = (Integer)iterator1.next();

    }
}
