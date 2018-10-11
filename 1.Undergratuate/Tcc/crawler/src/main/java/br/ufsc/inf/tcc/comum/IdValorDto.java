package br.ufsc.inf.tcc.comum;

import lombok.Getter;

@Getter
public class IdValorDto {

	private String id;
	private String valor;

	public IdValorDto(String id, String valor) {
		this.id = id;
		this.valor = valor;
	}

}
