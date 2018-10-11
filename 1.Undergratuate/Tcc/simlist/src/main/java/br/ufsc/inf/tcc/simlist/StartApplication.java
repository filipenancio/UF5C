package br.ufsc.inf.tcc.simlist;

import java.io.File;

import br.ufsc.inf.tcc.simlist.business.analizer.AnalyserList;
import br.ufsc.inf.tcc.simlist.business.analizer.AnalyserSimilarityMetricEnum;
import br.ufsc.inf.tcc.simlist.business.loader.xml.LoaderListXml;
import br.ufsc.inf.tcc.simlist.database.H2Database;
import br.ufsc.inf.tcc.simlist.database.updater.MigratorDataBase;
import lombok.NoArgsConstructor;

public class StartApplication {

	// public static void main(String[] args) {
	// final Logger log = LoggerFactory.getLogger(StartApplication.class);
	// new StartApplication().init(log);
	// }
	//
	// private void init(Logger log) {
	// log.info("Iniciando aplicação ...");
	//
	// // TODO Auto-generated method stub
	//
	// }

	public static void main(String[] args) throws Exception {
		// log.info("Iniciando aplicação...");
		new StartApplication().init(args);
	}

	private void init(String[] args) throws Exception {

		ConfigApplication config = ConfigApplication.tryExtractArgsToConfig(args);
		config.checkDatabase();

		MigratorDataBase migrator = new MigratorDataBase();
		migrator.checkDatabase(H2Database.getInstance());

		LoaderListXml listLoader = new LoaderListXml(config.getLoaderConfig());
		listLoader.load();

		AnalyserList listAnalyser = new AnalyserList(config.getAnalyserConfig());
		listAnalyser.analyse();
	}

	@NoArgsConstructor
	private static class ConfigApplication {

		private final char sC = File.separatorChar;

		private String databaseName = "simList";
		private String databasePath = "." + this.sC + "data";
		private String databaseUser = "sa";
		private String databasePassword = "sa";

		private String loaderPath = "." + this.sC + "listas";
		private AnalyserSimilarityMetricEnum analyserMetric = AnalyserSimilarityMetricEnum.COSINE_SIMILARITY;

		protected static ConfigApplication tryExtractArgsToConfig(String[] args) {

			if (args.length < 1) {
				return new ConfigApplication();
			}
			// TODO - Carregar arquivo de configurações
			return null;
		}

		protected void checkDatabase() throws Exception {

			H2Database.configureConnection(this.databaseName, this.databasePath,
			      this.databaseUser, this.databasePassword);
			H2Database db = H2Database.getInstance();
			db.validateConnection();
		}

		protected String getLoaderConfig() {
			return this.loaderPath;
		}

		protected AnalyserSimilarityMetricEnum getAnalyserConfig() {

			return this.analyserMetric;
		}
	}

}
