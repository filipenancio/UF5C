package br.ufsc.inf.tcc.comum;

import java.util.LinkedList;
import java.util.List;

import wrapper.comum.Token;

public class RegistroDto {

	private String atributos[];
	private List<Token> tokens;

	public RegistroDto() {
		tokens = new LinkedList<Token>();
		atributos = new String[0];
	}

	public void addToken(Token token) {
		tokens.add(token);
	}

	public void addAtributos(String atributos[]) {
		this.atributos = atributos;
	}

	public Token[] getTokens() {
		return (Token[]) tokens.toArray();
	}

	public String[] getAtributos() {
		return atributos;
	}

}
