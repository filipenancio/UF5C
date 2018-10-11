// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:54
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MainWrapper.java

package main;

import crawler.Settings;
import crawler.VisitStatus;
import database.*;
import java.io.PrintStream;
import java.util.*;

import org.jsoup.nodes.Document;

import br.ufsc.inf.tcc.comum.PageDto;
import br.ufsc.inf.tcc.comum.UrlDto;
import br.ufsc.inf.tcc.database.EntityLista;
import br.ufsc.inf.tcc.jsoap.DocumentCreator;
import wrapper.Wrapper;

public class MainWrapper
{

    public static void main(String args[])
    {
        (new MainWrapper()).doit();
    }

    public MainWrapper()
    {
        settingsValues = Settings.values();
    }

    public void doit()
    {
        List pageDtos = getPageDtos();
        int numVisitas = 0;
        int numVisitasMax = pageDtos.size();
        for(Iterator iterator = pageDtos.iterator(); iterator.hasNext();)
        {
            PageDto pageDto = (PageDto)iterator.next();
            numVisitas++;
            System.out.println((new StringBuilder(String.valueOf(numVisitas))).append("/").append(numVisitasMax).append(" : ").append(pageDto.getUrlDto().getUrl()).toString());
            Document doc = DocumentCreator.create(pageDto.getUrlDto().getUrl());
            try
            {
                Settings asettings[];
                int j = (asettings = settingsValues).length;
                for(int i = 0; i < j; i++)
                {
                    Settings settings = asettings[i];
                    Wrapper w = new Wrapper(settings);
                    PageDto pageToWrap = new PageDto(pageDto.getUrlDto());
                    pageToWrap.setDoc(doc);
                    w.wrap(pageToWrap);
                    EntityLista.inserirListasColetadas(pageToWrap, w.getSettings());
                }

                UpdateWrapperStatus.doit(pageDto.getUrlDto().getId(), VisitStatus.LISTAS_COLETADAS);
            }
            catch(Throwable t)
            {
                System.out.println("Falha no Wrapper.");
                UpdateWrapperStatus.doit(pageDto.getUrlDto().getId(), VisitStatus.PROBLEMA);
            }
        }

    }

    protected List getPageDtos()
    {
        LinkedList urlDtos = new LinkedList();
        String sql = (new StringBuilder("SELECT id, id_auth, descricao FROM tb_url WHERE id_wrapper_status = ")).append(VisitStatus.COLETAR_LISTAS.getId()).toString();
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

        List pageDtos = new LinkedList();
        UrlDto urlDto;
        for(Iterator iterator = urlDtos.iterator(); iterator.hasNext(); pageDtos.add(new PageDto(urlDto)))
            urlDto = (UrlDto)iterator.next();

        return pageDtos;
    }

    private Settings settingsValues[];
}