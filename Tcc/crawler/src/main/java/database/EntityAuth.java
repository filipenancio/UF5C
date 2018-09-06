// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:53
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   EntityAuth.java

package database;

import java.util.*;

import br.ufsc.inf.tcc.comum.PageDto;
import br.ufsc.inf.tcc.comum.UrlDto;

// Referenced classes of package database:
//            SequenceCtrl, Database

public class EntityAuth
{

    public EntityAuth()
    {
    }

    public static synchronized HashMap insert(PageDto pageDto)
    {
        SequenceCtrl seqCtrl = SequenceCtrl.getInstance();
        UrlDto urlDtos[] = pageDto.getUrlColetadas();
        HashMap hashUrlDtos = new HashMap();
        UrlDto aurldto[];
        int j = (aurldto = urlDtos).length;
        for(int i = 0; i < j; i++)
        {
            UrlDto urlDto = aurldto[i];
            if(!hashUrlDtos.containsKey(urlDto.getAuth()))
                hashUrlDtos.put(urlDto.getAuth(), Long.valueOf(0L));
        }

        String sql = "SELECT id, descricao FROM tb_auth WHERE descricao IN ( ? )";
        String x = "";
        for(Iterator iterator = hashUrlDtos.keySet().iterator(); iterator.hasNext();)
        {
            String auth = (String)iterator.next();
            x = (new StringBuilder(String.valueOf(x))).append(",'").append(auth).append("'").toString();
        }

        x = x.replaceFirst(",", "");
        sql = sql.replace("?", x);
        String result[][] = Database.getMatrizOf(sql);
        String as[][];
        int l = (as = result).length;
        for(int k = 0; k < l; k++)
        {
            String reg[] = as[k];
            Long idAuth = Long.valueOf(Long.parseLong(reg[0]));
            hashUrlDtos.put(reg[1], idAuth);
        }

        LinkedList sqls = new LinkedList();
        for(Iterator iterator1 = hashUrlDtos.keySet().iterator(); iterator1.hasNext();)
        {
            String auth = (String)iterator1.next();
            long idAuth = ((Long)hashUrlDtos.get(auth)).longValue();
            if(idAuth == 0L)
            {
                long novoIdAuth = seqCtrl.getNextIdAuth();
                String sqlInsert = (new StringBuilder("INSERT INTO tb_auth VALUES ( ")).append(novoIdAuth).append(" , '").append(auth).append("' )").toString();
                sqls.addLast(sqlInsert);
                hashUrlDtos.put(auth, Long.valueOf(novoIdAuth));
            }
        }

        Database.insert(sqls);
        return hashUrlDtos;
    }
}