// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PathCtrl.java

package wrapper.parser;

import java.util.*;
import wrapper.comum.Path;

public class PathCtrl
{

    public PathCtrl()
    {
        hashPath = new Hashtable();
        pathAtual = new LinkedList();
        idCtrl = 0;
    }

    public void in(String tag)
    {
        pathAtual.addLast(tag);
    }

    public void out()
    {
        pathAtual.removeLast();
    }

    public Path getPath()
    {
        LinkedList listPath = new LinkedList();
        String pathDescAtual = "";
        String tag;
        for(Iterator iterator = pathAtual.iterator(); iterator.hasNext(); listPath.addLast(tag))
        {
            tag = (String)iterator.next();
            pathDescAtual = (new StringBuilder(String.valueOf(pathDescAtual))).append(".").append(tag).toString();
        }

        if(!hashPath.containsKey(pathDescAtual))
        {
            Path path = new Path(listPath, ++idCtrl);
            hashPath.put(pathDescAtual, path);
        }
        return (Path)hashPath.get(pathDescAtual);
    }

    private Hashtable hashPath;
    private LinkedList pathAtual;
    private int idCtrl;
}
