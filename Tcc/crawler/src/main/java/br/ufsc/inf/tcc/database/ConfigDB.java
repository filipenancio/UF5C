package br.ufsc.inf.tcc.database;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ConfigDB {

	public static final String className = "org.postgresql.Driver";
	public static final String connectionUrl = "jdbc:postgresql://localhost:5432/swl";
	public static final String login = "postgres";
	public static final String password = "swl";

}
