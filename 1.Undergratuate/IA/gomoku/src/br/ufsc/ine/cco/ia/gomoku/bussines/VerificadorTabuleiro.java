package br.ufsc.ine.cco.ia.gomoku.bussines;

import java.util.ArrayList;
import java.util.List;

public class VerificadorTabuleiro {

	private final static String CONDICAO_VENCER = "(.|O|X)*(XXXXX|OOOOO)(.|O|X)*";

	public static boolean ehNodoFolha(Tabuleiro tabuleiro) {
		//@formatter:off
		return tabuleiro.dP.matches(CONDICAO_VENCER)   ||
				tabuleiro.dS.matches(CONDICAO_VENCER)  ||
				tabuleiro.lH.matches(CONDICAO_VENCER)     ||
				tabuleiro.lV.matches(CONDICAO_VENCER);
		//@formatter:on
	}

	public static List<Movimento> pegaListaMovimentos(Tabuleiro tabuleiro) {
		List<Movimento> movimentosCandidatos = new ArrayList<Movimento>();

		List<Movimento> jogadas = tabuleiro.pegaJogadas();
		for (Movimento movimento : jogadas) {
			for (Movimento vizinho : tabuleiro.pegaVizinhosLivres(movimento)) {
				boolean add = true;
				for (Movimento candidato : movimentosCandidatos) {
					if (candidato.equals(vizinho)) {
						add = false;
						break;
					}
				}
				if (add) {
					movimentosCandidatos.add(vizinho);
				}
			}
		}

		return movimentosCandidatos;
	}

	public static Double pegaPontuacao(Tabuleiro tabuleiro) {
		return tabuleiro.pegaPontuacaoMax() - tabuleiro.pegaPontuacaoMin();
	}
}
