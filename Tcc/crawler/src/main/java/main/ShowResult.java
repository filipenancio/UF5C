// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:54
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   ShowResult.java

package main;


// Referenced classes of package main:
//            MainShowResult

public class ShowResult
{

    public ShowResult()
    {
    }

    public static void main(String args[])
    {
        if(args.length != 1)
        {
            throw new RuntimeException("\n  crawler necessita apenas de 1 par\342metro");
        } else
        {
            int numberOfLastVisit = Integer.parseInt(args[1]);
            MainShowResult.doit(numberOfLastVisit);
            return;
        }
    }
}