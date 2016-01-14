package io.github.zkhan93.loginpage.server;

import io.github.zkhan93.loginpage.server.dao.Dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class Home
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Home() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * if the request is a GET request send invalid response
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = new PrintWriter(response.getOutputStream());
		response.setContentType("application/json");
		JSONObject jResponse = new JSONObject();
		try {
			jResponse.put("Authentication", false);
			jResponse.put("error", "Invalid Request");
		} catch (JSONException ex) {
			ex.printStackTrace();
		}
		out.print(jResponse.toString());
		out.flush();
	}

	/**
	 * if the request is POST request the check for post data convert it into JSONObject,
	 * check username and password and response accordingly
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//reading data from request stream and converting into jsonObject
		StringBuilder str_data = new StringBuilder();
		String line = null;
		JSONObject jsonData;
		try {
			BufferedReader br = request.getReader();
			while ((line = br.readLine()) != null) {
				str_data.append(line);
			}
			System.out.println(str_data.toString());
			jsonData = new JSONObject(str_data.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			jsonData = null;
		}
		JSONObject jResponse = new JSONObject();
		try {
			if (jsonData != null) {
				//reading username and password from JSONObject created earlier
				String username, password;
				username = jsonData.getString("username");
				password = jsonData.getString("password");
				//checking for correctness and response accordingly
				boolean authenticated=new Dao().checkUser(username, password);
				jResponse.put("Authentication",authenticated);
				if(authenticated){
					jResponse.put("msg","Welcome");
				}else{
					jResponse.put("error","Invalid credentials");
				}
			} else {
				jResponse.put("Authentication", false);
				jResponse.put("error", "No data received by server");
			}
		} catch (JSONException ex) {
			ex.printStackTrace();
		}
		//writing data to response
		PrintWriter out = new PrintWriter(response.getOutputStream());
		response.setContentType("application/json");
		out.print(jResponse.toString());
		out.flush();
	}

}
