package br.ufsc.inf.tcc.simlist.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListOf {
	private String title;

	@XmlElementWrapper(name = "lines")
	@XmlElement(name = "line")
	private List<Line> ListOfLines;

	public ListOf() {
	}

	public ListOf(String title, List<Line> lines) {
		this.title = title;
		this.ListOfLines = lines;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Line> getLines() {
		return this.ListOfLines;
	}

	public void setLines(List<Line> lines) {
		this.ListOfLines = lines;
	}

}
