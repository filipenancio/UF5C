package br.ufsc.inf.tcc.simlist.common;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import br.ufsc.inf.tcc.simlist.SimList;
import br.ufsc.inf.tcc.simlist.SimList.Metric;
import br.ufsc.inf.tcc.simlist.data.Document;
import junit.framework.TestCase;

public abstract class SimilarityTestCase extends TestCase {

	public SimList simList = new SimList(0.3f, 0.7f, true);
	List<String> similar = new ArrayList<>();
	private Float[][] results;
	private Float[] statistics;
	private Float totalComparation;

	public void testSimilarity(Document d1, Document d2, Metric m) {
		Float score = this.simList.analyse(d1, d2, m, m);
		SimilarityAssertion.assertIsClose(score);
	}

	public void testDissimilarity(Document d1, Document d2, Metric m) {
		Float score = this.simList.analyse(d1, d2, m, m);
		SimilarityAssertion.assertIsNotClose(score);
	}

	public void testSimilarity(File f1, File f2, Metric m) {
		this.testSimilarity(f1, f2, m, m);
	}

	public void testSimilarity(File f1, File f2, Metric mEp, Metric mSl) {
		Float score = 0.0f;
		try {
			score = this.simList.analyse(f1, f2, mEp, mSl);
			SimilarityAssertion.assertIsClose(score);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testDissimilarity(File f1, File f2, Metric m) {
		this.testDissimilarity(f1, f2, m, m);
	}

	public void testDissimilarity(File f1, File f2, Metric mEp, Metric mSl) {
		Float score = 0.0f;
		try {
			score = this.simList.analyse(f1, f2, mEp, mSl);
			SimilarityAssertion.assertIsNotClose(score);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Float[][] clusterTest(List<File> baseDocs, Metric m, Boolean printResult) {
		return this.clusterTest(baseDocs, m, m, printResult);
	}

	public Float[][] clusterTest(List<File> baseDocs, Metric mEp, Metric mSl, Boolean printResult) {
		this.results = new Float[baseDocs.size()][baseDocs.size()];
		this.similar.clear();

		for (int i = 0; i < this.results.length; i++) {
			for (int j = i; j < this.results[0].length; j++) {
				if (i == j) {
					this.results[i][j] = 1f;
				} else {
					try {
						this.results[i][j] = this.simList.analyse(baseDocs.get(i), baseDocs.get(j), mEp, mSl);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

		if (printResult) {
			this.printMResult(this.results);
		}

		this.totalComparation = this.getTotalComparation(this.results.length);
		return this.results;

	}

	public void printMResult(Float[][] result) {

		this.print("\n");
		for (int i = 0; i <= result.length; i++) {
			for (int j = 0; j <= result[0].length; j++) {
				if (i == 0 && j == 0) {
					this.print("-----");
				} else if (i == 0) {
					this.print("A" + j);
				} else if (j == 0) {
					this.print("A" + i);
				} else {
					if (result[i - 1][j - 1] == null) {
						this.print("-----");
					} else {
						this.print(result[i - 1][j - 1].toString());
					}
				}
			}
			this.print("\n");
		}
	}

	private void print(String s) {
		System.out.printf("%12s", s);
	}

	public void addSimilarity(String relation) {
		this.similar.add(relation);
	}

	public void addSameList(int qt) {
		for (int i = 1; i <= qt; i++) {
			this.similar.add("L" + i + "L" + i);
		}
	}

	public Float[] calculeStatistics(Float threshold, boolean printStatistics) {
		Float FP = 0f, FN = 0f, TP = 0f, TN = 0f;
		Float p, r, f;

		List<String> simResult = this.lookSimilarity(threshold);
		List<String> tempSimilar = new ArrayList<>();
		tempSimilar.addAll(this.similar);

		for (String sim : simResult) {
			if (this.similar.contains(sim)) {
				TP++;
				tempSimilar.remove(sim);
			} else {
				FP++;
			}
		}

		FN = new Float(tempSimilar.size());

		TN = this.totalComparation - FP - FN - TP;

		p = TP / (TP + FP);
		r = TP / (TP + FN);
		f = 2 * (p * r) / (p + r);

		this.statistics = new Float[] { FN, TP, FP, TN, p, r, f };

		if (printStatistics) {
			this.printStatistics();
		}
		return this.statistics;
	}

	private Float getTotalComparation(int number) {
		if (number == 1) {
			return 1f;
		}
		return number + this.getTotalComparation(number - 1);
	}

	private List<String> lookSimilarity(Float threshold) {
		List<String> listSim = new ArrayList<>();
		for (int i = 0; i < this.results.length; i++) {
			for (int j = i; j < this.results[0].length; j++) {
				if (this.results[i][j] >= threshold) {
					listSim.add("L" + (i + 1) + "L" + (j + 1));
				}
			}
		}

		return listSim;
	}

	private void printStatistics() {
		System.out.printf("\n%12s%12s%12s%12s%12s%12s%12s\n", "FN", "TP", "FP", "TN", "Precision", "Recall", "F-Measure");
		for (int i = 0; i < this.statistics.length; i++) {
			System.out.printf("%12f", this.statistics[i]);
		}
		System.out.println();
	}

}
