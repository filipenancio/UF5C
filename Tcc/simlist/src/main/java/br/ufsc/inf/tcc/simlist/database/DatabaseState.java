package br.ufsc.inf.tcc.simlist.database;

import static br.ufsc.inf.tcc.simlist.database.model.meta.QTbConfigSistema.tbConfigSistema;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysema.query.sql.SQLQuery;

import br.ufsc.inf.tcc.simlist.database.sql.SQLContext;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DatabaseState {

	private static String VERSAODATABASE = "VERSAOBASEDADOS";
	private Long version;

	public static DatabaseState extractState(H2Database db) throws ClassNotFoundException, SQLException {

		return new DatabaseState(db);
	}

	private DatabaseState(H2Database db) throws ClassNotFoundException, SQLException {
		Connection con = db.getActiveConnection();
		this.version = this.getVersion(con);

	}

	public Long getVersion() {
		return this.version;
	}

	private Long getVersion(Connection con) {

		SQLQuery select = SQLContext.newQuery(con);
		String version = select.from(tbConfigSistema).where(tbConfigSistema.dsConfig.like(VERSAODATABASE))
		      .uniqueResult(tbConfigSistema.vlConfig);
		return Long.parseLong(version.replace(".", ""));
	}
}
