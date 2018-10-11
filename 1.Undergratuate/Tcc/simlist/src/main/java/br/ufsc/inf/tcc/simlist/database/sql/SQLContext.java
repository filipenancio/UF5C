package br.ufsc.inf.tcc.simlist.database.sql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.inject.Provider;

import br.ufsc.inf.tcc.simlist.database.H2Database;

import com.mysema.query.QueryFactory;
import com.mysema.query.sql.Configuration;
import com.mysema.query.sql.H2Templates;
import com.mysema.query.sql.RelationalPath;
import com.mysema.query.sql.SQLQuery;
import com.mysema.query.sql.SQLQueryFactory;
import com.mysema.query.sql.SQLSubQuery;
import com.mysema.query.sql.SQLTemplates;
import com.mysema.query.sql.dml.SQLDeleteClause;
import com.mysema.query.sql.dml.SQLInsertClause;
import com.mysema.query.sql.dml.SQLUpdateClause;

public class SQLContext implements QueryFactory<SQLQuery, SQLSubQuery> {

	private SQLTemplates template;
	private SQLQueryFactory factory;

	public SQLContext() {
		template = H2Templates.DEFAULT;
		Configuration configuration = new Configuration(template);
		factory = new SQLQueryFactory(configuration, new Provider<Connection>() {

			public Connection get() {
				Connection con = null;
				try {
					con = H2Database.getInstance().getActiveConnection();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return con;
			}
		});
	}

	public static SQLQuery newQuery(Connection con) {
		return new SQLQuery(con, H2Templates.DEFAULT);
	}

	public static SQLQuery newQuery() throws ClassNotFoundException, SQLException, Exception {
		return new SQLQuery(H2Database.getInstance().getActiveConnection(), H2Templates.DEFAULT);
	}

	public SQLInsertClause insert(RelationalPath<?> entity) {
		return factory.insert(entity);
	}

	public SQLUpdateClause update(RelationalPath<?> entity) {
		return factory.update(entity);
	}

	public SQLDeleteClause delete(RelationalPath<?> entity) {
		return factory.delete(entity);
	}

	public SQLQuery query() {
		return factory.query();
	}

	public SQLSubQuery subQuery() {
		return factory.subQuery();
	}

}
