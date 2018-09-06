// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:53
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UpdateVisitStatus.java

package database;

import crawler.VisitStatus;
import java.util.LinkedList;
import java.util.List;

// Referenced classes of package database:
//            Database

public class UpdateVisitStatus
{

    public UpdateVisitStatus()
    {
    }

    public static void doit(long id_url, VisitStatus visitlStatus)
    {
        List sqls = new LinkedList();
        sqls.add((new StringBuilder("UPDATE tb_url SET id_visit_status = ")).append(visitlStatus.getId()).append(" WHERE id = ").append(id_url).toString());
        Database.insert(sqls);
    }
}