// Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 16/10/2017 22:29:52
// Home Page: http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Settings.java

package crawler;


public final class Settings extends Enum
{

    private Settings(String s, int i, int id, int numMinRegistros, int numMaxCaracteres, int numDerivacoes, float percentIdentPrevalecente, 
            float percentRepeticaoEmColuna)
    {
        super(s, i);
        this.id = id;
        this.numMinRegistros = numMinRegistros;
        this.numMaxCaracteres = numMaxCaracteres;
        numeroDeDerivacoes = numDerivacoes;
        percentIdentidadePrevalecente = percentIdentPrevalecente;
        this.percentRepeticaoEmColuna = percentRepeticaoEmColuna;
    }

    public int getId()
    {
        return id;
    }

    public int getNumMinRegistros()
    {
        return numMinRegistros;
    }

    public int getNumMaxCaracteres()
    {
        return numMaxCaracteres;
    }

    public int getNumDeDerivacoes()
    {
        return numeroDeDerivacoes;
    }

    public float getPercentIdentidadePrevalecente()
    {
        return percentIdentidadePrevalecente;
    }

    public float getPercentualMaximoParaRepeticaoEmColuna()
    {
        return percentRepeticaoEmColuna;
    }

    public static Settings getSettings(int id)
    {
        Settings settingsArray[] = values();
        Settings asettings[];
        int j = (asettings = settingsArray).length;
        for(int i = 0; i < j; i++)
        {
            Settings settings = asettings[i];
            if(settings.id == id)
                return settings;
        }

        return null;
    }

    public static Settings[] values()
    {
        Settings asettings[];
        int i;
        Settings asettings1[];
        System.arraycopy(asettings = ENUM$VALUES, 0, asettings1 = new Settings[i = asettings.length], 0, i);
        return asettings1;
    }

    public static Settings valueOf(String s)
    {
        return (Settings)Enum.valueOf(crawler/Settings, s);
    }

    public static final Settings SETT1;
    public static final Settings SETT2;
    public static final Settings SETT3;
    public static final Settings SETT4;
    public static final Settings SETT5;
    public static final Settings SETT6;
    public static final Settings SETT7;
    public static final Settings SETT8;
    private final int id;
    private final int numMinRegistros;
    private final int numMaxCaracteres;
    private final int numeroDeDerivacoes;
    private final float percentIdentidadePrevalecente;
    private final float percentRepeticaoEmColuna;
    private static final Settings ENUM$VALUES[];

    static 
    {
        SETT1 = new Settings("SETT1", 0, 1, 3, 160, 2, 0.5F, 0.8F);
        SETT2 = new Settings("SETT2", 1, 2, 3, 160, 2, 0.5F, 0.4F);
        SETT3 = new Settings("SETT3", 2, 3, 3, 80, 2, 0.5F, 0.8F);
        SETT4 = new Settings("SETT4", 3, 4, 3, 80, 2, 0.5F, 0.4F);
        SETT5 = new Settings("SETT5", 4, 5, 6, 160, 2, 0.8F, 0.8F);
        SETT6 = new Settings("SETT6", 5, 6, 6, 160, 2, 0.8F, 0.4F);
        SETT7 = new Settings("SETT7", 6, 7, 6, 80, 2, 0.8F, 0.8F);
        SETT8 = new Settings("SETT8", 7, 8, 6, 80, 2, 0.8F, 0.4F);
        ENUM$VALUES = (new Settings[] {
            SETT1, SETT2, SETT3, SETT4, SETT5, SETT6, SETT7, SETT8
        });
    }
}