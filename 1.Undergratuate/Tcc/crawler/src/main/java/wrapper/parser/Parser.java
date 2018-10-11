// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Parser.java

package wrapper.parser;

import java.util.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

import br.ufsc.inf.tcc.comum.PageDto;
import wrapper.agrup.Agrupador;
import wrapper.comum.ElementoTexto;
import wrapper.comum.Registro;

// Referenced classes of package wrapper.parser:
//            PathCtrl, TokenizadorElementoTexto

public class Parser
{

    public Parser()
    {
    }

    public void parse(PageDto page)
    {
        Document doc = page.getDoc();
        pathCtrl = new PathCtrl();
        tokenizadorET = new TokenizadorElementoTexto();
        this.page = page;
        agrupador = new Agrupador(page);
        Element body = doc.getElementsByTag("body").first();
        parse(((Node) (body)));
    }

    private Registro parse(Node node)
    {
        String tag = ((Element)node.parent()).tagName();
        pathCtrl.in(tag);
        Registro registro = null;
        if(node instanceof Element)
        {
            List childsNode = node.childNodes();
            LinkedList registrosChilds = new LinkedList();
            for(Iterator iterator = childsNode.iterator(); iterator.hasNext();)
            {
                Node child = (Node)iterator.next();
                Registro registroChild = parse(child);
                if(registroChild != null)
                    registrosChilds.addLast(registroChild);
            }

            if(registrosChilds.size() != 0)
                registro = agrupador.agrupar(registrosChilds);
        } else
        if(node instanceof TextNode)
        {
            String texto = ((TextNode)node).text().trim();
            if(texto != null && texto.length() > 0)
            {
                wrapper.comum.Path path = pathCtrl.getPath();
                ElementoTexto novoET = new ElementoTexto(texto, path, tokenizadorET);
                registro = new Registro(novoET);
            }
        }
        pathCtrl.out();
        return registro;
    }

    private PathCtrl pathCtrl;
    private TokenizadorElementoTexto tokenizadorET;
    private PageDto page;
    private Agrupador agrupador;
}
