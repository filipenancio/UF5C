package wrapper.agrup;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import br.ufsc.inf.tcc.comum.ListDto;
import br.ufsc.inf.tcc.comum.PageDto;
import br.ufsc.inf.tcc.comum.RegistroDto;
import br.ufsc.inf.tcc.comum.ValorQtdDto;
import wrapper.WrapperConfig;
import wrapper.comum.Registro;
import wrapper.separar.Separador;

public class Agrupador
{
    public class TextosColuna
    {

        public void addTexto(String texto)
        {
            if(!this.hashTextoColuna.containsKey(texto)) {
					this.hashTextoColuna.put(texto, Integer.valueOf(0));
				}
            int qtd = ((Integer)this.hashTextoColuna.remove(texto)).intValue();
            this.hashTextoColuna.put(texto, Integer.valueOf(qtd + 1));
        }

        public int getMaiorQuantidade()
        {
            Collection values = this.hashTextoColuna.values();
            int maior = 0;
            for(Iterator iterator = values.iterator(); iterator.hasNext();)
            {
                Integer i = (Integer)iterator.next();
                if(i.intValue() > maior) {
						maior = i.intValue();
					}
            }

            return maior;
        }

        HashMap hashTextoColuna;

        public TextosColuna()
        {
            super();
            this.hashTextoColuna = new HashMap();
        }
    }


    public Agrupador(PageDto page)
    {
        this.page = page;
        this.wrapperConfig = WrapperConfig.getInstance();
    }

    public Registro agrupar(List registros)
    {
        if(registros.size() == 0) {
			throw new RuntimeException("Zero registros foram entregues para agrupar");
		}
        if(registros.size() > 0 && registros.size() < this.wrapperConfig.getNumeroMinimoDeRegistrosPorLista()) {
			return Registro.compilar(registros);
		}
        Extrator extrator = new Extrator(this.wrapperConfig.getNumeroDeDerivacoes(), this.wrapperConfig.getNumeroMinimoDeRegistrosPorLista());
        List grupos = extrator.extrair(registros);
        if(grupos.size() == 0) {
			return Registro.compilar(registros);
		}
        for(Iterator iterator = grupos.iterator(); iterator.hasNext();)
        {
            GrupoDeRegistrosSemelhantes grupo = (GrupoDeRegistrosSemelhantes)iterator.next();
            ListDto listDto = grupo.getListDto();
            if(this.validarListDto(listDto))
            {
                Separador sep = new Separador();
                sep.separar(listDto);
                if(this.validarSeparacao(listDto)) {
						this.page.addLista(listDto);
					}
            }
        }

        return null;
    }

    private boolean validarSeparacao(ListDto listDto)
    {
        RegistroDto registrosDtos[] = listDto.registroDtos;
        int numAtts = listDto.getIdentComMaiorQuantidade().getIdent().length + 1;
        boolean attsVazios[] = new boolean[numAtts];
        for(int i = 0; i < attsVazios.length; i++) {
			attsVazios[i] = true;
		}

        HashSet attsValores[] = new HashSet[numAtts];
        for(int i = 0; i < attsValores.length; i++) {
			attsValores[i] = new HashSet();
		}

        TextosColuna textosColuna[] = new TextosColuna[numAtts];
        for(int i = 0; i < textosColuna.length; i++) {
			textosColuna[i] = new TextosColuna();
		}

        RegistroDto aregistrodto[];
        int k = (aregistrodto = registrosDtos).length;
        for(int j = 0; j < k; j++)
        {
            RegistroDto registroDto = aregistrodto[j];
            String atts[] = registroDto.getAtributos();
            if(atts != null && atts.length > 0)
            {
                for(int i = 0; i < atts.length; i++) {
						if(atts[i] != null && !atts[i].trim().equals(""))
                    {
                        attsVazios[i] = false;
                        attsValores[i].add(atts[i]);
                        textosColuna[i].addTexto(atts[i]);
                    }
					}

            }
        }

        HashSet indexOut = new HashSet();
        for(int i = 0; i < numAtts; i++) {
			if(attsVazios[i] || attsValores[i].size() < 2) {
				indexOut.add(Integer.valueOf(i));
			} else
			if(textosColuna[i].getMaiorQuantidade() > this.wrapperConfig.getPercentualMaximoParaRepeticaoEmColuna() * registrosDtos.length) {
				indexOut.add(Integer.valueOf(i));
			}
		}

        if(indexOut.size() > 0)
        {
            RegistroDto aregistrodto1[];
            int i1 = (aregistrodto1 = registrosDtos).length;
            for(int l = 0; l < i1; l++)
            {
                RegistroDto registroDto = aregistrodto1[l];
                String atts[] = registroDto.getAtributos();
                if(atts != null && atts.length > 0)
                {
                    LinkedList attsNovos = new LinkedList();
                    for(int i = 0; i < atts.length; i++) {
							if(!indexOut.contains(Integer.valueOf(i))) {
								attsNovos.addLast(atts[i]);
							}
						}

                    registroDto.addAtributos((String[])attsNovos.toArray(new String[attsNovos.size()]));
                }
            }

        }
        return numAtts - indexOut.size() >= 2;
    }

    private boolean validarListDto(ListDto listDto)
    {
        int qtdRegistros = listDto.registros.length;
        int maxIdent = listDto.getIdentComMaiorQuantidade().getQtd();
        float minAceito = qtdRegistros * this.wrapperConfig.getPercentualMinimoParaQuantidadeDeIdent();
        if(maxIdent < minAceito) {
			return false;
		}
        int contValidado = 0;
        int contNaoValidado = 0;
        ValorQtdDto avalorqtddto[];
        int k = (avalorqtddto = listDto.identsDesc).length;
        for(int j = 0; j < k; j++)
        {
            ValorQtdDto vqtd = avalorqtddto[j];
            String desc = vqtd.getValor();
            desc = desc.substring(1, desc.length());
            String descSplit[] = desc.split("\\.");
            boolean achouSegundoTexto = false;
            int i;
            for(i = 0; i < descSplit.length; i++)
            {
                if(!descSplit[i].startsWith("x") && !descSplit[i].startsWith("t")) {
						continue;
					}
                i++;
                break;
            }

            for(; i < descSplit.length; i++)
            {
                if(!descSplit[i].startsWith("s")) {
						continue;
					}
                i++;
                break;
            }

            for(; i < descSplit.length; i++)
            {
                if(!descSplit[i].startsWith("x") && !descSplit[i].startsWith("t")) {
						continue;
					}
                achouSegundoTexto = true;
                i++;
                break;
            }

            if(achouSegundoTexto) {
					contValidado += vqtd.getQtd();
				} else {
					contNaoValidado += vqtd.getQtd();
				}
        }

        return contValidado > contNaoValidado;
    }

    private PageDto page;
    private WrapperConfig wrapperConfig;
}
