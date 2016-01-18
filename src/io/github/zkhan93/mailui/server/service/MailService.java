package io.github.zkhan93.mailui.server.service;

import io.github.zkhan93.mailui.server.dao.MailDao;
import io.github.zkhan93.mailui.server.model.Mail;
import io.github.zkhan93.mailui.server.util.Util;

import java.util.ArrayList;

import org.json.JSONArray;

public class MailService {
	public JSONArray getMails(String username) {
		ArrayList<Mail> mails = new MailDao().getMails(username);
		if (mails != null)
			return Util.getJSONMail(mails);
		else
			return new JSONArray();
	}
}
