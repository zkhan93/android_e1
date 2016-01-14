package io.github.zkhan93.contactpage.server.dao;

import io.github.zkhan93.contactpage.server.model.Contact;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContactDao {

	public ContactDao() {
		// TODO Auto-generated constructor stub
	}
	public List<Contact> getContacts() {
		List<Contact> contacts = new ArrayList<>();
		Connection conn = DbConnection.getConnection();
		try {
			String sql = "select * from contact";
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			Contact contact = null;
			while (rs.next()) {
				contact = new Contact(rs.getLong(1), rs.getString(2),rs.getString(3));
				contacts.add(contact);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			DbConnection.closeConnection(conn);
		}
		return contacts;
	}
}
