package br.ufsc.inf.tcc.simlist.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Database {

	private static H2Database database;

	private String databaseName;
	private String databasePath;
	private String databaseUser;
	private String databasePassword;

	private H2Database(String databaseName, String databasePath, String databaseUser, String databasePassword) {
		this.databaseName = databaseName;
		this.databasePath = databasePath;
		this.databaseUser = databaseUser;
		this.databasePassword = databasePassword;
	}

	public static H2Database getInstance() throws Exception {
		if (database == null) {
			throw new Exception("Banco de dados não configurado!");
		}
		return database;
	}

	public static void configureConnection(String databaseName, String databasePath, String databaseUser, String databasePassword) {
		database = new H2Database(databaseName, databasePath, databaseUser, databasePassword);
	}

	public Connection getActiveConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.h2.Driver");
		return DriverManager.getConnection("jdbc:h2:" + databasePath + File.separatorChar + databaseName + ";MV_STORE=FALSE", databaseUser, databasePassword);
	}

	public void validateConnection() throws Exception {
		Connection connection = H2Database.getInstance().getActiveConnection();
		connection.close();
	}

}
