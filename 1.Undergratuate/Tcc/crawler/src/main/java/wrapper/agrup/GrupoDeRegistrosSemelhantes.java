// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GrupoDeRegistrosSemelhantes.java

package wrapper.agrup;

import comum.*;
import java.util.*;

import br.ufsc.inf.tcc.comum.IdValorDto;
import br.ufsc.inf.tcc.comum.ListDto;
import br.ufsc.inf.tcc.comum.RegistroDto;
import br.ufsc.inf.tcc.comum.ValorQtdDto;
import wrapper.comum.*;

public class GrupoDeRegistrosSemelhantes
{

    public GrupoDeRegistrosSemelhantes(List registros)
    {
        this.registros = registros;
        identidades = new LinkedList();
    }

    public void addIdentidade(Identidade identidade)
    {
        identidades.add(identidade);
    }

    public List getRegistros()
    {
        return registros;
    }

    public List getIdentidades()
    {
        return identidades;
    }

    public ListDto getListDto()
    {
        ListDto list = new ListDto();
        LinkedList valoresRegistros = new LinkedList();
        LinkedList registrosDtos = new LinkedList();
        HashMap separadores = new HashMap();
        HashMap tiposToken = new HashMap();
        HashMap paths = new HashMap();
        HashMap idents = new HashMap();
        HashMap identsPath = new HashMap();
        for(Iterator iterator = identidades.iterator(); iterator.hasNext();)
        {
            Identidade ident = (Identidade)iterator.next();
            Token atoken[];
            int j = (atoken = ident.getTokensEmComum()).length;
            for(int i = 0; i < j; i++)
            {
                Token token = atoken[i];
                if(!separadores.containsKey(token))
                {
                    String id = (new StringBuilder("s")).append(separadores.size() + 1).toString();
                    IdValorDto idv = new IdValorDto(id, token.getValor());
                    separadores.put(token, idv);
                }
            }

            TipoToken tiposTokenExistentes[] = TipoToken.values();
            Integer ainteger[];
            int l = (ainteger = ident.getTiposDeTokenEmComum()).length;
            for(int k = 0; k < l; k++)
            {
                Integer tipo = ainteger[k];
                if(!tiposToken.containsKey(tipo))
                {
                    String id = (new StringBuilder("t")).append(tiposToken.size() + 1).toString();
                    IdValorDto idv = new IdValorDto(id, tiposTokenExistentes[tipo.intValue()].toString());
                    tiposToken.put(tipo, idv);
                }
            }

        }

        for(Iterator iterator1 = registros.iterator(); iterator1.hasNext();)
        {
            Registro reg = (Registro)iterator1.next();
            valoresRegistros.addLast(reg.getTexto());
            RegistroDto registroDto = new RegistroDto();
            registrosDtos.add(registroDto);
            String ident = "";
            LinkedList identToken = new LinkedList();
            boolean x = false;
            ElementoTexto aelementotexto[];
            int j1 = (aelementotexto = reg.getElementoTexto()).length;
            for(int i1 = 0; i1 < j1; i1++)
            {
                ElementoTexto et = aelementotexto[i1];
                Path etPath = et.getPath();
                if(!paths.containsKey(etPath))
                {
                    String id = (new StringBuilder("p")).append(paths.size() + 1).toString();
                    String pathDesc[] = etPath.getPathDesc();
                    String path = "";
                    String as[];
                    int j2 = (as = pathDesc).length;
                    for(int i2 = 0; i2 < j2; i2++)
                    {
                        String pathItem = as[i2];
                        path = (new StringBuilder(String.valueOf(path))).append("/").append(pathItem).toString();
                    }

                    IdValorDto idv = new IdValorDto(id, path);
                    paths.put(etPath, idv);
                }
                Token atoken1[];
                int l1 = (atoken1 = et.getTokens()).length;
                for(int k1 = 0; k1 < l1; k1++)
                {
                    Token token = atoken1[k1];
                    registroDto.addToken(token);
                    if(separadores.containsKey(token))
                    {
                        IdValorDto idv = (IdValorDto)separadores.get(token);
                        identToken.add(token);
                        ident = (new StringBuilder(String.valueOf(ident))).append(".").append(idv.getId()).toString();
                        x = false;
                    } else
                    if(!x)
                    {
                        ident = (new StringBuilder(String.valueOf(ident))).append(".x").toString();
                        x = true;
                    }
                }

            }

            if(!idents.containsKey(ident))
            {
                ValorQtdDto vqtd = new ValorQtdDto(ident, 1);
                vqtd.addIdent(identToken);
                idents.put(ident, vqtd);
            } else
            {
                ValorQtdDto vqtd = (ValorQtdDto)idents.get(ident);
                vqtd.setQtd(vqtd.getQtd() + 1);
            }
        }

        list.registros = (String[])valoresRegistros.toArray(new String[valoresRegistros.size()]);
        list.registroDtos = (RegistroDto[])registrosDtos.toArray(new RegistroDto[registrosDtos.size()]);
        list.separadores = (IdValorDto[])separadores.values().toArray(new IdValorDto[separadores.size()]);
        list.tiposEspeciais = (IdValorDto[])tiposToken.values().toArray(new IdValorDto[tiposToken.size()]);
        list.paths = (IdValorDto[])paths.values().toArray(new IdValorDto[paths.size()]);
        list.pathsDesc = (ValorQtdDto[])identsPath.values().toArray(new ValorQtdDto[identsPath.size()]);
        list.identsDesc = (ValorQtdDto[])idents.values().toArray(new ValorQtdDto[idents.size()]);
        return list;
    }

    private List registros;
    private List identidades;
}
