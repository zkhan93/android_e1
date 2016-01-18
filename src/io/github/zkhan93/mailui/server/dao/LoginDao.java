package io.github.zkhan93.mailui.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao {

	public LoginDao() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * check username and password against stored data in database
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean checkUser(String username, String password) {
		Connection conn = DbConnection.getConnection();
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
			DbConnection.closeConnection(conn);
		}
		return false;
	}
}
