package io.github.zkhan93.mailui.server.model;

import io.github.zkhan93.mailui.server.util.Constants;

import org.json.JSONObject;

public class User {
	private int id;
	private String username;
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public JSONObject getJSON(){
		JSONObject self=new JSONObject();
		self.put(Constants.JSON_KEYS, .)
	}
}
