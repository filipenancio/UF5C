package br.ufsc.ine.cco.ia.gomoku.bussines;

import java.util.List;

public class Computer extends Jogador {

	public Computer(String nome, String marca) {
		super(nome, marca);
	}

	@Override
	public Movimento perguntarJogada(Tabuleiro tabuleiro) {
		tabuleiro.imprimir();
		return this.decide(tabuleiro);
	}

	private Movimento decide(Tabuleiro tabuleiro) {
		Object[] o = this.pegaMelhorMovimento(tabuleiro, 5, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
		System.out.println(o[0]);
		return (Movimento) o[1];
	}

	private Object[] pegaMelhorMovimento(Tabuleiro tabuleiro, int iteracao, double minhaPontuacao, double pontuacaoInimiga) {
		if (iteracao == 0) {
			Object[] x = { VerificadorTabuleiro.pegaPontuacao(tabuleiro), null };
			System.out.println(x[0]);
			return x;
		}
		List<Movimento> movimentos = VerificadorTabuleiro.pegaListaMovimentos(tabuleiro);

		Object[] temp;
		Double melhorPontuacao = minhaPontuacao;
		Movimento melhorMov = null;

		while (movimentos.size() > 0) {
			Tabuleiro novoTabuleiro = (Tabuleiro) tabuleiro.clone();
			Movimento proximoMov = movimentos.get(0);
			novoTabuleiro.tentaAtualizar(proximoMov, tabuleiro.proximaMarca(), false);
			novoTabuleiro.imprimir();
			temp = this.pegaMelhorMovimento(novoTabuleiro, iteracao - 1, -pontuacaoInimiga, -melhorPontuacao);
			Double pontuacaoTemp = (Double) temp[0];
			if (pontuacaoTemp > melhorPontuacao) {
				melhorPontuacao = pontuacaoTemp;
				melhorMov = proximoMov;
			}
			if (melhorPontuacao > pontuacaoInimiga) {
				Object[] x = { melhorPontuacao, melhorMov };
				return x;
			}
			movimentos.remove(0);
		}
		Object[] x = { melhorPontuacao, melhorMov };
		return x;
	}
}
