package br.ufsc.inf.tcc.simlist.analyser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.simmetrics.StringMetric;
import org.simmetrics.metrics.StringMetrics;

import br.ufsc.inf.tcc.simlist.SimList.Metric;
import br.ufsc.inf.tcc.simlist.data.Document;
import br.ufsc.inf.tcc.simlist.data.Line;
import lombok.AllArgsConstructor;

public class ListAnalyser {

	private StringMetric metricEp;
	private boolean needLog = false;
	private Float peso_ep;
	private Float peso_sl;
	private StringMetric metricSL;

	public ListAnalyser(Metric metricEp, Metric metricSL, Float pesoEP, Float pesoSL) {
		this.peso_ep = pesoEP;
		this.peso_sl = pesoSL;

		this.metricEp = this.getInstance(metricEp);
		this.metricSL = this.getInstance(metricSL);

	}

	private StringMetric getInstance(Metric metricEnum) {
		switch (metricEnum) {
			case COSINE_SIMILARITY:
				return StringMetrics.cosineSimilarity();
			case EUCLIDIAN_SIMILARITY:
				return StringMetrics.euclideanDistance();
			case JACCARD_SIMILARITY:
				return StringMetrics.jaccard();
			case JARO_SIMILARITY:
				return StringMetrics.jaro();
			case LEVENSHTEIN_SIMILARITY:
				return StringMetrics.levenshtein();
		}
		return null;
	}

	public Float analyse(Document doc1, Document doc2) {
		Document nDoc1 = DocumentNormalizer.normalize(doc1);
		Document nDoc2 = DocumentNormalizer.normalize(doc2);

		Float webListSim = this.analyseEP(nDoc1, nDoc2) * this.peso_ep + this.analyseSL(nDoc1, nDoc2) * this.peso_sl;
		this.log("\nWebListSim = EP * " + this.peso_ep + " + SL * " + this.peso_sl + " = " + webListSim);
		return webListSim;
	}

	private Float analyseEP(Document doc1, Document doc2) {

		Float TP = 0f;
		Float EE = 0f;
		Float P = 0f;

		int div = 0;

		if (this.canCompare(doc1.getTitlePage(), doc2.getTitlePage())) {
			TP = this.metricEp.compare(doc1.getTitlePage(), doc2.getTitlePage());
			div++;
		}

		if (this.canCompare(doc1.getLink(), doc2.getLink())) {
			EE = this.metricEp.compare(doc1.getLink(), doc2.getLink());
			div++;
		}

		if (this.canCompare(doc1.getText().getParagraph(), doc2.getText().getParagraph())) {
			P = this.metricEp.compare(doc1.getText().getParagraph(), doc2.getText().getParagraph());
			div++;
		}

		Float EP = (TP + EE + P) / div;

		this.log("\n" + this.metricEp.toString() + "\n\nTP = " + TP);
		this.log("EE = " + EE);
		this.log("P = " + P);
		this.log("EP =  (TP+EE+P)/3 = " + EP);

		return EP;
	}

	private Float analyseSL(Document doc1, Document doc2) {

		List<Line> linesList1 = doc1.getList().getLines();
		List<Line> linesList2 = doc2.getList().getLines();

		List<Line> minorList = new ArrayList<>();
		List<Line> majorList = new ArrayList<>();
		List<Line> temp = new ArrayList<>();

		if (linesList1.size() < linesList2.size()) {
			minorList.addAll(linesList1);
			majorList.addAll(linesList2);
			temp.addAll(linesList2);
		} else {
			minorList.addAll(linesList2);
			majorList.addAll(linesList1);
			temp.addAll(linesList1);
		}

		List<Relation> listRel = new ArrayList<Relation>();

		if (!minorList.isEmpty() && !majorList.isEmpty()) {
			Float[][] mSim = new Float[minorList.size()][majorList.size()];

			for (int i = 0; i < minorList.size(); i++) {
				Line line = minorList.get(i);
				for (int j = 0; j < majorList.size(); j++) {
					Line line2 = majorList.get(j);
					Float score = this.metricSL.compare(line.getAllElemets(), line2.getAllElemets());
					listRel.add(new Relation("A" + (i + 1), "B" + (j + 1), score));
					mSim[i][j] = score;
				}
			}
			this.log(mSim);
			Collections.sort(listRel);
		}

		List<String> selItens = new ArrayList<String>();
		Float SOR = 0f;

		for (Relation relation : listRel) {
			if (selItens.contains(relation.r1) || selItens.contains(relation.r2)) {
				continue;
			}
			selItens.add(relation.r1);
			selItens.add(relation.r2);
			SOR += relation.s;
			this.log(relation.r1 + " - " + relation.r2 + " = " + relation.s);
		}

		this.log("\nSOR = " + SOR);

		Float TL = 0f;

		if (this.canCompare(doc1.getList().getTitle(), doc2.getList().getTitle())) {
			TL = this.metricSL.compare(doc1.getList().getTitle(), doc2.getList().getTitle());
		}

		this.log("TL = " + TL);

		Float SL = (TL + SOR) / (minorList.size() + 1);

		this.log("SL = (TL + SOR) / (menor + 1) = " + SL);

		return SL;
	}

	private void log(Float[][] mSim) {
		if (this.needLog) {

			System.out.println();
			int lenght = mSim[0].length;
			for (int i = 0; i <= mSim.length; i++) {
				for (int j = 0; j <= lenght; j++) {
					if (i == 0 && j == 0) {
						System.out.printf("%15s", "x");
					} else if (i == 0) {
						System.out.printf("%15s", "B" + j);
					} else if (j == 0) {
						System.out.printf("%15s", "A" + i);
					} else {
						System.out.printf("%15s", "" + mSim[i - 1][j - 1]);
					}
				}
				System.out.println();
			}
			System.out.println();

		}
	}

	private boolean canCompare(String s1, String s2) {
		return !s1.isEmpty() && !s2.isEmpty();
	}

	public void setLog(boolean isLogEnabled) {
		this.needLog = isLogEnabled;
	}

	private void log(String message) {
		if (this.needLog) {
			System.out.println(message);
		}
	}

	@AllArgsConstructor
	private class Relation implements Comparable<Relation> {
		public String r1;
		public String r2;
		public Float s;

		@Override
		public int compareTo(Relation o) {
			if (this.s > o.s) {
				return -1;
			} else if (this.s < o.s) {
				return 1;
			}
			return 0;
		}

	}

}
