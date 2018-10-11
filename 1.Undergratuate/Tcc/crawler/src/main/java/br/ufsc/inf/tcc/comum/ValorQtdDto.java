package br.ufsc.inf.tcc.comum;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import wrapper.comum.Token;

@Getter
public class ValorQtdDto {

	private String valor;

	@Setter
	private int qtd;

	private List<Token> ident;

	public ValorQtdDto(String valor, int qtd) {
		this.valor = valor;
		this.qtd = qtd;
		ident = new LinkedList<Token>();
	}

	public void addIdent(List<Token> tokens) {
		ident = tokens;
	}

	public Token[] getIdent() {
		return ident.toArray(new Token[ident.size()]);
	}

}
