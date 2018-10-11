// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Grupo.java

package wrapper.agrup;

import java.util.Collection;
import java.util.Hashtable;
import wrapper.comum.Identidade;

public class Grupo
{

    public Grupo(int indexInicial, int indexFinal)
    {
        this.indexInicial = indexInicial;
        this.indexFinal = indexFinal;
        identidades = new Hashtable();
    }

    public void setIdentidade(Identidade identidade)
    {
        String key = identidade.getKey();
        if(!identidades.containsKey(key))
            identidades.put(key, identidade);
    }

    public Identidade[] getIdentidades()
    {
        return (Identidade[])identidades.values().toArray(new Identidade[identidades.size()]);
    }

    int indexInicial;
    int indexFinal;
    private Hashtable identidades;
}
