package io.github.zkhan93.mailui.server.model;

import io.github.zkhan93.mailui.server.util.Constants;

import org.json.JSONException;
import org.json.JSONObject;

public class Mail {
	private int id;
	private String subject, body;
	private User sender, receiver;
	private long time;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public JSONObject getJSON() {
		JSONObject self = new JSONObject();
		try {
			self.put(Constants.JSON_KEYS.Mail.ID, getId());
			self.put(Constants.JSON_KEYS.Mail.BODY, getBody());
			self.put(Constants.JSON_KEYS.Mail.SUBJECT, getSubject());
			self.put(Constants.JSON_KEYS.Mail.TIME, getTime());
			self.put(Constants.JSON_KEYS.Mail.SENDER, getSender().getJSON());
			self.put(Constants.JSON_KEYS.Mail.RECEIVER, getReceiver().getJSON());
		} catch (JSONException ex) {
			ex.printStackTrace();
			return null;
		}
		return self;
	}

}
