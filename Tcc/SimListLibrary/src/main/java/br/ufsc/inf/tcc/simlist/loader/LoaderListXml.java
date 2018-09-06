package br.ufsc.inf.tcc.simlist.loader;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import br.ufsc.inf.tcc.simlist.data.Document;

public class LoaderListXml {

	public static Document loadListFromFile(File file) throws Exception {
		JAXBContext context = JAXBContext.newInstance(Document.class);
		Unmarshaller um = context.createUnmarshaller();

		return (Document) um.unmarshal(file);
	}

}
