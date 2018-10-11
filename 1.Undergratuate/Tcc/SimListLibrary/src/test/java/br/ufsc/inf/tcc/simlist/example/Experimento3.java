package br.ufsc.inf.tcc.simlist.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.ufsc.inf.tcc.simlist.SimList;
import br.ufsc.inf.tcc.simlist.SimList.Metric;
import br.ufsc.inf.tcc.simlist.common.SimilarityTestCase;

public class Experimento3 extends SimilarityTestCase {

	private void testCluster(float pesoEp, float pesoSl, Metric m1, Metric m2) {

		List<File> list = new ArrayList<>();
		{
			list.add(new File(".\\src\\test\\resource\\teste4\\list-001.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-002.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-003.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-004.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-005.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-006.xml"));
		}

		{
			list.add(new File(".\\src\\test\\resource\\teste4\\list-007.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-008.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-009.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-010.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-011.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-012.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-013.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-014.xml"));
		}

		{
			list.add(new File(".\\src\\test\\resource\\teste4\\list-015.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-016.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-017.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-018.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-019.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-020.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-021.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-022.xml"));
		}
		{
			list.add(new File(".\\src\\test\\resource\\teste4\\list-023.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-024.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-025.xml"));
			list.add(new File(".\\src\\test\\resource\\teste4\\list-026.xml"));
		}
		{
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
		}

		this.simList = new SimList(pesoEp, pesoSl, false);
		this.clusterTest(list, m1, m2, false);

		{
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
		}
		{
			this.addSimilarity("L7L11");
			this.addSimilarity("L8L10");
			this.addSimilarity("L8L11");
			this.addSimilarity("L8L12");
			this.addSimilarity("L8L13");
			this.addSimilarity("L9L11");
			this.addSimilarity("L9L14");
			this.addSimilarity("L10L11");
			this.addSimilarity("L10L13");
			this.addSimilarity("L10L14");
			this.addSimilarity("L11L12");
			this.addSimilarity("L11L14");
			this.addSimilarity("L12L13");
			this.addSimilarity("L13L14");
		}
		{
			this.addSimilarity("L15L16");
			this.addSimilarity("L15L18");
			this.addSimilarity("L15L19");
			this.addSimilarity("L15L20");
			this.addSimilarity("L16L20");
			this.addSimilarity("L17L18");
			this.addSimilarity("L17L20");
			this.addSimilarity("L18L19");
			this.addSimilarity("L18L20");
			this.addSimilarity("L21L22");
		}
		{
			this.addSimilarity("L23L25");
			this.addSimilarity("L23L26");
			this.addSimilarity("L24L25");
			this.addSimilarity("L24L26");
			this.addSimilarity("L25L26");
		}
		{
			this.addSimilarity("L31L36");
			this.addSimilarity("L33L34");
		}

		this.addSameList(list.size());

		this.calculeStatistics(0.7f, true);
	}

	@Test
	public void testEuclidian() {
		this.testCluster(0.3f, 0.7f, Metric.COSINE_SIMILARITY,
		      Metric.EUCLIDIAN_SIMILARITY);
		this.testCluster(0.3f, 0.7f, Metric.EUCLIDIAN_SIMILARITY,
		      Metric.EUCLIDIAN_SIMILARITY);
		this.testCluster(0.3f, 0.7f, Metric.JACCARD_SIMILARITY,
		      Metric.EUCLIDIAN_SIMILARITY);
	}

}
