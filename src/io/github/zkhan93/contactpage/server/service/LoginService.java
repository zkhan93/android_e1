package io.github.zkhan93.contactpage.server.service;

import io.github.zkhan93.contactpage.server.dao.LoginDao;

public class LoginService {

	public LoginService() {
		// TODO Auto-generated constructor stub
	}

	public boolean checkUser(String username, String password) {
		return new LoginDao().checkUser(username, password);
	}
}
