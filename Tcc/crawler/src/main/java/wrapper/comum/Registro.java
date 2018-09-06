// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Registro.java

package wrapper.comum;

import java.io.PrintStream;
import java.util.*;
import wrapper.WrapperConfig;

// Referenced classes of package wrapper.comum:
//            ElementoTexto, Token, IdentTipoTokenItem, TipoToken, 
//            IdentSepItem

public class Registro
{

    public Registro(ElementoTexto et)
    {
        wrapperConfig = WrapperConfig.getInstance();
        texto = et.getTexto();
        elementosTextos = new ElementoTexto[1];
        elementosTextos[0] = et;
        analisarTokens();
    }

    public Registro(ElementoTexto ets[])
    {
        int i;
        int j;
        ElementoTexto aelementotexto[];
        elementosTextos = ets;
        texto = "";
        j = (aelementotexto = ets).length;
        i = 0;
          goto _L1
_L3:
        ElementoTexto et = aelementotexto[i];
        this;
        texto;
        JVM INSTR new #53  <Class StringBuilder>;
        JVM INSTR dup_x1 ;
        JVM INSTR swap ;
        String.valueOf();
        StringBuilder();
        et.getTexto();
        append();
        " ";
        append();
        toString();
        texto;
        i++;
_L1:
        if(i < j) goto _L3; else goto _L2
_L2:
        texto = texto.trim();
        analisarTokens();
        return;
    }

    private void analisarTokens()
    {
        caracteresEspeciais = new HashSet();
        tiposDeTokens = new HashSet();
        HashSet tiposTokenIdent = WrapperConfig.tiposTokenIdent;
        ElementoTexto aelementotexto[];
        int j = (aelementotexto = elementosTextos).length;
        for(int i = 0; i < j; i++)
        {
            ElementoTexto et = aelementotexto[i];
            Token atoken[];
            int l = (atoken = et.getTokens()).length;
            for(int k = 0; k < l; k++)
            {
                Token t = atoken[k];
                if(tiposTokenIdent.contains(Integer.valueOf(t.getTipo())))
                {
                    tiposDeTokens.add(Integer.valueOf(t.getTipo()));
                    IdentTipoTokenItem identtipotokenitem = new IdentTipoTokenItem(Integer.valueOf(t.getTipo()));
                } else
                if(t.getTipo() == TipoToken.CARACTERE_ESPECIAL.ordinal())
                {
                    caracteresEspeciais.add(t);
                    IdentSepItem identsepitem = new IdentSepItem(t);
                }
            }

        }

    }

    public boolean contemCaractereEspecial(Token token)
    {
        return caracteresEspeciais.contains(token);
    }

    public boolean contemTipo(Integer tipo)
    {
        return tiposDeTokens.contains(tipo);
    }

    public List getCaracteresEspeciais()
    {
        return new ArrayList(caracteresEspeciais);
    }

    public List getTiposDeTokenIdent()
    {
        return new ArrayList(tiposDeTokens);
    }

    public String getTexto()
    {
        return texto;
    }

    public ElementoTexto[] getElementoTexto()
    {
        return elementosTextos;
    }

    public static boolean validarRegistro(Registro registro)
    {
        WrapperConfig wrapperConfig = WrapperConfig.getInstance();
        String texto = registro.getTexto();
        int registroLength = texto.length();
        if(registroLength > wrapperConfig.getNumeroMaximoDeCaracteresPorRegistro())
            return false;
        int numEspeciais = 0;
        char ac[];
        int j = (ac = texto.toCharArray()).length;
        for(int i = 0; i < j; i++)
        {
            char c = ac[i];
            if(c > '\u0100' && !Character.isSpace(c))
                numEspeciais++;
        }

        return numEspeciais * 3 <= registroLength;
    }

    public static Registro compilar(List registros)
    {
        LinkedList elementosTextos = new LinkedList();
        for(Iterator iterator = registros.iterator(); iterator.hasNext();)
        {
            Registro reg = (Registro)iterator.next();
            ElementoTexto aelementotexto[];
            int j = (aelementotexto = reg.getElementoTexto()).length;
            for(int i = 0; i < j; i++)
            {
                ElementoTexto et = aelementotexto[i];
                elementosTextos.addLast(et);
            }

        }

        ElementoTexto arrElementosTextos[] = (ElementoTexto[])elementosTextos.toArray(new ElementoTexto[elementosTextos.size()]);
        Registro registroCompilado = new Registro(arrElementosTextos);
        if(validarRegistro(registroCompilado))
            return registroCompilado;
        else
            return null;
    }

    public static void main(String args[])
    {
        System.out.println(65);
    }

    private String texto;
    private ElementoTexto elementosTextos[];
    private HashSet caracteresEspeciais;
    private HashSet tiposDeTokens;
    private WrapperConfig wrapperConfig;
}
