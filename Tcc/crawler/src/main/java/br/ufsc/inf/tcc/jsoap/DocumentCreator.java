package br.ufsc.inf.tcc.jsoap;

import java.io.File;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DocumentCreator {

	public static Document create(File file) {
		try {
			return Jsoup.parse(file, "UTF-8");
		} catch (Throwable throwable) {
			return null;
		}
	}

	public static Document create(String urlDesc) {
		try {
			URL url = new URL(urlDesc);
			return Jsoup.parse(url, 10000);
		} catch (Throwable throwable) {
			return null;
		}
	}
}
