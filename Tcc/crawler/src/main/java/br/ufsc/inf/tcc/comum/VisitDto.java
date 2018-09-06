package br.ufsc.inf.tcc.comum;

import java.util.LinkedList;
import java.util.List;

import crawler.Settings;
import lombok.Getter;

@Getter
public class VisitDto {
	private long id;
	private Settings settings;
	private List<ListDto> listas;

	public VisitDto(Settings settings) {
		listas = new LinkedList<ListDto>();
		this.settings = settings;
	}

	public VisitDto(long id, Settings settings) {
		listas = new LinkedList<ListDto>();
		this.id = id;
		this.settings = settings;
	}

	public void addLista(ListDto listDto) {
		listas.add(listDto);
	}

}
