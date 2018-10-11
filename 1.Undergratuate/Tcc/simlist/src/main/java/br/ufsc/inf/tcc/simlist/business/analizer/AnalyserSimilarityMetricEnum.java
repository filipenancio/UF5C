package br.ufsc.inf.tcc.simlist.business.analizer;

public enum AnalyserSimilarityMetricEnum {

	//@formatter:off
	COSINE_SIMILARITY(1l), 
	LEVENSHTEIN_SIMILARITY(2l), 
	JARO_SIMILARITY(3l), 
	HAMMING_SIMILARITY(4l), 
	JACCARD_SIMILARITY(5l);
	//@formatter:on

	private Long value;

	private AnalyserSimilarityMetricEnum(Long value) {
		this.value = value;
	}

	public Long getValue() {

		return value;
	}

}
