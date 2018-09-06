// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:53
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   EntityUrl.java

package database;

import crawler.*;
import java.util.*;

import br.ufsc.inf.tcc.comum.PageDto;
import br.ufsc.inf.tcc.comum.UrlDto;
import br.ufsc.inf.tcc.jsoap.DocumentCreator;

// Referenced classes of package database:
//            SequenceCtrl, EntityAuth, Database

public class EntityUrl
{

    public EntityUrl()
    {
    }

    public static synchronized void inserirUrlColetadas(PageDto pageDto)
    {
        SequenceCtrl seqCtrl = SequenceCtrl.getInstance();
        HashMap hashAuth = EntityAuth.insert(pageDto);
        LinkedList sqls = new LinkedList();
        UrlDto urlDtos[] = pageDto.getUrlColetadas();
        HashSet hashUrlsJaInseridas = getUrlsJaInseridas(urlDtos);
        UrlDto aurldto[];
        int j = (aurldto = urlDtos).length;
        for(int i = 0; i < j; i++)
        {
            UrlDto urlDto = aurldto[i];
            if(!hashUrlsJaInseridas.contains(urlDto.getUrl()))
            {
                long id = seqCtrl.getNextIdUrl();
                long idAuth = ((Long)hashAuth.get(urlDto.getAuth())).longValue();
                String descricao = urlDto.getUrl();
                descricao = descricao.replaceAll("'", "''");
                int idUrlStatus = VisitStatus.NOVA_URL.getId();
                int idWrapperStatus = VisitStatus.NOVA_URL.getId();
                String sql = (new StringBuilder("INSERT INTO tb_url VALUES (")).append(id).append(",").append(idAuth).append(",'").append(descricao).append("',").append(idUrlStatus).append(",").append(idWrapperStatus).append(")").toString();
                sqls.add(sql);
            }
        }

        Database.insert(sqls);
    }

    private static HashSet getUrlsJaInseridas(UrlDto urlDtos[])
    {
        HashSet hashUrlsJaInseridas = new HashSet();
        String sql = "SELECT descricao FROM tb_url WHERE descricao IN ( ? )";
        String x = "";
        UrlDto aurldto[];
        int j = (aurldto = urlDtos).length;
        for(int i = 0; i < j; i++)
        {
            UrlDto urlDto = aurldto[i];
            String descricao = urlDto.getUrl().replaceAll("'", "''");
            x = (new StringBuilder(String.valueOf(x))).append(",'").append(descricao).append("'").toString();
        }

        x = x.replaceFirst(",", "");
        sql = sql.replace("?", x);
        String result[][] = Database.getMatrizOf(sql);
        String as[][];
        int l = (as = result).length;
        for(int k = 0; k < l; k++)
        {
            String reg[] = as[k];
            hashUrlsJaInseridas.add(reg[0]);
        }

        return hashUrlsJaInseridas;
    }

    public static void main(String args[])
    {
        UrlColector urlColector = new UrlColector();
        UrlDto urlDto = CrawlerConfig.seeds[4];
        org.jsoup.nodes.Document doc = DocumentCreator.create(urlDto.getUrl());
        PageDto pageDto = new PageDto(urlDto);
        pageDto.setDoc(doc);
        urlColector.coletar(pageDto);
        inserirUrlColetadas(pageDto);
    }
}