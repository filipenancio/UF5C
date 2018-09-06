package br.ufsc.inf.tcc.simlist;

import java.io.File;

import br.ufsc.inf.tcc.simlist.analyser.ListAnalyser;
import br.ufsc.inf.tcc.simlist.data.Document;
import br.ufsc.inf.tcc.simlist.loader.LoaderListXml;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimList {

	public enum Metric {
		//@formatter:off
		COSINE_SIMILARITY,
		EUCLIDIAN_SIMILARITY,
		JACCARD_SIMILARITY,
		LEVENSHTEIN_SIMILARITY,
		JARO_SIMILARITY;
		//@formatter:on
	}

	private boolean isLogEnabled = false;
	private Float pesoEP = 0.3f;
	private Float pesoSL = 0.7f;

	public SimList() {
	}

	public SimList(Float pesoEp, Float pesoSl, boolean enableLog) {
		this.isLogEnabled = enableLog;
		this.pesoEP = pesoEp;
		this.pesoSL = pesoSl;
	}

	public Float analyse(File file1, File file2, Metric metric) throws Exception {

		return this.analyse(file1, file2, metric, metric);
	}

	public Float analyse(File file1, File file2, Metric metricEp, Metric metricSL) throws Exception {

		this.validateFiles(file1, file2);

		Document doc1 = LoaderListXml.loadListFromFile(file1);
		Document doc2 = LoaderListXml.loadListFromFile(file2);

		return this.analyse(doc1, doc2, metricEp, metricSL);
	}

	public Float analyse(Document doc1, Document doc2, Metric metricEp, Metric metricSL) {

		ListAnalyser analyser = new ListAnalyser(metricEp, metricSL, this.pesoEP, this.pesoSL);
		analyser.setLog(this.isLogEnabled);
		return analyser.analyse(doc1, doc2);
	}

	private void validateFiles(File... files) throws Exception {
		for (File file : files) {
			if (!file.exists()) {
				throw new Exception("Arquivo " + file.getName() + " nao encontrado!");
			} else if (!file.isFile()) {
				throw new Exception("Arquivo " + file.getName() + " nao e um arquivo válido!");
			}
		}
	}

}
