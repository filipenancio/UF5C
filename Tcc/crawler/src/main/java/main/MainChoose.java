// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:54
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MainChoose.java

package main;

import crawler.VisitStatus;
import database.Database;

public class MainChoose
{

    public static void main(String args[])
    {
        (new MainChoose()).doit(5);
    }

    public MainChoose()
    {
    }

    public void doit(int num)
    {
        updateToVisit(num);
    }

    private void updateToVisit(int num)
    {
        int id_coletar = VisitStatus.COLETAR_LISTAS.getId();
        int id_nova = VisitStatus.NOVA_URL.getId();
        String sql = (new StringBuilder("UPDATE tb_url SET id_wrapper_status = ")).append(id_coletar).append(" WHERE id IN(SELECT id FROM tb_url WHERE id_wrapper_status = ").append(id_nova).append(" ORDER BY random() LIMIT ").append(num).append(")").toString();
        Database.insert(sql);
    }
}