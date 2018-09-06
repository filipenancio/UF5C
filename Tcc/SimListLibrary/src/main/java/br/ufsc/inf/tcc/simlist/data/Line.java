package br.ufsc.inf.tcc.simlist.data;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlAccessorType(XmlAccessType.FIELD)
public class Line {

	@XmlElementWrapper(name = "elements")
	@XmlElement(name = "element")
	private List<String> ListOfElements;

	public Line() {
	}

	public Line(List<String> elements) {
		this.ListOfElements = elements;
	}

	public List<String> getElement() {

		return this.ListOfElements;
	}

	public void setElement(List<String> elements) {

		this.ListOfElements = elements;
	}

	public String getAllElemets() {

		String line = "";

		for (String element : ListOfElements) {
			line += (" " + element);
		}

		return line;
	}

}
