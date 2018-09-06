// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:52
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   MapAuthTime.java

package crawler;


public class MapAuthTime
{

    public MapAuthTime(long auth, long timeMillis)
    {
        this.auth = auth;
        this.timeMillis = timeMillis;
    }

    public long getAuth()
    {
        return auth;
    }

    public long getTimeMillis()
    {
        return timeMillis;
    }

    private long auth;
    private long timeMillis;
}