package br.ufsc.inf.tcc.comum;

import lombok.Getter;

@Getter
public class UrlDto {

	private String auth;
	private long idAuth;
	private String url;
	private long id;

	public UrlDto(String auth, String url) {
		this.auth = auth;
		this.url = url;
	}

	public UrlDto(String url, long id, long idAuth) {
		this.url = url;
		this.id = id;
		this.idAuth = idAuth;
	}

	public UrlDto(String auth, String url, long id) {
		this.auth = auth;
		this.url = url;
		this.id = id;
	}

}
