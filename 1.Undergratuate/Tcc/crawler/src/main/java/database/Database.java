// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:53
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Database.java

package database;

import java.util.LinkedList;
import java.util.List;

// Referenced classes of package database:
//            Insert, Select

public class Database
{

    public Database()
    {
    }

    public static synchronized void insert(String sql)
    {
        LinkedList sqls = new LinkedList();
        sqls.add(sql);
        Insert.doit(sqls);
    }

    public static synchronized void insert(List sqls)
    {
        Insert.doit(sqls);
    }

    public static synchronized List selectListOf(String sql)
    {
        return Select.selectListOf(sql);
    }

    public static synchronized String[][] getMatrizOf(String sql)
    {
        return Select.getMatrizOf(sql);
    }

    public static synchronized String getValor(String sql)
    {
        return Select.getValor(sql);
    }
}