package br.ufsc.inf.tcc.simlist.functional;

import java.util.Arrays;

import org.junit.Test;

import br.ufsc.inf.tcc.simlist.SimList.Metric;
import br.ufsc.inf.tcc.simlist.common.SimilarityTestCase;
import br.ufsc.inf.tcc.simlist.data.Document;
import br.ufsc.inf.tcc.simlist.data.Line;
import br.ufsc.inf.tcc.simlist.data.ListOf;
import br.ufsc.inf.tcc.simlist.data.Text;

public class ScopeTest extends SimilarityTestCase {

	Document doc1 = new Document("ww.euclides.com/olamundo/lista1", "title",
	      new Text("One paragraph is necessary for test! So i wrote some words and whatever."), null);
	Document doc2 = new Document("www.euclides.com/olamundo/lista1", "title",
	      new Text("One paragraph is necessary for test! So i wrote some words and whatever."), null);
	Document doc3 = new Document(null, null, null,
	      new ListOf("A good title's list",
	            Arrays.asList(
	                  new Line(Arrays.asList("Number: 1", "City: Florianópolis", "Citizens: 400.000")),
	                  new Line(Arrays.asList("Number: 2", "City: São Paulo", "Citizens: 11.400.000")))));

	@Test
	public void testOrder1() {
		this.testSimilarity(this.doc1, this.doc2, Metric.EUCLIDIAN_SIMILARITY);
	}

	@Test
	public void testOrder2() {
		this.testSimilarity(this.doc2, this.doc1, Metric.EUCLIDIAN_SIMILARITY);
	}

	@Test
	public void testFault1() {
		this.testDissimilarity(this.doc3, this.doc1, Metric.EUCLIDIAN_SIMILARITY);
	}

}
