package io.github.zkhan93.mailui.server.dao;

import io.github.zkhan93.mailui.server.model.Mail;
import io.github.zkhan93.mailui.server.model.User;
import io.github.zkhan93.mailui.server.util.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MailDao {

	public MailDao() {

	}

	public ArrayList<Mail> getMails(String username) {
		ArrayList<Mail> mails = new ArrayList<Mail>();
		Connection conn = null;
		PreparedStatement pstm;
		ResultSet rs = null;
		String sql = "select * from login where username=?";
		try {
			conn = DbConnection.getConnection();
			;
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, username.trim());
			rs = pstm.executeQuery();
			User receiver;
			if (rs.next()) {
				receiver = new User();
				receiver.setId(rs.getInt(Constants.JSON_KEYS.User.ID));
				receiver.setEmail(rs.getString(Constants.JSON_KEYS.User.EMAIL));
				receiver.setUsername(username);
			} else {
				return mails;
			}
			rs.close();
			sql = "select * from mail join login on mail.sender=login.id where mail.receiver=?";
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, receiver.getId());
			rs = pstm.executeQuery();
			Mail mail;
			User sender;
			while (rs.next()) {
				mail = new Mail();
				mail.setId(rs.getInt(DbStructure.MAIL_TABLE.COLUMN_ID));
				mail.setBody(rs.getString(DbStructure.MAIL_TABLE.COLUMN_BODY));
				mail.setSubject(rs
						.getString(DbStructure.MAIL_TABLE.COLUMN_SUBJECT));
				mail.setTime(rs.getDate(DbStructure.MAIL_TABLE.COLUMN_TIME).getTime());
				sender = new User();
				sender.setId(rs.getInt(DbStructure.LOGIN_TABLE.COLUMN_ID));
				sender.setUsername(rs
						.getString(DbStructure.LOGIN_TABLE.COLUMN_USERNAME));
				sender.setEmail(rs
						.getString(DbStructure.LOGIN_TABLE.COLUMN_EMAIL));
				mail.setSender(sender);
				mail.setReceiver(receiver);
				mails.add(mail);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {

			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DbConnection.closeConnection(conn);
		}
		return mails;
	}
}
