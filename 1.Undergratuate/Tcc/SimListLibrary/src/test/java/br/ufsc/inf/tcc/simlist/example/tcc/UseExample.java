package br.ufsc.inf.tcc.simlist.example.tcc;

import java.io.File;

import org.junit.Test;

import br.ufsc.inf.tcc.simlist.SimList.Metric;
import br.ufsc.inf.tcc.simlist.common.SimilarityTestCase;

public class UseExample extends SimilarityTestCase {

	File f1 = new File(".\\src\\test\\resource\\example\\tcc\\listTestA.xml");
	File f2 = new File(".\\src\\test\\resource\\example\\tcc\\listTestB.xml");

	@Test
	public void testUseExampleCosine() {
		this.testDissimilarity(this.f1, this.f2, Metric.COSINE_SIMILARITY);
	}

	@Test
	public void testUseExampleEuclidian() {
		this.testDissimilarity(this.f1, this.f2, Metric.EUCLIDIAN_SIMILARITY);
	}

	@Test
	public void testUseExampleJaccard() {
		this.testDissimilarity(this.f1, this.f2, Metric.JACCARD_SIMILARITY);
	}

}
