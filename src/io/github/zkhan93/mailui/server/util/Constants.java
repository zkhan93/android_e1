package io.github.zkhan93.mailui.server.util;

public interface Constants {
	interface JSON_KEYS {
		String USERNAME = "username";
		String ERROR = "error";
		String PASSWORD = "password";
		String AUTHENTICATION = "authentication";
		String CONTACTS = "contacts";
		String MSG = "msg";
		String MAILS = "mails";

		interface Contacts {
			String ID = "id";
			String NAME = "name";
			String NUMBER = "number";
		}

		interface PARAMS_KEYS {
			String USERNAME = "username";
			String PASSWORD = "password";
		}

		interface Mail {
			String ID = "id";
			String SUBJECT = "subject";
			String BODY = "body";
			String SENDER = "sender";
			String RECEIVER = "receiver";
			String TIME = "time";
		}

		interface User {
			String ID = "id";
			String USERNAME = "username";
			String EMAIL = "email";
		}
	}
}
