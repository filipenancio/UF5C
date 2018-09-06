package br.ufsc.inf.tcc.simlist.business.analizer;

import static br.ufsc.inf.tcc.simlist.database.model.meta.QTbLista.tbLista;
import static br.ufsc.inf.tcc.simlist.database.model.meta.QTbSimilaridade.tbSimilaridade;
import static br.ufsc.inf.tcc.simlist.database.model.meta.QTbToken.tbToken;

import java.util.List;

import com.mysema.query.Tuple;

import br.ufsc.inf.tcc.simlist.database.sql.SQLContext;
import br.ufsc.inf.tcc.simlist.database.sql.SettingsInsert;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnalyserList {

	private AnalyserSimilarityMetricEnum analyserMetric;;

	public AnalyserList(AnalyserSimilarityMetricEnum analyserSimilarityMetricEnum) {

		this.analyserMetric = analyserSimilarityMetricEnum;
	}

	public void analyse() throws Exception {

		// log.info("Iniciando análise...");
		// TODO - análise

		List<Long> idListsToAnalyse = SQLContext.newQuery().from(tbLista).where(tbLista.stAnalise.eq(false))
		      .list(tbLista.coIdLista);

		for (Long idListToAnalyse : idListsToAnalyse) {

			List<Tuple> tokensToAnalyse = SQLContext.newQuery().from(tbToken)
			      .where(tbToken.coLista.eq(idListToAnalyse).and(tbToken.coTokenPai.isNull())).list();
			List<Tuple> allTokensPai = SQLContext.newQuery().from(tbToken).where(tbToken.coTokenPai.isNull()).list();

			this.analyseTokensPai(tokensToAnalyse, allTokensPai);

		}
	}

	private void analyseTokensPai(List<Tuple> tokensToAnalyse, List<Tuple> allTokensPai) {

		for (Tuple tuple : tokensToAnalyse) {
			for (Tuple tupleRef : allTokensPai) {
				// String str1 = "This is a sentence. It is made of words";
				// String str2 = "This sentence is similar. It has almost the same
				// words";
				//
				// StringMetric metric = StringMetrics.cosineSimilarity();
				//
				// float result = metric.compare(str1, str2); //0.4767
			}
		}

		SettingsInsert settingsInsert = new SettingsInsert().table(tbSimilaridade)
		      .value(tbSimilaridade.coTecnicaSimilaridade, this.analyserMetric.getValue());

	}

}
