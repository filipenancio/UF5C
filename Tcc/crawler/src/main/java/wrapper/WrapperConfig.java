// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WrapperConfig.java

package wrapper;

import crawler.Settings;
import java.util.HashSet;
import wrapper.comum.TipoToken;

public class WrapperConfig
{

    private WrapperConfig(Settings settings)
    {
        this.settings = settings;
        numeroMinimoDeRegistrosPorLista = settings.getNumMinRegistros();
        numeroMaximoDeCaracteresPorRegistro = settings.getNumMaxCaracteres();
        numeroDeDerivacoes = settings.getNumDeDerivacoes();
        percentualMinimoParaQuantidadeDeIdent = settings.getPercentIdentidadePrevalecente();
        percentualMaximoParaRepeticaoEmColuna = settings.getPercentualMaximoParaRepeticaoEmColuna();
    }

    public static WrapperConfig getInstance()
    {
        return instance;
    }

    public static void defineSettings(Settings settings)
    {
        instance = new WrapperConfig(settings);
    }

    public int getNumeroMinimoDeRegistrosPorLista()
    {
        return numeroMinimoDeRegistrosPorLista;
    }

    public int getNumeroMaximoDeCaracteresPorRegistro()
    {
        return numeroMaximoDeCaracteresPorRegistro;
    }

    public int getNumeroDeDerivacoes()
    {
        return numeroDeDerivacoes;
    }

    public float getPercentualMinimoParaQuantidadeDeIdent()
    {
        return percentualMinimoParaQuantidadeDeIdent;
    }

    public float getPercentualMaximoParaRepeticaoEmColuna()
    {
        return percentualMaximoParaRepeticaoEmColuna;
    }

    public Settings getSettings()
    {
        return settings;
    }

    public static HashSet getTiposTokenIdent()
    {
        HashSet tiposTokenIdent = new HashSet();
        tiposTokenIdent.add(Integer.valueOf(TipoToken.EMAIL.ordinal()));
        tiposTokenIdent.add(Integer.valueOf(TipoToken.DATA.ordinal()));
        return tiposTokenIdent;
    }

    private Settings settings;
    private int numeroMinimoDeRegistrosPorLista;
    private int numeroMaximoDeCaracteresPorRegistro;
    private int numeroDeDerivacoes;
    private float percentualMinimoParaQuantidadeDeIdent;
    private float percentualMaximoParaRepeticaoEmColuna;
    private static final int numeroMinimoDeRegistrosPorListaDefault = 8;
    private static final int numeroMaximoDeCaracteresPorRegistroDefault = 160;
    private static final int numeroDeDerivacoesDefault = 4;
    public static final float percentualMinimoParaQuantidadeDeIdentDefault = 0.6F;
    public static final float percentualMaximoParaRepeticaoEmColunaDefault = 0.7F;
    public static final HashSet tiposTokenIdent = getTiposTokenIdent();
    private static WrapperConfig instance;

}
