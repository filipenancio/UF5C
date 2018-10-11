package br.ufsc.inf.tcc.simlist.example;

import java.io.File;

import org.junit.Test;

import br.ufsc.inf.tcc.simlist.SimList.Metric;
import br.ufsc.inf.tcc.simlist.common.SimilarityTestCase;

public class Test1 extends SimilarityTestCase {

	@Test
	public void testExample1SameList() {
		File f1 = new File(".\\src\\test\\resource\\teste1\\listTest_001a.xml");

		this.testSimilarity(f1, f1, Metric.COSINE_SIMILARITY);
	}

	@Test
	public void testExample1Complete() {
		File f1 = new File(".\\src\\test\\resource\\teste1\\listTest_001a.xml");
		File f2 = new File(".\\src\\test\\resource\\teste1\\listTest_001b.xml");

		this.testSimilarity(f1, f2, Metric.COSINE_SIMILARITY);
	}

}
