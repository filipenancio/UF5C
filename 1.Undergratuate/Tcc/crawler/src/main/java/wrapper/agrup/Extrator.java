// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Extrator.java

package wrapper.agrup;

import java.util.*;
import wrapper.comum.Registro;

// Referenced classes of package wrapper.agrup:
//            NodoRegistro, Grupo, GrupoDeRegistrosSemelhantes, Nodo, 
//            NodoDeriv, AgruparNodos

public class Extrator
{

    public Extrator(int numeroDeDerivacoes, int numeroMinimoDeRegistrosAdjacentes)
    {
        this.numeroDeDerivacoes = numeroDeDerivacoes;
        this.numeroMinimoDeRegistrosAdjacentes = numeroMinimoDeRegistrosAdjacentes;
    }

    public List extrair(List registros)
    {
        LinkedList gruposDeRegistrosSemelhantes = new LinkedList();
        if(registros.size() < numeroDeDerivacoes + 1)
            return gruposDeRegistrosSemelhantes;
        ArrayList nodosRegistros = new ArrayList();
        Iterator itRegistros = registros.iterator();
        for(int i = 0; i < registros.size(); i++)
            nodosRegistros.add(new NodoRegistro((Registro)itRegistros.next(), i));

        for(int i = 0; i < numeroDeDerivacoes; i++)
            nodosRegistros = derivar(nodosRegistros);

        List grupos = agruparNodosAdjacentesSemelhantes(nodosRegistros);
        for(Iterator iterator = grupos.iterator(); iterator.hasNext();)
        {
            Grupo grupo = (Grupo)iterator.next();
            int tam = (grupo.indexFinal - grupo.indexInicial) + 1;
            if(tam >= numeroMinimoDeRegistrosAdjacentes)
            {
                List registrosDoGrupo = registros.subList(grupo.indexInicial, grupo.indexFinal + 1);
                GrupoDeRegistrosSemelhantes grupoDeRegistrosSemelhantes = new GrupoDeRegistrosSemelhantes(registrosDoGrupo);
                wrapper.comum.Identidade identidades[] = grupo.getIdentidades();
                wrapper.comum.Identidade aidentidade[];
                int k = (aidentidade = identidades).length;
                for(int j = 0; j < k; j++)
                {
                    wrapper.comum.Identidade identidade = aidentidade[j];
                    grupoDeRegistrosSemelhantes.addIdentidade(identidade);
                }

                gruposDeRegistrosSemelhantes.add(grupoDeRegistrosSemelhantes);
            }
        }

        return gruposDeRegistrosSemelhantes;
    }

    private ArrayList derivar(List lista)
    {
        if(lista.size() < 2)
            throw new RuntimeException("Lista para derivar deve conter no m\355nimo 2 elementos.");
        ArrayList listaDerivada = new ArrayList();
        Iterator it = lista.iterator();
        Nodo nodo2;
        for(Nodo nodo1 = (Nodo)it.next(); it.hasNext(); nodo1 = nodo2)
        {
            nodo2 = (Nodo)it.next();
            listaDerivada.add(new NodoDeriv(nodo1, nodo2));
        }

        return listaDerivada;
    }

    private List agruparNodosAdjacentesSemelhantes(List nodos)
    {
        AgruparNodos agrupNodos = new AgruparNodos();
        for(Iterator it = nodos.iterator(); it.hasNext(); agrupNodos.add((Nodo)it.next()));
        return agrupNodos.getGrupos();
    }

    private int numeroDeDerivacoes;
    private int numeroMinimoDeRegistrosAdjacentes;
}
