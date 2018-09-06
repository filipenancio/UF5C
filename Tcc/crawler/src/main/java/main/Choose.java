// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:53
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Choose.java

package main;


// Referenced classes of package main:
//            MainChoose

public class Choose
{

    public Choose()
    {
    }

    public static void main(String args[])
    {
        if(args.length != 1)
        {
            throw new RuntimeException("\n  choose necessita apenas de 1 par\342metro");
        } else
        {
            int numberOfLinksToVisit = Integer.parseInt(args[1]);
            (new MainChoose()).doit(numberOfLinksToVisit);
            return;
        }
    }
}