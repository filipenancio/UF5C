package br.ufsc.inf.tcc.simlist.data;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Document {
	private String link;
	private String titlePage;

	private Text text;
	private ListOf list;

	public Document() {

	}

	public Document(String link, String titlePage, Text text, ListOf list) {
		this.link = link;
		this.titlePage = titlePage;
		this.text = text;
		this.list = list;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitlePage() {
		return this.titlePage;
	}

	public void setTitlePage(String titlePage) {
		this.titlePage = titlePage;
	}

	public Text getText() {
		return this.text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public ListOf getList() {
		return this.list;
	}

	public void setList(ListOf list) {
		this.list = list;
	}

}
