package br.ufsc.ine.cco.ia.gomoku;

import br.ufsc.ine.cco.ia.gomoku.bussines.Computer;
import br.ufsc.ine.cco.ia.gomoku.bussines.Jogador;
import br.ufsc.ine.cco.ia.gomoku.bussines.Movimento;
import br.ufsc.ine.cco.ia.gomoku.bussines.Tabuleiro;

public class Gomoku {

	private static Gomoku gomoku;
	private Tabuleiro tabuleiro;
	private Jogador ultimoJogador;
	private Jogador atualJogador;

	private static boolean DX2 = false;

	private Gomoku() {
		this.tabuleiro = new Tabuleiro(15, 15);
		this.atualJogador = new Jogador("Jogador 1", "X");
		this.ultimoJogador = DX2 ? new Jogador("Jogador 2", "O") : new Computer("Computador", "O");
	}

	public static Gomoku pegaInstancia() {
		gomoku = gomoku == null ? new Gomoku() : gomoku;
		return gomoku;
	}

	public void iniciar() {
		do {
			Movimento mov = this.atualJogador.perguntarJogada(this.tabuleiro);
			this.tabuleiro.tentaAtualizar(mov, this.atualJogador.pegaMarca(), true);
			if (this.tabuleiro.jogadaAceita()) {
				this.trocaJogador();
			} else {
				System.out.println("Ultima jogada não aceita, local já ocupado.");
			}
		} while (!this.tabuleiro.temVencedor());
		this.tabuleiro.imprimir();
		this.parabenizarVencedor();
	}

	private void trocaJogador() {
		Jogador temp = this.atualJogador;
		this.atualJogador = this.ultimoJogador;
		this.ultimoJogador = temp;
	}

	private void parabenizarVencedor() {
		System.out.println("Parabéns " + this.ultimoJogador.pegaNome() + ", você ganhou!");
		System.out.println("Finalizando o jogo ...");
	}

}
