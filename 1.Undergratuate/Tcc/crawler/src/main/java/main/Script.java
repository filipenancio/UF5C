// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:54
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Script.java

package main;


// Referenced classes of package main:
//            Rebuild, MainCrawler, MainChoose, MainWrapper

public class Script
{

    public Script()
    {
    }

    public static void main(String args[])
    {
        Rebuild.doit();
        (new MainCrawler()).coletar(200);
        (new MainChoose()).doit(400);
        (new MainWrapper()).doit();
    }
}