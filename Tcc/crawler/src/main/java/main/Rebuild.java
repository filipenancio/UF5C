// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:54
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Rebuild.java

package main;

import crawler.InsertSeeds;
import database.Schema;

public class Rebuild
{

    public Rebuild()
    {
    }

    public static void main(String args[])
    {
        doit();
    }

    public static void doit()
    {
        Schema.doit();
        InsertSeeds.doit();
    }
}