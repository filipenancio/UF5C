// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:52
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   CrawlerConfig.java

package crawler;

import br.ufsc.inf.tcc.comum.UrlDto;

public class CrawlerConfig
{

    public CrawlerConfig()
    {
    }

    public static final int quantidadeDeWrappers = 4;
    public static final int quantidadeDeDownloadsSimultaneos = 8;
    public static final int milissegundosSemAcessoAoMesmoAuth = 10000;
    public static final int tamanhoBufferSeed = 100;
    public static final int tamanhoBufferDoc = 20;
    public static final UrlDto seeds[] = {
        new UrlDto("http://www.pmf.sc.gov.br", "http://www.pmf.sc.gov.br"), new UrlDto("http://www.brasil.gov.br", "http://www.brasil.gov.br"), new UrlDto("http://www.portalinformacao.net", "http://www.portalinformacao.net"), new UrlDto("http://g1.globo.com", "http://g1.globo.com/index.html"), new UrlDto("http://noticias.uol.com.br", "http://noticias.uol.com.br"), new UrlDto("http://www.jn.pt", "http://www.jn.pt/paginainicial"), new UrlDto("http://www.folha.uol.com.br", "http://www.folha.uol.com.br"), new UrlDto("http://noticias.terra.com.br", "http://noticias.terra.com.br"), new UrlDto("https://br.noticias.yahoo.com", "https://br.noticias.yahoo.com"), new UrlDto("http://www.dn.pt", "http://www.dn.pt/inicio/default.aspx"), 
        new UrlDto("http://www.msn.com", "http://www.msn.com/pt-br/noticias")
    };

}