package io.github.zkhan93.mailui.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	/**
	 * get database connection
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DbContants.DB_DRIVER);
			conn = DriverManager.getConnection(DbContants.DB_URL
					+ DbContants.DB_NAME, DbContants.DB_USERNAME,
					DbContants.DB_PASSWORD);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}

	/**
	 * close the connection
	 * 
	 * @param conn
	 */
	public static void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	
}
