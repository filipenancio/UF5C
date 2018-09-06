// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Separador.java

package wrapper.separar;

import br.ufsc.inf.tcc.comum.ListDto;
import br.ufsc.inf.tcc.comum.RegistroDto;
import comum.*;
import wrapper.comum.Token;

public class Separador
{

    public Separador()
    {
    }

    public void Separador()
    {
    }

    public void separar(ListDto listDto)
    {
        Token ident[] = listDto.getIdentComMaiorQuantidade().getIdent();
        RegistroDto registrosDtos[] = listDto.registroDtos;
        RegistroDto aregistrodto[];
        int k = (aregistrodto = registrosDtos).length;
        for(int j = 0; j < k; j++)
        {
            RegistroDto registroDto = aregistrodto[j];
            String atributos[] = new String[ident.length + 1];
            Token tokens[] = registroDto.getTokens();
            int indexIdent = 0;
            boolean completo = false;
            String atributoAtual = "";
            for(int indexTokens = 0; indexTokens < tokens.length; indexTokens++)
            {
                if(tokens[indexTokens] == ident[indexIdent])
                {
                    atributos[indexIdent] = atributoAtual.trim();
                    atributoAtual = "";
                    if(++indexIdent != ident.length)
                        continue;
                    String ultAtributo = "";
                    for(int i = indexTokens + 1; i < tokens.length; i++)
                        ultAtributo = (new StringBuilder(String.valueOf(ultAtributo))).append(tokens[i].getValor()).append(" ").toString();

                    atributos[indexIdent] = ultAtributo.trim();
                    completo = true;
                    break;
                }
                atributoAtual = (new StringBuilder(String.valueOf(atributoAtual))).append(tokens[indexTokens].getValor()).append(" ").toString();
            }

            if(completo)
                registroDto.addAtributos(atributos);
        }

    }
}
