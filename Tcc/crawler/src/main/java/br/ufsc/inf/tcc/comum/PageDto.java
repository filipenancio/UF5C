package br.ufsc.inf.tcc.comum;

import java.util.LinkedList;
import java.util.List;

import org.jsoup.nodes.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class PageDto {

	@Setter
	private Document doc;

	private UrlDto urlDto;
	private List<UrlDto> urlsColetadas;
	private List<ListDto> listas;

	public PageDto(UrlDto urlDto) {
		this.urlDto = urlDto;
		urlsColetadas = new LinkedList<UrlDto>();
		listas = new LinkedList<ListDto>();
	}

	public void addUrlColetada(UrlDto urlDto) {
		urlsColetadas.add(urlDto);
	}

	public void addLista(ListDto listDto) {
		listas.add(listDto);
	}

	public UrlDto[] getUrlColetadas() {
		return urlsColetadas.toArray(new UrlDto[urlsColetadas.size()]);
	}

}
