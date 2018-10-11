package br.ufsc.inf.tcc.simlist.functional;

import java.util.Arrays;

import org.junit.Test;

import br.ufsc.inf.tcc.simlist.SimList.Metric;
import br.ufsc.inf.tcc.simlist.common.SimilarityTestCase;
import br.ufsc.inf.tcc.simlist.data.Document;
import br.ufsc.inf.tcc.simlist.data.Line;
import br.ufsc.inf.tcc.simlist.data.ListOf;

public class ElementListTest extends SimilarityTestCase {

	@Test
	public void testJaccard() {
		this.testLittleDiference(Metric.JACCARD_SIMILARITY);
		this.testSubList(Metric.JACCARD_SIMILARITY);
	}

	@Test
	public void testEuclidian() {
		this.testLittleDiference(Metric.EUCLIDIAN_SIMILARITY);
		this.testSubList(Metric.EUCLIDIAN_SIMILARITY);
	}

	@Test
	public void testCosine() {
		this.testLittleDiference(Metric.COSINE_SIMILARITY);
		this.testSubList(Metric.COSINE_SIMILARITY);
	}

	private void testLittleDiference(Metric metric) {
		Document doc1 = new Document(null, null, null,
		      new ListOf("A good title's list",
		            Arrays.asList(
		                  new Line(Arrays.asList("Number: 1", "City: Florianópolis", "Citizens: 400.000")),
		                  new Line(Arrays.asList("Number: 2", "City: São Paulo", "Citizens: 11.400.000")))));

		Document doc2 = new Document(null, null, null,
		      new ListOf("A good title's list",
		            Arrays.asList(
		                  new Line(Arrays.asList("Number: 1", "City: São Paulo", "Citizens: 11.400.000")),
		                  new Line(Arrays.asList("Number: 2", "City: Floripa", "Citizens: 400.000")))));

		this.testSimilarity(doc1, doc2, metric);
	}

	private void testSubList(Metric metric) {
		Document doc1 = new Document(null, null, null,
		      new ListOf("A good title's list",
		            Arrays.asList(
		                  new Line(Arrays.asList("Number: 1", "City: São Paulo", "Citizens: 11.400.000")))));

		Document doc2 = new Document(null, null, null,
		      new ListOf("A good title's list",
		            Arrays.asList(
		                  new Line(Arrays.asList("Number: 1", "City: São Paulo", "Citizens: 11.400.000")),
		                  new Line(Arrays.asList("Number: 2", "City: Floripa", "Citizens: 400.000")))));

		this.testSimilarity(doc1, doc2, metric);
	}

}
