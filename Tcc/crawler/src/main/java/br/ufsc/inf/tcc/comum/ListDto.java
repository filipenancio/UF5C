package br.ufsc.inf.tcc.comum;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ListDto {

	public String registros[];
	public RegistroDto registroDtos[];
	public IdValorDto tiposEspeciais[];
	public IdValorDto separadores[];
	public IdValorDto paths[];
	public ValorQtdDto identsDesc[];
	public ValorQtdDto pathsDesc[];

	public ValorQtdDto getIdentComMaiorQuantidade() {
		int qtdMax = 0;
		ValorQtdDto vqtdMax = null;
		ValorQtdDto avalorqtddto[];
		int j = (avalorqtddto = identsDesc).length;
		for (int i = 0; i < j; i++) {
			ValorQtdDto vqtd = avalorqtddto[i];
			if (vqtd.getQtd() > qtdMax) {
				qtdMax = vqtd.getQtd();
				vqtdMax = vqtd;
			}
		}

		return vqtdMax;
	}

}
