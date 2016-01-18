package io.github.zkhan93.mailui.server.model;

public class Mail {
	private String subject,body;
	private User sender,receiver;
	private long time;
	
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
	private JSONObject getJSONSender(){
		getSender().getJSON();
	}
	
}
