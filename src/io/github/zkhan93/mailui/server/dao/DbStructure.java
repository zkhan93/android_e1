package io.github.zkhan93.mailui.server.dao;

public interface DbStructure {
	public interface LOGIN_TABLE {
		String COLUMN_USERNAME = "username";
		String COLUMN_PASSWORD = "password";
		String COLUMN_EMAIL = "email";
		String COLUMN_ID = "id";
	}

	public interface MAIL_TABLE {
		String COLUMN_ID = "id";
		String COLUMN_SUBJECT = "subject";
		String COLUMN_BODY = "body";
		String COLUMN_RECEIVER = "receiver";
		String COLUMN_SENDER = "sender";
		String COLUMN_TIME = "time";
	}
}
