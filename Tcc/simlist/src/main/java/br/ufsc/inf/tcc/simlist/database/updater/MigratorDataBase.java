package br.ufsc.inf.tcc.simlist.database.updater;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ufsc.inf.tcc.simlist.database.DatabaseState;
import br.ufsc.inf.tcc.simlist.database.H2Database;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.FileSystemResourceAccessor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MigratorDataBase {

	private final static char sC = File.separatorChar;
	private static final String PATH_CHANGESETS = "." + sC + "src" + sC + "resource" + sC + "br" + sC + "ufsc" + sC
	      + "inf" + sC + "tcc" + sC + "simlist" + sC
	      + "database" + sC + "updater" + sC;
	private static Long ACTUAL_VERSION = 1000l;

	public void checkDatabase(H2Database db) throws Exception {
		DatabaseState dbState = null;
		try {
			dbState = DatabaseState.extractState(db);
		} catch (Exception e) {
			// log.info("Base de dados não encontrada.");
		} finally {
			if (dbState == null) {
				// log.info("Criando nova base de dados.");
				this.migrate(db);
				// log.info("Base de dados criada com sucesso.");
				dbState = DatabaseState.extractState(db);
			}
			// log.info("Verificando versão base de dados...");
			if (dbState.getVersion().compareTo(ACTUAL_VERSION) < 0) {
				// log.info("Iniciando atualização da base de dados...");
				this.migrate(db);
				// log.info("Base de dados atualizada com sucesso.");
			} else if (dbState.getVersion().compareTo(ACTUAL_VERSION) > 0) {
				// log.info("Erro: Base de dados incompatível!");
				throw new Exception("Foi verificado que a base corresponde a uma versão mais atual que a do sistema.");
			} else {
				// log.info("Base de dados pronta para uso.");
			}
		}

	}

	private void migrate(H2Database db) throws ClassNotFoundException, LiquibaseException, SQLException {
		Connection con = null;
		Liquibase liquibase = null;
		ArrayList<String> listOfVersion = this.getListOfVersions();

		try {
			con = db.getActiveConnection();
			Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(con));
			database.setDatabaseChangeLogTableName("TB_MIGRACAO");
			database.setDatabaseChangeLogLockTableName("TB_MIGRACAO_TRAVA");
			database.setAutoCommit(false);

			for (String version : listOfVersion) {
				liquibase = new Liquibase(version, new FileSystemResourceAccessor(), database);
				liquibase.update("");
			}

		} catch (SQLException e) {
			throw new DatabaseException(e);
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}

	private ArrayList<String> getListOfVersions() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(PATH_CHANGESETS + "v1000" + File.separatorChar + "ChangeMaster-v1000.xml");

		return list;
	}

}
