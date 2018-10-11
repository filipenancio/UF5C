// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:52
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UrlsRecentementeVisitadas.java

package crawler;

import java.util.*;

import br.ufsc.inf.tcc.comum.UrlDto;

// Referenced classes of package crawler:
//            MapAuthTime

public class UrlsRecentementeVisitadas
{

    public UrlsRecentementeVisitadas()
    {
        hashUrlsIndicadas = new HashSet();
        hashUrlsVisitadas = new HashSet();
        listAuthTime = new LinkedList();
    }

    public synchronized List filterUrls(List urlDtos)
    {
        List urlsFiltradas = new LinkedList();
        for(Iterator iterator = urlDtos.iterator(); iterator.hasNext();)
        {
            UrlDto urlDto = (UrlDto)iterator.next();
            long idAuth = urlDto.getIdAuth();
            if(!hashUrlsIndicadas.contains(Long.valueOf(idAuth)) && !hashUrlsVisitadas.contains(Long.valueOf(idAuth)))
            {
                urlsFiltradas.add(urlDto);
                hashUrlsIndicadas.add(Long.valueOf(idAuth));
            }
        }

        return urlsFiltradas;
    }

    public synchronized void urlVisitada(UrlDto urlDto)
    {
        long timeMillisAtual = System.currentTimeMillis();
        long idAuth = urlDto.getIdAuth();
        hashUrlsIndicadas.remove(Long.valueOf(idAuth));
        hashUrlsVisitadas.add(Long.valueOf(idAuth));
        MapAuthTime map = new MapAuthTime(idAuth, timeMillisAtual);
        listAuthTime.addLast(map);
    }

    public synchronized void removeVisited(long timeMillis)
    {
        do
        {
            if(listAuthTime.size() == 0)
                break;
            MapAuthTime map = (MapAuthTime)listAuthTime.getFirst();
            if(map.getTimeMillis() >= timeMillis)
                break;
            hashUrlsVisitadas.remove(Long.valueOf(map.getAuth()));
        } while(listAuthTime.size() > 0);
    }

    private HashSet hashUrlsIndicadas;
    private HashSet hashUrlsVisitadas;
    private LinkedList listAuthTime;
}