// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Path.java

package wrapper.comum;

import java.util.LinkedList;

public class Path
{

    public Path(LinkedList pathDesc, int id)
    {
        this.pathDesc = pathDesc;
        this.id = id;
    }

    public String[] getPathDesc()
    {
        return (String[])pathDesc.toArray(new String[pathDesc.size()]);
    }

    public int getId()
    {
        return id;
    }

    private LinkedList pathDesc;
    private int id;
}
