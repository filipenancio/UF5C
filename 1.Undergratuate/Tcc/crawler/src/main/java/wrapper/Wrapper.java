// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Wrapper.java

package wrapper;

import br.ufsc.inf.tcc.comum.PageDto;
import crawler.Settings;
import wrapper.parser.Parser;

// Referenced classes of package wrapper:
//            WrapperConfig

public class Wrapper
{

    public Wrapper(Settings settings)
    {
        WrapperConfig.defineSettings(settings);
        this.settings = settings;
    }

    public Settings getSettings()
    {
        return settings;
    }

    public void wrap(PageDto page)
    {
        Parser parser = new Parser();
        parser.parse(page);
    }

    private Settings settings;
}
