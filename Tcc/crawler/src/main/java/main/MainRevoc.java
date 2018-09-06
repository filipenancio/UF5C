// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:54
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MainRevoc.java

package main;

import comum.*;
import crawler.Settings;
import java.io.*;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;

import br.ufsc.inf.tcc.comum.ListDto;
import br.ufsc.inf.tcc.comum.PageDto;
import br.ufsc.inf.tcc.comum.UrlDto;
import br.ufsc.inf.tcc.comum.VisitDto;
import br.ufsc.inf.tcc.jsoap.DocumentCreator;
import br.ufsc.inf.tcc.showresult.ShowResult;
import wrapper.Wrapper;

public class MainRevoc
{

    public MainRevoc()
    {
    }

    public static void main(String args[])
    {
        wrap();
    }

    public static void wrap()
    {
        Settings settingsValues[] = Settings.values();
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
                VisitDto visitDtos[] = new VisitDto[settingsValues.length];
                for(int i = 0; i < settingsValues.length; i++)
                {
                    Wrapper w = new Wrapper(settingsValues[i]);
                    PageDto pageToWrap = new PageDto(pageDto.getUrlDto());
                    pageToWrap.setDoc(doc);
                    w.wrap(pageToWrap);
                    VisitDto visitDto = new VisitDto(i + 1, w.getSettings());
                    ListDto listDto;
                    for(Iterator iterator1 = pageToWrap.getListas().iterator(); iterator1.hasNext(); visitDto.addLista(listDto))
                        listDto = (ListDto)iterator1.next();

                    visitDtos[i] = visitDto;
                }

                ShowResult.putInRevocResults(pageDto.getUrlDto(), visitDtos);
            }
            catch(Throwable t)
            {
                t.printStackTrace();
            }
        }

    }

    public static List getPageDtos()
    {
        LinkedList urlDtos = new LinkedList();
        File file = new File("revoc_links.txt");
        List lista = null;
        try
        {
            lista = FileUtils.readLines(file);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        int id = 0;
        UrlDto urlDto;
        for(Iterator iterator = lista.iterator(); iterator.hasNext(); urlDtos.add(urlDto))
        {
            String link = (String)iterator.next();
            urlDto = new UrlDto(link, ++id, 1L);
        }

        List pageDtos = new LinkedList();
        UrlDto urlDto;
        for(Iterator iterator1 = urlDtos.iterator(); iterator1.hasNext(); pageDtos.add(new PageDto(urlDto)))
            urlDto = (UrlDto)iterator1.next();

        return pageDtos;
    }
}