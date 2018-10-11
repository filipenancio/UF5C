// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AgruparNodos.java

package wrapper.agrup;

import java.util.*;
import wrapper.comum.Identidade;
import wrapper.comum.Token;

// Referenced classes of package wrapper.agrup:
//            Nodo, Grupo

public class AgruparNodos
{

    public AgruparNodos()
    {
        grupos = new LinkedList();
        grupoAtual = null;
    }

    public List getGrupos()
    {
        return grupos;
    }

    public void add(Nodo nodo)
    {
        List tokensEmComum = nodo.getTokensEmComum();
        List tiposDeTokenEmComum = nodo.getTiposDeTokenEmComum();
        int qtdTokensEmComum = tokensEmComum.size();
        int qtdTipoDeTokensEmComum = tiposDeTokenEmComum.size();
        if(qtdTokensEmComum == 0 && qtdTipoDeTokensEmComum == 0)
        {
            grupoAtual = null;
        } else
        {
            Identidade identidade = getRegistroIdentidadeDoNodo(nodo);
            if(grupoAtual == null)
            {
                grupoAtual = new Grupo(nodo.getIndexInicial(), nodo.getIndexFinal());
                grupos.add(grupoAtual);
                grupoAtual.setIdentidade(identidade);
            } else
            {
                grupoAtual.setIdentidade(identidade);
                grupoAtual.indexFinal = nodo.getIndexFinal();
            }
        }
    }

    private Identidade getRegistroIdentidadeDoNodo(Nodo nodo)
    {
        List tokensEmComum = nodo.getTokensEmComum();
        List tiposDeTokenEmComum = nodo.getTiposDeTokenEmComum();
        Identidade identidade = new Identidade();
        Token token;
        for(Iterator iterator = tokensEmComum.iterator(); iterator.hasNext(); identidade.addToken(token))
            token = (Token)iterator.next();

        Integer tipo;
        for(Iterator iterator1 = tiposDeTokenEmComum.iterator(); iterator1.hasNext(); identidade.addTipoDeToken(tipo))
            tipo = (Integer)iterator1.next();

        return identidade;
    }

    private LinkedList grupos;
    private Grupo grupoAtual;
}
