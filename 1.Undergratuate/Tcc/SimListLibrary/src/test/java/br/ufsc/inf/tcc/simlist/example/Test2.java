package br.ufsc.inf.tcc.simlist.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.ufsc.inf.tcc.simlist.SimList;
import br.ufsc.inf.tcc.simlist.SimList.Metric;
import br.ufsc.inf.tcc.simlist.common.SimilarityTestCase;

public class Test2 extends SimilarityTestCase {

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
		list.add(new File(".\\src\\test\\resource\\teste2\\listTest_001.xml"));
		list.add(new File(".\\src\\test\\resource\\teste2\\listTest_002.xml"));
		list.add(new File(".\\src\\test\\resource\\teste2\\listTest_003.xml"));
		list.add(new File(".\\src\\test\\resource\\teste2\\listTest_004.xml"));
		list.add(new File(".\\src\\test\\resource\\teste2\\listTest_005.xml"));

		list.add(new File(".\\src\\test\\resource\\teste2\\listTest_006.xml"));
		list.add(new File(".\\src\\test\\resource\\teste2\\listTest_007.xml"));
		list.add(new File(".\\src\\test\\resource\\teste2\\listTest_008.xml"));
		list.add(new File(".\\src\\test\\resource\\teste2\\listTest_009.xml"));
		list.add(new File(".\\src\\test\\resource\\teste2\\listTest_010.xml"));

		this.simList = new SimList(pesoEp, pesoSl, false);
		this.clusterTest(list, m, m, false);

		this.addSimilarity("L5L10");
		this.addSimilarity("L7L8");
		this.addSameList(list.size());

		this.calculeStatistics(0.7f, true);
	}

}
