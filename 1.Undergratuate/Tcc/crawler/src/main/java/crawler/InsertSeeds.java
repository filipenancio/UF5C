// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:52
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   InsertSeeds.java

package crawler;

import br.ufsc.inf.tcc.comum.PageDto;
import database.EntityUrl;

// Referenced classes of package crawler:
//            CrawlerConfig

public class InsertSeeds
{

    public InsertSeeds()
    {
    }

    public static void doit()
    {
        PageDto pageDto = new PageDto(null);
        br.ufsc.inf.tcc.comum.UrlDto aurldto[];
        int j = (aurldto = CrawlerConfig.seeds).length;
        for(int i = 0; i < j; i++)
        {
            br.ufsc.inf.tcc.comum.UrlDto urlDto = aurldto[i];
            pageDto.addUrlColetada(urlDto);
        }

        EntityUrl.inserirUrlColetadas(pageDto);
    }

    public static void main(String args[])
    {
        doit();
    }
}