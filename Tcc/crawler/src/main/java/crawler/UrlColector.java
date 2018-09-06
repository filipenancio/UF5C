// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:52
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UrlColector.java

package crawler;

import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.ufsc.inf.tcc.comum.PageDto;
import br.ufsc.inf.tcc.comum.UrlDto;

public class UrlColector
{

    public UrlColector()
    {
    }

    public void coletar(PageDto pageDto)
    {
        Document doc = pageDto.getDoc();
        Elements links = doc.select("a");
        for(Iterator it = links.iterator(); it.hasNext();)
        {
            String stringUrl = ((Element)it.next()).attr("abs:href");
            try
            {
                URL novaUrl = new URL(stringUrl);
                String protocol = novaUrl.getProtocol();
                String auth = novaUrl.getAuthority();
                if((protocol.equals("http") || protocol.equals("https")) && auth.length() < 80)
                {
                    UrlDto urlDto = new UrlDto(novaUrl.getAuthority(), novaUrl.toString());
                    pageDto.addUrlColetada(urlDto);
                }
            }
            catch(MalformedURLException malformedurlexception) { }
        }

    }

    public static void main(String args[])
    {
        try
        {
            URL url = new URL("http://A Caixa Econ\364mica Federal (CET) aplicou uma nova mudan\347a que deve dificultar o financiamento imobili\341rio. Ap\363s reduzir o valor que pode ser financiado na compra de um bem usado com recursos da poupan\347a, o banco aumentou a tarifa de avalia\347\343o de im\363vel de R$ 800 para R$ 2.200. O reajuste entrou em vigor na quarta-feira passada (13). Segundo a institui\347\343o, os valores estavam \u201Cmuito defasados e em grande falta de sintonia com o mercado que j\341 estava praticando tarifas na ordem de R$ 2.500\u201D. A tarifa de avalia\347\343o \351 cobrada para avaliar as condi\347\365es do im\363vel e se o pre\347o est\341 compat\355vel com o valor de mercado. O servi\347o tamb\351m visa a definir o valor do financiamento. No dia 4 deste m\352s, a Caixa mudou as regras para financiar um im\363vel usado com recursos da poupan\347a. Na pr\341tica, o banco reduziu o teto de financiamento, o que, consequentemente, exige um valor de entrada maior para fechar um contrato. Nas opera\347\365es pelo Sistema Financeiro de Habita\347\343o (SFH) \u2013 que permite financiar im\363veis de at\351 R$ 750 mil em Minas Gerais, Rio de Janeiro, S\343o Paulo e Distrito Federal, e de at\351 R$ 650 mil nos demais Estados -, o financiamento m\341ximo, que era de 80% do valor do im\363vel, passou a ser de 50%. J\341 pelo Sistema Financeiro Imobili\341rio (SFI) \u2013 utilizado na aquisi\347\343o de im\363vel cujo valor ultrapassa R$ 750 mil -, o teto de financiamento deixou de ser de 70% e passou para 40%. Essas mudan\347as n\343o atingem os financiamentos pelo programa Minha Casa, Minha Vida, e os realizados com recursos do Fundo de Garantia por Tempo de Servi\347o (FGTS).");
            System.out.println(url.toString());
            System.out.println(url.getProtocol());
            System.out.println(url.getAuthority());
        }
        catch(MalformedURLException e)
        {
            e.printStackTrace();
        }
    }
}