// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:54
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MainCrawler.java

package main;

import crawler.*;
import database.*;
import java.io.PrintStream;
import java.util.*;

import org.jsoup.nodes.Document;

import br.ufsc.inf.tcc.comum.PageDto;
import br.ufsc.inf.tcc.comum.UrlDto;
import br.ufsc.inf.tcc.jsoap.DocumentCreator;

public class MainCrawler
{

    public MainCrawler()
    {
        urlsVisited = new UrlsRecentementeVisitadas();
        c = new UrlColector();
        runnableRemoveVisitados = new RunnableRemoveVisitados(urlsVisited);
        (new Thread(runnableRemoveVisitados)).start();
    }

    public static void main(String args[])
    {
        MainCrawler crawler = new MainCrawler();
        crawler.coletar(5);
    }

    public void coletar(int numberOfLinksToColect)
    {
_L5:
        Iterator iterator;
        List pageDtos = getPageDtos();
        iterator = pageDtos.iterator();
          goto _L1
_L3:
        PageDto pageDto;
        Document doc;
        pageDto = (PageDto)iterator.next();
        numberOfLinksToColect--;
        System.out.println((new StringBuilder(String.valueOf(numberOfLinksToColect + 1))).append(" : ").append(pageDto.getUrlDto().getUrl()).toString());
        doc = DocumentCreator.create(pageDto.getUrlDto().getUrl());
        try
        {
            pageDto.setDoc(doc);
            c.coletar(pageDto);
            EntityUrl.inserirUrlColetadas(pageDto);
            UpdateVisitStatus.doit(pageDto.getUrlDto().getId(), VisitStatus.LINKS_COLETADOS);
            break MISSING_BLOCK_LABEL_161;
        }
        catch(Throwable t)
        {
            UpdateVisitStatus.doit(pageDto.getUrlDto().getId(), VisitStatus.PROBLEMA);
        }
        urlsVisited.urlVisitada(pageDto.getUrlDto());
        break MISSING_BLOCK_LABEL_172;
        Exception exception;
        exception;
        urlsVisited.urlVisitada(pageDto.getUrlDto());
        throw exception;
        urlsVisited.urlVisitada(pageDto.getUrlDto());
        if(numberOfLinksToColect <= 0)
            break; /* Loop/switch isn't completed */
_L1:
        if(iterator.hasNext()) goto _L3; else goto _L2
_L2:
        if(numberOfLinksToColect > 0) goto _L5; else goto _L4
_L4:
        runnableRemoveVisitados.stop();
        return;
    }

    protected List getPageDtos()
    {
        LinkedList urlDtos = new LinkedList();
        String sql = (new StringBuilder("SELECT id, id_auth, descricao FROM tb_url WHERE id_visit_status = ")).append(VisitStatus.NOVA_URL.getId()).append(" ORDER BY random() LIMIT 200").toString();
        String matrix[][] = Database.getMatrizOf(sql);
        String as[][];
        int j = (as = matrix).length;
        for(int i = 0; i < j; i++)
        {
            String reg[] = as[i];
            long id = Long.parseLong(reg[0]);
            long idAuth = Long.parseLong(reg[1]);
            String url = reg[2];
            UrlDto urlDto = new UrlDto(url, id, idAuth);
            urlDtos.addLast(urlDto);
        }

        List urlDtosFiltrados = urlsVisited.filterUrls(urlDtos);
        System.out.println((new StringBuilder("urlDtosFiltrados.size(): ")).append(urlDtosFiltrados.size()).toString());
        List pageDtos = new LinkedList();
        UrlDto urlDto;
        for(Iterator iterator = urlDtosFiltrados.iterator(); iterator.hasNext(); pageDtos.add(new PageDto(urlDto)))
            urlDto = (UrlDto)iterator.next();

        return pageDtos;
    }

    private UrlsRecentementeVisitadas urlsVisited;
    private RunnableRemoveVisitados runnableRemoveVisitados;
    private UrlColector c;
}