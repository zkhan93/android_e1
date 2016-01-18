package io.github.zkhan93.mailui.server;

import io.github.zkhan93.mailui.server.service.LoginService;
import io.github.zkhan93.mailui.server.service.MailService;
import io.github.zkhan93.mailui.server.util.Constants;
import io.github.zkhan93.mailui.server.util.Util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class MailController
 */
public class MailController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MailController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		/*
		 * PrintWriter out = new PrintWriter(response.getOutputStream());
		 * response.setContentType("application/json"); JSONObject jResponse =
		 * new JSONObject(); try {
		 * jResponse.put(Constants.JSON_KEYS.AUTHENTICATION, false);
		 * jResponse.put(Constants.JSON_KEYS.ERROR, "Invalid Request"); } catch
		 * (JSONException ex) { ex.printStackTrace(); }
		 * out.print(jResponse.toString()); out.flush();
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JSONObject jRequest = Util.getJSON(request.getReader());
		String username = jRequest.optString(Constants.JSON_KEYS.USERNAME);
		String password = jRequest.optString(Constants.JSON_KEYS.PASSWORD);
		JSONObject jResponse = new JSONObject();
		
		if (new LoginService().checkUser(username, password)) {
			try {
				jResponse.put(Constants.JSON_KEYS.AUTHENTICATION, true);
				jResponse.put(Constants.JSON_KEYS.MAILS,new MailService().getMails(username)); 
			} catch (JSONException ex) {
				ex.printStackTrace();
			}
		} else {
			try {
				jResponse.put(Constants.JSON_KEYS.AUTHENTICATION, false);
				jResponse.put(Constants.JSON_KEYS.ERROR,
						"Authentication failure!");
			} catch (JSONException ex) {
				ex.printStackTrace();
			}
		}
		PrintWriter out=response.getWriter();
		out.print(jResponse.toString());
		out.flush();
		System.out.println(jResponse);
	}

}
