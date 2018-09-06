package br.ufsc.inf.tcc.simlist.business.loader.xml;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import br.ufsc.inf.tcc.simlist.business.data.crud.DocumentSave;
import br.ufsc.inf.tcc.simlist.business.data.xml.Document;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoaderListXml {

	private String loaderPath;

	public LoaderListXml(String loaderConfigPath) {
		this.loaderPath = loaderConfigPath;
	}

	public void load() throws Exception {

		// log.info("Procurando listas no diretório '" + loaderPath + "'.");
		List<File> files = this.getListOfFiles();
		// String notify = files.isEmpty() ? "Diretório indicado está vazio."
		// : "Foi encontrado um total de " + files.size() + " lista(s).";
		// log.info(notify);

		DocumentSave docSave = new DocumentSave();
		for (File file : files) {
			try {
				Document doc = this.tryExtractDocument(file);
				docSave.save(doc, file.getName());
			} catch (Exception e) {
				// log.info("Não foi possível concluir a extração e o carregamento
				// do arquivo: " + file.getName());
				// log.info("ERRO\n\t Motivo: " + (e.getMessage() != null ?
				// e.getMessage() : "desconhecido") + "\n\t Causa: "
				// + (e.getCause() != null ? e.getCause() : "desconhecido"));
			}
		}

	}

	private List<File> getListOfFiles() throws Exception {

		File path = null;
		path = new File(this.loaderPath);

		if (!path.isDirectory()) {
			throw new Exception("Erro: Caminho especificado não é um diretório válido!");
		}
		return Arrays.asList(path.listFiles());
	}

	private Document tryExtractDocument(File doc) throws Exception {

		JAXBContext context = JAXBContext.newInstance(Document.class);
		Unmarshaller um = context.createUnmarshaller();

		return (Document) um.unmarshal(doc);
	}

}
