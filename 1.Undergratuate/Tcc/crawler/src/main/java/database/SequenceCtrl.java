// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:53
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   SequenceCtrl.java

package database;

import java.io.PrintStream;

// Referenced classes of package database:
//            Database

public class SequenceCtrl
{

    private SequenceCtrl()
    {
        String smaxidUrl = Database.getValor("SELECT max(id) FROM tb_url;");
        String smaxidAuth = Database.getValor("SELECT max(id) FROM tb_auth;");
        String smaxidLista = Database.getValor("SELECT max(id) FROM tb_lista;");
        String smaxidVisita = Database.getValor("SELECT max(id) FROM tb_visit;");
        id_url = smaxidUrl != null ? Long.parseLong(smaxidUrl) : 0L;
        id_auth = smaxidAuth != null ? Long.parseLong(smaxidAuth) : 0L;
        id_lista = smaxidLista != null ? Long.parseLong(smaxidLista) : 0L;
        id_visita = smaxidVisita != null ? Long.parseLong(smaxidVisita) : 0L;
    }

    public static SequenceCtrl getInstance()
    {
        if(sequenceCtrl == null)
            sequenceCtrl = new SequenceCtrl();
        return sequenceCtrl;
    }

    public synchronized long getNextIdUrl()
    {
        return ++id_url;
    }

    public synchronized long getNextIdAuth()
    {
        return ++id_auth;
    }

    public synchronized long getNextIdLista()
    {
        return ++id_lista;
    }

    public synchronized long getNextIdVisita()
    {
        return ++id_visita;
    }

    public static void main(String args[])
    {
        SequenceCtrl seqCtrl = getInstance();
        System.out.println(seqCtrl.getNextIdUrl());
    }

    private long id_url;
    private long id_auth;
    private long id_lista;
    private long id_visita;
    private static final String sqlMaxIdUrl = "SELECT max(id) FROM tb_url;";
    private static final String sqlMaxIdAuth = "SELECT max(id) FROM tb_auth;";
    private static final String sqlMaxIdLista = "SELECT max(id) FROM tb_lista;";
    private static final String sqlMaxIdVisita = "SELECT max(id) FROM tb_visit;";
    private static SequenceCtrl sequenceCtrl;
}