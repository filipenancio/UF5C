package br.ufsc.inf.tcc.simlist.data;

import javax.xml.bind.annotation.XmlType;

@XmlType
public class Text {
	private String paragraph;

	public Text() {
	}

	public Text(String paragraph) {
		this.paragraph = paragraph;
	}

	public String getParagraph() {
		return this.paragraph;
	}

	public void setParagraph(String paragraph) {
		this.paragraph = paragraph;
	}
}
