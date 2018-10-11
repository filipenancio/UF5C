// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:52
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RunnableRemoveVisitados.java

package crawler;


// Referenced classes of package crawler:
//            StopRunnable, UrlsRecentementeVisitadas

public class RunnableRemoveVisitados
    implements Runnable, StopRunnable
{

    public RunnableRemoveVisitados(UrlsRecentementeVisitadas urlsRecentementeVisitadas)
    {
        run = true;
        this.urlsRecentementeVisitadas = urlsRecentementeVisitadas;
    }

    public void run()
    {
        long gapRemove = 10000L;
        long gapWait = gapRemove / 2L;
        while(run) 
            try
            {
                long timeMillisAtual = System.currentTimeMillis();
                Thread.sleep(gapWait);
                urlsRecentementeVisitadas.removeVisited(timeMillisAtual - gapRemove);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
    }

    public void stop()
    {
        run = false;
    }

    private UrlsRecentementeVisitadas urlsRecentementeVisitadas;
    private boolean run;
}