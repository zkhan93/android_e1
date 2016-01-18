package io.github.zkhan93.mailui.server.util;

import java.io.BufferedReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class Util {

	public static JSONObject getJSON(BufferedReader br) {
		JSONObject result = null;
		StringBuilder str_data = new StringBuilder();
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
				str_data.append(line);
			}
			System.out.println(str_data.toString());
			result = new JSONObject(str_data.toString());
		} catch (JSONException ex) {
			ex.printStackTrace();
			result = null;
		} catch (IOException ex) {
			ex.printStackTrace();
			result = null;
		}
		return result;
	}
}
