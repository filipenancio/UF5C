package br.ufsc.inf.tcc.simlist.common;

import org.junit.Assert;

public class SimilarityAssertion {

	private static Float baseScoreToSimilar = 0.7f;

	public static void assertIsClose(Float maxScore, Float realScore) {
		Float dif = (maxScore - realScore);
		Float limitOfDissimilarity = 1.0f - baseScoreToSimilar;
		Assert.assertTrue(dif <= limitOfDissimilarity);
	}

	public static void assertIsClose(Float realScore) {
		assertIsClose(1.0f, realScore);
	}

	public static void assertIsNotClose(Float maxScore, Float realScore) {
		Float dif = (maxScore - realScore);
		Float limitOfDissimilarity = 1.0f - baseScoreToSimilar;
		Assert.assertTrue(dif > limitOfDissimilarity);
	}

	public static void assertIsNotClose(Float realScore) {
		assertIsNotClose(1.0f, realScore);
	}
}
