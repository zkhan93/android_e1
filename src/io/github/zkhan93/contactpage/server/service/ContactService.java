package io.github.zkhan93.contactpage.server.service;

import io.github.zkhan93.contactpage.server.dao.ContactDao;
import io.github.zkhan93.contactpage.server.model.Contact;
import io.github.zkhan93.contactpage.server.util.Constants;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContactService {

	public ContactService() {
		// TODO Auto-generated constructor stub
	}

	public JSONArray getContacts() {
		JSONArray jContacts = new JSONArray();
		JSONObject jContact = null;
		List<Contact> contacts = new ContactDao().getContacts();
		if (contacts != null) {
			for (Contact contact : contacts) {
				try {
					jContact = new JSONObject();
					jContact.put(Constants.JSON_KEYS.Contacts.ID,
							contact.getId());
					jContact.put(Constants.JSON_KEYS.Contacts.NAME,
							contact.getName());
					jContact.put(Constants.JSON_KEYS.Contacts.NUMBER,
							contact.getNumber());
					jContacts.put(jContact);
				} catch (JSONException ex) {
					ex.printStackTrace();
				}
			}
		}
		return jContacts;
	}
}
