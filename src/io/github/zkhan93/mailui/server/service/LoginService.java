package io.github.zkhan93.mailui.server.service;

import io.github.zkhan93.mailui.server.dao.LoginDao;

public class LoginService {

	public LoginService() {
		// TODO Auto-generated constructor stub
	}

	public boolean checkUser(String username, String password) {
		return new LoginDao().checkUser(username, password);
	}
}
