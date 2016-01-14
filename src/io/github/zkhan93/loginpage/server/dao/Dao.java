package io.github.zkhan93.loginpage.server.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Dao {

	/**
	 * get database connection
	 * 
	 * @return
	 */
	public Connection getConnection() {
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
	public void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * check username and password against stored data in database
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean checkUser(String username, String password) {
		Connection conn = getConnection();
		String pass = null;
		String sql = "select password from login where username=?";
		try {
			PreparedStatement pStm = conn.prepareStatement(sql);
			pStm.setString(1, username);
			ResultSet rs = pStm.executeQuery();
			if (rs.next()) {
				pass = rs.getString(1);
			}
			return pass != null && pass.equalsIgnoreCase(password);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(conn);
		}
		return false;
	}
}
