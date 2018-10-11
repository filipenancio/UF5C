// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:54
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MainShowResult.java

package main;

import br.ufsc.inf.tcc.comum.UrlDto;
import br.ufsc.inf.tcc.showresult.ShowResult;
import database.Database;

public class MainShowResult
{

    public MainShowResult()
    {
    }

    public static void main(String args[])
    {
        doit(200);
    }

    public static void doit(int qtd)
    {
        UrlDto urlDtos[] = getUltimasUrlsVisitadas(qtd);
        UrlDto aurldto[];
        int j = (aurldto = urlDtos).length;
        for(int i = 0; i < j; i++)
        {
            UrlDto urlDto = aurldto[i];
            ShowResult.putInResults(urlDto);
        }

    }

    public static UrlDto[] getUltimasUrlsVisitadas(int qtd)
    {
        String sql = (new StringBuilder("select * from tb_url where id IN (select id_url from tb_visit group by id_url order by max(id) DESC LIMIT ")).append(qtd).append(")").toString();
        String matrix[][] = Database.getMatrizOf(sql);
        UrlDto urlDtos[] = new UrlDto[matrix.length];
        for(int i = 0; i < matrix.length; i++)
        {
            String desc = matrix[i][2];
            long id = Long.parseLong(matrix[i][0]);
            long id_auth = Long.parseLong(matrix[i][1]);
            UrlDto urlDto = new UrlDto(desc, id, id_auth);
            urlDtos[i] = urlDto;
        }

        return urlDtos;
    }
}