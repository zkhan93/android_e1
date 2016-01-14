package io.github.zkhan93.contactpage.server.dao;

public interface DbStructure {
	public interface LOGIN_TABLE{
		String COLUMN_USERNAME="username";
		String COLUMN_PASSWORD="password";
	}
	public interface CONTACT_TABLE{
		String COLUMN_ID="id";
		String COLUMN_NAME="name";
		String COLUMN_NUMBER="number";
	}
}
