package br.ufsc.inf.tcc.simlist.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.ufsc.inf.tcc.simlist.SimList;
import br.ufsc.inf.tcc.simlist.SimList.Metric;
import br.ufsc.inf.tcc.simlist.common.SimilarityTestCase;

public class Experimento1 extends SimilarityTestCase {

	@Test
	public void testCosine() {
		this.testCluster(0.2f, 0.8f, Metric.COSINE_SIMILARITY);
		this.testCluster(0.3f, 0.7f, Metric.COSINE_SIMILARITY);
		this.testCluster(0.4f, 0.6f, Metric.COSINE_SIMILARITY);
	}

	@Test
	public void testEuclidian() {
		this.testCluster(0.2f, 0.8f, Metric.EUCLIDIAN_SIMILARITY);
		this.testCluster(0.3f, 0.7f, Metric.EUCLIDIAN_SIMILARITY);
		this.testCluster(0.4f, 0.6f, Metric.EUCLIDIAN_SIMILARITY);
	}

	@Test
	public void testJaccard() {
		this.testCluster(0.2f, 0.8f, Metric.JACCARD_SIMILARITY);
		this.testCluster(0.3f, 0.7f, Metric.JACCARD_SIMILARITY);
		this.testCluster(0.4f, 0.6f, Metric.JACCARD_SIMILARITY);
	}

	private void testCluster(float pesoEp, float pesoSl, Metric m) {

		List<File> list = new ArrayList<>();
		list.add(new File(".\\src\\test\\resource\\teste3\\list-001.xml"));
		list.add(new File(".\\src\\test\\resource\\teste3\\list-002.xml"));
		list.add(new File(".\\src\\test\\resource\\teste3\\list-003.xml"));
		list.add(new File(".\\src\\test\\resource\\teste3\\list-004.xml"));
		list.add(new File(".\\src\\test\\resource\\teste3\\list-005.xml"));

		list.add(new File(".\\src\\test\\resource\\teste3\\list-006.xml"));

		this.simList = new SimList(pesoEp, pesoSl, false);
		this.clusterTest(list, m, m, false);

		this.addSimilarity("L1L2");
		this.addSimilarity("L1L3");
		this.addSimilarity("L1L4");
		this.addSimilarity("L1L5");
		this.addSimilarity("L1L6");
		this.addSimilarity("L2L3");
		this.addSimilarity("L2L4");
		this.addSimilarity("L2L5");
		this.addSimilarity("L2L6");
		this.addSimilarity("L3L6");
		this.addSimilarity("L4L5");

		this.addSameList(list.size());

		this.calculeStatistics(0.7f, true);
	}

}
